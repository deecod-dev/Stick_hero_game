package com.example.stickhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Gameover implements Initializable {

    @FXML
    private Text bestScoreLabel;
    @FXML
    private Text currentScore;
    private int cherries;
    public void setcherries(int cherries)
    {
        this.cherries=cherries;
    }
    @FXML
    private ImageView restartIcon;
    @FXML
    void goToHome(MouseEvent event) throws IOException {
        Stage stage=(Stage) restartIcon.getScene().getWindow();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gameplay.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        stage.setTitle("Gameplay");
        Gameplay g=fxmlLoader.getController();
        System.out.println("setting before home"+bestscore);
        g.setbest(bestscore);
//        scene.getRoot().requestFocus();
        stage.setScene(scene);
        stage.show();
    }
    public void setScore(int score) {
        currentScore.setText("" + score);
    }
    private int bestscore;
    public void setBestScoreLabel(int best)
    {
        bestscore=best;
        bestScoreLabel.setText(""+best);
    }
    @FXML
    void restart(MouseEvent event) throws IOException {
        Stage currentstage = (Stage) restartIcon.getScene().getWindow();//button ke scene ki window
//        currentstage.close();//smoother if you don't close this
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("startgame.fxml"));//fxml to load
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        Startgame s=fxmlLoader.getController();
        System.out.println("best in lose: "+bestscore);
        s.setbestscore(bestscore);
        currentstage.setTitle("Hello!");
        currentstage.setScene(scene);
        currentstage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}