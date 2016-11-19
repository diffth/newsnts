package com.example.song.newsnts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AnotherActivity extends Activity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another);

        Button okButton = (Button) findViewById(R.id.button);


        /*String textDay = editDay.getText().toString();*/

        /*Toast toast = Toast.makeText(getApplicationContext(), + textYear + "년" + textMonth + "월" + textDay + "일" , Toast.LENGTH_LONG);
        toast.show();*/


    }

    public void onMenu1Click(View v){

        Intent intent = new Intent(getApplicationContext(),AnotherActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ANOTHER);
    }

}
