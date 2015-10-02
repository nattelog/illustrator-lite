package util;

/**
 * Created by nattelog on 2015-10-02.
 */
public class IndexOutOfBoundsException extends Exception
{
    private int index;

    public IndexOutOfBoundsException(final int index) {
	this.index = index;
    }

    @Override public String getMessage() {
	return "IndexOutOfBoundsException: Index " + index + " is not valid!\n";
    }
}
