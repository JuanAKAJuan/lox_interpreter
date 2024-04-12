package lox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lox.TokenType.*;

class Scanner {
	private final String source;
	private final List<Token> tokens = new ArrayList<>();
	private int start = 0;
	private int current = 0;
	private int line = 1;

	Scanner(String source) {
		this.source = source;
	}

	/**
	 * The scanner will work its way through the source code, adding tokens
	 * until it runs out of characters. Once it has ran out of characters, it
	 * appends one final "end of file" token.
	 *
	 * @return A list of tokens that have been scanned
	 */
	List<Token> scanTokens() {
		while (!isAtEnd()) {
			// We are at the beginning of the next lexeme.
			start = current;
			scanToken();
		}

		tokens.add(new Token(EOF, "", null, line));
		return tokens;
	}

	/**
	 * Helper function to tell if all of the characters have been consumed.
	 *
	 * @return true if we have gone past the source's length and false otherwise
	 */
	private boolean isAtEnd() {
		return current >= source.length();
	}
 
}
