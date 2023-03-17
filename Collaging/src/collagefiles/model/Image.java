package collagefiles.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Image {
  protected ArrayList<ArrayList<Pixel>> pixels;
  ArrayList<ArrayList<Pixel>> filterPixels;
  String filePath;
  String name;

  public Image(int width, int height) throws IllegalArgumentException {
if(width < 1 || height < 1){
  throw new IllegalArgumentException("Invalid height/width");
}
    this.pixels = new ArrayList<ArrayList<Pixel>>();

    for (int i = 0; i < height; i++) {
      this.pixels.add(new ArrayList<Pixel>());
    }
    for (List l : this.pixels) {
      for (int i = 0; i < width; i++) {
        l.add(new Pixel(new Color(255, 255, 255, 0)));
      }
    }
  }

  public Image(ArrayList<ArrayList<Pixel>> pixelList) {
    this.pixels = pixelList;
  }

  public String idk() {

    Pixel p = this.pixels.get(38).get(37);


    p.colorMeRed();
    return p.getPixelColor().toString();

}


  public void filterImageRed() {

    this.filterPixels = pixels;
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.colorMeRed();

      }
    }
  }


  public void filterImageGreen() {

    this.filterPixels = pixels;
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.colorMeGreen();
      }
    }
  }

  public void filterImageBlue() {
    this.filterPixels = pixels;
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.colorMeBlue();
      }
    }
  }

  public void brightenImage(String brightness) {
    this.filterPixels = pixels;
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.brightenMe(brightness);
      }
    }
  }

  public void darkenImage(String darkness) {
    this.filterPixels = pixels;
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.darkenMe(darkness);
      }
    }
  }

}

