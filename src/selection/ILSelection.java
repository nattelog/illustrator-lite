package selection;

import canvas.ILVectorException;
import canvas.ILVectorList;
import vector.ResizeBox;
import vector.SelectionBox;
import vector.Text;
import vector.Vector;
import vector.VectorType;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nattelog on 15-09-11.
 */
public class ILSelection
{
    /* The border around the selected vector. */
    private Vector selectionBorder;

    /* The small box at the corner of the selected vector. */
    private Vector resizeBox;

    /* The index of the selected vector. If no vectoris selected, this field
    is -1. */
    private int vectorIndex;

    private List<SelectionListener> selectionListeners;
    private ILVectorList vectorList;

    public ILSelection(final ILVectorList vectorList) {
	selectionBorder = new SelectionBox();
	resizeBox = new ResizeBox();
	vectorIndex = -1;
	selectionListeners = new ArrayList<>();
	this.vectorList = vectorList;
    }

    public void setVectorList(final ILVectorList vectorList)
    {
	this.vectorList = vectorList;
    }

    public Vector getVector() throws ILVectorException {
	return vectorList.get(vectorIndex);
    }

    public Vector getSelectionBorder() {
	return selectionBorder;
    }

    public Vector getResizeBox() {
	return resizeBox;
    }

    public void duplicate() throws ILVectorException {
	Vector oldVector = getVector();
	int oldVectorIndex = vectorIndex;
	vectorList.duplicate(vectorIndex);
	Vector newVector = vectorList.getLast();
	int newX = oldVector.getX() + oldVector.getWidth();
	int newY = oldVector.getY();
	newVector.move(newX, newY);
	select(oldVectorIndex);
    }

    public void delete() throws ILVectorException {
	vectorList.remove(vectorIndex);
	deselect();
    }

    public void move(final int x, final int y) throws ILVectorException {
	getVector().move(x, y);
	notifyVectorChanged();
    }

    public void resize(final int width, final int height) throws ILVectorException {
	getVector().resize(width, height);
	notifyVectorChanged();
    }

    public void resize(final int fontSize) throws ILVectorException {
	Vector v = getVector();

	if (v.getType() != VectorType.TEXT) {
	    throw new ILVectorException("Can't resize font on a non-text vector.");

	} else {
	    Text t = (Text) v;
	    t.setSize(fontSize);
	    notifyVectorChanged();
	}
    }

    public void changeFillColor(final Color color) throws ILVectorException {
        getVector().setFillColor(color);
        notifyVectorChanged();
    }

    public void select(final int x, final int y) {
	vectorIndex = vectorList.getIndex(x, y);
	if (isActive()) {
	    notifySelectionActivated();

	} else {
	    notifySelectionDeactivated();
	}
    }

    public void select(final int index) {
        vectorIndex = index;
	notifySelectionActivated();
    }

    public void deselect() {
	vectorIndex = -1;
	notifySelectionDeactivated();
    }

    public boolean isActive() {
	return vectorIndex != -1;
    }

    public void addListener(final SelectionListener listener) {
	selectionListeners.add(listener);
    }

    private void notifyVectorChanged() {
	selectionListeners.forEach(SelectionListener::vectorChanged);
    }

    private void notifySelectionActivated() {
	selectionListeners.forEach(SelectionListener::selectionActivated);
    }

    private void notifySelectionDeactivated() {
	selectionListeners.forEach(SelectionListener::selectionDeactivated);
    }

    // Makes theselectionBox fit on top of the selected vector.
    private void fitSelectionBox() throws ILVectorException {
	selectionBorder.move(getVector().getX(), getVector().getY());
	selectionBorder.resize(getVector().getWidth(), getVector().getHeight());
    }

    // Moves the resizeBox to the bottom right corner of the selectionBorder.
    private void moveResizeBox() {
	int targetX = selectionBorder.getX() + selectionBorder.getWidth() - (resizeBox.getWidth() / 2);
	int targetY = selectionBorder.getY() + selectionBorder.getHeight() - (resizeBox.getHeight() / 2);
	resizeBox.move(targetX, targetY);
    }

    public void draw(final Graphics g) {
	try {
	    fitSelectionBox();
	    moveResizeBox();
	    selectionBorder.draw(g);
	    resizeBox.draw(g);

	} catch (ILVectorException e) {
	    System.out.println("Could not draw selection: " + e.getMessage());
	}
    }
}
