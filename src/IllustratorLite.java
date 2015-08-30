import controller.*;
import modelview.canvas.ILCanvas;
import modelview.component.ILDebugPanel;
import modelview.component.ILFrame;
import modelview.component.ILToolbar;

/**
 * Created by nattelog on 15-06-17.
 * This is the main class running Illustrator Lite.
 */
public class IllustratorLite
{

    static public void main(String[] args){

	/**
	 * 	Declaring a main controller for this program. The controller is the program's brain
	 * 	listening for interactions and sets the program in different states.
	 */

	ILController controller = new ILController();
	controller.debugMode(true);


	/**
	 *  	Declaring the window frame which will consist of two views: the toolbar and the canvas,
	 *  	each on their own column in the frame. It will look something like this:
	 *
	 *  		###############
	 *  		# T #    C    #
	 *  		# O #    A    #
	 *  		# O #    N    #
	 *  		# L #    V    #
	 *  		# B #    A    #
	 *  		# A #    S    #
	 *  		# R #         #
	 *  		###############
	 *
	 */

	ILFrame frame = new ILFrame("Illustrator Lite");

	/**
	 * 	Configuring the toolbar.
	 */

	ILToolbar toolbar = new ILToolbar(controller);
	frame.add(toolbar, "width 100, shrink 0, al center 0");

	/**
	 * 	Configuring the canvas.
	 */

	ILCanvas canvas = new ILCanvas(controller);
	frame.add(canvas, "width 540, height 480, shrink 0, wrap");

	if (controller.debugOn()) {
	    ILDebugPanel debugPanel = new ILDebugPanel(controller);
	    frame.add(debugPanel, "pushx, growx, span 2");
	}

	frame.pack();
    }
}
