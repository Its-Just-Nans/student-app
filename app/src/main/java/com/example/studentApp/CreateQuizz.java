package com.example.studentApp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class CreateQuizz extends AppCompatActivity {
    private TextView title;
    private RadioGroup radioGroup_0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_quizz);
        title = (TextView) findViewById(R.id.create_quiz_title);

        SharedPreferences settings = getSharedPreferences("A", 0);
        boolean silent = settings.getBoolean("silentMode", false);
        title.setText(String.valueOf(silent));

        // edit
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(getString(R.string.signature_title), "heelo");
        editor.apply();
        String hello = settings.getString(getString(R.string.signature_title), "");
        title.setText(hello);

    }

    public void onClickRadioButton(View view){
        String ResourceIdAsString = this.getResources().getResourceName(view.getId());
        ResourceIdAsString = ResourceIdAsString.split("/")[1];
        String[] parts = ResourceIdAsString.split("_");
        for(int i=0;i<4;i++){
            String idInString = "radioButton_"+parts[1]+"_"+String.valueOf(i);
            int id = getResources().getIdentifier(idInString, "id", view.getContext().getPackageName());
            RadioButton radioButton = (RadioButton) findViewById(id);
            Log.e("", ResourceIdAsString+"<->"+idInString);
            if(ResourceIdAsString.trim().equals(idInString.trim())) {
                Log.e("", ResourceIdAsString+" now checked");
                radioButton.setChecked(true);
            }else {
                radioButton.setChecked(false);
            }
        }
    }

    private String getCorrectReponseIndex(View view, String indexOfQuestion){
        String answer = "1";
        for(int i=0;i<4;i++){
            String idInString = "radioButton_"+indexOfQuestion+"_"+String.valueOf(i);
            int id = getResources().getIdentifier(idInString, "id", view.getContext().getPackageName());
            RadioButton radioButton = (RadioButton) findViewById(id);
            if(radioButton.isChecked()) {
                answer = String.valueOf(i);
                break;
            }
        }
        return  answer;
    }

    public void saveQuiz(View view) {
        // create the quiz Entry Point and get ID
        Log.e("","Saving the quiz");
        SharedPreferences settings = getSharedPreferences("A", 0);
        String ID_USER = settings.getString(getString(R.string.id_user), "");
        String PASSWORD_USER = settings.getString(getString(R.string.password), "");
        String strTitle = this.getStringTextByID(view, "create_quiz_title");
        String res;
        String strDescription = this.getStringTextByID(view, "create_quiz_description");
        // very important : we add the type "quiz"
        res = new urlRequest("action=add&valeur="+strTitle+"_"+strDescription+"&idUser="+ID_USER+"&mdp="+PASSWORD_USER+"&type="+"quiz").executeRequest();
        Log.e("",res);
        String idQuiz = res;
        String typeOfValue;

        // we save quiz
        for(int repNumber = 0; repNumber<4; repNumber++){
            Log.e("", "Saving answers");
            String strRepNumber = String.valueOf(repNumber);
            // get lesson
            String strLesson = this.getStringTextByID(view, "create_quiz_lesson_"+strRepNumber);
            typeOfValue = idQuiz + "_lesson_" + strRepNumber;
            res = new urlRequest("action=add&valeur="+strLesson+"&idUser="+ID_USER+"&mdp="+PASSWORD_USER+"&type="+typeOfValue).executeRequest();
            String strQuestion = this.getStringTextByID(view, "create_quiz_questions_"+strRepNumber);
            typeOfValue = idQuiz + "_questions_" + strRepNumber;
            res = new urlRequest("action=add&valeur="+strQuestion+"&idUser="+ID_USER+"&mdp="+PASSWORD_USER+"&type="+typeOfValue).executeRequest();
            for(int i=0;i<4;i++){
                String iInString = String.valueOf(i);
                String strReponse = this.getStringTextByID(view, "create_quiz_responses_"+strRepNumber+"_"+iInString);
                typeOfValue = idQuiz + "_responses_" + strRepNumber + "_" + iInString;
                res = new urlRequest("action=add&valeur="+strReponse+"&idUser="+ID_USER+"&mdp="+PASSWORD_USER+"&type="+typeOfValue).executeRequest();
                Log.e("", "Answers "+repNumber+"_"+idQuiz+" saved !");
                // we need to save the correct answers !!

            }
            String strCorrectReponse = this.getCorrectReponseIndex(view, strRepNumber);
            typeOfValue = idQuiz + "_responses_" + strRepNumber + "_4"; // answer is in number 4
            res = new urlRequest("action=add&valeur="+strCorrectReponse+"&idUser="+ID_USER+"&mdp="+PASSWORD_USER+"&type="+typeOfValue).executeRequest();
        }
        // we save responses

        finish();
    }

    private String getStringTextByID(View view, String idInString){
        String toReturn = "";
        int idOfElement = getResources().getIdentifier(idInString, "id", view.getContext().getPackageName());
        TextView element = (TextView) findViewById(idOfElement);
        toReturn = element.getText().toString();
        return toReturn;
    }

}