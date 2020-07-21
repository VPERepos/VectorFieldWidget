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


public class Arrow extends Group {

        private final Line line;
    
        public Arrow() {
            this(new Line(), new Line(), new Line());
        }
    
        private static final double arrowLength = 20;
        private static final double arrowWidth = 7;
        
        private Arrow(Line line, Line arrow1, Line arrow2) {
            super(line, arrow1, arrow2);
            this.line = line;
            this.line.setOpacity(0.6);
            this.line.setStrokeWidth(2);
            this.line.setStroke(Color.rgb(50, 100, 50));
            arrow1.setStrokeWidth(2);
            arrow2.setStrokeWidth(2);
            arrow1.setOpacity(0.6);
            arrow2.setOpacity(0.6);
            InvalidationListener updater = o -> {
                double ex = getEndX();
                double ey = getEndY();
                double sx = getStartX();
                double sy = getStartY();
    
                arrow1.setEndX(ex);
                arrow1.setEndY(ey);
                arrow2.setEndX(ex);
                arrow2.setEndY(ey);
    
                if (ex == sx && ey == sy) {
                    // arrow parts of length 0
                    arrow1.setStartX(ex);
                    arrow1.setStartY(ey);
                    arrow2.setStartX(ex);
                    arrow2.setStartY(ey);
                } else {
                    double factor = arrowLength / Math.hypot(sx-ex, sy-ey);
                    double factorO = arrowWidth / Math.hypot(sx-ex, sy-ey);
    
                    // part in direction of main line
                    double dx = (sx - ex) * factor*0.5;
                    double dy = (sy - ey) * factor*0.5;
    
                    // part ortogonal to main line
                    double ox = (sx - ex) * factorO*0.4;
                    double oy = (sy - ey) * factorO*0.4;
    
                    arrow1.setStartX(ex + dx - oy);
                    arrow1.setStartY(ey + dy + ox);
                    arrow2.setStartX(ex + dx + oy);
                    arrow2.setStartY(ey + dy - ox);
                }
            };
    
            // add updater to properties
            startXProperty().addListener(updater);
            startYProperty().addListener(updater);
            endXProperty().addListener(updater);
            endYProperty().addListener(updater);
            updater.invalidated(null);
        }
    
        // start/end properties
    
        public final void setStartX(double value) {
            line.setStartX(value);
        }
    
        public final double getStartX() {
            return line.getStartX();
        }
    
        public final DoubleProperty startXProperty() {
            return line.startXProperty();
        }
    
        public final void setStartY(double value) {
            line.setStartY(value);
        }
    
        public final double getStartY() {
            return line.getStartY();
        }
    
        public final DoubleProperty startYProperty() {
            return line.startYProperty();
        }
    
        public final void setEndX(double value) {
            line.setEndX(value);
        }
    
        public final double getEndX() {
            return line.getEndX();
        }
    
        public final DoubleProperty endXProperty() {
            return line.endXProperty();
        }
    
        public final void setEndY(double value) {
            line.setEndY(value);
        }
    
        public final double getEndY() {
            return line.getEndY();
        }
    
        public final DoubleProperty endYProperty() {
            return line.endYProperty();
        }
    
    }