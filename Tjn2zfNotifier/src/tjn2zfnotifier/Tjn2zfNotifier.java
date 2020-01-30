/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjn2zfnotifier;

import java.text.SimpleDateFormat;
import java.util.Date;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;


/**
 *
 * @author thomasnewman
 */
public class Tjn2zfNotifier extends Application {
    
    public static String invokeMe(String pawprint){
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy HH:mm");
        //String string = ("my pawprint is " + pawprint + " and today's date is " + ft.format(dNow));
        String string = ("You have been \n" +
"notified by " + pawprint + " on " + ft.format(dNow));
        return string;
    }
    
    @Override
    public void start(Stage primaryStage) {
        
        
        Button notify = new Button("Notify");
        Button clear = new Button("Clear");
        Button print = new Button("Print");
        Button alert = new Button("Alert");
        
        notify.setMaxWidth(Double.MAX_VALUE);
        clear.setMaxWidth(Double.MAX_VALUE);
        print.setMaxWidth(Double.MAX_VALUE);
        alert.setMaxWidth(Double.MAX_VALUE);
                
        VBox vb = new VBox();
        vb.setPadding(new Insets(0, 0, 10, 0));
        vb.setSpacing(10);
        vb.getChildren().addAll(notify, clear, print, alert);
        
        TextField textfield = new TextField();
        textfield.setPrefWidth(350);
        
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);
        grid.add(textfield, 0, 0);
        
        //actiony stuff
        notify.setOnAction((ActionEvent event) -> {
            String pawprint = "tjn2zf";
            String string = invokeMe(pawprint);
            textfield.setText(string);

//            Date dNow = new Date();
//            SimpleDateFormat ft = new SimpleDateFormat ("MM/dd/yyyy HH:mm");
//            textfield.setText(", my pawprint is " + pawprint + " and today's date is " + ft.format(dNow));

        });
        
        clear.setOnAction((ActionEvent event) -> {
            textfield.clear();
        });
        
        print.setOnAction((ActionEvent event) -> {
            String text = textfield.getText();
            System.out.println(text);
        });
        
        alert.setOnAction((ActionEvent event) ->{
            String text = textfield.getText();
            Alert a = new Alert(AlertType.INFORMATION);
            a.setTitle("Alert");
            a.setContentText(text);
            a.showAndWait();
        });
        
        //
        //grid.add(textfield, 0, 0);
        grid.add(vb, 0, 1);
        
        Scene scene = new Scene(grid, 420, 250);
        
        primaryStage.setTitle("Notifier");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
