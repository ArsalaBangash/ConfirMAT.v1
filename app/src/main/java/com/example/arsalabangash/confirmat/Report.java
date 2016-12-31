package com.example.arsalabangash.confirmat;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

public class Report extends AppCompatActivity implements ReportAdapter.ListItemClickListener {
    Intent reportInit;
    private static final int NUM_REPORT_ITEMS = ReportData.getReportData().getReportSize();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_report);
        TextView reportTimeTakenText = (TextView)findViewById(R.id.reportTimeTaken);
        reportInit = getIntent();
        String timeTaken = reportInit.getStringExtra(Intent.EXTRA_TEXT);
        reportTimeTakenText.setText("Your Time Taken: " + timeTaken);

        RecyclerView reportRecycler = (RecyclerView) findViewById(R.id.reportList);
        LinearLayoutManager reportLayoutManager = new LinearLayoutManager(this);
        reportRecycler.setLayoutManager(reportLayoutManager);
        reportRecycler.setHasFixedSize(true);
        ReportAdapter reportAdapter = new ReportAdapter(NUM_REPORT_ITEMS, this);
        reportRecycler.setAdapter(reportAdapter);
    }

    @Override
    public void onListClick(int clickedItemIndex) {
        Toast toast = Toast.makeText(this, "Hello", Toast.LENGTH_SHORT);
        toast.show();
    }
}
