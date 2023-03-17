import org.junit.Before;

import java.util.ArrayList;
import java.util.Collections;

import collagefiles.model.Image;
import collagefiles.model.Pixel;

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
    this.blankImage = new Image(5,5);



    ArrayList<Pixel> row1 = new ArrayList<Pixel>();
    Collections.addAll(row1, redPixel,redPixel,redPixel);

    ArrayList<Pixel> row2 = new ArrayList<Pixel>();
    Collections.addAll(row2, redPixel,greenPixel,redPixel);

    ArrayList<Pixel> row3 = new ArrayList<Pixel>();
    Collections.addAll(row3, bluePixel,greenPixel,bluePixel);
    ArrayList<ArrayList<Pixel>> imageGrid = new ArrayList<>()
    Collections.addAll(imageGrid,row1,)

    this.image1 = new Image();

  }
}
