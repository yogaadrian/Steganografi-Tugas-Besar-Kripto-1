/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bitmap;

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
    
    blocks = new Block[((height/8)+1)][((width/8)+1)];
    for (int i = 0; i < height/8 + 1; i++) {
      for (int j = 0; j < width/8 + 1; j++) {
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
    for (int i = 0; i < height/8 + 1; i++) {
      for (int j = 0; j < width/8 + 1; j++) {
        valid += (blocks[i][j]).getFeasiblePlaneCount(threshold);
      }
    }    
    return valid * 8; /* Dalam Bytes */
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
