package canvas;

/**
 * Created by nattelog on 2016-01-03.
 */
public class ILVectorException extends Exception
{
    private final String caller;

    public ILVectorException(final String message) {
	super(message);
	this.caller = Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    @Override public String getMessage() {
	return caller + "::" + super.getMessage();
    }
}
