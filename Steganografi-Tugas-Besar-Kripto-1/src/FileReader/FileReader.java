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
    public String PSNR="";

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
    
    public int getMaximumSize(String inputImagePath, double threshold) {
      try {
        Path p = Paths.get(inputImagePath);
        
        if (GetExtension(inputImagePath).equals("png")) {
          ImageConverter.convertFormat(inputImagePath, "temp.bmp", "BMP");
          p = Paths.get("temp.bmp");
        }
        
        byte[] inputRawData = Files.readAllBytes(p);
        
        Bitmap inputImage = new Bitmap(inputRawData, threshold);
        return inputImage.getMaximumSize(threshold);
      } catch (IOException ex) {
        Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
      }
      return -1;
    }
    
    public void encryptStegano(String inputImagePath, String outputImagePath, 
                                String inputFilePath, 
                                String key, double threshold, boolean encrypted ) {
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
          
          String newContent = content;
          if (encrypted) { 
            newContent = encrypt(content, key, 2, 1); // ini vigenere 
          }
          
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
          
          /* Buat Laporan */
          Path p1 = Paths.get(inputImagePath);
          byte[] inputP1 = Files.readAllBytes(p1);
          Bitmap bit1 = new Bitmap(inputP1, threshold);
          Path p2 = Paths.get(outputImagePath);
          byte[] inputP2 = Files.readAllBytes(p2);
          Bitmap bit2 = new Bitmap(inputP2, threshold);
          System.out.println("ukuran maximum :"+ bit1.getMaximumSize(threshold));
          System.out.println(bit1.calculatepsnr(bit2));
          PSNR = String.valueOf(bit1.calculatepsnr(bit2));
          
      } catch (IOException ex) {
          Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
      }
      
    }
    
    public void decryptStegano(String inputImagePath, String outputFilePath, 
                                String key, double threshold, boolean encrypted ) {
      try {
          boolean isPNG = false;
          Path p = Paths.get(inputImagePath);
          
          if (GetExtension(inputImagePath).equals("png")) {
            isPNG = ImageConverter.convertFormat(inputImagePath, "temp.bmp", "BMP");
            p = Paths.get("temp.bmp");
          }          
          
          byte[] inputRawData = Files.readAllBytes(p);
          
          Bitmap inputImage = new Bitmap(inputRawData, threshold);
          
          String outputFile = inputImage.decrypt(threshold, key);
          
          if (encrypted) { 
            outputFile = decrypt(outputFile, key, 2, 1); // ini decrypt vigenere 
          }
          
          byte [] outFile = StringToBytes(outputFile);
          
          savefile(outputFilePath + "." + inputImage.ext, outFile);
          
      } catch (IOException ex) {
          Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
      }
      
    }
        
}
