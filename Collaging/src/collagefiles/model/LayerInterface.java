package collagefiles.model;

import java.util.List;

public interface LayerInterface {
  /**
   * Adds image onto specified coordinate of layer's background image.
   * Mutates the background image to copy over the specified image.
   *
   * @param xPos  Horizontal position on background image.
   * @param yPos  Vertical position on background image.
   * @param image Image to be added on background.
   * @throws IllegalArgumentException If positions are invalid.
   */
  void placeImage(int xPos, int yPos, ImageInterface image) throws IllegalArgumentException;

  void setFilter(String filter);

  /**
   * Applies a filter to all the images on a layer.
   *
   * @param filter Represents filter component.
   */
  void applyFilter(String filter, ImageInterface backgroundImage);

  /**
   * Acquires the images on the layer.
   *
   * @return All the images of this layer.
   */
  List<ImageInterface> getImages();

  /**
   * Formats the layer images into a PPM file.
   *
   * @return String representing image ppm.
   */
  String getImagePPM();

  /**
   * Formats image into more general text file.
   *
   * @return String format of layer images.
   */
  String getImageTxt();

  /**
   * Returns the filter of this layer.
   *
   * @return String representing filter.
   */
  String getFilter();

  /**
   * Returns the name of the layer.
   *
   * @return name of layer.
   */
  String getName();

  /**
   * Converts each layer to a string description of the images.
   *
   * @return String of the images.
   */
  String toString();
}
