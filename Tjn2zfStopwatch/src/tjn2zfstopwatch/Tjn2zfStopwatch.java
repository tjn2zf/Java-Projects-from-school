/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tjn2zfstopwatch;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author thomasnewman
 */
public class Tjn2zfStopwatch extends Application {
    
    private String appName = "StopWatch";
    
    @Override
    public void start(Stage primaryStage) {
        Stopwatch stopwatch = new Stopwatch();
        
        Scene scene = new Scene(stopwatch.getRootContainer(),
                                stopwatch.getWidth(),
                                stopwatch.getHeight());
        
        primaryStage.setTitle(appName);
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
