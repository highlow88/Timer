package com.example.timer;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

public class TimerThread extends Thread {

    private boolean isRunning = true;
    private Handler handler;

    public TimerThread(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        int seconds = 0;
        while (isRunning) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            seconds++;
            String time = formatTime(seconds);
            sendMessageToHandler(time);
        }
    }

    private String formatTime(int seconds) {
        int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        int secs = seconds % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, secs);
    }

    private void sendMessageToHandler(String time) {
        if (handler != null) {
            Message message = handler.obtainMessage();
            message.obj = time;
            handler.sendMessage(message);
        }
    }

    public void stopTimer() {
        isRunning = false;
    }
}
