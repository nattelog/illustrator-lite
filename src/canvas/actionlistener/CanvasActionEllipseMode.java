package canvas.actionlistener;

import canvas.ILCanvas;
import canvas.actor.EllipseActor;

import java.awt.event.ActionEvent;

/**
 * Created by nattelog on 2016-01-02.
 */
public class CanvasActionEllipseMode extends AbstractCanvasAction
{
    public CanvasActionEllipseMode(final ILCanvas canvas) {
	super(canvas);
    }

    @Override public void actionPerformed(final ActionEvent e) {
	canvas.setActor(new EllipseActor());
    }


}
