package filehandler;

import canvas.ILCanvas;
import canvas.ILVectorList;
import selection.SelectionListener;
import filehandler.svg.ILSVGAdapter;
import filehandler.svg.dom.ILDOMElement;
import filehandler.svg.dom.ILParseException;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by nattelog on 2015-12-23.
 */
public class ILFileHandler implements SelectionListener
{
    private ILCanvas canvas;
    private ILSVGAdapter svgAdapter;
    private Path path;
    private boolean saved;


    public ILFileHandler(final ILCanvas canvas)
    {
	this.canvas = canvas;
	this.canvas.getSelection().addListener(this);
	svgAdapter = new ILSVGAdapter(this.canvas);
	path = null;
	saved = true;
    }


    public void newFile()
    {
	checkSave();
	canvas.clear();
	path = null;
    }


    /**
     *	Let the user select a .filehandler.svg-file and turns the file into vectors.
     */
    public void openFile()
    {
	JFileChooser chooser = new JFileChooser();
	FileNameExtensionFilter filter = new FileNameExtensionFilter("SVG Files", "svg");

	chooser.setDialogTitle("Open file");
	chooser.setFileFilter(filter);

	checkSave();

	/* Let the user select a .svg-file. */
	if (chooser.showOpenDialog(canvas) == JFileChooser.APPROVE_OPTION) {
	    StringBuilder inputString = new StringBuilder();
	    path = chooser.getSelectedFile().toPath();

	    System.out.println("Trying to open '" + path + "'.");

	    /* Turn the file into a string. */
	    try (BufferedReader reader = Files.newBufferedReader(path)) {
		int noLines = 0;
		String s = reader.readLine();

		while (s != null) {
		    inputString.append(s);
		    inputString.append('\n');
		    noLines++;
		    s = reader.readLine();
		}

		System.out.println("Successfully read " + noLines + " lines!");

	    } catch (IOException e) {
		System.out.println("IO ERROR!\n" + e.getMessage());
		JOptionPane.showMessageDialog(canvas, "Could not open the file. Check the log for more information.");

	    }

	    ILDOMElement svgTree;

	    /* Start to parse the file. Turn it into an ILDOMElement. */
	    try {
		svgTree = svgAdapter.parseDOMFile(inputString.toString());

	    } catch (ILParseException e) {
		System.out.println("PARSE ERROR!\n" + e.getMessage());
		JOptionPane.showMessageDialog(canvas,
					      "Something got wrong while parsing the file.\n" +
					      "Check the log for more information.");
		return;
	    }

	    /* Turn the ILDOMElement into an ILVectorList and let @canvas point
	    its list to it instead of the previous one. */
	    try {
		ILVectorList list = svgAdapter.createVectorListFromSVGTree(svgTree);
		canvas.setVectorList(list);
		canvas.getSelection().deselect();

	    } catch (InvalidObjectException e) {
		System.out.println(e.getMessage());
		JOptionPane.showMessageDialog(canvas,
					      "Could not convert file.\n" +
					      "Check the log for more information.");
	    }
	}
    }


    /**
     *	Turns the vectorlist into a string of svg-elements.
     */
    public void saveFile()
    {
	/* If a directory hasn't been chosen yet, let the user choose one. */
	if (path == null) {
	    JFileChooser chooser = new JFileChooser();
	    chooser.setDialogTitle("Choose directory");
	    chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	    chooser.setAcceptAllFileFilterUsed(false);

	    /* If the the dialog is canceled, just exit this function. */
	    if (chooser.showOpenDialog(canvas) != JFileChooser.APPROVE_OPTION) {
		return;
	    }

	    /* Let the user input a name for the file. */
	    String fileName = JOptionPane.showInputDialog("Enter a filename.");

	    /* Make sure the filename isn't empty or null. */
	    if (fileName == null || fileName.isEmpty()) {
		return;
	    }

	    /* Check if the filename has a correct suffix. */
	    if (!fileName.contains(".svg")) {
		fileName += ".svg";
	    }

	    /* Create the path. */
	    path = Paths.get(chooser.getSelectedFile().getPath(), fileName);
	}

	/* Convert the vectors to an filehandler.svg-formated string and write to @path. */
	String output = svgAdapter.createSVGTreeFromVectorList().toString();

	try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
	    writer.write(output, 0, output.length());

	} catch (IOException e) {
	    System.out.println("IO ERROR: " + e.getMessage());
	    JOptionPane.showMessageDialog(canvas, "Could not save the file. Check the log for more information.");

	}

	saved = true;
	System.out.println("Successfully saved to path '" + path + "'.");
    }


    /**
     *	Makes sure the current canvas is saved, if not asks the user to save
     *	it.
     */
    private void checkSave()
    {
	if (!saved) {
    	    int answer = JOptionPane
    		    .showOptionDialog(canvas, "Do you want to save your canvas first?", "Save file", JOptionPane.YES_NO_OPTION,
    				      JOptionPane.QUESTION_MESSAGE, null, null, null);

    	    if (answer == JOptionPane.YES_OPTION) {
    		saveFile();
    	    }
    	}
    }


    /**
     *	The canvas will only be 'unsaved' if the user do something with any
     *	vector.
     */
    @Override public void vectorChanged() {
	saved = false;
    }

    @Override public void selectionActivated() {

    }

    @Override public void selectionDeactivated() {

    }
}
