package dogs.exceptions;

public class MovingIsTooLate extends Throwable{
    public MovingIsTooLate() {
    }

    public MovingIsTooLate(String moving) {
        super(moving);
    }
}
