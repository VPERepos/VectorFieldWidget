package com.personal.LearningJavaFX;

import java.util.List;

import javafx.application.*;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Pos;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
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

    private double m_MaxVecDX;
    private double m_MinVecDX;
    private double m_MaxVecDY;
    private double m_MinVecDY;

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

    private List<Double[]> m_VectorFieldData;


    private Group m_Canvas;
    private Rectangle m_PlotAxesRectangle;
    
    private Rectangle m_ColorBar1;
    
    private VBox m_MainVerticalBox;
    private Region m_MainVerticalSpacerUpper;
    private Region m_MainVerticalSpacerLower;

    private Region m_MainHorizontalSpacerLeft;
    private Region m_MainVerticalSpacerRight;

    private HBox m_PlotAreaBox;
    private Region m_MostLeftSpacer;
    
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

    private VBox m_PlotAreaXAxisPlacer;

	public VectorfieldWidget(Pane ParrentPane)
    {
        m_MainVerticalSpacerUpper = new Region();
        m_MainVerticalSpacerLower = new Region();
        
        

        m_XAxisName = "X-Axis";
        m_YAxisName = "Y-Axis";
        m_PlotName = "Vector Field Plot";

        m_XAxisLabel = new Text(m_XAxisName);
        m_YAxisLabel = new Text(m_YAxisName);
        m_PlotNameLabel = new Text(m_PlotName);

        m_FontSizeTickLabels = 12;
        m_FontSizeAxisLabels = 14;
        m_FontSizePlotLabel = 18;

        m_XAxisLabel.setFont(new Font(m_FontSizeAxisLabels));
        m_YAxisLabel.setFont(new Font(m_FontSizeAxisLabels));
        m_YAxisLabel.setRotate(-90.0);
        
        m_PlotNameLabel.setFont(new Font(m_FontSizePlotLabel));

        m_YLabelVerticalSpacerUpper = new Region();
        
        m_YLabelVerticalSpacerLower = new Region();
        
        m_YLabelBox = new VBox(m_YLabelVerticalSpacerUpper, m_YAxisLabel, m_YLabelVerticalSpacerLower);
        VBox.setVgrow(m_YLabelVerticalSpacerUpper, Priority.ALWAYS);
        VBox.setVgrow(m_YLabelVerticalSpacerLower, Priority.ALWAYS);

        
        m_MaxX = 100.0;
        m_MinX = 0.0;
        m_MaxY = 1000.0;
        m_MinY = 0.0;

        m_MaxDX = 0.1;
        m_MinDX = 0.01;
        m_MaxDY = 0.1;
        m_MinDY = 0.01;

        m_MaxVecDX = 0.01;
        m_MinVecDX = 0.001;
        m_MaxVecDY = 0.01;
        m_MinVecDY = 0.001;

        m_VecDScale = 1.0;

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
        var TestWidth = 0.5*m_XTickText5.getText().length();
        m_XTickText1.setTranslateX(-0.5*TestWidth*m_FontSizeTickLabels);
        m_XTickText2.setTranslateX(-0.4*TestWidth*m_FontSizeTickLabels);
        m_XTickText3.setTranslateX(-0.1*TestWidth*m_FontSizeTickLabels);
        m_XTickText4.setTranslateX(0.2*TestWidth*m_FontSizeTickLabels);
        m_XTickText5.setTranslateX(0.4*TestWidth*m_FontSizeTickLabels);

        m_XTickText1.setFont(new Font(m_FontSizeTickLabels));
        m_XTickText2.setFont(new Font(m_FontSizeTickLabels));
        m_XTickText3.setFont(new Font(m_FontSizeTickLabels));
        m_XTickText4.setFont(new Font(m_FontSizeTickLabels));
        m_XTickText5.setFont(new Font(m_FontSizeTickLabels));

        m_XTicksLabelBox = new HBox(m_XTickText1, m_XTicksLabelHorizontalSpacer1, m_XTickText2, m_XTicksLabelHorizontalSpacer2,m_XTickText3,m_XTicksLabelHorizontalSpacer3, m_XTickText4, m_XTicksLabelHorizontalSpacer4, m_XTickText5);
        m_XTicksLabelBox.setAlignment(Pos.CENTER_RIGHT);
        m_XTicksLabelBox.setAlignment(Pos.TOP_RIGHT);

        m_ParrentPane = ParrentPane;
        m_Width = 500.0;
        m_Height = 375.0;

        m_Canvas = new Group();

        m_PlotAxesRectangle = new Rectangle();
        
        
        m_ColorBar1 = new Rectangle();
        m_ColorBar1.setWidth(20);
        m_ColorBar1.setStroke(Color.BLACK);
        m_ColorBar1.setStrokeWidth(2);
        m_ColorBar1.setFill(null);

        m_PlotAxesRectangle.setStroke(Color.BLACK);
        m_PlotAxesRectangle.setStrokeWidth(2);
        m_PlotAxesRectangle.setFill(null);
        
        m_MostLeftSpacer = new Region();
        m_MostLeftSpacer.setMinWidth(0.05*m_Width);
        HBox.setHgrow(m_MostLeftSpacer, Priority.ALWAYS);
        
        m_PlotAreaXAxisPlacer = new VBox(m_PlotAxesRectangle, m_XTicksBox);

        m_PlotAreaBox = new HBox(m_MostLeftSpacer,m_YLabelBox, m_YTicksLabelBox, m_YTicksBox, m_PlotAreaXAxisPlacer, m_ColorBar1);

        m_MainVerticalBox = new VBox(m_MainVerticalSpacerUpper,m_PlotAreaBox, m_MainVerticalSpacerLower);
        VBox.setVgrow(m_MainVerticalSpacerUpper, Priority.ALWAYS);
        VBox.setVgrow(m_MainVerticalSpacerLower, Priority.ALWAYS);


        GridPane grid = new GridPane();
        
        m_PlotLabelHorizontalSpacerLeft = new Region();
        m_PlotLabelHorizontalSpacerRight = new Region();
        m_PlotLabelBox = new HBox(m_PlotLabelHorizontalSpacerLeft, m_PlotNameLabel, m_PlotLabelHorizontalSpacerRight);
        HBox.setHgrow(m_PlotLabelHorizontalSpacerLeft, Priority.ALWAYS);
        HBox.setHgrow(m_PlotLabelHorizontalSpacerRight, Priority.ALWAYS);

        m_MainVerticalSpacerUpper = new Region();
        VBox.setVgrow(m_MainVerticalSpacerUpper, Priority.ALWAYS);
        
        m_MainHorizontalSpacerLeft = new Region();
        HBox.setHgrow(m_MainHorizontalSpacerLeft, Priority.ALWAYS);

        grid.add(m_MainVerticalSpacerUpper, 4, 0);
        grid.add(m_PlotLabelBox, 4, 1);
        grid.add(m_PlotAxesRectangle, 4, 2);
        grid.add(m_XTicksBox, 4, 3);
        grid.add(m_XTicksLabelBox, 4, 4);
        grid.add(m_YTicksBox, 3, 2);
        grid.add(m_YTicksLabelBox, 2, 2);
        grid.add(m_YLabelBox, 1, 2);
        grid.add(m_MainHorizontalSpacerLeft, 0, 2);
        
        
        m_Canvas.getChildren().add(grid);
        ParrentPane.getChildren().add(m_Canvas);
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

    public void SetVectorFieldData(List<Double[]> VectorFieldData)
    {
        m_VectorFieldData = VectorFieldData;
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

    public void PlotVectorField()
    {
        m_MainVerticalSpacerUpper.setMinHeight(0.1*m_Width);
        m_MainVerticalSpacerLower.setMinHeight(0.1*m_Width);
        m_PlotAxesRectangle.setWidth(0.6*m_Width);  
        m_PlotAxesRectangle.setHeight(0.6*m_Height);
        //m_ColorBar1.setWidth(0.05*m_Width);  
        m_ColorBar1.setHeight(0.6*m_Height);

        
        
    }
}