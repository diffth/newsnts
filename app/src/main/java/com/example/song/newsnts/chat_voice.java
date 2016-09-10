package com.example.song.newsnts;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class chat_voice extends AppCompatActivity {
    final private static String RECORDED_FILE = "/sdcard/recorded.mp4";

    MediaPlayer player;
    MediaRecorder recorder;

  //  int playbackPosition = 0;
    Button record, stop, play, playstop, send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chat_voice);

        record = (Button) findViewById(R.id.record);
        stop = (Button) findViewById(R.id.stop);
        play = (Button) findViewById(R.id.play);
        playstop = (Button) findViewById(R.id.playstop);
        send = (Button) findViewById(R.id.send);

        record.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (recorder != null) {
                    recorder.stop();
                    recorder.release();
                    recorder = null;
                }// TODO Auto-generated method stub
                recorder = new MediaRecorder();
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
                recorder.setOutputFile(RECORDED_FILE);
                try {
                    Toast.makeText(getApplicationContext(), "녹음이 시작되었습니다.", Toast.LENGTH_LONG).show();

                    recorder.prepare();
                    recorder.start();
                } catch (Exception ex) {
                    Log.e("SampleAudioRecorder", "Exception : ", ex);
                }
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (recorder == null)
                    return;

                recorder.stop();
                recorder.release();
                recorder = null;

                Toast.makeText(getApplicationContext(), "녹음이 중지되었습니다.", Toast.LENGTH_LONG).show();

            }
        });

        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player != null) {
                    player.stop();
                    player.release();
                    player = null;
                }
                Toast.makeText(getApplicationContext(), "녹음된 파일을 재생합니다.", Toast.LENGTH_LONG).show();

                try {
                    player = new MediaPlayer();

                    player.setDataSource(RECORDED_FILE);
                    player.prepare();
                    player.start();


                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        playstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player == null)
                    return;
                Toast.makeText(getApplicationContext(), "재생을 중지합니다.", Toast.LENGTH_LONG).show();
                player.stop();
                player.release();
                player = null;
            }
        });
        checkDangerousPermissions();
    }
private void  checkDangerousPermissions(){
    int permissionCheck = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
    int permissionCheck2 = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
    int permissionCheck3 = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);

    if (permissionCheck == PackageManager.PERMISSION_GRANTED) {
        Toast.makeText(this, "허가를 부여했을때", Toast.LENGTH_LONG).show();
    } else {
        Toast.makeText(this, "아니면~", Toast.LENGTH_LONG).show();

        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            Toast.makeText(this, "이건 뭐지? 저장된걸 읽는다?", Toast.LENGTH_LONG).show();
        } else {
            String[] permissions = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.RECORD_AUDIO
            };

            ActivityCompat.requestPermissions(this, permissions, 1);
        }
    }
}
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 1) {
            for (int i = 0; i < permissions.length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, permissions[i] + " ///", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(this, permissions[i] + " ///.", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void playAudio(String url) throws Exception {
        killMediaPlayer();

        player = new MediaPlayer();
        player.setDataSource(url);
        player.prepare();
        player.start();
    }

    protected void onDestroy() {
        super.onDestroy();
        killMediaPlayer();
    }

    private void killMediaPlayer() {
        if (player != null) {
            try {
                player.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    protected void onPause(){
        if(recorder != null){
            recorder.release();
            recorder = null;
        }
        if (player != null){
            player.release();
            player = null;
        }

        super.onPause();

    }
}

