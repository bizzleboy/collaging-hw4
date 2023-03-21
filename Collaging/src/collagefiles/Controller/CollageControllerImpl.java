package collagefiles.controller;


import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import collagefiles.model.BasicCollageProject;
import collagefiles.model.Image;
import collagefiles.model.Pixel;
import collagefiles.model.Project;
import collagefiles.view.CollageView;

/**
 * Controller for operating collage project.
 */
public class CollageControllerImpl implements CollageController {

  private final Readable input;
  private final CollageView view;
  private Project currentProject;

  /**
   * Constructor for controller that takes in input and view.
   *
   * @param input Readable to process inputs.
   * @param view  View for storing output.
   * @throws IllegalArgumentException If any arguments are null.
   */
  public CollageControllerImpl(Readable input, CollageView view)
          throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("provided readable input cannot be null.");
    }
    if (view == null) {
      throw new IllegalArgumentException("provided view input cannot be null.");
    }
    this.input = input;
    this.view = view;
    this.currentProject = null;
  }

  /**
   * Controller that starts with a project for loading purposes.
   *
   * @param input Readable to process inputs.
   * @param p     Project to input.
   * @param view  View for storing output.
   * @throws IllegalArgumentException If any arguments are null.
   */
  public CollageControllerImpl(Readable input, Project p, CollageView view)
          throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("provided readable input cannot be null.");
    }
    if (view == null) {
      throw new IllegalArgumentException("provided view input cannot be null.");
    }
    if (p == null) {
      throw new IllegalArgumentException("provided project input cannot be null.");
    }
    this.input = input;
    this.view = view;
    this.currentProject = p;
  }

  @Override
  public void runProgram() throws IllegalStateException {

    Scanner scan = new Scanner(this.input);

    while (scan.hasNext()) {

      String input = scan.next();

      switch (input) {

        case "q":
          try {
            this.view.renderMessage("program quit!");
            return;
          } catch (IOException a) {
            throw new IllegalStateException(a);
          }

        case "new-project":
          try {
            this.view.renderMessage("enter width and height");
          } catch (IOException a) {
            throw new IllegalStateException(a);
          }

          while (!scan.hasNextInt()) {
            if (scan.next().equals("q")) {
              try {
                this.view.renderMessage("program quit!");
                return;
              } catch (IOException a) {
                throw new IllegalStateException(a);
              }
            }
          }

          int width = scan.nextInt();
          try {
            this.view.renderMessage("width entered");
          } catch (IOException a) {
            throw new IllegalStateException(a);
          }
          while (!scan.hasNextInt()) {
            if (scan.next().equals("q")) {
              try {
                this.view.renderMessage("program quit!");
                return;
              } catch (IOException a) {
                throw new IllegalStateException(a);
              }
            }
          }
          int height = scan.nextInt();
          try {
            this.view.renderMessage("height entered");
          } catch (IOException a) {
            throw new IllegalStateException(a);
          }
          if (this.currentProject == null) {
            this.currentProject = new BasicCollageProject(width, height, 255);
            this.currentProject.addLayer("background");
            try {
              this.view.renderMessage("new project made");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
          }
          break;

        case "load-project":
          try {
            this.view.renderMessage("enter project path");
          } catch (IOException a) {
            throw new IllegalStateException(a);
          }
          String loadProjectPath = scan.next();
          if (loadProjectPath.equals("q")) {
            try {
              this.view.renderMessage("program quit!");
              return;
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
          }
          if (this.currentProject == null) {
            this.currentProject = this.readProject(loadProjectPath);
            try {
              this.view.renderMessage("project loaded");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
          }
          break;
        case "save-project":
          if (this.currentProject != null) {
            try {
              this.view.renderMessage("enter file path");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
            String saveProjectPath = scan.next();
            if (saveProjectPath.equals("q")) {
              try {
                this.view.renderMessage("program quit!");
                return;
              } catch (IOException a) {
                throw new IllegalStateException(a);
              }
            }
            String projectString = this.currentProject.saveProject(saveProjectPath);
            File file = new File(saveProjectPath);
            FileWriter fr = null;
            try {
              fr = new FileWriter(file);
              fr.write(projectString);
              fr.close();
            } catch (IOException e) {
              //bababooey
            }
            try {
              this.view.renderMessage("project saved");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
          }
          break;

        case "save-image":
          if (this.currentProject != null) {
            try {
              this.view.renderMessage("enter image path");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
            String saveImagePath = scan.next();
            if (saveImagePath.equals("q")) {
              try {
                this.view.renderMessage("program quit!");
                return;
              } catch (IOException a) {
                throw new IllegalStateException(a);
              }
            }
            String imageString = this.currentProject.saveImage(saveImagePath);
            System.out.print(imageString);
            File file = new File(saveImagePath);
            FileWriter fr = null;
            try {
              fr = new FileWriter(file);
              fr.write(imageString);
              fr.close();
            } catch (IOException e) {
              //donezo
            }
            try {
              this.view.renderMessage("image saved");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
          }
          break;
        case "set-filter":
          if (this.currentProject != null) {
            try {
              this.view.renderMessage("enter layer name");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
            String layer = scan.next();
            try {
              this.view.renderMessage("enter filter name");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
            String filter = scan.next();
            if (layer.equals("q")) {
              try {
                this.view.renderMessage("program quit!");
                return;
              } catch (IOException a) {
                throw new IllegalStateException(a);
              }
            }
            if (filter.equals("q")) {
              try {
                this.view.renderMessage("program quit!");
                return;
              } catch (IOException a) {
                throw new IllegalStateException(a);
              }
            }
            this.currentProject.setFilter(layer, filter);
            try {
              this.view.renderMessage("filter set");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
          }
          break;
        case "add-layer":
          if (this.currentProject != null) {
            try {
              this.view.renderMessage("enter layer name");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
            String layerToAdd = scan.next();
            if (layerToAdd.equals("q")) {
              try {
                this.view.renderMessage("program quit!");
              } catch (IOException a) {
                throw new IllegalStateException(a);
              }
              return;
            }
            this.currentProject.addLayer(layerToAdd);
            try {
              this.view.renderMessage("layer added if name is unique");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
          }
          break;
        case "add-image":
          if (this.currentProject != null) {
            try {
              this.view.renderMessage("enter layer name");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
            String layerToAddTo = scan.next();
            try {
              this.view.renderMessage("enter image path");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
            String imageToAdd = scan.next();
            if (layerToAddTo.equals("q")) {
              try {
                this.view.renderMessage("program quit!");
                return;
              } catch (IOException a) {
                throw new IllegalStateException(a);
              }
            }
            if (imageToAdd.equals("q")) {
              try {
                this.view.renderMessage("program quit!");
                return;
              } catch (IOException a) {
                throw new IllegalStateException(a);
              }
            }
            while (!scan.hasNextInt()) {
              if (scan.next().equals("q")) {
                try {
                  this.view.renderMessage("program quit!");
                  return;
                } catch (IOException a) {
                  throw new IllegalStateException(a);
                }
              }
            }
            try {
              this.view.renderMessage("enter x position");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
            int xPos = scan.nextInt();
            try {
              this.view.renderMessage("x entered\nenter y position");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
            while (!scan.hasNextInt()) {
              if (scan.next().equals("q")) {
                try {
                  this.view.renderMessage("program quit!");
                } catch (IOException a) {
                  throw new IllegalStateException(a);
                }
                return;
              }
            }
            int yPos = scan.nextInt();
            try {
              this.view.renderMessage("y entered");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
            this.currentProject.addImageToLayer(layerToAddTo,
                    this.readImage(imageToAdd), xPos, yPos);
            try {
              this.view.renderMessage("image added to layer");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
          }
          break;
        default:
          try {
            this.view.renderMessage("Invalid input");
          } catch (IOException a) {
            throw new IllegalStateException(a);
          }
      }


    }


  }

  private Project readProject(String path) throws IllegalArgumentException {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      try {
        this.view.renderMessage("File " + path + " not found!");
      } catch (IOException a) {
        throw new IllegalStateException(a);
      }
      Scanner tryAgain = new Scanner(this.input);
      this.readProject(tryAgain.next());
    }

    int tracker = 3;
    int layerTracker = 0;
    String title = null;
    int width = -1;
    int height = -1;
    int maxVal = -1;


    title = sc.next();
    width = sc.nextInt();
    height = sc.nextInt();
    maxVal = sc.nextInt();

    this.currentProject = new BasicCollageProject(width, height, maxVal);

    while (sc.hasNextLine() && tracker < 3 + width * height + layerTracker) {

      ArrayList<ArrayList<Pixel>> nextPixels = new ArrayList<>();

      String layerName = sc.next();
      String filterName = sc.next();
      layerTracker += 1;
      tracker += 1;

      for (int y = 0; y < height; y++) {
        nextPixels.add(new ArrayList<Pixel>());
      }
      for (List l : nextPixels) {
        for (int x = 0; x < width; x++) {
          int r = sc.nextInt();
          int g = sc.nextInt();
          int b = sc.nextInt();
          int a = sc.nextInt();
          tracker += 1;
          l.add(new Pixel(r, g, b, a));
        }
      }
      this.currentProject.addLayer(layerName);
      this.currentProject.addImageToLayer(layerName, new Image(nextPixels), 0, 0);
      this.currentProject.setFilter(layerName, filterName);
    }
    return this.currentProject;
  }

  private Image readImage(String path) {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      try {
        this.view.renderMessage("File " + path + " not found!");
      } catch (IOException a) {
        throw new IllegalStateException(a);
      }
      Scanner tryAgain = new Scanner(this.input);
      return this.readImage(tryAgain.next());
    }
    StringBuilder imageBuilder = new StringBuilder();
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        imageBuilder.append(s + System.lineSeparator());
      }
    }

    sc = new Scanner(imageBuilder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      try {
        this.view.renderMessage("Invalid PPM file: plain RAW file should begin with P3");
      } catch (IOException a) {
        throw new IllegalStateException(a);
      }
    }
    int width = sc.nextInt();

    int height = sc.nextInt();


    ArrayList<ArrayList<Pixel>> pixels;
    pixels = new ArrayList<>();

    //adding the rows of pixels
    for (int i = 0; i < height; i++) {
      pixels.add(new ArrayList<Pixel>());
    }
    //adding pixels to each row
    for (List l : pixels) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        l.add(new Pixel(new Color(r, g, b)));


      }
    }

    Image readImage = new Image(pixels);
    return readImage;
  }

}
//
