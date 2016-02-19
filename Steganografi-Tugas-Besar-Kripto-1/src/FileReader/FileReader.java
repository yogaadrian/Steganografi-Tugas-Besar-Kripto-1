/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReader;
import Bitmap.Bitmap;
import Bitmap.Plane;
import Message.StringBlock;
import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Paths;
    import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author kevin
 */
public class FileReader {

    /**
     * @param args the command line arguments
     */
    
    public static String hex(int n) {
    // call toUpperCase() if that's required
    return String.format("0x%2s", Integer.toHexString(n)).replace(' ', '0');
}

    public static String hex(float f) {
    // change the float to raw integer bits(according to the OP's requirement)
    return hex(Float.floatToRawIntBits(f));
    }
    
    public static void main(String[] args) {

        try {
            Path path = Paths.get("testcase.bmp");
            byte[] rawData = Files.readAllBytes(path);
            
            double threshold = 0.01;
            
            Bitmap a = new Bitmap(rawData);
            System.out.println(a.getMaximumSize(threshold));
            
            if (a.insertMessage(new StringBlock("Yoga Adrian Saputra", threshold), threshold)) {
              System.out.println("Success");
            } else {
              System.out.println("Gagal");
            }
            Plane[] message;
            
            int counter=0;
            
            message = new Plane[a.getMaximumSize(threshold)];
            System.out.println(a.getMaximumSize(threshold));
            for (int i=0 ; i < a.blockX;i++){
                for (int j=0; j < a.blockY;j++){
                    for (int k=0; k < a.blocks[i][j].planes.length; k++){
                      if (a.blocks[i][j].planes[k].getComplexity() > 0.01){
                          message[counter] = a.blocks[i][j].planes[k];
                          counter++;
                      }
                    }
                }
            }
            System.out.println(counter);
            for (int i=0; i<counter; i++){
                message[i].print();
            }

            
            /*for (int i=0; i < rawData.length; i++)
                System.out.println((rawData[i]));*/
            
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
