package modelview.canvas;

import controller.ILMouseAdapter;
import modelview.ILDebug;
import modelview.component.ILDialog;
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
    private ILDialog dialog;

    public ILCanvasMouseAdapter(final ILCanvas canvas) {
        super(canvas.getController());
        this.canvas = canvas;
        this.dialog = new ILDialog(canvas.getController());
    }

    @Override public void mouseClicked(final MouseEvent e) {
	super.mouseClicked(e);
	ILDebug.getInstance().msg("x: " + e.getX() + ", y: " + e.getY());

        int x = e.getX();
        int y = e.getY();

        switch (controller.getState()) {

            case CIRCLE:
                if (dialog.getCircleProperties())
                    canvas.addVector(new Circle(x, y, controller.getInputRadius(), Color.BLACK));
                break;

            case RECTANGLE:
                if (dialog.getRectangleProperties())
                    canvas.addVector(new Rectangle(x, y, controller.getInputWidth(), controller.getInputHeight(), Color.BLACK));
                break;

            default:
                ILDebug.getInstance().msg("State is not defined here.");
                break;
        }
    }
}
