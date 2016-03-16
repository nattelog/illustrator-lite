package canvas.actionlistener;

import canvas.ILCanvas;
import canvas.actor.SelectionActor;

import java.awt.event.*;

/**
 * Created by nattelog on 2016-01-02.
 */
public class CanvasActionSelectMode extends AbstractCanvasAction
{
    public CanvasActionSelectMode(final ILCanvas canvas) {
	super(canvas);
    }

    @Override public void actionPerformed(final ActionEvent e) {
	canvas.setActor(new SelectionActor());
    }
}
