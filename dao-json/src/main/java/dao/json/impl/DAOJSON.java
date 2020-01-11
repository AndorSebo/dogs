package dao.json.impl;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dao.DogDAO;
import dogs.exceptions.AgeInvalidException;
import dogs.exceptions.InvalidDogSize;
import dogs.exceptions.MissingDog;
import dogs.exceptions.MovingIsTooLate;
import dogs.model.Dog;
import com.fasterxml.jackson.core.type.TypeReference;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class DAOJSON implements DogDAO {

    File jsonFile;
    ObjectMapper mapper;

    public DAOJSON(File jsonFile) {
        this.jsonFile = jsonFile;
        if (!jsonFile.exists()) {
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFile);
                writer.write("[]");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);

    }

    public DAOJSON(String jsonFilePath) {
        this.jsonFile = new File(jsonFilePath);
        if (!jsonFile.exists()) {
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFile);
                writer.write("[]");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    public void createDog(Dog dog) throws AgeInvalidException, MovingIsTooLate, InvalidDogSize {
        Collection<Dog> dogs = listAllDogs();
        Dog temp = new Dog(dog.getName(), dog.getAge(), dog.getMoving(), dog.getDogSize());
        dogs.add(temp);
        try {
            mapper.writeValue(jsonFile, dogs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updateDog(Dog dog) throws MissingDog, AgeInvalidException, MovingIsTooLate, InvalidDogSize {
        Dog temp = getDogById(dog.getId());
        Collection<Dog> dogs = listAllDogs();
        for (int i = 0; i < dogs.size(); i++) {
            Dog d = (Dog) ((dogs.toArray())[i]);
            if (d.getId().equals(temp.getId())) {
                d.setName(dog.getName());
                d.setDogSize(dog.getDogSize());
                d.setAge(dog.getAge());
                d.setMoving(dog.getMoving());
            }
        }
        try {
            mapper.writeValue(jsonFile, dogs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeDog(UUID id) throws MissingDog {
        Dog temp = getDogById(id);
        Collection<Dog> dogs = listAllDogs();
        Collection<Dog> result = new ArrayList<>();
        for (Dog d : dogs){
            if(!(d.getId().equals(temp.getId())))
                result.add(d);
        }
        System.out.println("List size: "+result.size());
        try {
            mapper.writeValue(jsonFile, result);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Dog getDogById(UUID id) throws MissingDog {
        Collection<Dog> dogs = listAllDogs();
        System.out.println("Get id:" + id);
        for (Dog d : dogs) {
            if (id.equals(d.getId())) {
                return d;
            }
        }
        throw new MissingDog("This Dog is not found in the system.");
    }

    public Collection<Dog> listAllDogs() {
        Collection<Dog> dogs = new ArrayList<>();
        try {
            dogs = mapper.readValue(jsonFile, new TypeReference<ArrayList<Dog>>() {
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dogs;
    }
}
