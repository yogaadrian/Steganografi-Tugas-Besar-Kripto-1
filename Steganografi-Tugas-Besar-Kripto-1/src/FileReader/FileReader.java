/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package FileReader;
import Bitmap.Bitmap;
import Bitmap.Plane;
import Message.StringBlock;
import static crypter.tucil.pkg1.CrypterTucil1.encrypt;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
    import java.nio.file.Files;
    import java.nio.file.Paths;
    import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

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
    
    public static String FileToString(String stringpath) throws IOException{
        String content="";
        Path path=Paths.get(stringpath);
        byte[] rawData = Files.readAllBytes(path);
        for(int i=0;i<rawData.length;i++){
            content=content.concat(Character.toString((char)rawData[i]));
        }
        return content;
    }
    
    public static void main(String[] args) {

        try {
            //Path path = Paths.get("testcase.bmp");
            /*Path path = Paths.get("tucil2.doc");
            byte[] rawData = Files.readAllBytes(path);
            for(int i=0;i<rawData.length;i++){
                System.out.println(hex((int)rawData[i]));
            }*/
            String content="";
            content=FileToString("tucil2.doc");
            System.out.println(content);
            System.out.println("------------------------------------------------------");
            String newcontent=encrypt(content,"feryimba",2,1);//ini vigenere
            System.out.println(newcontent);
            //double threshold = 0.01;
            
            //Bitmap a = new Bitmap(rawData, threshold);
            //System.out.println(a.getMessage(threshold));
            //System.out.println(a.getMaximumSize(threshold));
            /*
            if (a.insertMessage(new StringBlock("Yoga Adrian Saputra", threshold), threshold)) {
              System.out.println("Success");
            } else {
              System.out.println("Gagal");
            }
            
            BufferedImage image = ImageIO.read(new ByteArrayInputStream(a.extractBitmap()));
            ImageIO.write(image, "BMP", new File("stegano2.bmp"));
            */
        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
