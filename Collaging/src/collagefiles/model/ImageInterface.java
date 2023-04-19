package collagefiles.model;

import java.util.ArrayList;

public interface ImageInterface {
  /**
   * Filter this image red by iterating through each of its pixels.
   */
  void filterImageRed();

  /**
   * Filter this image green by iterating through each of its pixels.
   */
  void filterImageGreen();

  /**
   * Filter this image blue by iterating through each of its pixels.
   */
  void filterImageBlue();

  /**
   * Brighten this image based on a type of brightening.
   *
   * @param brightness Representing brightness type.
   */
  void brightenImage(String brightness);

  /**
   * Darken this image based on a type of darkening.
   *
   * @param darkness Representing darkness type.
   */
  void darkenImage(String darkness);

  /**
   * Get the difference in RGB values of image and the composite image beneath it.
   *
   * @param backgroundImage Representing the composite image on the layers beneath this image.
   */
  void differenceImage(ImageInterface backgroundImage);

  /**
   * Darken this image based on the RGB values of the composite image from the layer's beneath it.
   *
   * @param backgroundImage Representing the composite image on the layers beneath this image.
   */
  void multiplyImage(ImageInterface backgroundImage);

  /**
   * Lighten this image based on the RGB values of the composite image from the layer's beneath it.
   *
   * @param backgroundImage Representing the composite image on the layers beneath this image.
   */

  void screenImage(ImageInterface backgroundImage);

  /**
   * Gets the ArrayList of ArrayList(pixel) for testing and operations.
   *
   * @return The ArrayList of ArrayList(pixel).
   */
  ArrayList<ArrayList<PixelInterface>> getFilterPixels();

  /**
   * Gets the pixels of the image for creating deep copies.
   *
   * @return The ArrayList of ArrayList(pixel).
   */
  ArrayList<ArrayList<PixelInterface>> getPixels();

  void setPixelAt(PixelInterface replacementPixel, int col, int row);

  void normalMe();
}
