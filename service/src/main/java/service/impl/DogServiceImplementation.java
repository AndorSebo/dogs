package service.impl;

import dao.DogDAO;
import dogs.exceptions.AgeInvalidException;
import dogs.exceptions.MissingDog;
import dogs.exceptions.MovingIsTooLate;
import dogs.model.Dog;
import dogs.service.DogService;

import java.util.Collection;

public class DogServiceImplementation implements DogService {

    private DogDAO dao;

    public DogServiceImplementation(DogDAO dao) {
        this.dao = dao;
    }

    public Collection<Dog> listAllDogs() {
        return dao.listAllDogs();
    }

    public Dog getDogById(String id) throws MissingDog {
        return dao.getDogById(id);
    }

    public void addNewDog(Dog dog) throws AgeInvalidException, MovingIsTooLate {
        dao.createDog(dog);
    }

    public void updateDog(Dog dog) throws MissingDog, AgeInvalidException, MovingIsTooLate {
        dao.updateDog(dog);
    }

    public void deleteDog(String id) throws MissingDog {
        dao.removeDog(id);
    }

}
