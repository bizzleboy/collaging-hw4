package collagefiles.model;

import java.awt.Color;

/**
 * Represents a singular pixel that will be displayed on a layer. Can be 1 of any RGB color.
 */
public class Pixel {

  private int red;
  private int green;
  private int blue;
  private int alpha;

  /**
   * Constructs a pixel using the color class.
   *
   * @param color Color of desired pixel.
   */
  public Pixel(Color color) {

    this.red = color.getRed();
    this.blue = color.getBlue();
    this.green = color.getGreen();
    this.alpha = color.getAlpha();
  }

  /**
   * Pixel constructor that is created on provided rgba values.
   *
   * @param red Integer representing red.
   * @param green Integer representing green.
   * @param blue Integer representing blue.
   * @param alpha Integer representing alpha.
   * @throws IllegalArgumentException If negative values or values over 255 are provided.
   */
  public Pixel(int red, int green, int blue, int alpha) throws IllegalArgumentException {

    if (red < 0 || red > 255 || green < 0 || green > 255
            || blue < 0 || blue > 255 || alpha < 0 || alpha > 255) {
      throw new IllegalArgumentException("RGB and alpha values must be between 0 and 255");
    }

    this.red = red;
    this.green = green;
    this.blue = blue;
    this.alpha = alpha;
  }

  /**
   * Change the color of this pixel to red for filtering purposes.
   */
  public void colorMeRed() {

    this.green = 0;
    this.blue = 0;
  }

  /**
   * Change the color of this pixel to green for filtering purposes.
   */
  public void colorMeGreen() {

    this.blue = 0;
    this.red = 0;
  }

  /**
   * Change the color of this pixel to blue for filtering purposes.
   */
  public void colorMeBlue() {

    this.green = 0;
    this.red = 0;
  }

  /**
   * Brighten the pixel based on a type of brightness.
   *
   * @param brightnessType Type of brightness.
   */
  public void brightenMe(String brightnessType) {
    int brightness;
    switch (brightnessType) {
      case "brighten-value":
        brightness = Math.max(this.red, Math.max(this.green, this.blue));
        break;
      case "brighten-intensity":
        brightness = (this.red + this.green + this.blue) / 3;
        break;
      case "brighten-luma":
        brightness = (int) (.2126 * this.red + .7152 * this.green + .0722 * this.blue);
        break;
      default:
        brightness = 0;
    }
    int newRed;
    int newBlue;
    int newGreen;

    if (this.red + brightness > 255) {
      newRed = 255;
    } else {
      newRed = this.red + brightness;
    }
    if (this.blue + brightness > 255) {
      newBlue = 255;
    } else {
      newBlue = this.blue + brightness;
    }
    if (this.green + brightness > 255) {
      newGreen = 255;
    } else {
      newGreen = this.green + brightness;
    }
    this.red = newRed;
    this.green = newGreen;
    this.blue = newBlue;
  }

  /**
   * Darken the pixel based on a type of darkness.
   *
   * @param darknessType Type of darkness.
   */
  public void darkenMe(String darknessType) {
    int darkness;
    switch (darknessType) {
      case "darken-value":
        darkness = Math.max(this.red, Math.max(this.green, this.blue));
        break;
      case "darken-intensity":
        darkness = (this.red + this.green + this.blue) / 3;
        break;
      case "darken-luma":
        darkness = (int) Math.round(.2126 * this.red + .7152 * this.green + .0722 * this.blue);
        break;
      default:
        darkness = 0;
    }
    int newRed;
    int newBlue;
    int newGreen;

    if (this.red - darkness < 0) {
      newRed = 0;
    } else {
      newRed = this.red - darkness;
    }
    if (this.blue - darkness < 0) {
      newBlue = 0;
    } else {
      newBlue = this.blue - darkness;
    }
    if (this.green - darkness < 0) {
      newGreen = 0;
    } else {
      newGreen = this.green - darkness;
    }
    this.red = newRed;
    this.green = newGreen;
    this.blue = newBlue;
  }

  /**
   * Perform pixel math in order to account for varying alpha levels.
   *
   * @param pix2 The other pixel to add.
   */
  public void addPixels(Pixel pix2) {
    if(this.getPixelColor().toString().equals(new Color(255,255,255,0).toString())
            && pix2.getPixelColor().toString().equals(new Color(255,255,255,0).toString())
    && pix2.getPixelColor().getAlpha() == 0 && this.getPixelColor().getAlpha() ==0){
      //pass, without this, adding 2 transparent pixels makes black
    } else {
      float pix1Alpha = (float) this.alpha;
      float pix2Alpha = (float) pix2.alpha;
      float alphaPct = (pix2Alpha / 255 + pix1Alpha / 255 * (1 - (pix2Alpha / 255)));
      int newRed = Math.round((pix2Alpha / 255 * pix2.red + this.red * (pix1Alpha / 255)
              * (1 - pix2Alpha / 255)) * (1 / alphaPct));
      int newGreen = Math.round((pix2Alpha / 255 * pix2.green + this.green * (pix1Alpha / 255)
              * (1 - pix2Alpha / 255)) * (1 / alphaPct));
      int newBlue = Math.round((pix2Alpha / 255 * pix2.blue + this.blue * (pix1Alpha / 255)
              * (1 - pix2Alpha / 255)) * (1 / alphaPct));
      this.blue = newBlue;
      this.red = newRed;
      this.green = newGreen;
      this.alpha = (int) (alphaPct * 255);
    }

  }

  /**
   * Returns the color of this pixel for testing purposes.
   *
   * @return Color of the pixel.
   */
  public Color getPixelColor() {
    return new Color(this.red, this.green, this.blue, this.alpha);
  }



}