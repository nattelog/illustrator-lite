package toolbar;

import canvas.ILCanvas;
import canvas.SelectionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * Created by nattelog on 15-09-13.
 */
public class ILVectorModule extends ILToolbarModule implements SelectionListener
{
    private ILButton colorButton, duplicateButton, deleteButton;

    public ILVectorModule(final ILCanvas canvas, final int horizontalSpan) {
	super(canvas, horizontalSpan);
        setTitle("Vector");
        canvas.getSelection().addListener(this);

        // Initializing actions.
        Action delete = new AbstractAction("Delete")
        {
            @Override public void actionPerformed(final ActionEvent e) {
                canvas.getSelection().delete();
            }
        };

        Action duplicate = new AbstractAction("Duplicate")
        {
            @Override public void actionPerformed(final ActionEvent e) {
                canvas.getSelection().duplicate();
            }
        };

        Action changeColor = new AbstractAction("Color")
        {
            @Override public void actionPerformed(final ActionEvent e) {
                Color newColor = JColorChooser.showDialog(canvas, "Choose Background Color", canvas.getSelection().getVector().getFillColor());
                if (newColor != null)
                    canvas.getSelection().changeFillColor(newColor);
            }
        };

        colorButton = new ILButton("Color", changeColor, "C");
        duplicateButton = new ILButton("Duplicate", duplicate, "D");
        deleteButton = new ILButton("Delete", delete, "BACK_SPACE");

        add(colorButton, "width 100");
        add(duplicateButton, "width 100, wrap");
        add(deleteButton, "width 100");

        enableButtons(false);
    }

    private void enableButtons(final Boolean enabled){
        colorButton.setEnabled(enabled);
        duplicateButton.setEnabled(enabled);
        deleteButton.setEnabled(enabled);
    }

    @Override public void selectionChanged() {
        if (canvas.getSelection().isActive())
            enableButtons(true);
        else
            enableButtons(false);
    }
}
