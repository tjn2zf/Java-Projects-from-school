/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjn2zfcharacter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import static tjn2zfcharacter.Switchable.getControllerByName;
import static tjn2zfcharacter.Switchable.switchTo;

/**
 * FXML Controller class
 *
 * @author thomasnewman
 */
public class AboutController extends Switchable implements Initializable {

  @FXML
    private TextField cText; 
    
     ArrayList<String> scenes;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scenes = new ArrayList();
        scenes.add("BasicChar");
        scenes.add("AdvancedChar");
        scenes.add("About");
        
    }
    
    @FXML
    private void handleGoToA(ActionEvent event) {
        switchTo(scenes.get(0));
        
    }
    
    @FXML
    private void handleGoToB(ActionEvent event) {
        switchTo(scenes.get(1));
    }  
    
}
