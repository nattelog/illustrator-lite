package view;

import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import java.awt.*;

/**
 * Created by nattelog on 15-09-11.
 */
public class ILView extends JPanel
{
    public ILView() {
	setLayout(new MigLayout());
        setBackground(Color.WHITE);
    }

    public ILView(final String constraint) {
	setLayout(new MigLayout(constraint));
	setBackground(Color.WHITE);
    }
}
