/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.controllers;

import com.notnoop.apns.APNS;
import com.notnoop.apns.ApnsService;
import hr.minimunniminuna.model.Icon;
import hr.minimunniminuna.model.Prize;
import hr.minimunniminuna.model.Question;
import hr.minimunniminuna.model.Quiz;
import hr.minimunniminuna.model.Team;
import hr.minimunniminuna.repositories.PrizeRepository;
import hr.minimunniminuna.repositories.QuestionRepository;
import hr.minimunniminuna.repositories.QuizRepository;
import hr.minimunniminuna.repositories.TeamRepository;
import java.io.IOException;
import java.io.InputStream;
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
    TeamRepository teamRepo;
    QuestionRepository questions;
    PrizeRepository prizes;
    
    @Autowired
    public QuizController(QuizRepository repo, TeamRepository teamRepo, 
            QuestionRepository questions, PrizeRepository prizes) {
        this.repo = repo;
        this.teamRepo = teamRepo;
        this.questions = questions;
        this.prizes = prizes;
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

    @RequestMapping(value = "/{id}/join/{idteam}", method = RequestMethod.POST)
    public ResponseEntity<Quiz> joinQuiz(@PathVariable long id, @PathVariable long idteam){

        Quiz quiz = repo.findByIdQuiz(id);
        Team team = teamRepo.findByIdTeam(idteam);
        
        List<Team> teamsByQuiz = quiz.getTeams();
        if(teamsByQuiz.contains(team)){
            return new ResponseEntity(quiz, HttpStatus.NOT_ACCEPTABLE);
        }else{
            quiz.getTeams().add(team);
            repo.save(quiz);
        
            return new ResponseEntity(quiz, HttpStatus.OK);
        }
    }
    

    @RequestMapping(value = "/{id}/addQuestion/{idquestion}", method = RequestMethod.GET)
    public ResponseEntity<Quiz> addQuestion(@PathVariable long id, @PathVariable long idquestion){

        Quiz quiz = repo.findByIdQuiz(id);
        Question question = questions.findByIdQuestion(idquestion);
        
        quiz.getQuestions().add(question);
        repo.save(quiz);
        
        return new ResponseEntity(quiz, HttpStatus.OK);
    }
    

    @RequestMapping(value = "/{id}/addReward/{idaward}", method = RequestMethod.GET)
    public ResponseEntity<Quiz> addRewards(@PathVariable long id, @PathVariable long idaward){

        Quiz quiz = repo.findByIdQuiz(id);
        Prize prize = prizes.findByIdPrize(idaward);
        
        List<Prize> prizesByQuiz = quiz.getPrizes();
        if(prizesByQuiz.contains(prize)){
            return new ResponseEntity(quiz, HttpStatus.NOT_ACCEPTABLE);
        }else{
            quiz.getPrizes().add(prize);
            repo.save(quiz);
        
            return new ResponseEntity(quiz, HttpStatus.OK);
        }
    }
    
    @RequestMapping(value="/{id}/notificationBefore", method = RequestMethod.GET)
    public ResponseEntity<Quiz> notifyBeforeBeginning(@PathVariable long id){
        
        Quiz quiz = repo.findByIdQuiz(id);
        
        sendNotification("We'd like to remind you that quiz " + quiz.getName() + " is about begin in 1 minute.");
        
        return new ResponseEntity(quiz, HttpStatus.OK); 
    }
    
    @RequestMapping(value="/{id}/notificationTeam", method = RequestMethod.GET)
    public ResponseEntity<Quiz> notifyTeamStarts(@PathVariable long id){
        
        Quiz quiz = repo.findByIdQuiz(id);
        
        sendNotification("Your colleagues have just started quiz + " + quiz.getName() + "! Go help them out!");
        
        return new ResponseEntity(quiz, HttpStatus.OK); 
    }
    
    @RequestMapping(value="/{id}/notificationResult", method = RequestMethod.GET)
    public ResponseEntity<Quiz> notifyFinished(@PathVariable long id){
        
        Quiz quiz = repo.findByIdQuiz(id);
        
        sendNotification("The results for quiz + " + quiz.getName() + " have just arrived! Check them out!");
        
        return new ResponseEntity(quiz, HttpStatus.OK); 
    }
    
     private void sendNotification(String msg){
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream is = classloader.getResourceAsStream("rscCert.p12");
        
        ApnsService service = APNS.newService()
        .withCert(is, "minimun123")
        .withSandboxDestination()
        .build();
        
        String payload = APNS.newPayload().alertBody(msg).build();
        String token = "e0a8e974e147842c3ec1fddfb179b47e6d6b7b95ee6b2a51436dff7df1a98278";
        service.push(token, payload);
     }
}
