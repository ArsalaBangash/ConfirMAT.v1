package com.example.arsalabangash.confirmat;

import java.util.ArrayList;

/**
 * A Singleton class that contains final report data from the Math Practice storm
 */
public class ReportData {

    private static ReportData reportInstance = new ReportData();
    private ArrayList<String[]> reports = new ArrayList<>();
    private int questionNum = 0;

    private ReportData(){}

    /**
     * Returns the singleton instance of the ReportData class
     * @return The singleton instance of the ReportData class
     */
    public static ReportData getReportData(){
        return reportInstance;
    }

    /**
     * Creates an array of Strings that contains the report data for a particular question
     * and adds it to the reports ArrayList of questions
     * @param question The current question's text
     * @param answer The current question's answer
     * @param timeTaken The time taken on the question
     * @param attempts The attempts on the question
     */
    public void inputReportData(String question, String answer, String timeTaken, String attempts) {

        String[] reportDataArray = {question,answer,timeTaken,attempts};
        reports.add(questionNum, reportDataArray);
        questionNum++;
    }

    public int getReportSize() {
        return reports.size();
    }

    /**
     * Returns a character sequence representation of a question's report data. This is done by
     * getting the question's data from the reports ArrayList and concatenating the necessary data
     * into a character sequence.
     * @param questionIndex The question number to get the data from
     * @return A character sequence representation of the question's data
     */
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
