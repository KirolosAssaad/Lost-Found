package com.example.a2yn2ashya2y;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

public class CategoriesAdapter extends ArrayAdapter<String> {

    String[] Categories;
    int[] Images;
    Context context;

    //constructor to initialize value
    public CategoriesAdapter(Context context, String[] names, int[] images) {
        super(context,R.layout.categoryitem);
        this.Categories = names;
        this.Images = images;
        this.context = context;
    }

    @Override
    public int getCount() {
        return Categories.length;
    }

    // reduce number of calling findViewById() -> speed up performance
    static class ViewHolder {
        ImageView category_image;
        TextView category_name;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = new ViewHolder();

        //check if the view is being reused
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.categoryitem,parent,false);

            //data population
            holder.category_image = (ImageView) convertView.findViewById(R.id.categoryImage);
            holder.category_name = (TextView) convertView.findViewById(R.id.categoryName);
            convertView.setTag(holder); //Initialize view
        }
        else { //if not null get tag, don't initialize
            holder = (ViewHolder) convertView.getTag();
        }

        //sets data
        holder.category_image.setImageResource(Images[position]);
        holder.category_name.setText(Categories[position]);

        return convertView; //update views
    }

}
