import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Collections;

import collagefiles.model.Image;
import collagefiles.model.ImageInterface;
import collagefiles.model.Layer;
import collagefiles.model.LayerInterface;
import collagefiles.model.Pixel;
import collagefiles.model.PixelInterface;

import static org.junit.Assert.assertEquals;

/**
 * Testing layer class.
 */
public class TestLayer {

  PixelInterface redPixel;
  PixelInterface greenPixel;
  PixelInterface bluePixel;
  PixelInterface opaquePixel;
  PixelInterface invisiblePixel;
  PixelInterface opaqueBlue;
  PixelInterface opaqueRed;
  PixelInterface amaranthPixel;

  LayerInterface layer0;
  LayerInterface layer1;
  LayerInterface layer2;
  LayerInterface layer3;
  LayerInterface layer4;

  ImageInterface image1;
  ImageInterface image2;
  ImageInterface image3;
  ImageInterface image4;
  ImageInterface image5;
  ImageInterface image6;
  ImageInterface image7;
  ImageInterface image8;

  @Before
  public void init() {
    this.redPixel = new Pixel(255, 0, 0, 255);
    this.greenPixel = new Pixel(0, 255, 0, 255);
    this.bluePixel = new Pixel(0, 0, 255, 255);
    this.opaquePixel = new Pixel(255, 255, 255, 128);
    this.invisiblePixel = new Pixel(new Color(255, 255, 255, 0));
    this.opaqueBlue = new Pixel(0, 0, 200, 128);
    this.opaqueRed = new Pixel(200, 0, 0, 120);
    this.amaranthPixel = new Pixel(159, 43, 104, 255);


    this.layer0 = new Layer("l1", 5, 5, false);
    this.layer1 = new Layer("l1", 3, 3, false);
    this.layer2 = new Layer("l1", 3, 8, false);
    this.layer3 = new Layer("l1", 11, 2, false);
    this.layer4 = new Layer("l1", 1, 1, false);

    ArrayList<PixelInterface> row1 = new ArrayList<PixelInterface>();
    Collections.addAll(row1, redPixel, redPixel, redPixel);

    ArrayList<PixelInterface> row2 = new ArrayList<PixelInterface>();
    Collections.addAll(row2, redPixel, greenPixel, redPixel);

    ArrayList<PixelInterface> row3 = new ArrayList<PixelInterface>();
    Collections.addAll(row3, bluePixel, greenPixel, bluePixel);

    ArrayList<ArrayList<PixelInterface>> imageGrid = new ArrayList<>();
    Collections.addAll(imageGrid, row1, row2, row3);

    ArrayList<PixelInterface> row4 = new ArrayList<PixelInterface>();
    Collections.addAll(row4, opaquePixel, opaquePixel);
    ArrayList<PixelInterface> row5 = new ArrayList<PixelInterface>();

    //Collections.addAll(row1, opaquePixel, opaquePixel);

    Collections.addAll(row5, opaquePixel, opaqueBlue);

    ArrayList<ArrayList<PixelInterface>> imageGrid2 = new ArrayList<>();
    Collections.addAll(imageGrid2, row4, row5);

    ArrayList<ArrayList<PixelInterface>> singlePix1 = new ArrayList<ArrayList<PixelInterface>>();
    ArrayList<PixelInterface> singleRow1 = new ArrayList<PixelInterface>();
    singleRow1.add(opaqueRed);
    singlePix1.add(singleRow1);

    ArrayList<ArrayList<PixelInterface>> singlePix2 = new ArrayList<ArrayList<PixelInterface>>();
    ArrayList<PixelInterface> singleRow2 = new ArrayList<PixelInterface>();
    singleRow2.add(new Pixel(0, 255, 0, 255));
    singlePix2.add(singleRow2);

    ArrayList<ArrayList<PixelInterface>> singlePix3 = new ArrayList<ArrayList<PixelInterface>>();
    ArrayList<PixelInterface> singleRow3 = new ArrayList<PixelInterface>();
    singleRow3.add(new Pixel(159, 43, 104, 255));
    singlePix3.add(singleRow3);

    this.image1 = new Image(imageGrid);
    this.image2 = new Image(2, 2, false);
    this.image3 = new Image(3, 3, false);
    this.image4 = new Image(5, 5, false);
    this.image5 = new Image(imageGrid2);
    this.image6 = new Image(singlePix1);
    this.image7 = new Image(singlePix2);
    this.image8 = new Image(singlePix3);
  }

  @Test
  public void testValidConstruction() {
    for (int i = 0; i < 5; i++) {
      assertEquals(this.image4.getFilterPixels().get(i).get(i).getPixelColor(),
              this.layer0.getImages().get(0).getFilterPixels().get(i).get(i).getPixelColor());
    }
  }

  @Test
  public void testPlaceImage() {
    this.layer0.placeImage(1, 1, this.image1);

    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(0).get(0).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(0).get(1).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(0).get(2).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(0).get(3).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(0).get(4).getPixelColor());


    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(1).get(0).getPixelColor());
    assertEquals(new Color(255, 0, 0, 255),
            this.layer0.getImages().get(0).getFilterPixels().get(1).get(1).getPixelColor());
    assertEquals(new Color(255, 0, 0, 255),
            this.layer0.getImages().get(0).getFilterPixels().get(1).get(2).getPixelColor());
    assertEquals(new Color(255, 0, 0, 255),
            this.layer0.getImages().get(0).getFilterPixels().get(1).get(3).getPixelColor());

    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(2).get(0).getPixelColor());
    assertEquals(new Color(255, 0, 0, 255),
            this.layer0.getImages().get(0).getFilterPixels().get(2).get(1).getPixelColor());
    assertEquals(new Color(0, 255, 0, 255),
            this.layer0.getImages().get(0).getFilterPixels().get(2).get(2).getPixelColor());
    assertEquals(new Color(255, 0, 0, 255),
            this.layer0.getImages().get(0).getFilterPixels().get(2).get(3).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(2).get(4).getPixelColor());

    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(3).get(0).getPixelColor());
    assertEquals(new Color(0, 0, 255, 255),
            this.layer0.getImages().get(0).getFilterPixels().get(3).get(1).getPixelColor());
    assertEquals(new Color(0, 255, 0, 255),
            this.layer0.getImages().get(0).getFilterPixels().get(3).get(2).getPixelColor());
    assertEquals(new Color(0, 0, 255, 255),
            this.layer0.getImages().get(0).getFilterPixels().get(3).get(3).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(3).get(4).getPixelColor());

    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(4).get(0).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(4).get(1).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(4).get(2).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(4).get(3).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getFilterPixels().get(4).get(4).getPixelColor());

  }

  @Test
  public void testPlaceImageEndOfImageAlsoTransparentFactor() {
    this.layer0.placeImage(3, 3, this.image5);

    System.out.println(layer0.getImages().get(0).getFilterPixels().get(4).get(4)
            .getPixelColor().getAlpha());

    assertEquals(new Color(255, 255, 255, 128), this.layer0.getImages()
            .get(0).getFilterPixels().get(3).get(3).getPixelColor());
    assertEquals(new Pixel(new Color(0, 0, 200, 128)).getPixelColor(),
            this.layer0.getImages().get(0).getFilterPixels().get(4).get(4).getPixelColor());

  }

  @Test
  public void testPlaceImageEndOfImageAddingMultipleImages() {
    this.layer0.placeImage(3, 3, this.image5);

    this.layer0.placeImage(4, 4, this.image6);

    this.image5.getFilterPixels().get(1).get(1)
            .addPixels(this.image6.getFilterPixels().get(0).get(0));

    assertEquals(new Color(255, 255, 255, 128), this.layer0.getImages()
            .get(0).getFilterPixels().get(3).get(3).getPixelColor());
    assertEquals(new Color(128, 0, 72, 187), this.layer0.getImages()
            .get(0).getFilterPixels().get(4).get(4).getPixelColor());

  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlacingAnImageOffTheGridEntirely() {
    this.layer0.placeImage(5,
            4, this.image6);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testPlacingAnImageOffTheGridEntirely2() {
    this.layer0.placeImage(4, 5, this.image6);

  }

  @Test
  public void testPlacingAnImageThatStartsOnTheGridButThenGoesOff() {
    this.layer0.placeImage(3, 3, this.image1);

    assertEquals(new Color(255, 255, 255, 0), this.layer0.getImages()
            .get(0).getFilterPixels().get(2).get(4).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0), this.layer0.getImages()
            .get(0).getFilterPixels().get(3).get(2).getPixelColor());
    assertEquals(new Color(255, 0, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(3).get(3).getPixelColor());
    assertEquals(new Color(255, 0, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(3).get(4).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0), this.layer0.getImages()
            .get(0).getFilterPixels().get(4).get(2).getPixelColor());
    assertEquals(new Color(255, 0, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(4).get(3).getPixelColor());
    assertEquals(new Color(0, 255, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(4).get(4).getPixelColor());
  }

  @Test
  public void testApplyFilterRed() {
    this.layer0.placeImage(0, 0, this.image1);
    this.layer0.applyFilter("red-component", new Image(1, 1, false));

    assertEquals(new Color(255, 0, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(0).get(0).getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(2).get(1).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0), this.layer0.getImages()
            .get(0).getFilterPixels().get(4).get(4).getPixelColor());

    assertEquals(new Color(0, 0, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(2).get(1).getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(2).get(2).getPixelColor());


  }

  @Test
  public void testApplyFilterGreen() {
    this.layer0.placeImage(0, 0, this.image1);
    this.layer0.applyFilter("green-component", new Image(1, 1, false));

    assertEquals(new Color(0, 0, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(0).get(0).getPixelColor());
    assertEquals(new Color(0, 255, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(2).get(1).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0), this.layer0.getImages()
            .get(0).getFilterPixels().get(4).get(4).getPixelColor());

    assertEquals(new Color(0, 255, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(2).get(1).getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(2).get(2).getPixelColor());

  }

  @Test
  public void testApplyFilterBlue() {
    this.layer0.placeImage(0, 0, this.image1);
    this.layer0.applyFilter("blue-component", new Image(1, 1, false));

    assertEquals(new Color(0, 0, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(0).get(0).getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(2).get(1).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0), this.layer0.getImages()
            .get(0).getFilterPixels().get(4).get(4).getPixelColor());

    assertEquals(new Color(0, 0, 0, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(2).get(1).getPixelColor());
    assertEquals(new Color(0, 0, 255, 255), this.layer0.getImages()
            .get(0).getFilterPixels().get(2).get(2).getPixelColor());

  }

  @Test
  public void testApplyDifference() {
    this.layer1.placeImage(0, 0, this.image1);
    this.layer1.applyFilter("difference", this.image1);
    assertEquals(new Color(0, 0, 0, 255), this.layer1.getImages()
            .get(0).getFilterPixels().get(0).get(0).getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.layer1.getImages()
            .get(0).getFilterPixels().get(1).get(1).getPixelColor());
    assertEquals(new Color(0, 0, 0, 255), this.layer1.getImages()
            .get(0).getFilterPixels().get(2).get(2).getPixelColor());

    this.layer4.placeImage(0, 0, this.image7);
    this.layer4.applyFilter("difference", this.image8);
    assertEquals(new Color(159, 212, 104, 255), this.layer4.getImages()
            .get(0).getFilterPixels().get(0).get(0).getPixelColor());
  }

  @Test
  public void testGetLayerText() {
    this.init();
    this.layer0.placeImage(0, 0, this.image1);
    assertEquals(
            "255 0 0 255\n"
                    + "255 0 0 255\n"
                    + "255 0 0 255\n"
                    + "255 255 255 0\n"
                    + "255 255 255 0\n"
                    + "255 0 0 255\n"
                    + "0 255 0 255\n"
                    + "255 0 0 255\n"
                    + "255 255 255 0\n"
                    + "255 255 255 0\n"
                    + "0 0 255 255\n"
                    + "0 255 0 255\n"
                    + "0 0 255 255\n"
                    + "255 255 255 0\n"
                    + "255 255 255 0\n"
                    + "255 255 255 0\n"
                    + "255 255 255 0\n"
                    + "255 255 255 0\n"
                    + "255 255 255 0\n"
                    + "255 255 255 0\n"
                    + "255 255 255 0\n"
                    + "255 255 255 0\n"
                    + "255 255 255 0\n"
                    + "255 255 255 0\n"
                    + "255 255 255 0"
                    + "\n", this.layer0.getImageTxt());
  }

  @Test
  public void testGetImagePPM() {
    this.layer1.placeImage(0, 0, this.image1);
    assertEquals("255 0 0\n"
            + "255 0 0\n"
            + "255 0 0\n"
            + "255 0 0\n"
            + "0 255 0\n"
            + "255 0 0\n"
            + "0 0 255\n"
            + "0 255 0\n"
            + "0 0 255\n", this.layer1.getImagePPM());
  }

  @Test
  public void testGetName() {
    assertEquals("l1", this.layer1.getName());
  }

  @Test
  public void testGetFilter() {
    this.layer1.applyFilter("red-component", new Image(1, 1, false));
    assertEquals("red-component", this.layer1.getFilter());
  }
}
