package collagefiles.Controller;


import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
          // read in project from txt file
        case "save-project":
          String saveProjectPath = scan.next();
        case "save-image":
          String saveImagePath = scan.next();
        case "set-filter":
          String layer = scan.next();
          String filter = scan.next();
        case "add-layer":
          String layerToAdd = scan.next();
        case "add-image":
          String imageToAdd = scan.next();
          int xPos = scan.nextInt();
          int yPos = scan.nextInt();

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
