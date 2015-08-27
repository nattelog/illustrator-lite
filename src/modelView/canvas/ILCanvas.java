package modelview.canvas;

import controller.ILController;
import controller.ILMouseAdapter;
import modelview.ILView;
import modelview.vector.Vector;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

/**
 * Created by nattelog on 15-06-26.
 */
public class ILCanvas extends ILView
{
    private ILMouseAdapter mouseAdapter;
    private List<Vector> vectors;

    public ILCanvas(final ILController controller) {
        attachController(controller);

        vectors = new ArrayList<>();

        mouseAdapter = new ILCanvasMouseAdapter(this);
        addMouseListener(mouseAdapter);

        setOpaque(true);
    }

    public void addVector(final Vector vector){
        vectors.add(vector);
        notifyListeners();
    }

    private void notifyListeners(){
        this.repaint();
    }

    @Override protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        for (Vector vector : vectors){
            vector.draw(g);
        }
    }
}
