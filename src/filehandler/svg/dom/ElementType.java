package filehandler.svg.dom;

/**
 *  This enum is used to identify different types of DOM elements.
 */
public enum ElementType
{
    /**
     *  <element></element> - This is an open element.
     * */
    OPEN,

    /**
     *  <element/> - This is a closed element.
     * */
    CLOSED,

    /**
     *  This is the so called CDATA that is inside an open element.
     * */
    CONTENT
}
