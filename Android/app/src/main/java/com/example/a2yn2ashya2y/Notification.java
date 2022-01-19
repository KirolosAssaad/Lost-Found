package com.example.a2yn2ashya2y;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
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

public class Notification extends AppCompatActivity {
    String category;
    notificationAdapter adapter2;
    JSONArray jArray;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        jArray = new JSONArray();

        adapter2 = new notificationAdapter(Notification.this, jArray);

        Bundle extras = getIntent().getExtras();
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Categories2, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        ListView list_view = (ListView) findViewById(R.id.notificationItemsList);

        try {
            URL url = new URL("http://192.168.100.39:3000/notify");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new BufferedOutputStream(con.getOutputStream()), StandardCharsets.UTF_8));
            writer.write(("phonenum="+extras.getString("userNumber")));
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
                Toast.makeText(Notification.this, "NO ITEMS FOUND",
                        Toast.LENGTH_SHORT).show();
            }
            else {
                adapter2 = new notificationAdapter(Notification.this, jArray);
                list_view.setAdapter(adapter2);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        Button add = (Button) findViewById(R.id.addNotification);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

                Log.i("GTOUTOUT", "Nothing Selected");
            }
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int i, long id)  {

                switch (i){
                    case 0:
                    {
                        category = "";
                        add.setEnabled(false);
                        break;
                    }
                    case 1:
                    {
                        category = "IDs";
                        add.setEnabled(true);

                        break;
                    }
                    case 2:
                    {
                        category = "Keys";
                        add.setEnabled(true);

                        break;
                    }
                    case 3:
                    {
                        category = "Technology";
                        add.setEnabled(true);

                        break;
                    }
                    default:
                    {
                        category = "Other";
                        add.setEnabled(true);

                        break;
                    }
                }
            }
        });


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try
                {
                    URL url = new URL("http://192.168.100.39:3000/addNotify");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                            new BufferedOutputStream(con.getOutputStream()), StandardCharsets.UTF_8));
                    writer.write(("phonenum="+extras.getString("userNumber")+"&category="+category));
                    writer.flush();
                    writer.close();
                    con.getResponseCode();


                    URL url2 = new URL("http://192.168.100.39:3000/notify");
                    HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
                    con2.setRequestMethod("POST");
                    con2.setDoOutput(true);
                    con2.setDoInput(true);
                    BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(
                            new BufferedOutputStream(con2.getOutputStream()), StandardCharsets.UTF_8));
                    writer2.write(("phonenum="+extras.getString("userNumber")));
                    writer2.flush();
                    writer2.close();

                    BufferedReader in2 = new BufferedReader(new InputStreamReader(con2.getInputStream()));
                    String decodedString2 = in2.readLine();
                    con2.getResponseCode();
                    JSONArray x = new JSONArray(decodedString2);


                    adapter2 = new notificationAdapter(Notification.this, x);
                    list_view.setAdapter(adapter2);

                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(Notification.this, choicesActivity.class);
        intent.putExtras(getIntent().getExtras());
        startActivity(intent);
    }

}