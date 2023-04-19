package collagefiles.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.imageio.ImageIO;

import javax.swing.JFrame;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import collagefiles.model.BasicCollageProject;

import collagefiles.model.ImageInterface;


import collagefiles.model.LayerInterface;
import collagefiles.model.PixelInterface;
import collagefiles.model.Project;
import collagefiles.view.GUIView;


/**
 * Implementation of a controller that passes inputs and user commands to the view.
 * Provides functionality for adding images, layers, filters, saving, and more.
 */
public class GUIController2 extends JFrame implements ActionListener {
  private final GUIView view;

  private int x_offset;
  private int y_offset;
  private Project project;


  private ImageInterface imageToAdd;

  /**
   * Constructor which instantiates the view and attatches all listeners.
   *
   * @param view desired view.
   */
  public GUIController2(GUIView view) {

    super();
    //Was able to decouple view to not rely on a specific gui view.
    this.view = view;

    this.view.setListener(this);
  }

  /**
   * Various user actions like adding images, layers,filters,saving, etc.
   * This is a test.
   *
   * @param e the event to be processed.
   */
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if (command.equals("save-image")) {
      JFileChooser fileChooser = new JFileChooser();
      String fileName = this.view.renderInput("Enter image name");
      fileChooser.setDialogTitle("Choose a directory to save the file");
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

      int userSelection = fileChooser.showSaveDialog(view.getFrame());
      if (userSelection == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();

        this.saveImageNew(filePath, fileName);


        JOptionPane.showMessageDialog(view.getFrame(), "Saved");
      }


    }


    if (command.equals("load-project")) {
      JFileChooser fileChooser = new JFileChooser();
      int result = fileChooser.showOpenDialog(view.getFrame());
      if (result == JFileChooser.APPROVE_OPTION) {
        this.view.removeLayers();
        File selectedFile = fileChooser.getSelectedFile();
        this.project = this.readProject(selectedFile.getAbsolutePath());
        System.out.println(this.project.getLayers().size());
        BufferedImage renderedImage = this.renderImage(this.project.stackToImage(
                this.project.getLayers().size() - 1));
        this.view
                .displayImage(renderedImage, 0, 0);

      }
    }
    if (command.equals("save-project")) {

      JFileChooser fileChooser = new JFileChooser();
      String fileName = this.view.renderInput("Enter project name");
      fileChooser.setDialogTitle("Choose a directory to save the project");
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

      int userSelection = fileChooser.showSaveDialog(view.getFrame());
      if (userSelection == JFileChooser.APPROVE_OPTION) {
        for (LayerInterface l : this.project.getLayers()) {
          String saveFilter = l.getFilter();
          this.project.setFilter(l.getName(), "normal");
          l.setFilter(saveFilter);
        }
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        System.out.println("Save image to: " + filePath);
        String imageString = this.project.saveProject(filePath + "/" + fileName);
        System.out.print(imageString);
        File file = new File(filePath + "/" + fileName + ".txt");
        FileWriter fr = null;
        try {
          fr = new FileWriter(file);
          fr.write(imageString);
          fr.close();
        } catch (IOException r) {
          //Caught

        }

        JOptionPane.showMessageDialog(view.getFrame(), "Saved");
      }

    }


    if (command.equals("new-project")) {
      String pHeightS = JOptionPane.showInputDialog(view.getFrame(),
              "Enter Project Height");
      String pWidthS = JOptionPane.showInputDialog(view.getFrame(),
              "Enter Project Width");
      int pWidth = 0;
      int pHeight = 0;
      try {
        pHeight = Integer.parseInt(pHeightS);
        pWidth = Integer.parseInt(pWidthS);
      } catch (NumberFormatException n) {
        JOptionPane.showMessageDialog(view.getFrame(), "Invalid height/width.");
      }

      this.view.removeLayers();
      this.project = new BasicCollageProject(pWidth, pHeight, 255);
      this.view.removeLayers();
      BufferedImage renderedImage = this.renderImage(this.project.stackToImage(
              this.project.getLayers().size() - 1));
      this.view
              .displayImage(renderedImage, 0, 0);
    }

    if (command.equals("apply-filter")) {
      String[] options = {"normal", "red-component", "blue-component", "green-component",
                          "brighten-value", "darken-value", "brighten-luma", "darken-luma",
                          "brighten-intensity", "difference", "multiply", "screen"};
      String filter = (String) JOptionPane.showInputDialog(view.getFrame(),
              "Choose a filter:", "Filter", JOptionPane.QUESTION_MESSAGE,
              null, options, options[0]);
      if (filter != null) {
        List<String> array = new ArrayList<>();
        for (LayerInterface layer : this.project.getLayers()) {
          if (!layer.getName().equals("background")) {
            array.add(layer.getName());
          }
        }
        String[] options2 = array.toArray(new String[0]);
        String layer = (String) JOptionPane.showInputDialog(view.getFrame(),
                "Choose a layer:", "Layer", JOptionPane.QUESTION_MESSAGE, null,
                options2, options2[0]);
        if (layer != null) {

          System.out.print("Old filter of " + layer + ": "
                  + this.project.getLayers().get(array.indexOf(layer) + 1).getFilter() + "\n");
          this.project.setFilter(layer, filter);
          this.project.getLayers().get(array.indexOf(layer) + 1).setFilter(filter);

          BufferedImage renderedImage = this.renderImage(this.project.stackToImage(
                  this.project.getLayers().size() - 1));
          this.view
                  .displayImage(renderedImage, this.x_offset, this.y_offset);

          System.out.print("New filter of " + layer + ": "
                  + this.project.getLayers().get(array.indexOf(layer) + 1).getFilter() + "\n");
        }
      }
    }

    if (command.equals("add-layer")) {
      String layerName = this.view.renderInput("Enter layer name:");
      if (layerName == null) {
        //cancel without doing anything
      } else {
        this.project.addLayer(layerName);

        view.addLayerButton(layerName);
      }


    } else if (command.equals("add-image")) {
      JFileChooser fileChooser = new JFileChooser();
      int result = fileChooser.showOpenDialog(view.getFrame());
      if (result == JFileChooser.APPROVE_OPTION) {

        String x_string = this.view.renderInput("Enter x-coordinate:");
        String y_string = this.view.renderInput("Enter y-coordinate:");
        try {
          x_offset = Integer.parseInt(x_string);
          y_offset = Integer.parseInt(y_string);


          List<String> array = new ArrayList<>();
          for (LayerInterface layer : this.project.getLayers()) {
            if (!layer.getName().equals("background")) {
              array.add(layer.getName());
            }
          }
          String[] options2 = array.toArray(new String[0]);
          String layer = (String) JOptionPane.showInputDialog(view.getFrame(),
                  "Choose a layer:", "Layer", JOptionPane.QUESTION_MESSAGE,
                  null, options2, options2[0]);


          ImageInterface addingImage = null;
          File selectedFile = fileChooser.getSelectedFile();
          if (this.getExtension(selectedFile).equals("ppm")) {
             addingImage = this.readImage(selectedFile.getAbsolutePath());
          } else if (this.getExtension(selectedFile).equals("jpeg")
                  || (this.getExtension(selectedFile).equals("png")
                  || (this.getExtension(selectedFile).equals("jpg")))) {
            addingImage = this.readPngJpeg(selectedFile.getAbsolutePath());
          }

          if (layer != null) {


            this.project.addImageToLayer(layer, addingImage, x_offset, y_offset);
            BufferedImage renderedImage = this.renderImage(this.project.stackToImage(
                    this.project.getLayers().size() - 1));
            this.view
                    .displayImage(renderedImage, x_offset, y_offset);

          }

        } catch (NumberFormatException ex) {
          this.view.renderMessage("Invalid coordinates.");
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
      }


    }
  }

  /**
   * Reads in an image(ppm) and converts it to an Image (list of list of pixels).
   *
   * @param path Path to image on computer.
   * @return Image from computer.
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

    return this.project.LoadImagePixelsFromProjectPNGJPEG(image);
  }

  /**
   * Takes in a text file and converts it into a project.
   *
   * @param path Path to project.
   * @return Project to be read into project.
   * @throws IllegalArgumentException When path is null.
   */
  private Project readProject(String path) throws IllegalArgumentException {
    if (path == null) {
      throw new IllegalArgumentException("Bad path");
    }
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {
      this.view.renderMessage("invalid file");
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

    this.project = new BasicCollageProject(width, height, maxVal);

    while (sc.hasNextLine() && tracker <= 3 + ((width * height) + 1) * layerTracker) {


      ArrayList<ArrayList<PixelInterface>> nextPixels = new ArrayList<>();

      String layerName = sc.next();
      this.project.addLayer(layerName);

      String filterName = sc.next();

      tracker += 1;
      layerTracker += 1;

      for (int y = 0; y < height; y++) {
        nextPixels.add(new ArrayList<PixelInterface>());
      }


      this.project.addImageToLayer(layerName, this.project.LoadImagePixelsFromProject(sc,nextPixels), 0, 0);


      this.project.setFilter(layerName, filterName);
      this.view.addLayerButton(layerName);

      if (!sc.hasNext()) {
        tracker += 1;
      }

    }
    return this.project;
  }

  private String getExtension(File file) {
    String extension = "";
    int i = file.getName().lastIndexOf('.');
    if (i >= 0) {
      extension = file.getName().substring(i + 1);
    }
    return extension;
  }

  private BufferedImage renderImage(ImageInterface pixels) {
    int width = pixels.getPixels().size();
    int height = pixels.getPixels().get(0).size();

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        PixelInterface pixel = pixels.getFilterPixels().get(y).get(x);

        int color = pixel.getPixelColor().getRGB();

        image.setRGB(x, y, color);


      }
    }
    return image;

  }

  /**
   * Turns a png/jpeg into a Image, arraylist of array list of pixels..
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



    return this.project.LoadImagePixelsFromProjectPNGJPEG(image);
  }

  /**
   * New method for saving images, converts view to bufferedImage.
   *
   * @param filepath Path where you want to save file.
   * @param fileName Name of file.
   */
  public void saveImageNew(String filepath, String fileName) {

    ImageInterface finalImage = this.project.stackToImage(
            this.project.getLayers().size() - 1);
    int width = finalImage.getPixels().get(0).size();
    int height = finalImage.getPixels().size();
    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        Color color = finalImage.getPixels().get(i).get(j).getPixelColor();
        image.setRGB(j, i, color.getRGB());
      }
    }

    // Prompt the user to choose the file format (JPEG, PNG, or PPM)
    String[] options = {"JPEG", "PNG", "PPM"};
    int choice = JOptionPane.showOptionDialog(this.view.getFrame(),
            "Choose the file format to save:",
            "Save as JPEG, PNG, or PPM", JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

    String format = choice == 0 ? "jpeg" : (choice == 1 ? "png" : "ppm");
    File outputFile = new File(filepath + File.separator + fileName + "." + format);

    try {
      if (format.equals("ppm")) {
        saveAsPPM(image, outputFile);
      } else {
        ImageIO.write(image, format, outputFile);
      }
    } catch (IOException e) {
      System.err.println("Error saving the image file: " + e.getMessage());
    }
  }

  /**
   * Coverts a buffered image into a ppm.
   *
   * @param image      The composite image of all layers and such.
   * @param outputFile The path to save the ppm to.
   * @throws IOException Standard IO catch.
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
    } catch (IOException e) {
      view.renderMessage("");
    }
  }

}


