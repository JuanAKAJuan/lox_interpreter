package lox;

class Token {
	final TokenType type;
	final String lexeme;
	final Object literal;
	final int line;

	/**
	 * Create a new token and set the type, lexeme, literal, and line.
	 *
	 * @param type - Type of token
	 * @param lexeme - The lexeme to assign to a token
	 * @param literal -	IDENTIFIER, STRING, NUMBER
	 * @param line - The line number where the token is
	 */
	Token(TokenType type, String lexeme, Object literal, int line) {
		this.type = type;
		this.lexeme = lexeme;
		this.literal = literal;
		this.line = line;
	}

	/**
	 * Print the type of token, lexeme, and literal.
	 */
	public String toString() {
		return type + " " + lexeme + " " + literal;
	}
}
