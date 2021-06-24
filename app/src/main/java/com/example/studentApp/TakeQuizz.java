package com.example.studentApp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.studentApp.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class TakeQuizz extends AppCompatActivity {
    public String variable = "test";
    private Quiz quizSelected;
    private Integer questionIndex;
    private TabLayout tabs;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_quizz);
        Intent intent = getIntent();
        this.questionIndex = 0;
        String id;
        String idUser;
        if(intent != null){
            id = intent.getStringExtra("id");
            idUser = intent.getStringExtra("idU");
        }else{
            id = null;
            idUser = null;
        }
        quizSelected = new Quiz();
        Log.e("",id.toString());
        if(id != null){
            quizSelected.getQuizById(id, idUser);
        }

        // set viw element
        viewPager = (ViewPager) findViewById(R.id.view_pager);
        tabs = (TabLayout) findViewById(R.id.tabs);


        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs.setupWithViewPager(viewPager);
    }

    public Quiz getQuiz(){
        return this.quizSelected;
    }

    public int getQuestionsIndex(){
        return this.questionIndex;
    }

    public void changeQuestions(){
        this.questionIndex = this.questionIndex + 1;
        String nextLesson = this.quizSelected.getLesson(this.questionIndex);
        Log.e("", "nextlesson" + nextLesson);
        if(this.questionIndex == 4 || nextLesson.equals("")){
            // end of quiz
            SharedPreferences settings = getSharedPreferences("A", 0);
            String nbQuizPlayed = settings.getString("nbQuizPlayed", "0");
            Integer nbQuizPlayedInInt = Integer.parseInt(nbQuizPlayed);
            nbQuizPlayedInInt = nbQuizPlayedInInt + 1;
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("nbQuizPlayed", String.valueOf(nbQuizPlayedInInt));
            editor.apply();
            // SAVE COUNT QUIZ
            Toast.makeText(TakeQuizz.this, "Bien joué !", Toast.LENGTH_LONG).show();
            finish();
        }else {
            SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
            viewPager.setAdapter(sectionsPagerAdapter);
            tabs.setupWithViewPager(viewPager);
        }
    }
}
