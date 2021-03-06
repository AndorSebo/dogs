package service.impl;

import dao.DogDAO;
import dogs.exceptions.AgeInvalidException;
import dogs.exceptions.InvalidDogSize;
import dogs.exceptions.MissingDog;
import dogs.exceptions.MovingIsTooLate;
import dogs.model.Dog;
import dogs.service.DogService;

import java.util.Collection;
import java.util.UUID;

public class DogServiceImplementation implements DogService {

    private DogDAO dao;

    public DogServiceImplementation(DogDAO dao) {
        this.dao = dao;
    }

    public Collection<Dog> listAllDogs() {
        return dao.listAllDogs();
    }

    public Dog getDogById(UUID id) throws MissingDog {
        return dao.getDogById(id);
    }

    public void addNewDog(Dog dog) throws AgeInvalidException, MovingIsTooLate, InvalidDogSize {
        dao.createDog(dog);
    }

    public void updateDog(Dog dog) throws MissingDog, AgeInvalidException, MovingIsTooLate, InvalidDogSize {
        dao.updateDog(dog);
    }

    public void removeDog(UUID id) throws MissingDog {
        dao.removeDog(id);
    }

}
