package com.example.song.newsnts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class Mypage_Write extends Activity implements View.OnClickListener {

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;

    private Uri mImageCaptureUri;
    private ImageView mPhotoImageView;
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage__write);

        mButton = (Button) findViewById(R.id.photo_Btn);
        mPhotoImageView = (ImageView) findViewById(R.id.photo_imageView);

        mButton.setOnClickListener(this);
    }
    //카메라에서 이미지 가져오기
    private void doTakePhotoAction()
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        //임시로 사용할 파일의 경로를 생성
        String url = "tmp "+ String.valueOf(System.currentTimeMillis()) + ",jpg";
        mImageCaptureUri = Uri.fromFile(new File(Environment.getExternalStorageDirectory(),url));

        intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
        //특정기기에서 사진을 저장못하는 문제가 있어 이건 주석처리
        //intent.putExtra("return-data", true);
        startActivityForResult(intent, PICK_FROM_CAMERA);
    }

    //앨범에서 이밎 가져오기
    private void doTakeAlbumAction() {
        //앨범 호출
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
        startActivityForResult(intent, PICK_FROM_ALBUM);
    }

    @Override
    protected void onActivityResult(int requestCode, )

}
