package canvas;

import vector.Vector;
import util.ILView;

import java.awt.*;

/**
 * Created by nattelog on 15-09-11.
 */
public class ILCanvas extends ILView implements Canvas, SelectionListener
{
    private ILVectorList vectorList;
    private ILSelection selection;
    private ClickMode clickMode;

    public ILCanvas() {
	vectorList = new ILVectorList();
	selection = new ILSelection(vectorList);
	clickMode = ClickMode.SELECT;
	selection.addListener(this);
	initMouseAdapter(new ILCanvasMouseAdapter(this));
    }

    private void initMouseAdapter(final ILCanvasMouseAdapter mouseAdapter){
	addMouseListener(mouseAdapter);
	addMouseMotionListener(mouseAdapter);
    }

    @Override public void addVector(final Vector vector) {
	vectorList.add(vector);
        selection.select(vectorList.getLastIndex());
	repaint();
    }

    @Override public Vector getVector(final int index) {
	return vectorList.get(index);
    }

    @Override public Selection getSelection() {
	return selection;
    }

    @Override public ClickMode getClickMode() {
	return clickMode;
    }

    @Override public void setClickMode(final ClickMode clickMode) {
	this.clickMode = clickMode;
    }

    @Override public void saveCanvas() {

    }

    @Override public void openCanvas() {

    }

    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	for (Vector vector : vectorList)
	    vector.draw(g);
	if (selection.isActive())
	    selection.draw(g);
    }

    @Override public void selectionChanged() {
	repaint();
    }
}
