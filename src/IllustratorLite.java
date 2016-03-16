import canvas.ILCanvas;
import view.ILSelectionBar;
import view.menu.ILFileMenu;
import filehandler.ILFileHandler;
import view.menu.ILCanvasMenu;
import view.menu.ILSelectionMenu;
import view.ILSelectionPropertyBar;
import view.ILFrame;
import view.ILModeBar;
import view.ILView;

import javax.swing.*;
import java.awt.*;

/**
 *  The main class of IllustratorLite. Its purpose is to setup all main parts
 *  of the program and set it running.
 *
 *  The main window look like this:
 *  	=========================
 *  	= menu			=
 *  	= --------------------- =
 *  	= toolpanel		=
 *  	= --------------------- =
 *  	= canvas		=
 *  	=========================
 */
public final class IllustratorLite
{
	private IllustratorLite() {
	}

	public static void main(String[] args){

	/*
		The views are ordered like this.

	   	=========================
	   	= menu			=
	   	= --------------------- =
	   	= toolpanel		=
	   	= --------------------- =
	   	= canvas		=
	   	=========================
	 */

	/* The main frame of the program. */
		ILFrame frame = new ILFrame("Illustrator Lite");

	/* Initializing views. */
		JMenuBar menuBar = new JMenuBar();
		ILCanvas canvas = new ILCanvas();
		ILModeBar modebar = new ILModeBar(canvas);
		ILSelectionPropertyBar propertybar = new ILSelectionPropertyBar(canvas.getSelection());
		ILView toolPanel = new ILView();

		ILFileHandler fileHandler = new ILFileHandler(canvas);

	/* Initializing the menu. */
		menuBar.add(new ILFileMenu(fileHandler));
		menuBar.add(new ILCanvasMenu(canvas));
		menuBar.add(new ILSelectionMenu(canvas.getSelection()));

	/* Initializing the top toolpanel. */
		toolPanel.add(modebar);

	/* Adding a small separator. */
		toolPanel.add(new ILView(), "width 50");

		toolPanel.add(propertybar);

	/* Adding a small separator. */
		toolPanel.add(new ILView(), "width 50");

		toolPanel.add(new ILSelectionBar(canvas.getSelection()));
		toolPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));


	/* Finializing the frame. */
		menuBar.setOpaque(true);
		frame.setJMenuBar(menuBar);
		frame.add(toolPanel, "north, height 15");
		frame.add(canvas, "width 750, height 500, push, grow");
		frame.pack();
	}
}
