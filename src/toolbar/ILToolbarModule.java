package toolbar;

import canvas.ILCanvas;
import canvas.Selection;
import net.miginfocom.swing.MigLayout;
import util.ILView;

import javax.swing.*;

/**
 * Created by nattelog on 15-09-11.
 */
abstract class ILToolbarModule extends ILView implements ToolbarModule
{
    protected ILCanvas canvas;
    protected Selection selection;
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

    private void updateTitle(){
	titleLabel.setText(title);
    }

    @Override public void setTitle(final String title) {
	this.title = title;
	updateTitle();
    }
}
