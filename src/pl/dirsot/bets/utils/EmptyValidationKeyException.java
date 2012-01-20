package pl.dirsot.bets.utils;

public class EmptyValidationKeyException extends Exception {
	EmptyValidationKeyException(){
		super();
	}
    public EmptyValidationKeyException(String message) {
        super(message);
    }

    public EmptyValidationKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public EmptyValidationKeyException(Throwable cause) {
        super(cause);
    }
}
