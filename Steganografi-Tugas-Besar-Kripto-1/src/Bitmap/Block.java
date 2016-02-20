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
  int lastInsert = -1;
  public Plane[] planes;
  
  public Block(int i, int j, int[][] colorData, int bpp) {
    int x = 0;
    int y = 0;
    this.bpp = bpp; 
    size = 8;
    
    data = new int[size][size];
    
    int n, m;
    for (m = i; m < i + size; m++) {
      for (n = j; n < j + size; n++) {
        data[y][x] = colorData[m][n];
        x++;
      }
      x = 0;
      y++;
    }
    
    planes = new Plane[bpp * size];
    
    for(int a=0; a < bpp * size; a++) {
      planes[a] = new Plane(a, data, size);
    }
  }
  
  public void constructNewBlock() {
    convertAllToPBC();
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        String binary = "";
        
        for(int a = 0; a < bpp * size; a++) {
          binary = planes[a].getBitFromBlock(x, y) + binary;
        } 

        data[y][x] = Integer.parseInt(binary,2);
      }
    }
  }
  
  public int getFeasiblePlaneCount(double threshold) {
    int valid = 0;
    for (int i = 0; i < size; i++) {
      if ( (planes[i]).getComplexity() > threshold ) valid++;
    }
    return valid;
  }
  
  public boolean insertMessagePlane(Plane message, double threshold) {
    int i = lastInsert + 1;
    boolean success = false;
    
    while (i < size) {
      if (planes[i].getComplexity() > threshold) {
        planes[i].changeData(message.getData());
        success = true;
        lastInsert = i;
        //System.out.println("Changed Plane " + i);

        i = size;
      } else {
        ++i;
      }      
    }
    
    return success;
  }
  
  public void convertAllToPBC() {
    for (int i = 0; i < size * bpp; i++) {
      planes[i].toPBC();
    }
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
