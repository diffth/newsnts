package com.example.song.newsnts;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

public class Fragment2 extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView1;
    FriendChatListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView1 = (ListView) findViewById(R.id.listView1);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowTitleEnabled(false);

        adapter = new FriendChatListAdapter(this);

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

        listView1.setAdapter(adapter);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FriendChatListItem curItem = (FriendChatListItem) adapter.getItem(position);
                String[] curData = curItem.getData();

                Toast.makeText(getApplicationContext(), "Selected : " + curData[0], Toast.LENGTH_LONG).show();

            }

        });

    }
}
