package toolbar;

import canvas.ILCanvas;
import canvas.SelectionListener;
import vector.Text;
import vector.VectorType;

import javax.swing.*;

/**
 * Created by nattelog on 15-09-11.
 */
public class ILPropertyModule extends ILToolbarModule implements SelectionListener, ToolbarInputListener
{
    private ILInputField textFieldX, textFieldY, textFieldW, textFieldH, textFieldSz;

    public ILPropertyModule(final ILCanvas canvas, final int horizontalSpan) {
	super(canvas, horizontalSpan);
	setTitle("Properties");
	selection.addListener(this);

	textFieldX = new ILInputField(this);
	textFieldY = new ILInputField(this);
	textFieldW = new ILInputField(this);
	textFieldH = new ILInputField(this);
	textFieldSz = new ILInputField(this);

	add(new JLabel("X:"));
	add(textFieldX, "width 50");
	add(new JLabel("Y:"));
	add(textFieldY, "width 50");
	add(new JLabel("Size:"));
	add(textFieldSz, "width 50, wrap");

	add(new JLabel("W:"));
	add(textFieldW, "width 50");
	add(new JLabel("H:"));
	add(textFieldH, "width 50");

	updateBar();
    }

    private void enableBar(){
	setBarEnabled(true);
    }

    private void disableBar(){
	setBarEnabled(false);
    }

    private void setBarEnabled(final Boolean enabled){
	final Boolean textSelected = selection.getType() == VectorType.TEXT;

	textFieldX.setEditable(enabled);
	textFieldY.setEditable(enabled);
	textFieldW.setEditable(enabled && !textSelected);
	textFieldH.setEditable(enabled && !textSelected);
	textFieldSz.setEditable(enabled && textSelected);
    }

    private void updateBar(){
	if(selection.isActive()) {
	    enableBar();
	    textFieldX.setText(String.valueOf(selection.getVector().getX()));
	    textFieldY.setText(String.valueOf(selection.getVector().getY()));
	    textFieldW.setText(String.valueOf(selection.getVector().getWidth()));
	    textFieldH.setText(String.valueOf(selection.getVector().getHeight()));
	    updateSizeField();

	} else {
	    disableBar();
	    textFieldX.setText("");
	    textFieldY.setText("");
	    textFieldW.setText("");
	    textFieldH.setText("");
	    textFieldSz.setText("");
	}
    }

    private void updateSizeField(){
	if (selection.getType() == VectorType.TEXT) {
	    final Text text = (Text) selection.getVector();
	    textFieldSz.setText(String.valueOf(text.getSize()));

	} else
	    textFieldSz.setText("0");
    }

    private void updateVector(){
	try {
	    int x = Integer.parseInt(textFieldX.getText());
	    int y = Integer.parseInt(textFieldY.getText());
	    int w = Integer.parseInt(textFieldW.getText());
	    int h = Integer.parseInt(textFieldH.getText());
	    int size = Integer.parseInt(textFieldSz.getText());

	    // Updates the text-size if the selected vector is a text-type.
	    if (selection.getVector().getType() == VectorType.TEXT) {
		final Text text = (Text) selection.getVector();
		text.setSize(size);
	    }

	    selection.move(x, y);
	    selection.resize(w, h);
	}
	catch (NumberFormatException e){
	    System.out.print("Error in propertymodule input: " + e.getMessage() + "\n");
	}
    }

    @Override public void selectionChanged() {
	updateBar();
    }

    @Override public void toolbarInputChanged() {
	updateVector();
    }
}
