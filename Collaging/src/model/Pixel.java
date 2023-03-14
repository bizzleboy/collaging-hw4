package model;

import java.awt.*;

/**
 * Represents a singular pixel that will be displayed on a layer. Can be 1 of any RGB color.
 */
public class Pixel {
  private Color pixelColor;
  private int red;
  private int green;
  private int blue;
  private int transparency;

  //I dont think we need to throw an exception since the color class
  //unless we wanna throw for not inputting a valid color? idk if we have to worry about that
  public Pixel(Color color){
    this.pixelColor =color;
  }

  //idk if this constructor is necessary
  //i guess for lossy compression
  public Pixel(int red, int green, int blue, int alpha){

    this.pixelColor = new Color(red*(alpha/255),green*(alpha/255),blue*(alpha/255));
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.transparency = alpha;
  }


}
