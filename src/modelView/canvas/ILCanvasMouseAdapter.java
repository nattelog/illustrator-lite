package modelview.canvas;

import controller.ILMouseAdapter;
import modelview.ILDebug;
import modelview.vector.Circle;
import modelview.vector.Rectangle;

import java.awt.event.MouseEvent;
import java.awt.*;

/**
 * Created by nattelog on 15-07-06.
 */
public class ILCanvasMouseAdapter extends ILMouseAdapter
{
    private ILCanvas canvas;

    public ILCanvasMouseAdapter(final ILCanvas canvas) {
        super(canvas.getController());
        this.canvas = canvas;
    }

    @Override public void mouseClicked(final MouseEvent e) {
	super.mouseClicked(e);
	ILDebug.getInstance().msg("x: " + e.getX() + ", y: " + e.getY());

        int x = e.getX();
        int y = e.getY();

        switch (controller.getState()) {
            case CIRCLE:
                canvas.addVector(new Circle(x - 25, y - 25, 50, Color.BLACK));
                break;
            case RECTANGLE:
                canvas.addVector(new Rectangle(x, y, 50, 50, Color.BLACK));
                break;
            default:
                ILDebug.getInstance().msg("State is not defined here.");
                break;
        }
    }
}
