import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

import collagefiles.model.BasicCollageProject;
import collagefiles.model.Image;
import collagefiles.model.Layer;
import collagefiles.model.Pixel;
import collagefiles.model.Project;

import static org.junit.Assert.assertEquals;

/**
 * Testing layer class.
 */
public class TestProject {

  Pixel redPixel1;
  Pixel redPixel2;
  Pixel redPixel3;
  Pixel redPixel4;
  Pixel redPixel5;
  Pixel redPixel6;
  Pixel redPixel7;
  Pixel redPixel8;
  Pixel redPixel9;
  Pixel redPixel10;
  Pixel greenPixel1;
  Pixel greenPixel2;
  Pixel greenPixel3;
  Pixel greenPixel4;
  Pixel bluePixel1;
  Pixel bluePixel2;
  Pixel bluePixel3;
  Pixel bluePixel4;
  Pixel opaquePixel;
  Pixel invisiblePixel;
  Pixel opaqueBlue;
  Pixel opaqueRed;

  Layer layer0;
  Layer layer1;
  Layer layer2;
  Layer layer3;
  Layer layer4;
  Layer layer5;
  Layer layer6;

  Image image1;
  Image image1copy;
  Image image2;
  Image image3;
  Image image4;
  Image image5;
  Image image6;
  Image image7;
  Image image8;
  Image image9;

  Project project1;


  @Before
  public void init() {
    this.project1 = new BasicCollageProject(5, 5, 255);
    this.redPixel1 = new Pixel(255, 0, 0, 255);
    this.redPixel2 = new Pixel(255, 0, 0, 255);
    this.redPixel3 = new Pixel(255, 0, 0, 255);
    this.redPixel4 = new Pixel(255, 0, 0, 255);
    this.redPixel5 = new Pixel(255, 0, 0, 255);
    this.redPixel6 = new Pixel(255, 0, 0, 255);
    this.redPixel7 = new Pixel(255, 0, 0, 255);
    this.redPixel8 = new Pixel(255, 0, 0, 255);
    this.redPixel9 = new Pixel(255, 0, 0, 255);
    this.redPixel10 = new Pixel(255, 0, 0, 255);
    this.greenPixel1 = new Pixel(0, 255, 0, 255);
    this.greenPixel2 = new Pixel(0, 255, 0, 255);
    this.greenPixel3 = new Pixel(0, 255, 0, 255);
    this.greenPixel4 = new Pixel(0, 255, 0, 255);
    this.bluePixel1 = new Pixel(0, 0, 255, 255);
    this.bluePixel2 = new Pixel(0, 0, 255, 255);
    this.bluePixel3 = new Pixel(0, 0, 255, 255);
    this.bluePixel4 = new Pixel(0, 0, 255, 255);
    this.opaquePixel = new Pixel(255, 255, 255, 128);
    this.invisiblePixel = new Pixel(new Color(255, 255, 255, 0));
    this.opaqueBlue = new Pixel(0, 0, 200, 128);
    this.opaqueRed = new Pixel(200, 0, 0, 120);

    this.layer0 = new Layer("l1", 5, 5);
    this.layer1 = new Layer("l1", 3, 3);
    this.layer2 = new Layer("l1", 3, 8);
    this.layer3 = new Layer("l1", 11, 2);
    this.layer4 = new Layer("l1", 1, 1);
    this.layer5 = new Layer("l1", 1, 1);
    this.layer6 = new Layer("l1", 1, 1);


    ArrayList<Pixel> row1 = new ArrayList<Pixel>();
    Collections.addAll(row1, redPixel1, redPixel2, redPixel3);

    ArrayList<Pixel> row2 = new ArrayList<Pixel>();
    Collections.addAll(row2, redPixel4, greenPixel1, redPixel5);

    ArrayList<Pixel> row3 = new ArrayList<Pixel>();
    Collections.addAll(row3, bluePixel1, greenPixel2, bluePixel2);

    ArrayList<ArrayList<Pixel>> imageGrid = new ArrayList<>();
    Collections.addAll(imageGrid, row1, row2, row3);

    ArrayList<Pixel> row4 = new ArrayList<Pixel>();
    Collections.addAll(row4, opaquePixel, opaquePixel);
    ArrayList<Pixel> row5 = new ArrayList<Pixel>();
    Collections.addAll(row5, opaquePixel, opaqueBlue);

    ArrayList<ArrayList<Pixel>> imageGrid2 = new ArrayList<>();
    Collections.addAll(imageGrid2, row4, row5);

    ArrayList<ArrayList<Pixel>> singlePix = new ArrayList<ArrayList<Pixel>>();
    ArrayList<Pixel> singleRow = new ArrayList<Pixel>();
    singleRow.add(opaqueRed);

    ArrayList<ArrayList<Pixel>> singlePix2 = new ArrayList<ArrayList<Pixel>>();
    ArrayList<Pixel> singleRow2 = new ArrayList<Pixel>();
    singleRow2.add(new Pixel(0, 255, 0, 255));
    singlePix2.add(singleRow2);

    ArrayList<ArrayList<Pixel>> singlePix3 = new ArrayList<ArrayList<Pixel>>();
    ArrayList<Pixel> singleRow3 = new ArrayList<Pixel>();
    singleRow3.add(new Pixel(159, 43, 104, 255));
    singlePix3.add(singleRow3);

    ArrayList<ArrayList<Pixel>> singlePix4 = new ArrayList<ArrayList<Pixel>>();
    ArrayList<Pixel> singleRow4 = new ArrayList<Pixel>();
    singleRow4.add(new Pixel(0, 0, 255, 255));
    singlePix4.add(singleRow4);

    ArrayList<Pixel> row6 = new ArrayList<Pixel>();
    Collections.addAll(row6, redPixel6, redPixel7, redPixel8);

    ArrayList<Pixel> row7 = new ArrayList<Pixel>();
    Collections.addAll(row7, redPixel9, greenPixel3, redPixel10);

    ArrayList<Pixel> row8 = new ArrayList<Pixel>();
    Collections.addAll(row8, bluePixel3, greenPixel4, bluePixel4);

    ArrayList<ArrayList<Pixel>> imageGrid3 = new ArrayList<>();
    Collections.addAll(imageGrid3, row6, row7, row8);

    singlePix.add(singleRow);

    this.image1 = new Image(imageGrid);
    this.image1copy = new Image(imageGrid3);
    this.image2 = new Image(2, 2);
    this.image3 = new Image(3, 3);
    this.image4 = new Image(5, 5);
    this.image5 = new Image(imageGrid2);
    this.image6 = new Image(singlePix);
    this.image7 = new Image(singlePix2);
    this.image8 = new Image(singlePix3);
    this.image9 = new Image(singlePix4);

  }
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidProjectWidth(){
    this.project1 = new BasicCollageProject(0,2,2);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidProjectHeight(){
    this.project1 = new BasicCollageProject(2,0,2);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidProjectMaxVal(){
    this.project1 = new BasicCollageProject(3,2,0);
  }

  @Test
  public void testAddLayer(){
    this.project1.addLayer("background");

    String layerString = "";

    for (Layer l: this.project1.getLayers()){
      layerString = layerString + l.toString();
    }

    assertEquals("background normal # of images placed on this layer: 0"+"\n", layerString);
  }

  @Test
  public void testAddLayerApplyAFilterToIt(){
    this.project1.addLayer("background");
    this.project1.setFilter("background","red-component");

    String layerString = "";

    for (Layer l: this.project1.getLayers()){
      layerString = layerString + l.toString();
    }

    assertEquals("background red-component # of images placed on this layer: 0" +"\n", layerString);
  }
  @Test
  public void testAddMultipleLayersAndApplyFilters(){
    this.project1.addLayer("background");
    this.project1.setFilter("background","red-component");
    this.project1.addLayer("layer2");
    this.project1.setFilter("layer2","blue-component");

    String layerString = "";

    for (Layer l: this.project1.getLayers()){
      layerString = layerString + l.toString();
    }

    assertEquals("background red-component # of images placed on this layer: 0"+"\n"
           + "layer2 blue-component # of images placed on this layer: 0"+"\n", layerString);
  }

  @Test
  public void testAddMultipleLayersAndApplyFiltersAndAddImages(){
    this.project1.addLayer("background");
    this.project1.setFilter("background","red-component");
    this.project1.addImageToLayer("background",this.image1,0,0 );
    this.project1.addImageToLayer("background",this.image1,0,0 );


    this.project1.addLayer("layer2");
    this.project1.setFilter("layer2","blue-component");
    this.project1.addImageToLayer("layer2",this.image1,0,0 );

    String layerString = "";

    for (Layer l: this.project1.getLayers()){
      layerString = layerString + l.toString();
    }

    assertEquals("background red-component # of images placed on this layer: 2"+"\n"
            + "layer2 blue-component # of images placed on this layer: 1"+"\n", layerString);
  }

  @Test
  public void testAddImageToLayer(){
    this.project1.addLayer("background");
    this.project1.addImageToLayer("background",this.image1, 0 ,0);

    this.layer0.placeImage(0,0,this.image1);

    for(int i = 0; i < layer0.getImages().get(0).getPixels().size();i++){
      for (int j = 0; j < layer0.getImages().get(0).getPixels().get(0).size(); j++){
        assertEquals(layer0.getImages().get(0).getPixels().get(i).get(j).getPixelColor(),
                project1.getLayers().get(0)
                        .getImages().get(0)
                        .getPixels().get(i)
                        .get(j).getPixelColor() );
      }

    }
  }

  @Test
  public void testAddMultipleImageToLayer(){
    this.project1.addLayer("background");
    this.project1.addImageToLayer("background",this.image1, 0 ,0);
    this.project1.addImageToLayer("background",this.image5, 0 ,0);


    this.layer0.placeImage(0,0,this.image1);
    this.layer0.placeImage(0,0,this.image5);
    assertEquals(new Color(255,128,128),project1.getLayers().get(0).getImages().get(0).getPixels()
            .get(0)
            .get(0)
            .getPixelColor() );

    assertEquals(new Color(0,127,100),project1.getLayers().get(0).getImages().get(0).getPixels()
            .get(1)
            .get(1)
            .getPixelColor() );

    assertEquals(new Color(255,255,255,0),project1.getLayers().get(0).getImages().get(0).getPixels()
            .get(4)
            .get(4)
            .getPixelColor() );

  }

  @Test
  public void testAddImagesToMultipleLayers(){
    this.project1.addLayer("background");
    this.project1.addLayer("layer2");
    this.project1.addImageToLayer("background",this.image1, 0 ,0);
    this.project1.addImageToLayer("layer2",this.image5, 2 ,2);


    //Layers have yet to merge so images from one layer dont affect another
    assertEquals(new Color(255,0,0),project1.getLayers().get(0).getImages().get(0).getPixels()
            .get(0)
            .get(0)
            .getPixelColor() );

    assertEquals(new Color(0,255,0),project1.getLayers().get(0).getImages().get(0).getPixels()
            .get(1)
            .get(1)
            .getPixelColor() );

    assertEquals(new Color(255,255,255,0),project1.getLayers().get(0).getImages().get(0).getPixels()
            .get(4)
            .get(4)
            .getPixelColor() );

    //Layers have yet to merge so images from one layer dont affect another
    assertEquals(new Color(255,255,255,0),project1.getLayers().get(1).getImages().get(0).getPixels()
            .get(0)
            .get(0)
            .getPixelColor() );

    assertEquals(new Color(255,255,255,128),project1.getLayers().get(1).getImages().get(0).getPixels()
            .get(2)
            .get(2)
            .getPixelColor());

    assertEquals(new Color(0,0,200,128),project1.getLayers().get(1).getImages().get(0).getPixels()
            .get(3)
            .get(3)
            .getPixelColor() );
    assertEquals(new Color(255,255,255,0),project1.getLayers().get(1).getImages().get(0).getPixels()
            .get(0)
            .get(4)
            .getPixelColor() );

  }

  @Test
  public void testSaveImage(){
    this.project1.addLayer("background");

    assertEquals("P3\n"
+            "#\n"
 +           "5 5\n"
 +           "255\n"
 +           "\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
  +          "255 255 255\n"
  +          "255 255 255\n"
   +         "255 255 255\n"
    +        "255 255 255\n"
     +       "255 255 255\n"
      +      "255 255 255\n"
       +     "255 255 255\n"
        +    "255 255 255\n"
         +   "255 255 255\n"
        +    "255 255 255\n"
         +   "255 255 255"
            +"\n",  this.project1.saveImage(""));
  }
  @Test
  public void testSaveProjectAddImage(){
    this.project1.addLayer("background");
    this.project1.addImageToLayer("background",this.image1, 0 ,0);
    this.project1.addImageToLayer("background",this.image5, 0 ,0);


    assertEquals("test2\n"
  +          "5 5\n"
  +          "255\n"
  +          "background normal\n"
  +          "255 128 128 255\n"
  +          "255 128 128 255\n"
  +          "255 0 0 255\n"
   +         "255 255 255 0\n"
    +        "255 255 255 0\n"
    +        "255 128 128 255\n"
    +       "0 127 100 255\n"
    +        "255 0 0 255\n"
    +        "255 255 255 0\n"
    +        "255 255 255 0\n"
    +        "0 0 255 255\n"
    +        "0 255 0 255\n"
    +        "0 0 255 255\n"
    +        "255 255 255 0\n"
    +        "255 255 255 0\n"
    +        "255 255 255 0\n"
    +        "255 255 255 0\n"
    +        "255 255 255 0\n"
    +       "255 255 255 0\n"
    +        "255 255 255 0\n"
    +        "255 255 255 0\n"
    +        "255 255 255 0\n"
    +        "255 255 255 0\n"
    +        "255 255 255 0\n"
    +        "255 255 255 0\n",this.project1.saveProject("test2"));
  }
  @Test
  public void testSaveImageAddFilter(){
    this.project1.addLayer("background");
    this.project1.addImageToLayer("background",this.image1, 0 ,0);
    this.project1.setFilter("background","red-component");

    assertEquals("P3\n"
 +           "#test3\n"
 +           "5 5\n"
 +          "255\n"
 +           "\n"
 +           "255 0 0\n"
 +           "255 0 0\n"
 +           "255 0 0\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "255 0 0\n"
 +           "0 0 0\n"
 +           "255 0 0\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "0 0 0\n"
 +           "0 0 0\n"
 +           "0 0 0\n"
 +           "255 255 255\n"
  +          "255 255 255\n"
 +           "255 255 255\n"
   +         "255 255 255\n"
    +        "255 255 255\n"
     +       "255 255 255\n"
      +      "255 255 255\n"
       +     "255 255 255\n"
        +    "255 255 255\n"
         +   "255 255 255\n"
          +  "255 255 255\n"
        +    "255 255 255" + "\n",this.project1.saveImage("test3"));
  }
  @Test
  public void testAddImagesAndFiltersOnMultipleLayers(){
    this.project1.addLayer("background");
    this.project1.addImageToLayer("background",this.image1, 0 ,0);
    this.project1.setFilter("background","red-component");

    this.project1.addLayer("layer2");
    this.project1.addImageToLayer("layer2",this.image1copy, 2 ,2);
    this.project1.setFilter("layer2","blue-component");

    assertEquals("P3\n"
 +           "#baba\n"
 +           "5 5\n"
 +           "255\n"
 +           "\n"
 +           "255 0 0\n"
 +           "255 0 0\n"
 +           "255 0 0\n"
 +           "255 255 255\n"
 +           "255 255 255\n"
 +           "255 0 0\n"
 +           "0 0 0\n"
 +          "255 0 0\n"
 +           "255 255 255\n"
 +          "255 255 255\n"
 +           "0 0 0\n"
  +          "0 0 0\n"
   +         "0 0 0\n"
    +        "0 0 0\n"
     +       "0 0 0\n"
      +      "255 255 255\n"
            + "255 255 255\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "0 0 0\n"
            + "255 255 255\n"
            + "255 255 255\n"
            + "0 0 255\n"
            + "0 0 0\n"
            + "0 0 255\n", this.project1.saveImage("baba"));
  }


  @Test
  public void testDifferenceFunction() {

    this.project1 = new BasicCollageProject(1, 1, 255);
    this.project1.addLayer("l1");
    this.project1.addLayer("l2");
    //this.project1.addLayer("l3");
    this.project1.addImageToLayer("name", this.image7, 0, 0);
    this.project1.addImageToLayer("l1", this.image8, 0, 0);
    this.project1.addImageToLayer("l2", this.image9, 0, 0);
    this.project1.setFilter("name", "difference");
    this.project1.setFilter("l1", "difference");
    this.project1.setFilter("l2", "difference");

    assertEquals(new Color(0,0,1,255),
            this.project1.getLayers().get(1).getImages().get(0).getPixels().get(0).get(0).getPixelColor());


//    this.layer1.placeImage(0, 0, this.image1);
//    this.layer1.applyFilter("difference", this.image1);
//    assertEquals(new Color(0, 0, 0, 255), this.layer1.getImages()
//            .get(0).getPixels().get(0).get(0).getPixelColor());
//    assertEquals(new Color(0, 0, 0, 255), this.layer1.getImages()
//            .get(0).getPixels().get(1).get(1).getPixelColor());
//    assertEquals(new Color(0, 0, 0, 255), this.layer1.getImages()
//            .get(0).getPixels().get(2).get(2).getPixelColor());
//
//    this.layer4.placeImage(0,0,this.image7);
//    this.layer4.applyFilter("difference", this.image8);
//    assertEquals(new Color(159, 104, 212, 255), this.layer4.getImages()
//            .get(0).getPixels().get(0).get(0).getPixelColor());
  }

}