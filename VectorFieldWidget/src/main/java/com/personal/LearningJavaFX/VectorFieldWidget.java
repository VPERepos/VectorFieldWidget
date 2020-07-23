package com.personal.LearningJavaFX;

import java.util.ArrayList;
import java.util.List;

import javafx.application.*;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;
import javafx.stage.*;
import javafx.scene.text.*;
import javafx.scene.layout.GridPane;

class VectorfieldWidget
{
    private Pane m_ParrentPane;
    private double m_Width;
    private double m_Height;
    private double m_PrevWidth;
    private double m_PrevHeight;
    
    private double m_MaxX;
    private double m_MinX;
    private double m_MaxY;
    private double m_MinY;
    
    private double m_MaxDX;
    private double m_MinDX;
    private double m_MaxDY;
    private double m_MinDY;

    private double m_MaxVecLen;
    private double m_MinVecLen;
    
    private double m_VecDScale;

    private int m_FontSizeTickLabels;
    private int m_FontSizeAxisLabels;

    private String m_XAxisName;
    private String m_YAxisName;
    private Text m_XAxisLabel;
    private Text m_YAxisLabel;

    private String m_PlotName;
    private Text m_PlotNameLabel;
    private int m_FontSizePlotLabel;

    private ArrayList<Double[]> m_VectorFieldData;
    private ArrayList<Double[]> m_VectorFieldDataTransformed;


    private Group m_Canvas;
    private Rectangle m_PlotAxesRectangle;
    
    private Rectangle m_ColorBar;

    private VBox m_CBTicksLabelBox;
    private Region m_CBTicksLabelVerticalSpacer1;
    private Region m_CBTicksLabelVerticalSpacer2;
    private Region m_CBTicksLabelVerticalSpacer3;
    private Region m_CBTicksLabelVerticalSpacer4;
    private Text m_CBTickText1;
    private Text m_CBTickText2;
    private Text m_CBTickText3;
    private Text m_CBTickText4;
    private Text m_CBTickText5;
    
    private Region m_SpacerPlotAreaColorBar;
    
    private Region m_MainVerticalSpacerUpper;
    

    private Region m_MainHorizontalSpacerLeft;
        
    private Region m_YLabelVerticalSpacerUpper;
    private Region m_YLabelVerticalSpacerLower;
    private VBox m_YLabelBox;

    private Region m_PlotLabelHorizontalSpacerLeft;
    private Region m_PlotLabelHorizontalSpacerRight;
    private HBox m_PlotLabelBox;

    private VBox m_YTicksLabelBox;
    private Region m_YTicksLabelVerticalSpacer1;
    private Region m_YTicksLabelVerticalSpacer2;
    private Region m_YTicksLabelVerticalSpacer3;
    private Region m_YTicksLabelVerticalSpacer4;
    private Text m_YTickText1;
    private Text m_YTickText2;
    private Text m_YTickText3;
    private Text m_YTickText4;
    private Text m_YTickText5;

    private VBox m_YTicksBox;
    private Region m_YTicksVerticalSpacer1;
    private Region m_YTicksVerticalSpacer2;
    private Region m_YTicksVerticalSpacer3;
    private Region m_YTicksVerticalSpacer4;
    private Line m_YTick1;
    private Line m_YTick2;
    private Line m_YTick3;
    private Line m_YTick4;
    private Line m_YTick5;

    private Region m_XLabelHorizontalSpacerLeft;
    private Region m_XLabelHorizontalSpacerRight;
    private HBox m_XLabelBox;

    private HBox m_XTicksLabelBox;
    private Region m_XTicksLabelHorizontalSpacer1;
    private Region m_XTicksLabelHorizontalSpacer2;
    private Region m_XTicksLabelHorizontalSpacer3;
    private Region m_XTicksLabelHorizontalSpacer4;
    private Text m_XTickText1;
    private Text m_XTickText2;
    private Text m_XTickText3;
    private Text m_XTickText4;
    private Text m_XTickText5;

    private HBox m_XTicksBox;
    private Region m_XTicksHorizonatlSpacer1;
    private Region m_XTicksHorizontalSpacer2;
    private Region m_XTicksHorizontalSpacer3;
    private Region m_XTicksHorizontalSpacer4;
    private Line m_XTick1;
    private Line m_XTick2;
    private Line m_XTick3;
    private Line m_XTick4;
    private Line m_XTick5;

    private GridPane m_Grid;

    Group m_PlotingArea;

	public VectorfieldWidget(Pane ParrentPane)
    {
        m_XAxisName = "X-Axis";
        m_YAxisName = "Y-Axis";
        m_PlotName = "Vector Field Plot";

        m_FontSizeTickLabels = 12;
        m_FontSizeAxisLabels = 14;
        m_FontSizePlotLabel = 18;

        m_MaxX = 1.0;
        m_MinX = 0.0;
        m_MaxY = 1.0;
        m_MinY = 0.0;

        m_MaxDX = 0.1;
        m_MinDX = 0.01;
        m_MaxDY = 0.1;
        m_MinDY = 0.01;

        m_MaxVecLen = 0.01;
        m_MinVecLen = 0.001;
        
        m_VecDScale = 1.0;
       
        m_ParrentPane = ParrentPane;
        m_Width = m_ParrentPane.getWidth();
        m_Height = m_ParrentPane.getHeight();

        m_VectorFieldDataTransformed = new ArrayList<Double[]>();

        InitVectorField();
        
    }

    private void ClearFieldPlot()
    {
        m_Canvas = null;
        m_PlotAxesRectangle = null;
        m_ColorBar = null;
        m_CBTicksLabelBox = null;
        m_CBTicksLabelVerticalSpacer1 = null;
        m_CBTicksLabelVerticalSpacer2 = null;
        m_CBTicksLabelVerticalSpacer3 = null;
        m_CBTicksLabelVerticalSpacer4 = null;
        m_CBTickText1 = null;
        m_CBTickText2 = null;
        m_CBTickText3 = null;
        m_CBTickText4 = null;
        m_CBTickText5 = null;
        m_SpacerPlotAreaColorBar = null;
        m_MainVerticalSpacerUpper = null;
        m_MainHorizontalSpacerLeft = null;
        m_YLabelVerticalSpacerUpper = null;
        m_YLabelVerticalSpacerLower = null;
        m_YLabelBox = null;
        m_PlotLabelHorizontalSpacerLeft = null;
        m_PlotLabelHorizontalSpacerRight = null;
        m_PlotLabelBox = null;
        m_YTicksLabelBox = null;
        m_YTicksLabelVerticalSpacer1 = null;
        m_YTicksLabelVerticalSpacer2 = null;
        m_YTicksLabelVerticalSpacer3 = null;
        m_YTicksLabelVerticalSpacer4 = null;
        m_YTickText1 = null;
        m_YTickText2 = null;
        m_YTickText3 = null;
        m_YTickText4 = null;
        m_YTickText5 = null;
        m_YTicksBox = null;
        m_YTicksVerticalSpacer1 = null;
        m_YTicksVerticalSpacer2 = null;
        m_YTicksVerticalSpacer3 = null;
        m_YTicksVerticalSpacer4 = null;
        m_YTick1 = null;
        m_YTick2 = null;
        m_YTick3 = null;
        m_YTick4 = null;
        m_YTick5 = null;
        m_XLabelHorizontalSpacerLeft = null;
        m_XLabelHorizontalSpacerRight = null;
        m_XLabelBox = null;
        m_XTicksLabelBox = null;
        m_XTicksLabelHorizontalSpacer1 = null;
        m_XTicksLabelHorizontalSpacer2 = null;
        m_XTicksLabelHorizontalSpacer3 = null;
        m_XTicksLabelHorizontalSpacer4 = null;
        m_XTickText1 = null;
        m_XTickText2 = null;
        m_XTickText3 = null;
        m_XTickText4 = null;
        m_XTickText5 = null;
        m_XTicksBox = null;
        m_XTicksHorizonatlSpacer1 = null;
        m_XTicksHorizontalSpacer2 = null;
        m_XTicksHorizontalSpacer3 = null;
        m_XTicksHorizontalSpacer4 = null;
        m_XTick1 = null;
        m_XTick2 = null;
        m_XTick3 = null;
        m_XTick4 = null;
        m_XTick5 = null;
        m_Grid = null;
        m_PlotingArea = null;
    }
    
    private void InitPlotName()
    {
        m_PlotNameLabel = new Text(m_PlotName);
        m_PlotNameLabel.setFont(new Font(m_FontSizePlotLabel));

        m_PlotLabelHorizontalSpacerLeft = new Region();
        m_PlotLabelHorizontalSpacerRight = new Region();
        
        HBox.setHgrow(m_PlotLabelHorizontalSpacerLeft, Priority.ALWAYS);
        HBox.setHgrow(m_PlotLabelHorizontalSpacerRight, Priority.ALWAYS);

        m_PlotLabelBox = new HBox(m_PlotLabelHorizontalSpacerLeft, m_PlotNameLabel, m_PlotLabelHorizontalSpacerRight);
    }
    
    private void InitYAxis()
    {
        m_YAxisLabel = new Text(m_YAxisName);
        m_YAxisLabel.setFont(new Font(m_FontSizeAxisLabels));
        m_YAxisLabel.setRotate(-90.0);
        
        m_YLabelVerticalSpacerUpper = new Region();
        m_YLabelVerticalSpacerLower = new Region();
        VBox.setVgrow(m_YLabelVerticalSpacerUpper, Priority.ALWAYS);
        VBox.setVgrow(m_YLabelVerticalSpacerLower, Priority.ALWAYS);
                
        m_YLabelBox = new VBox(m_YLabelVerticalSpacerUpper, m_YAxisLabel, m_YLabelVerticalSpacerLower);

        m_YTicksLabelVerticalSpacer1 = new Region();
        m_YTicksLabelVerticalSpacer2 = new Region();
        m_YTicksLabelVerticalSpacer3 = new Region();
        m_YTicksLabelVerticalSpacer4 = new Region();
        VBox.setVgrow(m_YTicksLabelVerticalSpacer1, Priority.ALWAYS);
        VBox.setVgrow(m_YTicksLabelVerticalSpacer2, Priority.ALWAYS);
        VBox.setVgrow(m_YTicksLabelVerticalSpacer3, Priority.ALWAYS);
        VBox.setVgrow(m_YTicksLabelVerticalSpacer4, Priority.ALWAYS);

        double YTickDelta = Math.abs(m_MaxY-m_MinY)/4.0;
        m_YTickText1 = new Text(String.format("%.2f", m_MaxY));
        m_YTickText2 = new Text(String.format("%.2f", m_MinY+3.0*YTickDelta));
        m_YTickText3 = new Text(String.format("%.2f", m_MinY+2.0*YTickDelta));
        m_YTickText4 = new Text(String.format("%.2f", m_MinY+YTickDelta));
        m_YTickText5 = new Text(String.format("%.2f", m_MinY));
        
        m_YTickText1.setTranslateY(-0.4*m_FontSizeTickLabels);
        m_YTickText2.setTranslateY(-0.2*m_FontSizeTickLabels);
        m_YTickText3.setTranslateY(-0.1*m_FontSizeTickLabels);
        m_YTickText4.setTranslateY(0.2*m_FontSizeTickLabels);
        m_YTickText5.setTranslateY(0.4*m_FontSizeTickLabels);

        m_YTickText1.setFont(new Font(m_FontSizeTickLabels));
        m_YTickText2.setFont(new Font(m_FontSizeTickLabels));
        m_YTickText3.setFont(new Font(m_FontSizeTickLabels));
        m_YTickText4.setFont(new Font(m_FontSizeTickLabels));
        m_YTickText5.setFont(new Font(m_FontSizeTickLabels));

        m_YTicksLabelBox = new VBox(m_YTickText1, m_YTicksLabelVerticalSpacer1, m_YTickText2, m_YTicksLabelVerticalSpacer2,m_YTickText3,m_YTicksLabelVerticalSpacer3, m_YTickText4, m_YTicksLabelVerticalSpacer4, m_YTickText5);
        m_YTicksLabelBox.setAlignment(Pos.CENTER_RIGHT);
        m_YTicksLabelBox.setAlignment(Pos.TOP_RIGHT);
        
        m_YTicksVerticalSpacer1 = new Region();
        m_YTicksVerticalSpacer2 = new Region();
        m_YTicksVerticalSpacer3 = new Region();
        m_YTicksVerticalSpacer4 = new Region();
        VBox.setVgrow(m_YTicksVerticalSpacer1, Priority.ALWAYS);
        VBox.setVgrow(m_YTicksVerticalSpacer2, Priority.ALWAYS);
        VBox.setVgrow(m_YTicksVerticalSpacer3, Priority.ALWAYS);
        VBox.setVgrow(m_YTicksVerticalSpacer4, Priority.ALWAYS);
        
        m_YTick1 = new Line();
        m_YTick2 = new Line();
        m_YTick3 = new Line();
        m_YTick4 = new Line();
        m_YTick5 = new Line();
        
        m_YTick1.setEndX(3.0);
        m_YTick2.setEndX(3.0);
        m_YTick3.setEndX(3.0);
        m_YTick4.setEndX(3.0);
        m_YTick5.setEndX(3.0);
        
        m_YTick1.setStrokeWidth(2);
        m_YTick2.setStrokeWidth(2);
        m_YTick3.setStrokeWidth(2);
        m_YTick4.setStrokeWidth(2);
        m_YTick5.setStrokeWidth(2);

        m_YTicksBox = new VBox(m_YTick1, m_YTicksVerticalSpacer1, m_YTick2, m_YTicksVerticalSpacer2, m_YTick3, m_YTicksVerticalSpacer3, m_YTick4, m_YTicksVerticalSpacer4, m_YTick5); 
        
    }

    private void InitXAxis()
    {
        m_XAxisLabel = new Text(m_XAxisName);
        m_XAxisLabel.setFont(new Font(m_FontSizeAxisLabels));
        
        m_XLabelHorizontalSpacerLeft = new Region();
        m_XLabelHorizontalSpacerRight = new Region();
        
        m_XLabelBox = new HBox(m_XLabelHorizontalSpacerLeft, m_XAxisLabel, m_XLabelHorizontalSpacerRight);
        HBox.setHgrow(m_XLabelHorizontalSpacerLeft, Priority.ALWAYS);
        HBox.setHgrow(m_XLabelHorizontalSpacerRight, Priority.ALWAYS);
        
        m_XTick1 = new Line();
        m_XTick2 = new Line();
        m_XTick3 = new Line();
        m_XTick4 = new Line();
        m_XTick5 = new Line();
        
        m_XTick1.setEndY(3.0);
        m_XTick2.setEndY(3.0);
        m_XTick3.setEndY(3.0);
        m_XTick4.setEndY(3.0);
        m_XTick5.setEndY(3.0);
        
        m_XTick1.setStrokeWidth(2);
        m_XTick2.setStrokeWidth(2);
        m_XTick3.setStrokeWidth(2);
        m_XTick4.setStrokeWidth(2);
        m_XTick5.setStrokeWidth(2);

        m_XTicksHorizonatlSpacer1 = new Region();
        m_XTicksHorizontalSpacer2 = new Region();
        m_XTicksHorizontalSpacer3 = new Region();
        m_XTicksHorizontalSpacer4 = new Region();
        HBox.setHgrow(m_XTicksHorizonatlSpacer1, Priority.ALWAYS);
        HBox.setHgrow(m_XTicksHorizontalSpacer2, Priority.ALWAYS);
        HBox.setHgrow(m_XTicksHorizontalSpacer3, Priority.ALWAYS);
        HBox.setHgrow(m_XTicksHorizontalSpacer4, Priority.ALWAYS);

        m_XTicksBox = new HBox(m_XTick1, m_XTicksHorizonatlSpacer1, m_XTick2, m_XTicksHorizontalSpacer2, m_XTick3, m_XTicksHorizontalSpacer3, m_XTick4, m_XTicksHorizontalSpacer4, m_XTick5);


        m_XTicksLabelHorizontalSpacer1 = new Region();
        m_XTicksLabelHorizontalSpacer2 = new Region();
        m_XTicksLabelHorizontalSpacer3 = new Region();
        m_XTicksLabelHorizontalSpacer4 = new Region();
        HBox.setHgrow(m_XTicksLabelHorizontalSpacer1, Priority.ALWAYS);
        HBox.setHgrow(m_XTicksLabelHorizontalSpacer2, Priority.ALWAYS);
        HBox.setHgrow(m_XTicksLabelHorizontalSpacer3, Priority.ALWAYS);
        HBox.setHgrow(m_XTicksLabelHorizontalSpacer4, Priority.ALWAYS);

        double XTickDelta = Math.abs(m_MaxX-m_MinX)/4.0;
        m_XTickText5 = new Text(String.format("%.2f", m_MaxX));
        m_XTickText4 = new Text(String.format("%.2f", m_MinX+3.0*XTickDelta));
        m_XTickText3 = new Text(String.format("%.2f", m_MinX+2.0*XTickDelta));
        m_XTickText2 = new Text(String.format("%.2f", m_MinX+XTickDelta));
        m_XTickText1 = new Text(String.format("%.2f", m_MinX));
        
        var XtickLabelWidth5 = 0.5*m_XTickText5.getText().length();
        var XtickLabelWidth4 = 0.5*m_XTickText4.getText().length();
        var XtickLabelWidth3 = 0.5*m_XTickText3.getText().length();
        var XtickLabelWidth2 = 0.5*m_XTickText2.getText().length();
        var XtickLabelWidth1 = 0.5*m_XTickText1.getText().length();
        m_XTickText1.setTranslateX(-0.3*XtickLabelWidth1*m_FontSizeTickLabels);
        m_XTickText2.setTranslateX(-0.1*XtickLabelWidth2*m_FontSizeTickLabels);
        m_XTickText3.setTranslateX(0.2*XtickLabelWidth3*m_FontSizeTickLabels);
        m_XTickText4.setTranslateX(0.3*XtickLabelWidth4*m_FontSizeTickLabels);
        m_XTickText5.setTranslateX(0.4*XtickLabelWidth5*m_FontSizeTickLabels);

        m_XTickText1.setFont(new Font(m_FontSizeTickLabels));
        m_XTickText2.setFont(new Font(m_FontSizeTickLabels));
        m_XTickText3.setFont(new Font(m_FontSizeTickLabels));
        m_XTickText4.setFont(new Font(m_FontSizeTickLabels));
        m_XTickText5.setFont(new Font(m_FontSizeTickLabels));

        m_XTicksLabelBox = new HBox(m_XTickText1, m_XTicksLabelHorizontalSpacer1, m_XTickText2, m_XTicksLabelHorizontalSpacer2,m_XTickText3,m_XTicksLabelHorizontalSpacer3, m_XTickText4, m_XTicksLabelHorizontalSpacer4, m_XTickText5);
        m_XTicksLabelBox.setAlignment(Pos.CENTER_RIGHT);
        m_XTicksLabelBox.setAlignment(Pos.TOP_RIGHT);
    }

    private void InitColorBar()
    {
        m_CBTicksLabelVerticalSpacer1 = new Region();
        m_CBTicksLabelVerticalSpacer2 = new Region();
        m_CBTicksLabelVerticalSpacer3 = new Region();
        m_CBTicksLabelVerticalSpacer4 = new Region();
        VBox.setVgrow(m_CBTicksLabelVerticalSpacer1, Priority.ALWAYS);
        VBox.setVgrow(m_CBTicksLabelVerticalSpacer2, Priority.ALWAYS);
        VBox.setVgrow(m_CBTicksLabelVerticalSpacer3, Priority.ALWAYS);
        VBox.setVgrow(m_CBTicksLabelVerticalSpacer4, Priority.ALWAYS);

        
        double CBTickDelta = Math.abs(m_MaxVecLen-m_MinVecLen)/4.0;
        m_CBTickText1 = new Text(String.format("%.6f", m_MaxVecLen));
        m_CBTickText2 = new Text(String.format("%.6f", m_MinVecLen+3.0*CBTickDelta));
        m_CBTickText3 = new Text(String.format("%.6f", m_MinVecLen+2.0*CBTickDelta));
        m_CBTickText4 = new Text(String.format("%.6f", m_MinVecLen+CBTickDelta));
        m_CBTickText5 = new Text(String.format("%.6f", m_MinVecLen));
        
        m_CBTickText1.setTranslateY(-0.4*m_FontSizeTickLabels);
        m_CBTickText2.setTranslateY(-0.2*m_FontSizeTickLabels);
        m_CBTickText3.setTranslateY(-0.1*m_FontSizeTickLabels);
        m_CBTickText4.setTranslateY(0.2*m_FontSizeTickLabels);
        m_CBTickText5.setTranslateY(0.4*m_FontSizeTickLabels);

        m_CBTickText1.setFont(new Font(m_FontSizeTickLabels));
        m_CBTickText2.setFont(new Font(m_FontSizeTickLabels));
        m_CBTickText3.setFont(new Font(m_FontSizeTickLabels));
        m_CBTickText4.setFont(new Font(m_FontSizeTickLabels));
        m_CBTickText5.setFont(new Font(m_FontSizeTickLabels));

        m_CBTicksLabelBox = new VBox(m_CBTickText1, m_CBTicksLabelVerticalSpacer1, m_CBTickText2, m_CBTicksLabelVerticalSpacer2,m_CBTickText3,m_CBTicksLabelVerticalSpacer3, m_CBTickText4, m_CBTicksLabelVerticalSpacer4, m_CBTickText5);
        m_CBTicksLabelBox.setAlignment(Pos.CENTER_RIGHT);
        m_CBTicksLabelBox.setAlignment(Pos.TOP_RIGHT);

        Stop[] stops = new Stop[] { new Stop(0, Color.rgb(0,0,255)), new Stop(1, Color.rgb(255,0,0))};
        LinearGradient lg1 = new LinearGradient(0, 1, 0,0, true, CycleMethod.NO_CYCLE, stops);
                
        m_ColorBar = new Rectangle();
        m_ColorBar.setWidth(20);
        m_ColorBar.setHeight(0.6*m_Height);
        m_ColorBar.setStroke(Color.BLACK);
        m_ColorBar.setStrokeWidth(2);
        m_ColorBar.setFill(lg1);

        m_SpacerPlotAreaColorBar = new Region();
        m_SpacerPlotAreaColorBar.setMinWidth(5.0);

        
    }
    
    private void InitPlotAxesRectangle()
    {
        m_PlotAxesRectangle = new Rectangle();
        m_PlotAxesRectangle.setStroke(Color.BLACK);
        m_PlotAxesRectangle.setStrokeWidth(2);
        m_PlotAxesRectangle.setFill(null);
        m_PlotAxesRectangle.setWidth(0.6*m_Width);  
        m_PlotAxesRectangle.setHeight(0.6*m_Height);
        
    }
    
    private void TransformVectorFieldData()
    {
        double FactorX = m_PlotAxesRectangle.getWidth()/(m_MaxX-m_MinX);
        double FactorY = m_PlotAxesRectangle.getArcHeight()/(m_MaxY-m_MinY);

        for(var DE : m_VectorFieldData)
        {
            double TX1;
            double TY1;
            double TX2;
            double TY2;

            TX1 = (DE[0]-m_MinX)*FactorX;
            TX2 = (DE[2]-m_MinX)*FactorX;
        }

    }

    private void DrawVectors()
    {
        var Arrow1 = new Arrow(new Point2D(10.6,1.6), new Point2D(48.4,8.4), Color.rgb(0,0,255), 0.50); 
        var Arrow2 = new Arrow(new Point2D(100.6,10.6), new Point2D(48.4,57.4), Color.rgb(255,0,0), 0.50);
        m_PlotingArea = new Group(m_PlotAxesRectangle, Arrow1.CreateArrow());

        m_PlotingArea.getChildren().add(Arrow2.CreateArrow());
    }

    private void InitVectorField()
    {
        ClearFieldPlot();
        m_MainVerticalSpacerUpper = new Region();
        m_MainVerticalSpacerUpper.setMinHeight(0.1*m_Width);
        VBox.setVgrow(m_MainVerticalSpacerUpper, Priority.ALWAYS);

        m_MainHorizontalSpacerLeft = new Region();
        m_MainHorizontalSpacerLeft.setMinSize(0.1*m_Width, 0.6*m_Height);
        HBox.setHgrow(m_MainHorizontalSpacerLeft, Priority.ALWAYS);
                        
        InitPlotName();
        InitYAxis();
        InitXAxis();
        InitColorBar();
        InitPlotAxesRectangle();
        TransformVectorFieldData();
        DrawVectors();

        m_Canvas = new Group();

        m_Grid = new GridPane();
               
        m_Grid.add(m_MainHorizontalSpacerLeft, 0, 2);
        m_Grid.add(m_MainVerticalSpacerUpper, 4, 0);
        m_Grid.add(m_PlotLabelBox, 4, 1);
        m_Grid.add(m_PlotingArea, 4, 2);
        m_Grid.add(m_SpacerPlotAreaColorBar,5, 2);
        m_Grid.add(m_ColorBar, 6, 2);
        m_Grid.add(m_CBTicksLabelBox, 7, 2);
        m_Grid.add(m_XTicksBox, 4, 3);
        m_Grid.add(m_XTicksLabelBox, 4, 4);
        m_Grid.add(m_XLabelBox, 4, 5);
        m_Grid.add(m_YTicksBox, 3, 2);
        m_Grid.add(m_YTicksLabelBox, 2, 2);
        m_Grid.add(m_YLabelBox, 1, 2);
                
        m_Canvas.getChildren().add(m_Grid);
        m_ParrentPane.getChildren().add(m_Canvas);
        
    }
    
    public void PlotVectorField()
    {
        m_MainVerticalSpacerUpper.setMinHeight(0.1*m_Width);
        m_MainHorizontalSpacerLeft.setMinSize(0.1*m_Width, 0.6*m_Height);
        m_PlotAxesRectangle.setWidth(0.6*m_Width);  
        m_PlotAxesRectangle.setHeight(0.6*m_Height);
        m_ColorBar.setHeight(0.6*m_Height);
        TransformVectorFieldData();
    }

    public void SetFontSizeTickLabels(int FontSizeTickLabels)
    {
        m_FontSizeTickLabels = FontSizeTickLabels;
    }
    public void SetFontSizeAxisLabels(int FontSizeAxisLabels)
    {
        m_FontSizeAxisLabels = FontSizeAxisLabels;
    }
    public void SetScaleFactor(double ScaleFactor)
    {
        m_VecDScale = ScaleFactor;
    }

    public void SetXAxisName(String XAxisName)
    {
        m_XAxisName = XAxisName;
    }

    public void SetYAxisName(String YAxisName)
    {
        m_YAxisName = YAxisName;
    }

    public void SetVectorFieldData(ArrayList<Double[]> VectorFieldData)
    {
        if(m_VectorFieldData!=null)
        {
            m_VectorFieldData.clear();
        }
        
        m_VectorFieldData = VectorFieldData;
        
        m_MaxX = Double.MIN_VALUE;
        m_MinX = Double.MAX_VALUE;
        m_MaxY = Double.MIN_VALUE;
        m_MinY = Double.MAX_VALUE;

        m_MaxVecLen = Double.MIN_VALUE;
        m_MinVecLen = Double.MAX_VALUE;
        
        for(var VectorElem : m_VectorFieldData)
        {
            double ActVecLen = Math.sqrt(Math.pow((VectorElem[2]-VectorElem[0]), 2)+Math.pow((VectorElem[3]-VectorElem[1]), 2));

            if(ActVecLen>m_MaxVecLen)
            {
                m_MaxVecLen = ActVecLen;
            }
            if(ActVecLen<m_MinVecLen)
            {
                m_MaxVecLen = ActVecLen;
            }

            VectorElem[2] = VectorElem[2]+(VectorElem[2]-VectorElem[0])*m_VecDScale;
            VectorElem[3] = VectorElem[3]+(VectorElem[3]-VectorElem[1])*m_VecDScale;

            if(VectorElem[0]>m_MaxX)
            {
                m_MaxX = VectorElem[0];
            }
            if(VectorElem[0]<m_MinX)
            {
                m_MinX = VectorElem[0];
            }
            if(VectorElem[1]>m_MaxY)
            {
                m_MaxY = VectorElem[1];
            }
            if(VectorElem[1]<m_MinY)
            {
                m_MinY = VectorElem[1];
            }

            if(VectorElem[2]>m_MaxX)
            {
                m_MaxX = VectorElem[2];
            }
            if(VectorElem[2]<m_MinX)
            {
                m_MinX = VectorElem[2];
            }
            if(VectorElem[3]>m_MaxY)
            {
                m_MaxY = VectorElem[3];
            }
            if(VectorElem[3]<m_MinY)
            {
                m_MinY = VectorElem[3];
            }
            
        }
    } 

    public void SetWidth(double Width)
    {
        m_PrevWidth = m_Width;
        m_Width = Width;
    }

    public void SetHeight(double Height)
    {
        m_PrevHeight = m_Height;
        m_Height = Height;
    }

    
}