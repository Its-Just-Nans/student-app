package com.example.studentApp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateQuizz extends AppCompatActivity {
    private TextView title;
    private RadioGroup radioGroup_0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_quizz);
        title = (TextView) findViewById(R.id.create_quiz_title);
        title.setText("hello");

        SharedPreferences settings = getSharedPreferences("A", 0);
        boolean silent = settings.getBoolean("silentMode", false);
        title.setText(String.valueOf(silent));

        radioGroup_0 = (RadioGroup) findViewById(R.id.radioGroup_0);
        RadioGroup radioGroup_0 = new RadioGroup(this);
        radioGroup_0.add((RadioButton) findViewById(R.id.radioButton_0_0));
        radioGroup_0.addView((RadioButton) findViewById(R.id.radioButton_0_1));
        radioGroup_0.addView((RadioButton) findViewById(R.id.radioButton_0_2));
        radioGroup_0.addView((RadioButton) findViewById(R.id.radioButton_0_3));
        // edit
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(getString(R.string.signature_title), "heelo");
        editor.apply();
        String hello = settings.getString(getString(R.string.signature_title), "");
        title.setText(hello);

    }

}