package canvas;

import javafx.scene.text.Font;
import vector.Ellipse;
import vector.Rectangle;
import vector.Text;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

import static canvas.MousePressState.*;


/**
 * Created by nattelog on 15-09-11.
 */
public class ILCanvasMouseAdapter extends MouseInputAdapter
{
    private static final int DEFAULT_WIDTH = 50;
    private static final int DEFAULT_HEIGHT = 50;

    private ILCanvas canvas;
    private int x, y, dX, dY;
    private MousePressState pressState;

    public ILCanvasMouseAdapter(final ILCanvas canvas) {
	this.canvas = canvas;
	x = 0;
	y = 0;
	dX = 0;
	dY = 0;
	pressState = NOTHING;
    }

    private void setPosition(final MouseEvent e){
	x = e.getX();
	y = e.getY();
    }

    @Override public void mouseClicked(final MouseEvent e) {
	super.mouseClicked(e);
	setPosition(e);
	switch(canvas.getClickMode()){
	    case ELLIPSE:
		canvas.addVector(new Ellipse(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT));
		break;
	    case RECTANGLE:
		canvas.addVector(new Rectangle(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT));
		break;
	    case TEXT:
		Text text = new Text(x, y);
		if (text.setValue(canvas)) canvas.addVector(text);
		break;
	    default:
		canvas.getSelection().select(x, y);
		break;
	}
    }

    @Override public void mouseDragged(final MouseEvent e) {
	super.mouseDragged(e);
	setPosition(e);
	switch (pressState) {
	    case RESIZEBOX:
		int vectorX = canvas.getSelection().getVector().getX();
		int vectorY = canvas.getSelection().getVector().getY();
		canvas.getSelection().resize(x - vectorX, y - vectorY);
		break;
	    case SELECTIONBOX:
		canvas.getSelection().move(x - dX, y - dY);
		break;
	    default:
		break;
	}
    }

    // Checks where the user pressed the mouse.
    @Override public void mousePressed(final MouseEvent e) {
	super.mousePressed(e);
	setPosition(e);

	if (canvas.getSelection().isActive()) {
	    if (canvas.getSelection().getResizeBox().contains(x, y))
		pressState = RESIZEBOX;

	    else if (canvas.getSelection().getSelectionBox().contains(x, y)) {
		pressState = SELECTIONBOX;
		dX = x - canvas.getSelection().getVector().getX();
		dY = y - canvas.getSelection().getVector().getY();

	    } else
		pressState = NOTHING;

	} else
	    pressState = NOTHING;
    }
}