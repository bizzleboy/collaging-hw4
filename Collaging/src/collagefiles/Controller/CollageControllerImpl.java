package collagefiles.controller;


import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import java.util.Scanner;

import javax.imageio.ImageIO;


import collagefiles.model.BasicCollageProject;

import collagefiles.model.ImageInterface;

import collagefiles.model.PixelInterface;
import collagefiles.model.Project;
import collagefiles.view.CollageView;

/**
 * Controller for operating collage project.
 * This controller is operated through the command line using text inputs.
 */
public class CollageControllerImpl implements CollageController {

  private final Readable input;
  private final CollageView view;
  private Project currentProject;

  /**
   * Constructor for controller that takes in an input, a view, and a current project.
   * The inputs to this constructor are interfaces for reduced coupling.
   *
   * @param input Readable to process inputs.
   * @param view  View for storing output.
   * @throws IllegalArgumentException If any arguments are null.
   */
  public CollageControllerImpl(Readable input, CollageView view,Project project)
          throws IllegalArgumentException {
    if (input == null) {
      throw new IllegalArgumentException("provided readable input cannot be null.");
    }
    if (view == null) {
      throw new IllegalArgumentException("provided view input cannot be null.");
    }
    this.input = input;
    this.view = view;
    this.currentProject = project;
  }

  /**
   * Controller that starts with a project for loading purposes.
   * This project input allows a previously existing project to be loaded into the controller.
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

            this.currentProject = this.currentProject.resetProject(width);
            this.currentProject.addLayer("background");
            try {
              this.view.renderMessage("new project made");
            } catch (IOException a) {
              throw new IllegalStateException(a);
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
            this.currentProject = this.readProject(this.printPath() + loadProjectPath);
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
            File file = new File(this.printPath() + saveProjectPath);
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
            try {
              this.view.renderMessage("enter image name with file extension");
            } catch (IOException a) {
              throw new IllegalStateException(a);
            }
            String saveImageName = scan.next();
            if (saveImageName.equals("q")) {
              try {
                this.view.renderMessage("program quit!");
                return;
              } catch (IOException a) {
                throw new IllegalStateException(a);
              }
            }
            this.saveImageNew(this.printPath() + saveImagePath, saveImageName);
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
            System.out.print(imageToAdd);
            if (imageToAdd.split("\\.")[1].equals("ppm")) {
              try {
                this.currentProject.addImageToLayer(layerToAddTo,
                        this.readImage(this.printPath() + imageToAdd), xPos, yPos);
              } catch (IOException e) {
              }

              try {
                this.view.renderMessage("image added to layer");
              } catch (IOException a) {
                throw new IllegalStateException(a);
              }
            }
            else if (imageToAdd.split("\\.")[1].equals("jpeg") ||
                    imageToAdd.split("\\.")[1].equals("jpg") ||
                    imageToAdd.split("\\.")[1].equals("png")) {

              this.currentProject.addImageToLayer(layerToAddTo,
                      this.readPngJpeg(this.printPath() + imageToAdd), xPos, yPos);
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
    if (path == null) {
      throw new IllegalArgumentException("Bad path");
    }
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      try {
        this.view.renderMessage("invalid file");
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
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

    this.currentProject = this.currentProject.resetProject(width);
// && tracker <= 3 + ((width * height) + 1) * layerTracker
    while (sc.hasNextLine()) {


      ArrayList<ArrayList<PixelInterface>> nextPixels = new ArrayList<>();

      String layerName = sc.next();
      if(!layerName.equals("background")) {
        this.currentProject.addLayer(layerName);
      }

      String filterName = sc.next();

      tracker += 1;
      layerTracker += 1;

      for (int y = 0; y < height; y++) {
        nextPixels.add(new ArrayList<PixelInterface>());
      }

      BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

      // Read pixel data and populate the BufferedImage
      for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
          if (!sc.hasNextLine()) {
            break;
          }
          int red = sc.nextInt();
          int green = sc.nextInt();
          int blue = sc.nextInt();
          int alpha = sc.nextInt();
//          tracker+=1;

          int rgb = (alpha << 24) | (red << 16) | (green << 8) | blue;
          image.setRGB(x, y, rgb);
        }
      }




      this.currentProject.addImageToLayer(layerName, this.currentProject.LoadImagePixelsFromProjectPNGJPEG(image), 0, 0);


      this.currentProject.setFilter(layerName, filterName);


      if (!sc.hasNext()) {
        break;
      }

    }
    return this.currentProject;
  }

  /**
   * Reads in an image(ppm) and converts it to an object of ImageInterface (list of list of pixels).
   * This method relies on the file beginning with 'P3'.
   *
   * @param path Path to image on computer.
   * @return Image object based on components of image file from computer.
   */
  private ImageInterface readImage(String path) throws IOException {
    File file = new File(path);
    Scanner scan = new Scanner(new FileReader(file));

    // Read the header information
    String magicNumber = scan.next();
    if (!magicNumber.equals("P3")) {
      throw new IOException("Invalid PPM file format: expected P3 magic number.");
    }



    int width = scan.nextInt();
    int height = scan.nextInt();

    int maxColorValue = scan.nextInt();

    // Create a BufferedImage to hold the pixel data
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // Read pixel data and populate the BufferedImage
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        int red = scan.nextInt();
        int green = scan.nextInt();
        int blue = scan.nextInt();

        int rgb = ((red & 0xFF) << 16) | ((green & 0xFF) << 8) | (blue & 0xFF);
        image.setRGB(x, y, rgb);
      }
    }

    return this.currentProject.LoadImagePixelsFromProjectPNGJPEG(image);
  }

  /**
   * New method for saving images, converts view to bufferedImage.
   * @param filepath Path where you want to save file.
   * @param fileName Name of file.
   */
  public void saveImageNew(String filepath, String fileName) {
    Scanner fileScanner = new Scanner(this.input);

    ImageInterface finalImage = this.currentProject.stackToImage(
            this.currentProject.getLayers().size() - 1);
    int width = finalImage.getPixels().get(0).size();
    int height = finalImage.getPixels().size();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = finalImage.getPixels().get(i).get(j).getPixelColor();
        image.setRGB(j, i, color.getRGB());
      }
    }


    File outputFile = new File(filepath + File.separator + fileName);

    try {
      if (fileName.split("\\.")[1].equals("ppm")) {
        System.out.println(fileName);
        saveAsPPM(image, outputFile);
      } else {
        ImageIO.write(image, fileName.split("\\.")[1], outputFile);
      }
    } catch (IOException e) {
      System.err.println("Error saving the image file: " + e.getMessage());
    }
  }

  /**
   * Coverts a buffered image into a ppm.
   * @param image The composite image of all layers and such.
   * @param outputFile The path to save the ppm to.
   * @throws IOException Standard IO catch
   */
  private void saveAsPPM(BufferedImage image, File outputFile) throws IOException {
    try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
      int width = image.getWidth();
      int height = image.getHeight();

      // Write PPM header
      writer.write("P3\n");
      writer.write(width + " " + height + "\n");
      writer.write("255\n");

      // Write pixel data
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          Color color = new Color(image.getRGB(j, i));
          writer.write(color.getRed() + " " + color.getGreen() + " " + color.getBlue() + " ");
        }
        writer.write("\n");
      }

    }
  }

  /**
   * Renders the relative path for saving images.
   * @return String of the path.
   */
  private String printPath() {
    String path = String.format("%s/%s", System.getProperty("user.dir"),
            this.getClass().getPackage().getName().replace(".", "/"));
    return path.split("Collaging/")[0].split("collagefiles")[0];
  }





  /**
   * Turns a png/jpeg into an ImageInterface object (array list of array list of pixels).
   *
   * @param filepath Path to image.
   * @return Image to be rendered.
   */
  public ImageInterface readPngJpeg(String filepath) {


    BufferedImage image = null;
    try {
      image = ImageIO.read(new File(filepath));
    } catch (IOException e) {
      System.err.println("Error reading the image file: " + e.getMessage());
    }



    return this.currentProject.LoadImagePixelsFromProjectPNGJPEG(image);
  }



}
