package dogs.controller;

import dogs.exceptions.AgeInvalidException;
import dogs.exceptions.InvalidDogSize;
import dogs.exceptions.MissingDog;
import dogs.exceptions.MovingIsTooLate;
import dogs.model.Dog;
import dogs.service.DogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.UUID;

@Controller
public class DogController {
    private DogService service;

    public DogController(@Autowired DogService service) {
        this.service = service;
    }

    @RequestMapping(value = "/count",method = RequestMethod.GET)
    @ResponseBody
    public String getDogsCount(){
        return String.valueOf(service.listAllDogs().size());
    }

    @RequestMapping(value="/all",method = RequestMethod.GET)
    @ResponseBody
    public Collection<Dog> getAllDogs(){
        return service.listAllDogs();
    }

    @RequestMapping(value="/add",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String addDog(@RequestBody Dog dog) throws MovingIsTooLate, AgeInvalidException, InvalidDogSize {
        service.addNewDog(dog);
        return dog.getName()+" has added to database with id: "+dog.getId()+".";
    }

    @RequestMapping(value="/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public String deleteDog(@RequestParam(required = false) String id) throws MissingDog {
        service.removeDog(UUID.fromString(id));
        return "The dog with id ("+id+") has been removed.";
    }

    @RequestMapping(value="/update",method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String updateDog(@RequestBody Dog dog) throws MovingIsTooLate, MissingDog, AgeInvalidException, InvalidDogSize {
        service.updateDog(dog);
        return dog.getName()+" data's has updated.";
    }

    @RequestMapping(value="/id",method = RequestMethod.GET)
    @ResponseBody
    public Dog getDogById(@RequestParam(required = false) String id) throws MissingDog {
        return service.getDogById(UUID.fromString(id));
    }

    @RequestMapping(value="/age",method = RequestMethod.GET)
    @ResponseBody
    public Collection<Dog> getDogsUnderAge(@RequestParam(required = false) Integer age){
        Collection<Dog> dogs = service.listAllDogs();
        for (Dog d : dogs){
            if(d.getAge() != age)
                dogs.remove(d);
        }
        return dogs;
    }

    @ExceptionHandler(MovingIsTooLate.class)
    @ResponseBody
    public String handlerMovingIsTooLate(Exception e){
        return "The Moving date is invalid, because it's in the future.";
    }

    @ExceptionHandler(AgeInvalidException.class)
    @ResponseBody
    public String handlerAgeInvalidException(Exception e){
        return "The Age is invalid, because it must be between 0 - 13.";
    }

    @ExceptionHandler(MissingDog.class)
    @ResponseBody
    public String handlerMissingDog(Exception e){
        return "Dog with this id not found in the system.";
    }

}
