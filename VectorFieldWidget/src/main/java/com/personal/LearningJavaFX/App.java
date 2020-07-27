package com.personal.LearningJavaFX;

import java.util.ArrayList;
import java.util.List;

import javafx.application.*;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.*;
import java.util.Random;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;

/**
 * JavaFX App
 */
public class App extends Application {
    
    
    private double Xmin;
    private double Xmax;
    private double Ymin;
    private double Ymax;
    private double dXmin;
    private double dXmax;
    private double dYmin;
    private double dYmax;
    private int NumOfPoints;
    private Timer m_Timer;
    @Override
    public void start(Stage stage) {
        
        
        
        Pane root = new Pane();
        root.setPrefSize(1000, 750);
        
        TilePane VectorFieldPane = new TilePane(Orientation.HORIZONTAL);
        TilePane ParametersPane = new TilePane(Orientation.HORIZONTAL);
        
        
        BorderPane Divider = new BorderPane();
        root.setStyle("-fx-background-color: #FFFFFF" );
        Divider.setCenter(VectorFieldPane);
        Divider.setRight(ParametersPane);
        
        
        root.getChildren().add(Divider);
        VectorFieldPane.setPrefSize(850, 750);
        ParametersPane.setPrefSize(100, 600);
       

        VBox ParametersHolder = new VBox();

        Label XminLabel = new Label("Xmin");
        TextField XminCtrl = new TextField("0.0");
        ParametersHolder.getChildren().add(XminLabel);
        ParametersHolder.getChildren().add(XminCtrl);

        
        Label XmaxLabel = new Label("Xmax");
        TextField XmaxCtrl = new TextField("4056.0");
        ParametersHolder.getChildren().add(XmaxLabel);
        ParametersHolder.getChildren().add(XmaxCtrl);

        Label YminLabel = new Label("Ymin");
        TextField YminCtrl = new TextField("0.0");
        ParametersHolder.getChildren().add(YminLabel);
        ParametersHolder.getChildren().add(YminCtrl);

        Label YmaxLabel = new Label("Ymax");
        TextField YmaxCtrl = new TextField("3040.0");
        ParametersHolder.getChildren().add(YmaxLabel);
        ParametersHolder.getChildren().add(YmaxCtrl);

        Label dXminLabel = new Label("dXmin");
        TextField dXminCtrl = new TextField("-0.1");
        ParametersHolder.getChildren().add(dXminLabel);
        ParametersHolder.getChildren().add(dXminCtrl);

        Label dXmaxLabel = new Label("dXmax");
        TextField dXmaxCtrl = new TextField("0.1");
        ParametersHolder.getChildren().add(dXmaxLabel);
        ParametersHolder.getChildren().add(dXmaxCtrl);

        Label dYminLabel = new Label("dYmin");
        TextField dYminCtrl = new TextField("-0.1");
        ParametersHolder.getChildren().add(dYminLabel);
        ParametersHolder.getChildren().add(dYminCtrl);

        Label dYmaxLabel = new Label("dYmax");
        TextField dYmaxCtrl = new TextField("0.1");
        ParametersHolder.getChildren().add(dYmaxLabel);
        ParametersHolder.getChildren().add(dYmaxCtrl);

        Label NumOfPointsLabel = new Label("Number of Points");
        TextField NumOfPointsPar = new TextField("1000");
        ParametersHolder.getChildren().add(NumOfPointsLabel);
        ParametersHolder.getChildren().add(NumOfPointsPar);

        ParametersPane.getChildren().add(ParametersHolder);
        Label Mode = new Label("Mode");
        ObservableList<String> Modes = FXCollections.observableArrayList("Single Shot", "Continuous Generation");
        ComboBox<String> ModesCB = new ComboBox<>(Modes);
        ModesCB.setVisibleRowCount(2);
        ModesCB.setValue("Single Shot");
       
        ParametersHolder.getChildren().add(Mode);
        ParametersHolder.getChildren().add(ModesCB);

        Label RefreshingRateLabel = new Label("Refreshing Rate");
        TextField RefreshingRatePar = new TextField("5");
        ParametersHolder.getChildren().add(RefreshingRateLabel);
        ParametersHolder.getChildren().add(RefreshingRatePar);

        Label ScalingFactorLabel = new Label("Scaling Factor");
        TextField ScalingFactorPar = new TextField("10000.0");
        ParametersHolder.getChildren().add(ScalingFactorLabel);  
        ParametersHolder.getChildren().add(ScalingFactorPar);    
        
        VectorfieldWidget VectorField = new VectorfieldWidget(VectorFieldPane);
        Scene scene = new Scene(root, 1000, 600,true,SceneAntialiasing.BALANCED);
        
        Xmin=Double.parseDouble(XminCtrl.getText());
        Xmax=Double.parseDouble(XmaxCtrl.getText());
        Ymin=Double.parseDouble(YminCtrl.getText());
        Ymax=Double.parseDouble(YmaxCtrl.getText());
        dXmin=Double.parseDouble(dXminCtrl.getText());
        dXmax=Double.parseDouble(dXmaxCtrl.getText());
        dYmin=Double.parseDouble(dYminCtrl.getText());
        dYmax=Double.parseDouble(dYmaxCtrl.getText());
        NumOfPoints=Integer.parseInt(NumOfPointsPar.getText());
        
        VectorField.SetScaleFactor(Double.parseDouble(ScalingFactorPar.getText()));
        VectorField.SetVectorFieldData(GenerateVectorFieldData(Xmin,Xmax,Ymin,Ymax,dXmin,dXmax,dYmin,dYmax,NumOfPoints)); 
        class PeriodicDataGenerator extends TimerTask
        {
                        
            public PeriodicDataGenerator()
            {
                                
            }
            
            @Override
            public void run()
            {
                Xmin=Double.parseDouble(XminCtrl.getText());
                Xmax=Double.parseDouble(XmaxCtrl.getText());
                Ymin=Double.parseDouble(YminCtrl.getText());
                Ymax=Double.parseDouble(YmaxCtrl.getText());
                dXmin=Double.parseDouble(dXminCtrl.getText());
                dXmax=Double.parseDouble(dXmaxCtrl.getText());
                dYmin=Double.parseDouble(dYminCtrl.getText());
                dYmax=Double.parseDouble(dYmaxCtrl.getText());
                NumOfPoints=Integer.parseInt(NumOfPointsPar.getText());
                VectorField.SetScaleFactor(Double.parseDouble(ScalingFactorPar.getText()));
                VectorField.SetVectorFieldData(GenerateVectorFieldData(Xmin,Xmax,Ymin,Ymax,dXmin,dXmax,dYmin,dYmax,NumOfPoints));
                               
                
                Platform.runLater(new Runnable() {
                    @Override public void run() {
                        VectorField.PlotVectorField();                      
                    }
                }
                );
                            
            }
        }
        ModesCB.setOnAction(e->{
            if(ModesCB.getValue() == "Single Shot")
            {
                m_Timer.cancel();
            }
            else
            {
                m_Timer = new Timer();
                m_Timer.schedule(new PeriodicDataGenerator(), 100, (long)(1000.0/Double.parseDouble(RefreshingRatePar.getText())));
            }
        });
        XminCtrl.setOnAction(e->{
            VectorField.SetScaleFactor(Double.parseDouble(ScalingFactorPar.getText()));
            Xmin = Double.parseDouble(XminCtrl.getText());
            VectorField.SetVectorFieldData(GenerateVectorFieldData(Xmin,Xmax,Ymin,Ymax,dXmin,dXmax,dYmin,dYmax,NumOfPoints));            
            VectorField.PlotVectorField();
        });

        XmaxCtrl.setOnAction(e->{
            
            VectorField.SetScaleFactor(Double.parseDouble(ScalingFactorPar.getText()));
            Xmax = Double.parseDouble(XmaxCtrl.getText());
            VectorField.SetVectorFieldData(GenerateVectorFieldData(Xmin,Xmax,Ymin,Ymax,dXmin,dXmax,dYmin,dYmax,NumOfPoints));
            
            VectorField.PlotVectorField();
        });

        YminCtrl.setOnAction(e->{
            
            VectorField.SetScaleFactor(Double.parseDouble(ScalingFactorPar.getText()));
            Ymin = Double.parseDouble(YminCtrl.getText());
            VectorField.SetVectorFieldData(GenerateVectorFieldData(Xmin,Xmax,Ymin,Ymax,dXmin,dXmax,dYmin,dYmax,NumOfPoints));
            
            VectorField.PlotVectorField();
        });

        YmaxCtrl.setOnAction(e->{
            
            VectorField.SetScaleFactor(Double.parseDouble(ScalingFactorPar.getText()));
            Ymax = Double.parseDouble(YmaxCtrl.getText());
            VectorField.SetVectorFieldData(GenerateVectorFieldData(Xmin,Xmax,Ymin,Ymax,dXmin,dXmax,dYmin,dYmax,NumOfPoints));
            
            VectorField.PlotVectorField();
        });

        dXminCtrl.setOnAction(e->{
            
            VectorField.SetScaleFactor(Double.parseDouble(ScalingFactorPar.getText()));
            dXmin = Double.parseDouble(dXminCtrl.getText());
            VectorField.SetVectorFieldData(GenerateVectorFieldData(Xmin,Xmax,Ymin,Ymax,dXmin,dXmax,dYmin,dYmax,NumOfPoints));
            
            VectorField.PlotVectorField();
        });

        dXmaxCtrl.setOnAction(e->{
            
            VectorField.SetScaleFactor(Double.parseDouble(ScalingFactorPar.getText()));
            dXmax = Double.parseDouble(dXmaxCtrl.getText());
            VectorField.SetVectorFieldData(GenerateVectorFieldData(Xmin,Xmax,Ymin,Ymax,dXmin,dXmax,dYmin,dYmax,NumOfPoints));
            
            VectorField.PlotVectorField();
        });

        dYminCtrl.setOnAction(e->{
            
            VectorField.SetScaleFactor(Double.parseDouble(ScalingFactorPar.getText()));
            dYmin = Double.parseDouble(dYminCtrl.getText());
            VectorField.SetVectorFieldData(GenerateVectorFieldData(Xmin,Xmax,Ymin,Ymax,dXmin,dXmax,dYmin,dYmax,NumOfPoints));
            
            VectorField.PlotVectorField();
        });

        dYmaxCtrl.setOnAction(e->{
            
            VectorField.SetScaleFactor(Double.parseDouble(ScalingFactorPar.getText()));
            dYmax = Double.parseDouble(dYmaxCtrl.getText());
            VectorField.SetVectorFieldData(GenerateVectorFieldData(Xmin,Xmax,Ymin,Ymax,dXmin,dXmax,dYmin,dYmax,NumOfPoints));
            
            VectorField.PlotVectorField();
        });

        NumOfPointsPar.setOnAction(e->{
            
            VectorField.SetScaleFactor(Double.parseDouble(ScalingFactorPar.getText()));
            NumOfPoints = Integer.parseInt(NumOfPointsPar.getText());
            VectorField.SetVectorFieldData(GenerateVectorFieldData(Xmin,Xmax,Ymin,Ymax,dXmin,dXmax,dYmin,dYmax,NumOfPoints));
            
            VectorField.PlotVectorField();
        });

        ScalingFactorPar.setOnAction(e->{
            
            VectorField.SetScaleFactor(Double.parseDouble(ScalingFactorPar.getText()));
            VectorField.RefreshData();
            VectorField.PlotVectorField();
        });

        RefreshingRatePar.setOnAction(e->{
            if(ModesCB.getValue() == "Continuous Generation")
            {
                m_Timer.cancel();
                m_Timer = new Timer();
                m_Timer.schedule(new PeriodicDataGenerator(), 100, (long)(1000.0/Double.parseDouble(RefreshingRatePar.getText())));
            }

        });
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->{
            VectorFieldPane.setPrefSize(0.75*root.getWidth(), root.getHeight());
            ParametersPane.setPrefSize(0.1*root.getWidth(), root.getHeight());
            VectorField.SetWidth(VectorFieldPane.getWidth());
            VectorField.SetHeight(VectorFieldPane.getHeight());
            //VectorFieldPane.setPrefSize(root.getWidth());
            VectorField.PlotVectorField();
            //System.out.println(VectorFieldPane.getWidth());
        };
        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener); 
        stage.setScene(scene);
        stage.show();
    }

    public ArrayList<Double[]> GenerateVectorFieldData(double Xmin,double Xmax, double Ymin,double Ymax, double dXmin, double dXmax, double dYmin, double dYmax, int NumOfPoints)
    {
        ArrayList<Double[]> SampleData = new ArrayList<Double[]>();
        
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