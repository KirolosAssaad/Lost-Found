package com.example.a2yn2ashya2y;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.security.spec.ECField;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class notificationAdapter extends ArrayAdapter<String> {
    ViewHolder holder;
    Context context;
    JSONArray allOBJS;

    //constructor to initialize value
    public notificationAdapter (Context context, JSONArray allOBJS) {
        super(context, R.layout.reportitem);
        this.allOBJS = allOBJS;
        this.context = context;
    }

    @Override
    public int getCount() {
        return allOBJS.length();
    }

    // reduce number of calling findViewById() -> speed up performance
    static class ViewHolder {
        TextView item_name;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        holder = new ViewHolder();

        //check if the view is being reused
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.notificationitem,parent,false);

            //data population
            holder.item_name = (TextView) convertView.findViewById(R.id.notificationCategory);
            convertView.setTag(holder); //Initialize view
        }
        else { //if not null get tag, don't initialize
            holder = (ViewHolder) convertView.getTag();

        }

        JSONObject object = new JSONObject();

        try{
            object = allOBJS.getJSONObject(position);
            holder.item_name.setText(object.getString("category"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        Button delete = (Button) convertView.findViewById(R.id.deleteButton);

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    URL url = new URL("http://192.168.100.39:3000/deleteNotify");
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(
                            new BufferedOutputStream(con.getOutputStream()), StandardCharsets.UTF_8));
                    Bundle extras = ((Activity) context).getIntent().getExtras();
                    writer.write(("category=" + allOBJS.getJSONObject(position).getString("category") + "&phonenum=" + allOBJS.getJSONObject(position).getString("phonenum")));
                    writer.flush();
                    writer.close();

                    con.getResponseCode();

                    Intent intent = new Intent(context, Notification.class);
                    intent.putExtras(extras);
                    context.startActivity(intent);


                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }

            }
        });

        //sets data
        return convertView; //update views
    }


}
