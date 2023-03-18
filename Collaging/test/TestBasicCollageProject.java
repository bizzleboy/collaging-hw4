import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

import collagefiles.model.BasicCollageProject;
import collagefiles.model.Image;
import collagefiles.model.Layer;
import collagefiles.model.Pixel;

import static org.junit.Assert.assertEquals;

public class TestBasicCollageProject {
  BasicCollageProject project0;
  Layer layer0;
  Layer layer1;
  Pixel opaqueRed;
  Pixel opaqueBlue;
  Image image1;
  Image image2;

  @Before
  public void init() {
    this.project0 = new BasicCollageProject(5,5,255);
    this.layer0 = new Layer("l0", 5, 5);
    this.layer1 = new Layer("l1", 5, 5);

    this.opaqueRed = new Pixel(255,0,0,128);
    ArrayList<ArrayList<Pixel>> singlePix1 = new ArrayList<ArrayList<Pixel>>();
    ArrayList<Pixel> singleRow1 = new ArrayList<Pixel>();
    singleRow1.add(opaqueRed);
    singlePix1.add(singleRow1);
    this.image1 = new Image(singlePix1);

    this.opaqueBlue = new Pixel(0,0,255,128);
    ArrayList<ArrayList<Pixel>> singlePix2 = new ArrayList<ArrayList<Pixel>>();
    ArrayList<Pixel> singleRow2 = new ArrayList<Pixel>();
    singleRow2.add(opaqueBlue);
    singlePix2.add(singleRow2);
    this.image2 = new Image(singlePix2);
  }

  @Test
  public void testImageAdding() {
    this.project0.addLayer("l0");
    this.project0.addLayer("l1");
    this.project0.addImageToLayer("l0",this.image1,0,0);
    this.project0.addImageToLayer("l1",this.image2,0,0);
    assertEquals(new Color(254,0,0,128),
           this.project0.getLayers().get(1).getImages().get(0).getPixels().get(0).get(0).getPixelColor());

  }

}
