package collagefiles.model;

import java.util.ArrayList;
import java.util.List;

public class BasicCollageProject implements Project{
  List<Layer> layers;
  int height;
  int width;

  public BasicCollageProject (int canvasWidth, int canvasHeight) {
    this.layers = new ArrayList<Layer>();
    this.height= canvasHeight;
    this.width = canvasWidth;
    this.layers.add(new Layer("name",this.width,this.height));
  }



  @Override
  public String saveProject() {
    return "";
  }

  @Override
  public void addLayer(String layerName) {
    this.layers.add((new Layer(layerName,this.width,this.height)));

  }

  @Override
  public void addImageToLayer(String layerName, Image imageToAdd, int xPos, int yPos) {
    this.layers.get(this.layers.indexOf(layerName)).placeImage(xPos,yPos,imageToAdd);
  }

  @Override
  public void setFilter(String layerName, String filterType) {
    this.layers.get(this.layers.indexOf(layerName)).applyFilter(filterType);
  }

  @Override
  public String saveImage() {
    return "";
  }

  @Override
  public void quit() {

  }
}
