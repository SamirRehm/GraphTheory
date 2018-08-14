package graphtheory;

import java.util.Vector;
import javafx.beans.property.DoubleProperty;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;

class Anchor extends Circle {
    
    public static Anchor currVert;

    private GroupAndData group;
    public Vector<Anchor> edges;
    DoubleProperty xCent;
    DoubleProperty yCent;

    Anchor(Color color, DoubleProperty x, DoubleProperty y, GroupAndData groupParam) {
        super(x.get(), y.get(), 10);
        group = groupParam;
        setFill(color.deriveColor(1, 1, 1, 0.5));
        setStroke(color);
        setStrokeWidth(2);
        setStrokeType(StrokeType.OUTSIDE);

        x.bind(centerXProperty());
        y.bind(centerYProperty());
        xCent = x;
        yCent = y;
        enableDrag();
        currVert = null;
        edges = new Vector<Anchor>();
    }

    // make a node movable by dragging it around with the mouse.
    private void enableDrag() {
        final Delta dragDelta = new Delta();
        Anchor anch = this;
        setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (group.getMode() != GroupAndData.Mode.EdgeMode) {
                    dragDelta.x = getCenterX() - mouseEvent.getX();
                    dragDelta.y = getCenterY() - mouseEvent.getY();
                    getScene().setCursor(Cursor.MOVE);
                } else {
                    if (Anchor.currVert == null) {
                        currVert = anch;
                    } else {
                        anch.edges.add(Anchor.currVert);
                        Anchor.currVert.edges.add(anch);
                        anch.group.getGroup().getChildren().add(new BoundLine(currVert.xCent, currVert.yCent, anch.xCent, anch.yCent));
                        currVert=null;
                    }
                }
            }
        });
        setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                getScene().setCursor(Cursor.HAND);
            }
        });
        setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (group.getMode() != GroupAndData.Mode.EdgeMode) {
                    double newX = mouseEvent.getX() + dragDelta.x;
                    if (newX > 0 && newX < getScene().getWidth()) {
                        setCenterX(newX);
                    }
                    double newY = mouseEvent.getY() + dragDelta.y;
                    if (newY > 0 && newY < getScene().getHeight()) {
                        setCenterY(newY);
                    }
                }
            }
        });
        setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    getScene().setCursor(Cursor.HAND);
                }
            }
        });
        setOnMouseExited(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (!mouseEvent.isPrimaryButtonDown()) {
                    getScene().setCursor(Cursor.DEFAULT);
                }
            }
        });
    }
    
    public void setColour(Color color) {
        setFill(color.deriveColor(1, 1, 1, 0.5));
        setStroke(color);
        setStrokeWidth(2);
        setStrokeType(StrokeType.OUTSIDE);
    }
    
    public Color getColor() {
        return (Color)getFill();
    }
    
    private class Delta {
        double x, y;
    }
}
