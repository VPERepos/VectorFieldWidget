package com.personal.LearningJavaFX;

import java.util.ArrayList;
import java.util.List;

import javafx.application.*;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.*;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * JavaFX App
 */
public class App extends Application {
    
    @Override
    public void start(Stage stage) {
        Pane root = new Pane();
        
        root.setPrefSize(600, 375);
        VectorfieldWidget VectorField = new VectorfieldWidget(root);
        Scene scene = new Scene(root, 600, 375,true,SceneAntialiasing.BALANCED);
        
        double Xmin=0.0;
        double Xmax=4056.0;
        double Ymin=0.0;
        double Ymax=3040.0;
        double dXmin=-0.01;
        double dXmax=0.01;
        double dYmin=-0.01;
        double dYmax=0.01;
        int NumOfPoints=1000;
        
        VectorField.SetScaleFactor(10000.0);
        VectorField.SetVectorFieldData(GenerateVectorFieldData(Xmin,Xmax,Ymin,Ymax,dXmin,dXmax,dYmin,dYmax,NumOfPoints)); 
        
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->{
             
             VectorField.SetWidth(root.getWidth());
             VectorField.SetHeight(root.getHeight());
             VectorField.PlotVectorField();
        };
        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener); 
        stage.setScene(scene);
        stage.show();
    }

    public ArrayList<Double[]> GenerateVectorFieldData(double Xmin,double Xmax, double Ymin,double Ymax, double dXmin, double dXmax, double dYmin, double dYmax, int NumOfPoints)
    {
        ArrayList<Double[]> SampleData = new ArrayList<Double[]>();
        
        Random rand = new Random(); //instance of random class
        double X = 0.0;
        double Y = 0.0;
        double dX = 0.0;
        double dY = 0.0;
        for(int i=0; i<NumOfPoints; i++)
        {
            X = ThreadLocalRandom.current().nextDouble(Xmin, Xmax);
            Y = ThreadLocalRandom.current().nextDouble(Ymin, Ymax);
            dX = ThreadLocalRandom.current().nextDouble(dXmin, dXmax);
            dY = ThreadLocalRandom.current().nextDouble(dYmin, dYmax);

            SampleData.add(new Double[]{X,Y,X+dX,Y+dY});
        }
        
        return SampleData;
    }
    public static void main(String[] args) {
        launch();
    }

}