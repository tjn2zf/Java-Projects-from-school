/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjn2zfcpumonitorfxml;

import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

/**
 *
 * @author thomasnewman
 */
public class FXMLDocumentController implements Initializable {
    
    @FXML
    private GridPane root;
    @FXML
    private StackPane clock;
    @FXML
    private ImageView gauge;
    @FXML
    private ImageView hand;
    @FXML
    private Button start;
    @FXML
    private Button record;
    @FXML
    private HBox controls;
    @FXML
    private HBox record1;
    @FXML
    private HBox record2;
    @FXML
    private HBox record3;
    @FXML
    private HBox digital;
    @FXML
    private Label digitalTime;
    @FXML
    private Label time1;
    @FXML
    private Label time2;
    @FXML
    private Label time3;
    
    private double cpu;
    private Timeline timeline;
    private KeyFrame keyframe;
    
    private double dialAngle = 2.6;
    public int timeCount = 0;

    
    public boolean isRunning = false;
    public boolean reset = false;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public FXMLDocumentController(){
        setupTimer();
    }
    
    public void setupTimer(){
        boolean isRunning = false;
//        hand.setRotate(200.0);
        if(timeline != null) {
            if(timeline.getStatus() == Animation.Status.RUNNING) {
                isRunning = true;
                timeline.stop();
            }
        }
        
        keyframe = new KeyFrame(Duration.millis(100), (ActionEvent) -> {
            cpu = getCPUUsage();
            update(cpu);
            
        });
        
        timeline = new Timeline(keyframe);
        timeline.setCycleCount(Animation.INDEFINITE);
        
//        hand.setRotate(5.0);
    //timeline.setCycleCount(100);
    //timeline.play();
    }
    @FXML
    public void startBttn(ActionEvent event){
        //timeline.play();
    if(isRunning){
            stop();

        }
            else{
            start();
            }
    }
    @FXML
    public void recordBttn(ActionEvent event){
        if(reset){
                reset();
            }
            else{
                record();
            }
    }
    
    public void stop(){
        timeline.stop();
        isRunning=false;
        start.setText("Start");
        record.setText("Reset");
        reset=true;
    }
    
    public void start(){
        timeline.play();
        isRunning=true;
        start.setText("Stop");
        record.setText("Record");
        reset = false;
    }
    
    public void record(){
        timeCount++;
        if(timeCount % 3 == 1){
            time1.setText("Record " + timeCount + " " + digitalTime.getText());
        }
        if(timeCount % 3 == 2){
            time2.setText("Record " + timeCount + " " + digitalTime.getText());
        }
        if(timeCount % 3 == 0){
            time3.setText("Record " + timeCount + " " + digitalTime.getText());
        }
    }
    
    public void reset(){
        timeline.pause();
        hand.setRotate(-135.0);
        digitalTime.setText("00.00%");
        time1.setText("--.--%");
       time2.setText("--.--%");
       time3.setText("--.--%");
       timeCount = 0;
    }
    
    private void update(double cpu){
        cpu = cpu * 100;
        double rotation = (cpu * dialAngle) - 135.0;
        //System.out.println("CPU: " + cpu);
        hand.setRotate(rotation);
        digitalTime.setText(String.format("%.2f", cpu) + "%");
    }
    
    private static double getCPUUsage() {
        OperatingSystemMXBean operatingSystemMXBean = ManagementFactory.getOperatingSystemMXBean();
        double value = 0;
        for (Method method : operatingSystemMXBean.getClass().getDeclaredMethods()) {
            method.setAccessible(true);
            if (method.getName().startsWith("getSystemCpuLoad")
                    && Modifier.isPublic(method.getModifiers())) {
                try {
                    value = (double) method.invoke(operatingSystemMXBean);
                } catch (Exception e) {
                    value = 0;
                }
                //System.out.println(value);
                return value;
            }
        }
        //System.out.println(value);
        return value;
    }
}
