package dogs.service;

import dogs.exceptions.AgeInvalidException;
import dogs.exceptions.InvalidDogSize;
import dogs.exceptions.MissingDog;
import dogs.exceptions.MovingIsTooLate;
import dogs.model.Dog;

import java.util.Collection;
import java.util.UUID;

public interface DogService {
    public Collection<Dog> listAllDogs();
    public Dog getDogById(UUID id) throws MissingDog;
    public void addNewDog(Dog dog) throws AgeInvalidException, MovingIsTooLate, InvalidDogSize;
    public void updateDog(Dog dog) throws MissingDog, AgeInvalidException, MovingIsTooLate, InvalidDogSize;
    public void removeDog(UUID id) throws MissingDog;
}
