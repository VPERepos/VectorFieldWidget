package com.personal.LearningJavaFX;

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
    private int m_Panewidth;
    private int m_PaneHeight;
    
    private double MaxX;
    private double MinX;
    private double MaxY;
    private double MinY;
    
    private double MaxDX;
    private double MinDX;
    private double MaxDY;
    private double MinDY;

    private double MaxVecDX;
    private double MinVecDX;
    private double MaxVecDY;
    private double MinVecDY;

    private double VecDScale;

    public void VectorfieldWidget(Pane ParrentPane)
    {
        m_ParrentPane = ParrentPane;
    };
}