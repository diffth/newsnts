package com.example.song.newsnts;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;

public class Comment extends AppCompatActivity {

    private ListView mListView = null;
    private Comment_Arrayadapter mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        mListView = (ListView) findViewById(R.id.mList);

        mAdapter = new Comment_Arrayadapter(this);
        mListView.setAdapter(mAdapter);

        mAdapter.addItem(getResources().getDrawable(R.drawable.noun_425156_cc), "김서영", "안녕하세요");
        mAdapter.addItem(getResources().getDrawable(R.drawable.noun_578286_cc), "황지순", "안녕하세요2");
        mAdapter.addItem(getResources().getDrawable(R.drawable.noun_629576_cc), "박주희", "안녕하세요3");
        mAdapter.addItem(null, "이미지가 null이면", "안녕하세요");
    }

    private class ViewHolder {

        public ImageView comment_Img1;
        public TextView comment_Txt1;
        public TextView comment_Txt2;

    }

    private class Comment_Arrayadapter extends BaseAdapter {

        private Context mContext = null;
        private ArrayList<Comment_ListData> mListData = new ArrayList<Comment_ListData>();

        public Comment_Arrayadapter(Context mContext) {
            super();
            this.mContext = mContext;
        }

        @Override
        public int getCount() {
            return mListData.size();
        }

        @Override
        public Object getItem(int position) {
            return mListData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                holder = new ViewHolder();

                LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.oomment_item, null);

                holder.comment_Img1 = (ImageView) convertView.findViewById(R.id.comment_Img1);
                holder.comment_Txt1 = (TextView) convertView.findViewById(R.id.comment_Txt1);
                holder.comment_Txt2 = (TextView) convertView.findViewById(R.id.comment_Txt2);

                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }

            Comment_ListData mData = mListData.get(position);

            if (mData.comment_Img1 != null) {
                holder.comment_Img1.setVisibility(View.VISIBLE);
                holder.comment_Img1.setImageDrawable(mData.comment_Img1);
            } else {
                holder.comment_Img1.setVisibility(View.GONE);
            }

            holder.comment_Txt1.setText(mData.comment_Txt1);
            holder.comment_Txt2.setText(mData.comment_Txt2);

            return convertView;
        }

        public void addItem(Drawable comment_Img1, String comment_Txt1, String comment_Txt2) {
            Comment_ListData addInfo = null;
            addInfo = new Comment_ListData();
            addInfo.comment_Img1 = comment_Img1;
            addInfo.comment_Txt1 = comment_Txt1;
            addInfo.comment_Txt2 = comment_Txt2;

            mListData.add(addInfo);
        }

        public void remove(int position) {
            mListData.remove(position);
            dataChange();
        }

        public void sort() {
            Collections.sort(mListData, Comment_ListData.ALPHA_COMPARATOR);
            dataChange();
        }

        private void dataChange() {
            mAdapter.notifyDataSetChanged();
        }

    }

}

