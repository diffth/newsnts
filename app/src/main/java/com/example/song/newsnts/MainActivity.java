package com.example.song.newsnts;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.DialogInterface.OnShowListener;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    ListView listView1;
    FriendChatListAdapter adapter;
    public static final int REQUEST_CODE_ANOTHER = 1001;
    private Dialog mDialog = null;

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

    public void onMenu1PBClick(View v){
       /* Button layout = (Button) findViewById(R.id.fab4);
        layout.setVisibility(View.VISIBLE);*/
       /* Intent intent = new Intent(getApplicationContext(),AnotherActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ANOTHER);*/

        createDialog();

    }

    public void onDiag1Click(View v){
       Toast toast = Toast.makeText(getApplicationContext(), "년" , Toast.LENGTH_LONG);
        toast.show();

    }

    private void createDialog() {
        final View innerView = getLayoutInflater().inflate(R.layout.another, null);

        mDialog = new Dialog(this);
        mDialog.setTitle("Title");
        mDialog.setContentView(innerView);

        // Back키 눌렀을 경우 Dialog Cancle 여부 설정
        mDialog.setCancelable(true);

        // Dialog 생성시 배경화면 어둡게 하지 않기
        //mDialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);

        // Dialog 밖을 터치 했을 경우 Dialog 사라지게 하기
      mDialog.setCanceledOnTouchOutside(true);

        // Dialog 밖의 View를 터치할 수 있게 하기 (다른 View를 터치시 Dialog Dismiss)
        mDialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL,
                WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL);

        // Dialog 자체 배경을 투명하게 하기
      mDialog.getWindow().setBackgroundDrawable
              (new ColorDrawable(android.graphics.Color.TRANSPARENT));

        // Dialog Cancle시 Event 받기
        mDialog.setOnCancelListener(new OnCancelListener() {

            @Override
            public void onCancel(DialogInterface dialog) {
                Toast.makeText(MainActivity.this, "cancle listener",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Dialog Show시 Event 받기
        mDialog.setOnShowListener(new OnShowListener() {

            @Override
            public void onShow(DialogInterface dialog) {
                Toast.makeText(MainActivity.this, "show listener",
                        Toast.LENGTH_SHORT).show();
            }
        });

        // Dialog Dismiss시 Event 받기
        mDialog.setOnDismissListener(new OnDismissListener() {

            @Override
            public void onDismiss(DialogInterface dialog) {
                Toast.makeText(MainActivity.this, "dismiss listener",
                        Toast.LENGTH_SHORT).show();
            }
        });

        mDialog.show();

    }

    private void dismissDialog() {
        if(mDialog != null && mDialog.isShowing()) {
            mDialog.dismiss();
        }
    }

}
