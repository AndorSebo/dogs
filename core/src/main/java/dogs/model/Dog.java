package dogs.model;

import dogs.exceptions.AgeInvalidException;
import dogs.exceptions.MovingIsTooLate;

import java.time.LocalDate;
import java.util.UUID;

public class Dog {
    private UUID id;
    private String name;
    private byte age;
    private LocalDate moving;

    public Dog() {
        this.id = UUID.randomUUID();
    }

    public Dog(String name, byte age, LocalDate moving) throws MovingIsTooLate, AgeInvalidException {
        this.id = UUID.randomUUID();
        this.name = name;
        setAge(age);
        setMoving(moving);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getAge() {
        return age;
    }

    public void setAge(byte age) throws AgeInvalidException {
        if(age < 0 || age > 13)
            throw new AgeInvalidException("This dog's age is invalid");
        else
            this.age = age;
    }

    public LocalDate getMoving() {
        return moving;
    }

    public void setMoving(LocalDate moving) throws MovingIsTooLate {
        if(!moving.isBefore(LocalDate.now()))
            throw new MovingIsTooLate("This date has not yet arrived: "+moving.toString());
        else
            this.moving = moving;
    }
}
