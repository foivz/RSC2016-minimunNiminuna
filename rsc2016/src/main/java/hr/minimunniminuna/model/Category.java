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
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author paz
 */
@Entity
@Table(name="category")
public class Category{

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_category")
    long id;
    
    @Column(name="name")
    String name;
    
    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    List<Quiz> quiz;
    
    @JsonIgnore
    @ManyToMany(mappedBy="categories")
    List<Person> person;
    
    @JsonIgnore
    @ManyToMany(mappedBy="teamCategories")
    List<Team> team;
    
    @Column(name="icon")
    String icon;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Quiz> getQuiz() {
        return quiz;
    }

    public void setQuiz(List<Quiz> quiz) {
        this.quiz = quiz;
    }

    public List<Person> getPerson() {
        return person;
    }

    public void setPerson(List<Person> person) {
        this.person = person;
    }

    public List<Team> getTeam() {
        return team;
    }

    public void setTeam(List<Team> team) {
        this.team = team;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
    
    
    
    
}
