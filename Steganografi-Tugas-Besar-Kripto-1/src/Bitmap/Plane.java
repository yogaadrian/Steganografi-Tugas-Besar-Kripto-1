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
public class Plane {
  int size;
  double complexity;
  char[][] data;
  
  public Plane(int i, int[][] block, int size) {
    this.size = size;
    data = new char[size][size]; 
    
    for (int y = 0; y < size; y++) {
      for (int x = 0; x < size; x++) {
        String binary = Integer.toString(block[y][x], 2);
        if (binary.length() - i - 1 < 0) {
          data[y][x] = '0';
        } else {
          data[y][x] = binary.charAt(binary.length() - i - 1);
        }
      }
    }
    
    toCGC();    
    calculateComplexity();
  }
  
  public Plane(int i, String message, int size) {
    this.size = size;
    data = new char[size][size]; 
           
    for (int y = (i * size); y < (i + 1) * size; y++) {
      String binary = Integer.toString((int)message.charAt(y), 2);
      for (int x = 0; x < size; x++) {
        
        if (binary.length() - x - 1 < 0) {
          data[y][size - x - 1] = '0';
        } else {
          data[y][size - x - 1] = binary.charAt(binary.length() - x - 1);
        }
        
      }
    }
    
    //toCGC();    
    calculateComplexity();
  }
  
  public double getComplexity() {
    return complexity;
  }
  
  private void calculateComplexity() {
    int point = 0;
    
    for (int i = 0; i < size; i++) {
      for (int j = 0; j < size; j++) {
        if ( data[i][j] == '1' ) {
          if(i!=0){
            if (data[i][j]!=data[i-1][j]){
              point++;
            }
          } if(i!=size-1){
            if (data[i][j]!=data[i+1][j]){
              point++;
            }
          } if (j!=0){
            if (data[i][j]!=data[i][j-1]){
              point++;
            }
          } if (j!=size-1){
            if (data[i][j]!=data[i][j+1]){
              point++;
            }
          }
        }
      }
    }
    
    complexity = (double) point / ( ( ( size * size ) / 2 ) * 4 - ( 2 * size ) );
  }
  
  public void toCGC() {
    char[][] cgc;
    cgc = new char[size][size];
        
    for (int j = 0; j < size; j++) {
      cgc[j][0] = data[j][0];
      for (int i = 1; i < size; i++) {
        cgc[j][i] = (data[j][i-1] == data[j][i]) ? '0' : '1';
      }
    }
    
    data = cgc;
  }
  
  public void toPBC() {
    char[][] pbc;
    pbc = new char[size][size];
        
    for (int j = 0; j < size; j++) {
      pbc[j][0] = data[j][0];
      for (int i = 1; i < size; i++) {
        pbc[j][i] = (pbc[j][i-1] == data[j][i]) ? '0' : '1';
      }
    }
    
    data = pbc;    
  }
  
  public void print() {
    for (int i = 0; i < size; i++) {
      System.out.print("[");
      for (int j = 0; j < size; j++) {
         System.out.print(data[i][j] + ", ");
      }
      System.out.println("]");
    }
    System.out.println("Complexity: " + complexity);
    System.out.println("");
  }
}
