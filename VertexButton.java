/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheory;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

/**
 *
 * @author Samir Rehmtulla
 */
public class VertexButton extends Button {
    private GroupAndData group;
    VertexButton(GroupAndData g) {
        super("Add Vertex");
        group = g;
        setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            Anchor newAnch = new Anchor(Color.BLACK, new SimpleDoubleProperty(20), new SimpleDoubleProperty(20), group);
            g.getGroup().getChildren().add(newAnch);
            g.addVertex(newAnch);
    }
});
}
}
