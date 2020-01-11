package dogs.exceptions;

public class InvalidDogSize extends Throwable {
    public InvalidDogSize() {
    }

    public InvalidDogSize(String message) {
        super(message);
    }
}
