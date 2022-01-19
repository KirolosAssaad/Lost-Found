package com.example.a2yn2ashya2y;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class Verification extends AppCompatActivity {
    EditText ans1;
    EditText ans2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);
        Bundle extras = this.getIntent().getExtras();
        ColorDrawable colorDrawable = new ColorDrawable(Color.parseColor("#153F27"));
        getSupportActionBar().setBackgroundDrawable(colorDrawable);
        getSupportActionBar().setTitle("Verification");

        String catengoryName = getIntent().getExtras().getString("Categories");

        TextView quest1 = (TextView) findViewById(R.id.seqQues1);
        ans1 = (EditText) findViewById(R.id.seqAns1);
        TextView quest2 = (TextView) findViewById(R.id.seqQues2);
        ans2 = (EditText) findViewById(R.id.seqAns2);
        Button submitBut = (Button) findViewById(R.id.submitAddForm);

        switch (catengoryName){
            case "IDs":
                quest1.setText("What is the name on the ID?");
                quest1.setEnabled(true);
                quest1.setTextColor(getResources().getColor(R.color.black));
                quest1.setBackgroundColor(getResources().getColor(R.color.white));

                ans1.setEnabled(true);
                ans1.setHint("Name on ID");

                quest2.setText("What is the ID number?");
                quest2.setEnabled(true);
                quest2.setTextColor(getResources().getColor(R.color.black));
                quest2.setBackgroundColor(getResources().getColor(R.color.white));

                ans2.setEnabled(true);
                ans2.setHint("SID");


                submitBut.setEnabled(true);
                submitBut.setText(R.string.submit);
                submitBut.setBackgroundColor(Color.parseColor("#2D423D"));
                break;
            case "Keys":
                quest1.setText("How many keys does it have?");
                quest1.setEnabled(true);
                quest1.setTextColor(getResources().getColor(R.color.black));
                quest1.setBackgroundColor(getResources().getColor(R.color.white));

                ans1.setEnabled(true);
                ans1.setHint("Number of keys");

                quest2.setText("Does it have a keychain? If so, what is on it");
                quest2.setEnabled(true);
                quest2.setTextColor(getResources().getColor(R.color.black));
                quest2.setBackgroundColor(getResources().getColor(R.color.white));

                ans2.setEnabled(true);
                ans2.setHint("Keychain");

                submitBut.setEnabled(true);
                submitBut.setText(R.string.submit);
                submitBut.setBackgroundColor(Color.parseColor("#2D423D"));

                break;
            case "Technology":
                quest1.setText("What is the model of the Product?");
                quest1.setEnabled(true);
                quest1.setTextColor(getResources().getColor(R.color.black));
                quest1.setBackgroundColor(getResources().getColor(R.color.white));

                ans1.setEnabled(true);
                ans1.setHint("Product Model");

                quest2.setText("Does it have any unique features?");
                quest2.setEnabled(true);
                quest2.setTextColor(getResources().getColor(R.color.black));
                quest2.setBackgroundColor(getResources().getColor(R.color.white));

                ans2.setEnabled(true);
                ans2.setHint("Unique Features");


                submitBut.setEnabled(true);
                submitBut.setText(R.string.submit);
                submitBut.setBackgroundColor(Color.parseColor("#2D423D"));

                break;
            case "Other":
                quest1.setText("What is the Product?");
                quest1.setEnabled(true);
                quest1.setTextColor(getResources().getColor(R.color.black));
                quest1.setBackgroundColor(getResources().getColor(R.color.white));

                ans1.setEnabled(true);
                ans1.setHint("Product Model");

                quest2.setText("hat is the color of the product?");
                quest2.setEnabled(true);
                quest2.setTextColor(getResources().getColor(R.color.black));
                quest2.setBackgroundColor(getResources().getColor(R.color.white));

                ans2.setEnabled(true);
                ans2.setHint("Product Color");

                submitBut.setEnabled(true);
                submitBut.setText(R.string.submit);
                submitBut.setBackgroundColor(Color.parseColor("#2D423D"));

                break;
        }

        submitBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String ID = getIntent().getExtras().getString("objID");

                try{
                    URL url = new URL("http://192.168.100.39:3000/answerFetch");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                            new BufferedOutputStream(con.getOutputStream()), StandardCharsets.UTF_8));
                    writer.write(("objID="+ID));
                    writer.flush();
                    writer.close();

                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String decodedString = in.readLine();
                    con.getResponseCode();
                    JSONArray jarr = new JSONArray(decodedString);
                    Log.println(Log.DEBUG, jarr.getJSONObject(0).toString(), "VERIFICATION ANSWERS");
                    JSONObject jObj = jarr.getJSONObject(0);
                    String answer1  = jObj.getString("answer1");
                    String answer2  = jObj.getString("answer2");

                    Log.println(Log.DEBUG, ans1.getText().toString(), "quest1");
                    Log.println(Log.DEBUG, ans2.getText().toString(), "quest2");
                    Log.println(Log.DEBUG, answer1, "ans1");
                    Log.println(Log.DEBUG, answer2, "ans2");


                    if(ans1.getText().toString().toLowerCase().equals(answer1) &&
                            ans2.getText().toString().toLowerCase().equals(answer2))
                    {
                        URL url2 = new URL("http://192.168.100.39:3000/markFound");
                        HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
                        con2.setRequestMethod("POST");
                        con2.setDoOutput(true);
                        BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(
                                new BufferedOutputStream(con2.getOutputStream()), StandardCharsets.UTF_8));
                        writer2.write(("objID="+ID));
                        writer2.flush();
                        writer2.close();

                        con2.getResponseCode();

                        Intent intent = new Intent(Verification.this,ContactInfo.class);
                        intent.putExtras(extras);
                        startActivity(intent);

                    }
                    else
                    {
                        Toast.makeText(Verification.this, "Answers Incorrect",
                                Toast.LENGTH_SHORT).show();
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
        super.onBackPressed();
        Intent intent = new Intent(Verification.this, Items.class);
        intent.putExtras(getIntent().getExtras());
        startActivity(intent);
    }
}