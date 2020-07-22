package com.personal.LearningJavaFX;

import javafx.application.*;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.*;
import javafx.stage.*;


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

    public static void main(String[] args) {
        launch();
    }

}