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

  int colorStart;
  int bpp;

  int width;
  int height;
  int padding;

  byte[] rawData;
  int[][] colorData;
  
  Block[][] blocks;
  int blockX;
  int blockY;

  public Bitmap(byte[] data) {
    rawData = data;

    colorStart = hexToInt(data[10], data[11], data[12], data[13]);
    width = hexToInt(data[18], data[19], data[20], data[21]);
    height = hexToInt(data[22], data[23], data[24], data[25]);
    bpp = hexToInt(data[28], data[29], 0, 0)/8;
    padding = (width * bpp) % 4;
    colorData = new int[height + (8 - (height % 8))][width + (8 - (width % 8))];

    /* Creating Array of Color Only Data */ 
    int x = colorStart;
    for (int i = 0; i < height + (8 - (height % 8)); i++) {
      for (int j = 0; j < width + (8 - (width % 8)); j++) {
        if ( (i < height) && (j < width)  ) {
          int [] p = new int[4];
          Arrays.fill(p, 0);
          for (int k = 0; k < bpp; k++) {
            p[k] = data[x] & 0x000000FF;
            x++;
          }
          colorData[i][j] = hexToInt(p[0],p[1],p[2],p[3]);
        } else {
          colorData[i][j] = 0;
        }
        //System.out.println(colorData[i][j]);
      }
      
      x = x + padding;
    }
    
    blockY = (int) Math.ceil(height/8.0);
    blockX = (int) Math.ceil(width/8.0);
    
    blocks = new Block[blockY][blockX];
    for (int i = 0; i < blockY; i++) {
      for (int j = 0; j < blockX; j++) {
        blocks[i][j] = new Block(i*8, j*8, colorData, bpp);
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

  public int getMaximumSize( double threshold ) {
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
      int y = block/blockY;
      int x = block-(y * block);
            
      if ( y < blockY ) {
        while (!blocks[y][x].insertMessagePlane(message.getPlane(i), threshold) && stillTrying) {
          ++block;

          y = block/blockY;
          x = block-(y * block);

          if ( y >= blockY ) {
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
}
