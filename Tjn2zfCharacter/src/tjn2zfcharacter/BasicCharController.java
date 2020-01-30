/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjn2zfcharacter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Professor Wergeles
 */
public class BasicCharController extends Switchable implements Initializable, Character {
    
    @FXML
    public TextField textField;
    
    ArrayList<String> scenes;
    
    private Stage stage;
    
    @FXML TextField name;
    
    @FXML ChoiceBox<String> race;
    
    @FXML TextArea output;
    
    @FXML Button add;
    
    @FXML Button clear;
    
    String string1;
    
    String string2;
    
    String text;

//    @FXML ObservableArrayList list; 
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scenes = new ArrayList();
        scenes.add("BasicChar");
        scenes.add("AdvancedChar");
        scenes.add("About");

        ObservableList<String> availableChoices = FXCollections.observableArrayList("Human", "Elf", "Dwarf", "Gnome",
                "Halfling", "Half-Elf", "Dragonborn", "Orc", "Tiefling"); 
        race.setItems(availableChoices);
        
        //clear.setVisible(false);
        
        add.setVisible(false);
        
        checkAdd();
    } 
    
    
    @FXML
    private void handleGoToB(ActionEvent event) {
        switchTo(scenes.get(1));
    }
    
    @FXML
    private void handleGoToC(ActionEvent event) {
        switchTo(scenes.get(2)); 
    }

    @Override
    public String getName() {
        String charName = name.getText();
        clear.setVisible(false);
        return charName;
    }

    @Override
    public String getRace() {
        String charRace = race.getSelectionModel().getSelectedItem();
       return charRace;
    }
    
    public String add(){
        string1 = getRace();
        string2 = getName();
        
        output.appendText("You are " + string2 + ", a " + string1 + ".\n");
        string1= output.getText();
        return string1;
    }
    
    
    @FXML
    public void checkAdd(){
//        if(name.getText()!= null){
//            clear.setVisible(true);
//        }
        if(name.getText()!= null && race.getSelectionModel().getSelectedItem()!= null){
            add.setVisible(true);
            //clear.setVisible(true);
        }
    }
    
    public void clear(){
        output.clear();
        name.clear();
        //race.clear();
    }
    //@Override
    @FXML
    public void handleOpen(ActionEvent event){
        FileChooser fileChooser = new FileChooser(); 
        
       // Stage stage = (Stage) root.getScene().getWindow(); 
        
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files", "*.txt", "*.html", "*.c"));
        
        File file = fileChooser.showOpenDialog(stage);
        
        if(file != null){
            
            BufferedReader bufferedReader = null; 
            
            String document = ""; 
            String line = ""; 
            
            try {
                bufferedReader = new BufferedReader(new FileReader(file));
                
                while( (line = bufferedReader.readLine()) != null){
                    document += line + "\n";
                }
                
                output.setText(document);
                
            } catch (FileNotFoundException ex) {
                displayExceptionAlert(ex);
            } catch (IOException ex) {
                displayExceptionAlert(ex);
            } catch (Exception ex){
                displayExceptionAlert(ex);
            } finally {
                if(bufferedReader != null){
                    
                    try {
                        bufferedReader.close();
                    } catch (IOException ex) {
                        displayExceptionAlert(ex);
                    }
                }
            }
            
            
   
        }
    }
    
    @FXML
    @Override
    public void handleSave(ActionEvent event){
        FileChooser fileChooser = new FileChooser(); 
       // Stage stage = (Stage) root.getScene().getWindow(); 
        
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Text files", "*.txt", "*.html", "*.c"));
        
        File file = fileChooser.showSaveDialog(stage); 
        
        FileWriter writer = null; 
        
        if(file != null){
            
            try {
                writer = new FileWriter(file);
                writer.write(output.getText());
                
            } catch (IOException ex) {
                displayExceptionAlert(ex);
            } catch (Exception ex) {
                displayExceptionAlert(ex);
            } finally {
                if(writer != null){
                    try {
                        writer.close();
                    } catch (IOException ex) {
                        displayExceptionAlert(ex);
                    }
                }
            }
            
        }
    }
    
    public void displayExceptionAlert(Exception ex){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Exception Dialog");
        alert.setHeaderText("Exception!");
        alert.setContentText(ex.getMessage());
        
        // Create expandable Exception.
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String exceptionText = sw.toString();

        Label label = new Label("The exception stacktrace was:");

        TextArea textArea = new TextArea(exceptionText);
        textArea.setEditable(false);
        textArea.setWrapText(true);

        textArea.setMaxWidth(Double.MAX_VALUE);
        textArea.setMaxHeight(Double.MAX_VALUE);
        GridPane.setVgrow(textArea, Priority.ALWAYS);
        GridPane.setHgrow(textArea, Priority.ALWAYS);

        GridPane expContent = new GridPane();
        expContent.setMaxWidth(Double.MAX_VALUE);
        expContent.add(label, 0, 0);
        expContent.add(textArea, 0, 1);

        // Set expandable Exception into the dialog pane.
        alert.getDialogPane().setExpandableContent(expContent);

        alert.showAndWait(); 
    }

   
}
