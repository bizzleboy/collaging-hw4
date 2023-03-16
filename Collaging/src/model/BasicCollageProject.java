package model;

import java.util.List;

public class BasicCollageProject implements Project{
  List<Layer> layers;
  @Override
  public Project newProject(int canvasWidth, int canvasHeight) {
    return null;
  }

  @Override
  public Project loadProject(String path) {
    return null;
  }

  @Override
  public void saveProject() {

  }

  @Override
  public void addLayer(String layerName) {
    this.layers.add((new Layer(layerName)));

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
