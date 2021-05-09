package com.example.studentApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class ListQuizz extends AppCompatActivity implements QuizAdapter.OnClickListenner {
    ArrayList<Quiz> listQuiz;
    private RecyclerView recyclerViewQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_quizz);

        // Lookup the recyclerview in activity layout
        recyclerViewQuiz = (RecyclerView) findViewById(R.id.recyclerQuizList);

        // Initialize contacts
        listQuiz = Quiz.createQuizList(20);
        // Create adapter passing in the sample user data
        QuizAdapter adapter = new QuizAdapter(listQuiz, this);
        // Attach the adapter to the recyclerview to populate items
        recyclerViewQuiz.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerViewQuiz.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.newQuizButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public void onQuizClick(int position){
        Toast.makeText(ListQuizz.this, "Selected :" + " " + position, Toast.LENGTH_LONG).show();
        Intent i = new Intent(ListQuizz.this, com.example.studentApp.TakeQuizz.class);
        startActivity(i);
    }

}