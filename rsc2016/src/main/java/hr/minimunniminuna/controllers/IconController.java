/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.controllers;

import hr.minimunniminuna.model.Icon;
import hr.minimunniminuna.repositories.IconRepository;
import java.io.IOException;
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
@RequestMapping(value = "/icon")
public class IconController {
    
     IconRepository repo;
    
    
    @Autowired
    public IconController(IconRepository repo) {
        this.repo = repo;
    }
    
     @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Icon>> retrieveAll() throws IOException {
        
        return new ResponseEntity(this.repo.findAll(), HttpStatus.OK);
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity retrieve(@PathVariable long id) throws IOException {

        
        return new ResponseEntity(this.repo.findByIdIcon(id), HttpStatus.OK);
        
    }
    
    
    
}