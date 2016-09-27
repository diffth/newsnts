package com.example.song.newsnts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

public class Mypage_Cardview extends AppCompatActivity {

    TextView comment_Text;
    ListView mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage__cardview);

        comment_Text = (TextView) findViewById(R.id.comment_Text);
        mList = (ListView)findViewById(R.id.mList);

        comment_Text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Mypage_Cardview.this, Comment.class);
                startActivity(intent);
            }
        });

        mList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(Mypage_Cardview.this, Comment.class);
                startActivity(intent2);
            }
        });
    }
}
