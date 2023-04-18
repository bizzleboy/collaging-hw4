package collagefiles.model;

import java.util.ArrayList;
import java.util.List;

/**
 * A representation of an image, primarily displayed as.
 * An arraylist of arraylist(pixels).
 */
public class Image implements ImageInterface {
  protected ArrayList<ArrayList<PixelInterface>> pixels;
  ArrayList<ArrayList<PixelInterface>> filterPixels;

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
    this.pixels = new ArrayList<ArrayList<PixelInterface>>();

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

  /**
   * Another standard image constructor. Creates a copy of the pixels for.
   * Non-destructive image creation.
   */
  public Image() {
    this.pixels = new ArrayList<>();
    this.filterPixels = this.getPixels();
  }

  /**
   * Typical image constructor.
   * Builds from arraylist of arraylist(pixels).
   *
   * @param pixelList What image is constructed from.
   * @throws IllegalArgumentException Null pixel lists provided.
   */
  public Image(ArrayList<ArrayList<PixelInterface>> pixelList) throws IllegalArgumentException {
    if (pixelList == null) {
      throw new IllegalArgumentException("Invalid pixelList");
    }
    this.pixels = pixelList;
    this.filterPixels = this.getPixels();
  }

  @Override
  public void filterImageRed() {

    this.filterPixels = this.getPixels();
    for (List<PixelInterface> row : this.filterPixels) {
      for (PixelInterface pixel : row) {
        pixel.colorMeRed();

      }
    }
  }

  @Override
  public void filterImageGreen() {

    this.filterPixels = this.getPixels();
    for (List<PixelInterface> row : this.filterPixels) {
      for (PixelInterface pixel : row) {
        pixel.colorMeGreen();
      }
    }
  }

  @Override
  public void filterImageBlue() {
    this.filterPixels = this.getPixels();
    for (List<PixelInterface> row : this.filterPixels) {
      for (PixelInterface pixel : row) {
        pixel.colorMeBlue();
      }
    }
  }

  @Override
  public void brightenImage(String brightness) {
    this.filterPixels = this.getPixels();
    for (List<PixelInterface> row : this.filterPixels) {
      for (PixelInterface pixel : row) {
        pixel.brightenMe(brightness);
      }
    }
  }

  @Override
  public void darkenImage(String darkness) {
    this.filterPixels = this.getPixels();
    for (List<PixelInterface> row : this.filterPixels) {
      for (int i = 0; i < row.size(); i++) {
        row.get(i).darkenMe(darkness);
      }
    }
  }

  @Override
  public void differenceImage(ImageInterface backgroundImage) {
    this.filterPixels = this.getPixels();
    for (int i = 0; i < this.pixels.size(); i++) {
      for (int j = 0; j < this.pixels.get(0).size(); j++) {
        this.filterPixels.get(i).get(j).differenceMe(
                backgroundImage.getFilterPixels().get(i)
                        .get(j));
      }
    }
  }

  @Override
  public void multiplyImage(ImageInterface backgroundImage) {
    this.filterPixels = this.getPixels();
    for (int i = 0; i < this.pixels.size(); i++) {
      for (int j = 0; j < this.pixels.get(0).size(); j++) {
        this.filterPixels.get(i).get(j).hslFunc(
                backgroundImage.getFilterPixels().get(i)
                        .get(j), "multiply");
      }
    }
  }

  @Override
  public void screenImage(ImageInterface backgroundImage) {
    this.filterPixels = this.getPixels();
    for (int i = 0; i < this.pixels.size(); i++) {
      for (int j = 0; j < this.pixels.get(0).size(); j++) {
        this.filterPixels.get(i).get(j).hslFunc(
                backgroundImage.getFilterPixels().get(i)
                        .get(j), "screen");
      }
    }
  }

  @Override
  public ArrayList<ArrayList<PixelInterface>> getFilterPixels() {
    return this.filterPixels;
  }

  @Override
  public ArrayList<ArrayList<PixelInterface>> getPixels() {

    ArrayList<ArrayList<PixelInterface>> copy = new ArrayList<ArrayList<PixelInterface>>();

    for (ArrayList<PixelInterface> pixelRow : (ArrayList<ArrayList<PixelInterface>>) this.pixels.clone()) {
      ArrayList<PixelInterface> newRow = new ArrayList<>();
      for (PixelInterface pix : pixelRow) {
        Pixel newPix = new Pixel(pix.getPixelColor());
        newRow.add(newPix);
      }
      copy.add(newRow);
    }
    return copy;
  }


}

