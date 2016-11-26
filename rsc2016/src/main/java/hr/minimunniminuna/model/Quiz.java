/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author paz
 */
@Entity
@Table(name="quiz")
public class Quiz {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_quiz")
    long idQuiz;
    
    @Column(name="name")
    String name;
    @Column(name="begin_date")
    String beginDate;
    @Column(name="end_date")
    String endDate;
    @Column(name="begin_time")
    String beginTime;
    @Column(name="end_time")
    String endTime;
    @Column(name="all_answers")
    String allAnswers;
    
    
    
    @Embedded
    Location location;
    
    @ManyToOne
    @JoinColumn(name="id_person")
    Person cretaor;
    
    
    
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "quiz_has_awards",  joinColumns = { 
			@JoinColumn(name = "id_quiz", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_quiz_awards", 
					nullable = false, updatable = false) })
    List<Prize> prizes;
    
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "quiz_has_question",  joinColumns = { 
			@JoinColumn(name = "id_quiz", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_question", 
					nullable = false, updatable = false) })
    List<Question> questions;
    
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "team_has_quiz",  joinColumns = { 
			@JoinColumn(name = "id_quiz", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_team", 
					nullable = false, updatable = false) })
    List<Team> teams;
    
    @ManyToOne
    @JoinColumn(name="id_category")
    Category category;

    
            
    public long getIdQuiz() {
        return idQuiz;
    }

    public void setIdQuiz(long idQuiz) {
        this.idQuiz = idQuiz;
    }

    public String getName() {
        return name;
    }

    public Person getCretaor() {
        return cretaor;
    }

    public void setCretaor(Person cretaor) {
        this.cretaor = cretaor;
    }
    

    public String getAllAnswers() {
        return allAnswers;
    }

    public void setAllAnswers(String allAnswers) {
        this.allAnswers = allAnswers;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }
    
    

    public void setName(String name) {
        this.name = name;
    }

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }


    public List<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
    
    
    
    
}
