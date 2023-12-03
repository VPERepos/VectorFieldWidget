package com.personal.LearningJavaFX;

import java.util.ArrayList;


import javafx.application.*;
import javafx.beans.value.ChangeListener;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Orientation;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.*;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;


public class App extends Application {
    
    
    private double m_Xmin;
    private double m_Xmax;
    private double m_Ymin;
    private double m_Ymax;
    private double m_dXmin;
    private double m_dXmax;
    private double m_dYmin;
    private double m_dYmax;
    private int m_NumOfPoints;
    private Timer m_Timer;

    private Pane m_Root;
    private TilePane m_VectorFieldPane;
    private TilePane m_ParametersPane;
    private BorderPane m_Divider;
    private VBox m_ParametersHolder;
    private Label m_XminLabel;
    private TextField m_XminCtrl;
    private Label m_XmaxLabel;
    private TextField m_XmaxCtrl;
    private Label m_YminLabel;
    private TextField m_YminCtrl;
    private Label m_YmaxLabel;
    private TextField m_YmaxCtrl;
    private Label m_dXminLabel;
    private TextField m_dXminCtrl;
    private Label m_dXmaxLabel;
    private TextField m_dXmaxCtrl;
    private Label m_dYminLabel;
    private TextField m_dYminCtrl;
    private Label m_dYmaxLabel;
    private TextField m_dYmaxCtrl;
    private Label m_NumOfPointsLabel;
    private TextField m_NumOfPointsPar;
    private Label m_Mode;
    private ObservableList<String> m_Modes;
    private ComboBox<String> m_ModesCB;
    private Label m_RefreshingRateLabel;
    private TextField m_RefreshingRatePar;
    private Label m_ScalingFactorLabel;
    private TextField m_ScalingFactorPar;
    private VectorfieldWidget m_VectorField;
    private Scene m_Scene;

    class PeriodicDataGenerator extends TimerTask
    {
                    
        public PeriodicDataGenerator()
        {
            parseParametersFromGuiControls();                   
        }
        
        @Override
        public void run()
        {
            m_VectorField.setScaleFactor(Double.parseDouble(m_ScalingFactorPar.getText()));
            m_VectorField.setVectorFieldData(GenerateVectorFieldData(m_Xmin,m_Xmax,m_Ymin,m_Ymax,m_dXmin,m_dXmax,m_dYmin,m_dYmax,m_NumOfPoints));
                            
            
            Platform.runLater(new Runnable() {
                @Override public void run() {
                    m_VectorField.PlotVectorField();                      
                }
            }
            );
                        
        }
    }
    
    @Override
    public void start(Stage stage) {

        initializeRootPane();
        
        initializeChildrenPanes();

        initializeParametersHolder();
        
        addXminToParametersHolder();
        
        addXmaxToParametersHolder();
        
        addYminToParametersHolder();
        
        addYmaxToParametersHolder();

        addDeltaXminToParametersHolder();

        addDeltaXmaxToParametersHolder();

        addDeltaYminToParametersHolder();

        addDeltaYmaxToParametersHolder();

        addNumOfPointsToParametersHolder();

        addParametersHolderToParametersPane();
        
        addAppModeComboBoxToParametersHolder();
        
        addRefreshingRateToParametersHolder();

        addScalingFactorToParametersHolder();
        
        parseParametersFromGuiControls();

        initializeVectorField();

        setModesComboBoxOnAction();
        
        setXminCtrlOnAction();

        setXmaxCtrlOnAction();

        setYminCtrlOnAction();

        setYmaxCtrlOnAction();

        setDeltaXminCtrlOnAction();
        
        setDeltaXmaxCtrlOnAction();
        
        setDeltaYminCtrlOnAction();
        
        setDeltaYmaxCtrlOnAction();

        setNumberOfPointsOnAction();

        setScalingFactorOnAction();

        setRefreshinRateOnAction();

        initializeStage(stage);
        
    }

    private void initializeRootPane()
    {
        m_Root = new Pane();
        m_Root.setPrefSize(1000, 750);
    }

    private void initializeChildrenPanes()
    {
        m_VectorFieldPane = new TilePane(Orientation.HORIZONTAL);
        m_ParametersPane = new TilePane(Orientation.HORIZONTAL);
                
        m_Divider = new BorderPane();
        m_Root.setStyle("-fx-background-color: #FFFFFF" );
        m_Divider.setCenter(m_VectorFieldPane);
        m_Divider.setRight(m_ParametersPane);
        
        
        m_Root.getChildren().add(m_Divider);
        m_VectorFieldPane.setPrefSize(850, 750);
        m_ParametersPane.setPrefSize(100, 600);
    }

    private void initializeParametersHolder()
    {
        m_ParametersHolder = new VBox();
    }

    private void addXminToParametersHolder()
    {
        m_XminLabel = new Label("Xmin");
        m_XminCtrl = new TextField("0.0");
        m_ParametersHolder.getChildren().add(m_XminLabel);
        m_ParametersHolder.getChildren().add(m_XminCtrl);
    }

    private void addXmaxToParametersHolder()
    {
        m_XmaxLabel = new Label("Xmax");
        m_XmaxCtrl = new TextField("4056.0");
        m_ParametersHolder.getChildren().add(m_XmaxLabel);
        m_ParametersHolder.getChildren().add(m_XmaxCtrl);
    }

    private void addYminToParametersHolder()
    {
        m_YminLabel = new Label("Ymin");
        m_YminCtrl = new TextField("0.0");
        m_ParametersHolder.getChildren().add(m_YminLabel);
        m_ParametersHolder.getChildren().add(m_YminCtrl);    
    }

    private void addYmaxToParametersHolder()
    {
        m_YmaxLabel = new Label("Ymax");
        m_YmaxCtrl = new TextField("3040.0");
        m_ParametersHolder.getChildren().add(m_YmaxLabel);
        m_ParametersHolder.getChildren().add(m_YmaxCtrl);
    }

    private void addDeltaXminToParametersHolder()
    {
        m_dXminLabel = new Label("dXmin");
        m_dXminCtrl = new TextField("-0.1");
        m_ParametersHolder.getChildren().add(m_dXminLabel);
        m_ParametersHolder.getChildren().add(m_dXminCtrl);
    }

    private void addDeltaXmaxToParametersHolder()
    {
        m_dXmaxLabel = new Label("dXmax");
        m_dXmaxCtrl = new TextField("0.1");
        m_ParametersHolder.getChildren().add(m_dXmaxLabel);
        m_ParametersHolder.getChildren().add(m_dXmaxCtrl);
    }

    private void addDeltaYminToParametersHolder()
    {
        m_dYminLabel = new Label("dYmin");
        m_dYminCtrl = new TextField("-0.1");
        m_ParametersHolder.getChildren().add(m_dYminLabel);
        m_ParametersHolder.getChildren().add(m_dYminCtrl);
    }

    private void addDeltaYmaxToParametersHolder()
    {
        m_dYmaxLabel = new Label("dYmax");
        m_dYmaxCtrl = new TextField("0.1");
        m_ParametersHolder.getChildren().add(m_dYmaxLabel);
        m_ParametersHolder.getChildren().add(m_dYmaxCtrl);
    }

    private void addNumOfPointsToParametersHolder()
    {
        m_NumOfPointsLabel = new Label("Number of Points");
        m_NumOfPointsPar = new TextField("1000");
        m_ParametersHolder.getChildren().add(m_NumOfPointsLabel);
        m_ParametersHolder.getChildren().add(m_NumOfPointsPar);
    }

    private void addAppModeComboBoxToParametersHolder()
    {
        m_Mode = new Label("Mode");
        m_Modes = FXCollections.observableArrayList("Single Shot", "Continuous Generation");
        m_ModesCB = new ComboBox<>(m_Modes);
        m_ModesCB.setVisibleRowCount(2);
        m_ModesCB.setValue("Single Shot");
        m_ParametersHolder.getChildren().add(m_Mode);
        m_ParametersHolder.getChildren().add(m_ModesCB);
    }

    private void addParametersHolderToParametersPane()
    {
        m_ParametersPane.getChildren().add(m_ParametersHolder);
    }
    
    private void addRefreshingRateToParametersHolder()
    {
        m_RefreshingRateLabel = new Label("Refreshing Rate");
        m_RefreshingRatePar = new TextField("5");
        m_ParametersHolder.getChildren().add(m_RefreshingRateLabel);
        m_ParametersHolder.getChildren().add(m_RefreshingRatePar);
    }

    private void addScalingFactorToParametersHolder()
    {
        m_ScalingFactorLabel = new Label("Scaling Factor");
        m_ScalingFactorPar = new TextField("10000.0");
        m_ParametersHolder.getChildren().add(m_ScalingFactorLabel);  
        m_ParametersHolder.getChildren().add(m_ScalingFactorPar);   
    }

    private void parseParametersFromGuiControls()
    {
        m_Xmin=Double.parseDouble(m_XminCtrl.getText());
        m_Xmax=Double.parseDouble(m_XmaxCtrl.getText());
        m_Ymin=Double.parseDouble(m_YminCtrl.getText());
        m_Ymax=Double.parseDouble(m_YmaxCtrl.getText());
        m_dXmin=Double.parseDouble(m_dXminCtrl.getText());
        m_dXmax=Double.parseDouble(m_dXmaxCtrl.getText());
        m_dYmin=Double.parseDouble(m_dYminCtrl.getText());
        m_dYmax=Double.parseDouble(m_dYmaxCtrl.getText());
        m_NumOfPoints=Integer.parseInt(m_NumOfPointsPar.getText());
    }

    private void initializeVectorField()
    {
        m_VectorField = new VectorfieldWidget(m_VectorFieldPane);
        m_Scene = new Scene(m_Root, 1000, 600,true,SceneAntialiasing.BALANCED);
        
        m_VectorField.setScaleFactor(Double.parseDouble(m_ScalingFactorPar.getText()));
        m_VectorField.setVectorFieldData(GenerateVectorFieldData(m_Xmin,m_Xmax,m_Ymin,m_Ymax,m_dXmin,m_dXmax,m_dYmin,m_dYmax,m_NumOfPoints)); 
    }

    private void setModesComboBoxOnAction()
    {
        m_ModesCB.setOnAction(e->{
            if(m_ModesCB.getValue() == "Single Shot")
            {
                m_Timer.cancel();
            }
            else
            {
                m_Timer = new Timer();
                var timerTask = new PeriodicDataGenerator();
                m_Timer.schedule(timerTask, 100, (long)(1000.0/Double.parseDouble(m_RefreshingRatePar.getText())));
            }
        });
    }

    private void setXminCtrlOnAction()
    {
        m_XminCtrl.setOnAction(e->{
            m_VectorField.setScaleFactor(Double.parseDouble(m_ScalingFactorPar.getText()));
            m_Xmin = Double.parseDouble(m_XminCtrl.getText());
            m_VectorField.setVectorFieldData(GenerateVectorFieldData(m_Xmin,m_Xmax,m_Ymin,m_Ymax,m_dXmin,m_dXmax,m_dYmin,m_dYmax,m_NumOfPoints));            
            m_VectorField.PlotVectorField();
        });
    }

    private void setXmaxCtrlOnAction()
    {
        m_XmaxCtrl.setOnAction(e->{
            
            m_VectorField.setScaleFactor(Double.parseDouble(m_ScalingFactorPar.getText()));
            m_Xmax = Double.parseDouble(m_XmaxCtrl.getText());
            m_VectorField.setVectorFieldData(GenerateVectorFieldData(m_Xmin,m_Xmax,m_Ymin,m_Ymax,m_dXmin,m_dXmax,m_dYmin,m_dYmax,m_NumOfPoints));
            
            m_VectorField.PlotVectorField();
        });
    }

    private void setYminCtrlOnAction()
    {
        m_YminCtrl.setOnAction(e->{
            
            m_VectorField.setScaleFactor(Double.parseDouble(m_ScalingFactorPar.getText()));
            m_Ymin = Double.parseDouble(m_YminCtrl.getText());
            m_VectorField.setVectorFieldData(GenerateVectorFieldData(m_Xmin,m_Xmax,m_Ymin,m_Ymax,m_dXmin,m_dXmax,m_dYmin,m_dYmax,m_NumOfPoints));
            
            m_VectorField.PlotVectorField();
        });
    }

    private void setYmaxCtrlOnAction()
    {
        m_YmaxCtrl.setOnAction(e->{
            
            m_VectorField.setScaleFactor(Double.parseDouble(m_ScalingFactorPar.getText()));
            m_Ymax = Double.parseDouble(m_YmaxCtrl.getText());
            m_VectorField.setVectorFieldData(GenerateVectorFieldData(m_Xmin,m_Xmax,m_Ymin,m_Ymax,m_dXmin,m_dXmax,m_dYmin,m_dYmax,m_NumOfPoints));
            
            m_VectorField.PlotVectorField();
        });
    }

    private void setDeltaXminCtrlOnAction()
    {
        m_dXminCtrl.setOnAction(e->{
            
            m_VectorField.setScaleFactor(Double.parseDouble(m_ScalingFactorPar.getText()));
            m_dXmin = Double.parseDouble(m_dXminCtrl.getText());
            m_VectorField.setVectorFieldData(GenerateVectorFieldData(m_Xmin,m_Xmax,m_Ymin,m_Ymax,m_dXmin,m_dXmax,m_dYmin,m_dYmax,m_NumOfPoints));
            
            m_VectorField.PlotVectorField();
        });
    }

    private void setDeltaXmaxCtrlOnAction()
    {
        m_dXmaxCtrl.setOnAction(e->{
            
            m_VectorField.setScaleFactor(Double.parseDouble(m_ScalingFactorPar.getText()));
            m_dXmax = Double.parseDouble(m_dXmaxCtrl.getText());
            m_VectorField.setVectorFieldData(GenerateVectorFieldData(m_Xmin,m_Xmax,m_Ymin,m_Ymax,m_dXmin,m_dXmax,m_dYmin,m_dYmax,m_NumOfPoints));
            
            m_VectorField.PlotVectorField();
        });
    }

    private void setDeltaYminCtrlOnAction()
    {
        m_dYminCtrl.setOnAction(e->{
            
            m_VectorField.setScaleFactor(Double.parseDouble(m_ScalingFactorPar.getText()));
            m_dYmin = Double.parseDouble(m_dYminCtrl.getText());
            m_VectorField.setVectorFieldData(GenerateVectorFieldData(m_Xmin,m_Xmax,m_Ymin,m_Ymax,m_dXmin,m_dXmax,m_dYmin,m_dYmax,m_NumOfPoints));
            
            m_VectorField.PlotVectorField();
        });
    }

    private void setDeltaYmaxCtrlOnAction()
    {
        m_dYmaxCtrl.setOnAction(e->{
            
            m_VectorField.setScaleFactor(Double.parseDouble(m_ScalingFactorPar.getText()));
            m_dYmax = Double.parseDouble(m_dYmaxCtrl.getText());
            m_VectorField.setVectorFieldData(GenerateVectorFieldData(m_Xmin,m_Xmax,m_Ymin,m_Ymax,m_dXmin,m_dXmax,m_dYmin,m_dYmax,m_NumOfPoints));
            
            m_VectorField.PlotVectorField();
        });
    }

    private void setNumberOfPointsOnAction()
    {
        m_NumOfPointsPar.setOnAction(e->{
            var value = Double.parseDouble(m_ScalingFactorPar.getText());
            if(value > 0.0)
            {
                m_VectorField.setScaleFactor(value);
                m_NumOfPoints = Integer.parseInt(m_NumOfPointsPar.getText());
                m_VectorField.setVectorFieldData(GenerateVectorFieldData(m_Xmin,m_Xmax,m_Ymin,m_Ymax,m_dXmin,m_dXmax,m_dYmin,m_dYmax,m_NumOfPoints));
                m_VectorField.PlotVectorField();
            }
        });
    }

    private void setScalingFactorOnAction()
    {
        m_ScalingFactorPar.setOnAction(e->{
            var value = Double.parseDouble(m_ScalingFactorPar.getText());
            if(value > 0.0)
            {
                m_VectorField.setScaleFactor(value);
                m_VectorField.refreshData();
                m_VectorField.PlotVectorField();
            }
        });
    }

    private void setRefreshinRateOnAction()
    {
        m_RefreshingRatePar.setOnAction(e->{
            if(m_ModesCB.getValue() == "Continuous Generation")
            {
                m_Timer.cancel();
                m_Timer = new Timer();
                var timerTask = new PeriodicDataGenerator();
                m_Timer.schedule(timerTask, 100, (long)(1000.0/Double.parseDouble(m_RefreshingRatePar.getText())));
            }

        });
    }

    private void initializeStage(Stage stage)
    {
        ChangeListener<Number> stageSizeListener = (observable, oldValue, newValue) ->{
            m_VectorFieldPane.setPrefSize(0.75*m_Root.getWidth(), m_Root.getHeight());
            m_ParametersPane.setPrefSize(0.1*m_Root.getWidth(), m_Root.getHeight());
            m_VectorField.setWidth(m_VectorFieldPane.getWidth());
            m_VectorField.setHeight(m_VectorFieldPane.getHeight());
            m_VectorField.PlotVectorField();
            
        };
        stage.widthProperty().addListener(stageSizeListener);
        stage.heightProperty().addListener(stageSizeListener); 
        stage.setScene(m_Scene);
        stage.show();
    }

    private ArrayList<Double[]> GenerateVectorFieldData(double Xmin,double Xmax, double Ymin,double Ymax, double dXmin, double dXmax, double dYmin, double dYmax, int NumOfPoints)
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