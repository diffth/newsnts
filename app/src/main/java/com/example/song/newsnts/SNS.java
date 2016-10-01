package com.example.song.newsnts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SNS extends AppCompatActivity {

    ImageView profile_Img;
    TextView sns_Txt;
    Button mypage_Btn;
    Button newspeed_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sns);

        Button mypage_Btn = (Button)findViewById(R.id.mypage_Btn);
        Button newspeed_Btn = (Button)findViewById(R.id.newspeed_Btn);

        mypage_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SNS.this, Mypage.class);
                startActivity(intent);
            }
        });

        newspeed_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(SNS.this, Newspeed.class);
                startActivity(intent2);
            }
        });


    }
}
