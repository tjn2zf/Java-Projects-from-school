/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjn2zfmvcstopwatchfxml;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.util.Duration;

/**
 *
 * @author thomasnewman
 */
public class Digital {
    private Timeline timeline;
    private KeyFrame keyFrame;
    
    double rotation =0.0;
    
    private double tickTimeInSeconds =.01; //Change resolution
    private int recordNum;
    private double cpu;
    private double angle = 6.0; 
    
    Integer ms = 0, seconds = 0, minutes = 0;
    
    public int centisecondsElapsed = 1;
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
    
    public String digiAsString; 
    
    private PropertyChangeSupport propertyChangeSupport;
    
    public Digital(){
        propertyChangeSupport = new PropertyChangeSupport(this);
        tickTimeInSeconds = 0.01;
        recordNum = 1;
        cpu = 0;
    }
    
     public void addPropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        propertyChangeSupport.removePropertyChangeListener(listener);
    }

    protected void firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        propertyChangeSupport.firePropertyChange(propertyName, oldValue, newValue);
    }
    
    public boolean isRunnning(){
        if(timeline != null){
            if(timeline.getStatus() == Animation.Status.RUNNING){
                return true;
            }
        }
        return false;
    }
   
    public void setupMonitor() {        
        keyFrame = new KeyFrame(Duration.seconds(secondsElapsed *.01666), (ActionEvent event) -> {
                updateMonitor(); 
        });
        timeline = new Timeline(keyFrame);
        timeline.setCycleCount(Animation.INDEFINITE);
    }
    
    private void updateMonitor() {
        
        //updateAnalog();
        updateDigital();
    }
    
    public double getDifference(){
        double value =Double.parseDouble(digiAsString);
        
        
        return value;
    }
    
    public void updateDigital(){
        String oldString = digiAsString; 
        digiAsString = getDigiAsString();
//        System.out.println("Digital: " + cpuAsString);
        firePropertyChange("Digital", oldString, digiAsString);
    }
    
    
    public void start() {
        timeline.play();
    }
    
    public void stop() {
        timeline.stop();
    }
    
    public void reset() {
        timeline.stop();
        milliSecondsElapsed = 0;
        secondsElapsed = 1;
        incrementSeconds = 0;
        minutesElapsed = 0; 
    }
        
    public String getDigiAsString() {
        String digiString;
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
        digiString = (strHoursElapsed + ":" + strMinutesElapsed + "." + strSecondsElapsed);
        return digiString;
    }
}
