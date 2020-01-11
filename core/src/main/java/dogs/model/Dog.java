package dogs.model;

import dogs.exceptions.AgeInvalidException;
import dogs.exceptions.InvalidDogSize;
import dogs.exceptions.MovingIsTooLate;

import java.time.LocalDate;
import java.util.UUID;

public class Dog {
    public enum size {SMALL, MEDIUM, LARGE, EXTRA_LARGE}

    private UUID id;
    private String name;
    private int age;
    private LocalDate moving;
    private size dogSize;

    public Dog() {
        this.id = UUID.randomUUID();
    }

    public Dog(String name, int age, LocalDate moving, size dogSize) throws MovingIsTooLate, AgeInvalidException, InvalidDogSize {
        this.id = UUID.randomUUID();
        this.name = name;
        setDogSize(dogSize);
        setAge(age);
        setMoving(moving);
    }

    public size getDogSize() {
        return dogSize;
    }

    public void setDogSize(size dogSize) throws InvalidDogSize {
        if (!contains(dogSize.toString()))
            throw new InvalidDogSize("Invalid Dog size! Valid parameters:{SMALL, MEDIUM, LARGE, EXTRA_LARGE}");
        else{
            this.dogSize = size.valueOf(dogSize.toString());
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) throws AgeInvalidException {
        if (age < 0 || age > 13)
            throw new AgeInvalidException("This dog's age is invalid");
        else
            this.age = age;
    }

    public LocalDate getMoving() {
        return moving;
    }

    public void setMoving(LocalDate moving) throws MovingIsTooLate {
        if (!moving.isBefore(LocalDate.now()))
            throw new MovingIsTooLate("This date has not yet arrived: " + moving.toString());
        else
            this.moving = moving;
    }

    private boolean contains(String test) {
        for (size s : size.values()) {
            if (s.name().equals(test)) {
                return true;
            }
        }

        return false;
    }

}
