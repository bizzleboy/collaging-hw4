package collagefiles.model;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
//memes

/**
 * Represents a collage project that can handle PPMs.
 */

public class BasicCollageProject implements Project {
  private final List<LayerInterface> layers;
  private final int height;
  private final int width;
  private final int maxVal;

  /**
   * Instantiating a collage with basic parameters.
   *
   * @param canvasWidth  Width of canvas.
   * @param canvasHeight Height of canvas.
   * @param maxVal       Maximum pixel value.
   */
  public BasicCollageProject(int canvasWidth,
                             int canvasHeight, int maxVal) throws IllegalArgumentException {
    //ADDED EXCEPTION
    if (canvasHeight < 1 || canvasWidth < 1 || maxVal < 1) {
      throw new IllegalArgumentException("invalid arg");
    }
    this.layers = new ArrayList<LayerInterface>();
    this.height = canvasHeight;
    this.width = canvasWidth;
    this.maxVal = maxVal;
    this.layers.add(new Layer("background", this.width, this.height, true));
  }

  /**
   * Adds a layer to the current collage project.
   *
   * @param layerName Name for layer to be added.
   */
  @Override
  public void addLayer(String layerName) {

    boolean layerUnique = true;

    for (LayerInterface l : this.layers) {
      if (l.getName().equals(layerName)) {
        layerUnique = false;
        System.out.print("Layer with this name already exists\n");
      }
    }
    if (layerUnique) {
      this.layers.add((new Layer(layerName, this.width, this.height, false)));
      System.out.print("Layer added\n");
    }
  }

  /**
   * Adding an image onto an existing layer on the project.
   *
   * @param layerName  Name of existing layer.
   * @param imageToAdd Image to add.
   * @param xPos       Horizontal coordinate to add to on layer.
   * @param yPos       Vertical coordinate to add to on layer.
   */
  @Override
  public void addImageToLayer(String layerName, ImageInterface imageToAdd, int xPos, int yPos) {
    for (LayerInterface l : this.layers) {
      if (l.getName().equals(layerName)) {
        l.placeImage(xPos, yPos, imageToAdd);
        System.out.print("Image added to " + layerName + "\n");
        break;
      } else {
        System.out.print("Image not added to " + layerName + "\n");
      }
    }
  }

  /**
   * Changes the filter of the specified layer.
   *
   * @param layerName  String of layer to change.
   * @param filterType String specifying the layer to change to.
   */
  @Override
  public void setFilter(String layerName, String filterType) {
    System.out.print(filterType + " applied in project class \n");
    for (int i = 0; i < this.layers.size(); i++) {
      //for (Layer l : this.layers) {
      if (this.layers.get(i).getName().equals(layerName)) {
        System.out.print(layerName + " " + this.layers.get(i).getName());
        this.layers.get(i).setFilter(filterType);
        this.layers.get(i).applyFilter(filterType, this.stackToImage(i - 1));
        break;
      }
    }
  }


  /**
   * Saves the project in a particular format.
   *
   * @param projectPath Destination to where you save the project.
   * @return The formatted project.
   */
  @Override
  public String saveProject(String projectPath) {
    String projectString = "";
    String[] paths = projectPath.split("/");
    projectString += (paths[paths.length - 1].split("\\.")[0] + "\n");
    projectString += (this.width + " " + this.height);
    projectString += ("\n");
    projectString += (this.maxVal);
    projectString += ("\n");
    for (LayerInterface l : this.layers) {
      projectString += (l.getName() + " " + l.getFilter() + "\n");
      projectString += (l.getImageTxt());
    }
    return projectString;
  }




  /**
   * Returns the list of layers to perform operations and test on.
   *
   * @return Layers in this project.
   */
  public List<LayerInterface> getLayers() {
    return this.layers;
  }

  /**
   * Returns a composite image of all the layers below a specified layer.
   *
   * @param startIndex the index of the layer underneath which to get the composite image.
   * @return a composite image of the below layers.
   */
  @Override
  public ImageInterface stackToImage(int startIndex) {

    if (startIndex < 0) {
      return new Image();
    }

    List<LayerInterface> stack = new ArrayList<LayerInterface>();

    for (int j = 0; j <= startIndex; j++) {
      stack.add(this.layers.get(j));
    }

    LayerInterface startLayer = stack.get(0);
    startLayer.applyFilter(startLayer.getFilter(), new Image());

    for (int k = 1; k < stack.size(); k++) {
      stack.get(k).applyFilter(stack.get(k).getFilter(), startLayer.getImages().get(0));
      startLayer.placeImage(0, 0, stack.get(k).getImages().get(0));
    }

    return startLayer.getImages().get(0);
  }

  @Override
  public ImageInterface LoadImagePixelsFromProject(Scanner sc, ArrayList<ArrayList<PixelInterface>> imageToCreate) {
    ArrayList<ArrayList<PixelInterface>> imageColors = new ArrayList<>();

    for (ArrayList<PixelInterface> l : imageToCreate) {
      for (int x = 0; x < imageToCreate.size(); x++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        int a = sc.nextInt();
        l.add(new Pixel(r, g, b, a));

      }
      imageColors.add(l);
    }
    return new Image(imageColors);
  }

  @Override
  public ImageInterface LoadImagePixelsFromProjectPNGJPEG(BufferedImage imageToCreate) {
    ArrayList<ArrayList<PixelInterface>> imageColors = new ArrayList<>();

    int width = imageToCreate.getWidth();
    int height = imageToCreate.getHeight();

    for (int i = 0; i < height; i++) {
      ArrayList<PixelInterface> rowPixels = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        int rgb = imageToCreate.getRGB(j, i);
        Color color = new Color(rgb);

        rowPixels.add(new Pixel(color.getRed(), color.getGreen(),
                color.getBlue(), color.getAlpha()));
      }
      imageColors.add(rowPixels);
    }

    return new Image(imageColors);
  }

}