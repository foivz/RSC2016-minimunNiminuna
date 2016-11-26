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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author paz
 */
@Entity
@Table(name = "team")
public class Team {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_team")
    long idTeam;
    
    @Column(name="name")
    String name;
    
    @Column(name="description")
    String description;
    
    @ManyToOne
    @JoinColumn(name = "id_team_icon")
    Icon icon;
    
    
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "person_has_team",  joinColumns = { 
			@JoinColumn(name = "id_team", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_person", 
					nullable = false, updatable = false) })
    private List<Person> members;
    
    
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "category_has_team",  joinColumns = { 
			@JoinColumn(name = "id_team", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_category", 
					nullable = false, updatable = false) })
    private List<Category> teamCategories;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy ="teams")
    private List<Quiz> quizes;
    

    public Team() {
    }

    public List<Category> getTeamCategories() {
        return teamCategories;
    }

    public void setTeamCategories(List<Category> teamCategories) {
        this.teamCategories = teamCategories;
    }

    public List<Quiz> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<Quiz> quizes) {
        this.quizes = quizes;
    }
    
    
    
    
    
    public long getIdTeam() {
        return idTeam;
    }

    public void setIdTeam(long idTeam) {
        this.idTeam = idTeam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public List<Person> getMembers() {
        return members;
    }

    public void setMembers(List<Person> members) {
        this.members = members;
    }
    
    
    
    
}
