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
  
  public StringBlock(String data) {
    size = 8;
    this.data = data;
    planenumber = data.length()*8/(size*size);
    planes = new Plane[planenumber];
    
    for ( int i = 0; i < planenumber; i++ ) {
      planes[i] = new Plane(i * size, data, size);
      planes[i].print();
    }
  }  
}
