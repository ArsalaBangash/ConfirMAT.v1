package com.example.arsalabangash.confirmat;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.RelativeSizeSpan;
import android.text.style.SuperscriptSpan;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Random;

public class MathModel {

    private HashMap<Integer,Integer> exponentMap;
    private int operator, a, b, answer;
    private double c, d, dAnswer;
    private Random rand;
    private DecimalFormat numberFormat, numberFormat2;
    private Boolean isDecimalInt;
    private SpannableStringBuilder questionStringBuilder;
    SharedPreferences sharedPreferences;

    public MathModel(SharedPreferences boltsPreferences) {
        sharedPreferences = boltsPreferences;
        exponentMap = new HashMap<>();
        exponentMap.put(2,5);
        exponentMap.put(3,3);
        exponentMap.put(4,2);
        exponentMap.put(5,2);
        exponentMap.put(6,2);
        exponentMap.put(7,1);
        exponentMap.put(8,1);
        exponentMap.put(9,1);
        exponentMap.put(10,2);
        exponentMap.put(11,1);
        exponentMap.put(12,1);
        exponentMap.put(13,1);
        rand = new Random();
        numberFormat = new DecimalFormat("#.0");
        numberFormat2 = new DecimalFormat("#");
        operator = rand.nextInt(5);
    }


    private SpannableStringBuilder addFlash() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        a = rand.nextInt(100) + 1;
        b = rand.nextInt(100) + 1;
        answer = a + b;
        spannableStringBuilder.append(Integer.toString(a) + "+" + Integer.toString(b) + " =");
        return spannableStringBuilder;
    }

    private SpannableStringBuilder subtractFlash() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        a = rand.nextInt(100) + 1;
        b = rand.nextInt(100) + 1;
        answer = a - b;
        spannableStringBuilder.append(Integer.toString(a) + "-" + Integer.toString(b) + " =");
        return spannableStringBuilder;
    }

    private SpannableStringBuilder multiplyFlash() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        a = rand.nextInt(12) + 2;
        b = rand.nextInt(12) + 2;
        answer = a * b;
        spannableStringBuilder.append(Integer.toString(a) + "*" + Integer.toString(b) + " =");
        return spannableStringBuilder;
    }

    private SpannableStringBuilder divideFlash() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        answer = rand.nextInt(12) + 1;
        b = rand.nextInt(12) + 1;
        c = answer * b;
        spannableStringBuilder.append(numberFormat2.format(c) + "\u00F7" + Integer.toString(b) + " =");
        return spannableStringBuilder;
    }

    private SpannableStringBuilder exponentFlash() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        a = rand.nextInt(10) + 3;
        b = rand.nextInt(exponentMap.get(a)) + 2;
        answer = ((int) Math.pow((double) a, (double) b));
        spannableStringBuilder.append(String.valueOf(a) + String.valueOf(b) + " =");
        if (spannableStringBuilder.length() == 4) {
            spannableStringBuilder.setSpan(new SuperscriptSpan(), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableStringBuilder.setSpan(new RelativeSizeSpan(0.75f), 1, 2, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        if (spannableStringBuilder.length() == 5) {
            if (a - 10 >= 0) {
                spannableStringBuilder.setSpan(new SuperscriptSpan(), 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.setSpan(new RelativeSizeSpan(0.75f), 2, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            } else {
                spannableStringBuilder.setSpan(new SuperscriptSpan(), 1, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                spannableStringBuilder.setSpan(new RelativeSizeSpan(0.75f), 1, 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return spannableStringBuilder;
    }

    private SpannableStringBuilder rootFlash() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        answer = rand.nextInt(10) + 3;
        b = rand.nextInt(exponentMap.get(answer)) + 2;
        System.out.println(b);
        a = (int) Math.pow((double)answer, (double)b);
        if(b == 2) {
            spannableStringBuilder.append("\u221A" + String.valueOf(a) + " =");
        } else {
            spannableStringBuilder.append(String.valueOf(b) + "\u221A" + String.valueOf(a) + " =");
            spannableStringBuilder.setSpan(new SuperscriptSpan(), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            spannableStringBuilder.setSpan(new RelativeSizeSpan(0.5f), 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableStringBuilder;
    }

    private SpannableStringBuilder logFlash() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        b = rand.nextInt(10) + 3;
        answer = rand.nextInt(exponentMap.get(b)) + 2;
        a = ((int) Math.pow((double) b, (double) answer));
        spannableStringBuilder.append("log" + String.valueOf(b) + "(" + String.valueOf(a) + ")=");
        if (String.valueOf(b).length() == 1) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(0.35f), 3, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        } else if (String.valueOf(b).length() == 2) {
            spannableStringBuilder.setSpan(new RelativeSizeSpan(0.35f), 3, 5, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableStringBuilder;
    }

    private SpannableStringBuilder modulusFlash() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        b = rand.nextInt(20) + 2;
        a = b + rand.nextInt(20);
        answer = a % b;
        spannableStringBuilder.append(Integer.toString(a) + "\uFF05" + Integer.toString(b) + " =");
        return spannableStringBuilder;
    }

    private SpannableStringBuilder decAdditionFlash() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        c = Double.valueOf(numberFormat.format(1 + (21 - 1) * rand.nextDouble()));
        d = Double.valueOf(numberFormat.format(1 + (21 - 1) * rand.nextDouble()));
        dAnswer = Double.valueOf(numberFormat.format(c+d));
        if (dAnswer == Math.floor(dAnswer)) {
            isDecimalInt = true;
        } else {
            isDecimalInt = false;
        }
        spannableStringBuilder.append(Double.toString(c) + "+" + Double.toString(d) + " =");
        return spannableStringBuilder;
    }

    private SpannableStringBuilder decSubtractionFlash() {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        c = Double.valueOf(numberFormat.format(1 + (21 - 1) * rand.nextDouble()));
        d = Double.valueOf(numberFormat.format(1 + (21 - 1) * rand.nextDouble()));
        dAnswer = Double.valueOf(numberFormat.format(c - d));
        if (dAnswer == Math.floor(dAnswer)) {
            isDecimalInt = true;
        } else {
            isDecimalInt = false;
        }
        spannableStringBuilder.append(Double.toString(c) + "-" + Double.toString(d) + " =");
        return spannableStringBuilder;
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public SpannableStringBuilder newProblem() {
        questionStringBuilder = new SpannableStringBuilder();

        while ((questionStringBuilder.length() == 0)) {
            operator = rand.nextInt(76);

            if (operator <= 10) {
                if (sharedPreferences.getBoolean("Addition", true)) {
                    questionStringBuilder = addFlash();
                }

            } else if (operator > 10 && operator <= 20) {
                if (sharedPreferences.getBoolean("Subtraction", true)) {
                    questionStringBuilder = subtractFlash();
                }

            } else if (operator > 20 && operator <= 30) {
                if (sharedPreferences.getBoolean("Multiplication", true)) {
                    questionStringBuilder = multiplyFlash();
                }

            } else if (operator > 30 && operator <= 40) {
                if (sharedPreferences.getBoolean("Division", true)) {
                    questionStringBuilder = divideFlash();
                }

            } else if (operator > 40 && operator <= 49) {
                if (sharedPreferences.getBoolean("Log", true)) {
                    questionStringBuilder = exponentFlash();
                }

            } else if (operator > 49 && operator <= 54) {
                if (sharedPreferences.getBoolean("Exponents", true)) {
                    questionStringBuilder = rootFlash();
                }

            } else if (operator > 54 && operator <= 60) {
                if (sharedPreferences.getBoolean("Root", true)) {
                    questionStringBuilder = logFlash();
                }

            } else if (operator > 60 && operator <= 65) {
                if (sharedPreferences.getBoolean("Modulus", true)) {
                    questionStringBuilder = modulusFlash();
                }

            } else if (operator > 65 && operator <= 70) {
                if (sharedPreferences.getBoolean("DecimalAddition", true)) {
                    questionStringBuilder = decAdditionFlash();
                }

            } else if (operator > 70 && operator <= 75) {
                if (sharedPreferences.getBoolean("DecimalSubtraction", true)) {
                    questionStringBuilder = decSubtractionFlash();
                }

            }
        }
        return questionStringBuilder;
    }

    
    
    public String getAnswer() {
        if (operator > 65 && operator <= 75) {
            if (isDecimalInt) {
                return numberFormat2.format(dAnswer);
            } else {
                return String.valueOf(dAnswer);
            }
        } else
            return String.valueOf(answer);
    }
}
