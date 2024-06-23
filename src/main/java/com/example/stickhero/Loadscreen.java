package com.example.stickhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Loadscreen {


    @FXML
    private Label loadHeading;
    @FXML
    private AnchorPane pane;
    @FXML
    public void loadgame(MouseEvent event) throws IOException {
        System.out.println("loadgame was called");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("startgame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);

        // Get the controller of the loaded FXML
        Startgame controller = fxmlLoader.getController();

        // Load the game state and initialize the controller with the loaded data
        Startgame loadedGame = loadState();
        assert loadedGame != null;
        if(loadedGame==null)
        {
            System.out.println("bhenchod");
        }
        controller.initData(loadedGame);//passed the laoded game to that class

        // Get the current stage and set its scene to the loaded game scene
        Stage stage = (Stage) pane.getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
    public Startgame loadState()
    {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("gameState.ser"))) {
            Startgame loadedGame = (Startgame) ois.readObject();
            if(loadedGame==null)
            {
                System.out.println("bruhhhhhhhh");
            }
            System.out.println("Game state loaded successfully.");
            return loadedGame;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}