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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SettingsActivity extends AppCompatActivity {
    TextView AcccountNumber;
    TextView QuizPlayNumber;
    TextView nbQuiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);

        // set elements
        AcccountNumber = (TextView) findViewById(R.id.id_user);
        QuizPlayNumber = (TextView) findViewById(R.id.quiz_play_number);
        nbQuiz = (TextView) findViewById(R.id.nb_quiz);

        // get value from sharedPreferences
        SharedPreferences settings = getSharedPreferences("A", 0);
        String id_user = settings.getString(getString(R.string.id_user), "");
        String play_number = settings.getString("nbQuizPlayed", "0");

        // we set value on screen
        AcccountNumber.setText(id_user);
        QuizPlayNumber.setText(play_number);
        try {
            JSONObject jsonRep = new urlRequest("action=get&obj=valeur&type=quiz&idU="+id_user).executeRequestJSON();
            JSONArray arrayOfQuizz = jsonRep.getJSONArray("valeurs");
            Integer longeur = arrayOfQuizz.length();
            nbQuiz.setText(String.valueOf(longeur));
        } catch (JSONException e) {
            e.printStackTrace();
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
        editor.apply();
        AcccountNumber.setText(newID);
    }
}


