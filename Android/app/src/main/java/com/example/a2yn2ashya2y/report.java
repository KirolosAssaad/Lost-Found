package com.example.a2yn2ashya2y;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class report extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#153F27"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        String CategoryName = getIntent().getExtras().getString("Categories");
        getSupportActionBar().setTitle(CategoryName);
        ListView list_view = (ListView) findViewById(R.id.reportItemsList);

        String[] itemName = null;

        int itemImage;

        try {
            URL url = new URL("http://192.168.100.39:3000/foundFetch");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new BufferedOutputStream(con.getOutputStream()), StandardCharsets.UTF_8));
            writer.write(("category="+CategoryName));
            writer.flush();
            writer.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String decodedString = in.readLine();
            con.getResponseCode();

            JSONArray jArray = new JSONArray(decodedString);
            Log.println(Log.DEBUG, decodedString, "QUERY RESULT2");
            Log.println(Log.DEBUG, jArray.toString(), "QUERY RESULT");
//            itemName = new String[jArray.length()];
//            for(int i=0;i<itemName.length;i++)
//            {
//                JSONObject jObj = jArray.getJSONObject(i);
//                Log.println(Log.DEBUG, jObj.getString("name"), "name");
//
//                itemName[i] = jObj.getString("name");
//
//
//            }

            Log.println(Log.DEBUG, decodedString, "Query Result");
            if(decodedString.equals("[]"))
            {
                Toast.makeText(report.this, "NO ITEMS FOUND",
                        Toast.LENGTH_SHORT).show();
            }
            else {

                reportAdapter adapter = new reportAdapter(report.this, jArray);
                list_view.setAdapter(adapter);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(report.this, choicesActivity.class);
                intent.putExtra("Categories", CategoryName); //pass the category name
                startActivity(intent);
            }
        });

    }


}