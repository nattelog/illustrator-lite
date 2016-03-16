package canvas;

/**
 *  This enum is used to differentiate different states while pressing the
 *  mouse.
 */
public enum MousePressState
{
    /**
     *  Pressed the small rectangle in the corner of the selection.
     * */
    RESIZEBOX,

    /**
     *  Pressed the selection.
     * */
    SELECTIONBOX,

    /**
     *  Pressed on nothing.
     * */
    NOTHING
}
