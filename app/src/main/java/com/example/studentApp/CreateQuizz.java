package com.example.studentApp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateQuizz extends AppCompatActivity {
    private TextView title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_quizz);
        title = (TextView) findViewById(R.id.titleQuizz);
        title.setText("hello");

        SharedPreferences settings = getSharedPreferences("A", 0);
        boolean silent = settings.getBoolean("silentMode", false);
        title.setText(String.valueOf(silent));

        // edit
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(getString(R.string.signature_title), "heelo");
        editor.apply();
        String hello = settings.getString(getString(R.string.signature_title), "");
        title.setText(hello);

    }

}