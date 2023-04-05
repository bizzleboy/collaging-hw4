package collagefiles.controller;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.*;

import collagefiles.model.BasicCollageProject;
import collagefiles.model.Image;
import collagefiles.model.Layer;
import collagefiles.model.Pixel;
import collagefiles.model.Project;
import collagefiles.view.GUIView;
import collagefiles.view.GUIView2;


/**
 * Implementation of a controller that passes inputs and user commands to the view.
 * Provides functionality for adding images, layers, filters, saving, and more.
 */
public class GUIController2 extends JFrame implements ActionListener {
  private GUIView2 view;

  private int x_offset;
  private int y_offset;
  private Project project;
  private JLabel imgHolder = new JLabel();

  /**
   * Constructor which instantiates the view and attatches all listeners.
   * @param view desired view.
   */
  public GUIController2(GUIView2 view) {
    super();
    this.view = view;

    this.view.setListener(this);


  }

  /**
   * Various user actions like adding images, layers,filters,saving, etc.
   * @param e the event to be processed.
   */
  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if (command.equals("save-image")) {
      JFileChooser fileChooser = new JFileChooser();
      String fileName =this.view.renderInput("Enter image name");
      fileChooser.setDialogTitle("Choose a directory to save the file");
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

      int userSelection = fileChooser.showSaveDialog(view.getFrame());
      if (userSelection == JFileChooser.APPROVE_OPTION) {
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        System.out.println("Save image to: " + filePath);
        String imageString = this.project.saveImage(filePath +"/"+fileName);
        System.out.print(imageString);
        File file = new File(filePath +"/"+fileName+".ppm");
        FileWriter fr = null;
        try {
          fr = new FileWriter(file);
          fr.write(imageString);
          fr.close();
        } catch (IOException r) {

        }

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
        this.view.displayImage(this.project.stackToImage(this.project.getLayers().size()-1),0,0);

      }
    }
    if(command.equals("save-project")){

      JFileChooser fileChooser = new JFileChooser();
      String fileName = this.view.renderInput("Enter project name");
      fileChooser.setDialogTitle("Choose a directory to save the project");
      fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

      int userSelection = fileChooser.showSaveDialog(view.getFrame());
      if (userSelection == JFileChooser.APPROVE_OPTION) {
        for(Layer l: this.project.getLayers()){
          String saveFilter = l.getFilter();
          this.project.setFilter(l.getName(),"normal");
          l.setFilter(saveFilter);
        }
        String filePath = fileChooser.getSelectedFile().getAbsolutePath();
        System.out.println("Save image to: " + filePath);
        String imageString = this.project.saveProject(filePath +"/"+fileName);
        System.out.print(imageString);
        File file = new File(filePath +"/"+fileName+".txt");
        FileWriter fr = null;
        try {
          fr = new FileWriter(file);
          fr.write(imageString);
          fr.close();
        } catch (IOException r) {

        }

        JOptionPane.showMessageDialog(view.getFrame(), "Saved");
      }

    }


    if (command.equals("new-project")) {
      String pHeightS = JOptionPane.showInputDialog(view.getFrame(), "Enter Project Height");
      String pWidthS = JOptionPane.showInputDialog(view.getFrame(), "Enter Project Width");
      int pWidth = 0;
      int pHeight = 0;
      try {
        pHeight = Integer.parseInt(pHeightS);
        pWidth = Integer.parseInt(pWidthS);
      } catch (NumberFormatException n) {
        JOptionPane.showMessageDialog(view.getFrame(), "Invalid height/width.");
      }

      this.view.removeLayers();
      this.project = new BasicCollageProject(pWidth, pHeight,255);
      this.view.removeLayers();
      this.view.displayImage(this.project.stackToImage(this.project.getLayers().size()-1),0,0);
    }

    if (command.equals("apply-filter")) {
      String[] options = {"normal", "red-component", "blue-component","green-component","brighten-value","darken-value","brighten-luma","darken-luma","brighten-intensity","difference","multiply","screen"};
      String filter = (String) JOptionPane.showInputDialog(view.getFrame(), "Choose a filter:", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      if (filter != null) {
        List<String> array = new ArrayList<>();
        for (Layer layer : this.project.getLayers()) {
          if(layer.getName()!= "background") {
            array.add(layer.getName());
          }
        }
        String[] options2 = array.toArray(new String[0]);
        String layer = (String) JOptionPane.showInputDialog(view.getFrame(), "Choose a layer:", "Layer", JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
        if (layer != null ) {

          System.out.print("Old filter of " + layer + ": " + this.project.getLayers().get(array.indexOf(layer)).getFilter() + "\n");
          this.project.setFilter(layer, filter);
          this.project.getLayers().get(array.indexOf(layer)).setFilter(filter);




          this.view.displayImage(this.project.stackToImage(this.project.getLayers().size()-1),x_offset,y_offset);
          this.view.revalidate();
          this.view.repaint();
          System.out.print("New filter of " + layer + ": " + this.project.getLayers().get(array.indexOf(layer)).getFilter() + "\n");
        }
      }
    }

    if (command.equals("add-layer")) {
      String layerName = this.view.renderInput( "Enter layer name:");
      if(layerName==null){

      }else{
        this.project.addLayer(layerName);

        view.addLayerButton(layerName);
      }


    } else if (command.equals("add-image")) {
      JFileChooser fileChooser = new JFileChooser();
      int result = fileChooser.showOpenDialog(view.getFrame());
      if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();

        String x_string = this.view.renderInput("Enter x-coordinate:");
        String y_string = this.view.renderInput("Enter y-coordinate:");
        try {
          x_offset = Integer.parseInt(x_string);
          y_offset = Integer.parseInt(y_string);
          Image imageToAdd = this.readImage(selectedFile.getAbsolutePath());
          List<String> array = new ArrayList<>();
          for (Layer layer : this.project.getLayers()) {
            if(layer.getName()!= "background") {
              array.add(layer.getName());
            }
          }
          String[] options2 = array.toArray(new String[0]);
          String layer = (String) JOptionPane.showInputDialog(view.getFrame(), "Choose a layer:", "Layer", JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);


          if (layer != null) {
            this.project.addImageToLayer(layer, imageToAdd, x_offset, y_offset);
            this.view.displayImage(this.project.stackToImage(this.project.getLayers().size()-1),x_offset,y_offset);

          }

        } catch (NumberFormatException ex) {
          this.view.renderMessage("Invalid coordinates.");
        }
      }



    }
  }

  /**
   * Reads in an image(ppm) and converts it to an Image (list of list of pixels).
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
   * @param path Path to
   * @return
   * @throws IllegalArgumentException
   */
  private Project readProject(String path) throws IllegalArgumentException {
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

    while (sc.hasNextLine() && tracker <= 3 + ((width * height) + 1) *layerTracker) {


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

      if(!sc.hasNext()){
        tracker+=1;
      }

    }
    return this.project;
  }
}