package collagefiles.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of an image, primarily displayed as.
 * An arraylist of arraylist(pixels).
 */
public class Image {
  protected ArrayList<ArrayList<Pixel>> pixels;
  ArrayList<ArrayList<Pixel>> filterPixels;

  /**
   * Creates a blank image of a specified size.
   *
   * @param width  Of blank image.
   * @param height Of blank image.
   * @throws IllegalArgumentException If a height/width is less than 1.
   */
  public Image(int width, int height, boolean opaque) throws IllegalArgumentException {
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException("Invalid height/width");
    }
    this.pixels = new ArrayList<ArrayList<Pixel>>();

    for (int i = 0; i < height; i++) {
      this.pixels.add(new ArrayList<>());
    }
    for (List l : this.pixels) {
      for (int i = 0; i < width; i++) {
        if (opaque) {
          l.add(new Pixel(255, 255, 255, 255));
        } else {
          l.add(new Pixel(255, 255, 255, 0));
        }
      }
    }
    this.filterPixels = this.getPixels();
  }

  public Image(int width, int height, String base) {
    this.pixels = new ArrayList<>();
    this.filterPixels = this.getPixels();
    ;
  }

  /**
   * Typical image constructor.
   * Builds from arraylist of arraylist(pixels).
   *
   * @param pixelList What image is constructed from.
   * @throws IllegalArgumentException Null pixel lists provided.
   */
  public Image(ArrayList<ArrayList<Pixel>> pixelList) throws IllegalArgumentException {
    if (pixelList == null) {
      throw new IllegalArgumentException("Invalid pixelList");
    }
    this.pixels = pixelList;
    this.filterPixels = this.getPixels();
  }

  /**
   * Filter this image red by iterating through each of its pixels.
   */
  public void filterImageRed() {

    this.filterPixels = this.getPixels();
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.colorMeRed();

      }
    }
  }

  /**
   * Filter this image green by iterating through each of its pixels.
   */
  public void filterImageGreen() {

    this.filterPixels = this.getPixels();
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.colorMeGreen();
      }
    }
  }

  /**
   * Filter this image blue by iterating through each of its pixels.
   */
  public void filterImageBlue() {
    this.filterPixels = this.getPixels();
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.colorMeBlue();
      }
    }
  }

  /**
   * Brighten this image based on a type of brightening.
   *
   * @param brightness Representing brightness type.
   */
  public void brightenImage(String brightness) {
    this.filterPixels = this.getPixels();
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.brightenMe(brightness);
      }
    }
  }

  /**
   * Darken this image based on a type of darkening.
   *
   * @param darkness Representing darkness type.
   */
  public void darkenImage(String darkness) {
    this.filterPixels = this.getPixels();
    for (List<Pixel> row : this.filterPixels) {
      for (int i = 0; i < row.size(); i++) {
        row.get(i).darkenMe(darkness);
      }
    }
  }

  public void differenceImage(Image backgroundImage) {
    this.filterPixels = this.getPixels();
    for (int i = 0; i < this.pixels.size(); i++) {
      for (int j = 0; j < this.pixels.get(0).size(); j++) {
        this.filterPixels.get(i).get(j).differenceMe(
                backgroundImage.filterPixels.get(i)
                        .get(j));
      }
    }
  }

  public void multiplyImage(Image backgroundImage) {
    this.filterPixels = this.getPixels();
    for (int i = 0; i < this.pixels.size(); i++) {
      for (int j = 0; j < this.pixels.get(0).size(); j++) {
        this.filterPixels.get(i).get(j).hslFunc(
                backgroundImage.filterPixels.get(i)
                        .get(j), "multiply");
      }
    }
  }

  public void screenImage(Image backgroundImage) {
    this.filterPixels = this.getPixels();
    for (int i = 0; i < this.pixels.size(); i++) {
      for (int j = 0; j < this.pixels.get(0).size(); j++) {
        this.filterPixels.get(i).get(j).hslFunc(
                backgroundImage.filterPixels.get(i)
                        .get(j), "screen");
      }
    }
  }

  /**
   * Gets the ArrayList of ArrayList(pixel) for testing and operations.
   *
   * @return The ArrayList of ArrayList(pixel).
   */
  public ArrayList<ArrayList<Pixel>> getFilterPixels() {
    return this.filterPixels;
  }

  /**
   * Gets the pixels of the image for creating deep copies.
   *
   * @return The ArrayList of ArrayList(pixel).
   */
  public ArrayList<ArrayList<Pixel>> getPixels() {

    ArrayList<ArrayList<Pixel>> copy = new ArrayList<ArrayList<Pixel>>();

    for (ArrayList<Pixel> pixelRow : (ArrayList<ArrayList<Pixel>>) this.pixels.clone()) {
      ArrayList<Pixel> newRow = new ArrayList<>();
      for (Pixel pix : pixelRow) {
        Pixel newPix = new Pixel(pix.getPixelColor());
        newRow.add(newPix);
      }
      copy.add(newRow);
    }
    return copy;
    }


}

