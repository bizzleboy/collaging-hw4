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
  Pixel opaqueBlue;
  Pixel opaqueRed;

  Layer layer0;
  Layer layer1;
  Layer layer2;
  Layer layer3;

  Image image1;
  Image image2;
  Image image3;
  Image image4;
  Image image5;
  Image image6;


  @Before
  public void init() {
    this.redPixel = new Pixel(255, 0, 0, 255);
    this.greenPixel = new Pixel(0, 255, 0, 255);
    this.bluePixel = new Pixel(0, 0, 255, 255);
    this.opaquePixel = new Pixel(255, 255, 255, 128);
    this.invisiblePixel = new Pixel(new Color(255, 255, 255, 0));
    this.opaqueBlue = new Pixel(0,0,200,128);
    this.opaqueRed = new Pixel(200,0,0,120);




    this.layer0 = new Layer("l1", 5, 5);
    this.layer1 = new Layer("l1", 3, 3);
    this.layer2 = new Layer("l1", 3, 8);
    this.layer3 = new Layer("l1", 11, 2);

    ArrayList<Pixel> row1 = new ArrayList<Pixel>();
    Collections.addAll(row1, redPixel, redPixel, redPixel);

    ArrayList<Pixel> row2 = new ArrayList<Pixel>();
    Collections.addAll(row2, redPixel, greenPixel, redPixel);

    ArrayList<Pixel> row3 = new ArrayList<Pixel>();
    Collections.addAll(row3, bluePixel, greenPixel, bluePixel);

    ArrayList<ArrayList<Pixel>> imageGrid = new ArrayList<>();
    Collections.addAll(imageGrid, row1, row2, row3);

    ArrayList<Pixel> row4 = new ArrayList<Pixel>();
    Collections.addAll(row4, opaquePixel, opaquePixel);
    ArrayList<Pixel> row5 = new ArrayList<Pixel>();

    Collections.addAll(row1, opaquePixel, opaquePixel);

    Collections.addAll(row5, opaquePixel, opaqueBlue);

    ArrayList<ArrayList<Pixel>> imageGrid2 = new ArrayList<>();
    Collections.addAll(imageGrid2, row4, row5);

    ArrayList<ArrayList<Pixel>> singlePix = new ArrayList<ArrayList<Pixel>>();
    ArrayList<Pixel> singleRow = new ArrayList<Pixel>();
    singleRow.add(opaqueRed);

    singlePix.add(singleRow);

    this.image1 = new Image(imageGrid);
    this.image2 = new Image(2, 2);
    this.image3 = new Image(3, 3);
    this.image4 = new Image(5, 5);
    this.image5 = new Image(imageGrid2);
    this.image6 = new Image(singlePix);
  }

  @Test
  public void testValidConstruction() {
    for (int i = 0; i < 5; i++) {
      assertEquals(this.image4.getPixels().get(i).get(i).getPixelColor(),
              this.layer0.getImages().get(0).getPixels().get(i).get(i).getPixelColor());
    }
  }

  @Test
  public void testPlaceImage() {
    this.layer0.placeImage(1, 1, this.image1);

    // this.layer2.placeImage(0,5,this.image1);
    //this.layer3.placeImage(1,0,this.image1);

    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(0).get(0).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(0).get(1).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(0).get(2).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(0).get(3).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(0).get(4).getPixelColor());


    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(1).get(0).getPixelColor());
    assertEquals(new Color(255, 0, 0, 255),
            this.layer0.getImages().get(0).getPixels().get(1).get(1).getPixelColor());
    assertEquals(new Color(255, 0, 0, 255),
            this.layer0.getImages().get(0).getPixels().get(1).get(2).getPixelColor());
    assertEquals(new Color(255, 0, 0, 255),
            this.layer0.getImages().get(0).getPixels().get(1).get(3).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(1).get(4).getPixelColor());


    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(2).get(0).getPixelColor());
    assertEquals(new Color(255, 0, 0, 255),
            this.layer0.getImages().get(0).getPixels().get(2).get(1).getPixelColor());
    assertEquals(new Color(0, 255, 0, 255),
            this.layer0.getImages().get(0).getPixels().get(2).get(2).getPixelColor());
    assertEquals(new Color(255, 0, 0, 255),
            this.layer0.getImages().get(0).getPixels().get(2).get(3).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(2).get(4).getPixelColor());

    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(3).get(0).getPixelColor());
    assertEquals(new Color(0, 0, 255, 255),
            this.layer0.getImages().get(0).getPixels().get(3).get(1).getPixelColor());
    assertEquals(new Color(0, 255, 0, 255),
            this.layer0.getImages().get(0).getPixels().get(3).get(2).getPixelColor());
    assertEquals(new Color(0, 0, 255, 255),
            this.layer0.getImages().get(0).getPixels().get(3).get(3).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(3).get(4).getPixelColor());

    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(4).get(0).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(4).get(1).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(4).get(2).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(4).get(3).getPixelColor());
    assertEquals(new Color(255, 255, 255, 0),
            this.layer0.getImages().get(0).getPixels().get(4).get(4).getPixelColor());

//            this.layer0.getImages().get(0).getPixels().get(1).get(0).getPixelColor());
//    assertEquals(new Color(255,127,127,127),
//            this.layer0.getImages().get(0).getPixels().get(1).get(1).getPixelColor());
//    assertEquals(new Color(127,127,255,127),
//            this.layer0.getImages().get(0).getPixels().get(2).get(1).getPixelColor());
  }

  @Test
  public void testPlaceImageEndOfImageAlsoTransparentFactor() {
    this.layer0.placeImage(3,3,this.image5);

    System.out.println(layer0.getImages().get(0).getPixels().get(4).get(4).getPixelColor().getAlpha());

    assertEquals(new Color(254,254,254,128), this.layer0.getImages().get(0).getPixels().get(3).get(3).getPixelColor());
    assertEquals(new Pixel(new Color(0,0,200,128)).getPixelColor(), this.layer0.getImages().get(0).getPixels().get(4).get(4).getPixelColor());

  }
  @Test
  public void testPlaceImageEndOfImageAddingMultipleImages() {
    this.layer0.placeImage(3,3,this.image5);

    this.layer0.placeImage(4,4,this.image6);

    this.image5.getPixels().get(1).get(1).addPixels(this.image6.getPixels().get(0).get(0));
    System.out.println(this.image5.getPixels().get(1).get(1).getPixelColor());

    System.out.println(layer0.getImages().get(0).getPixels().get(4).get(4).getPixelColor().getAlpha());

   assertEquals(new Color(254,254,254,128), this.layer0.getImages().get(0).getPixels().get(3).get(3).getPixelColor());
    assertEquals(new Pixel(new Color(127,0,72,187)).getPixelColor(), this.layer0.getImages().get(0).getPixels().get(4).get(4).getPixelColor());

  }
  @Test(expected = IllegalArgumentException.class)
  public void testPlacingAnImageOffTheGridEntirely() {
    this.layer0.placeImage(5,4,this.image6);



  }
  @Test(expected = IllegalArgumentException.class)
  public void testPlacingAnImageOffTheGridEntirely2() {
    this.layer0.placeImage(4,5,this.image6);

  }
  @Test
  public void testPlacingAnImageThatStartsOnTheGridButThenGoesOff() {
    this.layer0.placeImage(3,3,this.image1);

    assertEquals(new Color(255,255,255,0), this.layer0.getImages().get(0).getPixels().get(2).get(4).getPixelColor());
    assertEquals(new Color(255,255,255,0), this.layer0.getImages().get(0).getPixels().get(3).get(2).getPixelColor());
    assertEquals(new Color(255,0,0,255), this.layer0.getImages().get(0).getPixels().get(3).get(3).getPixelColor());
    assertEquals(new Color(255,0,0,255), this.layer0.getImages().get(0).getPixels().get(3).get(4).getPixelColor());
    assertEquals(new Color(255,255,255,0), this.layer0.getImages().get(0).getPixels().get(4).get(2).getPixelColor());
    assertEquals(new Color(255,0,0,255), this.layer0.getImages().get(0).getPixels().get(4).get(3).getPixelColor());
    assertEquals(new Color(0,255,0,255), this.layer0.getImages().get(0).getPixels().get(4).get(4).getPixelColor());
  }

  @Test
  public void testApplyFilterRed(){
    this.layer1.placeImage(0,0,image1);
    this.layer1.applyFilter("red-component");

    assertEquals(new Color(255,255,255,0), this.layer0.getImages().get(0).getPixels().get(2).get(4).getPixelColor());
    assertEquals(new Color(255,255,255,0), this.layer0.getImages().get(0).getPixels().get(3).get(2).getPixelColor());


  }


}
