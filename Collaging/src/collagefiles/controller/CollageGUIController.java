//package collagefiles.controller;
//
//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import javax.swing.*;
//
//import collagefiles.model.BasicCollageProject;
//import collagefiles.model.Image;
//import collagefiles.model.Layer;
//import collagefiles.model.Pixel;
//import collagefiles.model.Project;
//import collagefiles.view.CollageGUI;
//import collagefiles.view.CollageView;
//import collagefiles.view.GUIView;
//public class CollageGUIController implements ActionListener {
//  Project model;
//  GUIView view;
//  public CollageGUIController(Project p,GUIView v){
//    this.model =p;
//    this.view = v;
//    this.view.setListener(this);
//
//  }
//  @Override
//  public void actionPerformed(ActionEvent e) {
//   Project guiProject = new BasicCollageProject(100,100,255);
//
//    switch (e.getActionCommand()) {
//      case "Add Layer Button":
//        String layerName = JOptionPane.showInputDialog(this.view.getFrame(), "Enter layer name:");
//        guiProject.addLayer(layerName);
//        break;
//      case "Add Image Button":
//        JFileChooser fileChooser = new JFileChooser();
//        String layerToAddToStr = JOptionPane.showInputDialog(this.view.getFrame(), "Add to which layer?");
//
//        int result = fileChooser.showOpenDialog(this.view.getFrame());
//        if (result == JFileChooser.APPROVE_OPTION) {
//          File selectedFile = fileChooser.getSelectedFile();
//
//          String xCoordStr = JOptionPane.showInputDialog(this.view.getFrame(), "Place on what x position");
//          int xCoord = Integer.parseInt(xCoordStr);
//          String yCoordStr = JOptionPane.showInputDialog(this.view.getFrame(), "Place on what y position");
//          int yCoord = Integer.parseInt(yCoordStr);
//
//          guiProject.addImageToLayer(layerToAddToStr,this.readImage(selectedFile.toString()),xCoord,yCoord);
//          System.out.println(selectedFile);
//          // do something with selectedFile
//        }
//        break;
//      default:
//        break;
//    }
//  }
//  private Image readImage(String path) {
//    Scanner sc = null;
//
//    try {
//      sc = new Scanner(new FileInputStream(path));
//    } catch (FileNotFoundException e) {
//      System.out.println("File " + path + " not found!");
//    //  Scanner tryAgain = new Scanner(this.input);
//     // return this.readImage(tryAgain.next());
//    }
//    StringBuilder imageBuilder = new StringBuilder();
//    while (sc.hasNextLine()) {
//      String s = sc.nextLine();
//      if (s.charAt(0) != '#') {
//        imageBuilder.append(s + System.lineSeparator());
//      }
//    }
//
//    sc = new Scanner(imageBuilder.toString());
//
//    String token;
//
//    token = sc.next();
//    if (!token.equals("P3")) {
//      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
//    }
//    int width = sc.nextInt();
//
//    int height = sc.nextInt();
//
//    int maxVal = sc.nextInt();
//
//    ArrayList<ArrayList<Pixel>> pixels;
//    pixels = new ArrayList<>();
//
//    //adding the rows of pixels
//    for (int i = 0; i < height; i++) {
//      pixels.add(new ArrayList<Pixel>());
//    }
//    //adding pixels to each row
//    for (List l : pixels) {
//      for (int j = 0; j < width; j++) {
//        int r = sc.nextInt();
//        int g = sc.nextInt();
//        int b = sc.nextInt();
//        l.add(new Pixel(new Color(r, g, b)));
//
//
//      }
//    }
//
//    Image readImage = new Image(pixels);
//    return readImage;
//  }
//}
