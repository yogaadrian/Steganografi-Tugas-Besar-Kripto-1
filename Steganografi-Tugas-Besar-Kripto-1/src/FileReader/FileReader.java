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
import static crypter.tucil.pkg1.CrypterTucil1.decrypt;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
    
    private static String hex(int n) {
    // call toUpperCase() if that's required
    return String.format("0x%2s", Integer.toHexString(n)).replace(' ', '0');
}

    private static String hex(float f) {
    // change the float to raw integer bits(according to the OP's requirement)
    return hex(Float.floatToRawIntBits(f));
    }
    
    private static String FileToString(String stringpath) throws IOException{
        String content="";
        Path path=Paths.get(stringpath);
        byte[] rawData = Files.readAllBytes(path);
        for(int i=0;i<rawData.length;i++){
            content=content.concat(Character.toString((char)rawData[i]));
        }
        return content;
    }
    
    private static String getFileExtension(File file) {
        String fileName = file.getName();
        if(fileName.lastIndexOf(".") != -1 && fileName.lastIndexOf(".") != 0)
        return fileName.substring(fileName.lastIndexOf(".")+1);
        else return "";
    }
    
    private static String GetExtension(String stringpath){
        File file = new File(stringpath);
        return getFileExtension(file);
    }
    
    private static void savefile(String stringpath,byte[] content) throws FileNotFoundException, IOException{
        FileOutputStream fos = new FileOutputStream(stringpath);
            fos.write(content);
            fos.close();
    }
    
    public byte[] StringToBytes(String str){
        byte[] b= new byte[str.length()];
        for(int i=0;i<str.length();i++){
            b[i]=(byte)str.charAt(i);
        }
        return b;
    }
    
    public void encryptStegano(String inputImagePath, String outputImagePath, 
                                String inputFilePath, 
                                String key, double threshold ) {
      try {
          boolean isPNG = false;
          Path p = Paths.get(inputImagePath);
          
          if (GetExtension(inputImagePath).equals("png")) {
            isPNG = ImageConverter.convertFormat(inputImagePath, "temp.bmp", "BMP");
            p = Paths.get("temp.bmp");
          }         
          
          byte[] inputRawData = Files.readAllBytes(p);
          
          Bitmap inputImage = new Bitmap(inputRawData, threshold);
          
          String content = "";
          content = FileToString(inputFilePath);
            
          String newContent = encrypt(content, key, 2, 1); // ini vigenere
          
          if (inputImage.insertMessage(new StringBlock(newContent, threshold), key, threshold)) {              
            System.out.println("Success");
          } else {
            System.out.println("Gagal");
          }        
          
          if (isPNG) {
            savefile("temp.bmp", inputImage.extractBitmap(GetExtension(inputFilePath)));
            ImageConverter.convertFormat("temp.bmp", outputImagePath, "PNG");
            //Files.delete(p);            
          } else {          
            savefile(outputImagePath, inputImage.extractBitmap(GetExtension(inputFilePath)));
          }
          
      } catch (IOException ex) {
          Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
      }
      
    }
    
    public void decryptStegano(String inputImagePath, String outputFilePath, 
                                String key, double threshold ) {
      try {
          boolean isPNG = false;
          Path p = Paths.get(inputImagePath);
          
          if (GetExtension(inputImagePath).equals("png")) {
            isPNG = ImageConverter.convertFormat(inputImagePath, "temp.bmp", "BMP");
            p = Paths.get("temp.bmp");
          }          
          
          byte[] inputRawData = Files.readAllBytes(p);
          
          Bitmap inputImage = new Bitmap(inputRawData, threshold);
          
          String outputFile = decrypt(inputImage.decrypt(threshold, key), key, 2, 1);
          byte [] outFile = StringToBytes(outputFile);
          
          savefile(outputFilePath + "." + inputImage.ext, outFile);
          
      } catch (IOException ex) {
          Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
      }
      
    }
    
    public static void main(String[] args) {
      
      //encryptStegano("Lenna.bmp", "yukkelar.bmp", "tubes.doc", "yogaimba", 0.3);
      //decryptStegano("yukkelar.bmp", "out", "yogaimba", 0.3);
      
      //boolean bool=ImageConverter.convertFormat("Lenna.png","Lenna2.bmp", "BMP");

      //System.out.println(a.calculatepsnr(b));
      //System.out.println(a.getMessage(threshold));
      //System.out.println(a.getMaximumSize(threshold));
            
    }
    
}
