package com.personal.LearningJavaFX;

import java.util.ArrayList;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Paint;
import javafx.scene.paint.Stop;
import javafx.scene.shape.*;

import javafx.scene.text.*;
import javafx.scene.layout.GridPane;

class VectorfieldWidget
{
    public class Arrow extends Group {

        private Line m_Middle;
        private Polygon m_Triangle;
    
        public Arrow(Point2D Start, Point2D End, Paint Color, double Opacity)
        {
            m_Middle = new Line();
            m_Middle.setStrokeWidth(1.5); 
            m_Middle.setStartX(Start.getX()); 
            m_Middle.setStartY(Start.getY());
            m_Middle.setEndX(End.getX()); 
            m_Middle.setEndY(End.getY());
            m_Middle.setStroke(Color);
            m_Middle.setOpacity(Opacity);
    
            m_Triangle = new Polygon();
            
            double PointX1 = End.getX();
            double PointY1 = End.getY();
                    
            double PointX2;
            double PointY2;
            
            double PointX3;
            double PointY3;
            
            if (End.getX() == Start.getX() && End.getY() == Start.getY()) 
            {
                PointX2 = End.getX();
                PointY2 = End.getY();
                PointX3 = End.getX();
                PointY3 = End.getY();
            } 
            else 
            {
                var ArrowWidth = 2;
                var ArrowLength = 6; 
                double factor = ArrowLength / Math.hypot(Start.getX() - End.getX(), Start.getY() - End.getY());
                double factorO = ArrowWidth / Math.hypot(Start.getX() - End.getX(), Start.getY() - End.getY());
    
                
                double dx = (Start.getX() - End.getX())*factor;
                double dy = (Start.getY() - End.getY())*factor;
    
                
                double ox = (Start.getX() - End.getX())*factorO;
                double oy = (Start.getY() - End.getY())*factorO;
    
                PointX2 = End.getX() + dx - oy;
                PointY2 = End.getY() + dy + ox;
    
                PointX3 = End.getX() + dx + oy;
                PointY3 = End.getY() + dy - ox;
    
                m_Triangle.getPoints().addAll(new Double[]{
                    PointX1, PointY1,
                    PointX2, PointY2,
                    PointX3, PointY3,
                    PointX1, PointY1});
    
                m_Triangle.setStroke(Color);
                m_Triangle.setFill(Color);
            }
        }
    
        public Group createArrow()
        {
            return new Group(m_Middle,m_Triangle);
        }
    }
    private Pane m_ParrentPane;
    private double m_Width;
    private double m_Height;
        
    private double m_MaxX;
    private double m_MinX;
    private double m_MaxY;
    private double m_MinY;
    
    private double m_MaxVecLen;
    private double m_MinVecLen;
    private double m_MaxVecLenTransformed;
    private double m_MinVecLenTransformed;
    
    private double m_VecDScale;
    private double m_PrevVecDScale;

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
    private Region m_MainVerticalSpacerLower;

    private Region m_MainHorizontalSpacerLeft;
    private Region m_MainHorizontalSpacerRight;

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

    private StackPane m_PlotingArea;
    private Group m_Arrows;

    private double m_FactorX;
    private double m_FactorY;

    private LinearGradient m_LinearGradient1;
    private Stop[] m_GradientStops;

    double m_ActVecLen;

	public VectorfieldWidget(Pane ParrentPane)
    {
        initPlotAndAxisNames();
        initFontSizes();
        setInitialMinAndMaxValues();
        initParrentPane(ParrentPane);       
        initVectorFieldData();
        initVectorField();
        
    }

    private void initPlotAndAxisNames()
    {
        m_XAxisName = "X-Axis";
        m_YAxisName = "Y-Axis";
        m_PlotName = "Vector Field Plot";
    }

    private void initFontSizes()
    {
        m_FontSizeTickLabels = 12;
        m_FontSizeAxisLabels = 14;
        m_FontSizePlotLabel = 18;
    }

    private void setInitialMinAndMaxValues()
    {
        m_MaxX = 1.0;
        m_MinX = 0.0;
        m_MaxY = 1.0;
        m_MinY = 0.0;
        
        m_MaxVecLen = 0.01;
        m_MinVecLen = 0.001;
        
        m_VecDScale = 1.0;
    }

    private void initParrentPane(Pane ParrentPane)
    {
        m_ParrentPane = ParrentPane;
        m_Width = m_ParrentPane.getWidth();
        m_Height = m_ParrentPane.getHeight();
    }

    private void initVectorFieldData()
    {
        m_VectorFieldDataTransformed = new ArrayList<Double[]>();
    }

    private void initVectorField()
    {
        
        initSpacers();                
        initPlotName();
        initYAxis();
        initXAxis();
        initColorBar();
        initPlotAxesRectangle();
        initCanvas();
        
    }

    private void initSpacers()
    {
        m_MainVerticalSpacerUpper = new Region();
        VBox.setVgrow(m_MainVerticalSpacerUpper, Priority.ALWAYS);

        m_MainVerticalSpacerLower = new Region();
        VBox.setVgrow(m_MainVerticalSpacerUpper, Priority.ALWAYS);

        m_MainHorizontalSpacerLeft = new Region();
        HBox.setHgrow(m_MainHorizontalSpacerLeft, Priority.ALWAYS);

        m_MainHorizontalSpacerRight = new Region();
        HBox.setHgrow(m_MainHorizontalSpacerRight, Priority.ALWAYS);
    }

    private void initCanvas()
    {
        m_Canvas = new Group();

        m_Grid = new GridPane();
        
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

    private void initPlotName()
    {
        m_PlotNameLabel = new Text(m_PlotName);
        m_PlotNameLabel.setFont(new Font(m_FontSizePlotLabel));

        m_PlotLabelHorizontalSpacerLeft = new Region();
        m_PlotLabelHorizontalSpacerRight = new Region();
        
        HBox.setHgrow(m_PlotLabelHorizontalSpacerLeft, Priority.ALWAYS);
        HBox.setHgrow(m_PlotLabelHorizontalSpacerRight, Priority.ALWAYS);

        m_PlotLabelBox = new HBox(m_PlotLabelHorizontalSpacerLeft, m_PlotNameLabel, m_PlotLabelHorizontalSpacerRight);
    }
    
    private void initYAxis()
    {
        initYAxisLabel();
        initYLabelBox();
        initYTickLabelSpacers();
        setYTicksText();
        setYTicksTextPosition();
        setYTicksTextFont();
        initYTicksLabelBox();
        initYTicksSpacers();
        initYTickDrawing();
        setYTickDrawingLength();
        setYTickDrawingWidth();
        initYTicksBox();
        
    }
    
    private void initYAxisLabel()
    {
        m_YAxisLabel = new Text(m_YAxisName);
        m_YAxisLabel.setFont(new Font(m_FontSizeAxisLabels));
        m_YAxisLabel.setRotate(-90.0);
    }
    
    private void initYLabelBox()
    {
        m_YLabelVerticalSpacerUpper = new Region();
        m_YLabelVerticalSpacerLower = new Region();
        VBox.setVgrow(m_YLabelVerticalSpacerUpper, Priority.ALWAYS);
        VBox.setVgrow(m_YLabelVerticalSpacerLower, Priority.ALWAYS);
                
        m_YLabelBox = new VBox(m_YLabelVerticalSpacerUpper, m_YAxisLabel, m_YLabelVerticalSpacerLower);
    }
    
    private void initYTickLabelSpacers()
    {
        m_YTicksLabelVerticalSpacer1 = new Region();
        m_YTicksLabelVerticalSpacer2 = new Region();
        m_YTicksLabelVerticalSpacer3 = new Region();
        m_YTicksLabelVerticalSpacer4 = new Region();
        VBox.setVgrow(m_YTicksLabelVerticalSpacer1, Priority.ALWAYS);
        VBox.setVgrow(m_YTicksLabelVerticalSpacer2, Priority.ALWAYS);
        VBox.setVgrow(m_YTicksLabelVerticalSpacer3, Priority.ALWAYS);
        VBox.setVgrow(m_YTicksLabelVerticalSpacer4, Priority.ALWAYS);
    }

    private void setYTicksText()
    {
        double YTickDelta = Math.abs(m_MaxY-m_MinY+20/m_FactorY)/4.0;
        m_YTickText1 = new Text(String.format("%.2f", m_MaxY+10/m_FactorY));
        m_YTickText2 = new Text(String.format("%.2f", m_MinY-10/m_FactorY+3.0*YTickDelta));
        m_YTickText3 = new Text(String.format("%.2f", m_MinY-10/m_FactorY+2.0*YTickDelta));
        m_YTickText4 = new Text(String.format("%.2f", m_MinY-10/m_FactorY+YTickDelta));
        m_YTickText5 = new Text(String.format("%.2f", m_MinY-10/m_FactorY));
    }

    private void setYTicksTextPosition()
    {
                
        m_YTickText1.setTranslateY(-0.4*m_FontSizeTickLabels);
        m_YTickText2.setTranslateY(-0.2*m_FontSizeTickLabels);
        m_YTickText3.setTranslateY(-0.1*m_FontSizeTickLabels);
        m_YTickText4.setTranslateY(0.2*m_FontSizeTickLabels);
        m_YTickText5.setTranslateY(0.4*m_FontSizeTickLabels);

    }

    private void setYTicksTextFont()
    {
        m_YTickText1.setFont(new Font(m_FontSizeTickLabels));
        m_YTickText2.setFont(new Font(m_FontSizeTickLabels));
        m_YTickText3.setFont(new Font(m_FontSizeTickLabels));
        m_YTickText4.setFont(new Font(m_FontSizeTickLabels));
        m_YTickText5.setFont(new Font(m_FontSizeTickLabels));
    }

    private void initYTicksLabelBox()
    {
        m_YTicksLabelBox = new VBox(m_YTickText1, m_YTicksLabelVerticalSpacer1, m_YTickText2, m_YTicksLabelVerticalSpacer2,m_YTickText3,m_YTicksLabelVerticalSpacer3, m_YTickText4, m_YTicksLabelVerticalSpacer4, m_YTickText5);
        m_YTicksLabelBox.setAlignment(Pos.CENTER_RIGHT);
        m_YTicksLabelBox.setAlignment(Pos.TOP_RIGHT);
    }

    private void initYTicksSpacers()
    {
        m_YTicksVerticalSpacer1 = new Region();
        m_YTicksVerticalSpacer2 = new Region();
        m_YTicksVerticalSpacer3 = new Region();
        m_YTicksVerticalSpacer4 = new Region();
        VBox.setVgrow(m_YTicksVerticalSpacer1, Priority.ALWAYS);
        VBox.setVgrow(m_YTicksVerticalSpacer2, Priority.ALWAYS);
        VBox.setVgrow(m_YTicksVerticalSpacer3, Priority.ALWAYS);
        VBox.setVgrow(m_YTicksVerticalSpacer4, Priority.ALWAYS);
    }

    private void initYTickDrawing()
    {
        m_YTick1 = new Line();
        m_YTick2 = new Line();
        m_YTick3 = new Line();
        m_YTick4 = new Line();
        m_YTick5 = new Line();
    }

    private void setYTickDrawingLength()
    {
        m_YTick1.setEndX(3.0);
        m_YTick2.setEndX(3.0);
        m_YTick3.setEndX(3.0);
        m_YTick4.setEndX(3.0);
        m_YTick5.setEndX(3.0);
    }
    
    private void setYTickDrawingWidth()
    {
        m_YTick1.setStrokeWidth(2);
        m_YTick2.setStrokeWidth(2);
        m_YTick3.setStrokeWidth(2);
        m_YTick4.setStrokeWidth(2);
        m_YTick5.setStrokeWidth(2);
    }

    private void initYTicksBox()
    {
        m_YTicksBox = new VBox(m_YTick1, m_YTicksVerticalSpacer1, m_YTick2, m_YTicksVerticalSpacer2, m_YTick3, m_YTicksVerticalSpacer3, m_YTick4, m_YTicksVerticalSpacer4, m_YTick5); 
    }
    
    private void initXAxis()
    {
        initXAxisLabel();
        initXLabelBox();
        initXTickLabelSpacers();
        setXTicksText();
        setXTicksTextPosition();
        setXTicksTextFont();
        initXTicksSpacers();
        setXTicksLabelBox();
        initXTicksDrawing();
        setXTickDrawingLength();
        setXTickDrawingWidth();
        initXTicksBox();
    }

    private void initXAxisLabel()
    {
        m_XAxisLabel = new Text(m_XAxisName);
        m_XAxisLabel.setFont(new Font(m_FontSizeAxisLabels));
    }

    private void initXLabelBox()
    {
        m_XLabelHorizontalSpacerLeft = new Region();
        m_XLabelHorizontalSpacerRight = new Region();
        
        m_XLabelBox = new HBox(m_XLabelHorizontalSpacerLeft, m_XAxisLabel, m_XLabelHorizontalSpacerRight);
        HBox.setHgrow(m_XLabelHorizontalSpacerLeft, Priority.ALWAYS);
        HBox.setHgrow(m_XLabelHorizontalSpacerRight, Priority.ALWAYS);
    }

    private void initXTickLabelSpacers()
    {
        m_XTicksHorizonatlSpacer1 = new Region();
        m_XTicksHorizontalSpacer2 = new Region();
        m_XTicksHorizontalSpacer3 = new Region();
        m_XTicksHorizontalSpacer4 = new Region();
       
        HBox.setHgrow(m_XTicksHorizonatlSpacer1, Priority.ALWAYS);
        HBox.setHgrow(m_XTicksHorizontalSpacer2, Priority.ALWAYS);
        HBox.setHgrow(m_XTicksHorizontalSpacer3, Priority.ALWAYS);
        HBox.setHgrow(m_XTicksHorizontalSpacer4, Priority.ALWAYS);
    }

    private void setXTicksText()
    {
        double XTickDelta = Math.abs(m_MaxX-m_MinX+20/m_FactorX)/4.0;
        m_XTickText5 = new Text(String.format("%.2f", m_MaxX+10/m_FactorX));
        m_XTickText4 = new Text(String.format("%.2f", m_MinX-10/m_FactorX+3.0*XTickDelta));
        m_XTickText3 = new Text(String.format("%.2f", m_MinX-10/m_FactorX+2.0*XTickDelta));
        m_XTickText2 = new Text(String.format("%.2f", m_MinX-10/m_FactorX+XTickDelta));
        m_XTickText1 = new Text(String.format("%.2f", m_MinX-10/m_FactorX));
    }

    private void setXTicksTextPosition()
    {
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
    }

    private void setXTicksTextFont()
    {
        m_XTickText1.setFont(new Font(m_FontSizeTickLabels));
        m_XTickText2.setFont(new Font(m_FontSizeTickLabels));
        m_XTickText3.setFont(new Font(m_FontSizeTickLabels));
        m_XTickText4.setFont(new Font(m_FontSizeTickLabels));
        m_XTickText5.setFont(new Font(m_FontSizeTickLabels));
    }

    private void setXTicksLabelBox()
    {
        m_XTicksLabelBox = new HBox(m_XTickText1, m_XTicksLabelHorizontalSpacer1, m_XTickText2, m_XTicksLabelHorizontalSpacer2,m_XTickText3,m_XTicksLabelHorizontalSpacer3, m_XTickText4, m_XTicksLabelHorizontalSpacer4, m_XTickText5);
        m_XTicksLabelBox.setAlignment(Pos.CENTER_RIGHT);
        m_XTicksLabelBox.setAlignment(Pos.TOP_RIGHT);
    }
    
    private void initXTicksSpacers()
    {
        m_XTicksLabelHorizontalSpacer1 = new Region();
        m_XTicksLabelHorizontalSpacer2 = new Region();
        m_XTicksLabelHorizontalSpacer3 = new Region();
        m_XTicksLabelHorizontalSpacer4 = new Region();
        HBox.setHgrow(m_XTicksLabelHorizontalSpacer1, Priority.ALWAYS);
        HBox.setHgrow(m_XTicksLabelHorizontalSpacer2, Priority.ALWAYS);
        HBox.setHgrow(m_XTicksLabelHorizontalSpacer3, Priority.ALWAYS);
        HBox.setHgrow(m_XTicksLabelHorizontalSpacer4, Priority.ALWAYS);
    }

    private void initXTicksDrawing()
    {
        m_XTick1 = new Line();
        m_XTick2 = new Line();
        m_XTick3 = new Line();
        m_XTick4 = new Line();
        m_XTick5 = new Line();
    }

    private void setXTickDrawingLength()
    {
        m_XTick1.setEndY(3.0);
        m_XTick2.setEndY(3.0);
        m_XTick3.setEndY(3.0);
        m_XTick4.setEndY(3.0);
        m_XTick5.setEndY(3.0);
    }

    private void setXTickDrawingWidth()
    {
        m_XTick1.setStrokeWidth(2);
        m_XTick2.setStrokeWidth(2);
        m_XTick3.setStrokeWidth(2);
        m_XTick4.setStrokeWidth(2);
        m_XTick5.setStrokeWidth(2);
    }

    private void initXTicksBox()
    {
        m_XTicksBox = new HBox(m_XTick1, m_XTicksHorizonatlSpacer1, m_XTick2, m_XTicksHorizontalSpacer2, m_XTick3, m_XTicksHorizontalSpacer3, m_XTick4, m_XTicksHorizontalSpacer4,m_XTick5);
    }


    private void initColorBar()
    {
        initColorBarSpacers();
        setColorBarTicksText();
        setColorBarTicksTextPosition();
        setColorBarTicksTextFont();
        setColorBarTicksLabelBox();
        initColorBarGradient();
        drawColorBar();
        intiColorBarSpacer();
    }
    
    private void initColorBarSpacers()
    {
        m_CBTicksLabelVerticalSpacer1 = new Region();
        m_CBTicksLabelVerticalSpacer2 = new Region();
        m_CBTicksLabelVerticalSpacer3 = new Region();
        m_CBTicksLabelVerticalSpacer4 = new Region();
        VBox.setVgrow(m_CBTicksLabelVerticalSpacer1, Priority.ALWAYS);
        VBox.setVgrow(m_CBTicksLabelVerticalSpacer2, Priority.ALWAYS);
        VBox.setVgrow(m_CBTicksLabelVerticalSpacer3, Priority.ALWAYS);
        VBox.setVgrow(m_CBTicksLabelVerticalSpacer4, Priority.ALWAYS);
    }

    private void setColorBarTicksText()
    {
        double CBTickDelta = Math.abs(m_MaxVecLen-m_MinVecLen)/4.0;
        m_CBTickText1 = new Text(String.format("%.6f", m_MaxVecLen));
        m_CBTickText2 = new Text(String.format("%.6f", m_MinVecLen+3.0*CBTickDelta));
        m_CBTickText3 = new Text(String.format("%.6f", m_MinVecLen+2.0*CBTickDelta));
        m_CBTickText4 = new Text(String.format("%.6f", m_MinVecLen+CBTickDelta));
        m_CBTickText5 = new Text(String.format("%.6f", m_MinVecLen));
    }

    private void setColorBarTicksTextPosition()
    {
        m_CBTickText1.setTranslateY(-0.4*m_FontSizeTickLabels);
        m_CBTickText2.setTranslateY(-0.2*m_FontSizeTickLabels);
        m_CBTickText3.setTranslateY(-0.1*m_FontSizeTickLabels);
        m_CBTickText4.setTranslateY(0.2*m_FontSizeTickLabels);
        m_CBTickText5.setTranslateY(0.4*m_FontSizeTickLabels);
    }

    private void setColorBarTicksTextFont()
    {
        m_CBTickText1.setFont(new Font(m_FontSizeTickLabels));
        m_CBTickText2.setFont(new Font(m_FontSizeTickLabels));
        m_CBTickText3.setFont(new Font(m_FontSizeTickLabels));
        m_CBTickText4.setFont(new Font(m_FontSizeTickLabels));
        m_CBTickText5.setFont(new Font(m_FontSizeTickLabels));
    }

    private void setColorBarTicksLabelBox()
    {
        m_CBTicksLabelBox = new VBox(m_CBTickText1, m_CBTicksLabelVerticalSpacer1, m_CBTickText2, m_CBTicksLabelVerticalSpacer2,m_CBTickText3,m_CBTicksLabelVerticalSpacer3, m_CBTickText4, m_CBTicksLabelVerticalSpacer4, m_CBTickText5);
        m_CBTicksLabelBox.setAlignment(Pos.CENTER_RIGHT);
        m_CBTicksLabelBox.setAlignment(Pos.TOP_RIGHT);
    }

    private void initColorBarGradient()
    {
        m_GradientStops = new Stop[] { new Stop(0, Color.rgb(0,0,255)), new Stop(1, Color.rgb(255,0,0))};
        m_LinearGradient1 = new LinearGradient(0, 1, 0,0, true, CycleMethod.NO_CYCLE, m_GradientStops);
    }

    private void drawColorBar()
    {
        m_ColorBar = new Rectangle();
        m_ColorBar.setWidth(20);
        m_ColorBar.setHeight(0.6*m_Height);
        m_ColorBar.setStroke(Color.BLACK);
        m_ColorBar.setStrokeWidth(2);
        m_ColorBar.setFill(m_LinearGradient1);
    }

    private void intiColorBarSpacer()
    {
        m_SpacerPlotAreaColorBar = new Region();
        m_SpacerPlotAreaColorBar.setMinWidth(5.0);
    }
    

    private void initPlotAxesRectangle()
    {
        drawAxisRectangle();
        putAxisRectangleIntoPlottingArea();
    }

    private void drawAxisRectangle()
    {
        m_PlotAxesRectangle = new Rectangle();
        m_PlotAxesRectangle.setStroke(Color.BLACK);
        m_PlotAxesRectangle.setOpacity(0.8);
        m_PlotAxesRectangle.setStrokeWidth(2);
        m_PlotAxesRectangle.setFill(null);
        m_PlotAxesRectangle.setWidth(0.6*m_Width);  
        m_PlotAxesRectangle.setHeight(0.6*m_Height);
    }

    private void putAxisRectangleIntoPlottingArea()
    {
        m_PlotingArea = new StackPane();
        m_Arrows = new Group();
        m_Arrows.getChildren().add(m_PlotAxesRectangle);
        m_PlotingArea.getChildren().add(m_PlotAxesRectangle);
        m_PlotingArea.getChildren().add(m_Arrows);
    }
    
    private void transformVectorFieldData()
    {
        m_FactorX = (m_PlotAxesRectangle.getWidth()-20)/(m_MaxX-m_MinX);
        m_FactorY = (m_PlotAxesRectangle.getHeight()-20)/(m_MaxY-m_MinY);
        m_MaxVecLenTransformed = -Double.MAX_VALUE;
        m_MinVecLenTransformed = Double.MAX_VALUE;

        if(m_VectorFieldData != null)
        {
            if(m_VectorFieldDataTransformed!=null)
            {
                m_VectorFieldDataTransformed.clear();
                m_VectorFieldDataTransformed = null;
                m_VectorFieldDataTransformed = new ArrayList<Double[]>();
            }
            else
            {
                m_VectorFieldDataTransformed = new ArrayList<Double[]>();
            }
            for(var DE : m_VectorFieldData)
            {
                double TX1;
                double TY1;
                double TX2;
                double TY2;
    
                TX1 = (DE[0]-m_MinX)*m_FactorX;
                TX2 = (DE[2]-m_MinX)*m_FactorX;

                TY1 = (DE[1]-m_MinY)*m_FactorY;
                TY2 = (DE[3]-m_MinY)*m_FactorY;

                

                m_VectorFieldDataTransformed.add(new Double[]{TX1,TY1,TX2,TY2});
                double ActVecLen = Math.sqrt(Math.pow((TX2-TX1), 2)+Math.pow((TY2-TY1), 2));

                if(ActVecLen>m_MaxVecLenTransformed)
                {
                    m_MaxVecLenTransformed = ActVecLen;
                }
                if(ActVecLen<m_MinVecLenTransformed)
                {
                    m_MinVecLenTransformed = ActVecLen;
                }
            }
            
        }
        

    }
        
    
    public void plotVectorField()
    {
        adjustSpacersForPlotting();
        setPlotAxesRectangleAndColorBarDimensions();
        
        setXTicksText();
        setXTicksTextPosition();
        putXTicksTextToLabelBox();
        
        setYTicksText();
        setYTicksTextPosition();
        putYTicksTextToLabelBox();
        
        setColorBarTicksText();
        setColorBarTicksTextPosition();
        putColorBarTicksTextToLabelBox();
        
        transformVectorFieldData();
        drawVectors();
       
    }

    private void adjustSpacersForPlotting()
    {
        m_MainVerticalSpacerUpper.setMinHeight(0.15*m_Height);
        m_MainHorizontalSpacerLeft.setMinSize(0.05*m_Width, 0.6*m_Height);
        m_MainVerticalSpacerLower.setMinHeight(0.15*m_Height);
        m_MainHorizontalSpacerRight.setMinSize(0.05*m_Width, 0.6*m_Height);
    }

    private void setPlotAxesRectangleAndColorBarDimensions()
    {
        m_PlotAxesRectangle.setWidth(0.6*m_Width);  
        m_PlotAxesRectangle.setHeight(0.6*m_Height);
        m_ColorBar.setHeight(0.6*m_Height);
    }

    private void putXTicksTextToLabelBox()
    {
        m_XTicksLabelBox.getChildren().clear();
        
        m_XTicksLabelBox.getChildren().add(m_XTickText1);
        m_XTicksLabelBox.getChildren().add(m_XTicksLabelHorizontalSpacer1);
        m_XTicksLabelBox.getChildren().add(m_XTickText2);
        m_XTicksLabelBox.getChildren().add(m_XTicksLabelHorizontalSpacer2);
        m_XTicksLabelBox.getChildren().add(m_XTickText3);
        m_XTicksLabelBox.getChildren().add(m_XTicksLabelHorizontalSpacer3);
        m_XTicksLabelBox.getChildren().add(m_XTickText4);
        m_XTicksLabelBox.getChildren().add(m_XTicksLabelHorizontalSpacer4);
        m_XTicksLabelBox.getChildren().add(m_XTickText5);
    }

    private void putYTicksTextToLabelBox()
    {
        m_YTicksLabelBox.getChildren().clear();
        m_YTicksLabelBox.getChildren().add(m_YTickText1);
        m_YTicksLabelBox.getChildren().add(m_YTicksLabelVerticalSpacer1);
        m_YTicksLabelBox.getChildren().add(m_YTickText2);
        m_YTicksLabelBox.getChildren().add(m_YTicksLabelVerticalSpacer2);
        m_YTicksLabelBox.getChildren().add(m_YTickText3);
        m_YTicksLabelBox.getChildren().add(m_YTicksLabelVerticalSpacer3);
        m_YTicksLabelBox.getChildren().add(m_YTickText4);
        m_YTicksLabelBox.getChildren().add(m_YTicksLabelVerticalSpacer4);
        m_YTicksLabelBox.getChildren().add(m_YTickText5);
    }

    private void putColorBarTicksTextToLabelBox()
    {
        m_CBTicksLabelBox.getChildren().clear();
        m_CBTicksLabelBox.getChildren().add(m_CBTickText1);
        m_CBTicksLabelBox.getChildren().add(m_CBTicksLabelVerticalSpacer1);
        m_CBTicksLabelBox.getChildren().add(m_CBTickText2);
        m_CBTicksLabelBox.getChildren().add(m_CBTicksLabelVerticalSpacer2);
        m_CBTicksLabelBox.getChildren().add(m_CBTickText3);
        m_CBTicksLabelBox.getChildren().add(m_CBTicksLabelVerticalSpacer3);
        m_CBTicksLabelBox.getChildren().add(m_CBTickText4);
        m_CBTicksLabelBox.getChildren().add(m_CBTicksLabelVerticalSpacer4);
        m_CBTicksLabelBox.getChildren().add(m_CBTickText5);
    }

    private void drawVectors()
    {
        
        if(m_Arrows!=null)
        {
            m_Arrows.getChildren().clear();
        }
        
        for(var Elem : m_VectorFieldDataTransformed)
        {
            var ActVectorLength = Math.sqrt(Math.pow(((Elem[2]-Elem[0])),2)+Math.pow(((Elem[3]-Elem[1])),2));
            var DirectRatio = (ActVectorLength/m_MaxVecLenTransformed);
            
            var ReverseRatio = (m_MinVecLenTransformed/ActVectorLength);
            var arrow = new Arrow(new Point2D(Elem[0],Elem[1]), new Point2D(Elem[2],Elem[3]), Color.rgb((int)(DirectRatio*255),0,(int)(ReverseRatio*255)), 0.5); 
            m_Arrows.getChildren().add(arrow.createArrow());
            
        }
        
    }

    public void setFontSizeTickLabels(int FontSizeTickLabels)
    {
        m_FontSizeTickLabels = FontSizeTickLabels;
    }
    
    public void setFontSizeAxisLabels(int FontSizeAxisLabels)
    {
        m_FontSizeAxisLabels = FontSizeAxisLabels;
    }
    
    public void setScaleFactor(double ScaleFactor)
    {
        m_PrevVecDScale = m_VecDScale;
        m_VecDScale = ScaleFactor;
    }

    public void setXAxisName(String XAxisName)
    {
        m_XAxisName = XAxisName;
    }

    public void setYAxisName(String YAxisName)
    {
        m_YAxisName = YAxisName;
    }
   
    
    public void setVectorFieldData(ArrayList<Double[]> VectorFieldData)
    {
        if(m_VectorFieldData!=null)
        {
            m_VectorFieldData.clear();
        }
        m_VectorFieldData = null;
        m_VectorFieldData = VectorFieldData;
        
        refreshData(true);
    } 

    public void refreshData(boolean... setData )
    {
                
        initCoordinateRanges();
        
        for(var vectorElem : m_VectorFieldData)
        {
            if(setData.length == 0)
            {
                vectorElem[2] = vectorElem[0]+(vectorElem[2]-vectorElem[0])/m_PrevVecDScale;
                vectorElem[3] = vectorElem[1]+(vectorElem[3]-vectorElem[1])/m_PrevVecDScale;
            }
            calculateActualVectorLength(vectorElem);

            rescaleVectorElement(vectorElem);
            
        }
        recalculateFactors();
    }

    public void initCoordinateRanges()
    {
        m_MaxX = -Double.MAX_VALUE;
        m_MinX = Double.MAX_VALUE;
        m_MaxY = -Double.MAX_VALUE;
        m_MinY = Double.MAX_VALUE;

        m_MaxVecLen = Double.MIN_VALUE;
        m_MinVecLen = Double.MAX_VALUE;
    }

    private void calculateActualVectorLength(Double[] vectorElem)
    {
        m_ActVecLen = Math.sqrt(Math.pow((vectorElem[2]-vectorElem[0]), 2)+Math.pow((vectorElem[3]-vectorElem[1]), 2));

        if(m_ActVecLen>m_MaxVecLen)
        {
            m_MaxVecLen = m_ActVecLen;
        }
        if(m_ActVecLen<m_MinVecLen)
        {
            m_MinVecLen = m_ActVecLen;
        }
    }

    private void rescaleVectorElement(Double[] vectorElem)
    {
        vectorElem[2] = vectorElem[0]+(vectorElem[2]-vectorElem[0])*m_VecDScale;
        vectorElem[3] = vectorElem[1]+(vectorElem[3]-vectorElem[1])*m_VecDScale;

        if(vectorElem[0]>m_MaxX)
        {
            m_MaxX = vectorElem[0];
        }
        if(vectorElem[0]<m_MinX)
        {
            m_MinX = vectorElem[0];
        }
        if(vectorElem[1]>m_MaxY)
        {
            m_MaxY = vectorElem[1];
        }
        if(vectorElem[1]<m_MinY)
        {
            m_MinY = vectorElem[1];
        }

        if(vectorElem[2]>m_MaxX)
        {
            m_MaxX = vectorElem[2];
        }
        if(vectorElem[2]<m_MinX)
        {
            m_MinX = vectorElem[2];
        }
        if(vectorElem[3]>m_MaxY)
        {
            m_MaxY = vectorElem[3];
        }
        if(vectorElem[3]<m_MinY)
        {
            m_MinY = vectorElem[3];
        }
    }    

    private void recalculateFactors()
    {
        m_FactorX = (m_PlotAxesRectangle.getWidth()-20)/(m_MaxX-m_MinX);
        m_FactorY = (m_PlotAxesRectangle.getHeight()-20)/(m_MaxY-m_MinY);
    }


    public ArrayList<Double[]> getData()
    {
        return m_VectorFieldData;
    }
    
    public void setWidth(double Width)
    {
        m_Width = Width;
    }

    public void setHeight(double Height)
    {
        m_Height = Height;
    }

        
}