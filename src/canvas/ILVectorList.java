package canvas;

import vector.*;
import vector.Rectangle;

import java.util.ArrayList;
import java.awt.*;

/**
 * Created by nattelog on 15-09-11.
 */

// Code analyzing says this extension should be remove. Not sure why.
public class ILVectorList extends ArrayList<Vector>
{
    // Returns the index of a vector at position (x, y).
    // If no vector was found, returns -1.
    public int getIndex(final int x, final int y) {
	for (int index = size() - 1; index >= 0; index--)
	    if (get(index).contains(x, y))
		return index;
	return -1;
    }

    // Creates a duplicate of the vector at index.
    public boolean duplicate(final int index){
	Vector newVector, oldVector = get(index);
	int x = oldVector.getX();
	int y = oldVector.getY();
	int width = oldVector.getWidth();
	int height = oldVector.getHeight();
	Color color = oldVector.getFillColor();

	switch(oldVector.getType()) {
	    case ELLIPSE:
		newVector = new Ellipse(x, y, width, height);
		newVector.setFillColor(color);
		break;
	    case RECTANGLE:
		newVector = new Rectangle(x, y, width, height);
		newVector.setFillColor(color);
		break;
	    case TEXT:
		Text text = new Text(x, y), oldText = (Text) get(index);
		int size = oldText.getSize();
		text.setFillColor(color);
		text.setSize(size);
		text.resize(width, height);
		return add(text);
	    case UNDEFINED:
	    default:
		return false;
	}
	return add(newVector);
    }

    public Vector getLast(){
	int lastIndex = getLastIndex();
	return get(lastIndex);
    }

    public int getLastIndex(){
	return size() - 1;
    }

    // Used for debug.
    public void print(){
	for (Vector vector : this)
	    System.out.print(vector + "\n");
    }
}
