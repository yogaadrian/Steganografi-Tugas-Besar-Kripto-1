/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Crypter;

import Bitmap.Bitmap;
import Bitmap.Plane;
import FileReader.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import Crypter.CrypterTucil1;

/**
 *
 * @author kevin
 */
public class Decrypt {
    public String mes;
    public Plane[] message;
    
    public Decrypt(String img, String key, boolean cipher){
    try {
            Path path = Paths.get(img);
            byte[] rawData = Files.readAllBytes(path);

            double threshold = 0.01;
            
            int counter=0;
            
            Bitmap a = new Bitmap(rawData, threshold);
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
            mes ="";
            for (int i=0; i<counter; i++){
                for (int j=0; j< message[i].getData().length; j++){
                    for (int k=0; k< message[i].getData()[j].length; k++){
                        mes = mes.concat(Character.toString(message[i].getData()[j][k]));
                    }
                }
            }
            if (cipher){
                mes = CrypterTucil1.decrypt(mes,key,1,1);
            }
            
           
            
            
    }
        catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
}