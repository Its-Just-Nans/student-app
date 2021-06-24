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
    private String creatorId;
    private ArrayList<String> lessons;
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

    public Quiz(String title, String desc, String id, String idCreator) {
        this.title = title;
        this.description = desc;
        this.id = id;
        this.creatorId = idCreator;
    }

    /* getter */

    public String getId(){
        return this.id;
    }

    public String getIdCreator(){
        return this.creatorId;
    }

    public String getLesson(Integer index){
        String lesson = "";
        try{
            lesson = this.lessons.get(index);
        }catch (IndexOutOfBoundsException e){
            // e.printStackTrace();
            Log.e("", "Error index for lesson " + this.lessons.toString());
        }
        return lesson;
    }

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }

    public String getQuestions(Integer index) {
        String question = "";
        try{
            question = this.questions.get(index);
        }catch (IndexOutOfBoundsException e){
            // e.printStackTrace();
            Log.e("", "Error index");
        }
        return question;
    }

    public ArrayList<String> getChoices(Integer index) {
        return this.choices.get(index);
    }

    public Integer getReponses(Integer index) {
        Integer res = 1;
        try{
            res = this.reponses.get(index);
        }catch (IndexOutOfBoundsException e){
            Log.e("", this.reponses.toString());
        }
        return res;
    }

    private static int lastContactId = 0;

    public void getQuizById(String idOfQuiz, String idUser){
        try {
            this.id = idOfQuiz;
            this.creatorId = idUser;
            urlRequest getQuizz = new urlRequest("action=get&obj=valeur&idU="+this.creatorId);
            JSONObject values = getQuizz.executeRequestJSON();
            JSONArray arrayOfValues = values.getJSONArray("valeurs");
            this.choices = new ArrayList<>();
            ArrayList reponses0_TEMP = new ArrayList<String>();
            ArrayList reponses1_TEMP = new ArrayList<String>();
            ArrayList reponses2_TEMP = new ArrayList<String>();
            ArrayList reponses3_TEMP = new ArrayList<String>();
            this.lessons = new ArrayList<String>();
            this.questions = new ArrayList<String>();
            this.reponses = new ArrayList<Integer>();
            int longueur = arrayOfValues.length();
            Log.e("", "Longeur de la chaine " + String.valueOf(longueur));
            for (Integer i = 0; i < longueur; i++) {
                JSONObject oneValue = arrayOfValues.getJSONObject(i);
                String typeOfValue = oneValue.getString("type");
                String valueOfValue = oneValue.getString("valeur");
                if(valueOfValue == null){
                    valueOfValue = " ";
                }
                Log.e("", typeOfValue +" -> " + valueOfValue);
                if(typeOfValue.startsWith(this.id)){
                    if(typeOfValue.endsWith("4")){
                        // the reponses_x_4 is the index answers !!
                        // we add the answers
                        this.reponses.add(Integer.parseInt(valueOfValue));
                    }else {
                        //Log.e("", typeOfValue +" -> " + valueOfValue);
                        if(typeOfValue.startsWith(this.id+"_lesson_")) {
                            this.lessons.add(valueOfValue);
                        }else if(typeOfValue.startsWith(this.id+"_responses_0")) {
                            reponses0_TEMP.add(valueOfValue);
                        }else if(typeOfValue.startsWith(this.id+"_responses_1")){
                            reponses1_TEMP.add(valueOfValue);
                        }else if(typeOfValue.startsWith(this.id+"_responses_2")){
                            reponses2_TEMP.add(valueOfValue);
                        }else if(typeOfValue.startsWith(this.id+"_responses_3")){
                            reponses3_TEMP.add(valueOfValue);
                        }else if(typeOfValue.startsWith(this.id+"_questions_")){
                            this.questions.add(valueOfValue);
                        }
                    }
                }

                // we get the whole quizz with the id entryPoint;
            }
            this.choices = new ArrayList<>();
            this.choices.add(reponses0_TEMP);
            this.choices.add(reponses1_TEMP);
            this.choices.add(reponses2_TEMP);
            this.choices.add(reponses3_TEMP);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Quiz> createQuizList(int number) {
        ArrayList<Quiz> quizzes = new ArrayList<Quiz>();
        try {
            Log.e("", "Start Function");
            urlRequest getQuizz = new urlRequest("action=get&obj=valeur&type=quiz");
            Log.e("", "Request start");
            JSONObject values = getQuizz.executeRequestJSON();
            Log.e("", "Request done");
            JSONArray arrayOfQuizz = values.getJSONArray("valeurs");
            Integer longeur = arrayOfQuizz.length();
            Log.e("", String.valueOf(longeur));
            for (Integer i = 0; i < 40; i++) {
                JSONObject oneValue = arrayOfQuizz.getJSONObject(i);
                if(oneValue != null){
                    String titleAndDesc = oneValue.getString("valeur");
                    String[] title_desc = titleAndDesc.split("_");
                    String title = "Pas de titre ?!";
                    String description = "Pas de description";
                    try{
                        if(title_desc[0] != null){
                            title = title_desc[0];
                        }
                        if(title_desc.length > 1 && title_desc[1] != null){
                            description = title_desc[1];
                        }
                    }catch (ArrayIndexOutOfBoundsException e){
                        Log.e("", "Error desc : "+titleAndDesc);
                    }
                    String id = oneValue.getString("id");
                    String idCreator = oneValue.getString("idU");
                    quizzes.add(new Quiz(title, description, id, idCreator));
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
