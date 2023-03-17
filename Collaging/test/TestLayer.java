import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import collagefiles.model.Image;
import collagefiles.model.Layer;
import collagefiles.model.Pixel;

import static org.junit.Assert.assertEquals;


public class TestLayer {

  Pixel redPixel;
  Pixel greenPixel;
  Pixel bluePixel;
  Pixel opaquePixel;
  Pixel invisiblePixel;

  Layer layer0;
  Layer layer1;
  Layer layer2;
  Layer layer3;

  Image image1;
  Image image2;
  Image image3;
  Image image4;

  @Before
  public void init() {
    this.redPixel = new Pixel(255, 0, 0, 255);
    this.greenPixel = new Pixel(0, 255, 0, 255);
    this.bluePixel = new Pixel(0, 0, 255, 255);
    this.opaquePixel = new Pixel(255, 255, 255, 128);
    this.invisiblePixel = new Pixel(new Color(255,255,255,0));


    this.layer0 = new Layer("l1",5,5);
    this.layer1 = new Layer("l1",3,3);
    this.layer2 = new Layer("l1",3,8);
    this.layer3 = new Layer("l1",11,2);

    ArrayList<Pixel> row1 = new ArrayList<Pixel>();
    Collections.addAll(row1, redPixel, redPixel, opaquePixel);
    ArrayList<Pixel> row2 = new ArrayList<Pixel>();
    Collections.addAll(row2, redPixel, greenPixel, redPixel);
    ArrayList<Pixel> row3 = new ArrayList<Pixel>();
    Collections.addAll(row3, bluePixel, greenPixel, bluePixel);
    ArrayList<ArrayList<Pixel>> imageGrid = new ArrayList<>();
    Collections.addAll(imageGrid, row1, row2, row3 );

    this.image1 = new Image(imageGrid);
    this.image2 = new Image(2,2);
    this.image3 = new Image(3,3);
    this.image4 = new Image(5,5);


  }

  @Test
  public void testValidConstruction() {
    for (int i=0;i<5;i++) {
      assertEquals(this.image4.getPixels().get(i).get(i).getPixelColor(),
              this.layer1.getImages().get(0).getPixels().get(i).get(i).getPixelColor());
    }
  }

  @Test
  public void testPlaceImage() {
    this.layer0.placeImage(1,1,this.image1);

    // this.layer2.placeImage(0,5,this.image1);
    //this.layer3.placeImage(1,0,this.image1);

    assertEquals(new Color(255,255,255,0),
            this.layer0.getImages().get(0).getPixels().get(0).get(0).getPixelColor());
    assertEquals(new Color(255,255,255,0),
            this.layer0.getImages().get(0).getPixels().get(0).get(1).getPixelColor());
    assertEquals(new Color(255,255,255,0),
            this.layer0.getImages().get(0).getPixels().get(1).get(0).getPixelColor());
    assertEquals(new Color(255,127,127,127),
            this.layer0.getImages().get(0).getPixels().get(1).get(1).getPixelColor());
    assertEquals(new Color(127,127,255,127),
            this.layer0.getImages().get(0).getPixels().get(2).get(1).getPixelColor());
  }

  @Test
  public void testPlaceImage2() {

    ArrayList<Pixel> testRow1 = new ArrayList<Pixel>();
    Collections.addAll(testRow1, this.invisiblePixel, this.invisiblePixel, this.invisiblePixel, this.invisiblePixel, this.invisiblePixel);
    ArrayList<Pixel> testRow2 = new ArrayList<Pixel>();
    Collections.addAll(testRow2, this.invisiblePixel, this.invisiblePixel, this.redPixel, this.redPixel, this.redPixel);
    ArrayList<Pixel> testRow3 = new ArrayList<Pixel>();
    Collections.addAll(testRow3, this.invisiblePixel, this.invisiblePixel, this.greenPixel, this.redPixel, this.greenPixel);

    ArrayList<Pixel> testRow4 = new ArrayList<Pixel>();
    Collections.addAll(testRow4, this.invisiblePixel, this.invisiblePixel, this.bluePixel, this.bluePixel, this.bluePixel);

    ArrayList<Pixel> testRow5 = new ArrayList<Pixel>();
    Collections.addAll(testRow1, this.invisiblePixel, this.invisiblePixel, this.invisiblePixel, this.invisiblePixel, this.invisiblePixel);

    ArrayList<ArrayList<Pixel>> imageGrid = new ArrayList<>();
    Collections.addAll(imageGrid, testRow1, testRow2, testRow3, testRow4, testRow5);

    Image testImage = new Image(imageGrid);

    assertEquals(layer1.getImages().get(0).getPixels().get(0).get(0).getPixelColor(),testImage.getPixels().get(0).get(0).getPixelColor());
    this.layer0.placeImage(2, 1, this.image1);

    for (int y = 0; y < 5; y++) {
      for (int x = 0; x < 5; x++) {
        assertEquals(testImage.getPixels().get(y).get(x).getPixelColor(),
                this.layer0.getImages().get(0).getPixels().get(y).get(x).getPixelColor());
      }
    }
  }
}
