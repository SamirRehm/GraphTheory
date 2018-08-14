package graphtheory;

import javafx.application.Application;
import javafx.beans.property.*;
import javafx.scene.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;
import javafx.stage.Stage;

/** Example of dragging anchors around to manipulate a line. */
public class GraphTheory extends Application {
  public static void main(String[] args) throws Exception { launch(args); }
  @Override public void start(final Stage stage) throws Exception {
    GroupAndData group = new GroupAndData();
    VertexButton addVertex = new VertexButton(group);
    EdgeButton edgeMode = new EdgeButton(group);
    BipartitionButton bp = new BipartitionButton(group);
    edgeMode.setTranslateX(75);
    bp.setTranslateX(150);
    
    stage.setTitle("Line Manipulation Sample");
    group.getGroup().getChildren().addAll(addVertex, edgeMode, bp);
    stage.setScene(new Scene(group.getGroup(), 400, 400, Color.ALICEBLUE));
    stage.show();
  } 
}