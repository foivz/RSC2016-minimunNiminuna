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
import javax.persistence.Table;

/**
 *
 * @author paz
 */
@Entity
@Table(name="quiz_awards")
public class Prize {

    @Id 
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id_quiz_awards")
    long idPrize; 
    
    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "prizes")
    private  List<Quiz>  quizes;
    
    @Column(name="name")
    String name;

    public Prize() {
    }

    
    public long getIdPrize() {
        return idPrize;
    }

    public void setIdPrize(long idPrize) {
        this.idPrize = idPrize;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Quiz> getQuizes() {
        return quizes;
    }

    public void setQuizes(List<Quiz> quizes) {
        this.quizes = quizes;
    }
    
    
    
    
}
