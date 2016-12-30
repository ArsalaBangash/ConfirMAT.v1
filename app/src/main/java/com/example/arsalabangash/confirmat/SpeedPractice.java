package com.example.arsalabangash.confirmat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Chronometer;
import android.widget.TextView;

public class SpeedPractice extends AppCompatActivity {
    TextView currentProblem, currentAnswer, questionsLeft;
    int questions, currentQuestionsAttempts = 0, currentQuestionTimeTaken = 0;
    MediaPlayer correctMP, inCorrectMP;
    Chronometer timer;
    MathModel mathModel;
    Intent endScreenIntent, startIntent, factorisationIntent, speedPracticeInit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_speed_practice);

        //Initializes TextViews, MediaPlayers, and Chronometer
        correctMP = MediaPlayer.create(this, R.raw.correct);
        inCorrectMP = MediaPlayer.create(this, R.raw.incorrect);
        currentProblem = (TextView) findViewById(R.id.currentProblem);
        currentAnswer = (TextView) findViewById(R.id.openBracket);
        questionsLeft = (TextView) findViewById(R.id.questionsLeft);
        timer = (Chronometer) findViewById(R.id.timeTaken);


        questions = 5;
        mathModel = new MathModel();
        currentProblem.setText(mathModel.newProblem());
        speedPracticeInit = getIntent();
        if (speedPracticeInit.getStringExtra(Intent.EXTRA_TEXT).equals("Initial")) {
            timer.start();
        } else {
            timer.setBase(speedPracticeInit.getLongExtra("CHRONO_TIME", 0));
        }
        timer.start();
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            public void onChronometerTick(Chronometer chronometer) {
                chronometer.refreshDrawableState();
                currentQuestionTimeTaken++;
            }
        });
        endScreenIntent = new Intent(SpeedPractice.this, EndScreen.class);
        startIntent = new Intent(SpeedPractice.this, StartScreen.class);
        factorisationIntent = new Intent(SpeedPractice.this, FactorisationBolt.class);



    }

    public String slice_end(String s, int endIndex) {
        if (endIndex < 0) endIndex = s.length() + endIndex;
        return s.substring(0, endIndex);
    }

    public void pull(View view) {
        if (currentAnswer.length() >0) {
            String newCurrentAnswer = slice_end(currentAnswer.getText().toString(), currentAnswer.getText().toString().length() - 1);
            currentAnswer.setText(newCurrentAnswer);
        }
    }


    public void check(View view) {
        currentQuestionsAttempts++;
        if (currentAnswer.getText().equals(mathModel.getAnswer())) {
            if(correctMP.isPlaying()) {
                correctMP.stop();
            }
            correctMP.start();
            questions--;
            ReportData.getReportData().inputReportData(
                    String.valueOf(currentProblem.getText()).substring(0, currentProblem.getText().length() - 1),
                    String.valueOf(currentAnswer.getText()),
                    String.valueOf(currentQuestionTimeTaken) + "s",
                    String.valueOf(currentQuestionsAttempts));
            currentQuestionsAttempts = 0;
            currentQuestionTimeTaken = 0;
            currentProblem.setText(mathModel.newProblem());

        } else {
            inCorrectMP.start();
        }

        if (questions == 0) {
            String state = speedPracticeInit.getStringExtra(Intent.EXTRA_TEXT);
            if (state.equals("Initial")) {
                factorisationIntent.putExtra("CHRONO_TIME", timer.getBase());
                startActivity(factorisationIntent);
            } else if (state.equals("Final")){
                endScreenIntent.putExtra(Intent.EXTRA_TEXT, timer.getContentDescription());
                startActivity(endScreenIntent);
            }
        }
        currentAnswer.setText("");
        questionsLeft.setText(Integer.toString(questions));

    }

    public void push(View view) {

        if (currentAnswer.getText().toString().length() < 8) {
            currentAnswer.setText(currentAnswer.getText() + view.getTag().toString());
        }
    }

    @Override
    public void onBackPressed() {
        timer.stop();
        startActivity(startIntent);

    }
}
