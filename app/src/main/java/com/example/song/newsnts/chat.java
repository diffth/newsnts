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
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;

public class chat extends AppCompatActivity {

    Button set;
    Button out;
    Button voice;
    Button camera;
    Button sendbt;
    Button prebt;
    Button menubt;
    TextView chatTextview;
    EditText editText;

    Button image;
    private Context mContext;
    private ImageView mImgPhoto = null;

    boolean isPageOpen = false;
    Animation translateLeftAnim;
    Animation translateRightAnim;
    LinearLayout slidingPage01;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        image = (Button) findViewById(R.id.image);
        sendbt = (Button) findViewById(R.id.sendbt);
        prebt = (Button) findViewById(R.id.prebt);
        menubt = (Button) findViewById(R.id.menubt);
        editText = (EditText) findViewById(R.id.editText);
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
        sendbt.setOnClickListener(new View.OnClickListener() { //전송버튼
            public void onClick(View v) {
                String mes = editText.getText().toString();
                String name = "지순님의 말 : ";
                String chat = name + mes;
                chatTextview.setText(chat);

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

    }
    public void onButton1Clicked(View v) { //메뉴버튼 누르기

        if (isPageOpen) {
            slidingPage01.startAnimation(translateRightAnim);
        } else {
            slidingPage01.setVisibility(View.VISIBLE);
            slidingPage01.startAnimation(translateLeftAnim);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1001:
                if (resultCode == RESULT_CANCELED)
                    return;
                if (data != null) {
                    try {
                        Uri imgUri = data.getData();

                        String imagePath = ImageUtil.getImageRealPathFromURI(mContext.getContentResolver(), imgUri); // path 경로
                        Bitmap bmpImage = BitmapFactory.decodeFile(imagePath);

                        // 이미지를 상황에 맞게 회전시킨다
                        ExifInterface exif = new ExifInterface(imagePath);
                        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,	ExifInterface.ORIENTATION_NORMAL);
                        int exifDegree = ImageUtil.exifOrientationToDegrees(exifOrientation);
                        bmpImage = ImageUtil.rotate(bmpImage, exifDegree);
                        // 이미지 축소
                        //bmpImage = ImageUtil.getResizedResourceImage(bmpImage, bmpImage.getWidth()/2, bmpImage.getHeight()/2);
                        bmpImage = Bitmap.createScaledBitmap(bmpImage,(int)(bmpImage.getWidth()*0.3), (int)(bmpImage.getHeight()*0.3), true); // scale 유지됨
                        mImgPhoto.setImageBitmap(bmpImage);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
                break;
            case 1002:

                if (resultCode == RESULT_CANCELED)
                    return;

                if (data != null) {
                    try {
                        Uri imgUri = data.getData();

                        // 비트맵 이미지로 가져온다
                        String imagePath = ImageUtil.getImageRealPathFromURI(mContext.getContentResolver(), imgUri); // path 경로
                        Bitmap bmpImage = BitmapFactory.decodeFile(imagePath);

                        // 이미지를 상황에 맞게 회전시킨다
                        ExifInterface exif = new ExifInterface(imagePath);
                        int exifOrientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,	ExifInterface.ORIENTATION_NORMAL);
                        int exifDegree = ImageUtil.exifOrientationToDegrees(exifOrientation);
                        bmpImage = ImageUtil.rotate(bmpImage, exifDegree);
                        // 이미지 축소
                        //bmpImage = ImageUtil.getResizedResourceImage(bmpImage, bmpImage.getWidth()/2, bmpImage.getHeight()/2);
                        bmpImage = Bitmap.createScaledBitmap(bmpImage,(int)(bmpImage.getWidth()*0.3), (int)(bmpImage.getHeight()*0.3), true); // scale 유지됨
                        mImgPhoto.setImageBitmap(bmpImage);

                    } catch (Exception e) {
                        // TODO: handle exception
                        e.printStackTrace();
                    }

                }

                break;


        }


    }
    public static class ImageUtil {

        public  Bitmap getResizedResourceImage(Bitmap originBitmap, int newWidth, int newHeight) {
            int width = originBitmap.getWidth();
            int height = originBitmap.getHeight();

            if(width == newWidth && height == newHeight) return originBitmap;

            final float widthScale = (float) newWidth / (float) width;
            final float heightScale = (float) newHeight / (float) height;

            final int scaledWidth = (int) (width * widthScale);
            final int scaledHeight = (int) (height * heightScale);

            Bitmap resizedBitmap = Bitmap.createScaledBitmap(originBitmap, scaledWidth, scaledHeight, true);

            return resizedBitmap;
        }

        public  Bitmap getDisplayBitmap(View v, int width, int height) {
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_4444);
            Canvas canvas = new Canvas(bmp);
            Drawable d = v.getBackground();
            if (BitmapDrawable.class.isInstance(d)) {
                BitmapDrawable bd = (BitmapDrawable)d;
                Bitmap bgbmp = bd.getBitmap();
                if(bgbmp != null)	canvas.drawBitmap(bgbmp, 0, 0, new Paint());
            }
            v.draw(canvas);
            return bmp;
        }

        public  Bitmap toBitmap(View v, int left, int top, int width, int height) {
            v.measure(width, height);
            v.layout(left, top, width, height);

            boolean isDrawingCacheEnabled = v.isDrawingCacheEnabled();
            v.setDrawingCacheEnabled(true);

            Bitmap rbmp = null;
            Bitmap bmp = v.getDrawingCache();
            if(bmp != null) rbmp = bmp.copy(Bitmap.Config.ARGB_8888, true);

            v.setDrawingCacheEnabled(isDrawingCacheEnabled);

            return rbmp;
        }

        public  Bitmap toBitmap(View v) {
            Rect r = new Rect();
            v.getDrawingRect(r);
            //Logger.i("ghi", "   rect " + r.flattenToString());
            return toBitmap(v, r.left, r.top, r.width(), r.height());
        }

        public static void recycle(Bitmap bmp) {
            if(bmp != null && !bmp.isRecycled()) {
                bmp.recycle();
            }
        }

        public  void recycle(ImageView image) {
            if(image == null) return;

            Drawable d = image.getDrawable();
            if(d != null && BitmapDrawable.class.isInstance(d))
            {
                BitmapDrawable bd = (BitmapDrawable)d;
                Bitmap bm = bd.getBitmap();
                if(bm != null) bm.recycle();
            }
        }

        public Bitmap resourceToBitmap(Context ctx, int resid) {
            return BitmapFactory.decodeResource(ctx.getResources(), resid);
        }

        public Bitmap drawableToBitmap(BitmapDrawable d) {
            return d.getBitmap();
        }

        public Drawable bitmapToDrawable(Bitmap b) {
            return new BitmapDrawable(b);
        }

        protected  Canvas getCanvas(Bitmap b) {
            if(b == null) {
                return new Canvas();
            }
            else if(!b.isMutable()) {
                Bitmap mutable = b.copy(Bitmap.Config.ARGB_8888, true);
                return new Canvas(mutable);
            } else {
                return new Canvas(b);
            }
        }

        public  Bitmap toGrayscale(Bitmap bmpOriginal) {
            int width, height;
            height = bmpOriginal.getHeight();
            width = bmpOriginal.getWidth();

            Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);
            Canvas c = new Canvas(bmpGrayscale);
            Paint paint = new Paint();
            ColorMatrix cm = new ColorMatrix();
            cm.setSaturation(0);
            ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
            paint.setColorFilter(f);
            c.drawBitmap(bmpOriginal, 0, 0, paint);
            return bmpGrayscale;
        }


        public  Bitmap getRoundedCornerBitmap(Bitmap bitmap, int width, int height, int round) {
            return getRoundedCornerBitmap(bitmap, width, height, round, false, 0.0f, 0.0f, 0.0f, 0);
        }

        public  Bitmap getRoundedCornerBitmap(Bitmap bitmap, int width, int height, int round
                , boolean shadow, float shadowRound, float shadowXOffset, float shadowYOffset, int shadowColor) {

            Bitmap output = Bitmap.createBitmap(width + (int)shadowXOffset, height + (int)shadowXOffset, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(output);

            final int color = 0xff424242;
            final Paint paint = new Paint();
            final Rect rect = new Rect(0, 0, width, height);
            final RectF rectF = new RectF(rect);
            float roundPx = round;

            final Rect srect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());

            if (shadow) paint.setShadowLayer(shadowRound, shadowXOffset, shadowYOffset, shadowColor);
            paint.setAntiAlias(true);
            canvas.drawARGB(0, 0, 0, 0);
            paint.setColor(color);

            canvas.drawRoundRect(rectF, roundPx, roundPx, paint);

            paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));

            canvas.drawBitmap(bitmap, srect, rect, paint);

            return output;
        }


        public Bitmap getBitmapByExifRotation(String imagepath, BitmapFactory.Options opt) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagepath, opt);
            try {
                ExifInterface exif = new ExifInterface(imagepath);
                int orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
                int rotate = 0;
                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        rotate -= 90;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        rotate -= 90;
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        rotate -= 90;
                }
                if (rotate != 0) {
                    Bitmap rbitmap = ImageUtil.rotateBitmap(bitmap, (-1) * rotate);
                    ImageUtil.recycle(bitmap);
                    return rbitmap;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        public static Bitmap rotateBitmap(Bitmap bmp, int rotation) {
            int w = bmp.getWidth();
            int h = bmp.getHeight();
            // Setting post rotate to 90
            Matrix mtx = new Matrix();
            mtx.postRotate(rotation);
            // Rotating Bitmap
            Bitmap rotatedBMP = Bitmap.createBitmap(bmp, 0, 0, w, h, mtx, true);
            return rotatedBMP;
        }

        public  Bitmap getBitmapByExifRotation(String imagepath) {
            return getBitmapByExifRotation(imagepath, null);
        }






        public  Bitmap getLoading(Context ctx) {
            Bitmap bm = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.trans);
            Bitmap newbm = Bitmap.createBitmap(bm.getWidth(), bm.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(newbm);
            canvas.drawBitmap(bm, 0, 0, null);
            return newbm;
        }

        public  Bitmap scaleImage(Context context, File imageFile, int maxWidth, int maxHeight) throws IOException {

            String pathName = imageFile.getAbsolutePath();

            BitmapFactory.Options dbo = new BitmapFactory.Options();
            dbo.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(pathName, dbo);

            int rotatedWidth, rotatedHeight;
            int orientation = getOrientation(context, pathName);

            if (orientation == 90 || orientation == 270) {
                rotatedWidth = dbo.outHeight;
                rotatedHeight = dbo.outWidth;
            } else {
                rotatedWidth = dbo.outWidth;
                rotatedHeight = dbo.outHeight;
            }

            Bitmap srcBitmap;
            if (rotatedWidth > maxWidth || rotatedHeight > maxHeight) {
                float widthRatio = ((float) rotatedWidth) / ((float) maxWidth);
                float heightRatio = ((float) rotatedHeight) / ((float) maxHeight);
                float maxRatio = Math.max(widthRatio, heightRatio);

                // Create the bitmap from file
                BitmapFactory.Options opts = new BitmapFactory.Options();
                opts.inSampleSize = (int) maxRatio;
                srcBitmap = BitmapFactory.decodeFile(pathName, opts);
            } else {
                srcBitmap = BitmapFactory.decodeFile(pathName);
            }


            // if the orientation is not 0 (or -1, which means we don't know), we have to do a rotation.

            if (orientation > 0) {
                Matrix matrix = new Matrix();
                matrix.postRotate(orientation);

                srcBitmap = Bitmap.createBitmap(srcBitmap, 0, 0, srcBitmap.getWidth(),
                        srcBitmap.getHeight(), matrix, true);
            }

            return srcBitmap;
        }

        public  int getOrientation(Context context, String imagepath) throws IOException {
            ExifInterface exif = new ExifInterface(imagepath);
            return exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
        }

        /**
         * EXIF정보를 회전각도로 변환하는 메서드
         * @param exifOrientation EXIF 회전각
         * @return 실제 각도
         */
        public static int exifOrientationToDegrees(int exifOrientation) {
            if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_90) {
                return 90;
            } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_180) {
                return 180;
            } else if (exifOrientation == ExifInterface.ORIENTATION_ROTATE_270) {
                return 270;
            }
            return 0;
        }

        /**
         * 이미지를 회전시킵니다.
         *
         * @param bitmap 비트맵 이미지
         * @param degrees
         *            회전 각도
         * @return 회전된 이미지
         */
        public static Bitmap rotate(Bitmap bitmap, int degrees) {
            if (degrees != 0 && bitmap != null) {
                Matrix m = new Matrix();
                m.setRotate(degrees, (float) bitmap.getWidth() / 2,
                        (float) bitmap.getHeight() / 2);

                try {
                    Bitmap converted = Bitmap.createBitmap(bitmap, 0, 0,
                            bitmap.getWidth(), bitmap.getHeight(), m, true);
                    if (bitmap != converted) {
                        bitmap.recycle();
                        bitmap = converted;
                    }
                } catch (OutOfMemoryError ex) {
                    // 메모리가 부족하여 회전을 시키지 못할 경우 그냥 원본을 반환합니다.
                }
            }
            return bitmap;
        }

        public static String getImageRealPathFromURI(ContentResolver cr,
                                                     Uri contentUri) {
            // can post image
            String[] proj = { MediaStore.Images.Media.DATA };

            Cursor cursor = cr.query(contentUri, proj, // Which columns to return
                    null, // WHERE clause; which rows to return (all rows)
                    null, // WHERE clause selection arguments (none)
                    null); // Order-by clause (ascending by name)

            if (cursor == null) {
                return contentUri.getPath();
            } else {
                int path = cursor
                        .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();

                String tmp = cursor.getString(path);
                cursor.close();
                return tmp;
            }
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


}
