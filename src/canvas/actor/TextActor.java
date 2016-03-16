package canvas.actor;

import canvas.ILCanvas;
import vector.Text;

/**
 * Created by nattelog on 2015-10-25.
 */
public class TextActor extends AbstractCanvasActor
{
    @Override public void canvasClick(final ILCanvas canvas, final int x, final int y) {
	Text text = new Text(x, y);

	// Opens a dialog where the user can input the value
	// of the text-vector.
	if (text.setValueFromInput(canvas))
	    canvas.addVector(text);
    }

    @Override public String toString() {
	return "TextActor";
    }
}
