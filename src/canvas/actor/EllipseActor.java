package canvas.actor;

import canvas.ILCanvas;
import vector.Ellipse;

/**
 * Created by nattelog on 2015-10-25.
 */
public class EllipseActor extends AbstractCanvasActor
{
    @Override public void canvasClick(final ILCanvas canvas, final int x, final int y) {
	canvas.addVector(new Ellipse(x, y, DEFAULT_WIDTH, DEFAULT_HEIGHT));
    }

    @Override public String toString() {
	return "EllipseActor";
    }
}
