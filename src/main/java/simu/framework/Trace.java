package simu.framework;

/**
 * The `Trace` class provides a logging mechanism with configurable levels (INFO, WARN, ERR) to output messages to the console based on the set trace level.
 */

public class Trace {

	/**
	 * Defines the levels of logging used by the Trace class.
	 */
	public enum Level{INFO, WAR, ERR}
	
	private static Level traceLevel;

	/**
	 * Sets the current logging level of the Trace class.
	 * Only messages with a level higher than or equal to this will be printed.
	 * @param lvl The logging level to be set.
	 */
	public static void setTraceLevel(Level lvl){
		traceLevel = lvl;
	}
	/**
	 * Outputs a message to the console if its level is higher than or equal to the set trace level.
	 * @param lvl The level of the message being logged.
	 * @param txt The text of the message to log.
	 */
	public static void out(Level lvl, String txt){
		if (lvl.ordinal() >= traceLevel.ordinal()){
			System.out.println(txt);
		}
	}
	
	
	
}