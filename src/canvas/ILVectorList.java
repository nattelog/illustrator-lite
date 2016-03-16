package canvas;

import vector.*;
import vector.Rectangle;
import vector.Vector;

import java.util.*;
import java.awt.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by nattelog on 15-09-11.
 */

public class ILVectorList
{
    private final static Logger LOGGER = Logger.getLogger(ILVectorList.class.getName());

    private final List<Vector> vectorList = new ArrayList<>();

    public List<Vector> getVectorList(){
	return vectorList;
    }

    public void add(final Vector vector){
	LOGGER.log(Level.INFO, "Added vector: " + vector);
	vectorList.add(vector);
    }

    public void remove(final int index) throws ILVectorException {
	vectorExist(index);
	LOGGER.log(Level.INFO, "Removing vector on index " + index);
	vectorList.remove(index);
    }

    public Vector get(final int index) throws ILVectorException {
	vectorExist(index);
	return vectorList.get(index);
    }

    // Returns the index of a vector at position (x, y).
    // If no vector was found, returns -1.
    public int getIndex(final int x, final int y) {
	for (int index = getLastIndex(); index >= 0; index--)
	    if (vectorList.get(index).contains(x, y))
		return index;
	return -1;
    }

    // Creates a duplicate of the vector at index.
    public void duplicate(final int index) throws ILVectorException {

	vectorExist(index);

	Vector newVector, oldVector = vectorList.get(index);
	int x = oldVector.getX();
	int y = oldVector.getY();
	int width = oldVector.getWidth();
	int height = oldVector.getHeight();
	Color color = oldVector.getFillColor();

	switch (oldVector.getType()) {
	    case ELLIPSE:
		newVector = new Ellipse(x, y, width, height);
		newVector.setFillColor(color);
		break;
	    case RECTANGLE:
		newVector = new Rectangle(x, y, width, height);
		newVector.setFillColor(color);
		break;
	    case TEXT:
		Text text = new Text(x, y), oldText = (Text) vectorList.get(index);
		float size = oldText.getSize();
		text.setFillColor(color);
		text.setSize(size);
		//text.resize(width, height);
		text.setValue(oldText.getValue());
		vectorList.add(text);
		return;
	    case UNDEFINED:
	    default:
		throw new ILVectorException("Vector-type undefined.");
	   }

	LOGGER.log(Level.INFO, "Duplicated vector: " + newVector);
	vectorList.add(newVector);
    }

    public Vector getLast(){
	int lastIndex = getLastIndex();
	return vectorList.get(lastIndex);
    }

    public int getLastIndex(){
	return vectorList.size() - 1;
    }

    @Override public String toString() {
	StringBuilder result = new StringBuilder("List of vectors:\n");
	for (Vector vector : vectorList) {
	    result.append(vector).append("\n");
	}
	return result.toString();
    }

    private void vectorExist(final int index) throws ILVectorException {
	if (!(index >= 0 && index <= getLastIndex())) {
	    throw new ILVectorException("Vector don't exist.");
	}
    }

    public void clear() {
	vectorList.clear();
    }
}
