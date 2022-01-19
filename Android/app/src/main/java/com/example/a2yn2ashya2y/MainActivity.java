package com.example.a2yn2ashya2y;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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

public class MainActivity extends AppCompatActivity {

    //172.20.10.4
    //192.168.100.39
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#153F27"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);

        TextView login = (TextView) findViewById(R.id.login);
        EditText username = (EditText) findViewById(R.id.username);
        EditText password = (EditText) findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String getUsername = username.getText().toString();
                String getPassworrd = password.getText().toString();

                try {
                    URL url = new URL("http://192.168.100.39:3000/userVerify");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                            new BufferedOutputStream(con.getOutputStream()), StandardCharsets.UTF_8));
                    writer.write(("username="+getUsername+"&password="+getPassworrd));
                    writer.flush();
                    writer.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String decodedString = in.readLine();
                    con.getResponseCode();
//                    Log.println(Log.DEBUG, decodedString, "Query Result");
                    if(decodedString.equals("[]"))
                    {
                        Toast.makeText(MainActivity.this, "User Not Found",
                                Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        JSONArray jArray = new JSONArray(decodedString);
                        JSONObject jObj = jArray.getJSONObject(0);
                        String number = jObj.getString("phonenum");
                        String id = jObj.getString("SID");
                        Intent intent = new Intent(MainActivity.this,choicesActivity.class);
                        Bundle extras = new Bundle();
                        extras.putString("userNumber", number);
                        extras.putString("userID", id);
                        intent.putExtras(extras);
                        startActivity(intent);
                    }
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
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}
