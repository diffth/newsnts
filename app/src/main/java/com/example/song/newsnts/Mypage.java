package com.example.song.newsnts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Mypage extends AppCompatActivity {

    ImageView profile_Img;
    TextView my_friend;
    TextView my_item;
    Button write_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage);

        ImageView profile_Img = (ImageView)findViewById(R.id.profile_Img);
        TextView my_friend = (TextView)findViewById(R.id.my_friend);
        TextView my_item = (TextView)findViewById(R.id.my_item);
        Button write_Btn = (Button)findViewById(R.id.write_Btn);

        write_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage.this, Mypage_Write.class);
                startActivity(intent);
            }
        });

    }
}
