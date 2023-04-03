package collagefiles.controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import collagefiles.model.BasicCollageProject;
import collagefiles.model.Image;
import collagefiles.model.Layer;
import collagefiles.model.Pixel;
import collagefiles.model.Project;
import collagefiles.view.GUIView2;



public class GUIController2 extends JFrame implements ActionListener {
  private GUIView2 view;
  private JPanel currentLayer;
  private int x_offset;
  private int y_offset;
  private Project project;
  private JLabel imgHolder = new JLabel();

  public GUIController2(GUIView2 view) {
    super();
    this.view = view;
    this.currentLayer = view.addLayer();
    this.view.setListener(this);


  }

  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

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

      this.project = new BasicCollageProject(pWidth, pHeight,255);
    }

    if (command.equals("apply-filter")) {
      String[] options = {"normal", "red-component", "blue-component","green-component"};
      String filter = (String) JOptionPane.showInputDialog(view.getFrame(), "Choose a filter:", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      if (filter != null) {
        List<String> array = new ArrayList<>();
        for (Layer layer : this.project.getLayers()) {
          array.add(layer.getName());
        }
        String[] options2 = array.toArray(new String[0]);
        String layer = (String) JOptionPane.showInputDialog(view.getFrame(), "Choose a layer:", "Layer", JOptionPane.QUESTION_MESSAGE, null, options2, options2[0]);
        if (layer != null) {
          System.out.print("Old filter of " + layer + ": " + this.project.getLayers().get(array.indexOf(layer)).getFilter() + "\n");
          this.project.setFilter(layer, filter);
          this.view.displayImage(this.project.getLayers().get(array.indexOf(layer)).getImages().get(0),x_offset,y_offset);
          this.view.revalidate();
          this.view.repaint();
          System.out.print("New filter of " + layer + ": " + this.project.getLayers().get(array.indexOf(layer)).getFilter() + "\n");
        }
      }
    }

    if (command.equals("add-layer")) {
      String layerName = JOptionPane.showInputDialog(view.getFrame(), "Enter layer name:");
      this.currentLayer = view.addLayer();

      this.project.addLayer(layerName);

      view.addLayerButton(layerName);
    } else if (command.equals("add-image")) {
      JFileChooser fileChooser = new JFileChooser();
      int result = fileChooser.showOpenDialog(view.getFrame());
      if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();

        String x_string = JOptionPane.showInputDialog(view.getFrame(), "Enter x-coordinate:");
        String y_string = JOptionPane.showInputDialog(view.getFrame(), "Enter y-coordinate:");
        try {
          x_offset = Integer.parseInt(x_string);
          y_offset = Integer.parseInt(y_string);
          Image imageToAdd = this.readImage(selectedFile.getAbsolutePath());
          this.project.addImageToLayer("bob",imageToAdd,x_offset,y_offset);
          this.view.displayImage(this.project.getLayers().get(0).getImages().get(0),x_offset,y_offset);

        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(view.getFrame(), "Invalid coordinates.");
        }
      }



    }
  }
  public void displayImage(Image pixels, int xOffset, int yOffset) {
    int width = pixels.getPixels().size();
    int height = pixels.getPixels().get(0).size();

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = pixels.getPixels().get(y).get(x);
        int color =pixel.getPixelColor().getRGB();
        image.setRGB(x, y, color);
      }
    }

    ImageIcon icon = new ImageIcon(image);

    this.imgHolder.setIcon(icon);



    this.imgHolder.setBorder(BorderFactory.createEmptyBorder(yOffset, xOffset, 0, 0));

    this.currentLayer.add(this.imgHolder);

    this.view.getFrame().getContentPane().add(this.currentLayer, BorderLayout.CENTER);
    this.view.revalidate();
    this.view.getFrame().repaint();

  }
  private Image readImage(String path) {
    Scanner sc = null;

    try {
      sc = new Scanner(new FileInputStream(path));
    } catch (FileNotFoundException e) {

        JOptionPane.showMessageDialog(view.getFrame(), ("File " + path + " not found!"));

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

        JOptionPane.showMessageDialog(view.getFrame(),("Invalid PPM file: plain RAW file should begin with P3"));

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
}