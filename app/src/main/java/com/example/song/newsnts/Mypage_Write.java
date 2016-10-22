package com.example.song.newsnts;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class Mypage_Write extends Activity implements View.OnClickListener {

    private static final int PICK_FROM_CAMERA = 0;
    private static final int PICK_FROM_ALBUM = 1;
    private static final int CROP_FROM_CAMERA = 2;

    private Uri mImageCaptureUri;
    private ImageView mPhotoImageView;
    private Button mButton;
    public static String value = "";

    EditText my_Location;
    Button ok_Btn;
    TextView write_Txt;
    ImageView photo_imageView;
    EditText dialog_edit;
    Button photo_Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mypage__write);

        mButton = (Button) findViewById(R.id.photo_Btn);
        mPhotoImageView = (ImageView) findViewById(R.id.photo_imageView);
        my_Location = (EditText) findViewById(R.id.my_Location);
        ok_Btn = (Button) findViewById(R.id.ok_Btn);
        write_Txt = (TextView)findViewById(R.id.write_Txt);
        dialog_edit = (EditText)findViewById(R.id.dialog_edit);
        photo_Btn = (Button)findViewById(R.id.photo_Btn);


        mButton.setOnClickListener(this);


        ok_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                insert();
                Intent intent3 = new Intent(Mypage_Write.this, Mypage_Cardview.class);
                startActivity(intent3);
            }
        });

        write_Txt.setOnClickListener(this);

    }

    public void insert() {


        String content = write_Txt.getText().toString();
        String location = my_Location.getText().toString();


        insertToDatabase(content,location);
    }

    private void insertToDatabase(String content,String location){
        class InsertData extends AsyncTask<String, Void, String> {
            //   ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                //   loading = ProgressDialog.show(userFormView.this, "Please Wait", null, true, true);
            }

            @Override
            protected String doInBackground(String... params) {

                try{
                    String content = (String)params[0];
                    String location = (String)params[1];


                    String link="http://52.69.109.184/write.php";
                    String data = URLEncoder.encode("content", "UTF-8") + "=" + URLEncoder.encode(content, "UTF-8");
                    data += "&" + URLEncoder.encode("location", "UTF-8") + "=" + URLEncoder.encode(location, "UTF-8");

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
        task.execute(content,location);
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode != RESULT_OK)
        {
            return;
        }
        switch(requestCode) {
            case CROP_FROM_CAMERA: {
                // 크롭이 된 이후의 이미지를 넘겨 받습니다.
                // 이미지 뷰에 이미지를 보여준다거나 부가적인 작업 이후에 임시파일을 삭제
                final Bundle extras = data.getExtras();

                if (extras != null) {
                    Bitmap photo = extras.getParcelable("data");
                    mPhotoImageView.setImageBitmap(photo);
                }
                // 임시 파일 삭제
                File f = new File(mImageCaptureUri.getPath());
                if (f.exists()) {
                    f.delete();
                }
                break;
            }
            case PICK_FROM_ALBUM: {
                //이후의 처리가 카메라와 같으므로 일단 break 없이 진행
                //실제 코드에서는 좀 더 합리적인 방법을 선택
                mImageCaptureUri = data.getData();
            }

            case PICK_FROM_CAMERA:
                //이미지를 가져온 이후의 리사이즈할 이미지 크기를 결정
                //이후에 이미지 크롭 어플리케이션을 호출하게 됨
                Intent intent = new Intent("com.android.camera.action.CROP");
                intent.setDataAndType(mImageCaptureUri, "image/*");

                intent.putExtra("outputX", 250);
                intent.putExtra("outputY", 150);
                intent.putExtra("aspectX", 1);
                intent.putExtra("outputY", 1);
                intent.putExtra("scale", true);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, CROP_FROM_CAMERA);

                break;
        }
    }
    @Override
    public void onClick(View v) {
        DialogInterface.OnClickListener cameraListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakePhotoAction();
            }
        };
        DialogInterface.OnClickListener albumListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                doTakeAlbumAction();
            }
        };
        DialogInterface.OnClickListener cancelListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        };
        if(v.equals(photo_Btn)) {
            new AlertDialog.Builder(this).setTitle("업로드할 이미지 선택")
                    .setPositiveButton("사진촬영", cameraListener)
                    .setNeutralButton("앨범선택", albumListener)
                    .setNegativeButton("취소", cancelListener)
                    .show();
        } else if(v.equals(write_Txt)) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Title");
            alert.setMessage("Message");
            // Set an EditText view to get user input

            final EditText input = new EditText(this);
            alert.setView(input);
            alert.setPositiveButton("완료", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    String value = input.getText().toString();
                    value.toString();
                    write_Txt.setText(value);
                    Toast.makeText(Mypage_Write.this, "글 내용이 추가되었습니다.", Toast.LENGTH_SHORT).show();
                    // Do something with value!
                }

            });

            alert.setNegativeButton("취소", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int whichButton) {
                    // Canceled.
                    Toast.makeText(Mypage_Write.this, "글쓰기를 취소하였습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            alert.show();
        }
    }
}
