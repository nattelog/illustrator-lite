package toolbar;

import javax.swing.*;

/**
 * Created by nattelog on 15-09-13.
 */
public class ILInputField extends JTextField
{
    public ILInputField(final ToolbarInputListener inputListener) {
	addKeyListener(new ILToolbarKeyAdapter(this, inputListener));
    }
}
