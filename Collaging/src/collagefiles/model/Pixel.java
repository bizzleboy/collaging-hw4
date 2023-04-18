package collagefiles.model;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Represents a singular pixel that will be displayed on a layer. Can be 1 of any RGB color.
 */
public class Pixel implements PixelInterface {

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
   * @param red   Integer representing red.
   * @param green Integer representing green.
   * @param blue  Integer representing blue.
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

  @Override
  public void colorMeRed() {
    if (this.getPixelColor().getAlpha() == 0) {
      //pass
    } else {
      this.green = 0;
      this.blue = 0;
    }
  }

  @Override
  public void colorMeGreen() {
    if (this.getPixelColor().getAlpha() == 0) {
      //pass
    } else {
      this.blue = 0;
      this.red = 0;
    }
  }

  @Override
  public void colorMeBlue() {
    if (this.getPixelColor().getAlpha() == 0) {
      //pass
    } else {
      this.green = 0;
      this.red = 0;
    }
  }

  @Override
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

  @Override
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

  @Override
  public void differenceMe(PixelInterface backgroundPixel) {

    int newRed;
    int newBlue;
    int newGreen;

    newRed = Math.abs(this.red - backgroundPixel.getPixelColor().getRed());
    newBlue = Math.abs(this.blue - backgroundPixel.getPixelColor().getBlue());
    newGreen = Math.abs(this.green - backgroundPixel.getPixelColor().getGreen());

    this.red = newRed;
    this.green = newGreen;
    this.blue = newBlue;
  }

  @Override
  public void hslFunc(PixelInterface backgroundPixel, String func) {

    ArrayList<Double> thisHSL = this.convertRGBtoHSL(
            ((double) this.red) / 255,
            ((double) this.green) / 255,
            ((double) this.blue) / 255);

    ArrayList<Double> otherHSL = this.convertRGBtoHSL(
            ((double) backgroundPixel.getPixelColor().getRed()) / 255,
            ((double) backgroundPixel.getPixelColor().getGreen()) / 255,
            ((double) backgroundPixel.getPixelColor().getBlue()) / 255);

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

  @Override
  public void addPixels(PixelInterface pix2) {
    if (this.getPixelColor().toString().equals(new Color(255, 255, 255, 0).toString())
            && pix2.getPixelColor().toString().equals(new Color(255, 255, 255, 0).toString())
            && pix2.getPixelColor().getAlpha() == 0 && this.getPixelColor().getAlpha() == 0) {
      //pass, without this, adding 2 transparent pixels makes black
    } else {
      float pix1Alpha = (float) this.alpha;
      float pix2Alpha = (float) pix2.getPixelColor().getAlpha();
      float alphaPct = (pix2Alpha / 255 + pix1Alpha / 255 * (1 - (pix2Alpha / 255)));
      int newRed = Math.round((pix2Alpha / 255 * pix2.getPixelColor().getRed() + this.red * (pix1Alpha / 255)
              * (1 - pix2Alpha / 255)) * (1 / alphaPct));
      int newGreen = Math.round((pix2Alpha / 255 * pix2.getPixelColor().getGreen() + this.green * (pix1Alpha / 255)
              * (1 - pix2Alpha / 255)) * (1 / alphaPct));
      int newBlue = Math.round((pix2Alpha / 255 * pix2.getPixelColor().getBlue() + this.blue * (pix1Alpha / 255)
              * (1 - pix2Alpha / 255)) * (1 / alphaPct));
      this.blue = newBlue;
      this.red = newRed;
      this.green = newGreen;
      this.alpha = (int) (alphaPct * 255);
    }

  }

  @Override
  public Color getPixelColor() {
    return new Color(this.red, this.green, this.blue, this.alpha);
  }


  @Override
  public ArrayList<Double> convertRGBtoHSL(double r, double g, double b) {

    ArrayList<Double> hsls = new ArrayList<Double>();
    double componentMax = Math.max(r, Math.max(g, b));
    double componentMin = Math.min(r, Math.min(g, b));
    double delta = componentMax - componentMin;

    double lightness = (componentMax + componentMin) / 2;
    double hue;
    double saturation;
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

    hsls.add(hue);
    hsls.add(saturation);
    hsls.add(lightness);

    return hsls;

  }


  @Override
  public ArrayList<Double> convertHSLtoRGB(double hue, double saturation, double lightness) {

    ArrayList<Double> rgbs = new ArrayList<Double>();

    double r = convertFn(hue, saturation, lightness, 0) * 255;
    double g = convertFn(hue, saturation, lightness, 8) * 255;
    double b = convertFn(hue, saturation, lightness, 4) * 255;

    rgbs.add(r);
    rgbs.add(g);
    rgbs.add(b);

    return rgbs;
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