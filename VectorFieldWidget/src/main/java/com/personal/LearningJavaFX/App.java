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
        /*Arrow arrow = new Arrow();
        root.getChildren().add(arrow);

        root.setOnMouseClicked(evt -> {
            switch (evt.getButton()) {
                case PRIMARY:
                    // set pos of end with arrow head
                    arrow.setEndX(evt.getX());
                    arrow.setEndY(evt.getY());
                    break;
                case SECONDARY:
                    // set pos of end without arrow head
                    arrow.setStartX(evt.getX());
                    arrow.setStartY(evt.getY());
                    break;
            }
        });*/
        VectorfieldWidget VectorField = new VectorfieldWidget(root, 400.0, 400.0);
        Scene scene = new Scene(root, 400, 400);
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->{
             System.out.println("Height: " + stage.getHeight() + " Width: " + stage.getWidth());
             VectorField.SetWidth(stage.getWidth());
             VectorField.SetHeight(stage.getHeight());
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