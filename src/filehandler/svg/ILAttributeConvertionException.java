package filehandler.svg;

/**
 * Created by nattelog on 2015-12-31.
 */
public class ILAttributeConvertionException extends Exception
{
    private final String attributeName;


    public ILAttributeConvertionException(final String message, final String attributeName)
    {
	super(message);
	this.attributeName = attributeName;
    }


    public String getAttributeName()
    {
	return attributeName;
    }
}
