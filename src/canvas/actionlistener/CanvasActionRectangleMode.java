package canvas.actionlistener;

import canvas.ILCanvas;
import canvas.actor.RectangleActor;

import java.awt.event.ActionEvent;

/**
 * Created by nattelog on 2016-01-02.
 */
public class CanvasActionRectangleMode extends AbstractCanvasAction
{
    public CanvasActionRectangleMode(final ILCanvas canvas) {
	super(canvas);
    }

    @Override public void actionPerformed(final ActionEvent e) {
	canvas.setActor(new RectangleActor());
    }
}
