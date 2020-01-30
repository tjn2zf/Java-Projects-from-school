/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjn2zfcharacter;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static tjn2zfcharacter.Switchable.getControllerByName;

/**
 * FXML Controller class
 *
 * @author Professor Wergeles
 */
public class AdvancedCharController extends BasicCharController implements Initializable, Character {

    @FXML
    public TextField textField; 
    
    ArrayList<String> scenes;
    
    private Stage stage;
    
    @FXML TextField name;
    
    @FXML ChoiceBox<String> race;
    
    @FXML ChoiceBox<String> charClass;
    
    @FXML ChoiceBox<String> background;
    
    @FXML ChoiceBox<String> skill1;
    
    @FXML ChoiceBox<String> skill2;
    
    @FXML TextArea output;
    
    @FXML Button add;
    
    @FXML Button clear;
    
    String string1;
    
    String string2;
    
    String string3;
    
    String string4;
    
    String string5;
    
    String string6;
    
    String text;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        scenes = new ArrayList();
        scenes.add("BasicChar");
        scenes.add("AdvancedChar");
        scenes.add("About");
        
        ObservableList<String> raceChoices = FXCollections.observableArrayList("Human", "Elf", "Dwarf", "Gnome",
                "Halfling", "Half-Elf", "Dragonborn", "Orc", "Tiefling"); 
        race.setItems(raceChoices);
        
        ObservableList<String> backgroundChoices = FXCollections.observableArrayList("Acolyte", "Charlatan", "Folk Hero",
                "Entertainer", "Soldier", "Outlander", "Sage", "Noble", "Guild Artisan"); 
        background.setItems(backgroundChoices);
        
        ObservableList<String> classChoices = FXCollections.observableArrayList("Barabarian", "Bard", "Cleric",
                "Druid", "Fighter", "Monk", "Paladin", "Ranger", "Rogue", "Sorceror", "Warlock", "Wizard"); 
        charClass.setItems(classChoices);
        
        ObservableList<String> skill1Choices = FXCollections.observableArrayList("Athletics", "Acrobatics", "Animal Handling",
                "Arcana", "Deception", "History", "Insight", "Intimidation", "Investigation", "Medicine", "Nature",
      "Perception", "Performance", "Persuason", "Religion", "Sleight of Hand", "Stealth", "Survival" ); 
        skill1.setItems(skill1Choices);
        
        ObservableList<String> skill2Choices = FXCollections.observableArrayList("Athletics", "Acrobatics", "Animal Handling",
                "Arcana", "Deception", "History", "Insight", "Intimidation", "Investigation", "Medicine", "Nature",
      "Perception", "Performance", "Persuason", "Religion", "Sleight of Hand", "Stealth", "Survival" ); 
        skill2.setItems(skill2Choices);
        
        add.setVisible(false);
    }
    @FXML
    public void handleSave(ActionEvent event){
        super.handleSave(event);
    }
    @FXML
    public void handleOpen(ActionEvent event){
        super.handleOpen(event);
    }
    
    public String getBackground(){
        String charBackground = background.getSelectionModel().getSelectedItem();
       return charBackground;
    }
    
    public String getCharClass(){
        String characterClass = charClass.getSelectionModel().getSelectedItem();
       return characterClass;
    }
    
    public String getSkill1(){
        String charSkill1 = skill1.getSelectionModel().getSelectedItem();
       return charSkill1;
    }
    
    public String getSkill2(){
        String charSkill2 = skill2.getSelectionModel().getSelectedItem();
       return charSkill2;
    }
    @FXML
    public String add(){
        string1 = getRace();
        string2 = getName();
        string3 = getBackground();
        string4 = getCharClass();
        string5 = getSkill1();
        string6 = getSkill2();
        output.appendText("You are " + string2 + ", a " + string1 + " " + string4 + ". You were once a " + string3 
                + " and you're talented in " + string5 + " and " + string6 +".\n");
        if(string5.equals(string6)){
            output.appendText("WOW!! You must be the world's greatest at " + string5 + "!\n");
        }
        string1= output.getText();
        return string1;
    }
    
    public String getName(){
        String charName = super.getName();
        return charName;
    }
    
    public String getRace() {
       String charRace = super.getRace();
       return charRace;
    }
    
    public void clear(){
        super.clear();
    }
    
    public void displayExceptionError(Exception ex){
        super.displayExceptionAlert(ex);
    }
    
    @FXML
    private void handleGoToA(ActionEvent event) {
        switchTo(scenes.get(0));
    }
    
    @FXML
    private void handleGoToC(ActionEvent event) {   
        switchTo(scenes.get(2));
    }
    
    public void checkAdd(){
//        if(name.getText()!= null){
//            clear.setVisible(true);
//        }
        if(name.getText()!= null && race.getSelectionModel().getSelectedItem()!= null && charClass.getSelectionModel().getSelectedItem()!= null
                && background.getSelectionModel().getSelectedItem()!= null && skill1.getSelectionModel().getSelectedItem()!= null && 
                skill2.getSelectionModel().getSelectedItem()!= null){
            add.setVisible(true);
            //clear.setVisible(true);
        }
    }
    
}

