package com.example.a2yn2ashya2y;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class ContactInfo extends AppCompatActivity {
    JSONObject jObj;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_info);
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#153F27"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("Contact Info");
        Bundle extras = this.getIntent().getExtras();
        TextView name = (TextView) findViewById(R.id.name);
        TextView number = (TextView) findViewById(R.id.number);
        TextView call = (TextView) findViewById(R.id.call);


        try {
            URL url = new URL("http://192.168.100.39:3000/findFinder");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("POST");
            con.setDoOutput(true);
            con.setDoInput(true);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                    new BufferedOutputStream(con.getOutputStream()), StandardCharsets.UTF_8));
            writer.write(("SID="+extras.getString("userID")));
            writer.flush();
            writer.close();

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String decodedString = in.readLine();
            con.getResponseCode();

            JSONArray jarr = new JSONArray(decodedString);
            jObj = jarr.getJSONObject(0);
            name.setText(jObj.getString("name"));
            number.setText(jObj.getString("phonenum"));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }


        call.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        try {
                                            Intent intent = new Intent(Intent.ACTION_DIAL);
                                            intent.setData(Uri.parse("tel:"+jObj.getString("phonenum")));
                                            startActivity(intent);

                                        }
                                        catch (Exception e)
                                        {
                                            e.printStackTrace();
                                        }
                                    }
                                }
        );

        TextView done = (TextView) findViewById(R.id.done);

        done.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent intent2 = new Intent(ContactInfo.this, choicesActivity.class);
                                        intent2.putExtras(extras);
                                        startActivity(intent2);
                                    }
                                }
        );
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(ContactInfo.this, Items.class);
        intent.putExtras(getIntent().getExtras());
        startActivity(intent);
    }
}