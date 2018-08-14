/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheory;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.paint.Color;

/**
 *
 * @author Samir Rehmtulla
 */
public class BipartitionButton extends Button {
    private GroupAndData group;

    public BipartitionButton(GroupAndData g) {
        super("Find bipartition");
        group = g;
        setOnAction(new EventHandler<ActionEvent>() {
        @Override public void handle(ActionEvent e) {
            try {
                group.createBipartition();
            } catch (Exception ex) {
                Alert alert = new Alert(Alert.AlertType.ERROR, "The graph is not bipartite. A cycle of odd length is highlighted in yellow.", ButtonType.OK);
                group.resetVertices();
                alert.showAndWait();
            }
        }
});
    }
    
}
