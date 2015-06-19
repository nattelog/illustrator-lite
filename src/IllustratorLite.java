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

	// controller = new ILController();

	/**
	 * 	Declaring the model. In this version, the controller can only handle one model which
	 * 	in this case is the canvas where all vectors are stored.
	 */

	// canvas = new ILModel();
	// controller.attachModel(canvas);

	/**
	 *  	Declaring the window frame which will consist of two panels: the toolbar and the canvas,
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
	 *  	By default the frame width and height will be 640x480.
	 */

	// frame = new ILFrame();

	/**
	 * 	Building the toolbar. It's just a panel containing buttons.
	 * 	The buttons have different functions. In this version they only change the controller's state.
	 */

	// toolbar = new ILPanel();

	// selectBtn = new ILButton("Select");
	// selectBtn.onClick(controller.setState(controller.state.SELECT));

	/**
	 * 	The frame consists of rows and columns. In this case, the frame only requires two columns.
	 */

    }
}
