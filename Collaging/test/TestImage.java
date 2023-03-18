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

  Image image1;
  Image blankImage;


  Image image2;

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
//TEST FOR INVALID CONSTRUCTION

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
  public void testFilterNormal(){
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
}

