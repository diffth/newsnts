package com.example.song.newsnts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by EOM on 2015-08-20.
 */
public class Fragment3 extends Fragment {

    ImageView profile_Img;
    TextView sns_Txt;
    Button mypage_Btn;
    Button newspeed_Btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment3, container, false);

        profile_Img = (ImageView) rootView.findViewById(R.id.profile_Img);
        sns_Txt = (TextView) rootView.findViewById(R.id.sns_Txt);
        mypage_Btn = (Button) rootView.findViewById(R.id.mypage_Btn);
        newspeed_Btn = (Button) rootView.findViewById(R.id.newspeed_Btn);

        mypage_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), com.example.song.newsnts.Mypage.class);
                startActivity(intent);
            }
        });

        newspeed_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getActivity(), Newspeed.class);
                startActivity(intent2);
            }
        });

        return rootView;
    }
}
