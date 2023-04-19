package collagefiles.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a layer, that contains images and filters.
 */
public class Layer implements LayerInterface {
  List<ImageInterface> imagesOnLayer;
  private String filter;
  private final String name;

  /**
   * Construction of a layer that creates a background image and its name.
   *
   * @param name   Of layer.
   * @param width  Of layer.
   * @param height Of layer.
   */
  public Layer(String name, int width, int height, boolean opaque) {
    this.imagesOnLayer = new ArrayList<ImageInterface>();
    this.name = name;
    this.filter = "normal";
    ImageInterface background = new Image(width, height, opaque);
    this.imagesOnLayer.add(background);
  }

  @Override
  public void placeImage(int xPos, int yPos, ImageInterface image) throws IllegalArgumentException {
    int imageSize = this.imagesOnLayer.get(0).getPixels().size();
    if (xPos < 0 || yPos >= imageSize || yPos < 0
            || xPos >= imageSize) {
      throw new IllegalArgumentException("Invalid positions");
    }
    int xIndex = xPos;
    int yIndex = yPos;

    for (List<PixelInterface> list : image.getFilterPixels()) {
      if (yIndex >= imageSize) {
        break;
      } else {
        for (PixelInterface p : list) {
          if (xIndex >= imageSize) {
            break;
          } else {

            PixelInterface alteredPixel = this.imagesOnLayer.get(0).getPixels().get(yIndex).get(xIndex);
            alteredPixel.addPixels(p);
            this.imagesOnLayer.get(0).setPixelAt(alteredPixel,xIndex,yIndex);

//                    getPixels().get(yIndex).set(xIndex, alteredPixel);
            this.imagesOnLayer.get(0).getFilterPixels().get(yIndex).set(xIndex, alteredPixel);
            xIndex += 1;

          }
        }
        xIndex = xPos;
        yIndex += 1;
      }
    }
    //this.imagesOnLayer.get(0).filterPixels = this.imagesOnLayer.get(0).getPixels();
    this.imagesOnLayer.add(image);

  }

  @Override
  public void setFilter(String filter) {
    this.filter = filter;
  }

  @Override
  public void applyFilter(String filter, ImageInterface backgroundImage) {
    switch (filter) {
      case "normal":
        this.filter = filter;
        for (ImageInterface image : imagesOnLayer) {
          image.normalMe();
//          ArrayList<ArrayList<PixelInterface>> imagePixels = image.getFilterPixels();
//          imagePixels = image.getPixels();
        }
        break;
      case "red-component":
        this.filter = filter;
        for (ImageInterface image : imagesOnLayer) {
          image.filterImageRed();
        }
        break;
      case "blue-component":
        this.filter = filter;
        for (ImageInterface image : imagesOnLayer) {
          image.filterImageBlue();

        }
        break;
      case "green-component":
        this.filter = filter;
        for (ImageInterface image : imagesOnLayer) {
          image.filterImageGreen();

        }
        break;
      case "brighten-value":
        this.filter = filter;
        for (ImageInterface image : imagesOnLayer) {
          image.brightenImage("brighten-value");
        }
        break;
      case "darken-value":
        this.filter = filter;
        for (ImageInterface image : imagesOnLayer) {
          image.darkenImage("darken-value");

        }
        break;
      case "brighten-luma":
        this.filter = filter;
        for (ImageInterface image : imagesOnLayer) {
          image.brightenImage("brighten-luma");

        }
        break;
      case "darken-luma":
        this.filter = filter;
        for (ImageInterface image : imagesOnLayer) {
          image.darkenImage("darken-luma");

        }
        break;
      case "brighten-intensity":
        this.filter = filter;
        for (ImageInterface image : imagesOnLayer) {
          image.brightenImage("brighten-intensity");

        }
        break;
      case "darken-intensity":
        this.filter = filter;
        for (ImageInterface image : imagesOnLayer) {
          image.darkenImage("darken-intensity");

        }
        break;
      case "difference":
        this.filter = filter;
        if (backgroundImage.getPixels().size() != 0) {
          for (ImageInterface image : imagesOnLayer) {
            image.differenceImage(backgroundImage);
          }
        }
        break;
      case "multiply":
        this.filter = filter;
        if (backgroundImage.getPixels().size() != 0) {
          for (ImageInterface image : imagesOnLayer) {
            image.multiplyImage(backgroundImage);
          }
        }
        break;
      case "screen":
        this.filter = filter;
        if (backgroundImage.getPixels().size() != 0) {
          for (ImageInterface image : imagesOnLayer) {
            image.screenImage(backgroundImage);
          }
        }
        break;
      default:
        System.out.println("Invalid Input!");
    }
    System.out.print(filter + " applied in layer class at layer '" + this.name + "'\n");
  }

  @Override
  public List<ImageInterface> getImages() {
    return this.imagesOnLayer;
  }

  @Override
  public String getImagePPM() {

    String imageString = "";

    for (List<PixelInterface> list : this.imagesOnLayer.get(0).getFilterPixels()) {
      for (PixelInterface p : list) {
        imageString += (String.format("%d %d %d\n",
                p.getPixelColor().getRed(),
                p.getPixelColor().getGreen(),
                p.getPixelColor().getBlue()
        ));
      }
    }
    return imageString;

  }

  @Override
  public String getImageTxt() {
    String imageString = "";
    for (List<PixelInterface> list : this.imagesOnLayer.get(0).getPixels()) {
      for (PixelInterface p : list) {

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

  @Override
  public String getFilter() {
    return this.filter;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public String toString() {
    int numPics = 0;
    for (ImageInterface i : this.imagesOnLayer) {
      numPics++;
    }
    return this.name + " " + this.filter + " # of images placed on this layer: "
            + (numPics - 1) + "\n";
  }
}