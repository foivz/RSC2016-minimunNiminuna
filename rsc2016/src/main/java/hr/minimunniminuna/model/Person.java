/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Leon Palaić
 */
@Entity
@Table(name="person") 
public class Person implements Serializable {
    
    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_person")
    long idPerson;
    
    @Column(name="name")
    String name;
    
    @Column(name="surname")
    String surname;
    
    @Column(name="moderator")
    boolean isModerator;
    
    @Embedded
    Credentials credentials;
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "members")
    private  List<Team>  memberOfGroups;
    
    @ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "person_has_category",  joinColumns = { 
			@JoinColumn(name = "id_person", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "id_category", 
					nullable = false, updatable = false) })
    private List<Category> categories;
    
    
    @OneToMany(mappedBy="creator")
    List<Quiz> creatorOfQuizes;
   

    public Person() {
        
    }

    public Person(Person person) {
        super();
        this.idPerson = person.getIdPerson();
        this.name = person.getName();
        this.surname = person.getSurname();
        this.credentials = person.getCredentials();
    }

 
    // String token

    public long getIdPerson() {
        return idPerson;
    }

    public boolean isIsModerator() {
        return isModerator;
    }

    public void setIsModerator(boolean isModerator) {
        this.isModerator = isModerator;
    }
    
    
    public void setIdPerson(long idPerson) {
        this.idPerson = idPerson;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }


    
    
    


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }
    
    @Override
    public String toString() {
        return this.name + " " + this.surname;
    }

    public List<Team> getMemberOfGroups() {
        return memberOfGroups;
    }

    public void setMemberOfGroups(List<Team> memberOfGroups) {
        this.memberOfGroups = memberOfGroups;
    }
    


    
    
}
