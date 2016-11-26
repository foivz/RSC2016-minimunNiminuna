/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.controllers;

import hr.minimunniminuna.model.Icon;
import hr.minimunniminuna.model.Quiz;
import hr.minimunniminuna.model.Team;
import hr.minimunniminuna.repositories.QuizRepository;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
@RequestMapping(value = "/quiz")
public class QuizController {
    
    QuizRepository repo;
    
    @Autowired
    public QuizController(QuizRepository repo) {
        this.repo = repo;
    }
    
     @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Icon>> retrieveAll() throws IOException {
        
        return new ResponseEntity(this.repo.findAll(), HttpStatus.OK);
        
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity retrieve(@PathVariable long id) throws IOException {
        
        return new ResponseEntity(this.repo.findByIdQuiz(id), HttpStatus.OK);
        
    }
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<Quiz> signup(@RequestBody Quiz quiz2) {
        
        Logger.getLogger("QuizController.java").log(Level.INFO,
                "POST on /quiz/q -- " + quiz2.getName());
        
        Logger.getLogger("QuizController.java").log(Level.INFO,
                "POST on /quiz/q -- " + quiz2.getCategory().getId());
        
        Quiz quiz = this.repo.save(quiz2);
        
        return (quiz != null)?new ResponseEntity(quiz, HttpStatus.OK)
                               :new ResponseEntity(HttpStatus.BAD_REQUEST);
          
    }
    
    @RequestMapping(value = "/create", method = RequestMethod.PUT)
    public ResponseEntity<Quiz> signup(@PathVariable long id, @RequestBody Quiz quiz2) {
        
        Quiz quiz = this.repo.save(quiz2);
        
        return (quiz != null)?new ResponseEntity(quiz, HttpStatus.OK)
                               :new ResponseEntity(HttpStatus.BAD_REQUEST);
          
    }

    
}
