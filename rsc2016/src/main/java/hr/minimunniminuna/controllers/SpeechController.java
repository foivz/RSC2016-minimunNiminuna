/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.controllers;

import com.ibm.watson.developer_cloud.alchemy.v1.AlchemyVision;
import com.ibm.watson.developer_cloud.alchemy.v1.model.ImageKeywords;
import com.ibm.watson.developer_cloud.http.HttpMediaType;
import com.ibm.watson.developer_cloud.speech_to_text.v1.SpeechToText;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.RecognizeOptions;
import com.ibm.watson.developer_cloud.speech_to_text.v1.model.SpeechResults;
import com.ibm.watson.developer_cloud.speech_to_text.v1.websocket.BaseRecognizeCallback;
import com.ibm.watson.developer_cloud.visual_recognition.v3.VisualRecognition;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.ClassifyImagesOptions;
import com.ibm.watson.developer_cloud.visual_recognition.v3.model.VisualClassification;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioFileFormat.Type;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    public String getTextFromSpeechh(@RequestBody File64 file) throws IllegalStateException, IOException{
        
        
        
        SpeechToText service = new SpeechToText();
        service.setUsernameAndPassword("257413c5-7f5d-4045-a009-a1e3e3bd89fc", "hthmwugNOrLc");
        
        
        System.out.println(file.getData());
        File f = getAudio(file.decode(file.getData()));
        
        RecognizeOptions options = new RecognizeOptions.Builder()
            .contentType(HttpMediaType.AUDIO_WAV)
            .build();
        
        
        SpeechResults transcript = service.recognize(f, options).execute();
        
        System.out.println(transcript.toString());
        
        return transcript.toString();
    
    }
    
    @RequestMapping(value = "/img", method = RequestMethod.POST)
    public String getTextFromImg(@RequestBody File64 file) throws IllegalStateException, IOException{
        
        
        
        VisualRecognition service = new VisualRecognition(VisualRecognition.VERSION_DATE_2016_05_20);
        service.setApiKey("a9c987abccb0abb8d53e49ba5e0b3cc7a856b2a8");
        
        File f = getImage(file.decode(file.getData()));
        
        System.out.println("Classify an image");
        ClassifyImagesOptions options = new ClassifyImagesOptions.Builder()
            .images(f)
        .build();
        
        VisualClassification result = service.classify(options).execute();
        System.out.println(result);
        
        return result.toString();
    
    }
    
    
   public File convert(MultipartFile file) throws IOException{    
    File convFile = new File(file.getOriginalFilename()+".wav");
    convFile.createNewFile(); 
    FileOutputStream fos = new FileOutputStream(convFile); 
    fos.write(file.getBytes());
    fos.close(); 
    return convFile;
    }
    
    public File getAudio(byte[] array) throws FileNotFoundException, IOException{
        
        InputStream b_in = new ByteArrayInputStream(array);

        int sampleRate = 16000;
        AudioFormat format = new AudioFormat(sampleRate, 16, 1, true, false);
        AudioInputStream stream = new AudioInputStream(b_in, format,
                array.length);
        File file = new File("file.wav");
        AudioSystem.write(stream, Type.WAVE, file);
       
        return file;
    }
    
    public File getImage(byte[] array) throws IOException{
        
        ByteArrayInputStream bis = new ByteArrayInputStream(array);
        BufferedImage image = ImageIO.read(bis);
        bis.close();

        // write the image to a file
        File outputfile = new File("image.png");
        ImageIO.write(image, "png", outputfile);
        return outputfile;
    
    }
        
    
   
   
}