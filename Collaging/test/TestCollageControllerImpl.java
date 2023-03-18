import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import collagefiles.Controller.CollageController;
import collagefiles.Controller.CollageControllerImpl;
import collagefiles.model.Image;
import collagefiles.model.Project;

import static org.junit.Assert.assertEquals;

public class TestCollageControllerImpl {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionReadable() {
    CollageController impl = new CollageControllerImpl(null);
  }

  @Test
  public void testQuitBeforeFunctions() {
    Readable in = new StringReader("q");
    Appendable log = new StringBuilder();
    Project p = new BasicCollageProjectConfirmInputsMock(log);
    CollageController controller = new CollageControllerImpl(in,p);
    controller.runProgram();
    assertEquals("", log.toString());
  }

  @Test
  public void testAddLayer() {
    Readable in = new StringReader("new-project 60 60 add-layer layer-2 q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmMethodsMock p = new BasicCollageProjectConfirmMethodsMock(log);
    CollageController controller = new CollageControllerImpl(in, p);
    controller.runProgram();
    assertEquals("addLayer ", log.toString());
  }

  @Test
  public void testAddLayerInputs() {
    Readable in = new StringReader("new-project 60 60 add-layer layer-2 q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmInputsMock p = new BasicCollageProjectConfirmInputsMock(log);
    CollageController controller = new CollageControllerImpl(in, p);
    controller.runProgram();
    assertEquals("layerName=layer-2\n", log.toString());
  }

  @Test
  public void testAddImageToLayer() {
    Readable in = new StringReader("new-project 60 60 add-image name " +
            "/Users/dylaningham/Downloads/CS3500/collaging-hw4/Collaging/src/newSprite.ppm  " +
            "10 10 q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmMethodsMock p = new BasicCollageProjectConfirmMethodsMock(log);
    CollageController controller = new CollageControllerImpl(in, p);
    controller.runProgram();
    assertEquals("addImageToLayer ", log.toString());
  }

  @Test
  public void testAddImageToLayerInputs() {
    Readable in = new StringReader("new-project 2 2 add-image name " +
            "/Users/dylaningham/Downloads/CS3500/collaging-hw4/Collaging/src/newSprite.ppm  " +
            "10 10 q");    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmInputsMock p = new BasicCollageProjectConfirmInputsMock(log);
    CollageController controller = new CollageControllerImpl(in, p);
    controller.runProgram();
    assertEquals("layerName=name imageLength=100 xPos=10 yPos=10\n", log.toString());
  }

  @Test
  public void testSetFilter() {
    Readable in = new StringReader("new-project 60 60 set-filter layer-2 red-component q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmMethodsMock p = new BasicCollageProjectConfirmMethodsMock(log);
    CollageController controller = new CollageControllerImpl(in, p);
    controller.runProgram();
    assertEquals("setFilter ", log.toString());
  }

  @Test
  public void testSetFilterInputs() {
    Readable in = new StringReader("new-project 60 60 set-filter layer-2 red-component q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmInputsMock p = new BasicCollageProjectConfirmInputsMock(log);
    CollageController controller = new CollageControllerImpl(in, p);
    controller.runProgram();
    assertEquals("layerName=layer-2 filterType=red-component\n", log.toString());
  }

  @Test
  public void testSaveImage() {
    Readable in = new StringReader("new-project 60 60 save-image Collaging/src/image1.ppm q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmMethodsMock p = new BasicCollageProjectConfirmMethodsMock(log);
    CollageController controller = new CollageControllerImpl(in, p);
    controller.runProgram();
    assertEquals("saveImage ", log.toString());
  }

  @Test
  public void testSaveImageInputs() {
    Readable in = new StringReader("new-project 60 60 save-image Collaging/src/image1.ppm q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmInputsMock p = new BasicCollageProjectConfirmInputsMock(log);
    CollageController controller = new CollageControllerImpl(in, p);
    controller.runProgram();
    assertEquals("imagePath=Collaging/src/image1.ppm\n", log.toString());
  }

  @Test
  public void testSaveProject() {
    Readable in = new StringReader("new-project 60 60 save-project Collaging/src/project1 q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmMethodsMock p = new BasicCollageProjectConfirmMethodsMock(log);
    CollageController controller = new CollageControllerImpl(in, p);
    controller.runProgram();
    assertEquals("saveProject ", log.toString());
  }

  @Test
  public void testSaveProjectInputs() {
    Readable in = new StringReader("new-project 60 60 save-project Collaging/src/project1.txt q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmInputsMock p = new BasicCollageProjectConfirmInputsMock(log);
    CollageController controller = new CollageControllerImpl(in, p);
    controller.runProgram();
    assertEquals("ProjectPath=Collaging/src/project1.txt\n", log.toString());
  }

}



class BasicCollageProjectConfirmInputsMock implements Project {

  protected final Appendable log;

  public BasicCollageProjectConfirmInputsMock(Appendable log) {
    this.log = log;
  }

  @Override
  public String saveProject(String ProjectPath) {
    try {
      this.log.append("ProjectPath=" + ProjectPath + "\n");
    } catch (IOException ignore) {
    }
    return null;
  }

  @Override
  public void addLayer(String layerName) {
    try {
      this.log.append("layerName=" + layerName + "\n");
    } catch (IOException ignore) {
    }
  }

  @Override
  public void addImageToLayer(String layerName, Image image, int xPos, int yPos) {
    try {
      this.log.append("layerName=" + layerName +
              " imageLength=" + image.getPixels().size() + " xPos=" + xPos + " yPos=" + yPos + "\n");
    } catch (IOException ignore) {
    }
  }

  @Override
  public void setFilter(String layerName, String filterType) {
    try {
      this.log.append("layerName=" + layerName +
              " filterType=" + filterType + "\n");
    } catch (IOException ignore) {
    }
  }

  @Override
  public String saveImage(String imagePath) {
    try {
      this.log.append("imagePath=" + imagePath + "\n");
    } catch (IOException ignore) {
    }
    return null;
  }

}

class BasicCollageProjectConfirmMethodsMock implements Project {

  protected final Appendable log;

  public BasicCollageProjectConfirmMethodsMock(Appendable log) {

    this.log = log;
  }

  @Override
  public String saveProject(String ProjectPath) {
    try {
      this.log.append("saveProject ");
    } catch (IOException ignore) {
      //do nothing
    }
    return null;
  }

  @Override
  public void addLayer(String layerName) {
    try {
      this.log.append("addLayer ");
    } catch (IOException ignore) {
      //do nothing
    }
  }

  @Override
  public void addImageToLayer(String layerName, Image image, int xPos, int yPos) {
    try {
      this.log.append("addImageToLayer ");
    } catch (IOException ignore) {
      //do nothing
    }
  }

  @Override
  public void setFilter(String layerName, String filterType) {
    try {
      this.log.append("setFilter ");
    } catch (IOException ignore) {
      //do nothing
    }
  }

  @Override
  public String saveImage(String imagePath) {
    try {
      this.log.append("saveImage ");
    } catch (IOException ignore) {
      //do nothing
    }
    return null;
  }
}