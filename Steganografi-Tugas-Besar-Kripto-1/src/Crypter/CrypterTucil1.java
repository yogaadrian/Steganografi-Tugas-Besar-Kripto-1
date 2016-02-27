/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crypter.tucil.pkg1;


/**
 *
 * @author yoga
 */
public class CrypterTucil1 {

    /**
     * @param args the command line arguments
     */

    //FUNGSI ENKRIPSI
    public static String encrypt(String plaintext, String key, int algorithm, int form) {
        String ans = "";
        if (algorithm == 1) {
            //ALGORITMA VIGENERE CHIPERTEXT STANDARD

            int j = -1;
            for (int i = 0; i < plaintext.length(); i++) {
                Character chartemp = plaintext.charAt(i);
                if (Character.isLetter(chartemp)) {
                    int temp = (int) plaintext.charAt(i);
                    j++;
                    int offset;
                    if (temp < 95) {
                        offset = 65;
                    } else {
                        offset = 97;
                    }
                    int keytemp = (int) key.charAt(j % key.length());
                    if (keytemp < 95) {
                        temp = (temp - offset + key.charAt(j % key.length()) - 65) % 26 + offset;
                        ans = ans.concat(Character.toString((char) temp));
                    } else {
                        temp = (temp - offset + key.charAt(j % key.length()) - 97) % 26 + offset;
                        ans = ans.concat(Character.toString((char) temp));
                    }
                } else {
                    ans = ans.concat(chartemp.toString());
                }
            }

        } else if (algorithm == 2) {
            //ALGORITMA VIGENERE CHIPERTEXT EXTENDED

            for (int i = 0; i < plaintext.length(); i++) {

                byte temp = (byte) plaintext.charAt(i);
                temp = (byte) ((temp + (byte) key.charAt(i % key.length())) % 256);
                ans = ans.concat(Character.toString((char) temp));

            }

        } else {
            //ALGORITMA PLAYFAIR CHIPER
            //INISIASI

            String keyblock = "abcdefghiklmnopqrstuvwxyz"; //PENANDA HURUF YANG SUDAH DIAMBIL
            String plaintextlow = plaintext.toLowerCase();
            plaintextlow = plaintextlow.replace("j", "");
            String keytemp = key.toLowerCase();
            keytemp = keytemp.replace("j", "");//menghilangkan huruf j dalam key
            Character[][] keymatrix = new Character[5][5];
            int x = 0, y = 0;

            //MEMASUKKAN SETIAP HURUF DALAM KUNCI KE DALAM MATRIX TANPA PENGULANGAN HURUF
            for (int i = 0; i < keytemp.length(); i++) {
                if (Character.isLetter(keytemp.charAt(i))) {
                    keymatrix[x][y] = keytemp.charAt(i);
                    keyblock = keyblock.replace(Character.toString(keytemp.charAt(i)), "");
                    keytemp = keytemp.replace(Character.toString(keytemp.charAt(i)), "");
                    i--;

                    if (y == 4) {
                        x++;
                        y = 0;
                    } else {
                        y++;
                    }
                }
            }
            //MEMASUKKAN SISA HURUF YANG BELUM TERMASUK KECUALI J
            for (int i = 0; i < keyblock.length(); i++) {
                keymatrix[x][y] = keyblock.charAt(i);
                if (y == 4) {
                    x++;
                    y = 0;
                } else {
                    y++;
                }
            }
            char first, second;
            //AMBIL 2 CHARACTER PERTAMA DARI PLAINTEXT
            for (int i = 0; i < plaintextlow.length(); i++) {
                if (Character.isLetter(plaintextlow.charAt(i))) {
                    first = plaintextlow.charAt(i);
                    i++;
                    if (i >= plaintextlow.length()) {
                        second = 'z';
                    } else if (Character.isLetter(plaintextlow.charAt(i))) {
                        second = plaintextlow.charAt(i);
                    } else {
                        //ulangi sampai ketemu alfabet kedua
                        while (i < plaintextlow.length() && !Character.isLetter(plaintextlow.charAt(i))) {
                            i++;
                        }
                    }

                    if (i < plaintextlow.length()) {
                        second = plaintextlow.charAt(i);
                        //jika huruf perrtama dan kedua sama
                        if (first == second) {
                            i--;
                            second = 'z';

                        }
                    } else {
                        //jika jumlah huruf ganjil
                        second = 'z';
                    }

                    int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
                    //mencari posisi 2 huruf tersebut dalam tabel
                    for (int j = 0; j < 5; j++) {
                        for (int k = 0; k < 5; k++) {
                            if (keymatrix[j][k] == first) {
                                x1 = k;
                                y1 = j;
                            }
                            if (keymatrix[j][k] == second) {
                                x2 = k;
                                y2 = j;
                            }

                        }
                    }

                    if (x1 == x2) {
                        if (y1 == 4) {
                            y1 = 0;
                        } else {
                            y1++;
                        }

                        if (y2 == 4) {
                            y2 = 0;
                        } else {
                            y2++;
                        }
                    } else if (y1 == y2) {
                        if (x1 == 4) {
                            x1 = 0;
                        } else {
                            x1++;
                        }

                        if (x2 == 4) {
                            x2 = 0;
                        } else {
                            x2++;
                        }
                    } else {
                        int temp = 0;
                        temp = x1;
                        x1 = x2;
                        x2 = temp;

                    }

                    ans = ans.concat(Character.toString(keymatrix[y1][x1]));
                    ans = ans.concat(Character.toString(keymatrix[y2][x2]));

                }

            }
        }
        if (form == 1) {
            return ans;
        } else if (form == 2) {
            ans=ans.replace(" ","");
            return ans;
        } else {
            ans=ans.replace(" ","");
            String finalans="";
            for(int i=0;i<ans.length();i++){
                finalans=finalans.concat(Character.toString(ans.charAt(i)));
                if(i%5==4){
                    finalans=finalans.concat(" ");
                }
            }
            return finalans;
        }

    }

//FUNGSI DEKRIPSI
    public static String decrypt(String chipertext, String key, int algorithm,int form) {
        String ans = "";
        //ALGORITMA VINEGERE CHIPER TEXT STANDARD
        if (algorithm == 1) {

            int j = -1;
            for (int i = 0; i < chipertext.length(); i++) {
                Character chartemp = chipertext.charAt(i);
                if (Character.isLetter(chartemp)) {
                    int temp = (int) chipertext.charAt(i);
                    j++;
                    int offset;
                    if (temp < 95) {
                        offset = 65;
                    } else {
                        offset = 97;
                    }
                    int keytemp = (int) key.charAt(j % key.length());
                    if (keytemp < 95) {
                        temp = ((temp - offset) - (key.charAt(j % key.length()) - 65));
                        if (temp < 0) {
                            temp = temp + 26;
                        }
                        ans = ans.concat(Character.toString((char) (temp + offset)));
                    } else {
                        temp = ((temp - offset) - (key.charAt(j % key.length()) - 97));
                        if (temp < 0) {
                            temp = temp + 26;
                        }
                        ans = ans.concat(Character.toString((char) (temp + offset)));
                    }
                } else {
                    ans = ans.concat(chartemp.toString());
                }
            }

        } else if (algorithm == 2) {
            //ALGORITMA VIGENERE CHIPERTEXT EXTENDED

            for (int i = 0; i < chipertext.length(); i++) {

                byte temp = (byte) chipertext.charAt(i);
                temp = (byte) (temp - (byte) key.charAt(i % key.length()));
                if (temp < 0) {
                    temp = (byte) (temp + 256);
                }
                ans = ans.concat(Character.toString((char) temp));

            }

        } else {
            //ALGORITMA PLAYFAIR CHIPER
            //INISIASI

            String keyblock = "abcdefghiklmnopqrstuvwxyz"; //PENANDA HURUF YANG SUDAH DIAMBIL
            String chipertextlow = chipertext.toLowerCase();
            chipertextlow = chipertextlow.replace("j", "");
            String keytemp = key.toLowerCase();
            keytemp = keytemp.replace("j", "");//menghilangkan huruf j dalam key
            Character[][] keymatrix = new Character[5][5];
            int x = 0, y = 0;

            //MEMASUKKAN SETIAP HURUF DALAM KUNCI KE DALAM MATRIX TANPA PENGULANGAN HURUF
            for (int i = 0; i < keytemp.length(); i++) {
                if (Character.isLetter(keytemp.charAt(i))) {
                    keymatrix[x][y] = keytemp.charAt(i);
                    keyblock = keyblock.replace(Character.toString(keytemp.charAt(i)), "");
                    keytemp = keytemp.replace(Character.toString(keytemp.charAt(i)), "");
                    i--;

                    if (y == 4) {
                        x++;
                        y = 0;
                    } else {
                        y++;
                    }
                }
            }
            //MEMASUKKAN SISA HURUF YANG BELUM TERMASUK KECUALI J
            for (int i = 0; i < keyblock.length(); i++) {
                keymatrix[x][y] = keyblock.charAt(i);
                if (y == 4) {
                    x++;
                    y = 0;
                } else {
                    y++;
                }
            }
            char first, second;
            //AMBIL 2 CHARACTER PERTAMA DARI PLAINTEXT
            for (int i = 0; i < chipertextlow.length(); i++) {
                first = chipertextlow.charAt(i);
                i++;
                second = chipertextlow.charAt(i);

                int x1 = 0, x2 = 0, y1 = 0, y2 = 0;
                //mencari posisi 2 huruf tersebut dalam tabel
                for (int j = 0; j < 5; j++) {
                    for (int k = 0; k < 5; k++) {
                        if (keymatrix[j][k] == first) {
                            x1 = k;
                            y1 = j;
                        }
                        if (keymatrix[j][k] == second) {
                            x2 = k;
                            y2 = j;
                        }

                    }
                }
                if (x1 == x2) {
                    if (y1 == 0) {
                        y1 = 4;
                    } else {
                        y1--;
                    }

                    if (y2 == 0) {
                        y2 = 4;
                    } else {
                        y2--;
                    }
                } else if (y1 == y2) {
                    if (x1 == 0) {
                        x1 = 4;
                    } else {
                        x1--;
                    }

                    if (x2 == 0) {
                        x2 = 4;
                    } else {
                        x2--;
                    }
                } else {
                    int temp = 0;
                    temp = x1;
                    x1 = x2;
                    x2 = temp;

                }
                ans = ans.concat(Character.toString(keymatrix[y1][x1]));
                if (keymatrix[y2][x2] != 'z') {
                    ans = ans.concat(Character.toString(keymatrix[y2][x2]));
                }
            }

        }
        if (form == 1) {
            return ans;
        } else if (form == 2) {
            ans=ans.replace(" ","");
            return ans;
        } else {
            ans=ans.replace(" ","");
            String finalans="";
            for(int i=0;i<ans.length();i++){
                finalans=finalans.concat(Character.toString(ans.charAt(i)));
                if(i%5==4){
                    finalans=finalans.concat(" ");
                }
            }
            return finalans;
        }
    }

}
