package canvas;

import vector.Vector;
import util.ILView;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by nattelog on 15-09-11.
 */
public class ILCanvas extends ILView implements SelectionListener
{
    private final static Logger logger = Logger.getLogger(ILCanvas.class.getName());
    private ILVectorList vectorList;
    private ILSelection selection;
    private ClickMode clickMode;

    public ILCanvas() {
	vectorList = new ILVectorList();
	selection = new ILSelection(vectorList);
        this.clickMode = ClickMode.SELECT;
        selection.addListener(this);
	initMouseAdapter(new ILCanvasMouseAdapter(this));
    }

    private void initMouseAdapter(final ILCanvasMouseAdapter mouseAdapter){
	addMouseListener(mouseAdapter);
	addMouseMotionListener(mouseAdapter);
    }

    public void addVector(final Vector vector) {
	vectorList.add(vector);
        logger.log(Level.INFO, "Added vector: " + vector);
	repaint();
    }

    public ILSelection getSelection() {
	return selection;
    }

    public ClickMode getClickMode() {
	return clickMode;
    }

    public void setClickMode(final ClickMode clickMode) {
	this.clickMode = clickMode;
        logger.log(Level.INFO, "ClickMode set to " + this.clickMode);
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
