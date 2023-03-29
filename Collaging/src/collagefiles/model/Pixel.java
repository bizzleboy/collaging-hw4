package collagefiles.model;

import java.awt.Color;
import java.util.ArrayList;

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
    if (this.getPixelColor().getAlpha() == 0){
      //pass
    } else {
      this.green = 0;
      this.blue = 0;
    }
  }

  /**
   * Change the color of this pixel to green for filtering purposes.
   */
  public void colorMeGreen() {
    if (this.getPixelColor().getAlpha() == 0){
      //pass
    } else {
      this.blue = 0;
      this.red = 0;
    }
  }

  /**
   * Change the color of this pixel to blue for filtering purposes.
   */
  public void colorMeBlue() {
    if (this.getPixelColor().getAlpha() == 0){
      //pass
    } else {
      this.green = 0;
      this.red = 0;
    }
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
   * Adjust the pixel based on the difference between its values and the values of the
   * pixel behind in the composite image.
   * @param backgroundPixel the pixel directly behind this one in the composite image.
   */
  public void differenceMe(Pixel backgroundPixel) {

    int newRed;
    int newBlue;
    int newGreen;

    newRed = Math.abs(this.red - backgroundPixel.red);
    newBlue = Math.abs(this.blue - backgroundPixel.blue);
    newGreen = Math.abs(this.green - backgroundPixel.green);

    this.red = newRed;
    this.green = newGreen;
    this.blue = newBlue;
  }

  public void hslFunc(Pixel backgroundPixel, String func) {

    ArrayList<Double> thisHSL = this.convertRGBtoHSL(
            ((double) this.red) / 255,
            ((double) this.green) / 255,
            ((double) this.blue) /255);

    ArrayList<Double> otherHSL = this.convertRGBtoHSL(
            ((double) backgroundPixel.red) / 255,
            ((double) backgroundPixel.green) / 255,
            ((double) backgroundPixel.blue) /255);

    ArrayList<Double> thisRGB = new ArrayList<>();

    if (func.equals("screen")) {
      thisRGB = this.convertHSLtoRGB(
              thisHSL.get(0),
              thisHSL.get(1),
              (1 - ((1 - thisHSL.get(2)) * (1 - otherHSL.get(2)))));

    } else if (func.equals("multiply")) {
      thisRGB = this.convertHSLtoRGB(
              thisHSL.get(0),
              thisHSL.get(1),
              thisHSL.get(2) * otherHSL.get(2));
    }

    if (thisRGB.size() == 3) {
      this.red = thisRGB.get(0).intValue();
      this.green = thisRGB.get(1).intValue();
      this.blue = thisRGB.get(2).intValue();
    }
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






  /**
   * Convers an RGB representation in the range 0-1 into an HSL
   * representation where
   * <ul>
   * <li> 0 &lt;= H &lt; 360</li>
   * <li> 0 &lt;= S &lt;= 1</li>
   * <li> 0 &lt;= L &lt;= 1</li>
   * </ul>
   *
   * @param r red value of the RGB between 0 and 1
   * @param g green value of the RGB between 0 and 1
   * @param b blue value of the RGB between 0 and 1
   */
  public ArrayList<Double> convertRGBtoHSL(double r, double g, double b) {

    ArrayList<Double> HSLs = new ArrayList<Double>();
    double componentMax = Math.max(r, Math.max(g, b));
    double componentMin = Math.min(r, Math.min(g, b));
    double delta = componentMax - componentMin;

    double lightness = (componentMax + componentMin) / 2;
    double hue, saturation;
    if (delta == 0) {
      hue = 0;
      saturation = 0;
    } else {
      saturation = delta / (1 - Math.abs(2 * lightness - 1));
      hue = 0;
      if (componentMax == r) {
        hue = (g - b) / delta;
        while (hue < 0) {
          hue += 6; //hue must be positive to find the appropriate modulus
        }
        hue = hue % 6;
      } else if (componentMax == g) {
        hue = (b - r) / delta;
        hue += 2;
      } else if (componentMax == b) {
        hue = (r - g) / delta;
        hue += 4;
      }

      hue = hue * 60;
    }

    HSLs.add(hue);
    HSLs.add(saturation);
    HSLs.add(lightness);

    return HSLs;

    //System.out.println("RGB (" + r + "," + g + "," + b + ") to HSL => (" + hue + "," + saturation + "," + lightness + ")");
  }


  /**
   * Convers an HSL representation where
   * <ul>
   * <li> 0 &lt;= H &lt; 360</li>
   * <li> 0 &lt;= S &lt;= 1</li>
   * <li> 0 &lt;= L &lt;= 1</li>
   * </ul>
   * into an RGB representation where each component is in the range 0-1
   *
   * @param hue        hue of the HSL representation
   * @param saturation saturation of the HSL representation
   * @param lightness  lightness of the HSL representation
   */

  public ArrayList<Double> convertHSLtoRGB(double hue, double saturation, double lightness) {

    ArrayList<Double> RGBs = new ArrayList<Double>();

    double r = convertFn(hue, saturation, lightness, 0) * 255;
    double g = convertFn(hue, saturation, lightness, 8) * 255;
    double b = convertFn(hue, saturation, lightness, 4) * 255;

    RGBs.add(r);
    RGBs.add(g);
    RGBs.add(b);

    return RGBs;
    //System.out.println("HSL (" + hue + "," + saturation + "," + lightness + ") to RGB => (" + r + "," + g + "," + b + ")");
  }

  /*
   * Helper method that performs the translation from the HSL polygonal
   * model to the more familiar RGB model
   */
  private double convertFn(double hue, double saturation, double lightness, int n) {
    double k = (n + (hue / 30)) % 12;
    double a = saturation * Math.min(lightness, 1 - lightness);

    return lightness - a * Math.max(-1, Math.min(k - 3, Math.min(9 - k, 1)));
  }



}