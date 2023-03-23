import org.junit.Before;
import org.junit.Test;

import java.awt.Color;
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
  Project project1;


  @Before
  public void init() {
    this.project1 = new BasicCollageProject(5,5,255);
    this.redPixel = new Pixel(255, 0, 0, 255);
    this.greenPixel = new Pixel(0, 255, 0, 255);
    this.bluePixel = new Pixel(0, 0, 255, 255);
    this.opaquePixel = new Pixel(255, 255, 255, 128);
    this.invisiblePixel = new Pixel(new Color(255, 255, 255, 0));
    this.opaqueBlue = new Pixel(0, 0, 200, 128);
    this.opaqueRed = new Pixel(200, 0, 0, 120);


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
    this.project1.addImageToLayer("layer2",this.image1, 2 ,2);
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
       +     "255 255 255\n"
        +    "0 0 0\n"
         +   "0 0 0\n"
          +  "0 0 0\n"
           + "255 255 255\n"
            +"255 255 255\n"
           + "0 0 255\n"
          +  "0 0 0\n"
           + "0 0 255\n + \n",this.project1.saveImage("baba"));
  }

}