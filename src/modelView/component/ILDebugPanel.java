package modelview.component;

import controller.Controller;
import modelview.ILView;
import net.miginfocom.swing.MigLayout;
import modelview.ILDebug;
import modelview.LabelListener;

import javax.swing.*;

/**
 * Created by nattelog on 15-07-06.
 */
public class ILDebugPanel extends ILView implements LabelListener
{
    private JLabel debugLabel;
    private ILDebug debug;

    public ILDebugPanel(final Controller controller) {
	attachController(controller);
	setLayout(new MigLayout("insets 5 5 5 5"));

	debug = ILDebug.getInstance();
	debug.addLabelListener(this);

	debugLabel = new JLabel();
	add(debugLabel, "height 25");
    }


    @Override public void labelChanged() {
	debugLabel.setText((String) debug.getMessage());
	System.out.println(debug.getMessage());
    }
}
