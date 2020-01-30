/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjn2zfmvcstopwatchfxml;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

/**
 *
 * @author thomasnewman
 */
public class FXMLDocumentController implements Initializable, PropertyChangeListener {
    
    @FXML
    private Text digitalLabel;
    @FXML
    private Label record1;
    @FXML
    private Label record2;
    @FXML
    private Label record3;
    @FXML
    private ImageView hand;
    @FXML
    private Button startStopButton;
    @FXML
    private Button recordResetButton;

//    Model model;
    Analog analog;
    Digital digital;
    public int timeCount =0;
     
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        model=new Model();
        analog = new Analog();
        digital = new Digital();
        
        analog.setupMonitor(); 
        analog.addPropertyChangeListener(this);
        
        digital.setupMonitor(); 
        digital.addPropertyChangeListener(this);
        
//        model.setupMonitor(); 
//        model.addPropertyChangeListener(this);
        hand.setRotate(0);
        digitalLabel.setText("00:00.00");
    }
    

    @FXML
    public void startStopMonitor(ActionEvent event) {
        if ( !(analog.isRunnning())) {
            analog.start();
            digital.start();
            startStopButton.setText("Stop");
            recordResetButton.setText("Record");
        } else {
            analog.stop();
            digital.stop();
            startStopButton.setText("Start");
            recordResetButton.setText("Reset");
        }
    }

    @FXML
    public void recordResetMonitor(ActionEvent event) {
        if (!(analog.isRunnning())) {
            hand.setRotate(0);
            digitalLabel.setText("00:00.00");
            record1.setText("--:--.-- ");
            record2.setText("--:--.-- ");
            record3.setText("--:--.-- ");
            recordResetButton.setText("Record");
            analog.reset();
            digital.reset();
            timeCount=0;
        } else {
            timeCount++;
        if(timeCount  == 1){
            record1.setText("Lap " + timeCount + " " + digitalLabel.getText());
        }
        else if(timeCount % 3 == 1){
            record1.setText("Lap " + timeCount + " " + digitalLabel.getText(/*digital.getDifference().toString()*/));
        }
        /*
        model.getDifference();
        */
        else if(timeCount % 3 == 2){
            record2.setText("Lap " + timeCount + " " + digitalLabel.getText());
        }
        else if(timeCount % 3 == 0){
            record3.setText("Lap " + timeCount + " " + digitalLabel.getText());
        }  
            //model.incrementRecordNumber();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println(evt.getPropertyName() + ": " + evt.getNewValue());
        if(evt.getPropertyName().equals("Analog")){
            hand.setRotate((double) evt.getNewValue());
        }
        else if (evt.getPropertyName().equals("Digital")){
            digitalLabel.setText(evt.getNewValue().toString());
        }
        else{
            System.out.println("Error, unknown property name");
        }
//        hand.setRotate((double) evt.getNewValue());
//        cpuLabel.setText(evt.getNewValue().toString());
    }
    
}
