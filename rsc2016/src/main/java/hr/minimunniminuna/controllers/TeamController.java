/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.controllers;

import hr.minimunniminuna.model.Person;
import hr.minimunniminuna.model.Team;
import hr.minimunniminuna.repositories.PersonRepository;
import hr.minimunniminuna.repositories.TeamRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jboss.logging.Logger;
import org.jboss.logging.Logger.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author paz
 */
@RestController
@RequestMapping(value = "/team")
public class TeamController {
    
    TeamRepository teamRepository;
    PersonRepository repository;
    
    @Autowired
    public TeamController(TeamRepository teamRepository, PersonRepository repo) {
        this.teamRepository = teamRepository;
        this.repository = repo;
    }
    
     @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Team>> retrieveAll() throws IOException {
        
        Logger.getLogger("TeamController.java").log(Logger.Level.INFO,
                "GET on /team-- retrieving full list of teams");
        
        
        return new ResponseEntity(this.teamRepository.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity retrieve(@PathVariable long id) throws IOException {
        
        Logger.getLogger("TeamController.java").log(Logger.Level.INFO,
                "GET on /team--" + id);
        
        return new ResponseEntity(this.teamRepository.findByIdTeam(id), HttpStatus.OK);
        
    }
    
      @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity modify(@PathVariable long id, @RequestBody Team team) {
        Logger.getLogger("TeamController.java").log(Level.INFO,
                "PUT on /team/" + id + " -- " + team.toString());
        
        Team signed = this.teamRepository.findByIdTeam(id);
        
        if(signed != null) {
            this.teamRepository.save(team);
            Logger.getLogger("TeamController.java").log(Level.INFO,
                    "Update successful for " + team.toString());
            return new ResponseEntity(HttpStatus.OK);
        } else {
            Logger.getLogger("TeamController.java").log(Level.WARN,
                    "No user found for id " + id);
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(value="/team/person/{id}", method = RequestMethod.GET)
    public  ResponseEntity<List<Team>> teamList(@PathVariable long id){
        
        Person person = repository.findByIdPerson(id);
        
        List<Team> teams = this.teamRepository.findAll();
        ArrayList<Team> retTeams = new ArrayList();
        
        for(Team t : teams){
            if(t.getMembers().contains(person))
                retTeams.add(t);
        }
        return new ResponseEntity(retTeams, HttpStatus.OK);
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Team> signup(@RequestBody Team team) {
        
        Logger.getLogger("TeamController.java").log(Level.INFO,
                "POST on /team/create -- " + team.toString());
     
        
        Team signed = this.teamRepository.save(team);
        
        return (signed != null)?new ResponseEntity(signed, HttpStatus.OK)
                               :new ResponseEntity(HttpStatus.BAD_REQUEST);
          
    }
    
    
    
    
    
}
