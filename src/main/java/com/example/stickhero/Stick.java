package com.example.stickhero;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.io.Serializable;

public class Stick implements Serializable {
    transient private static Stick st=null;

    public static Stick getInstance(AnchorPane startgamePane,int TOWERHEIGHT,int width1)
    {
        if(st==null)
        {
            st=new Stick(startgamePane,TOWERHEIGHT,width1);
        }
        return st;
    }

    private double stickrootx;
    private double stickrooty;
    transient private Rectangle Stick;

    public void setStickRotating(int stickRotating) {
        this.stickRotating = stickRotating;
    }

    private int stickRotating=0;
    public double getStickrootx() {
        return stickrootx;
    }

    public double getStickrooty() {
        return stickrooty;
    }

    public Rectangle getStick() {
        return Stick;
    }

    public boolean isElongating() {
        return elongating;
    }

    public void setStickrootx(double stickrootx) {
        this.stickrootx = stickrootx;
    }

    public void setStickrooty(double stickrooty) {
        this.stickrooty = stickrooty;
    }

    public void setStick(Rectangle stick) {
        Stick = stick;
    }

    public void setElongating(boolean elongating) {
        this.elongating = elongating;
    }

    private boolean elongating = false;
    transient private AnchorPane startgamePane;
    private int TOWERHEIGHT;
    private int width1;
    Stick(AnchorPane startgamePane,int TOWERHEIGHT,int width1)
    {
        this.startgamePane=startgamePane;
        this.TOWERHEIGHT=TOWERHEIGHT;
        this.width1=width1;
//        setupStick();
    }
    public void setupStick(int width1)
    {
        Stick=new Rectangle(5,5);
        Stick.setFill(Color.BLACK);
        stickrootx=120+width1-5;
        stickrooty=TOWERHEIGHT;
        Stick.setY(stickrooty);
        Stick.setX(stickrootx);
        startgamePane.getChildren().addAll(Stick);
    }
    public void elongateStick()//by one
    {
        if (elongating && stickRotating!=1) {
//            Platform.runLater(() -> {
                Stick.setHeight(Stick.getHeight() + 1);
                Stick.setY(Stick.getY()-1);
//            });
        }
    }
    public void stick_function(int i)
    {
        switch (i)
        {
            case 1:
                elongateStick();
                break;
            case 2:
                startElongationAnimation();
                break;
            default:
                System.out.println("Not an applicable case");
                break;
        }
    }
    public int getStickRotating() {
        return stickRotating;
    }

    public void startElongationAnimation()//--------------------------------------
    {
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(10),e -> elongateStick())
        );
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }
}
