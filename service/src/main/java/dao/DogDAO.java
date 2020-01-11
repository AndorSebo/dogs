package dao;

import dogs.exceptions.AgeInvalidException;
import dogs.exceptions.MissingDog;
import dogs.exceptions.MovingIsTooLate;
import dogs.model.Dog;

import java.util.Collection;
import java.util.UUID;

public interface DogDAO {
    public void createDog(Dog dog) throws AgeInvalidException, MovingIsTooLate;

    public void updateDog(Dog dog) throws MissingDog, AgeInvalidException, MovingIsTooLate;

    public void removeDog(UUID id) throws MissingDog;

    public Dog getDogById(UUID id) throws MissingDog;

    public Collection<Dog> listAllDogs();
}
