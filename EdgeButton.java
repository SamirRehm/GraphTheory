/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheory;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.paint.Color;

/**
 *
 * @author Samir Rehmtulla
 */
public class EdgeButton extends ToggleButton {

    private GroupAndData group;

    EdgeButton(GroupAndData g) {
        super("Edge Mode");
        group = g;
        setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                if (g.getMode() == GroupAndData.Mode.VertexMode) {
                    g.setMode(GroupAndData.Mode.EdgeMode);
                } else {
                    g.setMode(GroupAndData.Mode.VertexMode);
                }
            }
        });
    }
    
}
