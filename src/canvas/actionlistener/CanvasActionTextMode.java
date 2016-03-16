package canvas.actionlistener;

import canvas.ILCanvas;
import canvas.actor.TextActor;

import java.awt.event.ActionEvent;

/**
 * Created by nattelog on 2016-01-02.
 */
public class CanvasActionTextMode extends AbstractCanvasAction
{
    public CanvasActionTextMode(final ILCanvas canvas) {
	super(canvas);
    }

    @Override public void actionPerformed(final ActionEvent e) {
	canvas.setActor(new TextActor());
    }
}
