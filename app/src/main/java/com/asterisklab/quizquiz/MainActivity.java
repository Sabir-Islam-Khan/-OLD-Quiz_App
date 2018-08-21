package com.asterisklab.quizquiz;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Declares the member variables
    int index = 0,score = 0;
    int DisplayQuestion;
    ProgressBar progress;
    TextView question, crntScore;
    Button trueBtn, falseBtn;

    // sets the qestion and answer array to pre-created constructor in  class TrueFalse

    final TrueFalse[] questionBank = new TrueFalse[]{

            new TrueFalse(R.string.question_1, false),
            new TrueFalse(R.string.question_2,true),
            new TrueFalse(R.string.question_3,false),
            new TrueFalse(R.string.question_4,false),
            new TrueFalse(R.string.question_5,true),
            new TrueFalse(R.string.question_6,true),
            new TrueFalse(R.string.question_7,false),
            new TrueFalse(R.string.question_8,true),
            new TrueFalse(R.string.question_9,false),
            new TrueFalse(R.string.question_10,true),
            new TrueFalse(R.string.question_11,true),
            new TrueFalse(R.string.question_12,false),
            new TrueFalse(R.string.question_13,true)
    };

    final int PROGRESS_BAR_INCREMENT = (int) Math.ceil( 100.0 / questionBank.length );
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState != null){

            score = savedInstanceState.getInt("scoreKey");
            index = savedInstanceState.getInt("indexKey");


        } else {
            score = 0;
            index = 0;
        }


        // links all the textviews, buttons and progressBar to main java code

        trueBtn = findViewById(R.id.trueBtn);
        falseBtn = findViewById(R.id.falseBtn);

        question = findViewById(R.id.questions);
        crntScore = findViewById(R.id.score);

        // let's change this view here if the display changes
        crntScore.setText("Score :" + score + "/" + questionBank.length);

        progress = findViewById(R.id.progressBar);


        DisplayQuestion = questionBank[index].getQuestionID();
        // let's change this view here if the display changes


        trueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAnswer(true);
                updateQuestion();

            }
        });
        falseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkAnswer(false);
                updateQuestion();
            }
        });

    }
 // Creates a method to change the  question
    private void updateQuestion(){

        index = (index + 1) % questionBank.length;

        DisplayQuestion = questionBank[index].getQuestionID();

        question.setText(DisplayQuestion);

        progress.incrementProgressBy(PROGRESS_BAR_INCREMENT);

        crntScore.setText("Score " + score + "/" + questionBank.length);

        // Let's provide a alert dialogue !

        if(index == 0){
// In this line, if i set the context to "this" the app works.. If i use getApplicationContext(), it crashes !!
            AlertDialog.Builder myAlert = new AlertDialog.Builder(this);
            myAlert.setTitle("Finished !");
            myAlert.setCancelable(false);
            myAlert.setMessage("You Scored " + score + "/" + questionBank.length  + " Points !");
            myAlert.setPositiveButton("Close Application", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });

            myAlert.show();
        }

    }


    //Method for checking the answer

    private void checkAnswer(boolean givenAnswer){

        boolean correctAnswer = questionBank[index].getAnswer();

        if(givenAnswer == correctAnswer){
            Toast.makeText(getApplicationContext(),R.string.trueToast, Toast.LENGTH_SHORT).show();
            score = score + 1;
            // Let's add sound effect !!
            MediaPlayer rightSound = MediaPlayer.create(getApplicationContext(), R.raw.correct_answer);
            rightSound.start();
        } else {
            Toast.makeText(getApplicationContext(), R.string.falseToast, Toast.LENGTH_SHORT).show();
            // Same thing here
            MediaPlayer wrongSound = MediaPlayer.create(getApplicationContext(), R.raw.false_answer);
            wrongSound.start();
        }
    }

    // Let's solve the orientation problem
    @Override

    public  void onSaveInstanceState(Bundle outState){
        super.onSaveInstanceState(outState);

        outState.putInt("scoreKey", score);
        outState.putInt("indexKey",index);
    }
}

