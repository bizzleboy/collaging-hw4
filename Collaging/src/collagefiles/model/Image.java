package collagefiles.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Image {
  ArrayList<ArrayList<Pixel>> pixels;
  ArrayList<ArrayList<Pixel>> filterPixels;
  String filePath;
  String name;

  public Image(int width, int height) {

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
<<<<<<< HEAD
}
  public Image(List<List<Pixel>> pixelList){
  this.pixels = pixelList;
  }
=======
>>>>>>> ba743554b65f9c1e050efdfcd4307b9ac3d27d30

  public Image(ArrayList<ArrayList<Pixel>> pixelList) {
    this.pixels = pixelList;

<<<<<<< HEAD
public String idk() {
=======

  }

  public String idk() {

    Pixel p = this.pixels.get(38).get(37);
>>>>>>> ba743554b65f9c1e050efdfcd4307b9ac3d27d30

    p.colorMeRed();
    return p.getPixelColor().toString();
  }

<<<<<<< HEAD
  p.colorMeRed();
  return p.getPixelColor().toString();
}
  protected void filterImageRed(){
=======
  public void filterImageRed() {
>>>>>>> ba743554b65f9c1e050efdfcd4307b9ac3d27d30
    this.filterPixels = pixels;
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.colorMeRed();

      }
    }
  }

<<<<<<< HEAD
  protected void filterImageGreen(){
=======
  public void filterImageGreen() {
>>>>>>> ba743554b65f9c1e050efdfcd4307b9ac3d27d30
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

