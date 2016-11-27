/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.repositories;

import hr.minimunniminuna.model.TeamQuiz;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author paz
 */
public interface TeamQuizStatRepository extends JpaRepository<TeamQuiz, String>{
    
    
    
}
