package model;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Image {
List<List<Pixel>> pixels;
  List<List<Pixel>> filterPixels;
  String filePath;
  int width;
  int height;

  public Image(String filename){
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    }
    catch (FileNotFoundException e) {
      System.out.println("File "+filename+ " not found!");
      return;
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0)!='#') {
        builder.append(s+System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
//    System.out.println("Width of image: "+width);
    this.width = width;
    int height = sc.nextInt();
    this.height = height;
//    int maxValue = sc.nextInt();
//    System.out.println("Maximum value of a color in this file (usually 255): "+maxValue);

    //adding the rows of pixels
    for (int i=0; i< this.height; i++){
      this.pixels.add(new ArrayList<Pixel>());
    }
    //adding pixels to each row
    for (List l: this.pixels) {
      for (int j=0;j<width;j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        l.add(new Pixel(new Color(r,g,b)));
//        System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);

      }
    }




  }

  public void filterImageRed(){
    this.filterPixels = pixels;
    for (List<Pixel> row: this.filterPixels){
      for(Pixel pixel: row){
        pixel.colorMeRed();
      }
    }
  }

  public void filterImageGreen(){
    this.filterPixels = pixels;
    for (List<Pixel> row: this.filterPixels){
      for(Pixel pixel: row){
        pixel.colorMeGreen();
      }
    }
  }

  public void filterImageBlue(){
    this.filterPixels = pixels;
    for (List<Pixel> row: this.filterPixels){
      for(Pixel pixel: row){
        pixel.colorMeBlue();
      }
    }
  }

  public void brightenImage(int brightness){
    this.filterPixels = pixels;
    for (List<Pixel> row: this.filterPixels){
      for(Pixel pixel: row){
        pixel.brightenMe(brightness);
      }
    }
  }
  public void darkenImage(int darkness){
    this.filterPixels = pixels;
    for (List<Pixel> row: this.filterPixels){
      for(Pixel pixel: row){
        pixel.darkenMe(darkness);
      }
    }
  }

}
