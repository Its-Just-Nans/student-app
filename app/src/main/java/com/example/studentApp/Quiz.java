package com.example.studentApp;

import java.util.ArrayList;
import java.util.HashMap;

public class Quiz {
    private String description;
    private String title;

    public Quiz() {
        title = "AAAA";
        description = "BBB";
    }

    public Quiz(HashMap<String, String> quiz) {
        title = quiz.get("title");
        description = quiz.get("description");
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    private static int lastContactId = 0;

    public static ArrayList<Quiz> createQuizList(int number) {
        ArrayList<Quiz> quizzes = new ArrayList<Quiz>();

        for (int i = 1; i <= number; i++) {
            quizzes.add(new Quiz());
        }

        return quizzes;
    }
}
