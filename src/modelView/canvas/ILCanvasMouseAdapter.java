package modelview.canvas;

import controller.ILMouseAdapter;
import controller.State;
import java.awt.*;
import modelview.ILDebug;
import modelview.component.ILDialog;
import modelview.vector.Circle;
import modelview.vector.Rectangle;
import modelview.vector.Text;

import java.awt.event.MouseEvent;

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

        int x = e.getX();
        int y = e.getY();

        ILDebug.getInstance().msg("x: " + x + ", y: " + y);

        switch (controller.getState()) {

            case SELECT:
                int vectorIndex = controller.getVectors().findVector(x, y);
                if (vectorIndex != -1) {
                    controller.getVectors().deselectAll();
                    controller.getVectors().getVectors().get(vectorIndex).select();
                    canvas.repaint();

                } else {
                    controller.getVectors().deselectAll();
                    canvas.repaint();
                }
                break;

            case CIRCLE:
                if (dialog.getCircleProperties())
                    canvas.addVector(new Circle(x, y, controller.getInputRadius(), controller.getInputStrokeColor(), controller.getInputFillColor()));
                break;

            case RECTANGLE:
                if (dialog.getRectangleProperties())
                    canvas.addVector(new Rectangle(x, y, controller.getInputWidth(), controller.getInputHeight(), controller.getInputStrokeColor(), controller.getInputFillColor()));
                break;

            case TEXT:
                if (dialog.getTextProperties())
                    canvas.addVector(new Text(x, y, controller.getInputText(), controller.getInputStrokeColor(), controller.getInputFillColor(), new Font("Serif", Font.PLAIN, 12)));
                break;

            default:
                ILDebug.getInstance().msg("State is not defined here.");
                break;
        }
    }

    @Override public void mouseMoved(final MouseEvent e) {
        super.mouseMoved(e);
        if (controller.getState() == State.SELECT) {
            int x = e.getX(), y = e.getY();
            int vectorIndex = controller.getVectors().findVector(x, y);
            if (vectorIndex == -1)
                ILDebug.getInstance().msg("No vector on (" + x + ", " + y + ")");
            else
                ILDebug.getInstance().msg("Vector with index " + vectorIndex + " on (" + x + "," + y + ")");
        }
    }
}
