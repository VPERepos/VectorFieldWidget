package com.personal.LearningJavaFX;

import javafx.application.*;
import javafx.beans.InvalidationListener;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Point2D;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;
import javafx.stage.*;

public class Arrow extends Group {

    private Line m_Middle;
    private Polygon m_Triangle;

    public Arrow(Point2D Start, Point2D End, Paint Color, double Opacity)
    {
        m_Middle = new Line();
        m_Middle.setStrokeWidth(1); 
        m_Middle.setStartX(Start.getX()); 
        m_Middle.setStartY(Start.getY());
        m_Middle.setEndX(End.getX()); 
        m_Middle.setEndY(End.getY());
        m_Middle.setStroke(Color);
        m_Middle.setOpacity(Opacity);

        m_Triangle = new Polygon();
        var VectorDir = 0.0;
        if(End.getX()-Start.getX()< 0.00000000000001)
        {
            VectorDir = 0.5*Math.PI;
        }
        else
        {
            VectorDir = Math.atan((Start.getY()-End.getY())/(Start.getX()-End.getX()));
        }
        m_Triangle.getPoints().addAll(new Double[]{
            End.getX(), End.getY(),
            End.getX()+10.0*Math.cos(-VectorDir-0.3-0.5*Math.PI), End.getX()+10.0*Math.sin(-VectorDir-0.3-0.5*Math.PI),
            End.getX()+10.0*Math.cos(-VectorDir+0.3-0.5*Math.PI), End.getX()+10.0*Math.sin(-VectorDir+0.3-0.5*Math.PI),
            End.getX(), End.getY()});
        m_Triangle.setStroke(Color);
        m_Triangle.setFill(Color);
    }

    public Group CreateArrow()
    {
        return new Group(m_Middle,m_Triangle);
    }
}