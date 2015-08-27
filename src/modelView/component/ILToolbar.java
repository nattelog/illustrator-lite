package modelview.component;

import controller.ILController;
import controller.State;
import modelview.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;

/**
 * Created by nattelog on 15-06-29.
 */
public class ILToolbar extends ILView
{
    public ILToolbar(final ILController controller) {
        attachController(controller);
        setLayout(new MigLayout("insets 5"));
        setOpaque(true);

        /**
         *      Declaring buttons.
         */

        JButton circleBtn = new JButton("Circle");
        JButton rectBtn = new JButton("Rectangle");


        /**
         *      Setting buttonlisteners.
         */

        circleBtn.addActionListener(controller.setUserState(State.CIRCLE));
        rectBtn.addActionListener(controller.setUserState(State.RECTANGLE));


        /**
         *      Adding buttons.
         */

        add(circleBtn, "pushx, growx, wrap");
        add(rectBtn, "pushx, growx");
    }
}
