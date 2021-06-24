package com.example.studentApp.main;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.studentApp.MainActivity;
import com.example.studentApp.Quiz;
import com.example.studentApp.R;
import com.example.studentApp.TakeQuizz;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class quizz_part2 extends Fragment {
    private TextView question;
    private Integer responsesIndex;
    private Quiz actualQuiz;
    private Button validateButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.quizz_part2, container, false);
        actualQuiz = ((TakeQuizz) getActivity()).getQuiz();
        int indexQuestion = ((TakeQuizz) getActivity()).getQuestionsIndex();
        // set value
        question = (TextView) view.findViewById(R.id.take_quiz_question);
        question.setText(actualQuiz.getQuestions(indexQuestion));



        // get response
        ArrayList<String> all_rep = actualQuiz.getChoices(indexQuestion);
        responsesIndex = actualQuiz.getReponses(indexQuestion);

        // get and set choices
        for(int i =0; i < 4; i++){
            String correctString = "take_quiz_responses_"+String.valueOf(i);
            int idElement = getResources().getIdentifier(correctString, "id", view.getContext().getPackageName());
            TextView responsesTemp = (TextView) view.findViewById(idElement);
            String reponse = all_rep.get(i);
            if(reponse == "" || reponse == " "){
                responsesTemp.setVisibility(View.GONE);
            }else {
                responsesTemp.setText(reponse);
            }
        }

        // set callBack onCLick for validate
        validateButton = (Button) view.findViewById(R.id.take_quiz_validate);
        validateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // check answer

                // HERE WE USE view and not v !!! (bcause you can't use findViewById with v
                Integer indexFromUser = 0;
                for(int i =0; i < 4; i++){
                    String idInString = "take_quiz_radioButton_"+String.valueOf(i);
                    int idOfElement = getResources().getIdentifier(idInString, "id", view.getContext().getPackageName());
                    Log.e("", idInString);
                    RadioButton element = (RadioButton) view.findViewById(idOfElement);
                    if(element.isChecked()){
                        indexFromUser = i;
                    }
                }
                if(responsesIndex == indexFromUser){
                    Log.e("", "ANSWERED");
                    ((TakeQuizz) getActivity()).changeQuestions();
                }else {
                    Toast.makeText(getContext(), "Incorrect ! :(", Toast.LENGTH_LONG).show();
                    // if incorrect, on affiche la popup
                }
            }
        });

        return view;
    }

}
