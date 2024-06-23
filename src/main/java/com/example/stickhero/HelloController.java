package com.example.stickhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private Button changeSceneryButton;

    @FXML
    private AnchorPane pane;

    @FXML
    void changeScenery(MouseEvent event) throws IOException {
        Stage currentstage = (Stage) changeSceneryButton.getScene().getWindow();//button ke scene ki window
//        currentstage.close();//smoother if you don't close this
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Gameplay.fxml"));//fxml to load
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        currentstage.setTitle("Hello!");
        currentstage.setScene(scene);
        currentstage.show();
    }
}