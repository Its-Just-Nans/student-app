package com.example.studentApp;

import android.content.Intent;
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
                if(o.toString() == "Quizz"){
                    Intent i = new Intent(MainActivity.this, com.example.studentApp.ListQuizz.class);
                    startActivity(i);
                }else{
                    Toast.makeText(MainActivity.this, "Selected :" + " " + o.toString(), Toast.LENGTH_LONG).show();
                }
            }
        });
        listMenu.setAdapter(myAdapter);
        String quizzButton = "Quizz";
        String newsButton = "News";
        listeActions.add(quizzButton);
        listeActions.add(newsButton);
    }

    public void goToSettingsMenu (View view){
        Intent i = new Intent(MainActivity.this, com.example.studentApp.SettingsActivity.class);
        startActivity(i);
    }
}

