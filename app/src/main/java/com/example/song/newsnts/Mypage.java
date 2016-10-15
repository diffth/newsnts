package com.example.song.newsnts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

public class Mypage extends Activity {

    ImageView profile_Img;
    TextView my_friend;
    TextView my_item;
    Button write_Btn;
    GridView my_album;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        ImageView profile_Img = (ImageView)findViewById(R.id.profile_Img);
        TextView my_friend = (TextView)findViewById(R.id.my_friend);
        TextView my_item = (TextView)findViewById(R.id.my_item);
        Button write_Btn = (Button)findViewById(R.id.write_Btn);
        GridView my_album = (GridView)findViewById(R.id.my_album);
        my_album.setAdapter(new ImgAdapter(this));

        write_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage.this, Mypage_Write.class);
                startActivity(intent);
            }
        });

        my_album.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent intent2 = new Intent(Mypage.this, Mypage_Cardview.class);
                startActivity(intent2);
            }
        });
    }

}
