package com.rashmi.android.umkc.edu.helloworldapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Second_activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_activity);
    }
    public void goback(View v){
    Intent intent = new Intent(Second_activity.this, MainActivity.class);
    startActivity(intent);
}

}
