/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.repositories;

import hr.minimunniminuna.model.Icon;
import hr.minimunniminuna.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author paz
 */
public interface IconRepository extends JpaRepository<Icon, String>{
    
    public Icon findByIdIcon(long id);
    
}
