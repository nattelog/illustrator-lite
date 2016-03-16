package filehandler.actionlistener;

import filehandler.ILFileHandler;

import java.awt.event.ActionEvent;

/**
 * Created by nattelog on 2016-01-02.
 */
public class FileHandlerActionNewFile extends AbstractFileHandlerAction
{
    public FileHandlerActionNewFile(final ILFileHandler fileHandler) {
	super(fileHandler);
    }

    @Override public void actionPerformed(final ActionEvent e) {
	fileHandler.newFile();
    }
}
