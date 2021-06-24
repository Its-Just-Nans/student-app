package com.example.studentApp.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.studentApp.Quiz;
import com.example.studentApp.R;
import com.example.studentApp.TakeQuizz;

public class quizz_part1 extends Fragment {
    private Quiz actualQuiz;
    private TextView takeQuiz_title;
    private TextView takeQuiz_lesson;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.quizz_part1, container, false);
        takeQuiz_lesson = (TextView) view.findViewById(R.id.takeQuiz_lesson);
        takeQuiz_title = (TextView) view.findViewById(R.id.takeQuiz_title);
        actualQuiz = ((TakeQuizz) getActivity()).getQuiz();

        Integer index = ((TakeQuizz) getActivity()).getQuestionsIndex();

        String lesson = actualQuiz.getLesson(index);
        // show lesson
        takeQuiz_title.setText(lesson);
        return view;
    }
}
