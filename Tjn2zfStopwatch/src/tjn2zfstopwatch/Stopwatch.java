/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjn2zfstopwatch;

import javafx.animation.Animation;
import javafx.animation.Animation.Status;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author thomasnewman
 */
public class Stopwatch {
    
    
    
    public Timeline timeline;
    public Timeline digitalTimeline;
    public KeyFrame keyFrame;
    public KeyFrame digitalKeyFrame;
    ImageView dialImageView;
    ImageView handImageView;
    
    public int centisecondsElapsed = 1;
    public double tickTimeInSeconds = 0.01; // how to change the resolution
    public double angleDeltaPerSeconds = 6.0;
    public boolean isRunning;
    public boolean reset=false;
    
    private double milliSecondsElapsed = 0.0;
    private int secondsElapsed = 1;
    private int incrementCentiseconds = 0;
    private int incrementSeconds= 0;
    private int minutesElapsed = 0;
    private int hoursElapsed = 0;
    
    private String strHoursElapsed = String.format("%02d", hoursElapsed);
    private String strCentisecondsElapsed = String.format("%02d", centisecondsElapsed);
    private String strSecondsElapsed =  String.format("%02d", secondsElapsed);
    private String strMinutesElapsed =  String.format("%02d", minutesElapsed);
    //
    
    public Label digitalDisplay;
    public Label time1;
    public Label time2;
    public Label time3;
    
    public Button start;
    public Button record;
    
    public String buttonText1 = "Start";
    public String buttonText2 = "Record";
    
    private GridPane rootContainer;
    private StackPane clock;
    private Image dialImage;
    private Image handImage;
    
    public int timeCount = 0;
    

    
    public Stopwatch(){
        setupUI();
        setupTimer();
    }
    
    public void setupUI(){
        rootContainer = new GridPane();
        clock = new StackPane();
        dialImageView = new ImageView();
        handImageView = new ImageView();
       
        dialImage = new Image(getClass().getResourceAsStream("clockface.png"));
        handImage = new Image(getClass().getResourceAsStream("hand.png"));
       
        dialImageView.setImage(dialImage);
        handImageView.setImage(handImage);
    
        clock.getChildren().addAll(dialImageView, handImageView);
        
        HBox controls = new HBox();
        start = new Button();
        record = new Button();
        start.setText(buttonText1);
        record.setText(buttonText2);
        
        //Button start = new Button("Start");
        //Button record = new Button("Record");
    
       controls.getChildren().addAll(start, record);
       controls.setAlignment(Pos.CENTER);
       controls.setSpacing(20.0);
       controls.setPadding(new Insets(15,12,15,12));
       
       time1 = new Label();
       time2 = new Label();
       time3 = new Label();
       HBox records = new HBox();
       records.getChildren().addAll(time1);
       records.setAlignment(Pos.CENTER);
       records.setPadding(new Insets(15,12,15,12));
       
       HBox records2 = new HBox();
       records2.getChildren().addAll(time2);
       records2.setAlignment(Pos.CENTER);
       records2.setPadding(new Insets(15,12,15,12));
       
       HBox records3 = new HBox();
       records3.getChildren().addAll(time3);
       records3.setAlignment(Pos.CENTER);
       records3.setPadding(new Insets(15,12,15,12));
       time1.setText("Lap 1: 00:00.00 ");
       time2.setText("Lap 2: 00:00.00 ");
       time3.setText("Lap 3: 00:00.00 ");

       digitalDisplay = new Label();
       HBox digitalHBox = new HBox();
       digitalHBox.getChildren().addAll(digitalDisplay);
       digitalHBox.setAlignment(Pos.CENTER);
       digitalHBox.setPadding(new Insets(15,12,15,12));
       digitalDisplay.setText("00:00.00");

        start.setOnAction((ActionEvent event) -> {
            if(isRunning){
            stop();

        }
            else{
            start();
            }
        });
        
        record.setOnAction((ActionEvent event) -> {
            if(reset){
                reset();
            }
            else{
                record();
            }
        });
        
        rootContainer.setAlignment(Pos.CENTER);
        
        rootContainer.setGridLinesVisible(true);
        
        rootContainer.add(controls, 0, 0);
        rootContainer.add(clock, 0, 1);
        rootContainer.add(digitalHBox, 0, 2);
        rootContainer.add(records, 0, 3);
        rootContainer.add(records2, 0, 4);
        rootContainer.add(records3, 0, 5);
        
        controls.toFront();
//        
    }
    
    public void setupTimer() {
        boolean running = false;
        if(timeline != null) {
            if(timeline.getStatus() == Status.RUNNING) {
                running = true;
                timeline.stop();
                digitalTimeline.stop();
            }
        }
        keyFrame = new KeyFrame(Duration.millis(tickTimeInSeconds * 1000), (ActionEvent event) -> {
            update();
        });
        
//        digitalKeyFrame = new KeyFrame(Duration.seconds(secondsElapsed), (ActionEvent event) -> {
//            updateDigital();
//        });
        digitalKeyFrame = new KeyFrame(Duration.seconds(secondsElapsed *.01666), (ActionEvent event) -> {
            updateDigital();
        });
        
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
        
        digitalTimeline = new Timeline(digitalKeyFrame);
        digitalTimeline.setCycleCount(Animation.INDEFINITE);
    }
    
    private void update() {
        milliSecondsElapsed += tickTimeInSeconds;
       
        double rotation = milliSecondsElapsed * angleDeltaPerSeconds;
        handImageView.setRotate(rotation);
    }
    
   private void updateDigital() {
        incrementSeconds+=secondsElapsed;
        if(incrementSeconds > 59){
            minutesElapsed += 1;
            incrementSeconds = 0;
        }
        if(minutesElapsed > 59) {
            hoursElapsed += 1;
            minutesElapsed = 0;
        }
        
        strHoursElapsed = String.format("%02d", hoursElapsed);
        strSecondsElapsed = String.format("%02d", incrementSeconds);
        strMinutesElapsed = String.format("%02d", minutesElapsed);
//        digitalDisplay.setText(strMinutesElapsed + ":" + strSecondsElapsed + "." + centisecondsElapsed);
        digitalDisplay.setText(strHoursElapsed + ":" + strMinutesElapsed + "." + strSecondsElapsed);
        
    }
    
    public Parent getRootContainer() {
        return rootContainer;
    }
    
    public Double getWidth() {
        if (dialImage != null) return rootContainer.getMaxHeight();
        else return 0.0;
    }
    
    public Double getHeight() {
        if (dialImage != null) return rootContainer.getMaxWidth();
        else return 0.0;
    }
    
    public void start() {
        timeline.play();
        isRunning=true;
        digitalTimeline.play();
        start.setText("Stop");
        record.setText("Record");
        reset=false;
    }
    
    public void stop() {
        timeline.stop();
        isRunning=false;
        digitalTimeline.stop();
        start.setText("Start");
        record.setText("Reset");
        reset=true;
    }
    
    public void reset() {
        timeline.pause();
        digitalTimeline.pause();
        milliSecondsElapsed = 0;
        handImageView.setRotate(0.0);
        secondsElapsed = 1;
        incrementSeconds = 0;
        minutesElapsed = 0;
        digitalDisplay.setText("00:00.00");
        time1.setText("Lap 1: 00:00.00 ");
       time2.setText("Lap 2: 00:00.00 ");
       time3.setText("Lap 3: 00:00.00 ");
       timeCount = 0;
    }
    public void record() {
        timeCount++;
        if(timeCount % 3 == 1){
            time1.setText("Lap " + timeCount + " " + digitalDisplay.getText());
        }
        if(timeCount % 3 == 2){
            time2.setText("Lap " + timeCount + " " + digitalDisplay.getText());
        }
        if(timeCount % 3 == 0){
            time3.setText("Lap " + timeCount + " " + digitalDisplay.getText());
        }
    }

    
    public boolean isRunning(){
        if(timeline != null){
            if(timeline.getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        return false;
    }
    
    public void setTickTimeInSeconds(Double tickTimeInSeconds) {
        this.tickTimeInSeconds = tickTimeInSeconds;
        setupTimer();
    }

}
