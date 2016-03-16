package filehandler.actionlistener;

import filehandler.ILFileHandler;

import javax.swing.*;

/**
 * Created by nattelog on 2016-01-02.
 */
abstract class AbstractFileHandlerAction extends AbstractAction
{
    protected ILFileHandler fileHandler;

    AbstractFileHandlerAction(final ILFileHandler fileHandler) {
	this.fileHandler = fileHandler;
    }
}
