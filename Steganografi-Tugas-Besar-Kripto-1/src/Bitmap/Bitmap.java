/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bitmap;

import Message.StringBlock;
import static java.lang.Math.abs;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Asus
 */
public class Bitmap {

  public int colorStart;
  public int bpp;

  public int width;
  public int height;
  public int padding;

  public byte[] rawData;
  public int[][] colorData;

  public Block[][] blocks;
  public int blockX;
  public int blockY;

  private byte[] conjugateBlock = new byte[0];
  private int messageLength = 0;

  public Bitmap(byte[] data, double complexity) {
    rawData = data;

    colorStart = hexToInt(data[10], data[11], data[12], data[13]);
    width = hexToInt(data[18], data[19], data[20], data[21]);
    height = hexToInt(data[22], data[23], data[24], data[25]);
    bpp = hexToInt(data[28], data[29], 0, 0) / 8;
    padding = (width * bpp) % 4;

    blockY = (int) Math.ceil(height / 8.0);
    blockX = (int) Math.ceil(width / 8.0);

    colorData = new int[blockY * 8][blockX * 8];

    /* Get Conjugate Map */
    int headerSize = hexToInt(data[14], data[15], data[16], data[17]);

    if ( colorStart - (headerSize + 14 + 4) > 0 ) {
      conjugateBlock = new byte[colorStart - (headerSize + 14 + 4)];
    } else {
      conjugateBlock = new byte[0];
    }

    int n = 0;
    
    for (int i = headerSize + 14; i < colorStart; i++ ) {
      if ( i == headerSize + 14 ) {
        messageLength = hexToInt(data[i], data[i+1], data[i+2], data[i+3]);
        i = i + 3;
      } else {
        conjugateBlock[n] = data[i];
        ++n;
      }
    }
    
    /* Creating Array of Color Only Data */
    int x = colorStart;
    for (int i = 0; i < blockY * 8; i++) {
      for (int j = 0; j < blockX * 8; j++) {
        if ((i < height) && (j < width)) {
          int[] p = new int[4];
          Arrays.fill(p, 0);
          for (int k = 0; k < bpp; k++) {
            p[k] = data[x] & 0x000000FF;
            x++;
          }
          colorData[i][j] = hexToInt(p[0], p[1], p[2], p[3]);
        } else {
          colorData[i][j] = 0;
        }
        //System.out.println(colorData[i][j]);
      }

      x = x + padding;
    }

    blocks = new Block[blockY][blockX];
    for (int i = 0; i < blockY; i++) {
      for (int j = 0; j < blockX; j++) {
        blocks[i][j] = new Block(i * 8, j * 8, colorData, bpp, complexity);
      }
    }     
        
  }

  private int hexToInt(int a, int b, int c, int d) {
    /* Little Endian */
    a = a & 0x000000FF;
    b = b & 0x000000FF;
    c = c & 0x000000FF;
    d = d & 0x000000FF;
    return a
      + (b / 16) * 16 * 16 * 16 + (b % 16) * 16 * 16
      + (c / 16) * 16 * 16 * 16 * 16 * 16 + (c % 16) * 16 * 16 * 16 * 16
      + (d / 16) * 16 * 16 * 16 * 16 * 16 * 16 * 16 + (d % 16) * 16 * 16 * 16 * 16 * 16 * 16;
  }

  private byte[] intToHex(int a) {
    int[] arr = new int[4];

    arr[0] = a % (16 * 16);
    arr[1] = a / (16 * 16) % (16 * 16);
    arr[2] = a / (16 * 16 * 16 * 16) % (16 * 16);
    arr[3] = a / (16 * 16 * 16 * 16 * 16 * 16) % (16 * 16);

    byte[] arrbyte = new byte[4];
    arrbyte[0] = (byte) arr[0];
    arrbyte[1] = (byte) arr[1];
    arrbyte[2] = (byte) arr[2];
    arrbyte[3] = (byte) arr[3];

    return arrbyte;
  }

  public byte[] createConjugateBlock(ArrayList<Integer> conjugateMap, int planenumber) {
    byte[] b = new byte[conjugateMap.size() * 4];
    for (int i = 0; i < conjugateMap.size(); i++) {
      byte[] temp = new byte[4];
      temp=intToHex(conjugateMap.get(i));
      b[i*4]=temp[0];
      b[i*4+1]=temp[1];
      b[i*4+2]=temp[2];
      b[i*4+3]=temp[3];
    }
    return b;
  }

  public byte[] extractBitmap() {
    constructNewBitmap();
    byte[] newBitmap = new byte[colorStart
      + ( (blockY * 8) * (blockX * 8) * bpp )
      + conjugateBlock.length + 4 ];

    /* Copy Header */
    for (int i = 0; i < colorStart; i++) {
      newBitmap[i] = rawData[i];
    }

    /* Ubah ukuran Gambar */
    byte[] temp;
    temp = intToHex(blockX * 8);
    for (int i = 0; i < 4; i++) {
      newBitmap[18 + i] = temp[i];
    }
    temp = intToHex(blockY * 8);
    for (int i = 0; i < 4; i++) {
      newBitmap[22 + i] = temp[i];
    }
    
    /* Kalo ada Conjugate Map ini keubah */
    int newSize = hexToInt(newBitmap[2], newBitmap[3], newBitmap[4], newBitmap[5]) + conjugateBlock.length;
    int oldColorStart = colorStart;
    colorStart = colorStart + conjugateBlock.length + 4;
    int newHeaderSize = hexToInt(newBitmap[14], newBitmap[15], newBitmap[16], newBitmap[17]) + conjugateBlock.length;
    
    byte[] bNewSize = intToHex(newSize);
    byte[] bColorStart = intToHex(colorStart);
    //byte[] bNewHeaderSize = intToHex(newHeaderSize);

    for (int i = 0; i < 4; i++) {
      newBitmap[2+i] = bNewSize[0+i];
    }
    for (int i = 0; i < 4; i++) {
      newBitmap[10+i] = bColorStart[0+i];
    }
    
    System.arraycopy(intToHex(messageLength), 0, newBitmap, (oldColorStart), 4);
   
    /* Masukin Conjugate Map nya */
    System.arraycopy(conjugateBlock, 0, newBitmap, (oldColorStart+4), conjugateBlock.length);
    
    /* Mulai Color */
    int bit = colorStart;
    for (int i = 0; i < blockY * 8; i++) {
      for (int j = 0; j < blockX * 8; j++) {
        temp = intToHex(colorData[i][j]);
        for (int b = 0; b < bpp; b++) {
          newBitmap[bit] = temp[b];
          bit++;
        }
      }
    }
   
    return newBitmap;
  }

  public int getMaximumSize(double threshold) {
    int valid = 0;
    for (int i = 0; i < blockY; i++) {
      for (int j = 0; j < blockX; j++) {
        valid += (blocks[i][j]).getFeasiblePlaneCount(threshold);
      }
    }
    return valid * 8; /* Dalam Bytes */

  }

  private int xorshiftplus(int seed[]) {
    int x = seed[0];
    int y = seed[1];
    
    seed[0] = y;
    x ^= x << 23; // a
    seed[1] = x ^ y ^ (x >> 17) ^ (y >> 26); // b, c
    return seed[1] + y;
  }
  
  private int[] keyToSeed(String key) {
    int seed[] = new int[2];
    int seed1 = 1234;
    int seed2 = 4321;
    
    for (int i=0; i < key.length(); i++) {
        seed2 += (int) key.charAt(i);
        seed1 += (int) key.charAt(key.length() - i - 1) * 3;
    }  
    
    seed[0] = seed1 * 17;
    seed[1] = seed2 * 3;    
    return seed;    
  }
  
  public boolean insertMessage(StringBlock message, String key, double threshold) {
    int block = 0;
    int successPlane = 0;
    
    int tempseed[] = keyToSeed(key);

    for (int i = 0; i < message.getPlaneNumber(); i++) {
      boolean stillTrying = true;
      
      tempseed[0] = xorshiftplus(tempseed);
      tempseed[1] = xorshiftplus(tempseed);

      int y = abs(tempseed[0] % blockY);
      int x = abs(tempseed[1] % blockX);
      
      if (y < blockY) {
        while (!blocks[y][x].insertMessagePlane(message.getPlane(i), threshold) && stillTrying) {
          ++block;

          tempseed[0] = xorshiftplus(tempseed);
          tempseed[1] = xorshiftplus(tempseed);
 
          y = abs(tempseed[0] % blockY);
          x = abs(tempseed[1] % blockX);           
          
          if (y >= blockY) {
            stillTrying = false;
            y = 0;
            x = 0;
          }
        }
        if (stillTrying) {
          successPlane++;
        }
      } else {
        stillTrying = false;
      }
    }
    
    conjugateBlock = createConjugateBlock(message.conjugateMap, message.planenumber);
    messageLength = message.planenumber;
    
    return (successPlane == message.getPlaneNumber());
  }

  public void decrypt(double threshold, String key) {
    
    int tempseed[] = keyToSeed(key);
    
    String message = "";

    for (int i = 0; i < messageLength; i++) {
      String ret = "";
      do {
        tempseed[0] = xorshiftplus(tempseed);
        tempseed[1] = xorshiftplus(tempseed);

        int y = abs(tempseed[0] % blockY);
        int x = abs(tempseed[1] % blockX); 

        ret = blocks[y][x].getMessagePlane(threshold);
      } while (ret.equals(""));
      message += ret;
    }
    
    StringBlock sb = new StringBlock(message, 0);
    
    for (int i = 0; i < conjugateBlock.length; i++) {
      int temp = hexToInt(conjugateBlock[i], conjugateBlock[i+1], conjugateBlock[i+2], conjugateBlock[i+3]);
       sb.getPlane(temp).conjugate();
       System.out.println("conj " + temp);
      i += 3;
    }
     
    String ret = "";
    for (int i = 0; i < sb.getPlaneNumber(); i++) {
      sb.getPlane(i).genereateString();
      ret = ret + sb.getPlane(i).detectedString;
    }
    
    System.out.println("msg: " + ret);
  }
  
  public int getColorStart() {
    return colorStart;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public int getBPP() {
    return bpp;
  }

  public void constructNewBitmap() {
    for (int i = 0; i < blockY; i++) {
      for (int j = 0; j < blockX; j++) {
        blocks[i][j].constructNewBlock();
      }
    }

    for (int i = 0; i < blockY * 8; i++) {
      for (int j = 0; j < blockX * 8; j++) {
        colorData[i][j] = blocks[i / 8][j / 8].data[i % 8][j % 8];
      }
    }
  }
  
  public float calculatepsnr(Bitmap bitmap){
      
      int sum=0;
      for(int i=0;i<height;i++){
          for(int j=0;j<width;j++){
              for(int k=0;k<bpp;k++){
              }
              sum=(int) (sum+Math.pow((colorData[i][j]%256-bitmap.colorData[i][j]%256),2.0));
              sum=(int) (sum+Math.pow(((colorData[i][j]/256)%256-(bitmap.colorData[i][j]/256)%256),2.0));
              sum=(int) (sum+Math.pow(((colorData[i][j]/(256*256))%256-(bitmap.colorData[i][j]/(256*256))%256),2.0));
              sum=(int) (sum+Math.pow(((colorData[i][j]/(256*256*256))%256-(bitmap.colorData[i][j]/(256*256*256))%256),2.0));
          }
      }
      float rms=(float) Math.sqrt(1.0/(width*height*bpp)*sum);
      float psnr=(float) (20*Math.log10(256/rms));
      return psnr;
  }
}
