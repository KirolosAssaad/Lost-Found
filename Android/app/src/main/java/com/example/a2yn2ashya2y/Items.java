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

public class Items extends AppCompatActivity {
    JSONArray jArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_items);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#153F27"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        Bundle extras = this.getIntent().getExtras();
        String CategoryName = extras.getString("Categories");
        getSupportActionBar().setTitle(CategoryName);

        ListView list_view = (ListView) findViewById(R.id.itemsList);

        String[] itemName = null;
        int itemImage;

        try {
            URL url = new URL("http://192.168.100.39:3000/objectFetch");
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

            jArray = new JSONArray(decodedString);
            Log.println(Log.DEBUG, decodedString, "QUERY RESULT2");
            Log.println(Log.DEBUG, jArray.toString(), "QUERY RESULT");

            Log.println(Log.DEBUG, decodedString, "Query Result");
            if(decodedString.equals("[]"))
            {
                Toast.makeText(Items.this, "NO ITEMS FOUND",
                        Toast.LENGTH_SHORT).show();
            }
            else {

                ItemsAdapter adapter = new ItemsAdapter(Items.this, jArray);
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
                Intent intent = new Intent(Items.this, Verification.class);
                String OID= null;
                try {

                    OID = jArray.getJSONObject(i).getString("objID");
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                intent.putExtra("Categories", CategoryName); //pass the category name
                extras.putString("objID", OID);
//                intent.putExtra("objID", OID);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Items.this, categories.class);
        intent.putExtras(getIntent().getExtras());
        startActivity(intent);
    }


}