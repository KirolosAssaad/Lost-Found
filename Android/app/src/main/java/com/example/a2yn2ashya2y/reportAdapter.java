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

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.security.spec.ECField;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class reportAdapter extends ArrayAdapter<String> {

    //    String[] ItemsName;
//    int Images;
    Context context;
    JSONArray allOBJS;

    //constructor to initialize value
    public reportAdapter(Context context, JSONArray allOBJS) {
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
        ImageView item_image;
        TextView item_name;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();

        //check if the view is being reused
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.reportitem,parent,false);

            //data population
            holder.item_image = (ImageView) convertView.findViewById(R.id.itemImage);
            holder.item_name = (TextView) convertView.findViewById(R.id.itemName);
            convertView.setTag(holder); //Initialize view
        }
        else { //if not null get tag, don't initialize
            holder = (ViewHolder) convertView.getTag();

        }

        JSONObject object = new JSONObject();


        try{
            object = allOBJS.getJSONObject(position);
            Log.println(Log.DEBUG, object.getString("Name"), "Name of object");
            Log.println(Log.DEBUG, object.getString("image"), "Image Path");
            String x = object.getString("image");
            File f=new File(x);
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            holder.item_image.setImageBitmap(b);
            holder.item_name.setText(object.getString("category")+" "+(position+1));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        Button report = (Button) convertView.findViewById(R.id.report);

        report.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                AlertDialog.Builder alert = new AlertDialog.Builder(context);
//                alert.setTitle("Report Item");
//                alert.setMessage("Report request has been sent to Admin, You will be notified soon!");
//                alert.setPositiveButton("OK",null);
//                alert.show();

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Report request has been sent to Admin, You will be notified soon!");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(context, choicesActivity.class);
                        Bundle extras = ((Activity) context).getIntent().getExtras();
                        intent.putExtras(extras);
                        context.startActivity(intent);
                    }
                });
                AlertDialog alert = builder.create();
                alert.show();


            }
        });

        //sets data
        return convertView; //update views
    }


}
