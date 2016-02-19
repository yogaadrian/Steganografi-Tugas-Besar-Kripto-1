/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Bitmap;

/**
 *
 * @author Asus
 */
public class Bitmap {
  int colorStart;
  int bpp;
  
  int width;
  int height;
  
  byte[] rawData;
  byte[] colorData;
  
  Bitmap(byte[] data ){
    rawData = data;
  
    colorStart = hexToInt(data[10], data[11], data[12], data[13]);
    width = hexToInt(data[18], data[19], data[20], data[21]);
    height = hexToInt(data[22], data[23], data[24], data[25]);  
    bpp = hexToInt(data[28], data[29], 0, 0);
  }
  
  private int hexToInt(int a, int b, int c, int d) {
    /* Little Endian */
    return a 
        + (b / 16) * 16 * 16 * 16 + (b % 16) * 16 * 16
        + (c / 16) * 16 * 16 * 16 * 16 * 16 + (c % 16) * 16 * 16 * 16 * 16
        + (d / 16) * 16 * 16 * 16 * 16 * 16 * 16 * 16 + (d % 16) * 16 * 16 * 16 * 16 * 16 * 16;
  }
  
  int getColorStart() {
    return colorStart;
  }
  
  int getWidth() {
    return width;
  }
  
  int getHeight() {
    return height;
  }
  
  int getBPP() {
    return bpp;
  }
}
