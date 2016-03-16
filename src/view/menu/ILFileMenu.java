package view.menu;

import filehandler.ILFileHandler;
import filehandler.actionlistener.FileHandlerActionNewFile;
import filehandler.actionlistener.FileHandlerActionOpenFile;
import filehandler.actionlistener.FileHandlerActionSaveFile;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;

/**
 * Created by nattelog on 2016-01-02.
 */
public class ILFileMenu extends JMenu
{
    public ILFileMenu(final ILFileHandler fileHandler) {
	setText("File");
	setMnemonic(KeyEvent.VK_F);

	JMenuItem newFileButton = new JMenuItem("New");
	JMenuItem openFileButton = new JMenuItem("Open");
	JMenuItem saveFileButton = new JMenuItem("Save");

	newFileButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
	newFileButton.addActionListener(new FileHandlerActionNewFile(fileHandler));

	openFileButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
	openFileButton.addActionListener(new FileHandlerActionOpenFile(fileHandler));

	saveFileButton.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
	saveFileButton.addActionListener(new FileHandlerActionSaveFile(fileHandler));

	add(newFileButton);
	add(openFileButton);
	add(saveFileButton);
    }
}
