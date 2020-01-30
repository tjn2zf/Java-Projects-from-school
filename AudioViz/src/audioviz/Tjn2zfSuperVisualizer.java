/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audioviz;

import static java.lang.Integer.min;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author thomasnewman
 */
public class Tjn2zfSuperVisualizer implements Visualizer {
    
    private final String name = "Tjn2zf Super Visualizer";
    
    private Integer numBands;
    private AnchorPane vizPane;
    
    private final Double bandHeightPercentage = 1.3;
    private final Double minRectangle = 10.0;  // 10.0
    
    private Double width = 0.0;
    private Double height = 0.0;
    
    private Double bandWidth = 0.0;
    private Double bandHeight = 0.0;
    private Double halfBandHeight = 0.0;
    
    private final Double startHue = 260.0;
    
    private Double magnitudeOffset = 70.0;
    
    private String vizPaneInitialStyle = "";

    private Rectangle[] group1;
    private Rectangle[] group2;
    
    public Tjn2zfSuperVisualizer() {
    }
    
        @Override
    public String getName() {
        return name;
    }

    @Override
    public void start(Integer numBands, AnchorPane vizPane) {
        end();
        
        this.numBands = numBands;
        this.vizPane = vizPane;
        
        height = vizPane.getHeight();
        width = vizPane.getWidth();
        
        bandWidth = width / numBands;
        bandHeight = height * bandHeightPercentage;
        halfBandHeight = bandHeight / 2;
        group1 = new Rectangle[numBands];
        group2 = new Rectangle[numBands];
        
        for (int i = 0; i < numBands; i++) {
            Rectangle rec1 = new Rectangle();
            Rectangle rec2 = new Rectangle();
            
            rec1.setX(bandWidth * i);
            rec1.setY(height/2);
            rec1.setWidth(bandWidth/2);
            rec1.setHeight(minRectangle);
            rec1.setFill(Color.hsb(startHue, 1.0, 1.0, 1.0));
            vizPane.getChildren().add(rec1);
            group1[i] = rec1;
            
            rec2.setX(bandWidth * i);
            rec2.setY(height/2);
            rec2.setWidth(bandWidth/2);
            rec2.setHeight(minRectangle);
            rec2.setFill(Color.hsb(startHue, 1.0, 1.0, 1.0));
            vizPane.getChildren().add(rec2);
            group2[i] = rec2;
        }
    }

    @Override
    public void end() {
        if (group1 != null) {
            for (Rectangle rectangle : group1) {
                vizPane.getChildren().remove(rectangle);
            }
            group1 = null;
        }
        if (group2 != null) {
            for (Rectangle rectangle : group2) {
                vizPane.getChildren().remove(rectangle);
            }
            group2 = null;
        }
    }

    @Override
    public void update(double timestamp, double duration, float[] magnitudes, float[] phases) {
        if(group1 == null){
            return;
        }
        
        if(group2 == null){
            return;
        }
        
        Integer num = min(group1.length, magnitudes.length);
        
        for(int i = 0; i < num; i++){
            group1[i].setHeight(((60.0 + magnitudes[i])/60.0) * halfBandHeight + minRectangle);
            group1[i].setFill(Color.hsb(startHue - (magnitudes[i] * -12.0), 1.0, 1.0, 1.0));
            
            group2[i].setHeight(((60.0 + magnitudes[i])/60.0) * halfBandHeight + minRectangle);
            group2[i].setTranslateY(- group1[i].getHeight());
            group2[i].setFill(Color.hsb(startHue - (magnitudes[i] * -12.0), 1.0, 1.0, 1.0));
            
            if(i%2==0){
                group1[i].setFill(Color.grayRgb(0));
                group2[i].setFill(Color.grayRgb(0));
            }
         
        }
        
        Double hue = ((60.0 + magnitudes[0])/30.0) *360;
        
        vizPane.setStyle("-fx-background-color: hsb(" + hue + ", 20%, 100%)");
    }

    
    
}
