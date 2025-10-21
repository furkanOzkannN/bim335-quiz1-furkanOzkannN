package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    private TextView winnerTextView;
    private Button resetGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        winnerTextView = findViewById(R.id.winnerTextView);
        resetGameButton = findViewById(R.id.resetGameButton);

        
        Intent intent = getIntent();
        String winnerName = intent.getStringExtra("WINNER_NAME");

        
        winnerTextView.setText(getString(R.string.winner_text, winnerName));

       
        resetGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                finish();
            }
        });
    }
}
