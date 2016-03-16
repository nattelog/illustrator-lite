package filehandler.actionlistener;

import filehandler.ILFileHandler;

import java.awt.event.ActionEvent;

/**
 * Created by nattelog on 2016-01-02.
 */
public class FileHandlerActionOpenFile extends AbstractFileHandlerAction
{
    public FileHandlerActionOpenFile(final ILFileHandler fileHandler) {
	super(fileHandler);
    }

    @Override public void actionPerformed(final ActionEvent e) {
	fileHandler.openFile();
    }
}
