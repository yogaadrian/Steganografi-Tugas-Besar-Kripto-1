/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Bitmap.Bitmap;
import FileReader.FileReader;
import FileReader.ImageConverter;
import Message.StringBlock;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author kevin
 */
public class MainMenu extends javax.swing.JFrame {

    File input;
    
    
    File output;
    Image imageOutput;

    /**
     * Creates new form MainMenu
     */
    public MainMenu() {
      initComponents();        
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    imageOpener = new javax.swing.JFileChooser();
    insertCipherGroup = new javax.swing.ButtonGroup();
    extractCipherGroup = new javax.swing.ButtonGroup();
    jTabbedPane1 = new javax.swing.JTabbedPane();
    jPanel3 = new javax.swing.JPanel();
    btnOpenImageInsert = new javax.swing.JButton();
    imageNameInputInsert = new javax.swing.JTextField();
    fileNameInput = new javax.swing.JTextField();
    jLabel2 = new javax.swing.JLabel();
    btnSelectFile = new javax.swing.JButton();
    jLabel3 = new javax.swing.JLabel();
    jPanel5 = new javax.swing.JPanel();
    jLabel8 = new javax.swing.JLabel();
    textThresholdInsert = new javax.swing.JTextField();
    jPanel2 = new javax.swing.JPanel();
    rbVigenereInsert = new javax.swing.JRadioButton();
    textKeyInsert = new javax.swing.JTextField();
    jLabel6 = new javax.swing.JLabel();
    jLabel10 = new javax.swing.JLabel();
    textOutputNameInsert = new javax.swing.JTextField();
    rbNoCipherInsert = new javax.swing.JRadioButton();
    btnInsertStegano = new javax.swing.JButton();
    jButton1 = new javax.swing.JButton();
    jPanel4 = new javax.swing.JPanel();
    btn_openImage1 = new javax.swing.JButton();
    imageNameInput1 = new javax.swing.JTextField();
    jLabel4 = new javax.swing.JLabel();
    jPanel1 = new javax.swing.JPanel();
    jPanel6 = new javax.swing.JPanel();
    rbNoCipherExtract = new javax.swing.JRadioButton();
    rbVigenereExtract = new javax.swing.JRadioButton();
    jLabel1 = new javax.swing.JLabel();
    jTextField3 = new javax.swing.JTextField();
    jLabel9 = new javax.swing.JLabel();
    jTextField7 = new javax.swing.JTextField();
    jLabel5 = new javax.swing.JLabel();
    jTextField4 = new javax.swing.JTextField();
    btnExtractStegano = new javax.swing.JButton();

    setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
    setTitle("Steganography Application");
    setLocationByPlatform(true);
    setResizable(false);

    btnOpenImageInsert.setText("Select Image");
    btnOpenImageInsert.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnOpenImageInsertActionPerformed(evt);
      }
    });

    imageNameInputInsert.setEditable(false);
    imageNameInputInsert.setForeground(new java.awt.Color(110, 110, 110));
    imageNameInputInsert.setText("No Selected Picture");
    imageNameInputInsert.setToolTipText("");

    fileNameInput.setText("No File Input");
    fileNameInput.setToolTipText("");
    fileNameInput.setEnabled(false);

    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabel2.setText("Message");
    jLabel2.setVerifyInputWhenFocusTarget(false);

    btnSelectFile.setText("Select File");
    btnSelectFile.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnSelectFileActionPerformed(evt);
      }
    });

    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabel3.setText("Cover Image");
    jLabel3.setVerifyInputWhenFocusTarget(false);

    jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Steganography Settings"));

    jLabel8.setText("Threshold");

    textThresholdInsert.setText("0.3");
    textThresholdInsert.setToolTipText("");
    textThresholdInsert.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        textThresholdInsertActionPerformed(evt);
      }
    });

    insertCipherGroup.add(rbVigenereInsert);
    rbVigenereInsert.setSelected(true);
    rbVigenereInsert.setText("Vigenere");
    rbVigenereInsert.setToolTipText("");
    rbVigenereInsert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    rbVigenereInsert.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        rbVigenereInsertActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout jPanel2Layout = new org.jdesktop.layout.GroupLayout(jPanel2);
    jPanel2.setLayout(jPanel2Layout);
    jPanel2Layout.setHorizontalGroup(
      jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel2Layout.createSequentialGroup()
        .add(rbVigenereInsert, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 94, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .add(0, 6, Short.MAX_VALUE))
    );
    jPanel2Layout.setVerticalGroup(
      jPanel2Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(org.jdesktop.layout.GroupLayout.TRAILING, rbVigenereInsert)
    );

    jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel6.setText("Key");

    jLabel10.setText("Output Filename");

    textOutputNameInsert.setText("output");

    insertCipherGroup.add(rbNoCipherInsert);
    rbNoCipherInsert.setText("No Cipher");
    rbNoCipherInsert.setToolTipText("");
    rbNoCipherInsert.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    org.jdesktop.layout.GroupLayout jPanel5Layout = new org.jdesktop.layout.GroupLayout(jPanel5);
    jPanel5.setLayout(jPanel5Layout);
    jPanel5Layout.setHorizontalGroup(
      jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel5Layout.createSequentialGroup()
        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanel5Layout.createSequentialGroup()
            .addContainerGap()
            .add(rbNoCipherInsert, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 95, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 34, Short.MAX_VALUE)
            .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
            .add(textKeyInsert, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 219, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(jPanel5Layout.createSequentialGroup()
            .add(18, 18, 18)
            .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jLabel8)
              .add(textThresholdInsert, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 107, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(52, 52, 52)
            .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jPanel5Layout.createSequentialGroup()
                .add(jLabel10)
                .add(0, 0, Short.MAX_VALUE))
              .add(textOutputNameInsert))))
        .addContainerGap())
    );
    jPanel5Layout.setVerticalGroup(
      jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel5Layout.createSequentialGroup()
        .addContainerGap()
        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
          .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(jPanel2, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(rbNoCipherInsert))
          .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
            .add(textKeyInsert, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(jLabel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
        .add(18, 18, 18)
        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jLabel8)
          .add(jLabel10))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel5Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(textThresholdInsert, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(textOutputNameInsert, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    btnInsertStegano.setText("Start Stegano");
    btnInsertStegano.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnInsertSteganoActionPerformed(evt);
      }
    });

    jButton1.setText("Check Size Capacity");
    jButton1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jButton1ActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
    jPanel3.setLayout(jPanel3Layout);
    jPanel3Layout.setHorizontalGroup(
      jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanel3Layout.createSequentialGroup()
            .add(jLabel3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
          .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
            .add(jLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(18, 18, 18))
          .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(imageNameInputInsert)
              .add(fileNameInput))
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
            .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
              .add(btnSelectFile, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .add(btnOpenImageInsert, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 135, Short.MAX_VALUE))
            .add(10, 10, 10))
          .add(org.jdesktop.layout.GroupLayout.TRAILING, jPanel3Layout.createSequentialGroup()
            .add(jPanel5, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())
          .add(jPanel3Layout.createSequentialGroup()
            .add(btnInsertStegano, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .add(jButton1)
            .addContainerGap())))
    );
    jPanel3Layout.setVerticalGroup(
      jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel3Layout.createSequentialGroup()
        .addContainerGap()
        .add(jLabel3)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(imageNameInputInsert)
          .add(btnOpenImageInsert, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jLabel2)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(fileNameInput, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(btnSelectFile))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
        .add(jPanel5, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(btnInsertStegano)
          .add(jButton1))
        .add(47, 47, 47))
    );

    jTabbedPane1.addTab("Insert Message", jPanel3);

    btn_openImage1.setText("Select Image");
    btn_openImage1.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btn_openImage1ActionPerformed(evt);
      }
    });

    imageNameInput1.setEditable(false);
    imageNameInput1.setForeground(new java.awt.Color(110, 110, 110));
    imageNameInput1.setText("No Picture Selected");

    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
    jLabel4.setText("Steganed Image");
    jLabel4.setVerifyInputWhenFocusTarget(false);

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Steganography Settings"));

    extractCipherGroup.add(rbNoCipherExtract);
    rbNoCipherExtract.setText("No Cipher");
    rbNoCipherExtract.setToolTipText("");
    rbNoCipherExtract.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    extractCipherGroup.add(rbVigenereExtract);
    rbVigenereExtract.setSelected(true);
    rbVigenereExtract.setText("Vigenere");
    rbVigenereExtract.setToolTipText("");
    rbVigenereExtract.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

    org.jdesktop.layout.GroupLayout jPanel6Layout = new org.jdesktop.layout.GroupLayout(jPanel6);
    jPanel6.setLayout(jPanel6Layout);
    jPanel6Layout.setHorizontalGroup(
      jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel6Layout.createSequentialGroup()
        .add(rbNoCipherExtract, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 122, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(rbVigenereExtract, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
        .addContainerGap())
    );
    jPanel6Layout.setVerticalGroup(
      jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel6Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
        .add(rbNoCipherExtract)
        .add(rbVigenereExtract))
    );

    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("Key");

    jLabel9.setText("Threshold");

    jTextField7.setText("0.3");
    jTextField7.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextField7ActionPerformed(evt);
      }
    });

    jLabel5.setText("Output File Name");

    jTextField4.setText("output");
    jTextField4.setToolTipText("");
    jTextField4.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        jTextField4ActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout jPanel1Layout = new org.jdesktop.layout.GroupLayout(jPanel1);
    jPanel1.setLayout(jPanel1Layout);
    jPanel1Layout.setHorizontalGroup(
      jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel1Layout.createSequentialGroup()
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanel1Layout.createSequentialGroup()
            .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(41, 41, 41)
            .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 42, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(18, 18, 18)
            .add(jTextField3, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 172, Short.MAX_VALUE))
          .add(jPanel1Layout.createSequentialGroup()
            .add(29, 29, 29)
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jLabel9)
              .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 91, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(50, 50, 50)
            .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
              .add(jTextField4)
              .add(jLabel5))))
        .addContainerGap())
    );
    jPanel1Layout.setVerticalGroup(
      jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel1Layout.createSequentialGroup()
        .addContainerGap()
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
          .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
            .add(jTextField3, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(jLabel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(jPanel6, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .add(18, 18, 18)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jLabel9)
          .add(jLabel5))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel1Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(jTextField7, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(jTextField4, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addContainerGap(30, Short.MAX_VALUE))
    );

    btnExtractStegano.setText("Extract Message");
    btnExtractStegano.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        btnExtractSteganoActionPerformed(evt);
      }
    });

    org.jdesktop.layout.GroupLayout jPanel4Layout = new org.jdesktop.layout.GroupLayout(jPanel4);
    jPanel4.setLayout(jPanel4Layout);
    jPanel4Layout.setHorizontalGroup(
      jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel4Layout.createSequentialGroup()
        .addContainerGap()
        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
          .add(jPanel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(jLabel4, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .add(jPanel4Layout.createSequentialGroup()
            .add(imageNameInput1)
            .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
            .add(btn_openImage1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 133, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
          .add(jPanel4Layout.createSequentialGroup()
            .add(btnExtractStegano, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 130, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
            .add(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    jPanel4Layout.setVerticalGroup(
      jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(jPanel4Layout.createSequentialGroup()
        .add(12, 12, 12)
        .add(jLabel4)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
        .add(jPanel4Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
          .add(imageNameInput1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
          .add(btn_openImage1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 23, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
        .add(jPanel1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, 35, Short.MAX_VALUE)
        .add(btnExtractStegano)
        .addContainerGap())
    );

    jTabbedPane1.addTab("Extract Message", jPanel4);

    org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
    getContentPane().setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .addContainerGap()
        .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 549, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
      .add(layout.createSequentialGroup()
        .add(12, 12, 12)
        .add(jTabbedPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 310, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
        .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    pack();
  }// </editor-fold>//GEN-END:initComponents

    private void btnOpenImageInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOpenImageInsertActionPerformed
        // TODO add your handling code here:
        if ( imageOpener.showOpenDialog(this)  == JFileChooser.APPROVE_OPTION ) {
            input = imageOpener.getSelectedFile();
            System.out.println(input.getName());
            if(!input.getName().endsWith(".bmp") && !input.getName().endsWith(".png")) {
                input = null;
                imageNameInputInsert.setText("No Picture Selected");
                JOptionPane.showMessageDialog(null, "Please select a bmp/png");
            } else {
                try {
                    JFrame frame = new JFrame();
                    JLabel label = new JLabel();
                    Image imageInput;
                    imageInput = ImageIO.read(input);
                    label.setIcon(new ImageIcon(imageInput));

                    JLabel filesize = new JLabel();
                    JLabel fileEx = new JLabel(" File extension " + "\t" + " : " + (input.getName().substring(((int)input.getName().length())-3)));
                    String fs = " File Size " + "\t" + " : " + String.valueOf(input.length()) + " bytes";
                    
                    fileEx.setSize(200,15);
                    fileEx.setLocation(15, 15);
                    fileEx.setBackground(Color.white);
                    fileEx.setOpaque(true);
                    
                    filesize.setLocation(15, 35);
                    filesize.setText(fs);
                    filesize.setSize(200, 15);
                    filesize.setBackground(Color.white);
                    filesize.setOpaque(true);

                    frame.add(filesize);
                    frame.add(fileEx);
                    frame.add(label);

                    int fW = 200;
                    int fH = 200;
                    if ( imageInput.getWidth(frame) > fW ) {
                      fW = imageInput.getWidth(frame);
                    }
                    if ( imageInput.getHeight(frame) > fH ) {
                      fH = imageInput.getHeight(frame);
                    }
                    
                    frame.setSize(fW, fH);
                    frame.setVisible(true);
                    imageNameInputInsert.setText(input.getAbsolutePath());
                } catch (IOException ex) {
                    Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_btnOpenImageInsertActionPerformed

    private void btnInsertSteganoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInsertSteganoActionPerformed
        // TODO add your handling code here:
        try {
            boolean enc;
            enc = rbVigenereInsert.isSelected();
            
            Path path = Paths.get(imageNameInputInsert.getText());
            
            FileReader f = new FileReader();
                        
            f.encryptStegano(imageNameInputInsert.getText(), textOutputNameInsert.getText(), 
                            fileNameInput.getText(), 
                            textKeyInsert.getText(), Double.valueOf(textThresholdInsert.getText()), enc);
                        
            JFrame frame = new JFrame();
            JLabel label = new JLabel();
            Image imageInput;
                    
            imageInput = ImageIO.read(new File(textOutputNameInsert.getText()));
            label.setIcon(new ImageIcon(imageInput));
            
            
            JLabel filePNSR = new JLabel();
            filePNSR.setText("PSNR : "+ f.PSNR);
            JLabel filesize = new JLabel();
            JLabel fileEx = new JLabel(" File extension " + "\t" + " : " + (input.getName().substring(((int)input.getName().length())-3)));
            String fs = " File Size " + "\t" + " : " + String.valueOf(input.length()) + " bytes";

            fileEx.setSize(200,15);
            fileEx.setLocation(15, 15);
            fileEx.setBackground(Color.white);
            fileEx.setOpaque(true);

            filesize.setLocation(15, 35);
            filesize.setText(fs);
            filesize.setSize(200, 15);
            filesize.setBackground(Color.white);
            filesize.setOpaque(true);

            filePNSR.setSize(200, 15);
            filePNSR.setLocation(15, 55);
            filePNSR.setBackground(Color.white);
            filePNSR.setOpaque(true);
            
            frame.add(filesize);
            frame.add(fileEx);
            frame.add(label);

            int fW = 200;
            int fH = 200;
            if ( imageInput.getWidth(frame) > fW ) {
              fW = imageInput.getWidth(frame);
            }
            if ( imageInput.getHeight(frame) > fH ) {
              fH = imageInput.getHeight(frame);
            }

            frame.add(filePNSR);
            frame.add(filesize);
            frame.add(fileEx);
            frame.add(label);

            frame.setSize(fW, fH);
            frame.setVisible(true);

        } catch (IOException ex) {
            Logger.getLogger(FileReader.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }//GEN-LAST:event_btnInsertSteganoActionPerformed

    private void btnSelectFileActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSelectFileActionPerformed
        // TODO add your handling code here:
        JFileChooser chooseFile = new JFileChooser();
        chooseFile.showOpenDialog(null);
        File f = chooseFile.getSelectedFile();
        String filename = f.getAbsolutePath();
        fileNameInput.setText(filename);
    }//GEN-LAST:event_btnSelectFileActionPerformed

    private void btn_openImage1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_openImage1ActionPerformed
        JFileChooser chooseFile = new JFileChooser();
        chooseFile.showOpenDialog(null);
        File f = chooseFile.getSelectedFile();
        String filename = f.getAbsolutePath();
        imageNameInput1.setText(filename);
    }//GEN-LAST:event_btn_openImage1ActionPerformed

    private void jTextField4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField4ActionPerformed

    private void rbVigenereInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbVigenereInsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbVigenereInsertActionPerformed

    private void btnExtractSteganoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExtractSteganoActionPerformed
            boolean enc = rbVigenereExtract.isSelected();
            String getImage = imageNameInput1.getText();
            String fileName = jTextField4.getText();
            String key = jTextField3.getText();
            FileReader f = new FileReader();
            f.decryptStegano( getImage, fileName, 
                              key, Double.valueOf(jTextField7.getText()), enc);
            JOptionPane.showMessageDialog(null, "Message have been extracted. Check your folder.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_btnExtractSteganoActionPerformed

    private void textThresholdInsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textThresholdInsertActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_textThresholdInsertActionPerformed

    private void jTextField7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField7ActionPerformed

  private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    // TODO add your handling code here:
    
    Path path = Paths.get(imageNameInputInsert.getText());

    FileReader f = new FileReader();
    JOptionPane.showMessageDialog(null, "Maximum Size: " + f.getMaximumSize(imageNameInputInsert.getText(), Double.valueOf(textThresholdInsert.getText()))
                                      , "Checker", JOptionPane.INFORMATION_MESSAGE);
    
  }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try {
          /* Set the Nimbus look and feel */
          UIManager.setLookAndFeel(
            UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
          Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
          Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
          Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
          Logger.getLogger(MainMenu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
    }
    
    private javax.swing.JFileChooser jFileChooser1;
  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JButton btnExtractStegano;
  private javax.swing.JButton btnInsertStegano;
  private javax.swing.JButton btnOpenImageInsert;
  private javax.swing.JButton btnSelectFile;
  private javax.swing.JButton btn_openImage1;
  private javax.swing.ButtonGroup extractCipherGroup;
  private javax.swing.JTextField fileNameInput;
  private javax.swing.JTextField imageNameInput1;
  private javax.swing.JTextField imageNameInputInsert;
  private javax.swing.JFileChooser imageOpener;
  private javax.swing.ButtonGroup insertCipherGroup;
  private javax.swing.JButton jButton1;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel10;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JPanel jPanel1;
  private javax.swing.JPanel jPanel2;
  private javax.swing.JPanel jPanel3;
  private javax.swing.JPanel jPanel4;
  private javax.swing.JPanel jPanel5;
  private javax.swing.JPanel jPanel6;
  private javax.swing.JTabbedPane jTabbedPane1;
  private javax.swing.JTextField jTextField3;
  private javax.swing.JTextField jTextField4;
  private javax.swing.JTextField jTextField7;
  private javax.swing.JRadioButton rbNoCipherExtract;
  private javax.swing.JRadioButton rbNoCipherInsert;
  private javax.swing.JRadioButton rbVigenereExtract;
  private javax.swing.JRadioButton rbVigenereInsert;
  private javax.swing.JTextField textKeyInsert;
  private javax.swing.JTextField textOutputNameInsert;
  private javax.swing.JTextField textThresholdInsert;
  // End of variables declaration//GEN-END:variables
}
