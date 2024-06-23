package com.example.stickhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Gameplay {
    @FXML
    private Circle characterSelectCircle;
    @FXML
    private Circle playCircle;
    @FXML
    private Circle tutorialPlayCIrcle;
    private int bestscore;
    @FXML
    public void loadGameScreen() throws IOException {
        loadgame();
    }
    public void loadgame() throws IOException {
        System.out.println("loadgame was called");
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("startgame.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);

        // Get the controller of the loaded FXML
        Startgame controller = fxmlLoader.getController();

        // Load the game state and initialize the controller with the loaded data
        Startgame loadedGame = loadState();
//        assert loadedGame != null;
        if(loadedGame==null)
        {
            System.out.println("broo");
        }
        assert loadedGame != null;
        controller.initData(loadedGame);//passed the laoded game to that class

        // Get the current stage and set its scene to the loaded game scene
        Stage stage = (Stage) playCircle.getScene().getWindow();
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
    @FXML
    void selectCharacterMenu(MouseEvent event) throws IOException {
        Stage currentstage = (Stage) playCircle.getScene().getWindow();//button ke scene ki window
//        currentstage.close();//smoother if you don't close this
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("characterchoose.fxml"));//fxml to load
        Scene scene = new Scene(fxmlLoader.load(), 600, 450);
        currentstage.setTitle("Hello!");
        currentstage.setScene(scene);
        currentstage.show();
    }

    @FXML
    void startGame(MouseEvent event) throws IOException {
        Stage currentstage = (Stage) characterSelectCircle.getScene().getWindow();//button ke scene ki window
//        currentstage.close();//smoother if you don't close this
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("startgame.fxml"));//fxml to load
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        currentstage.setScene(scene);
        currentstage.show();
        Startgame s=fxmlLoader.getController();
        System.out.println("setting in new one: "+bestscore);
        s.setbestscore(bestscore);
//        currentstage.setTitle("Hello!");

    }
    @FXML
    void tutorialPlay(MouseEvent event) throws IOException {
        //change the scene
        Stage currentstage = (Stage) playCircle.getScene().getWindow();//button ke scene ki window
//        currentstage.close();//smoother if you don't close this
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("tutorialplay.fxml"));//fxml to load
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        currentstage.setTitle("Hello!");
        currentstage.setScene(scene);
        currentstage.show();

        //start tutorial on this scene

        //start previous scene in tutorialplay controller
        //back option loads back corresponding fxml
    }
    public void setbest(int bestscore) {
        System.out.println("socre that came in new gameplay"+bestscore);
        this.bestscore=bestscore;
    }
}