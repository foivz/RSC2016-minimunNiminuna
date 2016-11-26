/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.controllers;
import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import hr.minimunniminuna.model.Credentials;
import hr.minimunniminuna.model.Person;
import hr.minimunniminuna.model.Role;
import hr.minimunniminuna.repositories.PersonRepository;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import org.jboss.logging.Logger.Level;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;



/**
 *
 * @author Leon Palaić
 * 
 * 
 */
@RestController
@RequestMapping(value = "/person")
public class PersonController {
    
    PersonRepository personRepository;
   

    @Autowired
    public PersonController(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }
    
    /**
     * gets all users from database
     * @return all users in json format with HTTP 200
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Person>> retrieveAll() throws IOException {
        Logger.getLogger("PersonController.java").log(Level.INFO,
                "GET on /person -- retrieving full list of users");
        
        
        
        /*
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("rscCert.p12");
        
        ApnsService service = APNS.newService()
        .withCert(is, "minimun123")
        .withSandboxDestination()
        .build();
        
        String payload = APNS.newPayload().alertBody("Can't be simpler than this!").build();
        String token = "3838f683f713828784c7cf2b02c754a8abbe2a3f57c4582b712e87024685aab8";
        service.push(token, payload);
        */
        return new ResponseEntity(this.personRepository.findAll(), HttpStatus.OK);
    }
    
    /**
     * looks up a user and verifies password
     * @param credentials username and password
     * @return person info and HTTP 200 on success or HTTP 404 on fail
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseEntity<Person> login(@RequestBody Credentials credentials) {
        Logger.getLogger("PersonController.java").log(Level.INFO,
                "POST on /person/login -- " + credentials.toString() + credentials.getPassword());
        
        String username = credentials.getUsername();
        String password = credentials.getPassword();
        
        Person found = this.personRepository.findByCredentialsUsername(username);
        
        return (found != null && found.getCredentials().getPassword().equals(password))?
                 new ResponseEntity(found, HttpStatus.OK)
                :new ResponseEntity(HttpStatus.NOT_FOUND);
     
    }
    
    /**
     * inserts new user to database
     * @param person user to insert
     * @return person info and HTTP 200 on success or HTTP BAD REQUEST on fail
     */
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public ResponseEntity<Person> signup(@RequestBody Person person) {
        
        Logger.getLogger("PersonController.java").log(Level.INFO,
                "POST on /person/signup -- " + person.toString());
        Role role = new Role();
        role.setId(2);
        role.setName("ROLE_USER");
        HashSet<Role> roles = new HashSet<>();

        roles.add(role);
        person.setRoles(roles);
        
        Person signed = this.personRepository.save(person);
        return (signed != null)?new ResponseEntity(signed, HttpStatus.OK)
                               :new ResponseEntity(HttpStatus.BAD_REQUEST);
          
    }
    
    /**
     * gets user with specified id
     * @param id id of user
     * @return person info with HTTP 200 on success or HTTP 404 on fail
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Person> retrieveById(@RequestParam long id) {
        Logger.getLogger("PersonController.java").log(Level.INFO,
                "GET on /person/" + id + " -- ");
        Person found = this.personRepository.findByIdPerson(id);
        
        return (found != null)? new ResponseEntity(found, HttpStatus.OK)
                                :new ResponseEntity(HttpStatus.NOT_FOUND);
          
    }
   
     /**
     * updates user with specified id
     * @param id id of user
     * @param person
     * @return person info with HTTP 200 on success or HTTP 404 on fail
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity modify(@PathVariable long id, @RequestBody Person person) {
        Logger.getLogger("PersonController.java").log(Level.INFO,
                "PUT on /person/" + id + " -- " + person.toString());
        
        Person signed = this.personRepository.findByIdPerson(id);
        if(signed != null) {
            this.personRepository.save(person);
            Logger.getLogger("PersonController.java").log(Level.INFO,
                    "Update successful for " + person.toString());
            return new ResponseEntity(HttpStatus.OK);
        } else {
            Logger.getLogger("PersonController.java").log(Level.WARN,
                    "No user found for id " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
}
