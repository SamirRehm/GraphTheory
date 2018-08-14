package graphtheory;

import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Vector;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.util.Pair;

public class GroupAndData {

    public enum Mode {
        VertexMode,
        EdgeMode
    }
    private Group g;
    private Vector<Anchor> vertices;
    private Mode mode;

    public GroupAndData() {
        g = new Group();
        vertices = new Vector<>();
        mode = Mode.VertexMode;
    }

    public Group getGroup() {
        return g;
    }

    public void setMode(Mode m) {
        mode = m;
        Anchor.currVert = null;
    }

    public Mode getMode() {
        return mode;
    }

    public void addVertex(Anchor a) {
        vertices.add(a);
    }

    public void createBipartition() {
        LinkedList<Anchor> queue = new LinkedList();
        ArrayList<Pair<Integer, Anchor>> parents = new ArrayList<Pair<Integer, Anchor>>();
        resetVertices();

        Integer currentParent = -1;
        int currIndex = 1;
        Hashtable<Anchor, Integer> ht = new Hashtable<Anchor, Integer>();
        vertices.get(0).setColour(Color.BLUE);
        queue.add(vertices.get(0));
        parents.add(new Pair(currentParent, vertices.get(0)));

        while (!queue.isEmpty()) {
            currentParent++;
            Anchor curr = queue.pop();
            for (int i = 0; i < curr.edges.size(); ++i) {
                if (curr.edges.get(i).getStroke() == curr.getStroke()) {
                    Integer index = ht.get(curr);
                    Integer currParent = ht.get(curr);
                    Integer otherCurrParent = ht.get(curr.edges.get(i));
                    while(!Objects.equals(currParent, otherCurrParent)) {
                        parents.get(currParent).getValue().setColour(Color.YELLOW);
                        parents.get(otherCurrParent).getValue().setColour(Color.YELLOW);
                        currParent = parents.get(currParent).getKey();
                        otherCurrParent = parents.get(otherCurrParent).getKey();
                    }
                    parents.get(currParent).getValue().setColour(Color.YELLOW);
                    throw new EmptyStackException();
                } else if (curr.edges.get(i).getStroke() == Color.BLACK) {
                    if (curr.getStroke() == Color.BLUE) {
                        curr.edges.get(i).setColour(Color.RED);
                    } else {
                        curr.edges.get(i).setColour(Color.BLUE);
                    }
                    queue.add(curr.edges.get(i));
                    parents.add(new Pair(currentParent, curr.edges.get(i)));
                    ht.put(curr.edges.get(i), currIndex);
                    currIndex++;
                }
            }
        }
    }

    public void resetVertices() {
        for (Anchor i : vertices) {
            if(i.getStroke() != Color.YELLOW) {
                i.setColour(Color.BLACK);
            }
        }
    }
}
