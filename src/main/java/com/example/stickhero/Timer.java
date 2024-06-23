package com.example.stickhero;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.Serializable;

public class Timer extends Thread implements Serializable {
    transient private AnchorPane p;
    transient private Label l;
    private long starttime;
    private boolean isPaused;
    private long pauseTime;
    private long earlier;
    transient private final Object lock = new Object();

    Timer(AnchorPane p, Label l,long e) {
        this.earlier=e;
        this.l = l;
        this.p = p;
        this.starttime = System.currentTimeMillis();
        l.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        if(e!=0) {
            this.isPaused = true;
        }
        else {
            this.isPaused = false;
        }
    }
    public double getdiff() {
        return (earlier+((double) (System.currentTimeMillis() - starttime) / 1000));
    }
    public void pause() {
        isPaused = true;
        pauseTime = (System.currentTimeMillis() - starttime)/1000;
        System.out.println("pausing time: "+pauseTime);
    }
    public long getLastTime()
    {
        return (long) getdiff();
    }

    public void setPaused(boolean paused) {
        isPaused = paused;
    }

    public void res(long e) {
        this.earlier = e;
        System.out.println(isPaused);
        if (isPaused) {
            isPaused = false;
            synchronized (lock) {
                lock.notify(); // Notify the thread to resume
            }
            // Update starttime to the current time minus the pause time
            starttime = System.currentTimeMillis() - pauseTime;
        }
    }

    public void loadt(long e) {
        //at time of saving
        this.earlier = e;
        System.out.println(isPaused);
        if (isPaused) {
            isPaused = false;
            if (!isAlive()) { // Check if the thread is not already running
                start();
            }
        }
    }

    @Override
    public void run() {
        while (true) {
            synchronized (lock) {
                try {
                    if (isPaused) {
                        lock.wait();
                        // Don't update starttime here
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }

            Platform.runLater(() -> l.setText("" + getdiff()));
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
