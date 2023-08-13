package com.example.timer;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.timer.R;

public class MainActivity extends AppCompatActivity {

    private TextView timerTextView;
    private Button startStopButton;
    private boolean isTimerRunning = false;

    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        timerTextView = findViewById(R.id.timerTextView);
        startStopButton = findViewById(R.id.startStopButton);

        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                // Update the timerTextView with the new time
                timerTextView.setText((String) msg.obj);
            }
        };

        startStopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isTimerRunning) {
                    // Stop the timer
                    isTimerRunning = false;
                    startStopButton.setText("Start");
                    // Handle stopping the timer thread
                } else {
                    // Start the timer
                    isTimerRunning = true;
                    startStopButton.setText("Stop");
                    // Handle starting the timer thread
                }
            }
        });
    }
}
