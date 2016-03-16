package canvas;

import canvas.actor.CanvasActor;
import canvas.actor.SelectionActor;
import selection.ILSelection;
import selection.SelectionListener;
import vector.Vector;
import view.ILView;

import java.awt.*;

/**
 *  ILCanvas is the main container holding all vectors and the selection.
 */
public class ILCanvas extends ILView implements SelectionListener
{
    private ILVectorList vectorList;
    private ILSelection selection;
    private CanvasActor actor;

    public ILCanvas() {
	vectorList = new ILVectorList();
	selection = new ILSelection(vectorList);
        selection.addListener(this);
	initMouseAdapter(new ILCanvasMouseAdapter(this));
        actor = new SelectionActor();
    }

    private void initMouseAdapter(final ILCanvasMouseAdapter mouseAdapter){
	addMouseListener(mouseAdapter);
	addMouseMotionListener(mouseAdapter);
    }

    public void addVector(final Vector vector) {
	vectorList.add(vector);
	repaint();
    }

    public ILVectorList getVectorList() {
        return vectorList;
    }

    public void setVectorList(final ILVectorList vectorList)
    {
	this.vectorList = vectorList;
	selection.setVectorList(vectorList);
	repaint();
    }

    public ILSelection getSelection() {
	return selection;
    }

    public CanvasActor getActor() {
        return actor;
    }

    public void setActor(final CanvasActor actor) {
        this.actor = actor;
    }

    @Override protected void paintComponent(final Graphics g) {
	super.paintComponent(g);
	for (Vector vector : vectorList.getVectorList())
	    vector.draw(g);
	if (selection.isActive())
	    selection.draw(g);
    }

    @Override public void selectionActivated() {
	repaint();
    }

    @Override public void selectionDeactivated() {
	repaint();
    }

    @Override public void vectorChanged() {
	repaint();
    }

    public void clear() {
        vectorList.clear();
        selection.deselect();
        repaint();
    }
 }
