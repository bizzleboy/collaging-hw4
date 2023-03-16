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
  public void saveProject() {

  }

  @Override
  public void addLayer(String layerName) {
    this.layers.add((new Layer(layerName,this.width,this.height)));

  }

  @Override
  public void addImageToLayer(String layerName, String imagePath, int xPos, int yPos) {

  }

  @Override
  public void setFilter(String layerName, String filterType) {

  }

  @Override
  public void saveImage(String fileName) {

  }

  @Override
  public void quit() {

  }
}
