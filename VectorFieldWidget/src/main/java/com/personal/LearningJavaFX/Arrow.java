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
        
        double PointX1 = End.getX();
        double PointY1 = End.getY();
                
        double PointX2;
        double PointY2;
        
        double PointX3;
        double PointY3;
        
        if (End.getX() == Start.getX() && End.getY() == Start.getY()) 
        {
            // arrow parts of length 0
            PointX2 = End.getX();
            PointY2 = End.getY();
            PointX3 = End.getX();
            PointY3 = End.getY();
        } 
        else 
        {
            /*double factor = arrowLength / Math.hypot(sx-ex, sy-ey);
            double factorO = arrowWidth / Math.hypot(sx-ex, sy-ey);*/

            // part in direction of main line
            double dx = (Start.getX() - End.getX()) * 0.1;
            double dy = (Start.getY() - End.getY()) * 0.1;

            // part ortogonal to main line
            double ox = (Start.getX() - End.getX()) * 0.04;
            double oy = (Start.getY() - End.getY()) * 0.04;

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

    public Group CreateArrow()
    {
        return new Group(m_Middle,m_Triangle);
    }
}