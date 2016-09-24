package com.example.song.newsnts;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 * Created by 서영 on 2016-09-20.
 */
public class ImgAdapter extends BaseAdapter {
    public int getCount() {
        // TODO Auto-generated method stub
        return 0;
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView iv;

        if(convertView == null) {
            iv = new ImageView(context);
            iv.setLayoutParams(new GridView.LayoutParams(85, 85));
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            iv.setPadding(8, 8, 8, 8);
        }
        else {
            iv = (ImageView) convertView;
        }
        iv.setImageResource(imgs[position]);
        return iv;
    }
    Context context;
    private Integer[] imgs = {
            R.drawable.noun_425156_cc, R.drawable.noun_578286_cc, R.drawable.noun_629576_cc,
            R.drawable.noun_425156_cc, R.drawable.noun_578286_cc, R.drawable.noun_629576_cc};

}
