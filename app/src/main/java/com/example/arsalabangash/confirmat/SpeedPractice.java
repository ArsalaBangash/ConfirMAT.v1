package com.example.arsalabangash.confirmat;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SpeedPractice extends AppCompatActivity {
    TextView currentProblem, currentAnswer, questionsLeft;
    int questions;
    MediaPlayer correctMP, inCorrectMP;
    Chronometer timer;
    MathModel mathModel;
    Intent endScreenIntent, startIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_speed_practice);
        Typeface robotoFont = Typeface.createFromAsset(getAssets(), "fonts/roboto.ttf");
        correctMP = MediaPlayer.create(this, R.raw.correct);
        inCorrectMP = MediaPlayer.create(this, R.raw.incorrect);
        currentProblem = (TextView) findViewById(R.id.currentProblem);
        currentProblem.setTypeface(robotoFont);
        currentAnswer = (TextView) findViewById(R.id.currentAnswer);
        currentAnswer.setTypeface(robotoFont);
        questionsLeft = (TextView) findViewById(R.id.questionsLeft);
        questionsLeft.setTypeface(robotoFont);
        questions = 20;
        mathModel = new MathModel();
        currentProblem.setText(mathModel.newProblem());
        timer = (Chronometer) findViewById(R.id.timeTaken);
        timer.start();
        endScreenIntent = new Intent(SpeedPractice.this, EndScreen.class);
        startIntent = new Intent(SpeedPractice.this, StartScreen.class);


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
        Log.d("MYAPP", String.valueOf(currentAnswer.getText()));
        if (currentAnswer.getText().equals(mathModel.getAnswer())) {
            if(correctMP.isPlaying()) {
                correctMP.stop();
            }
            correctMP.start();
            questions--;
            currentProblem.setText(mathModel.newProblem());

        } else {
            inCorrectMP.start();
        }

        if (questions == 0) {
            endScreenIntent.putExtra(Intent.EXTRA_TEXT, timer.getContentDescription());
            startActivity(endScreenIntent);
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
