import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;

import collagefiles.model.Pixel;

import static org.junit.Assert.assertEquals;

/**
 * Testing pixel class.
 */
public class TestPixel {

  Pixel redPixel;
  Pixel greenPixel;
  Pixel bluePixel;
  Pixel whitePixel;
  Pixel blackPixel;
  Pixel mixedPixel;
  Pixel transparentPixel;
  Pixel opaquePixel;
  Pixel amaranthPixel;

  @Before
  public void init() {
    this.redPixel = new Pixel(255,0,0,255);
    this.greenPixel = new Pixel(0,255,0,255);
    this.bluePixel = new Pixel(0,0,255,255);
    this.whitePixel = new Pixel(255,255,255,255);
    this.blackPixel = new Pixel(0,0,0,255);
    this.mixedPixel = new Pixel(128,128,128,255);
    this.transparentPixel = new Pixel(255,255,255,0);
    this.opaquePixel = new Pixel(255,255,255,128);
    this.amaranthPixel = new Pixel(159, 43, 104, 255);
  }


  @Test
  public void testValidConstruction() {
    assertEquals(new Color(255, 0, 0, 255), this.redPixel.getPixelColor());
    assertEquals(new Color(0, 255, 0, 255), this.greenPixel.getPixelColor());
    assertEquals(new Color(0, 0, 255, 255), this.bluePixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 255), this.whitePixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.blackPixel.getPixelColor());
    assertEquals(new Color(159, 43, 104, 255), this.amaranthPixel.getPixelColor());
    assertEquals(new Color(128, 128, 128, 255), this.mixedPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 0), this.transparentPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 128), this.opaquePixel.getPixelColor());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionLargeRed() {
    this.redPixel = new Pixel(256, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionSmallRed() {
    this.redPixel = new Pixel(-1, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionLargeGreen() {
    this.redPixel = new Pixel(0, 256, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionSmallGreen() {
    this.redPixel = new Pixel(0, -1, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionLargeBlue() {
    this.redPixel = new Pixel(256, 0, 0, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionSmallBlue() {
    this.redPixel = new Pixel(0, 0, -1, 0);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionLargeAlpha() {
    this.redPixel = new Pixel(0, 0, 0, 256);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionSmallAlpha() {
    this.redPixel = new Pixel(0, 0, 0, -1);
  }

  @Test
  public void testRedFilter() {
    this.redPixel.colorMeRed();
    this.greenPixel.colorMeRed();
    this.bluePixel.colorMeRed();
    this.whitePixel.colorMeRed();
    this.blackPixel.colorMeRed();
    this.mixedPixel.colorMeRed();
    this.transparentPixel.colorMeRed();
    this.opaquePixel.colorMeRed();

    assertEquals(new Color(255,0,0,255), this.redPixel.getPixelColor());
    assertEquals(new Color(0,0,0,255), this.greenPixel.getPixelColor());
    assertEquals(new Color(0,0,0,255), this.bluePixel.getPixelColor());
    assertEquals(new Color(255,0,0,255), this.whitePixel.getPixelColor());
    assertEquals(new Color(0,0,0,255).getAlpha(), this.blackPixel.getPixelColor().getAlpha());
    assertEquals(new Color(128,0,0,255), this.mixedPixel.getPixelColor());
    assertEquals(new Color(255,255,255,0), this.transparentPixel.getPixelColor());
    assertEquals(new Color(255,0,0,128), this.opaquePixel.getPixelColor());
  }

  @Test
  public void testGreenFilter() {
    this.redPixel.colorMeGreen();
    this.greenPixel.colorMeGreen();
    this.bluePixel.colorMeGreen();
    this.whitePixel.colorMeGreen();
    this.blackPixel.colorMeGreen();
    this.mixedPixel.colorMeGreen();
    this.transparentPixel.colorMeGreen();
    this.opaquePixel.colorMeGreen();

    assertEquals(new Color(0, 0, 0, 255), this.redPixel.getPixelColor());
    assertEquals(new Color(0, 255, 0, 255), this.greenPixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.bluePixel.getPixelColor());
    assertEquals(new Color(0, 255, 0, 255), this.whitePixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.blackPixel.getPixelColor());
    assertEquals(new Color(0, 128, 0, 255), this.mixedPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 0), this.transparentPixel.getPixelColor());
    assertEquals(new Color(0, 255, 0, 128), this.opaquePixel.getPixelColor());
  }

  @Test
  public void testBlueFilter() {
    this.redPixel.colorMeBlue();
    this.greenPixel.colorMeBlue();
    this.bluePixel.colorMeBlue();
    this.whitePixel.colorMeBlue();
    this.blackPixel.colorMeBlue();
    this.mixedPixel.colorMeBlue();
    this.transparentPixel.colorMeBlue();
    this.opaquePixel.colorMeBlue();

    assertEquals(new Color(0, 0, 0, 255), this.redPixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.greenPixel.getPixelColor());
    assertEquals(new Color(0, 0, 255, 255), this.bluePixel.getPixelColor());
    assertEquals(new Color(0, 0, 255, 255), this.whitePixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.blackPixel.getPixelColor());
    assertEquals(new Color(0, 0, 128, 255), this.mixedPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 0), this.transparentPixel.getPixelColor());
    assertEquals(new Color(0, 0, 255, 128), this.opaquePixel.getPixelColor());
  }

  @Test
  public void testBrightenValue() {
    this.redPixel.brightenMe("brighten-value");
    this.greenPixel.brightenMe("brighten-value");
    this.bluePixel.brightenMe("brighten-value");
    this.whitePixel.brightenMe("brighten-value");
    this.blackPixel.brightenMe("brighten-value");
    this.mixedPixel.brightenMe("brighten-value");
    this.amaranthPixel.brightenMe("brighten-value");
    this.transparentPixel.brightenMe("brighten-value");
    this.opaquePixel.brightenMe("brighten-value");

    assertEquals(new Color(255, 255, 255, 255), this.redPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 255), this.greenPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 255), this.bluePixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 255), this.whitePixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.blackPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 255), this.mixedPixel.getPixelColor());
    assertEquals(new Color(255, 202, 255, 255), this.amaranthPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 0), this.transparentPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 128), this.opaquePixel.getPixelColor());
  }

  @Test
  public void testBrightenIntensity() {
    this.redPixel.brightenMe("brighten-intensity");
    this.greenPixel.brightenMe("brighten-intensity");
    this.bluePixel.brightenMe("brighten-intensity");
    this.whitePixel.brightenMe("brighten-intensity");
    this.blackPixel.brightenMe("brighten-intensity");
    this.mixedPixel.brightenMe("brighten-intensity");
    this.amaranthPixel.brightenMe("brighten-intensity");
    this.transparentPixel.brightenMe("brighten-intensity");
    this.opaquePixel.brightenMe("brighten-intensity");

    assertEquals(new Color(255, 85, 85, 255), this.redPixel.getPixelColor());
    assertEquals(new Color(85, 255, 85, 255), this.greenPixel.getPixelColor());
    assertEquals(new Color(85, 85, 255, 255), this.bluePixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 255), this.whitePixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.blackPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 255), this.mixedPixel.getPixelColor());
    assertEquals(new Color(255, 145, 206, 255), this.amaranthPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 0), this.transparentPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 128), this.opaquePixel.getPixelColor());
  }

  @Test
  public void testBrightenLuma() {
    this.redPixel.brightenMe("brighten-luma");
    this.greenPixel.brightenMe("brighten-luma");
    this.bluePixel.brightenMe("brighten-luma");
    this.whitePixel.brightenMe("brighten-luma");
    this.blackPixel.brightenMe("brighten-luma");
    this.mixedPixel.brightenMe("brighten-luma");
    this.amaranthPixel.brightenMe("brighten-luma");
    this.transparentPixel.brightenMe("brighten-luma");
    this.opaquePixel.brightenMe("brighten-luma");

    assertEquals(new Color(255, 54, 54, 255), this.redPixel.getPixelColor());
    assertEquals(new Color(182, 255, 182, 255), this.greenPixel.getPixelColor());
    assertEquals(new Color(18, 18, 255, 255), this.bluePixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 255), this.whitePixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.blackPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 255), this.mixedPixel.getPixelColor());
    assertEquals(new Color(231, 115, 176, 255), this.amaranthPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 0), this.transparentPixel.getPixelColor());
    assertEquals(new Color(255, 255, 255, 128), this.opaquePixel.getPixelColor());
  }


  @Test
  public void testDarkenValue() {
    this.redPixel.darkenMe("darken-value");
    this.greenPixel.darkenMe("darken-value");
    this.bluePixel.darkenMe("darken-value");
    this.whitePixel.darkenMe("darken-value");
    this.blackPixel.darkenMe("darken-value");
    this.mixedPixel.darkenMe("darken-value");
    this.amaranthPixel.darkenMe("darken-value");
    this.transparentPixel.darkenMe("darken-value");
    this.opaquePixel.darkenMe("darken-value");

    assertEquals(new Color(0, 0, 0, 255), this.redPixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.greenPixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.bluePixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.whitePixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.blackPixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.mixedPixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.amaranthPixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 0), this.transparentPixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 128), this.opaquePixel.getPixelColor());
  }

  @Test
  public void testDarkenIntensity() {
    this.redPixel.darkenMe("darken-intensity");
    this.greenPixel.darkenMe("darken-intensity");
    this.bluePixel.darkenMe("darken-intensity");
    this.whitePixel.darkenMe("darken-intensity");
    this.blackPixel.darkenMe("darken-intensity");
    this.mixedPixel.darkenMe("darken-intensity");
    this.amaranthPixel.darkenMe("darken-intensity");
    this.transparentPixel.darkenMe("darken-intensity");
    this.opaquePixel.darkenMe("darken-intensity");

    assertEquals(new Color(170, 0, 0, 255), this.redPixel.getPixelColor());
    assertEquals(new Color(0, 170, 0, 255), this.greenPixel.getPixelColor());
    assertEquals(new Color(0, 0, 170, 255), this.bluePixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.whitePixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.blackPixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.mixedPixel.getPixelColor());
    assertEquals(new Color(57, 0, 2, 255), this.amaranthPixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 0), this.transparentPixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 128), this.opaquePixel.getPixelColor());
  }

  @Test
  public void testDarkenLuma() {
    this.redPixel.darkenMe("darken-luma");
    this.greenPixel.darkenMe("darken-luma");
    this.bluePixel.darkenMe("darken-luma");
    this.whitePixel.darkenMe("darken-luma");
    this.blackPixel.darkenMe("darken-luma");
    this.mixedPixel.darkenMe("darken-luma");
    this.amaranthPixel.darkenMe("darken-luma");
    this.transparentPixel.darkenMe("darken-luma");
    this.opaquePixel.darkenMe("darken-luma");

    assertEquals(new Color(201, 0, 0, 255), this.redPixel.getPixelColor());
    assertEquals(new Color(0, 73, 0, 255), this.greenPixel.getPixelColor());
    assertEquals(new Color(0, 0, 237, 255), this.bluePixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.whitePixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.blackPixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.mixedPixel.getPixelColor());
    assertEquals(new Color(87, 0, 32, 255), this.amaranthPixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 0), this.transparentPixel.getPixelColor());
    assertEquals(new Color(0, 0, 0, 128), this.opaquePixel.getPixelColor());
  }

  @Test
  public void testDifference() {
    this.redPixel.differenceMe(this.bluePixel);
    this.amaranthPixel.differenceMe(this.greenPixel);
    this.whitePixel.differenceMe(this.blackPixel);

    assertEquals(new Color(255,0,255), this.redPixel.getPixelColor());
    assertEquals(new Color(159,212,104), this.amaranthPixel.getPixelColor());
    assertEquals(new Color(255,255,255), this.whitePixel.getPixelColor());

  }

  @Test
  public void testConvertRGBToHSL() {

    ArrayList<Double> testList = new ArrayList<Double>();
    testList.add(0.0);
    testList.add(1.0);
    testList.add(0.5);
    assertEquals(
    this.redPixel.convertRGBtoHSL((double) this.redPixel.getPixelColor().getRed() / 255,
            (double) this.redPixel.getPixelColor().getGreen() / 255,
            (double) this.redPixel.getPixelColor().getBlue() / 255),
            testList);

    testList = new ArrayList<Double>();
    testList.add(120.0);
    testList.add(1.0);
    testList.add(0.5);
    assertEquals(
            this.greenPixel.convertRGBtoHSL((double) this.greenPixel.getPixelColor().getRed() / 255,
                    (double) this.greenPixel.getPixelColor().getGreen() / 255,
                    (double) this.greenPixel.getPixelColor().getBlue() / 255),
            testList);

    testList = new ArrayList<Double>();
    testList.add(240.0);
    testList.add(1.0);
    testList.add(0.5);
    assertEquals(
            this.bluePixel.convertRGBtoHSL((double) this.bluePixel.getPixelColor().getRed() / 255,
                    (double) this.bluePixel.getPixelColor().getGreen() / 255,
                    (double) this.bluePixel.getPixelColor().getBlue() / 255),
            testList);
  }

  @Test
  public void testConvertHSLToRGB() {

    ArrayList<Double> testList = new ArrayList<Double>();
    testList.add(255.0);
    testList.add(0.0);
    testList.add(0.0);
    assertEquals(this.redPixel.convertHSLtoRGB(0.0,1.0,0.5), testList);

    testList = new ArrayList<Double>();
    testList.add(0.0);
    testList.add(255.0);
    testList.add(0.0);
    assertEquals(this.greenPixel.convertHSLtoRGB(120.0,1.0,0.5), testList);


    testList = new ArrayList<Double>();
    testList.add(0.0);
    testList.add(0.0);
    testList.add(255.0);
    assertEquals(this.bluePixel.convertHSLtoRGB(240.0,1.0,0.5), testList);

  }

  @Test
  public void testMultiply() {
    this.redPixel.hslFunc(this.bluePixel,"multiply");
    assertEquals(new Color(127,0,0), this.redPixel.getPixelColor());

    this.bluePixel.hslFunc(this.greenPixel,"multiply");
    assertEquals(new Color(0,0,127), this.bluePixel.getPixelColor());

    this.whitePixel.hslFunc(this.blackPixel, "multiply");
    assertEquals(new Color(0,0,0), this.whitePixel.getPixelColor());
  }

  @Test
  public void testScreen() {
    this.redPixel.hslFunc(this.bluePixel,"screen");
    assertEquals(new Color(255,127,127), this.redPixel.getPixelColor());

    this.bluePixel.hslFunc(this.greenPixel,"screen");
    assertEquals(new Color(127,127,255), this.bluePixel.getPixelColor());

    this.blackPixel.hslFunc(this.whitePixel, "screen");
    assertEquals(new Color(255,255,255), this.blackPixel.getPixelColor());
  }

  @Test
  public void testAddPixels() {
    bluePixel.addPixels(this.mixedPixel);
    whitePixel.addPixels(this.transparentPixel);



assertEquals(new Color(255,255,255),whitePixel.getPixelColor());
    assertEquals(new Color(128, 128, 128), bluePixel.getPixelColor());


    bluePixel.addPixels(redPixel);
    whitePixel.addPixels(blackPixel);

  }
  @Test
  public void testAddPixels2() {

    transparentPixel.addPixels(this.whitePixel);
    redPixel.addPixels(bluePixel);
    transparentPixel.addPixels(opaquePixel);


    assertEquals(new Color(255,255,255),whitePixel.getPixelColor());
    assertEquals(new Color(0,0,255),redPixel.getPixelColor());




    bluePixel.addPixels(redPixel);
    whitePixel.addPixels(blackPixel);

  }
  @Test
  public void testAddPixels3() {

    redPixel.addPixels(bluePixel);
    transparentPixel.addPixels(opaquePixel);



    assertEquals(new Color(0,0,255),redPixel.getPixelColor());
    assertEquals(new Color(255,255,255,128),transparentPixel.getPixelColor());



    bluePixel.addPixels(redPixel);
    whitePixel.addPixels(blackPixel);

  }
  @Test
  public void testAddPixels4() {

assertEquals(transparentPixel.getPixelColor().toString(), new Color(255,255,255,0).toString());
    transparentPixel.addPixels(new Pixel(255,255,255,0));
    assertEquals(new Color(255, 255, 255,0),transparentPixel.getPixelColor());

  }
}
