package collagefiles.model;

import java.util.List;

/**
 * Represents a project that a user can perform collage functions on.
 */
public interface Project {
  /**
   * Saves the project in a particular format.
   *
   * @param projectPath Destination to where you save the project.
   * @return The formatted project.
   */
  String saveProject(String projectPath);

  /**
   * Adds a layer to the current collage project.
   *
   * @param layerName Name for layer to be added.
   */
  void addLayer(String layerName);

  /**
   * Adding an image onto an existing layer on the project.
   *
   * @param layerName Name of existing layer.
   * @param image     Image to add.
   * @param xPos      Horizontal coordinate to add to on layer.
   * @param yPos      Vertical coordinate to add to on layer.
   */
  void addImageToLayer(String layerName, Image image, int xPos, int yPos);

  /**
   * Changes the filter of the specified layer.
   *
   * @param layerName  String of layer to change.
   * @param filterType String specifying the layer to change to.
   */
  void setFilter(String layerName, String filterType);


  /**
   * View the layers of this project, primarily for testing purposes.
   *
   * @return All the layers of this project.
   */
  public List<Layer> getLayers();

  /**
   * Returns a composite image of all the layers below a specified layer.
   *
   * @param startIndex the index of the layer underneath which to get the composite image.
   * @return a composite image of the below layers.
   */
  public Image stackToImage(int startIndex);
}
