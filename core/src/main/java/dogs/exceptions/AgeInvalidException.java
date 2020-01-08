package dogs.exceptions;

public class AgeInvalidException extends Throwable {
    public AgeInvalidException() {
    }

    public AgeInvalidException(String message) {
        super(message);
    }
}
