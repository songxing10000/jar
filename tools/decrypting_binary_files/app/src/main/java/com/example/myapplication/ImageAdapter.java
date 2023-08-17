package com.example.myapplication;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.provider.CalendarContract;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.sql.Array;
import java.util.ArrayList;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private Bitmap[] bitmapArray ;
private  ArrayList<String> imageNames;
    public ImageAdapter(Context context, Bitmap[] bitmaps, ArrayList<String> imgNames) {
        mContext = context;
        bitmapArray = bitmaps;
        imageNames = imgNames;
    }

    @Override
    public int getCount() {
        return bitmapArray.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        ImageView imageView;
//
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(150, 150));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//        imageView.setBackgroundColor(Color.GRAY);
//Bitmap img = this.bitmapArray[position];
//        imageView.setImageBitmap(img);
//        return imageView;
        LinearLayout layout;

        if (convertView == null) {
            layout = new LinearLayout(mContext);
            layout.setLayoutParams(new GridView.LayoutParams(150, 200));
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setGravity(Gravity.CENTER);

            ImageView imageView = new ImageView(mContext);
            imageView.setLayoutParams(new LinearLayout.LayoutParams(150, 150));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setBackgroundColor(Color.GRAY);
            layout.addView(imageView);

            TextView textView = new TextView(mContext);
            textView.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            textView.setGravity(Gravity.CENTER);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 8); // 设置初始字号大小，单位为sp

            textView.setText(imageNames.get(position));
            textView.setMaxLines(3); // 设置最大显示行数

            layout.addView(textView);

        } else {
            layout = (LinearLayout) convertView;
        }

        ImageView imageView = (ImageView) layout.getChildAt(0);
        imageView.setImageBitmap(bitmapArray[position]);

        return layout;
    }
}
