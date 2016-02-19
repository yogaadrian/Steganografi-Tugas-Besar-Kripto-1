package Message;

import Bitmap.Plane;

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
  int planenumber;
  String data;
  Plane[] planes;
  
  public StringBlock(String data, double threshold) {
    size = 8;
    this.data = data;
    planenumber = (int) Math.ceil(data.length()*8/(double)(size*size));
    planes = new Plane[planenumber];
    
    for ( int i = 0; i < planenumber; i++ ) {
      planes[i] = new Plane(i, data, size);
      if ( planes[i].getComplexity() < threshold ) planes[i].conjugate();
      //planes[i].print();
    }
  }  
  
  public int getPlaneNumber() {
    return planenumber;
  }
  
  public Plane getPlane(int i) {
    return planes[i];
  }
}
