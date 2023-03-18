package collagefiles.model;

import java.util.ArrayList;
import java.util.List;

public class BasicCollageProject implements Project{
  List<Layer> layers;
  int height;
  int width;
  int maxVal;

  public BasicCollageProject (int canvasWidth, int canvasHeight, int maxVal) {
    this.layers = new ArrayList<Layer>();
    this.height= canvasHeight;
    this.width = canvasWidth;
    this.maxVal = maxVal;
    this.layers.add(new Layer("name",this.width,this.height));
  }

  @Override
  public void addLayer(String layerName) {

    boolean layerUnique = true;

    for (Layer l: this.layers) {
      if (l.getName().equals(layerName)) {
        layerUnique = false;
        System.out.print("Layer with this name already exists\n");
      }
    }
    if (layerUnique) {
      this.layers.add((new Layer(layerName,this.width,this.height)));
      System.out.print("Layer added\n");
    }
  }

  @Override
  public void addImageToLayer(String layerName, Image imageToAdd, int xPos, int yPos) {
    for (Layer l: this.layers) {
      if (l.getName().equals(layerName)) {
        l.placeImage(xPos,yPos,imageToAdd);
        System.out.print("Image added to layer\n");
        break;
      }
    }
  }

  @Override
  public void setFilter(String layerName, String filterType) {
    for (Layer l: this.layers) {
      if (l.getName().equals(layerName)) {
        l.applyFilter(filterType);
        System.out.print("Filter applied to layer\n");
        break;
      }
    }
    //this.layers.get(this.layers.indexOf(layerName)).applyFilter(filterType);
  }

  @Override
  public String saveProject(String projectPath) {
    String projectString = "";
    projectString += (projectPath + "\n");
    projectString += (this.width + " " + this.height + "\n" + this.maxVal);
    for (Layer l: this.layers) {
      projectString += (l.getName() + " " + l.getFilter() + "\n");
      projectString += (l.getImageTxt());
    }
    return projectString;
  }

  @Override
  public String saveImage(String imagePath) {
    String imageString = "";
    String[] paths = imagePath.split("/");
    imageString += "P3\n";
    imageString += ("#" + paths[paths.length-1].split("\\.")[0] + "\n");
    imageString += (this.width + " " + this.height + "\n" + this.maxVal + "\n" + "\n");

    Image startImage = null;

    for (Layer l: this.layers) {
      if (startImage == null) {
        startImage = l.getImages().get(0);
      } else {
        l.placeImage(0,0,startImage);
        startImage = l.getImages().get(0);
      }
      imageString = (l.getImagePPM());
    }
    return imageString;
  }

  public List<Layer> getLayers() {
    return this.layers;
  }

  @Override
  public void quit() {

  }
}