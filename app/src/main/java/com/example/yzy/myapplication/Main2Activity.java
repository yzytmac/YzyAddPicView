package com.example.yzy.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.addpicview.AddPicView;


public class Main2Activity extends AppCompatActivity {

    private AddPicView mAddPicView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        mAddPicView = (AddPicView) findViewById(R.id.add_pic_view);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x01 && resultCode == Activity.RESULT_OK) {
            Uri vData = data.getData();
            Log.e("yzy", "onActivityResult: " + vData);
            mAddPicView.addPicture(vData);
        }
    }
}
