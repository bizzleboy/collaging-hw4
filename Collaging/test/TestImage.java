import org.junit.Before;

import java.util.ArrayList;
import java.util.Collections;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import collagefiles.model.Image;
import collagefiles.model.Pixel;


import static org.junit.Assert.assertEquals;

public class TestImage {

  Pixel redPixel;
  Pixel greenPixel;
  Pixel bluePixel;
  Pixel whitePixel;
  Pixel blackPixel;
  Pixel mixedPixel;
  Pixel transparentPixel;
  Pixel opaquePixel;
  Pixel amaranthPixel;
  Pixel backgroundPixel;

  Image image1;
  Image blankImage;


  Image image2;
  Image image3;

  @Before
  public void init() {
    this.redPixel = new Pixel(255, 0, 0, 255);
    this.greenPixel = new Pixel(0, 255, 0, 255);
    this.bluePixel = new Pixel(0, 0, 255, 255);
    this.whitePixel = new Pixel(255, 255, 255, 255);
    this.blackPixel = new Pixel(0, 0, 0, 255);
    this.mixedPixel = new Pixel(128, 128, 128, 255);
    this.transparentPixel = new Pixel(255, 255, 255, 0);
    this.opaquePixel = new Pixel(255, 255, 255, 128);
    this.amaranthPixel = new Pixel(159, 43, 104, 255);
    this.backgroundPixel = new Pixel(255,255,255,0);
    this.blankImage = new Image(5, 5);


    ArrayList<Pixel> row1 = new ArrayList<Pixel>();
    Collections.addAll(row1, redPixel, redPixel, redPixel);

    ArrayList<Pixel> row2 = new ArrayList<Pixel>();
    Collections.addAll(row2, redPixel, greenPixel, redPixel);

    ArrayList<Pixel> row3 = new ArrayList<Pixel>();
    Collections.addAll(row3, bluePixel, greenPixel, bluePixel);
    ArrayList<ArrayList<Pixel>> imageGrid = new ArrayList<>();
    Collections.addAll(imageGrid, row1, row2, row3 );

    this.image1 = new Image(imageGrid);
    this.image3 = new Image(2,2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionNullGrid() {
    ArrayList<ArrayList<Pixel>> imageGrid = null;
    this.image3 = new Image(imageGrid);

    this.image3 = new Image(-1,-1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionWidthTooSmall() {
    this.image3 = new Image(0,1);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionHeightTooSmall() {
    this.image3 = new Image(1,0);
  }

  @Test
  public void testValidConstruction() {
    ArrayList<ArrayList<Pixel>> imageGrid = new ArrayList<>();
    ArrayList<Pixel> row1 = new ArrayList<Pixel>();
    ArrayList<Pixel> row2 = new ArrayList<Pixel>();
    Collections.addAll(row1, bluePixel, greenPixel, bluePixel);
    Collections.addAll(row2, redPixel, greenPixel, bluePixel);
    Collections.addAll(imageGrid, row1, row2);
    this.image2 = new Image(imageGrid);

    assertEquals(this.image2.getPixels().get(0).get(1).getPixelColor(), this.greenPixel.getPixelColor());
    assertEquals(this.image2.getPixels().get(0).get(2).getPixelColor(), this.bluePixel.getPixelColor());
    assertEquals(this.image2.getPixels().get(1).get(0).getPixelColor(), this.redPixel.getPixelColor());
    assertEquals(this.image2.getPixels().get(1).get(1).getPixelColor(), this.greenPixel.getPixelColor());
    assertEquals(this.image2.getPixels().get(1).get(2).getPixelColor(), this.bluePixel.getPixelColor());

    assertEquals(this.image3.getPixels().get(0).get(0).getPixelColor(), this.backgroundPixel.getPixelColor());
    assertEquals(this.image3.getPixels().get(0).get(1).getPixelColor(), this.backgroundPixel.getPixelColor());
    assertEquals(this.image3.getPixels().get(1).get(0).getPixelColor(), this.backgroundPixel.getPixelColor());
    assertEquals(this.image3.getPixels().get(1).get(1).getPixelColor(), this.backgroundPixel.getPixelColor());
  }

  @Test
  public void testFilterImageRed(){
    ArrayList<Pixel> testRow1 = new ArrayList<Pixel>();
    Collections.addAll(testRow1, redPixel, redPixel, redPixel);

    ArrayList<Pixel> testRow2 = new ArrayList<Pixel>();
    Collections.addAll(testRow2, redPixel, blackPixel, redPixel);

    ArrayList<Pixel> testRow3 = new ArrayList<Pixel>();
    Collections.addAll(testRow3, blackPixel, blackPixel, blackPixel);

    ArrayList<ArrayList<Pixel>> testImageGrid = new ArrayList<>();
    Collections.addAll(testImageGrid, testRow1, testRow2, testRow3 );

    Image testImage = new Image(testImageGrid);
    this.image1.filterImageRed();
    for (int y = 0; y < 3; y++) {
      for(int x = 0; x<3; x++ )
      assertEquals(testImage.getPixels().get(y).get(x).getPixelColor(), this.image1.getPixels().get(y).get(x).getPixelColor());
    }
  }
  @Test
  public void testFilterImageGreen(){
    ArrayList<Pixel> testRow1 = new ArrayList<Pixel>();
    Collections.addAll(testRow1, blackPixel, blackPixel, blackPixel);

    ArrayList<Pixel> testRow2 = new ArrayList<Pixel>();
    Collections.addAll(testRow2, blackPixel, greenPixel, blackPixel);

    ArrayList<Pixel> testRow3 = new ArrayList<Pixel>();
    Collections.addAll(testRow3, blackPixel, greenPixel, blackPixel);

    ArrayList<ArrayList<Pixel>> testImageGrid = new ArrayList<>();
    Collections.addAll(testImageGrid, testRow1, testRow2, testRow3 );

    Image testImage = new Image(testImageGrid);
    this.image1.filterImageGreen();
    for (int y = 0; y < 3; y++) {
      for(int x = 0; x<3; x++ )
        assertEquals(testImage.getPixels().get(y).get(x).getPixelColor(), this.image1.getPixels().get(y).get(x).getPixelColor());
    }
  }
  @Test
  public void testFilterImageBlue(){
    ArrayList<Pixel> testRow1 = new ArrayList<Pixel>();
    Collections.addAll(testRow1, blackPixel, blackPixel, blackPixel);

    ArrayList<Pixel> testRow2 = new ArrayList<Pixel>();
    Collections.addAll(testRow2, blackPixel, blackPixel, blackPixel);

    ArrayList<Pixel> testRow3 = new ArrayList<Pixel>();
    Collections.addAll(testRow3, bluePixel, blackPixel, bluePixel);

    ArrayList<ArrayList<Pixel>> testImageGrid = new ArrayList<>();
    Collections.addAll(testImageGrid, testRow1, testRow2, testRow3 );

    Image testImage = new Image(testImageGrid);
    this.image1.filterImageBlue();
    for (int y = 0; y < 3; y++) {
      for(int x = 0; x<3; x++ )
        assertEquals(testImage.getPixels().get(y).get(x).getPixelColor(), this.image1.getPixels().get(y).get(x).getPixelColor());
    }
  }
  @Test
  public void testFilterDarkenValue(){
    ArrayList<Pixel> testRow1 = new ArrayList<Pixel>();
    Collections.addAll(testRow1, blackPixel, blackPixel, blackPixel);

    ArrayList<Pixel> testRow2 = new ArrayList<Pixel>();
    Collections.addAll(testRow2, blackPixel, blackPixel, blackPixel);

    ArrayList<Pixel> testRow3 = new ArrayList<Pixel>();
    Collections.addAll(testRow3, blackPixel, blackPixel, blackPixel);

    ArrayList<ArrayList<Pixel>> testImageGrid = new ArrayList<>();
    Collections.addAll(testImageGrid, testRow1, testRow2, testRow3 );

    Image testImage = new Image(testImageGrid);
    this.image1.darkenImage("darken-value");
    for (int y = 0; y < 3; y++) {
      for(int x = 0; x<3; x++ )
        assertEquals(testImage.getPixels().get(y).get(x).getPixelColor(), this.image1.getPixels().get(y).get(x).getPixelColor());
    }
  }

  @Test
  public void testFilterDarkenIntensity(){
    Pixel darkenRedIntensity = new Pixel(new Color(170,0,0));
    Pixel darkenGreenIntensity = new Pixel(new Color(0,170,0));
    Pixel darkenBlueIntensity = new Pixel(new Color(0,0,170));
    ArrayList<Pixel> testRow1 = new ArrayList<Pixel>();
    Collections.addAll(testRow1,darkenRedIntensity, darkenRedIntensity, darkenRedIntensity);

    ArrayList<Pixel> testRow2 = new ArrayList<Pixel>();
    Collections.addAll(testRow2, darkenRedIntensity, darkenGreenIntensity, darkenRedIntensity);

    ArrayList<Pixel> testRow3 = new ArrayList<Pixel>();
    Collections.addAll(testRow3, darkenBlueIntensity, darkenGreenIntensity, darkenBlueIntensity);

    ArrayList<ArrayList<Pixel>> testImageGrid = new ArrayList<>();
    Collections.addAll(testImageGrid, testRow1, testRow2, testRow3 );

    Image testImage = new Image(testImageGrid);
    this.image1.darkenImage("darken-intensity");
    for (int y = 0; y < 3; y++) {
      for(int x = 0; x<3; x++ )
        assertEquals(testImage.getPixels().get(y).get(x).getPixelColor(), this.image1.getPixels().get(y).get(x).getPixelColor());
    }
  }
  @Test
  public void testFilterDarkenLuma(){
    Pixel darkenRedLuma = new Pixel(new Color(201,0,0));
    Pixel darkenGreenLuma = new Pixel(new Color(0,73,0));
    Pixel darkenBlueLuma = new Pixel(new Color(0,0,237));
    ArrayList<Pixel> testRow1 = new ArrayList<Pixel>();
    Collections.addAll(testRow1,darkenRedLuma, darkenRedLuma, darkenRedLuma);

    ArrayList<Pixel> testRow2 = new ArrayList<Pixel>();
    Collections.addAll(testRow2, darkenRedLuma, darkenGreenLuma, darkenRedLuma);

    ArrayList<Pixel> testRow3 = new ArrayList<Pixel>();
    Collections.addAll(testRow3, darkenBlueLuma, darkenGreenLuma, darkenBlueLuma);

    ArrayList<ArrayList<Pixel>> testImageGrid = new ArrayList<>();
    Collections.addAll(testImageGrid, testRow1, testRow2, testRow3 );

    Image testImage = new Image(testImageGrid);
    this.image1.darkenImage("darken-luma");
    for (int y = 0; y < 3; y++) {
      for(int x = 0; x<3; x++ )
        assertEquals(testImage.getPixels().get(y).get(x).getPixelColor(), this.image1.getPixels().get(y).get(x).getPixelColor());
    }
  }

  @Test
  public void testFilterBrightenValue(){
    ArrayList<Pixel> testRow1 = new ArrayList<Pixel>();
    Collections.addAll(testRow1, whitePixel, whitePixel, whitePixel);

    ArrayList<Pixel> testRow2 = new ArrayList<Pixel>();
    Collections.addAll(testRow2, whitePixel, whitePixel, whitePixel);

    ArrayList<Pixel> testRow3 = new ArrayList<Pixel>();
    Collections.addAll(testRow3, whitePixel, whitePixel, whitePixel);

    ArrayList<ArrayList<Pixel>> testImageGrid = new ArrayList<>();
    Collections.addAll(testImageGrid, testRow1, testRow2, testRow3 );

    Image testImage = new Image(testImageGrid);
    this.image1.brightenImage("brighten-value");
    for (int y = 0; y < 3; y++) {
      for(int x = 0; x<3; x++ )
        assertEquals(testImage.getPixels().get(y).get(x).getPixelColor(), this.image1.getPixels().get(y).get(x).getPixelColor());
    }
  }

  @Test
  public void testFilterBrightenIntensity(){
    Pixel brightenRedIntensity = new Pixel(new Color(255,85,85));
    Pixel brightenGreenIntensity = new Pixel(new Color(85,255,85));
    Pixel brightenBlueIntensity = new Pixel(new Color(85,85,255));
    ArrayList<Pixel> testRow1 = new ArrayList<Pixel>();
    Collections.addAll(testRow1,brightenRedIntensity, brightenRedIntensity, brightenRedIntensity);

    ArrayList<Pixel> testRow2 = new ArrayList<Pixel>();
    Collections.addAll(testRow2, brightenRedIntensity, brightenGreenIntensity, brightenRedIntensity);

    ArrayList<Pixel> testRow3 = new ArrayList<Pixel>();
    Collections.addAll(testRow3, brightenBlueIntensity, brightenGreenIntensity, brightenBlueIntensity);

    ArrayList<ArrayList<Pixel>> testImageGrid = new ArrayList<>();
    Collections.addAll(testImageGrid, testRow1, testRow2, testRow3 );

    Image testImage = new Image(testImageGrid);
    this.image1.brightenImage("brighten-intensity");
    for (int y = 0; y < 3; y++) {
      for(int x = 0; x<3; x++ )
        assertEquals(testImage.getPixels().get(y).get(x).getPixelColor(), this.image1.getPixels().get(y).get(x).getPixelColor());
    }
  }
  @Test
  public void testFilterBrightenLuma(){
    Pixel brightenRedLuma = new Pixel(new Color(255,54,54));
    Pixel brightenGreenLuma = new Pixel(new Color(182,255,182));
    Pixel brightenBlueLuma = new Pixel(new Color(18,18,255));
    ArrayList<Pixel> testRow1 = new ArrayList<Pixel>();
    Collections.addAll(testRow1,brightenRedLuma, brightenRedLuma, brightenRedLuma);

    ArrayList<Pixel> testRow2 = new ArrayList<Pixel>();
    Collections.addAll(testRow2, brightenRedLuma, brightenGreenLuma, brightenRedLuma);

    ArrayList<Pixel> testRow3 = new ArrayList<Pixel>();
    Collections.addAll(testRow3, brightenBlueLuma, brightenGreenLuma, brightenBlueLuma);

    ArrayList<ArrayList<Pixel>> testImageGrid = new ArrayList<>();
    Collections.addAll(testImageGrid, testRow1, testRow2, testRow3 );

    Image testImage = new Image(testImageGrid);
    this.image1.brightenImage("brighten-luma");
    for (int y = 0; y < 3; y++) {
      for(int x = 0; x<3; x++ )
        assertEquals(testImage.getPixels().get(y).get(x).getPixelColor(), this.image1.getPixels().get(y).get(x).getPixelColor());
    }
  }
}

