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
public class Block {
  int size;
  int bpp; /*in Byte*/
  int data[][];
  Plane[] planes;
  
  public Block(int i, int j, int[][] colorData, int bpp) {
    int x = 0;
    int y = 0;
    this.bpp = bpp; 
    size = 8;
    
    data = new int[size][size];
    
    int n, m;
    for (n = j; n < j + size; n++) {
      for (m = i; m < i + size; m++) {
        data[x][y] = colorData[m][n];
        x++;
      }
      x = 0;
      y++;
    }
    
    planes = new Plane[bpp * size];
    
    for(int a=0; a < bpp * size; a++) {
      planes[a] = new Plane(a, data, size);
      //planes[a].print();
    }
  }
  
  public int getFeasiblePlaneCount(double threshold) {
    int valid = 0;
    for (int i = 0; i < size; i++) {
      if ( (planes[i]).getComplexity() > threshold ) valid++;
    }
    return valid;
  }
  
  public void print() {
    for (int i = 0; i < size; i++) {
      System.out.print("[");
      for (int j = 0; j < size; j++) {
         System.out.print(data[i][j] + ", ");
      }
      System.out.println("]");
    }    
    System.out.println("");
  }
}
