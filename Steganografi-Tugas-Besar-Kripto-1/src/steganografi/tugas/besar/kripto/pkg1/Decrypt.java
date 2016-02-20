/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package steganografi.tugas.besar.kripto.pkg1;

import Bitmap.Bitmap;
import Bitmap.Plane;
import FileReader.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class Decrypt {
    Decrypt(){
    try {
            Path path = Paths.get("testcase.bmp");
            byte[] rawData = Files.readAllBytes(path);
            Plane[] message;
            double threshold = 0.01;
            
            int counter=0;
            
            Bitmap a = new Bitmap(rawData);
            message = new Plane[a.getMaximumSize(threshold)/8];
            System.out.println(a.getMaximumSize(threshold));
            for (int i=0 ; i < a.getHeight();i++){
                for (int j=0; j < a.getWidth();i++){
                    for (int k=0; k < a.blocks[i][j].planes.length; k++){
                      if (a.blocks[i][j].planes[k].getComplexity() > 0.3){
                          message[counter] = a.blocks[i][j].planes[k];
                      }
                    }
                }
            }
            
            for (int i=0; i<counter; i++)
                System.out.println(message[i]);
    }
        catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}