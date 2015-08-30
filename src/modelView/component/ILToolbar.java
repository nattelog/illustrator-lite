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

        JButton selectBtn = new JButton("Select");
        JButton circleBtn = new JButton("Circle");
        JButton rectBtn = new JButton("Rectangle");
        JButton textBtn = new JButton("Text");
        JButton showVectorsBtn = new JButton("Show vectors");


        /**
         *      Setting buttonlisteners.
         */

        selectBtn.addActionListener(controller.setUserState(State.SELECT));
        circleBtn.addActionListener(controller.setUserState(State.CIRCLE));
        rectBtn.addActionListener(controller.setUserState(State.RECTANGLE));
        textBtn.addActionListener(controller.setUserState(State.TEXT));
        showVectorsBtn.addActionListener(controller.showVectors());


        /**
         *      Adding buttons.
         */

        add(selectBtn, "pushx, growx, wrap");
        add(circleBtn, "pushx, growx, wrap");
        add(rectBtn, "pushx, growx, wrap");
        add(textBtn, "pushx, growx, wrap");
        add(showVectorsBtn, "pushx, growx");
    }
}
