import com.fasterxml.jackson.databind.ObjectMapper;
import dao.DogDAO;
import dogs.exceptions.AgeInvalidException;
import dogs.exceptions.MissingDog;
import dogs.exceptions.MovingIsTooLate;
import dogs.model.Dog;

import java.io.File;
import java.util.Collection;
import java.util.UUID;

public class DAOJSON  implements DogDAO {

    File jsonFile;
    ObjectMapper mapper;

    public DAOJSON(File jsonFile) {
        this.jsonFile = jsonFile;
    }

    public DAOJSON(String jsonPath) {
        
    }

    @Override
    public void createDog(Dog dog) throws AgeInvalidException, MovingIsTooLate {

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
