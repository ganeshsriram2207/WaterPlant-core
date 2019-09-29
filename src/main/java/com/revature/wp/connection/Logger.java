package com.revature.wp.connection;

public class Logger {

	private static Logger logger = null; // creating a OBJECT as Private so if user creates obj outside this class error
											// will be thrown

	private Logger() // creating a Constructor
	{

	}

	public static Logger getInstance() // creating a method to check whether to provide user new object or exsisting
										// obj
	{
		if (logger == null) // if logger(object)==null it allows to create new object
		{
			return new Logger();
		}
		return logger; // else restricting user from creating new objects
	}

	public static final String level = "DEBUG";

	public static void debug(Object message) {
		if (level == "DEBUG") {
			System.out.println(message);
		}
	}

	public static void info(Object message) {
		if (level == "INFO") {
			System.out.println(message);
		}
	}

	public static void error(Object message) {
		if (level == "ERROR") {
			System.out.println(message);
		}

	}
}
