package dogs.exceptions;

public class MissingDog extends Throwable {
    public MissingDog() {
    }

    public MissingDog(String message) {
        super(message);
    }
}
