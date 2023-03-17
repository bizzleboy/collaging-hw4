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
    this.layers.get(this.layers.indexOf(layerName)).applyFilter(filterType);
  }

  @Override
  public String saveProject(String imagePath) {
    String imageString = "";
    String[] filePaths = imagePath.split("\\\\");
    imageString += (filePaths[filePaths.length-1].split(".")[0]);
    imageString += (this.width + " " + this.height + "\n" + this.maxVal + "\n");
    for (Layer l: this.layers) {
      imageString += (l.getName() + " " + l.getFilter() + "\n");
      imageString += (l.getImageTxt());
    }
    return imageString;
  }

  @Override
  public String saveImage(String imagePath) {
    String imageString = "";
    String[] filePaths = imagePath.split("\\\\");
    imageString += "P3\n";
    imageString += String.format("#%s\n",filePaths[filePaths.length-1].split(".")[0]);
    imageString += (this.width + " " + this.height + "\n");
    for (Layer l: this.layers) {
      imageString += (l.getImagePPM() + "\n");
    }
    return imageString;
  }

  @Override
  public void quit() {

  }
}
