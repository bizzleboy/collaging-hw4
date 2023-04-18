package collagefiles.controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import collagefiles.model.Image;
import collagefiles.model.Layer;
import collagefiles.model.Pixel;
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


  private Image imageToAdd;

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
        for (Layer l : this.project.getLayers()) {
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
        for (Layer layer : this.project.getLayers()) {
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
          for (Layer layer : this.project.getLayers()) {
            if (!layer.getName().equals("background")) {
              array.add(layer.getName());
            }
          }
          String[] options2 = array.toArray(new String[0]);
          String layer = (String) JOptionPane.showInputDialog(view.getFrame(),
                  "Choose a layer:", "Layer", JOptionPane.QUESTION_MESSAGE,
                  null, options2, options2[0]);


          File selectedFile = fileChooser.getSelectedFile();
          if (this.getExtension(selectedFile).equals("ppm")) {
            this.imageToAdd = this.readImage(selectedFile.getAbsolutePath());
          } else if (this.getExtension(selectedFile).equals("jpeg")
                  || (this.getExtension(selectedFile).equals("png")
                  || (this.getExtension(selectedFile).equals("jpg")))) {
            this.imageToAdd = this.readPngJpeg(selectedFile.getAbsolutePath());
          }

          if (layer != null) {

            this.project.addImageToLayer(layer, this.imageToAdd, x_offset, y_offset);
            BufferedImage renderedImage = this.renderImage(this.project.stackToImage(
                    this.project.getLayers().size() - 1));
            this.view
                    .displayImage(renderedImage, x_offset, y_offset);

          }

        } catch (NumberFormatException ex) {
          this.view.renderMessage("Invalid coordinates.");
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
  private Image readImage(String path) {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {

      this.view.renderMessage("File " + path + " not found!");

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

      this.view.renderMessage("Invalid PPM file: plain RAW file should begin with P3");

    }
    int width = sc.nextInt();

    int height = sc.nextInt();

    int maxVal = sc.nextInt();

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


      ArrayList<ArrayList<Pixel>> nextPixels = new ArrayList<>();

      String layerName = sc.next();

      String filterName = sc.next();

      tracker += 1;
      layerTracker += 1;

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
      this.project.addLayer(layerName);
      this.project.addImageToLayer(layerName, new Image(nextPixels), 0, 0);
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

  private BufferedImage renderImage(Image pixels) {
    int width = pixels.getPixels().size();
    int height = pixels.getPixels().get(0).size();

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = pixels.getFilterPixels().get(y).get(x);

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
  public Image readPngJpeg(String filepath) {
    ArrayList<ArrayList<Pixel>> imageColors = new ArrayList<>();

    try {
      BufferedImage image = ImageIO.read(new File(filepath));
      int width = image.getWidth();
      int height = image.getHeight();

      for (int i = 0; i < height; i++) {
        ArrayList<Pixel> rowPixels = new ArrayList<>();
        for (int j = 0; j < width; j++) {
          int rgb = image.getRGB(j, i);
          Color color = new Color(rgb);



          rowPixels.add(new Pixel(color.getRed(), color.getGreen(),
                  color.getBlue(), color.getAlpha()));
        }
        imageColors.add(rowPixels);
      }

    } catch (IOException e) {
      System.err.println("Error reading the image file: " + e.getMessage());
    }

    return new Image(imageColors);
  }

  /**
   * New method for saving images, converts view to bufferedImage.
   *
   * @param filepath Path where you want to save file.
   * @param fileName Name of file.
   */
  public void saveImageNew(String filepath, String fileName) {
    // Create a BufferedImage from the ArrayList<ArrayList<Color>>

    Image finalImage = this.project.stackToImage(
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
    } catch (IOException e) {
      view.renderMessage("");
    }
  }

}


