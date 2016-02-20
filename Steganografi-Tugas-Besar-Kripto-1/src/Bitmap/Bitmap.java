/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bitmap;

import Message.StringBlock;
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

  public Bitmap(byte[] data) {
    rawData = data;

    colorStart = hexToInt(data[10], data[11], data[12], data[13]);
    width = hexToInt(data[18], data[19], data[20], data[21]);
    height = hexToInt(data[22], data[23], data[24], data[25]);
    bpp = hexToInt(data[28], data[29], 0, 0) / 8;
    padding = (width * bpp) % 4;
    
    blockY = (int) Math.ceil(height / 8.0);
    blockX = (int) Math.ceil(width / 8.0);

    colorData = new int[blockY * 8][blockX * 8];

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
        blocks[i][j] = new Block(i * 8, j * 8, colorData, bpp);
        //blocks[i][j].print();
      }
    }
  }

  private int hexToInt(int a, int b, int c, int d) {
    /* Little Endian */
    return a
      + (b / 16) * 16 * 16 * 16 + (b % 16) * 16 * 16
      + (c / 16) * 16 * 16 * 16 * 16 * 16 + (c % 16) * 16 * 16 * 16 * 16
      + (d / 16) * 16 * 16 * 16 * 16 * 16 * 16 * 16 + (d % 16) * 16 * 16 * 16 * 16 * 16 * 16;
  }
  
  private byte[] intToHex(int a){
    int[] arr = new int[4];
    
    arr[0]= a % (16*16);
    arr[1]= a / (16 * 16) % (16 * 16) ;
    arr[2]= a / (16 * 16 * 16 * 16) % (16 * 16);
    arr[3]= a /(16 * 16 * 16 * 16 * 16 * 16) % (16 * 16);
    
    byte[] arrbyte= new byte[4];
    arrbyte[0]=(byte) arr[0];
    arrbyte[1]=(byte) arr[1];
    arrbyte[2]=(byte) arr[2];
    arrbyte[3]=(byte) arr[3];
    
    return arrbyte;
  }
  
  public byte[] extractBitmap() {
    constructNewBitmap();
    byte[] newBitmap = new byte[colorStart 
                                + (blockY * 8) * ( blockX * 8 ) * bpp ];
    
    /* Copy Header */
    for (int i = 0; i < colorStart; i++) {
      newBitmap[i] = rawData[i];
    }
    
    byte[] temp;
    temp = intToHex(blockX * 8);
    for (int i = 0; i < 4; i++) {
      newBitmap[18+i] = temp[i];
    }
    temp = intToHex(blockY * 8);
    for (int i = 0; i < 4; i++) {
      newBitmap[22+i] = temp[i];
    }
    
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

  public boolean insertMessage(StringBlock message, double threshold) {
    int block = 0;
    int successPlane = 0;

    for (int i = 0; i < message.getPlaneNumber(); i++) {
      boolean stillTrying = true;
      int y = block / blockY;
      int x = block - (y * block);

      if (y < blockY) {
        while (!blocks[y][x].insertMessagePlane(message.getPlane(i), threshold) && stillTrying) {
          ++block;

          y = block / blockY;
          x = block - (y * block);

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

    return (successPlane == message.getPlaneNumber());
  }

  private void convertAllToPBC() {
    for (int block = 0; block < (blockX * blockY); block++) {
      int y = block / blockY;
      int x = block - (y * block);
      blocks[y][x].convertAllToPBC();
    }
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

  public String getMessage(double threshold) {
    String message = "";
    for (int i = 0; i < blockY; i++) {
      for (int j = 0; j < blockX; j++) {
        for (int k = 0; k < blocks[i][j].planes.length; k++) {
          if (blocks[i][j].planes[k].getComplexity() > threshold) {
            for (int l = 0; l < blocks[i][j].planes[k].size; l++) {
              String biner = "";
              for (int m = 0; m < blocks[i][j].planes[k].size; m++) {
                biner = biner.concat(Character.toString(blocks[i][j].planes[k].data[l][m]));
              }
              message = message.concat(Character.toString((char) Integer.parseInt(biner, 2)));
              //System.out.println(message);
            }
          }
        }
      }
    }
    return message;
  }

  public void constructNewBitmap() {
    for (int i = 0; i < blockY * 8; i++) {
      for (int j = 0; j < blockX * 8; j++) {
        blocks[i / 8][j / 8].constructNewBlock();
        colorData[i][j] = blocks[i / 8][j / 8].data[i % 8][j % 8];
      }
    }
  }
}
