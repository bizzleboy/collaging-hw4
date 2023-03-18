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
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException("Invalid height/width");
    }
    this.pixels = new ArrayList<ArrayList<Pixel>>();

    for (int i = 0; i < height; i++) {
      this.pixels.add(new ArrayList<>());
    }
    for (List l : this.pixels) {
      for (int i = 0; i < width; i++) {
        l.add(new Pixel(255,255,255,0));
      }
    }
    this.filterPixels = this.pixels;
  }

  public Image(ArrayList<ArrayList<Pixel>> pixelList) throws IllegalArgumentException {
    if (pixelList == null) {
      throw new IllegalArgumentException("Invalid pixelList");
    }
    this.pixels = pixelList;
    this.filterPixels = this.pixels;
  }


  public void filterImageRed() {

    this.filterPixels = this.pixels;
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.colorMeRed();

      }
    }
  }


  public void filterImageGreen() {

    this.filterPixels = this.pixels;
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.colorMeGreen();
      }
    }
  }

  public void filterImageBlue() {
    this.filterPixels = this.pixels;
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.colorMeBlue();
      }
    }
  }

  public void brightenImage(String brightness) {
    this.filterPixels = this.pixels;
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.brightenMe(brightness);
      }
    }
  }

  public void darkenImage(String darkness) {
    this.filterPixels = this.pixels;
    for (List<Pixel> row : this.filterPixels) {
      for (Pixel pixel : row) {
        pixel.darkenMe(darkness);
      }
    }
  }


  public ArrayList<ArrayList<Pixel>> getPixels() {
    return this.filterPixels;
  }

}

