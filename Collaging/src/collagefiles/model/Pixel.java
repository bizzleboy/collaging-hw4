package collagefiles.model;

import java.awt.*;

/**
 * Represents a singular pixel that will be displayed on a layer. Can be 1 of any RGB color.
 */
public class Pixel {
  private Color pixelColor;
  private int red;
  private int green;
  private int blue;
  private int alpha;

  //I dont think we need to throw an exception since the color class
  //unless we wanna throw for not inputting a valid color? idk if we have to worry about that
  public Pixel(Color color){
    this.pixelColor = color;
    this.red = color.getRed();
    this.blue = color.getBlue();
    this.green = color.getGreen();
    this.alpha = color.getAlpha();
  }

  //idk if this constructor is necessary
  //i guess for lossy compression
  public Pixel(int red, int green, int blue, int alpha) throws IllegalArgumentException {

    if (red < 0 || red > 255 || green < 0 || green > 255
            || blue < 0 || blue > 255 || alpha < 0 || alpha > 255) {
      throw new IllegalArgumentException("RGB and alpha values must be between 0 and 255");
    }

    this.pixelColor = new Color(this.red, this.green, this.blue, this.alpha);
    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
  }

  /**
   * Change the color of this pixel to red for filtering purposes
   */
  public void colorMeRed(){
    this.blue = 0;
    this.green = 0;
    this.pixelColor = new Color(this.red,0,0,this.alpha);
  }

  /**
   * Change the color of this pixel to green for filtering purposes
   */
  public void colorMeGreen(){
    this.red = 0;
    this.blue = 0;
    this.pixelColor = new Color(0,this.green,0,this.alpha);
  }
  /**
   * Change the color of this pixel to blue for filtering purposes
   */
  public void colorMeBlue(){
    this.red = 0;
    this.green = 0;
    this.pixelColor = new Color(0,0,this.blue,this.alpha);
  }

  public void brightenMe(String brightnessType){
    int brightness;
    switch(brightnessType){
      case "brighten-value":
        brightness = Math.max(this.red,Math.max(this.green,this.blue));
        break;
      case"brighten-intensity":
        brightness = (this.red+this.green+this.blue)/3;
        break;
      case "brighten-luma":
        brightness = (int)(.2126*this.red+ .7152*this.green+ .0722*this.blue);
        break;
      default: brightness= 0;
    }
    int newRed;
    int newBlue;
    int newGreen;

    if (this.red + brightness > 255){
      newRed = 255;
    } else{
      newRed = this.red + brightness;
    }
    if (this.blue + brightness > 255){
      newBlue = 255;
    } else{
      newBlue = this.blue + brightness;
    }
    if (this.green + brightness > 255){
      newGreen = 255;
    } else {
      newGreen = this.green + brightness;
    }
    this.pixelColor = new Color(newRed,newGreen,newBlue,this.alpha);
  }

  public void darkenMe(String darknessType){
    int darkness;
    switch(darknessType){
      case "darken-value":
        darkness = Math.max(this.red,Math.max(this.green,this.blue));
        break;
      case"darken-intensity":
        darkness= (this.red+this.green+this.blue)/3;
        break;
      case "darken-luma":
        darkness=(int)(.2126*this.red+ .7152*this.green+ .0722*this.blue);
        break;
      default: darkness= 0;
    }
    int newRed;
    int newBlue;
    int newGreen;

    if (this.red - darkness < 0){
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
    this.pixelColor = new Color(newRed,newGreen,newBlue,this.alpha);
  }

  public void addPixels(Pixel pix2) {
    float pix1Alpha = (float) this.alpha;
    float pix2Alpha = (float) pix2.alpha;
    float alphaPct = (pix2Alpha/255 + pix1Alpha/255 * (1 - (pix2Alpha/255)));
    int newRed = (int) ((pix2Alpha/255 * pix2.red + this.red*(pix1Alpha/255) *(1-pix2Alpha/255)) * (1/alphaPct));
    int newGreen = (int) ((pix2Alpha/255 * pix2.green + this.green*(pix1Alpha/255) * (1-pix2Alpha/255)) * (1/alphaPct));
    int newBlue = (int) ((pix2Alpha/255 * pix2.blue + this.blue*(pix1Alpha/255) *(1-pix2Alpha/255)) * (1/alphaPct));
    this.pixelColor = new Color(newRed,newGreen,newBlue, (int) (alphaPct*255));
    this.blue = newBlue;
    this.red= newRed;
    this.green = newGreen;
    this.alpha =  (int) (alphaPct*255);
//    this.pixelColor = new Color((this.red + pix2.red) / 2,
//            (this.blue + pix2.blue) / 2,
//            (this.green + pix2.green) / 2,
//            (this.alpha + pix2.alpha) / 2);
  }

  public Color getPixelColor() {
    return this.pixelColor;
  }

  public String toString() {
    return String.format("%d %d %d %d", this.red, this.blue, this.green, this.alpha);
  }
}
