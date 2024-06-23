package com.example.stickhero;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;

public class Datatable {
    private int width1;
    private int score=0;
    private int width2;
    private int rand1;
    private int numCherries;
    transient private Rectangle TOWER1;
    transient private Rectangle TOWER2;
    private int distBetweenTowers;
    transient private Stick st;
    private int charState=0;
    /////////////////////above fields are to be changed
    @FXML
    transient private ImageView backgroundImg;
    @FXML
    transient private Label cherryLabel;
    @FXML
    transient private Label scoreLabel;
    private final int DIST_2NDTOWERSTART=280;
    private final int TOWERHEIGHT=243;
    private int charMoving=0;//---------------------------------------------------------
    transient private ImageView characterImg;//-------------------------------------------------
    transient private ImageView cherryImg;
    @FXML
    transient private AnchorPane startgamePane;
    boolean cherryCollision=false;
}
