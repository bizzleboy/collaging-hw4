package collagefiles.model;

import java.awt.Color;
import java.util.ArrayList;

public interface PixelInterface {
  /**
   * Change the color of this pixel to red for filtering purposes.
   */
  void colorMeRed();

  /**
   * Change the color of this pixel to green for filtering purposes.
   */
  void colorMeGreen();

  /**
   * Change the color of this pixel to blue for filtering purposes.
   */
  void colorMeBlue();

  /**
   * Brighten the pixel based on a type of brightness.
   *
   * @param brightnessType Type of brightness.
   */
  void brightenMe(String brightnessType);

  /**
   * Darken the pixel based on a type of darkness.
   *
   * @param darknessType Type of darkness.
   */
  void darkenMe(String darknessType);

  /**
   * Adjust the pixel based on the difference between its values and the values of the
   * pixel behind in the composite image.
   *
   * @param backgroundPixel the pixel directly behind this one in the composite image.
   */
  void differenceMe(PixelInterface backgroundPixel);

  /**
   * Applied a specified filter function to the pixel based on this pixel and the.
   * Background  pixel's HSL values.
   * @param backgroundPixel The pixel to filter this pixel by.
   * @param func The filter function to apply to this filter.
   */
  void hslFunc(PixelInterface backgroundPixel, String func);

  /**
   * Perform pixel math in order to account for varying alpha levels.
   *
   * @param pix2 The other pixel to add.
   */
  void addPixels(PixelInterface pix2);


  

  /**
   * Returns the color of this pixel for testing purposes.
   *
   * @return Color of the pixel.
   */
  Color getPixelColor();

  /**
   * Convers an RGB representation in the range 0-1 into an HSL.
   * Representation where.
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
  ArrayList<Double> convertRGBtoHSL(double r, double g, double b);

  /**
   * Convers an HSL representation where.
   * <ul>
   * <li> 0 &lt;= H &lt; 360</li>
   * <li> 0 &lt;= S &lt;= 1</li>
   * <li> 0 &lt;= L &lt;= 1</li>
   * </ul>
   * Into an RGB representation where each component is in the range 0-1.
   *
   * @param hue        hue of the HSL representation
   * @param saturation saturation of the HSL representation
   * @param lightness  lightness of the HSL representation
   */

  ArrayList<Double> convertHSLtoRGB(double hue, double saturation, double lightness);
}
