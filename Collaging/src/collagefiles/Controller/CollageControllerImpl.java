package collagefiles.Controller;


import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

import collagefiles.model.BasicCollageProject;
import collagefiles.model.Image;
import collagefiles.model.Pixel;
import collagefiles.model.Project;


public class CollageControllerImpl implements CollageController {

  private final Readable input;
  private Project currentProject;


  public CollageControllerImpl(Readable input)
          throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("provided readable input cannot be null.");
    }
    this.input = input;
    this.currentProject = null;
  }

  @Override
  public void runProgram() throws IllegalStateException {

    Scanner scan = new Scanner(this.input);

    String input = scan.next();

    boolean systemRun = true;

    while (systemRun) {

      switch (input) {
        case "q":
          System.out.println("q");
          systemRun = false;
        case "new-project":
          System.out.println("new-project");
          int width = scan.nextInt();
          System.out.println(width);
          int height = scan.nextInt();
          System.out.println(height);
          this.currentProject = new BasicCollageProject(width, height);
          this.currentProject.addLayer("layer-1");

        case "load-project":
          String loadProjectPath = scan.next();
          this.currentProject = this.readProject(loadProjectPath);


        case "save-project":
          String saveProjectPath = scan.next();
          String projectString = this.currentProject.saveProject(saveProjectPath);
          try {
            FileWriter fr = new FileWriter(new File(saveProjectPath, projectString));
          } catch (IOException io) {

          }

        case "save-image":
          String saveImagePath = scan.next();
          String imageString = this.currentProject.saveImage(saveImagePath);
          try {
            FileWriter fr = new FileWriter(new File(saveImagePath,imageString));
          } catch (IOException io) {
          }

        case "set-filter":
          String layer = scan.next();
          String filter = scan.next();
          this.currentProject.setFilter(layer, filter);

        case "add-layer":
          String layerToAdd = scan.next();
          this.currentProject.addLayer(layerToAdd);

        case "add-image":
          String layerToAddTo = scan.next();
          String imageToAdd = scan.next();
          int xPos = scan.nextInt();
          int yPos = scan.nextInt();
          this.currentProject.addImageToLayer(layerToAddTo, this.readImage(imageToAdd), xPos, yPos);
      }
    }


    // check for next line input
    // if q: quit
    // if new-project: make new Project
    // if load-project: create Project based on txt w/ exception if not available
    // if save-project: adjust txt
    // if save-image: create new PPM based on current project


//     check for next line input
//     if q: quit
//     if new-project: make new Project
//     if load-project: create Project based on txt w/ exception if not available
//     if save-project: adjust txt
//     if save-image: create new PPM based on current project
  }

  private Project readProject(String path) throws IllegalArgumentException {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      System.out.println("File "+path+ " not found!");
      Scanner tryAgain = new Scanner(this.input);
      this.readProject(tryAgain.next());
    }

    String title = null;
    int width = -1;
    int height = -1;
    int maxVal = -1;
    String layerName = null;
    String filterName = null;
    ArrayList<ArrayList<Pixel>> nextPixels = new ArrayList<>();

    //if (title == null && width == -1 && height == -1 && maxVal == -1);
    title = sc.next();
    width = sc.nextInt();
    height = sc.nextInt();
    maxVal = sc.nextInt();

    this.currentProject = new BasicCollageProject(width, height, maxVal);

    while (sc.hasNextLine()) {

      layerName = sc.next();
      filterName = sc.next();

      for (int y = 0; y < height; y++) {
        nextPixels.add(new ArrayList<Pixel>());
      }
      for (List l : nextPixels) {
        for (int x = 0; x < width; x++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          int a = sc.nextInt();
          l.add(new Pixel(new Color(r, g, b, a)));
        }
      }
      this.currentProject.addLayer(layerName);
      this.currentProject.addImageToLayer(layerName, new Image(nextPixels), 0, 0);
      this.currentProject.setFilter(layerName, filterName);
    }
    return this.currentProject;
  }

  private Image readImage(String path){
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(path));
    }
    catch (FileNotFoundException e) {
      System.out.println("File "+path+ " not found!");
      Scanner tryAgain = new Scanner(this.input);
      return this.readImage(tryAgain.next());
      // return or repeat statement here
    }
    StringBuilder imageBuilder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0)!='#') {
        imageBuilder.append(s+System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(imageBuilder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
//    System.out.println("Width of image: "+width);

    int height = sc.nextInt();

//    int maxValue = sc.nextInt();
//    System.out.println("Maximum value of a color in this file (usually 255): "+maxValue);
    ArrayList<ArrayList<Pixel>> pixels;
    pixels = new ArrayList<>();

    //adding the rows of pixels
    for (int i=0; i< height; i++){
      pixels.add(new ArrayList<Pixel>());
    }
    //adding pixels to each row
    for (List l: pixels) {
      for (int j=0;j<width;j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        l.add(new Pixel(new Color(r,g,b)));
//        System.out.println("Color of pixel ("+j+","+i+"): "+ r+","+g+","+b);

      }
    }

    Image readImage = new Image(pixels);
    return readImage;
  }
}
//
