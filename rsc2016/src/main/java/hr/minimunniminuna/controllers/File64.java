/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.controllers;
import org.apache.commons.codec.binary.Base64;

/**
 *
 * @author paz
 */
public class File64 {
    
    String data;

    public File64() {
    }

    

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
   
    
    byte[] decode(String file){
        
        return Base64.decodeBase64(file);
    
    }
    
    
    
}
