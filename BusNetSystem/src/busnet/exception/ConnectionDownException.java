package busnet.exception;

public class ConnectionDownException extends Exception {
	private static final long serialVersionUID = 2260943535306201581L;

	public ConnectionDownException(String message) {
		super(message);
	}
}
