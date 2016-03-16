package filehandler.svg.dom;

/**
 * Created by nattelog on 2015-12-29.
 */
public class ILParseException extends Exception
{
    final static int TRIM_SIZE = 20;

    final private String input, message, caller;


    public ILParseException(final String message, final int index, final String input) {
	this.input = trimInput(input, index);
	this.message = message;
	this.caller = Thread.currentThread().getStackTrace()[2].getMethodName();
    }


    public ILParseException(final String message) {
	this.message = message;
	this.input = null;
	this.caller = Thread.currentThread().getStackTrace()[2].getMethodName();
    }

    @Override public String getMessage() {
	super.getMessage();
	String message = caller + "::" + this.message;

	/* If this exception was called with an input, print it out too. */
	if (input != null) {
	    message += "\n\t" + input;
	}

	return message;
    }


    /**
     *	Trims @TRIM_SIZE characters before and after @index and returns the
     *	result. Also creates a parenthesis around the character with @index.
     */
    private String trimInput(final String input, final int index)
    {
	int startIndex = index > TRIM_SIZE ? index - TRIM_SIZE : 0;
	int endIndex = input.length() > (index + TRIM_SIZE) ? index + TRIM_SIZE : input.length();

	String trim = input.substring(startIndex, endIndex);
	String preTrim = trim.substring(0, index - startIndex);
	String focusChar = "(" + trim.charAt(index - startIndex) + ")";
	String postTrim = trim.substring(index - startIndex + 1, trim.length());

	//System.out.println(trim + "\n" + preTrim + "\n" + postTrim + "\n" + focusChar);

	return "..." + preTrim + focusChar + postTrim + "...";
    }
}
