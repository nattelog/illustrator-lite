package canvas;

import javax.swing.event.MouseInputAdapter;
import java.awt.event.MouseEvent;

import static canvas.MousePressState.*;


/**
 * Created by nattelog on 15-09-11.
 */
public class ILCanvasMouseAdapter extends MouseInputAdapter
{
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

	// When a user clicks on the canvas, a vector will be added.
	canvas.getActor().canvasClick(canvas, x, y);
    }

    @Override public void mouseDragged(final MouseEvent e) {
	super.mouseDragged(e);
	setPosition(e);
	try {
	    switch (pressState) {
		case RESIZEBOX:
		    int vectorX = canvas.getSelection().getVector().getX();
		    int vectorY = canvas.getSelection().getVector().getY();
		    canvas.getSelection().resize(x - vectorX, y - vectorY);
		    break;
		case SELECTIONBOX:
		    // The vector is moved relative to where the mouse
		    // pressed on the selection.
		    canvas.getSelection().move(x - dX, y - dY);
		    break;
		case NOTHING:
		    break;
	    }

	} catch (ILVectorException ex) {
	    System.out.println(ex.getMessage());
	}
    }

    // Checks where the user pressed the mouse.
    @Override public void mousePressed(final MouseEvent e) {
	super.mousePressed(e);
	setPosition(e);

	try {
	    if (canvas.getSelection().isActive()) {
		if (canvas.getSelection().getResizeBox().contains(x, y)) pressState = RESIZEBOX;

		else if (canvas.getSelection().getSelectionBorder().contains(x, y)) {
		    pressState = SELECTIONBOX;

		    // Saves the difference in x and y of the press-position
		    // and the vectorposition.
		    dX = x - canvas.getSelection().getVector().getX();
		    dY = y - canvas.getSelection().getVector().getY();

		} else pressState = NOTHING;

	    } else pressState = NOTHING;

	} catch (ILVectorException ex) {
	    System.out.println(ex.getMessage());
	}
    }


}
