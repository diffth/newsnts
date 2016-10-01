package com.example.song.newsnts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

/**
 * Created by EOM on 2015-08-20.
 */
public class Fragment1 extends Fragment {

    ListView listView1;
    FriendChatListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment1, container, false);


        // 리스트뷰 객체 참조

        // 어댑터 객체 생성
        /*adapter = new FriendChatListAdapter(this);

        // 아이템 데이터 만들기
        Resources res = getResources();

        adapter.addItem(new FriendChatListItem(res.getDrawable(R.drawable.user), "송준석", "오늘따라 피곤하네..", "9"));
        adapter.addItem(new FriendChatListItem(res.getDrawable(R.drawable.user), "황지순", "조장님 php안되여..", "15"));
        adapter.addItem(new FriendChatListItem(res.getDrawable(R.drawable.user), "김서영", "크리스마스엔 오키나와!", "9"));
        adapter.addItem(new FriendChatListItem(res.getDrawable(R.drawable.user), "오세헌", "가정의 평화를...", "4"));
        adapter.addItem(new FriendChatListItem(res.getDrawable(R.drawable.user), "홍석천", " ", "1"));
        adapter.addItem(new FriendChatListItem(res.getDrawable(R.drawable.user), "아이유", " ", "4"));
        adapter.addItem(new FriendChatListItem(res.getDrawable(R.drawable.user), "강타", " ", "5 "));
        adapter.addItem(new FriendChatListItem(res.getDrawable(R.drawable.user), "백현", " ", "12"));
        adapter.addItem(new FriendChatListItem(res.getDrawable(R.drawable.user), "서현", " ", "3"));
        adapter.addItem(new FriendChatListItem(res.getDrawable(R.drawable.user), "김희철", " ", "1"));
        adapter.addItem(new FriendChatListItem(res.getDrawable(R.drawable.user), "김경훈", " ", "3"));
        adapter.addItem(new FriendChatListItem(res.getDrawable(R.drawable.user), "강호동", " ", "4"));
       

        // 리스트뷰에 어댑터 설정
        listView1.setAdapter(adapter);*/

        // 새로 정의한 리스너로 객체를 만들어 설정
       /* listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FriendChatListItem curItem = (FriendChatListItem) adapter.getItem(position);
                String[] curData = curItem.getData();

                Toast.makeText(getApplicationContext(), "Selected : " + curData[0], Toast.LENGTH_LONG).show();

            }

        });*/


        return rootView;
    }
}
