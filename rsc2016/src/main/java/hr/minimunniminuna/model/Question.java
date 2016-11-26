/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author paz
 */
@Entity
@Table(name="question")
public class Question {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_question")
    private Long idQuestion;
    
    @Column(name="question")
    String question;
    @Column(name = "answer")
    String answer;
    
    @Column(name="all_answers")
    String allAnswers;
    
    @ManyToOne
    @JoinColumn(name = "id_question_category")
    QuestionCategory cat;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "questions")
    List<Quiz> quizes;
    
    public Question() {
    }
    
    
    public Long getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(Long idQuestion) {
        this.idQuestion = idQuestion;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public QuestionCategory getCat() {
        return cat;
    }

    public void setCat(QuestionCategory cat) {
        this.cat = cat;
    }

    public String getAllAnswers() {
        return allAnswers;
    }

    public void setAllAnswers(String allAnswers) {
        this.allAnswers = allAnswers;
    }

    public List<Quiz> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<Quiz> quizes) {
        this.quizes = quizes;
    }
    
    
   
    
}
