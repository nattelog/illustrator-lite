package canvas.actor;

import canvas.ILCanvas;

/**
 * Created by nattelog on 2015-10-25.
 */
public class SelectionActor extends AbstractCanvasActor
{
    @Override public void canvasClick(final ILCanvas canvas, final int x, final int y) {
	canvas.getSelection().select(x, y);
    }

    @Override public String toString() {
	return "SelectionActor";
    }
}
