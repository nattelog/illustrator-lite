package toolbar;

import canvas.ILCanvas;
import util.ILView;

/**
 * Created by nattelog on 15-09-11.
 */
public class ILToolbar extends ILView
{
    public ILToolbar(final ILCanvas canvas) {
	// Adding all modules for the toolbar.
	add(new ILToolsModule(canvas, 2));
	add(new ILPropertyModule(canvas, 6));
	add(new ILVectorModule(canvas, 2));

	// Adding an empty module to the very right that will act as
	// the stretching part of the toolbar.
	add(new ILView(), "grow x, push x");
    }
}
