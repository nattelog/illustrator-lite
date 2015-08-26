package modelview.component;

import controller.Controller;
import controller.State;
import javafx.scene.paint.Color;
import modelview.*;
import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;

/**
 * Created by nattelog on 15-06-29.
 */
public class ILToolbar extends ILView
{
    public ILToolbar(final Controller controller) {
        attachController(controller);
        setLayout(new MigLayout("insets 5"));
        setOpaque(true);

        /**
         *      Declaring buttons.
         */

        JButton selectBtn = new JButton("Select");


        /**
         *      Setting buttonlisteners.
         */

        selectBtn.addActionListener(controller.setUserState(State.SELECT));


        /**
         *      Adding buttons.
         */

        add(selectBtn, "pushx, growx");
    }
}
