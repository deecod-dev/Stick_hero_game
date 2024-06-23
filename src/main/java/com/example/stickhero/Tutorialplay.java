package com.example.stickhero;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
//import javafx.scene.media.Media;
//import javafx.scene.media.MediaPlayer;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Tutorialplay implements Initializable {

    @FXML
    private Polygon gobackbutton;

    @FXML
    private AnchorPane tutorialpane;
    @FXML
    private MediaView mediaView;

    private MediaPlayer mediaPlayer;

    @FXML
    void goback(MouseEvent event) throws IOException {
        Stage currentstage = (Stage) gobackbutton.getScene().getWindow();//button ke scene ki window
//        currentstage.close();//smoother if you don't close this
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gameplay.fxml"));//fxml to load
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        currentstage.setTitle("Hello!");
        currentstage.setScene(scene);
        currentstage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("tut runs................done");

        // Replace spaces with %20 in the file path
        String videoPath = "file:/C:/Users/Dev%20Utkarsh/OneDrive/Desktop/idea/AP_PROJ-5th_update/stickHero/stickHero/src/main/resources/com/example/stickhero/tut.mp4";

        // Create a Media object with the video file
        Media media = new Media(videoPath);

        // Create a MediaPlayer with the Media object
        mediaPlayer = new MediaPlayer(media);

        // Set the MediaPlayer to the MediaView
        mediaView.setMediaPlayer(mediaPlayer);

        // Start playing the video
        mediaPlayer.play();
    }

}