package toolbar;

import canvas.ILCanvas;
import canvas.ILSelection;
import util.ILView;

import javax.swing.*;

/**
 * Created by nattelog on 15-09-11.
 */
abstract class ILToolbarModule extends ILView
{
    protected ILCanvas canvas;
    protected ILSelection selection;
    protected String title;
    protected int span;
    private JLabel titleLabel;


    protected ILToolbarModule(final ILCanvas canvas, final int horizontalSpan) {
	this.canvas = canvas;
	selection = this.canvas.getSelection();
	title = "";
	span = horizontalSpan;
	titleLabel = new JLabel(title);
	add(titleLabel, "wrap, center, span " + span);
    }

    public void setTitle(final String title) {
	this.title = title;
        titleLabel.setText(this.title);
    }
}
