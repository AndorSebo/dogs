import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import dao.DogDAO;
import dogs.exceptions.AgeInvalidException;
import dogs.exceptions.MissingDog;
import dogs.exceptions.MovingIsTooLate;
import dogs.model.Dog;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class DAOJSON implements DogDAO {

    private File jsonFile;
    private ObjectMapper mapper;

    public DAOJSON(File jsonFile) {
        this.jsonFile = jsonFile;
        ctorExtension();
    }

    public DAOJSON(String jsonPath) {
        this.jsonFile = new File(jsonPath);
        ctorExtension();
    }

    private void ctorExtension() {
        if (!jsonFile.exists())
            try {
                jsonFile.createNewFile();
                FileWriter writer = new FileWriter(jsonFile);
                writer.write("[]");
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.enable(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT);
    }

    @Override
    public void createDog(Dog dog) throws AgeInvalidException, MovingIsTooLate {
        Collection<Dog> dogs = listAllDogs();
        Dog temp = new Dog(dog.getName(),dog.getAge(),dog.getMoving());
        dogs.add(temp);
        try {
            mapper.writeValue(jsonFile, dogs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDog(Dog dog) throws MissingDog, AgeInvalidException, MovingIsTooLate {
        Dog temp = getDogById(dog.getId());
        removeDog(temp.getId());
        temp.setAge(dog.getAge());
        temp.setMoving(dog.getMoving());
        temp.setName(dog.getName());
        createDog(temp);
    }

    @Override
    public void removeDog(UUID id) throws MissingDog {
        Dog temp = getDogById(id);
        Collection<Dog> dogs = listAllDogs();
        dogs.remove(temp);
        try {
            mapper.writeValue(jsonFile, dogs);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Dog getDogById(UUID id) throws MissingDog {
        Collection<Dog> dogs = listAllDogs();
        for (Dog d : dogs) {
            if (id.equals(d.getId())) {
                return d;
            }
        }
        throw new MissingDog("This Dog is not found in the system.");
    }

    @Override
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
