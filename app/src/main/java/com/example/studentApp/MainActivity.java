package com.example.studentApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText tt;
    private ArrayList<String> listeActions;
    private ArrayAdapter<String> myAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listeActions = new ArrayList<>();
        myAdapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, listeActions);
        ListView listMenu = (ListView) findViewById(R.id.listeActionss);
        listMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = listMenu.getItemAtPosition(position);
                if(position == 0){
                    Intent i = new Intent(MainActivity.this, com.example.studentApp.ListQuizz.class);
                    startActivity(i);
                }else if (position == 1){
                    Intent i = new Intent(MainActivity.this, com.example.studentApp.CreateQuizz.class);
                    startActivity(i);
                }else {
                    Toast.makeText(MainActivity.this, "Selected :" + " " + o.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });


        listMenu.setAdapter(myAdapter);
        listeActions.add("Faire un quiz");
        listeActions.add("Créer un quiz");
        listeActions.add("Voir les news");


        SharedPreferences settings = getSharedPreferences("A", 0);
        String id_user = settings.getString(getString(R.string.id_user), "");
        String password = settings.getString(getString(R.string.password), "");
        if(id_user == null || id_user == "" || password == null || password == "") {
            Toast.makeText(MainActivity.this, "user="+id_user.toString(), Toast.LENGTH_LONG).show();
            // we need to get and id_user
            SharedPreferences.Editor editor = settings.edit();
            String default_password = "test";
            urlRequest getNewID = new urlRequest("action=create&nom=studentAccount&prenom=account&mail=m&mdp="+default_password);
            String newID = getNewID.executeRequest();
            newID = newID.trim();
            Toast.makeText(MainActivity.this, "newID_GENERATED="+newID, Toast.LENGTH_LONG).show();
            editor.putString(getString(R.string.id_user), newID);
            editor.putString(getString(R.string.password), default_password);
            editor.apply();
        }
        id_user = settings.getString(getString(R.string.id_user), "");
        password = settings.getString(getString(R.string.password), "");
        Toast.makeText(MainActivity.this, "Connected with "+id_user.trim(), Toast.LENGTH_LONG).show();

    }

    public void goToSettingsMenu (View view){
        Intent i = new Intent(MainActivity.this, com.example.studentApp.SettingsActivity.class);
        startActivity(i);
    }
}

