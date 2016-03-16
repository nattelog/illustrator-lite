package canvas.actionlistener;

import canvas.ILCanvas;

import javax.swing.*;

/**
 * Created by nattelog on 2016-01-02.
 */
abstract class AbstractCanvasAction extends AbstractAction
{
    protected ILCanvas canvas;

    AbstractCanvasAction(final ILCanvas canvas) {
	this.canvas = canvas;
    }
}
