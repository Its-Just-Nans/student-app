package com.example.studentApp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
        Quiz temp = new Quiz();
        Log.e("", "Quiz created");
        listQuiz = temp.createQuizList(1);

        Log.e("", "Quizzes created");

        // Create adapter passing in the sample user data
        QuizAdapter adapter = new QuizAdapter(listQuiz, this);
        // Attach the adapter to the recyclerview to populate items
        recyclerViewQuiz.setAdapter(adapter);
        // Set layout manager to position the items
        recyclerViewQuiz.setLayoutManager(new LinearLayoutManager(this));
        // That's all!

    }

    @Override
    public void onQuizClick(int position){
        // Toast.makeText(ListQuizz.this, "Selected :" + " " + position, Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ListQuizz.this, com.example.studentApp.TakeQuizz.class);
        String quizzNumber = listQuiz.get(position).getId();
        String idCreator = listQuiz.get(position).getIdCreator();
        Log.e("", quizzNumber);
        intent.putExtra("id", quizzNumber);
        intent.putExtra("idU", idCreator);
        startActivity(intent);
    }

}