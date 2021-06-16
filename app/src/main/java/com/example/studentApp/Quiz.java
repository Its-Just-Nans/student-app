package com.example.studentApp;

import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Quiz {
    private String description;
    private String title;
    private String id;
    private String lesson;
    private ArrayList<Integer> reponses;
    private ArrayList<ArrayList<String>> choices;
    private ArrayList<String> questions;

    /* constructor */
    public Quiz() {
        this.title = "AAAA";
        this.description = "BBB";
        this.id = "123456";
    }

    public Quiz(String title, String desc, String id) {
        this.title = title;
        this.description = desc;
        this.id = id;
    }

    /* getter */
    public String getId(){
        return this.id;
    }

    public String getLesson(){
        return this.lesson;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getQuestions(Integer index) {
        return this.questions.get(index);
    }

    public ArrayList<String> getChoices(Integer index) {
        return this.choices.get(index);
    }

    public Integer getReponses(Integer index) {
        return this.reponses.get(index);
    }

    private static int lastContactId = 0;

    public void getQuizById(String id){
        // we get the whole quizz with the id entryPoint;
        this.choices = new ArrayList<>();
        ArrayList reponsesTemp = new ArrayList<String>();
        reponsesTemp.add("Res 1");
        reponsesTemp.add("Res 2");
        reponsesTemp.add("Res 3");
        this.choices.add(reponsesTemp);
        this.reponses = new ArrayList<Integer>();
        this.reponses.add(1);
        this.questions = new ArrayList<String>();
        this.questions.add("Questions 1");
    }

    public ArrayList<Quiz> createQuizList(int number) {
        ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
        try {
            urlRequest getQuizz = new urlRequest("action=get&obj=valeur&type=quizz");
            JSONObject values = getQuizz.executeRequestJSON();
            JSONArray arrayOfQuizz = values.getJSONArray("valeurs");
            Integer longeur = arrayOfQuizz.length();
            for (Integer i = 0; i < 40; i++) {
                    JSONObject oneValue = arrayOfQuizz.getJSONObject(i);
                    if(oneValue != null){
                    String[] title_desc = oneValue.getString("valeur").split("_");
                    String title = "Pas de titre ?!";
                    String description = "Pas de description";
                    if(title_desc[0] != null){
                        title = title_desc[0];
                    }
                    if(title_desc.length > 1 && title_desc[2] != null){
                        description = title_desc[1];
                    }
                    String id = oneValue.getString("id");
                    quizzes.add(new Quiz(title, description, id));
                }else {
                    break;
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return quizzes;
    }
}
