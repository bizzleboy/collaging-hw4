import org.junit.Before;
import org.junit.Test;

import java.awt.*;

import model.Pixel;

import static org.junit.Assert.assertEquals;

public class TestPixel {

  Pixel redPixel;
  Pixel greenPixel;
  Pixel bluePixel;
  Pixel blackPixel;
  Pixel whitePixel;
  Pixel mixedPixel;
  Pixel transparentPixel;
  Pixel opaquePixel;

  @Before
  public void init() {
    this.redPixel = new Pixel(255,0,0,255);
    this.greenPixel = new Pixel(0,255,0,255);
    this.bluePixel = new Pixel(0,0,255,255);
    this.blackPixel = new Pixel(255,255,255,255);
    this.whitePixel = new Pixel(0,0,0,255);
    this.mixedPixel = new Pixel(128,128,128,255);
    this.transparentPixel = new Pixel(255,255,255,0);
    this.opaquePixel = new Pixel(255,255,255,128);
  }


  @Test
  public void testValidConstruction() {
    assertEquals(new Color(255,0,0,255), this.redPixel.getPixelColor());
    assertEquals(new Color(0,255,0,255), this.greenPixel.getPixelColor());
    assertEquals(new Color(0,0,255,255), this.bluePixel.getPixelColor());
    assertEquals(new Color(255,255,255,255), this.blackPixel.getPixelColor());
    assertEquals(new Color(0,0,0,255), this.whitePixel.getPixelColor());
    assertEquals(new Color(128,128,128,255), this.mixedPixel.getPixelColor());
    assertEquals(new Color(0,0,0), this.transparentPixel.getPixelColor());
    assertEquals(new Color(128,128,128), this.opaquePixel.getPixelColor());
  }

}
