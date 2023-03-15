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

    this.pixelColor = new Color(red*alpha/255,
            green*alpha/255,
            blue*alpha/255);
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.transparency = alpha;
  }

  /**
   * Change the color of this pixel to red for filtering purposes
   */
  public void colorMeRed(){
    this.pixelColor = new Color(this.red,0,0,this.transparency);
  }

  /**
   * Change the color of this pixel to green for filtering purposes
   */
  public void colorMeGreen(){
    this.pixelColor = new Color(this.red,0,0,this.transparency);
  }
  /**
   * Change the color of this pixel to blue for filtering purposes
   */
  public void colorMeBlue(){
    this.pixelColor = new Color(this.red,0,0,this.transparency);
  }

  public void brightenMe(int brightness){
    int newRed;
    int newBlue;
    int newGreen;

    if (this.red+brightness > 255){
      newRed = 255;
    } else{
      newRed = this.red+brightness;
    }
    if (this.blue+brightness > 255){
      newBlue = 255;
    } else{
      newBlue = this.blue+brightness;
    }
    if (this.green+brightness > 255){
      newGreen = 255;
    } else{
      newGreen = this.green+brightness;
    }
    this.pixelColor = new Color(newRed,newGreen,newBlue,this.transparency);
  }
  public void darkenMe(int darkness){
    int newRed;
    int newBlue;
    int newGreen;

    if (this.red-darkness < 0){
      newRed = 0;
    } else{
      newRed = this.red-darkness;
    }
    if (this.blue-darkness < 0){
      newBlue = 0;
    } else{
      newBlue = this.blue-darkness;
    }
    if (this.green-darkness < 0){
      newGreen = 0;
    } else{
      newGreen = this.green-darkness;
    }
    this.pixelColor = new Color(newRed,newGreen,newBlue,this.transparency);
  }

  public Color getPixelColor() {
    return this.pixelColor;
  }


}
