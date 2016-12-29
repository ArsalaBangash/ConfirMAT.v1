package com.example.arsalabangash.confirmat;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class FactorisationBolt extends AppCompatActivity {

    TextView currentProblemText, firstFactorText, questionsLeft, secondFactorText;
    Chronometer timer;
    int charactersEntered = 0, questions, currentQuestionsAttempts, currentQuestionTimeTaken;
    private Random rand;
    MediaPlayer correctMP, inCorrectMP;
    Intent speedPracticeIntent, factorisationInit;
    String answer1, answer2, parsedAnswer;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factorisation_bolt);

        //Initializes TextViews, MediaPlayers, and Chronometer
        correctMP = MediaPlayer.create(this, R.raw.correct);
        inCorrectMP = MediaPlayer.create(this, R.raw.incorrect);
        currentProblemText = (TextView)findViewById(R.id.currentProblem);
        firstFactorText = (TextView)findViewById(R.id.firstFactorText);
        secondFactorText = (TextView)findViewById(R.id.secondFactorText);
        questionsLeft = (TextView) findViewById(R.id.questionsLeft);
        timer = (Chronometer) findViewById(R.id.timeTaken);

        questions = 3;
        questionsLeft.setText(String.valueOf(questions));
        timer.start();
        rand = new Random();
        this.newFactorisationProblem();
        speedPracticeIntent = new Intent(FactorisationBolt.this, SpeedPractice.class);
        factorisationInit = getIntent();
        timer.setBase(factorisationInit.getLongExtra("CHRONO_TIME", 0));
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {

            public void onChronometerTick(Chronometer chronometer) {
                chronometer.refreshDrawableState();
                currentQuestionTimeTaken++;
            }
        });


    }

    public void push(View view) {
            charactersEntered++;
            if (charactersEntered <=3) {
                firstFactorText.setText(firstFactorText.getText() + view.getTag().toString());
            } else if (charactersEntered <=6 && charactersEntered > 3){
                secondFactorText.setText(secondFactorText.getText() + view.getTag().toString());

            }
    }

    public String slice_end(String s, int endIndex) {
        if (endIndex < 0) endIndex = s.length() + endIndex;
        return s.substring(0, endIndex);
    }

    public void pull(View view) {

        if (charactersEntered <=3) {
            if (firstFactorText.length() >0) {
                String newCurrentAnswer = slice_end(firstFactorText.getText().toString(),
                        firstFactorText.getText().toString().length() - 1);
                firstFactorText.setText(newCurrentAnswer);
            }
        } else if (charactersEntered > 3){
            if (secondFactorText.length() >0) {
                String newCurrentAnswer = slice_end(secondFactorText.getText().toString(),
                        secondFactorText.getText().toString().length() - 1);
                secondFactorText.setText(newCurrentAnswer);
            }
        }
        if (charactersEntered >0) {
            charactersEntered--;
        }
    }

    public void newFactorisationProblem() {
        currentQuestionsAttempts = 0;
        currentQuestionTimeTaken = 0;
        int number1 = rand.nextInt(9) + 1;
        int number2 = rand.nextInt(9) + 1;
        int operator1 = rand.nextInt(2);
        int operator2 = rand.nextInt(2);
        int xFirst1 = rand.nextInt(2);
        int xFirst2 = rand.nextInt(2);
        answer1 = "";
        answer2 = "";
        int[] answer1Array = new int[2];
        int[] answer2Array = new int[2];
        if (xFirst1 == 0) {
            answer1Array[0] = 1000;
            if (operator1 == 0) {
                answer1Array[1] = number1;
            } else if (operator1 == 1) {
                answer1Array[1] = (-1*number1);
            }
        } else if (xFirst1 == 1) {
            answer1Array[0] = number1;
            if (operator1 == 0) {
                answer1Array[1] = 1000;
            } else if (operator1 == 1) {
                answer1Array[1] = -1000;
            }
        }

        if (xFirst2 == 0) {
            answer2Array[0] = 1000;
            if (operator2 == 0) {
                answer2Array[1] = number2;
            } else if (operator2 == 1) {
                answer2Array[1] = (-1*number2);
            }
        } else if (xFirst2 == 1) {
            answer2Array[0] = number2;
            if (operator2 == 0) {
                answer2Array[1] = 1000;
            } else if (operator2 == 1) {
                answer2Array[1] = -1000;
            }
        }
        ArrayList<String> quadArray = parseArrayFactors(answer1Array,answer2Array);

        parsedAnswer = getParsedAnswer(quadArray);
        SpannableStringBuilder stringBuilder = new SpannableStringBuilder();
        Integer squaredCoeff = Integer.parseInt(quadArray.get(0));
        Integer xCoeff = (Integer.parseInt(quadArray.get(1)) + Integer.parseInt(quadArray.get(2))) / 1000;
        Integer constant = Integer.parseInt(quadArray.get(3));

        if ( squaredCoeff > 0) {
            stringBuilder.append("x2 ");
        } else {
            stringBuilder.append("-x2 ");
        }

        if (xCoeff > 0) {
            stringBuilder.append("+ " + xCoeff + "x ");
        } else if (xCoeff < 0){
            stringBuilder.append("- " + (-1*xCoeff) + "x ");
        }

        if (constant > 0) {
            stringBuilder.append("+ " + constant);
        } else {
            stringBuilder.append("- " + (-1*constant));
        }

        if (String.valueOf(stringBuilder.charAt(0)).equals("-")) {
            stringBuilder.setSpan(new SuperscriptSpan(), 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            stringBuilder.setSpan(new RelativeSizeSpan(0.75f), 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else {
            stringBuilder.setSpan(new SuperscriptSpan(), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            stringBuilder.setSpan(new RelativeSizeSpan(0.75f), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        currentProblemText.setText(stringBuilder);
    }



    public void check(View view) {
        currentQuestionsAttempts++;
        if (firstFactorText.getText().length() == 3 && secondFactorText.getText().length() == 3) {
            int[] factor1Array = parseStringFactors(String.valueOf(firstFactorText.getText()));
            int[] factor2Array = parseStringFactors(String.valueOf(secondFactorText.getText()));
            ArrayList<String> answerParsedArray = parseArrayFactors(factor1Array, factor2Array);
            ArrayList<String> answerParsedArray2 = parseArrayFactors(factor2Array, factor1Array);
            String currentParsedAnswer = getParsedAnswer(answerParsedArray);
            String currentParsedAnswer2 = getParsedAnswer(answerParsedArray2);

            if (currentParsedAnswer.equals(parsedAnswer) || currentParsedAnswer2.equals(parsedAnswer)) {
                if(correctMP.isPlaying()) {
                    correctMP.stop();
                }
                correctMP.start();
                questions--;
                ReportData.getReportData().inputReportData(String.valueOf(currentProblemText.getText()),
                        "(" + firstFactorText.getText() + ") (" + secondFactorText.getText() + ")",
                        String.valueOf(currentQuestionTimeTaken) + "s",
                        String.valueOf(currentQuestionsAttempts));
                newFactorisationProblem();
            } else {
                inCorrectMP.start();
            }

            if (questions == 0) {
                speedPracticeIntent.putExtra("CHRONO_TIME", timer.getBase());
                speedPracticeIntent.putExtra(Intent.EXTRA_TEXT, "Final");
                startActivity(speedPracticeIntent);
            }

        } else {
            inCorrectMP.start();
        }
        firstFactorText.setText("");
        secondFactorText.setText("");
        questionsLeft.setText(Integer.toString(questions));
        charactersEntered = 0;


    }

    public ArrayList<String> parseArrayFactors(int[] answer1Array, int[] answer2Array) {
        ArrayList<String> quadArray = new ArrayList<String>();
        quadArray.add(String.valueOf(answer1Array[0] * answer2Array[0]));
        quadArray.add(String.valueOf(answer1Array[0] * answer2Array[1]));
        quadArray.add(String.valueOf(answer1Array[1] * answer2Array[0]));
        quadArray.add(String.valueOf(answer1Array[1] * answer2Array[1]));
        Collections.sort(quadArray, new Comparator<String>()
        {
            public int compare(String s1,String s2)
            {
                return s2.length() - s1.length();
            }
        });
        return quadArray;
    }

    public int[] parseStringFactors(String factorString) {
        int[] stringFactorArray = new int[2];
        try {
            if (String.valueOf(factorString.charAt(0)).equals("x")) {
                stringFactorArray[0] = 1000;
            } else {
                stringFactorArray[0] = Integer.parseInt(String.valueOf(factorString.charAt(0)));
            }
            if (String.valueOf(factorString.charAt(1)).equals("+")) {
                if (String.valueOf(factorString.charAt(2)).equals("x")) {
                    stringFactorArray[1] = 1000;
                } else {
                    stringFactorArray[1] = Integer.parseInt(String.valueOf(factorString.charAt(2)));

                }
            } else {
                if (String.valueOf(factorString.charAt(2)).equals("x")) {
                    stringFactorArray[1] = -1000;
                } else {
                    stringFactorArray[1] = -1 * Integer.parseInt(String.valueOf(factorString.charAt(2)));
                }
            }
        } catch (NumberFormatException e) {
            stringFactorArray[0] = -1;
            stringFactorArray[1] = -1;
        }
        return stringFactorArray;

    }


    public String getParsedAnswer(ArrayList<String> parsedArray) {
        String parsed = "";
        for (int i = 0; i<parsedArray.size(); i++) {
            parsed += parsedArray.get(i);
            parsed += ",,";
        }
        return parsed;
    }
}