package collagefiles.model;

import java.util.ArrayList;

public class Filter {

  //protected ArrayList<Pixel> pixels;
  protected String filterType;

  public Filter(String filterType) {
    this.filterType = filterType;
  }

  public void applyFilter(ArrayList<ArrayList<Pixel>> pixels) {
    for (ArrayList<Pixel> row: pixels) {
      for (Pixel pixelToFilter: row) {
        switch (this.filterType) {
          case "red-component":
            if (pixelToFilter.getPixelColor().getAlpha() == 0){
              //pass
            } else {
              //pixelToFilter.green = 0;
              //pixelToFilter.blue = 0;
            }
          case "blue-component":
          case "green-component":
          case "brighten-value":
          case "brighten-luma":
          case "brighten-intensity":
          case "darker-value":
          case "darken-luma":
          case "darken-intensity":
        }
      }
    }
  }

  private void brightnessCount(Pixel pixel) {

  }
}
