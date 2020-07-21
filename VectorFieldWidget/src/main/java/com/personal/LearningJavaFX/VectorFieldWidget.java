package com.personal.LearningJavaFX;

import java.util.List;

import javafx.application.*;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.*;



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

    private int m_FontSize;

    private String m_XAxisName;
    private String m_YAxisName;

    private List<Double[]> m_VectorFieldData;


    private Group m_Canvas;
    private Rectangle m_PlotAxesRectangle;
    

	public VectorfieldWidget(Pane ParrentPane, double Width, double Height)
    {
        m_ParrentPane = ParrentPane;
        m_Width = Width;
        m_Height = Height;

        m_Canvas = new Group();

        m_PlotAxesRectangle = new Rectangle(0.3*Width,0.3*Height,0.6*Width,0.6*Height);
        m_PlotAxesRectangle.setStroke(Color.BLACK);
        m_PlotAxesRectangle.setStrokeWidth(3);
        m_PlotAxesRectangle.setFill(null);
        m_Canvas.getChildren().add(m_PlotAxesRectangle);
        ParrentPane.getChildren().add(m_Canvas);
    }

    public void SetFontSize(int FontSize)
    {
        m_FontSize = FontSize;
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
        m_PlotAxesRectangle.setWidth(0.6*m_Width);  
        m_PlotAxesRectangle.setHeight(0.6*m_Height);
        m_PlotAxesRectangle.relocate(0.2*m_Width, 0.2*m_Height);
        
    }
}