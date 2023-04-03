package collagefiles.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collage project that can handle PPMs.
 */
public class BasicCollageProject implements Project {
  private final List<Layer> layers;
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
  public BasicCollageProject(int canvasWidth, int canvasHeight, int maxVal) throws IllegalArgumentException{
    //ADDED EXCEPTION
    if (canvasHeight <1 || canvasWidth <1 || maxVal < 1){
      throw new IllegalArgumentException("invalid arg");
    }
    this.layers = new ArrayList<Layer>();
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

    for (Layer l : this.layers) {
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
  public void addImageToLayer(String layerName, Image imageToAdd, int xPos, int yPos) {
    for (Layer l : this.layers) {
      if (l.getName().equals(layerName)) {
        l.placeImage(xPos, yPos, imageToAdd);
        System.out.print("Image added to layer\n");
        break;
      } else{
        System.out.print("Image not added\n");
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
    for (int i = 0; i < this.layers.size(); i++) {
      //for (Layer l : this.layers) {
      if (this.layers.get(i).getName().equals(layerName)) {
        this.layers.get(i).setFilter(filterType);
        this.getLayers().get(0).imagesOnLayer.get(0).filterImageGreen();
//        if (i > 0) {
//          this.layers.get(i).applyFilter(filterType, this.stackToImage(i - 1));
//        } else {
//          this.layers.get(i).applyFilter(filterType, new Image(0,0,"base"));
//        }
//        System.out.print("Filter set for layer\n");
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
    for (Layer l : this.layers) {
      projectString += (l.getName() + " " + l.getFilter() + "\n");
      projectString += (l.getImageTxt());
    }
    return projectString;
  }

  /**
   * Saves the image as a PPM.
   *
   * @param imagePath Destination of particular image.
   * @return PPM formatted file.
   */
  @Override
  public String saveImage(String imagePath) {
    String imageString = "";
    String[] paths = imagePath.split("/");
    imageString += "P3\n";
    imageString += ("#" + paths[paths.length-1].split("\\.")[0] + "\n");
    imageString += (this.width + " " + this.height + "\n" + this.maxVal + "\n" + "\n");

    Layer startLayer = new Layer("base", this.width, this.height, false);

    startLayer.applyFilter(startLayer.getFilter(),new Image(0,0,"base"));

    startLayer.placeImage(0,0,this.stackToImage(this.layers.size()-1));

    imageString += startLayer.getImagePPM();
    return imageString;
  }


  /**
   * Returns the list of layers to perform operations and test on.
   *
   * @return Layers in this project.
   */
  public List<Layer> getLayers() {
    return this.layers;
  }

  public Image stackToImage(int startIndex) {


    List<Layer> stack = new ArrayList<Layer>();

    for (int j = 0; j <= startIndex; j++) {
      stack.add(this.layers.get(j));
    }

    Layer startLayer = stack.get(0);
    startLayer.applyFilter(startLayer.getFilter(),new Image(0,0,"base"));

    for (int k = 1; k < stack.size(); k++) {
      stack.get(k).applyFilter(stack.get(k).getFilter(), startLayer.getImages().get(0));
      startLayer.placeImage(0,0,stack.get(k).getImages().get(0));
    }

    return startLayer.getImages().get(0);
  }
}