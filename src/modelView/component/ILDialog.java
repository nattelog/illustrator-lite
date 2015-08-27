package modelview.component;

import controller.ILController;
import dev.Console;
import dev.ILInput;
import dev.ILPanel;
import modelview.ILDebug;
import modelview.ILView;
import net.miginfocom.swing.MigLayout;

import javax.naming.NotContextException;
import javax.swing.*;

/**
 * Created by nattelog on 15-08-27.
 */
public class ILDialog extends ILView
{

    public ILDialog(final ILController controller) {
	attachController(controller);
    }

    private int openDialog(final JPanel panel, final String title){
	return JOptionPane.showConfirmDialog(null, panel, title, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
    }

    public Boolean getRectangleProperties() {
	JPanel panel = new JPanel();
	panel.setLayout(new MigLayout());

	JTextField widthInputField = new JTextField();
	JTextField heightInputField = new JTextField();

	panel.add(new JLabel("Width:"));
	panel.add(widthInputField, "pushx, growx, wrap");
	panel.add(new JLabel("Height:"));
	panel.add(heightInputField, "pushx, growx");

	Boolean firstFail = true;

    	while (true) {
    	    if (openDialog(panel, "Draw rectangle") == JOptionPane.OK_OPTION) {
		ILDebug.getInstance().msg("width: " + widthInputField.getText() + ", height: " + heightInputField.getText());

		try {
		    controller.setInputWidth(Integer.parseUnsignedInt(widthInputField.getText()));
		    controller.setInputHeight(Integer.parseUnsignedInt(heightInputField.getText()));

		    if(controller.getInputWidth() == 0 || controller.getInputHeight() == 0)
			throw new NumberFormatException();

		    return true;
		}
		catch (NumberFormatException e) {
		    ILDebug.getInstance().msg(e.getMessage());

		    if (firstFail)
			panel.add(new JLabel("Not valid input. Try again!"), "dock north");

		    firstFail = false;
		}

	    } else
		return false;
    	}
    }

    public Boolean getCircleProperties(){
	JPanel panel = new JPanel();
	panel.setLayout(new MigLayout());

	JTextField radiusInputField = new JTextField();

	panel.add(new JLabel("Radius:"));
	panel.add(radiusInputField, "pushx, growx, wrap");

	Boolean firstFail = true;

	while (true) {
	    if (openDialog(panel, "Draw circle") == JOptionPane.OK_OPTION) {
		ILDebug.getInstance().msg("radius: " + radiusInputField.getText());

		try {
		    controller.setInputRadius(Integer.parseUnsignedInt(radiusInputField.getText()));

		    if(controller.getInputRadius() == 0)
			throw new NumberFormatException();

		    return true;
		}
		catch (NumberFormatException e) {
		    ILDebug.getInstance().msg(e.getMessage());

		    if (firstFail)
			panel.add(new JLabel("Not valid input. Try again!"), "dock north");

		    firstFail = false;
		}

	    } else
		return false;
	}
    }
}
