package canvas;

import vector.ResizeBox;
import vector.SelectionBox;
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
    private Vector selectionBox, resizeBox;
    private int vectorIndex;
    private List<SelectionListener> selectionListeners;
    private ILVectorList vectorList;

    public ILSelection(final ILVectorList vectorList) {
	selectionBox = new SelectionBox();
	resizeBox = new ResizeBox();
	vectorIndex = -1;
	selectionListeners = new ArrayList<>();
	this.vectorList = vectorList;
    }

    public Vector getVector() {
	return vectorList.get(vectorIndex);
    }

    public VectorType getType() {
        if (!isActive())
            return VectorType.UNDEFINED;
        else
            return getVector().getType();
    }

    public Vector getSelectionBox() {
	return selectionBox;
    }

    public Vector getResizeBox() {
	return resizeBox;
    }

    public void duplicate() {
        Vector oldVector = getVector();
        int oldVectorIndex = vectorIndex;
        if (vectorList.duplicate(vectorIndex)) {
            Vector newVector = vectorList.getLast();
            int newX = oldVector.getX() + oldVector.getWidth();
            int newY = oldVector.getY();
            newVector.move(newX, newY);
            select(oldVectorIndex);
        }
    }

    public void delete() {
        vectorList.remove(vectorIndex);
        deselect();
    }

    public void move(final int x, final int y) {
	getVector().move(x, y);
	notifyListeners();
    }

    public void resize(final int width, final int height) {
	getVector().resize(width, height);
	notifyListeners();
    }

    public void select(final int x, final int y) {
	vectorIndex = vectorList.getIndex(x, y);
	notifyListeners();
    }

    public void changeFillColor(final Color color) {
        getVector().setFillColor(color);
        notifyListeners();
    }

    public void select(final int index) {
        vectorIndex = index;
        notifyListeners();
    }

    public void deselect() {
	vectorIndex = -1;
	notifyListeners();
    }

    public boolean isActive() {
	return vectorIndex != -1;
    }

    public void addListener(final SelectionListener listener) {
	selectionListeners.add(listener);
    }

    public void notifyListeners() {
	selectionListeners.forEach(SelectionListener::selectionChanged);
    }

    // Makes the selectionBox fit on top of the selected vector.
    private void fitSelectionBox(){
	selectionBox.move(getVector().getX(), getVector().getY());
	selectionBox.resize(getVector().getWidth(), getVector().getHeight());
    }

    // Moves the resizeBox to the bottom right corner of the selectionBox.
    private void moveResizeBox(){
	int targetX = selectionBox.getX() + selectionBox.getWidth() - (resizeBox.getWidth() / 2);
	int targetY = selectionBox.getY() + selectionBox.getHeight() - (resizeBox.getHeight() / 2);
	resizeBox.move(targetX, targetY);
    }

    public void draw(final Graphics g) {
	fitSelectionBox();
	moveResizeBox();
	selectionBox.draw(g);
	resizeBox.draw(g);
    }
}
