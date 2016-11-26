/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.controllers;

import hr.minimunniminuna.model.Icon;
import hr.minimunniminuna.model.Question;
import hr.minimunniminuna.model.QuestionCategory;
import hr.minimunniminuna.repositories.QuestionCategoryRepository;
import hr.minimunniminuna.repositories.QuestionRepository;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author paz
 */
@RestController
@RequestMapping(value = "/question")
public class QuestionController {
    
    QuestionRepository repo;
    QuestionCategoryRepository repoCat;    
    
    @Autowired
    public QuestionController(QuestionRepository repo, QuestionCategoryRepository repoCat) {
        this.repo = repo;
        this.repoCat = repoCat;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Question>> retrieveAll() throws IOException {
        
        return new ResponseEntity(this.repo.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity retrieve(@PathVariable long id) throws IOException {

        
        return new ResponseEntity(this.repo.findByIdQuestion(id), HttpStatus.OK);
        
    }
    
    @RequestMapping(value = "cat/{id}", method = RequestMethod.GET)
    public ResponseEntity<List<Question>> getCatQuestions(@PathVariable long id) throws IOException {
        
        QuestionCategory cat = this.repoCat.findByIdCat(id);
        
        List<Question> questions = this.repo.findAll();
        ArrayList<Question> qu = new ArrayList();
        
        for(Question q : questions){
            if(q.getCat().getIdCat() == cat.getIdCat())
                qu.add(q);
        }
        
        
        return new ResponseEntity(qu, HttpStatus.OK);
        
        
    }
    
    
}
