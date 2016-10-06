package com.example.song.newsnts;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class chat extends AppCompatActivity {

    Button set;
    Button out;
    Button voice;
    Button camera;
    Button sendbt;
    Button prebt;
    Button menubt;
    TextView chatTextview;
    EditText textmsg;

    Button image;
    private Context mContext;
    private ImageView mImgPhoto = null;

    boolean isPageOpen = false;
    Animation translateLeftAnim;
    Animation translateRightAnim;
    LinearLayout slidingPage01;

    String urlAddress = "http://52.69.109.184/chatshow.php";
    Handler handler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        image = (Button) findViewById(R.id.image);
        sendbt = (Button) findViewById(R.id.sendbt);
        prebt = (Button) findViewById(R.id.prebt);
        menubt = (Button) findViewById(R.id.menubt);
        textmsg = (EditText) findViewById(R.id.textmsg);
        chatTextview = (TextView) findViewById(R.id.chatTextview);

        slidingPage01 = (LinearLayout) findViewById(R.id.slidingPage01);


        translateLeftAnim = AnimationUtils.loadAnimation(this, R.anim.translate_left);
        translateRightAnim = AnimationUtils.loadAnimation(this, R.anim.translate_right);

        SlidingPageAnimationListener animListener = new SlidingPageAnimationListener();
        translateLeftAnim.setAnimationListener(animListener);
        translateRightAnim.setAnimationListener(animListener);

        image.setOnClickListener(new View.OnClickListener(){ //메뉴에서 사진보내기 버튼
            public void onClick(View v){
                Intent intent = null;
                intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1001);
            }
        });

        camera = (Button) findViewById(R.id.camera);
        camera.setOnClickListener(new View.OnClickListener(){
            public  void onClick(View v){
                Intent intent = new Intent();
                intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 1002);


            }
        });

        voice = (Button) findViewById(R.id.voice);
        voice.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
            Intent intent1 = new Intent(getBaseContext(),com.example.song.newsnts.chat_voice.class);
                startActivity(intent1);
            }
        });
        set = (Button) findViewById(R.id.set);
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getBaseContext(),com.example.song.newsnts.chat_settiing.class);
                startActivity(intent2);
            }
        });

        out = (Button) findViewById(R.id.out);
        out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(chat.this);
                alertDialog.setTitle("대화방 나가기");
                alertDialog.setMessage("정말로 대화방을 나가시겠습니까?");
                alertDialog.setPositiveButton("나갈래요",null);
                alertDialog.setNegativeButton("아니요",null);
                alertDialog.show();

            }
        });
        sendbt.setOnClickListener(new View.OnClickListener() { //전송버튼
            public void onClick(View v) {

                insert();
                loadHtml();
            }
        });

    }
    public void insert() {

        String msg = textmsg.getText().toString();


        insertToDatabase(msg);
    }

    private void insertToDatabase(String msg){
        class InsertData extends AsyncTask<String, Void, String>{
            //   ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //   loading = ProgressDialog.show(userFormView.this, "Please Wait", null, true, true);
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String msg = (String)params[0];


                    String link="http://52.69.109.184/chat.php";
                    String data = URLEncoder.encode("msg", "UTF-8") + "=" + URLEncoder.encode(msg, "UTF-8");

                    Log.i("확인", data+"\n" );

                    URL url = new URL(link);
                    HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                    conn.setDoInput(true);
                    conn.setDoOutput(true);
                    OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

                    wr.write( data );
                    Log.i("확인", data+"\n");
                    wr.flush();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuilder sb = new StringBuilder();
                    String line = null;

                    // Read Server Response
                    while((line = reader.readLine()) != null)
                    {
                        sb.append(line);
                        break;
                    }
                    Log.i("확인", sb.toString());
                    return sb.toString();
                }catch(Exception e){
                    return new String("Exception: " + e.getMessage());
                }

            }
        }

        InsertData task = new InsertData();
        task.execute(msg);
    }

    public void onButton1Clicked(View v) { //메뉴버튼 누르기

        if (isPageOpen) {
            slidingPage01.startAnimation(translateRightAnim);
        } else {
            slidingPage01.setVisibility(View.VISIBLE);
            slidingPage01.startAnimation(translateLeftAnim);
        }
    }
    private class SlidingPageAnimationListener implements Animation.AnimationListener {

        public void onAnimationEnd(Animation animation) {
            if (isPageOpen) {
                slidingPage01.setVisibility(View.INVISIBLE);

                isPageOpen = false;
            } else {
                isPageOpen = true;
            }
        }

        public void onAnimationRepeat(Animation animation) {

        }

        public void onAnimationStart(Animation animation) {

        }

    }
    void loadHtml() { // 웹에서 html 읽어오기
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                final StringBuffer sb = new StringBuffer();

                try {
                    URL url = new URL(urlAddress);
                    HttpURLConnection conn =
                            (HttpURLConnection)url.openConnection();// 접속
                    if (conn != null) {
                        conn.setConnectTimeout(2000);
                        conn.setUseCaches(false);
                        if (conn.getResponseCode()
                                ==HttpURLConnection.HTTP_OK){
                            //    데이터 읽기
                            BufferedReader br
                                    = new BufferedReader(new InputStreamReader
                                    (conn.getInputStream(),"euc-kr"));//"utf-8"
                            while(true) {

                                String line = br.readLine();
                                if (line == null) break;
                                sb.append(line+"\n");
                            }
                            br.close(); // 스트림 해제
                        }
                        conn.disconnect(); // 연결 끊기
                    }
                    // 값을 출력하기
                    Log.d("test", sb.toString());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            chatTextview.setText(sb.toString());
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start(); // 쓰레드 시작
    }



}
