/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReader;
import Bitmap.Bitmap;
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
            Path path = Paths.get("simple.bmp");
            byte[] rawData = Files.readAllBytes(path);
            
            Bitmap a = new Bitmap(rawData);
            //System.out.println(a.getMaximumSize(0.3));
            
            StringBlock s = new StringBlock("YogaAdrian", 0.3);
            
            /*for (int i=0; i < rawData.length; i++)
                System.out.println((rawData[i]));*/
            
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
