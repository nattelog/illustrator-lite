package modelview.component;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;

/**
 * Created by nattelog on 15-06-26.
 */
public class ILFrame extends JFrame
{
    private static final String DEFAULT_TITLE = "Default title";
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;

    public ILFrame() {
	super(DEFAULT_TITLE);
	initializeFrame();
    }

    public ILFrame(final String title) {
	super(title);
	initializeFrame();
    }

    private void initializeFrame(){
	setLayout(new MigLayout("insets 0"));
	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
	setVisible(true);
    }


}
