package com.example.song.newsnts;

import android.media.Image;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class Newspeed extends AppCompatActivity {

    GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newspeed);

        ImgAdapter adapter = new ImgAdapter(this);
        GridView gridView = (GridView)findViewById(R.id.gridview);
        gridView.setAdapter(adapter);
        
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MyListAct.this, "" + id, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
