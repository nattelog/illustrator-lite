package modelview.canvas;

import modelview.ILDebug;
import modelview.vector.Vector;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nattelog on 15-08-28.
 */
public class ILCanvasVectorList
{
    private List<Vector> vectors;

    public ILCanvasVectorList() {
	vectors = new ArrayList<>();
    }

    public List<Vector> getVectors() {
	return vectors;
    }

    public void addVector(final Vector vector){
	vectors.add(0, vector);
    }

    public int findVector(final int x, final int y){
        int index = 0;
        for (Vector vector : vectors) {
            if (x >= vector.getX() && x < vector.getX() + vector.getSelectionWidth() &&
                    y >= vector.getY() && y < vector.getY() + vector.getSelectionHeight())
                return index;
            index++;
        }
        return -1;
    }

    public void deselectAll(){
        for (Vector vector : vectors)
            vector.deselect();
    }

    public void debugVectorList(){
        String debugString = "";

        if (vectors.isEmpty())
            debugString = "No vectors in canvas.";

        else {
            debugString = vectors.size() + " vectors in canvas\n";
            for (Vector v : vectors) {
                debugString += "  " + v.debug() + "\n";
            }
        }

        ILDebug.getInstance().msg(debugString);
    }
}
