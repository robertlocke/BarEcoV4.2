package com.example.barecov1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class HomeActivity extends AppCompatActivity {

    User user = new User();

    //declaring variables to correspond to elements
    private Button scan;
    private Button history;
    private Button feedback;
    private Button account;
    private TextView test;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //https://stackoverflow.com/questions/2091465/how-do-i-pass-data-between-activities-in-android-application
        String email = getIntent().getStringExtra("user_email");
        user.setEmail(email);

        //connecting variables to elements by id
        scan = (Button) findViewById(R.id.btnScan);
        history = (Button) findViewById(R.id.btnHistory);
        feedback = (Button) findViewById(R.id.btnFeedback);
        account = (Button) findViewById(R.id.btnAccount);

        //Giving actions to the variables when they are clicked
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openScanner();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHistory();
            }
        });
        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openFeedback();
            }
        });
        account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAccountSettings();
            }
        });

}

    //Opens the Scan activity
    public void openScanner(){
        Intent intent = new Intent(this, ScanActivity.class);
        startActivity(intent);
    }
    //Opens the History activity
    public void openHistory(){
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }
    //Opens the feedback activity
    public void openFeedback(){
        Intent intent = new Intent(this, FeedbackActivity.class);
        startActivity(intent);
    }

    //Opens the account activity
    public void openAccountSettings(){
        Intent intent = new Intent(this, AccountActivity.class);
        intent.putExtra("user_email", user.getEmail());
        startActivity(intent);
    }



}
