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
import java.util.Collection;
import java.util.UUID;

public class DAOJSON implements DogDAO {

    File jsonFile;
    ObjectMapper mapper;

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
        dogs.add(dog);
        try{
            mapper.writeValue(jsonFile,dogs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDog(Dog dog) throws MissingDog, AgeInvalidException, MovingIsTooLate {

    }

    @Override
    public void removeDog(UUID id) throws MissingDog {

    }

    @Override
    public Dog getDogById(UUID id) throws MissingDog {
        return null;
    }

    @Override
    public Collection<Dog> listAllDogs() {
        return null;
    }
}
