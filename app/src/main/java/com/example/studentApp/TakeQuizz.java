package com.example.studentApp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.studentApp.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class TakeQuizz extends AppCompatActivity {
    public String variable = "test";
    private Quiz quizSelected;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.take_quizz);
        Intent intent = getIntent();
        String id;
        if(intent != null){
            id = intent.getStringExtra("id");
        }else{
            id = null;
        }
        quizSelected = new Quiz();
        Log.e("",id.toString());
        if(id != null){
            quizSelected.getQuizById(id);
        }
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = (ViewPager) findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
    }

    public Quiz getQuiz(){
        return this.quizSelected;
    }
}
