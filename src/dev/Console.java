package dev;

/**
 * Created by nattelog on 15-04-03.
 */
public final class Console
{
    private static StackTraceElement[] stack;

    // Returns the name of the method of the caller
    private static String callerInfo(){
	stack = Thread.currentThread().getStackTrace();

	// Returns the name of caller of whoever called this
	// method, i.e. index 3
	return stack[3].getMethodName();
    }

    public static void msg(final Object message){
        System.out.println(callerInfo() + ": " + message);
    }
}
