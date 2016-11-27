/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author paz
 */
@Entity
@Table(name="team_has_quiz")
public class TeamQuiz {
    
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_team_quiz")
    long idTeamQuiz;
    
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "id_quiz")
    Quiz cat;
    
    @ManyToOne
    @JoinColumn(name = "id_team")
    Team t;
    
    @Column(name="average")
    float average;

    public TeamQuiz() {
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
    
    public long getIdTeamQuiz() {
        return idTeamQuiz;
    }

    public void setIdTeamQuiz(long idTeamQuiz) {
        this.idTeamQuiz = idTeamQuiz;
    }

    public Quiz getCat() {
        return cat;
    }

    public void setCat(Quiz cat) {
        this.cat = cat;
    }

    public Team getT() {
        return t;
    }

    public void setT(Team t) {
        this.t = t;
    }
    
    
    
    
   
    
    
}
