/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.controllers;

import hr.minimunniminuna.model.Quiz;
import hr.minimunniminuna.model.Team;
import hr.minimunniminuna.model.TeamQuiz;
import hr.minimunniminuna.repositories.QuizRepository;
import hr.minimunniminuna.repositories.TeamQuizStatRepository;
import hr.minimunniminuna.repositories.TeamRepository;
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
@RequestMapping(value = "/stat")
public class StatController {
    
    QuizRepository quizRepo;
    TeamRepository teamRepo;
    TeamQuizStatRepository statRepo;
    
    @Autowired
    public StatController(QuizRepository quizRepo, TeamRepository teamRepo, TeamQuizStatRepository statRepo) {
        this.quizRepo = quizRepo;
        this.teamRepo = teamRepo;
        this.statRepo = statRepo;
    }
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<TeamQuiz>> retrieveAll() throws IOException {
        
        return new ResponseEntity(this.statRepo.findAll(), HttpStatus.OK);
        
    }
    
    @RequestMapping(value = "/{id_game}/team/{id_team}/{score}", method = RequestMethod.GET)
    public ResponseEntity<String> retrieveAll(@PathVariable("id_game") long id_game,
                        @PathVariable("id_team") long id_team, @PathVariable("score") long score) throws IOException {
        
        Quiz q = this.quizRepo.findByIdQuiz(id_game);
        Team t = this.teamRepo.findByIdTeam(id_team);
        float average = 0;
        
        for(TeamQuiz quiz : this.statRepo.findAll()){
           if(quiz.getCat().getIdQuiz() == q.getIdQuiz())
               if(t.getIdTeam() == quiz.getT().getIdTeam()){
                   average = quiz.getAverage();
                   average += score;
                   quiz.setAverage(average);
                   this.statRepo.save(quiz);
               }
        }
        
        
        return new ResponseEntity(Float.toString(average), HttpStatus.OK);
    }

    
    
    
    
    
}
