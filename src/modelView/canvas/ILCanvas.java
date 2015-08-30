package modelview.canvas;

import controller.ILController;
import controller.ILMouseAdapter;
import modelview.ILView;
import modelview.vector.Vector;

import java.awt.*;

/**
 * Created by nattelog on 15-06-26.
 */
public class ILCanvas extends ILView
{

    public ILCanvas(final ILController controller) {
        attachController(controller);

        final ILMouseAdapter mouseAdapter = new ILCanvasMouseAdapter(this);
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

        setOpaque(true);
    }

    public void addVector(final Vector vector){
        controller.getVectors().addVector(vector);
        notifyListeners();
    }

    private void notifyListeners(){
        this.repaint();
    }

    @Override protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        for (Vector vector : controller.getVectors().getVectors()){
            vector.draw(g);
        }
    }
}
