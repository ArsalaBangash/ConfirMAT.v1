package com.example.arsalabangash.confirmat;

import java.util.ArrayList;


public class ReportData {

    private static ReportData reportInstance = new ReportData();
    private ArrayList<String[]> reports = new ArrayList<>();
    private int questionNum = 0;

    private ReportData(){}

    public static ReportData getReportData(){
        return reportInstance;
    }

    public void inputReportData(String question, String answer, String timeTaken, String attempts) {

        String[] reportDataArray = {question,answer,timeTaken,attempts};
        reports.add(questionNum, reportDataArray);
        questionNum++;
    }

    public int getReportSize() {
        return reports.size();
    }

    public StringBuilder getQuestionReport(int questionIndex) {
        String [] reportDataArray = reports.get(questionIndex);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Question: " + reportDataArray[0]);
        stringBuilder.append("\nAnswer: " + reportDataArray[1]);
        stringBuilder.append("\nTime Taken: " + reportDataArray[2]);
        stringBuilder.append("\nAttempts: " + reportDataArray[3]);
        return stringBuilder;
    }
}
