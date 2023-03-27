package collagefiles.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a layer, that contains images and filters.
 */
public class Layer {
  List<Image> imagesOnLayer;
  private String filter;
  private final String name;

  /**
   * Construction of a layer that creates a background image and its name.
   *
   * @param name   Of layer.
   * @param width  Of layer.
   * @param height Of layer.
   */
  public Layer(String name, int width, int height) {
    this.imagesOnLayer = new ArrayList<Image>();
    this.name = name;
    this.filter = "normal";
    Image background = new Image(width, height);
    this.imagesOnLayer.add(background);
  }

  /**
   * Adds image onto specified coordinate of layer's background image.
   * Mutates the background image to copy over the specified image.
   *
   * @param xPos  Horizontal position on background image.
   * @param yPos  Vertical position on background image.
   * @param image Image to be added on background.
   * @throws IllegalArgumentException If positions are invalid.
   */
  public void placeImage(int xPos, int yPos, Image image) throws IllegalArgumentException {
    if (xPos < 0 || yPos >= this.imagesOnLayer.get(0).pixels.size() || yPos < 0
            || xPos >= this.imagesOnLayer.get(0).pixels.get(0).size()) {
      throw new IllegalArgumentException("Invalid positions");
    }
    int xIndex = xPos;
    int yIndex = yPos;


    for (List<Pixel> list : image.filterPixels) {
      if (yIndex >= this.imagesOnLayer.get(0).pixels.size()) {
        break;
      } else {
        for (Pixel p : list) {
          if (xIndex >= this.imagesOnLayer.get(0).pixels.get(0).size()) {
            break;
          } else {
            Pixel alteredPixel = this.imagesOnLayer.get(0).filterPixels.get(yIndex).get(xIndex);

            alteredPixel.addPixels(p);
            this.imagesOnLayer.get(0).filterPixels.get(yIndex).set(xIndex, alteredPixel);
            xIndex += 1;

          }
        }
        xIndex = xPos;
        yIndex += 1;
      }
    }
    this.imagesOnLayer.add(image);

  }

  /**
   * Applies a filter to all the images on a layer.
   *
   * @param filter Represents filter component.
   */
  public void applyFilter(String filter, Image backgroundImage) {
    switch (filter) {
      case "normal":
        this.filter = filter;
        for (Image image : imagesOnLayer) {
          image.filterPixels = image.pixels;
        }
        break;
      case "red-component":
        this.filter = filter;
        for (Image image : imagesOnLayer) {
          image.filterImageRed();
        }
        break;
      case "blue-component":
        this.filter = filter;
        for (Image image : imagesOnLayer) {
          image.filterImageBlue();

        }
        break;
      case "green-component":
        this.filter = filter;
        for (Image image : imagesOnLayer) {
          image.filterImageGreen();

        }
        break;
      case "brighten-value":
        this.filter = filter;
        for (Image image : imagesOnLayer) {
          image.brightenImage("brighten-value");
        }
        break;
      case "darken-value":
        this.filter = filter;
        for (Image image : imagesOnLayer) {
          image.darkenImage("darken-value");

        }
        break;
      case "brighten-luma":
        this.filter = filter;
        for (Image image : imagesOnLayer) {
          image.brightenImage("brighten-luma");

        }
        break;
      case "darken-luma":
        this.filter = filter;
        for (Image image : imagesOnLayer) {
          image.darkenImage("darken-luma");

        }
        break;
      case "brighten-intensity":
        this.filter = filter;
        for (Image image : imagesOnLayer) {
          image.brightenImage("brighten-intensity");

        }
        break;
      case "darken-intensity":
        this.filter = filter;
        for (Image image : imagesOnLayer) {
          image.darkenImage("darken-intensity");

        }
        break;
      //case "difference":
      //  this.filter = filter;
      default:
        System.out.println("Invalid Input!");
    }

  }

  /**
   * Acquires the images on the layer.
   *
   * @return All the images of this layer.
   */
  public List<Image> getImages() {
    return this.imagesOnLayer;
  }

  /**
   * Formats the layer images into a PPM file.
   *
   * @return String representing image ppm.
   */
  public String getImagePPM() {

    String imageString = "";

    for (List<Pixel> list : this.imagesOnLayer.get(0).filterPixels) {
      for (Pixel p : list) {
        imageString += (String.format("%d %d %d\n",
                p.getPixelColor().getRed(),
                p.getPixelColor().getGreen(),
                p.getPixelColor().getBlue()
        ));
      }
    }
    return imageString;

  }

  /**
   * Formats image into more general text file.
   *
   * @return String format of layer images.
   */
  public String getImageTxt() {
    String imageString = "";
    for (List<Pixel> list : this.imagesOnLayer.get(0).filterPixels) {
      for (Pixel p : list) {

        imageString += (String.format("%d %d %d %d\n",
                p.getPixelColor().getRed(),
                p.getPixelColor().getGreen(),
                p.getPixelColor().getBlue(),
                p.getPixelColor().getAlpha()
        ));
      }
    }
    return imageString;
  }

  /**
   * Returns the filter of this layer.
   *
   * @return String representing filter.
   */
  public String getFilter() {
    return this.filter;
  }

  /**
   * Returns the name of the layer.
   *
   * @return name of layer.
   */
  public String getName() {
    return this.name;
  }


  public String toString() {
    int numPics = 0;
    for (Image i: this.imagesOnLayer){
      numPics ++;
    }
    return this.name + " " + this.filter + " # of images placed on this layer: " + (numPics -1) +"\n" ;
  }
}