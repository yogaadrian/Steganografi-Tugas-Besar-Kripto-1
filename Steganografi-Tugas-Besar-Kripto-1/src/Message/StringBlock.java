package Message;

import Bitmap.Plane;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Asus
 */
public class StringBlock {
  int size;
  public int planenumber;
  String data;
  Plane[] planes;
  public ArrayList<Integer> conjugateMap = new ArrayList();
  
  public StringBlock(String data, double threshold) {
    size = 8;
    this.data = data;
    planenumber = (int) Math.ceil(data.length()*8/(double)(size*size));
    planes = new Plane[planenumber];
    
    for ( int i = 0; i < planenumber; i++ ) {
      planes[i] = new Plane(i, data, size);
      if ( planes[i].getComplexity() < threshold ) {
        planes[i].conjugate();
        System.out.println("conjugate!");
        conjugateMap.add(i);
      }
    }
  }  
  
  public int getPlaneNumber() {
    return planenumber;
  }
  
  public Plane getPlane(int i) {
    return planes[i];
  }
}
