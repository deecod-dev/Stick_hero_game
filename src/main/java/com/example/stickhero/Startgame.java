package com.example.stickhero;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;
import javafx.fxml.Initializable;
import javafx.animation.FadeTransition;
import javafx.scene.control.Label;
import java.io.*;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Scale;
import javafx.scene.transform.Translate;
import javafx.stage.Stage;

public class Startgame implements Initializable,Serializable {
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
    private int bestscore;
    private int charMoving=0;//---------------------------------------------------------
    transient private ImageView characterImg;//-------------------------------------------------
    transient private ImageView cherryImg;
    @FXML
    transient private AnchorPane startgamePane;
    private boolean cherryCollision=false;
    transient private Random rand=new Random();
    private double cherryX;
    private double cherryY;
    private Timer timer;
    @FXML
    transient private Label timerlabel;
    private long earlier;
    transient private Rectangle rsmall;
    @FXML
    transient private Label perfectLabel;
    private transient String[] backgrounds = {"C:\\Users\\Dev Utkarsh\\OneDrive\\Desktop\\idea\\AP_PROJ-5th_update\\stickHero\\stickHero\\src\\main\\resources\\com\\example\\stickhero\\bb1.png", "C:\\Users\\Dev Utkarsh\\OneDrive\\Desktop\\idea\\AP_PROJ-5th_update\\stickHero\\stickHero\\src\\main\\resources\\com\\example\\stickhero\\bb3.png","C:\\Users\\Dev Utkarsh\\OneDrive\\Desktop\\idea\\AP_PROJ-5th_update\\stickHero\\stickHero\\src\\main\\resources\\com\\example\\stickhero\\bb4.png","C:\\Users\\Dev Utkarsh\\OneDrive\\Desktop\\idea\\AP_PROJ-5th_update\\stickHero\\stickHero\\src\\main\\resources\\com\\example\\stickhero\\bb5.png","C:\\Users\\Dev Utkarsh\\OneDrive\\Desktop\\idea\\AP_PROJ-5th_update\\stickHero\\stickHero\\src\\main\\resources\\com\\example\\stickhero\\bb6.jpg"};
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        System.out.println("wditdms"+width1);
        st=new Stick(startgamePane,TOWERHEIGHT,width1);
//        st=Stick.getInstance(startgamePane,TOWERHEIGHT,width1);
        startAnimation();
    }
    public double getCherryX() {
        return cherryX;
    }

    public double getCherryY() {
        return cherryY;
    }

    public long getEarlier() {
        return earlier;
    }

    public int getBestscore() {
        return bestscore;
    }

    public void initData(Startgame loadedgame)
    {
        backgroundImg.setImage(new Image(backgrounds[rand.nextInt(0,5)]));
//        playSound("C:\\Users\\Dev Utkarsh\\OneDrive\\Desktop\\idea\\AP_PROJ-5th_update\\stickHero\\stickHero\\src\\main\\resources\\com\\example\\stickhero\\mainsound.mp3",0,100000);
        startgamePane.getChildren().remove(TOWER1);
        startgamePane.getChildren().remove(TOWER2);
        startgamePane.getChildren().remove(characterImg);
        startgamePane.getChildren().remove(cherryImg);
        startgamePane.getChildren().remove(st.getStick());
        startgamePane.getChildren().remove(rsmall);
//        System.out.println("came here");
        System.out.println("hhhhhhhhhhhhhhhhh"+loadedgame.getEarlier());
        earlier=loadedgame.getEarlier();
//        p = loadedgame.getStartgamePane();
//        this.startgamePane=loadedgame.getStartgamePane();
        System.out.println("changed pane and wasn't null");
        this.bestscore=loadedgame.getBestscore();
        this.width1 = loadedgame.getWidth1();
        this.score = loadedgame.getScore();
        this.width2 = loadedgame.getWidth2();
        this.rand1 = loadedgame.getRand1();
        this.numCherries = loadedgame.getNumCherries();
        this.cherryX=loadedgame.getCherryX();
        this.cherryY=loadedgame.getCherryY();
//        this.TOWER1 = loadedgame.getTOWER1();//done
//        this.TOWER2 = loadedgame.getTOWER2();//done
        this.distBetweenTowers = loadedgame.getDistBetweenTowers();
//        this.st=new Stick(startgamePane,TOWERHEIGHT,width1);//------------------------------
        this.charState = loadedgame.getCharState();
//        this.backgroundImg = loadedgame.getBackgroundImg();
//        this.cherryLabel = loadedgame.getCherryLabel();
//        this.scoreLabel = loadedgame.getScoreLabel();
        this.charMoving = loadedgame.getCharMoving();
//        this.characterImg = loadedgame.getCharacterImg();//done
//        this.cherryImg = loadedgame.getCherryImg();
        this.cherryCollision = loadedgame.isCherryCollision();
//        this.rand=new Random();//-----------------------------------------------
        //make methods to place rectangles at specific distances and characterimg
        // at a distance and stick too and
        //saare commented ko math ki values se load karna and then no method call
        //cuz startanimation ke baad click ka wait hai
        Rectangle r1= new Rectangle(width1,160);
        Rectangle r2=new Rectangle(width2,160);
        r1.setX(120);
        r2.setX(120+width1+distBetweenTowers);
        r1.setY(TOWERHEIGHT);
        r2.setY(TOWERHEIGHT);
        r1.setFill(Color.BLACK);
        r2.setFill(Color.BLACK);
        TOWER1=r1;
        TOWER2=r2;
        startgamePane.getChildren().addAll(TOWER1,TOWER2);

        cherryImg=new ImageView();
        cherryImg.setImage(new Image("file:C:/Users/Dev Utkarsh/OneDrive/Desktop/idea/stickHero/src/assets/cherry.png",20.0,20.0,false,false));
        cherryImg.setX(cherryX);
        cherryImg.setY(248);
        startgamePane.getChildren().addAll(cherryImg);

        st= new Stick(startgamePane,TOWERHEIGHT,width1);
//        st=Stick.getInstance(startgamePane,TOWERHEIGHT,width1);
        st.setupStick(width1);
//        startgamePane.getChildren().addAll(s.getStick());

        setupCharacter("C:/Users/Dev Utkarsh/OneDrive/Desktop/idea/stickHero/src/assets/char.png",width1);

        scoreLabel.setText(""+score);
        cherryLabel.setText(""+numCherries);
//        Timer t=new Timer(startgamePane,timerlabel,earlier);
        System.out.println(earlier);
//        t.res(earlier);
        timer.pause();
        timer=new Timer(startgamePane,timerlabel,earlier);
        System.out.println(earlier);
        timer.loadt(earlier);

        rsmall=new Rectangle(10,5);
        rsmall.setFill(Color.RED);
        rsmall.setX(DIST_2NDTOWERSTART+rand1+((double) width2 /2)-5);
        rsmall.setY(TOWERHEIGHT);
        startgamePane.getChildren().addAll(rsmall);
    }
    public void startAnimation()
    {
        backgroundImg.setImage(new Image(backgrounds[rand.nextInt(0,5)]));
//        playSound("C:\\Users\\Dev Utkarsh\\OneDrive\\Desktop\\idea\\AP_PROJ-5th_update\\stickHero\\stickHero\\src\\main\\resources\\com\\example\\stickhero\\mainsound.mp3",0,1000);
        width1=make_and_place_rectangle(120,0);
        rand1=make_and_place_rectangle(120+width1,1);
        distBetweenTowers= DIST_2NDTOWERSTART-120-width1+rand1;
        setupCharacter("C:/Users/Dev Utkarsh/OneDrive/Desktop/idea/stickHero/src/assets/char.png",width1);
        setupCherry("C:/Users/Dev Utkarsh/OneDrive/Desktop/idea/stickHero/src/assets/cherry.png",120+width1,120+width1+distBetweenTowers);
        st.setupStick(width1);

        ////////this
        timer=new Timer(startgamePane,timerlabel,0);
        timer.start();

        System.out.println("in starting---------");
        System.out.println("dist bw towers: "+distBetweenTowers);
        System.out.println("Tower1 width: "+width1+"Tower2 width: "+width2);
        System.out.println("\n\n");
    }
    public void animationFinished()
    {
        score++;
        scoreLabel.setText(""+score);
        //only left tower on screen
        TOWER1=TOWER2;
        width1=width2;//puraani vaali
//        stickrootx=120+width1;
        st.setStickrootx(120+width1);
        rand1=make_and_place_rectangle(0,1);//tower2 set width2 set rand1 set
        distBetweenTowers= 280+rand1-120-width1;
        st=new Stick(startgamePane,TOWERHEIGHT,width1);
        st.setupStick(width1);
        setupCherry("C:/Users/Dev Utkarsh/OneDrive/Desktop/idea/stickHero/src/assets/cherry.png",120+width1,120+width1+distBetweenTowers);
//        line1 = new Line(distBetweenTowers + width1 + 120, 0, distBetweenTowers + width1 + 120, TOWERHEIGHT);
//        startgamePane.getChildren().add(line1);
//
//        // Adding the second line
//        line2 = new Line(distBetweenTowers + width1 + 120 + width2, 0, distBetweenTowers + width1 + 120 + width2, TOWERHEIGHT);
//        startgamePane.getChildren().add(line2);
    }
    public void setupCharacter(String path,double width1)//------------------------------
    {
        characterImg=new ImageView();
        characterImg.setImage(new Image("file:" + path,26.0,27.0,false,false));//38 is extra width of imageview
        characterImg.setX(100+width1-10);
        characterImg.setY(218);
        startgamePane.getChildren().addAll(characterImg);
    }
    public void setupCherry(String path,int start,int end)
    {
        cherryImg=new ImageView();
        cherryImg.setImage(new Image("file:" + path,20.0,20.0,false,false));
        int cherryind=(rand.nextInt(start,end-15));
        cherryImg.setX(cherryind);
        System.out.println("Cherry at: "+cherryind);
        cherryImg.setY(248);
        this.cherryX=cherryImg.getX();
        this.cherryY=cherryImg.getY();
        startgamePane.getChildren().addAll(cherryImg);
    }
    public int make_and_place_rectangle(int dist,int flag)//distance from the previous tower's right
    {
        //all blocks after 120 length
        //1st one right part is b/w 150 and 200, exact=120+width1
        //2nd one after 400 and bef 530, exact left=DIST_2NDTOWERSTART+random length
        if(flag==0)
        {
            width1=rand.nextInt(30,80);
            Rectangle r = new Rectangle(width1,160);
            r.setX(dist);
            r.setY(TOWERHEIGHT);
            r.setFill(Color.BLACK);
            TOWER1=r;
            startgamePane.getChildren().addAll(r);
//            Stick.setX(120+width1-5);
            return width1;
        }
        System.out.println("in block making------------");
        rand1=rand.nextInt(0,90);
        width2=rand.nextInt(30,80);
        Rectangle r = new Rectangle(width2,160);
        TOWER2=r;
        r.setX(DIST_2NDTOWERSTART+rand1);
        r.setY(TOWERHEIGHT);
        r.setFill(Color.BLACK);
        startgamePane.getChildren().addAll(r);

        rsmall=new Rectangle(10,5);
        rsmall.setFill(Color.RED);
        rsmall.setX(DIST_2NDTOWERSTART+rand1+((double) width2 /2)-5);
        rsmall.setY(TOWERHEIGHT);
        startgamePane.getChildren().addAll(rsmall);
        return rand1;
    }
    @FXML
    void pauseGame(MouseEvent event) throws IOException {
        //timer here
        //communicating with controllers
        timer.pause();
        this.earlier=timer.getLastTime();
        System.out.println("time at last: "+timer.getLastTime());
        // Create a semi-transparent overlay rectangle
        Rectangle overlay = new Rectangle(startgamePane.getWidth(), startgamePane.getHeight(), Color.rgb(50, 0, 90, 0.5));
        startgamePane.setDisable(true);//is pane ko disable kardiya
        startgamePane.getChildren().add(overlay);//uspe rec add kardiya

        //loading controller
        FXMLLoader fxml = new FXMLLoader(HelloApplication.class.getResource("pausescreen.fxml"));//pauscescreen ko load kiya and uska controller
        Scene pauseScene = new Scene(fxml.load(), 400, 300);//uska scene set kiya with pause fxml

        ///////////////////////////thissssssssssssssssssssssssss is talking to controllers
        Pausescreen controller=fxml.getController();//a variable of pausescreen type to pass value in a method
        //yaha se this pass karna hai taaki, we can save this(startgame of this instance) on pause page, the thing we can do here
        controller.getEarlier(timer,earlier);
//        System.out.println(timer.getEarlier());
        controller.setInstanceToSave(this);
        controller.savethispane((Stage)timerlabel.getScene().getWindow());
        Stage pauseStage = new Stage();
        pauseStage.setTitle("Paused");
        //add scene to stage
        pauseStage.setScene(pauseScene);
        // Show the pause screen
        System.out.println("bruhhhhhhhhhhhhhhhhhhhhh");
        pauseStage.showAndWait();//bas paused code here, till not closed, then I close it in pausescreen class
        // Remove the overlay and re-enable event handlers after closing the pause screen
        startgamePane.getChildren().remove(overlay);
        startgamePane.setDisable(false);
    }
    @FXML
    void pressed()
    {
        if(charMoving==1)//moving
        {
            flipChar();
        }
        else if(st.getStickRotating() != 1)//not rotating
        {
            /////////////////////////
            /////////////////////////
//            elongating = true;
            st.setElongating(true);
//            startElongationAnimation();
//            st.startElongationAnimation();
            st.stick_function(2);
//            stick.startElongationAnimation();
        }
    }
    @FXML
    void released()
    {
        if(charMoving!=1 && st.getStickRotating()!=1)//not rotating and moving
        {
            st.setElongating(false);
            rotateStickAroundAndMoveChar();
        }
    }
    public int getWidth1() {
        return width1;
    }

    public int getScore() {
        return score;
    }

    public int getWidth2() {
        return width2;
    }

    public int getRand1() {
        return rand1;
    }

    public int getNumCherries() {
        return numCherries;
    }

    public Rectangle getTOWER1() {
        return TOWER1;
    }

    public Rectangle getTOWER2() {
        return TOWER2;
    }

    public int getDistBetweenTowers() {
        return distBetweenTowers;
    }

    public Stick getSt() {
        return st;
    }

    public int getCharState() {
        return charState;
    }

    public ImageView getBackgroundImg() {
        return backgroundImg;
    }

    public Label getCherryLabel() {
        return cherryLabel;
    }

    public Label getScoreLabel() {
        return scoreLabel;
    }

    public int getDIST_2NDTOWERSTART() {
        return DIST_2NDTOWERSTART;
    }

    public int getTOWERHEIGHT() {
        return TOWERHEIGHT;
    }

    public int getCharMoving() {
        return charMoving;
    }

    public ImageView getCharacterImg() {
        return characterImg;
    }

    public ImageView getCherryImg() {
        return cherryImg;
    }

    public AnchorPane getStartgamePane() {
        return startgamePane;
    }

    public boolean isCherryCollision() {
        return cherryCollision;
    }

    public Random getRand() {
        return rand;
    }

    public void flipChar()
    {
        charState=charState==1 ? 0:1;// 1 hai to 0, 0 hai to 1
        Platform.runLater(() -> {
//            double pivotX = st.getStickrootx() + characterImg.getBoundsInParent().getWidth() / 2.0;
//            double pivotY = st.getStickrooty() + st.getStick().getHeight() / 2.0;

            Scale scale = new Scale(1, -1, st.getStickrootx(), st.getStickrooty()+2);
            characterImg.getTransforms().add(scale);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(0), new KeyValue(scale.yProperty(), 1))
            );
            timeline.play();
        });
    }
    public void rotateStickAroundAndMoveChar()//after this moveCharacter
    {
        Platform.runLater(() -> {
            st.setStickRotating(1);
            double pivotX = st.getStickrootx();
            double pivotY = st.getStickrooty();

            Rotate rotation = new Rotate(0, pivotX, pivotY);
            st.getStick().getTransforms().add(rotation);

            Timeline timeline = new Timeline(
                    new KeyFrame(Duration.seconds(1), new KeyValue(rotation.angleProperty(), 90))
            );
            timeline.setOnFinished(event -> {
                try {
                    playSound("C:\\Users\\Dev Utkarsh\\OneDrive\\Desktop\\idea\\AP_PROJ-5th_update\\stickHero\\stickHero\\src\\main\\resources\\com\\example\\stickhero\\thud.mp3",0,1);
                    moveCharacter();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            timeline.play();
        });
    }
    public int isFalling() throws IOException, InterruptedException//-----------------------------------------
    {
        System.out.println("Checking length");
        System.out.println((distBetweenTowers)+8 );
        System.out.println(st.getStick().getHeight());
        System.out.println((distBetweenTowers)+8 +width2);
        double stickLeft = st.getStick().getBoundsInParent().getMinX();
        double stickRight = st.getStick().getBoundsInParent().getMaxX();

        double towerLeft = TOWER2.getBoundsInParent().getMinX();
        double towerRight = TOWER2.getBoundsInParent().getMaxX();
        if ((stickRight < towerLeft || stickRight > towerRight))
        {
            fallchar();
            return 1;
        }
        if(stickRight>DIST_2NDTOWERSTART+rand1+((double) width2 /2)-5 && stickRight<DIST_2NDTOWERSTART+rand1+((double) width2 /2)-5+10)
        {
            showPerfect();
            System.out.println("\n\nper\n\n");
        }
        return 0;
    }
    public void showPerfect() {
        playSound("C:\\Users\\Dev Utkarsh\\OneDrive\\Desktop\\idea\\AP_PROJ-5th_update\\stickHero\\stickHero\\src\\main\\resources\\com\\example\\stickhero\\perfect.mp3",0,1);
        perfectLabel.setText("PERFECT +1");
        scoreLabel.setText((Integer.parseInt(scoreLabel.getText())+2)+"");
        score+=1;
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(2), perfectLabel);
        fadeTransition.setFromValue(1.0);
        fadeTransition.setToValue(0.0);
        fadeTransition.setOnFinished(event -> {
            perfectLabel.setText("");
            perfectLabel.setOpacity(1.0);
        });
        fadeTransition.play();
    }
    public double getDistToMove()
    {
        if (st.getStick().getHeight()<(distBetweenTowers)+10 || st.getStick().getHeight()>((distBetweenTowers)+8 +width2))
        {
            return st.getStick().getHeight();
        }
        return distBetweenTowers+width2;
    }
    public void playSound(String soundFilePath, double t1, double t2) {
        Media sound = new Media(new File(soundFilePath).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(sound);

        mediaPlayer.setStartTime(Duration.seconds(t1));

        mediaPlayer.setStopTime(Duration.seconds(t2));
        mediaPlayer.play();
    }
    public void moveCharacter() throws IOException//after this checkfall and then shiftTowersAndStick//-------------------------
    {
        charMoving=1;
        System.out.println("Character moving front rn");
        Translate translate=new Translate(0,0);
        characterImg.getTransforms().add(translate);
//        playSound("file:C:/Users/Dev Utkarsh/OneDrive/Desktop/idea/stickHero/AP_PROJ-5th_update/stickHero/src/assets/run.mp3",0.0,1.0);
        playSound("C:\\Users\\Dev Utkarsh\\OneDrive\\Desktop\\idea\\AP_PROJ-5th_update\\stickHero\\stickHero\\src\\assets\\run.mp3",0,1);
        AnimationTimer collisionTimer=new AnimationTimer()
        {
            @Override
            public void handle(long l) {
                if(checkCollision(characterImg,cherryImg)==1)
                {
                    cherryCollision=true;
                    cherryLabel.setText(""+(numCherries+1));
                    cherryImg.setVisible(false);
                }
                if(checkCollision(TOWER2,characterImg)==1)
                {
                    fallchar();
//                    System.out.println("LOOOOOSSSSSSEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
//                    Stage stage = (Stage) backgroundImg.getScene().getWindow();
//                    FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gameover.fxml"));
//                    Parent root = null;
//                    try {
//                        root = fxmlLoader.load();
//                    } catch (IOException e) {
//                        throw new RuntimeException(e);
//                    }
//                    Gameover gameoverController = fxmlLoader.getController();
//                    gameoverController.setScore(score);
//                    stage.setTitle("Game Over");
//                    stage.setScene(new Scene(root, 600, 400));
//                    stage.show();
                }
            }
        };

        collisionTimer.start();
        double d=getDistToMove();
        Timeline t=new Timeline(
                new KeyFrame(Duration.seconds(1),new KeyValue(translate.xProperty(),d)));//naya frame kitni der mai and final value kya

        t.setOnFinished(event -> {
//            charMoving=0;    should be after shifting character and towers
            System.out.println("Character moving front done");
            collisionTimer.stop();
            //stick rotation done
            st.setStickRotating(0);
            try {
                if(isFalling()==0)
                {
                    startgamePane.getChildren().remove(st.getStick());
                    shiftTowersAndStick();
                }//else part will be executed in isFalling itself
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            if(cherryCollision)
            {
                numCherries++;
                cherryImg.setVisible(false);
//                cherryLabel.setText(""+numCherries);
                cherryCollision=false;
            }
            startgamePane.getChildren().remove(rsmall);
        });
        t.play();
    }
    public int checkCollision(Node n1,Node n2)
    {
        if (n1.getBoundsInParent().intersects(n2.getBoundsInParent()) && charState==1) {
            System.out.println("Collision!");
            return 1;
        }
        return 0;
    }
    public void shiftTowersAndStick()
    {
        charMoving=1;//although it should be already be one here
        System.out.println("Character moving back rn");
        Translate tt1=new Translate(0,0);
        TOWER2.getTransforms().add(tt1);
        Timeline t1=new Timeline(
                new KeyFrame(Duration.seconds(1),new KeyValue(tt1.xProperty(),-(distBetweenTowers+width1))));

        Translate tt2=new Translate(0,0);
        st.getStick().getTransforms().add(tt2);
        Timeline t2=new Timeline(
                new KeyFrame(Duration.seconds(1),new KeyValue(tt2.xProperty(),-(distBetweenTowers+width1))));
//
        Translate tt3=new Translate(0,0);
        TOWER1.getTransforms().add(tt3);
        Timeline t3=new Timeline(
                new KeyFrame(Duration.seconds(1),new KeyValue(tt3.xProperty(),-(distBetweenTowers+width1))));

        Translate tt4=new Translate(0,0);
        characterImg.getTransforms().add(tt4);
        Timeline t4=new Timeline(
                new KeyFrame(Duration.seconds(1),new KeyValue(tt4.xProperty(),-(distBetweenTowers+width1))));
        t4.setOnFinished(event -> {
            charMoving=0;
            System.out.println("Character moving back done");
            System.out.println("Tower shifted back:  "+distBetweenTowers+width1);
            startgamePane.getChildren().remove(TOWER1);
            startgamePane.getChildren().remove(cherryImg);
            startgamePane.getChildren().remove(st.getStick());
            animationFinished();
        });
        t1.play();
        t2.play();
        t3.play();
        t4.play();
    }
    public void fallchar()
    {
        playSound("C:\\Users\\Dev Utkarsh\\OneDrive\\Desktop\\idea\\AP_PROJ-5th_update\\stickHero\\stickHero\\src\\main\\resources\\com\\example\\stickhero\\charfall.mp3",0,1.5);
        System.out.println("\n\n\nfalling\n\n");
        Translate trans=new Translate(0,0);
        characterImg.getTransforms().add(trans);
        int dir=500;
        if(charState==1)
        {
            dir*=-1;
        }
        Timeline t=new Timeline(
                new KeyFrame(Duration.seconds(1),new KeyValue(trans.yProperty(),dir)));
        t.play();
        t.setOnFinished(e->{
            System.out.println("fallen\n");
            System.out.println("LOOOOOSSSSSSEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEE");
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("gameover.fxml"));
            Parent root = null;
            try {
                root = fxmlLoader.load();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            Gameover gameoverController = fxmlLoader.getController();
            gameoverController.setScore(score);
            gameoverController.setcherries(numCherries);
            if(score>bestscore)
            {
                bestscore=score;
                gameoverController.setBestScoreLabel(score);
            }
            else {
                gameoverController.setBestScoreLabel(bestscore);
            }
            Stage stage = (Stage) backgroundImg.getScene().getWindow();
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        });
    }
    public void setbestscore(int best) {
        System.out.println("best her eis"+best);
        bestscore=best;
    }
}
