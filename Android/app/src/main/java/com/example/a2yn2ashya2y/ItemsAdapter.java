package com.example.a2yn2ashya2y;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.InputStream;

public class ItemsAdapter extends ArrayAdapter<String> {

    JSONArray allObjs;
    Context context;

    //constructor to initialize value
    public ItemsAdapter(Context context, JSONArray allObjs) {
        super(context, R.layout.itemlist);
        this.allObjs = allObjs;
        this.context = context;
    }

    @Override
    public int getCount() {
        return allObjs.length();
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.itemlist,parent,false);

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
            object = allObjs.getJSONObject(position);
            Log.println(Log.DEBUG, object.getString("name"), "Name of object");
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


        //sets data
        return convertView; //update views
    }
}
