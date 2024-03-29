package simu.framework;

/**
 * The `Trace` class provides a logging mechanism with configurable levels (INFO, WARN, ERR) to output messages to the console based on the set trace level.
 */

public class Trace {

	public enum Level{INFO, WAR, ERR}
	
	private static Level traceLevel = Level.INFO;
	
	public static void setTraceLevel(Level lvl){
		traceLevel = lvl;
	}
	public static void out(Level lvl, String txt){
		if (lvl.ordinal() >= traceLevel.ordinal()){
			System.out.println(txt);
		}
	}
	
	
	
}