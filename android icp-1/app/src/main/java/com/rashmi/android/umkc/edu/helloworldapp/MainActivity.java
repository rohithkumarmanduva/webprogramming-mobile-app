package com.rashmi.android.umkc.edu.helloworldapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    private EditText userid;
    private EditText Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void basicactivity(View v) {
        userid = (EditText) findViewById(R.id.editText);
        Password = (EditText) findViewById(R.id.editText2);


        String a1 = userid.getText().toString();
        String a2 = Password.getText().toString();

        if ((a1.equals("rohith")) && (a2.equals("kumar"))) {
            Intent intent = new Intent(MainActivity.this, Second_activity.class);
            startActivity(intent);

        } else {
            Toast.makeText(getApplicationContext(), "Your toast message",
                    Toast.LENGTH_LONG).show();

        }
    }
}
