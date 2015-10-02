package toolbar;

import javax.swing.*;

/**
 * Created by nattelog on 2015-10-02.
 */
public class ILButton extends JButton
{
    public ILButton(final String title, final Action action, final String hotkey) {
	super(title);
	setAction(action);
	getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(hotkey), title);
	getActionMap().put(title, action);
    }
}
