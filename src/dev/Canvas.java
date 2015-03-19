package dev;

import java.util.ArrayList;

/**
 * Created by nattelog on 15-03-19.
 */
public class Canvas
{
    private ArrayList<Shape> shapes;

    public Canvas() {
	shapes = new ArrayList<Shape>();
    }

    public ArrayList<Shape> getShapes(){
	return shapes;
    }
}
