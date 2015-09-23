import canvas.ILCanvas;
import toolbar.ILToolbar;
import util.ILFrame;

/**
 * Created by nattelog on 15-09-10.
 */
public class IllustratorLite
{
    static public void main(String[] args){

        ILFrame frame = new ILFrame("Illustrator Lite");

        ILCanvas canvas = new ILCanvas();
        ILToolbar toolbar = new ILToolbar(canvas);

        frame.add(toolbar, "height 50, push x, grow x, wrap");
        frame.add(canvas, "width 750, height 500, push, grow");

        frame.pack();
    }
}
