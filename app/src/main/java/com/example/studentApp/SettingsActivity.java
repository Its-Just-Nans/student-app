package com.example.studentApp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.preference.PreferenceFragmentCompat;

public class SettingsActivity extends AppCompatActivity {
    TextView AcccountNumber;
    TextView QuizPlayNumber;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        // set elements
        AcccountNumber = (TextView) findViewById(R.id.id_user);
        QuizPlayNumber = (TextView) findViewById(R.id.quiz_play_number);


        // get value from sharedPreferences
        SharedPreferences settings = getSharedPreferences("A", 0);
        String id_user = settings.getString(getString(R.string.id_user), "");
        String play_number = settings.getString("quizPlay", "");

        // we set value on screen
        AcccountNumber.setText(id_user);
        if(play_number == ""){
            QuizPlayNumber.setText("0");
        }else{
            QuizPlayNumber.setText(play_number);
        }
    }

    public void changeAccount (View v){
        String default_password = "default";
        urlRequest getNewID = new urlRequest("action=create&nom=studentAccount&prenom=account&mail=m&mdp="+default_password);
        String newID = getNewID.executeRequest();
        newID = newID.trim();
        SharedPreferences.Editor editor = getSharedPreferences("A", 0).edit();
        editor.putString(getString(R.string.id_user), newID);
        editor.putString(getString(R.string.password), default_password);
        AcccountNumber.setText(newID);
    }
}


