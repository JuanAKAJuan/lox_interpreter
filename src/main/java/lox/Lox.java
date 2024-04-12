package lox;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Lox {
	static boolean hadError = false;

	public static void main(String[] args) throws IOException {
		if (args.length > 1) {
			System.out.println("Usage: jlox [script]");
			System.exit(64);
		} else if (args.length == 1) {
			runFile(args[0]);
		} else {
			runPrompt();
		}
	}

	/**
	 * Start jlox from the command line and give it a path to a file. It will
	 * then read the file and execute it.
	 *
	 * @param path - Path to the file
	 */
	private static void runFile(String path) throws IOException {
		byte[] bytes = Files.readAllBytes(Paths.get(path));
		run(new String(bytes, Charset.defaultCharset()));
		if (hadError) System.exit(65);
	}

	/**
	 * Start jlox without any arguments, and it will the user into a prompt where
	 * they can enter and execute code one line at a time.
	 */
	private static void runPrompt() throws IOException {
		InputStreamReader input = new InputStreamReader(System.in);
		BufferedReader reader = new BufferedReader(input);
		
		for (;;) {
			System.out.print("> ");
			String line = reader.readLine();
			if (line == null) break;
			run(line);
			hadError = false;
		}
	}
	
	/**
	 * TODO: Document this once it is fully implemented.
	 *
	 * @param source - Where the scanner should look for tokens
	 */
	private static void run(String source) {
		Scanner scanner = new Scanner(source);
		List<Token> tokens = scanner.scanTokens();

		for (Token token : tokens) {
			System.out.println(token);
		}
	}

	/**
	 * Check to see if there is a syntax error on a given line.
	 *
	 * @param line - The line number where the syntax error occurred
	 * @param message - The message for the syntax error
	 */
	static void error(int line, String message) {
		report(line, "", message);
	}

	/**
	 * Report to the user that a syntax error has occurred and on what line.
	 *
	 * @param line - The line number where the syntax error occurred
	 * @param where - Where the syntax error occurred
	 * @param message - The message for the syntax error
	 */
	private static void report(int line, String where, String message) {
		System.err.println("[line " + line + "] Error" + where + ": " + message);
		hadError = true;
	}


}
