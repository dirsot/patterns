package pl.dirsot.bets.utils;

public class InvalidValidationKeyException extends Exception {
	InvalidValidationKeyException(){
		super();
	}
    public InvalidValidationKeyException(String message) {
        super(message);
    }

    public InvalidValidationKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidValidationKeyException(Throwable cause) {
        super(cause);
    }
}
