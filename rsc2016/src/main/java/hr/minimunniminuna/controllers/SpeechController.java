/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.controllers;

import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author paz
 */
@RestController
@RequestMapping(value = "/speech")
public class SpeechController {
    
    @RequestMapping(value = "/", method = RequestMethod.POST)
    
    public String getTextFromSpeech(@RequestParam("file") MultipartFile file) throws IllegalStateException, IOException{
        
        SpeechToText service = new SpeechToText();
        service.setUsernameAndPassword("257413c5-7f5d-4045-a009-a1e3e3bd89fc", "hthmwugNOrLc");

        File audio = multipartToFile(file);
        
        RecognizeOptions options = new RecognizeOptions.Builder()
            .continuous(true)
            .interimResults(true)
            .contentType(HttpMediaType.AUDIO_WAV)
            .build();
        
        SpeechResults transcript = service.recognize(audio, options).execute();
        
        System.out.println(transcript);
        
        return transcript.toString();
    
    }
    
    
    public File multipartToFile(MultipartFile multipart) throws IllegalStateException, IOException {
        File convFile = new File( multipart.getOriginalFilename());
        multipart.transferTo(convFile);
        return convFile;
    }

}