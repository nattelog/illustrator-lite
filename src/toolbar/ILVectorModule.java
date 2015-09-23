package toolbar;

import canvas.ILCanvas;
import canvas.SelectionListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by nattelog on 15-09-13.
 */
public class ILVectorModule extends ILToolbarModule implements SelectionListener
{
    private JButton colorButton, duplicateButton, deleteButton;

    public ILVectorModule(final ILCanvas canvas, final int horizontalSpan) {
	super(canvas, horizontalSpan);
        setTitle("Vector");
        canvas.getSelection().addListener(this);

        colorButton = new JButton("Color");
        duplicateButton = new JButton("Duplicate");
        deleteButton = new JButton("Delete");


        colorButton.addActionListener(changeColor());
        duplicateButton.addActionListener(duplicate());
        deleteButton.addActionListener(delete());

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

    private ActionListener delete(){
        return new AbstractAction(){
            @Override public void actionPerformed(final ActionEvent e) {
                canvas.getSelection().delete();
            }
        };
    }

    private ActionListener duplicate(){
        return new AbstractAction(){
            @Override public void actionPerformed(final ActionEvent e) {
                canvas.getSelection().duplicate();
            }
        };
    }

    private ActionListener changeColor(){
        return new AbstractAction(){
            @Override public void actionPerformed(final ActionEvent e) {
                Color newColor = JColorChooser.showDialog(canvas, "Choose Background Color", canvas.getSelection().getVector().getFillColor());
                if (newColor != null)
                    canvas.getSelection().changeFillColor(newColor);
            }
        };
    }
}
