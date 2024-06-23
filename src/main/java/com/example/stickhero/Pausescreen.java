package com.example.stickhero;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class Pausescreen {
    private Stage toremove;
    private Startgame toSave;//instance to save
    @FXML
    private Pane pane;
    @FXML
    private ImageView resumeButton;
    private long earlier;
    private Timer t;
    public void getEarlier(Timer t,long e)
    {
        this.earlier=e;
        this.t=t;
        System.out.println("passed: "+e);
//        t.saveearlier(e);
//        t.res(e);
    }
    public void savethispane(Stage st)
    {
        this.toremove=st;
    }
    public void setInstanceToSave(Startgame s)
    {
        this.toSave=s;
    }
    @FXML
    public void resumeGame(MouseEvent mouseEvent) {
        Stage currentScene=(Stage) resumeButton.getScene().getWindow();
        t.setPaused(true);
        t.res(earlier);
        currentScene.close();
    }
    public void saveGame(MouseEvent mouseEvent) throws IOException {
//        t.saveearlier(earlier);
        toremove.close();
//        System.out.println(t.getEarlier()+"pppppppppppppppppppp");//yaha pe bhi correct before save
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("gameState.ser"))) {
//            System.out.println();
            oos.writeObject(toSave);
            System.out.println("Game state saved successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stage currentstage = (Stage) resumeButton.getScene().getWindow();//button ke scene ki window
//        currentstage.close();//smoother if you don't close this
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gameplay.fxml"));//fxml to load
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        currentstage.setTitle("Hello!");
        currentstage.setScene(scene);
        currentstage.show();
    }
}
