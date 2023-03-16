package Controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.BasicCollageProject;
import model.Pixel;

import model.Project;


public class CollageControllerImpl implements CollageController {

  private final Readable input;
  private  Project currentProject;


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

    switch(input) {
      case "q":
        return;
      case "new-project":
        int width = scan.nextInt();
        int height = scan.nextInt();
        this.currentProject = new BasicCollageProject(width, height);
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
//    if (input.equalsIgnoreCase("q"));

    //now set up the scanner to read from the string we just built
   // sc = new Scanner(builder.toString());

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

}
