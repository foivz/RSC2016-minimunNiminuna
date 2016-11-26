/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.minimunniminuna.configurations;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.jboss.logging.Logger;
import org.springframework.web.multipart.MultipartFile;


public class Multimedia{
    
    /**
     * Method for uploading files
     * @param path path to put file
     * @param file file to upload
     * @return path to file or Failed keyword on fail
     * @throws java.nio.file.FileSystemException
     * @throws java.io.FileNotFoundException
     */
    public static String handleFileUpload(String path,
             MultipartFile file) throws IOException{
        Logger.getLogger("Multimedia.java").log(Logger.Level.INFO, "Initiating file upload");
        if (!file.isEmpty()) {
                byte[] bytes = file.getBytes();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(new File(path, file.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
                Logger.getLogger("Multimedia.java").log(Logger.Level.INFO, "File uploaded");
                return path;
        } else {
            Logger.getLogger("Multimedia.java").log(Logger.Level.ERROR, "File size 0! ");
            return null;
        }
    }
    
}
