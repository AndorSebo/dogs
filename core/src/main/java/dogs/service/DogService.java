package dogs.service;

import dogs.exceptions.AgeInvalidException;
import dogs.exceptions.MissingDog;
import dogs.exceptions.MovingIsTooLate;
import dogs.model.Dog;

import java.util.Collection;

public interface DogService {
    public Collection<Dog> listAllDogs();
    public Dog getDogById(String id) throws MissingDog;
    public void addNewDog(Dog dog) throws AgeInvalidException, MovingIsTooLate;
    public void updateDog(Dog dog) throws MissingDog, AgeInvalidException, MovingIsTooLate;
    public void deleteDog(String id) throws MissingDog;
}
