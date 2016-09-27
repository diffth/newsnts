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
    private Context mContext;

    private Integer[] mThumbIds = {
      R.drawable.noun_425156_cc, R.drawable.noun_629576_cc,
            R.drawable.noun_578286_cc, R.drawable.noun_425156_cc, R.drawable.noun_629576_cc,
            R.drawable.noun_578286_cc, R.drawable.noun_629576_cc
    };

    public ImgAdapter(Context c) {
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
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
        ImageView imageView;
        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(300, 300));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(mThumbIds[position]);

        return imageView;
    }
}
