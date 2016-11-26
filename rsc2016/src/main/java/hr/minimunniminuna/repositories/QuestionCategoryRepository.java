/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.repositories;

import hr.minimunniminuna.model.QuestionCategory;
import java.io.Serializable;
import javax.persistence.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author paz
 */
@Repository
@Table(name="question_category")
public interface QuestionCategoryRepository extends JpaRepository<QuestionCategory, String>{
    
    public QuestionCategory findByIdCategory(long id);
    
}
