package com.example.a2yn2ashya2y;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.telephony.SmsManager;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Locale;

public class addItem extends AppCompatActivity {
    ImageView img;
    Uri selectedImageUri;
    final int SELECT_PICTURE = 200;
    String category;
    String name;
    boolean imgAdded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        name = category;
        getSupportActionBar().setBackgroundDrawable( new ColorDrawable(Color.parseColor("#2D423D")));

        Bundle extras = this.getIntent().getExtras();
        category = new String();
        img = (ImageView) findViewById(R.id.addImage);
        TextView quest1 = (TextView) findViewById(R.id.seqQues1);
        EditText ans1 = (EditText) findViewById(R.id.seqAns1);
        TextView quest2 = (TextView) findViewById(R.id.seqQues2);
        EditText ans2 = (EditText) findViewById(R.id.seqAns2);
        Button submitBut = (Button) findViewById(R.id.submitAddForm);
        Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.Categories2, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Intent= new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(Intent,1888);
            }
        });


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

                        quest1.setText("");
                        quest1.setEnabled(false);
                        quest1.setTextColor(getResources().getColor(R.color.white));
                        quest1.setBackgroundColor(getResources().getColor(R.color.white));

                        ans1.setEnabled(false);
                        ans1.setHint("");
                        ans1.setInputType(InputType.TYPE_CLASS_TEXT);

                        quest2.setText("");
                        quest2.setEnabled(false);
                        quest2.setTextColor(getResources().getColor(R.color.white));
                        quest2.setBackgroundColor(getResources().getColor(R.color.white));

                        ans2.setEnabled(false);
                        ans2.setHint("");
                        ans2.setInputType(InputType.TYPE_CLASS_TEXT);

                        submitBut.setEnabled(false);
                        submitBut.setText("");
                        submitBut.setBackgroundColor(Color.WHITE);

                        img.setVisibility(View.INVISIBLE);
                        img.setEnabled(false);

                        break;

                    }
                    case 1:
                    {
                        category = "IDs";

                        quest1.setText("What is the first name on the ID?");
                        quest1.setEnabled(true);
                        quest1.setTextColor(getResources().getColor(R.color.black));
                        quest1.setBackgroundColor(getResources().getColor(R.color.white));
                        ans1.setInputType(InputType.TYPE_CLASS_TEXT);

                        ans1.setEnabled(true);
                        ans1.setHint("First Name on ID");

                        quest2.setText("What is the ID number?");
                        quest2.setEnabled(true);
                        quest2.setTextColor(getResources().getColor(R.color.black));
                        quest2.setBackgroundColor(getResources().getColor(R.color.white));

                        ans2.setEnabled(true);
                        ans2.setHint("SID");
                        ans2.setInputType(InputType.TYPE_CLASS_NUMBER);

                        submitBut.setEnabled(true);
                        submitBut.setText(R.string.submit);
                        submitBut.setBackgroundColor(Color.parseColor("#2D423D"));

                        img.setVisibility(View.VISIBLE);
                        img.setEnabled(true);


                        break;
                    }
                    case 2:
                    {
                        category = "Keys";

                        quest1.setText("How many keys does it have?");
                        quest1.setEnabled(true);
                        quest1.setTextColor(getResources().getColor(R.color.black));
                        quest1.setBackgroundColor(getResources().getColor(R.color.white));

                        ans1.setEnabled(true);
                        ans1.setHint("Number of keys");
                        ans1.setInputType(InputType.TYPE_CLASS_NUMBER);

                        quest2.setText("Does it have a keychain? If so, what is on it");
                        quest2.setEnabled(true);
                        quest2.setTextColor(getResources().getColor(R.color.black));
                        quest2.setBackgroundColor(getResources().getColor(R.color.white));

                        ans2.setEnabled(true);
                        ans2.setHint("Keychain");
                        ans2.setInputType(InputType.TYPE_CLASS_TEXT);

                        submitBut.setEnabled(true);
                        submitBut.setText(R.string.submit);
                        submitBut.setBackgroundColor(Color.parseColor("#2D423D"));

                        img.setVisibility(View.VISIBLE);
                        img.setEnabled(true);

                        break;
                    }
                    case 3:
                    {
                        category = "Technology";

                        quest1.setText("What is the model of the Product?");
                        quest1.setEnabled(true);
                        quest1.setTextColor(getResources().getColor(R.color.black));
                        quest1.setBackgroundColor(getResources().getColor(R.color.white));

                        ans1.setEnabled(true);
                        ans1.setHint("Product Model");
                        ans1.setInputType(InputType.TYPE_CLASS_TEXT);

                        quest2.setText("Does it have any unique features?");
                        quest2.setEnabled(true);
                        quest2.setTextColor(getResources().getColor(R.color.black));
                        quest2.setBackgroundColor(getResources().getColor(R.color.white));

                        ans2.setEnabled(true);
                        ans2.setHint("Unique Features");
                        ans2.setInputType(InputType.TYPE_CLASS_TEXT);

                        submitBut.setEnabled(true);
                        submitBut.setText(R.string.submit);
                        submitBut.setBackgroundColor(Color.parseColor("#2D423D"));

                        img.setVisibility(View.VISIBLE);
                        img.setEnabled(true);

                        break;
                    }
                    default:
                    {
                        category = "Other";

                        quest1.setText("What is the Product?");
                        quest1.setEnabled(true);
                        quest1.setTextColor(getResources().getColor(R.color.black));
                        quest1.setBackgroundColor(getResources().getColor(R.color.white));

                        ans1.setEnabled(true);
                        ans1.setHint("Product Model");
                        ans1.setInputType(InputType.TYPE_CLASS_TEXT);

                        quest2.setText("What is the color of the product?");
                        quest2.setEnabled(true);
                        quest2.setTextColor(getResources().getColor(R.color.black));
                        quest2.setBackgroundColor(getResources().getColor(R.color.white));

                        ans2.setEnabled(true);
                        ans2.setHint("Product Color");
                        ans2.setInputType(InputType.TYPE_CLASS_TEXT);


                        submitBut.setEnabled(true);
                        submitBut.setText(R.string.submit);
                        submitBut.setBackgroundColor(Color.parseColor("#2D423D"));

                        img.setVisibility(View.VISIBLE);
                        img.setEnabled(true);

                        break;
                    }
                }
            }
        });

        submitBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String q1 = ans1.getText().toString();
                String q2 = ans2.getText().toString();
//                String imageString = selectedImageUri.toString();

                if( q1.equals("") || q2.equals("") || !imgAdded)
                {
                    Toast.makeText(addItem.this, "Please fill out all the questions",
                            Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                {
                    try {
                        URL url4 = new URL("http://192.168.100.39:3000/getLastObj");
                        HttpURLConnection con4 = (HttpURLConnection) url4.openConnection();
                        con4.setRequestMethod("POST");
                        con4.setDoInput(true);
                        BufferedWriter writer4 = new BufferedWriter(new OutputStreamWriter(
                                new BufferedOutputStream(con4.getOutputStream()), StandardCharsets.UTF_8));
                        writer4.write(("category=" + category));
                        writer4.flush();
                        writer4.close();

                        BufferedReader in = new BufferedReader(new InputStreamReader(con4.getInputStream()));
                        String decodedString = in.readLine();
                        con4.getResponseCode();

                        JSONArray jarr = new JSONArray(decodedString);

                        Integer imageNumber = 0;
                        Log.println(Log.DEBUG,decodedString,"decodedString");
                        if(decodedString.equals("[]")) {
                            imageNumber = 0;
                        }
                        else
                        {
                            JSONObject obj = jarr.getJSONObject(jarr.length() - 1);
                            String x = obj.getString("image");
                            String[] arrOfStr = x.split(category, 2);
                            imageNumber = Integer.parseInt(arrOfStr[arrOfStr.length - 1]);

                        }




                        URL url = new URL("http://192.168.100.39:3000/addObject");
                        HttpURLConnection con = (HttpURLConnection) url.openConnection();
                        con.setRequestMethod("POST");
                        con.setDoInput(true);

                        Bitmap bitmap=((BitmapDrawable)img.getDrawable()).getBitmap();
                        String imgPath = saveToInternalStorage(bitmap,(category+(imageNumber+1)))+"/"+ category+(imageNumber+1);

                        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                                new BufferedOutputStream(con.getOutputStream()), StandardCharsets.UTF_8));
                        writer.write("name=" + "name" + "&description=" + "x"+
                                "&image=" + imgPath + "&SID=" + extras.getString("userID")+
                                "&category=" +category);
                        writer.flush();
                        writer.close();

                        con.getResponseCode();

                        URL url2 = new URL("http://192.168.100.39:3000/addAnswer");
                        HttpURLConnection con2 = (HttpURLConnection) url2.openConnection();
                        con2.setRequestMethod("POST");
                        con2.setDoInput(true);
                        BufferedWriter writer2 = new BufferedWriter(new OutputStreamWriter(
                                new BufferedOutputStream(con2.getOutputStream()), StandardCharsets.UTF_8));
                        writer2.write(("answer1=" + q1.toLowerCase() + "&answer2=" + q2.toLowerCase()+ "&answer3=" + "x"));
                        writer2.flush();
                        writer2.close();
                        con2.getResponseCode();

                        URL link = new URL("http://192.168.100.39:3000/notifyCategory");
                        HttpURLConnection connection = (HttpURLConnection) link.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true);
                        connection.setDoInput(true);
                        BufferedWriter buffer = new BufferedWriter(new OutputStreamWriter(
                                new BufferedOutputStream(connection.getOutputStream()), StandardCharsets.UTF_8));
                        buffer.write(("category="+ category));
                        buffer.flush();
                        buffer.close();

                        BufferedReader bufferIn = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                        String StringDecoded = bufferIn.readLine();
                        connection.getResponseCode();

                        JSONArray numbers = new JSONArray(StringDecoded);
                        for (int i =0 ; i<numbers.length(); i++){
                            JSONObject phone = numbers.getJSONObject(i);
                            SmsManager smsManager = SmsManager.getDefault(); // send message to mobile
                            smsManager.sendTextMessage(phone.getString("phonenum"), null, "There is an item in "+category+" that matches the description of the item you have lost", null, null);
                        }


                        Log.d("category" , StringDecoded);
                    }
                    catch (Exception e)
                    {
                        e.printStackTrace();
                    }
                }


                Intent intent = new Intent(addItem.this,choicesActivity.class);
                intent.putExtras(extras);
                startActivity(intent);
            }
        });
    }

    private String saveToInternalStorage(Bitmap bitmapImage, String fileName){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,fileName);

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if(requestCode == 1888) {
                Bitmap bitmap = (Bitmap) data.getExtras().get("data");
                Log.println(Log.DEBUG, bitmap.toString(), "the bitmap");
                img.setImageBitmap(bitmap);
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
                byte[] byteArray = stream.toByteArray();
                imgAdded = true;
            } }
    }

}