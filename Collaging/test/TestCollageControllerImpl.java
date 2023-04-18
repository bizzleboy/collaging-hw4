import org.junit.Test;

import java.io.StringReader;

import collagefiles.controller.CollageController;
import collagefiles.controller.CollageControllerImpl;
import collagefiles.model.Project;
import collagefiles.view.CollageTextView;

import static org.junit.Assert.assertEquals;

/**
 * Does controller work.
 */
public class TestCollageControllerImpl {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionReadable() {
    CollageController impl = new CollageControllerImpl(null, new CollageTextView());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidConstructionView() {
    CollageController impl = new CollageControllerImpl(new StringReader("q"), null);
  }

  @Test
  public void testQuitBeforeFunctions() {
    Readable in = new StringReader("q");
    Appendable log = new StringBuilder();
    Project p = new BasicCollageProjectConfirmInputsMock(log);
    CollageController controller = new CollageControllerImpl(in, p, new CollageTextView());
    controller.runProgram();
    assertEquals("", log.toString());
  }

  @Test
  public void testAddLayer() {
    Readable in = new StringReader("new-project 60 60 add-layer layer-2 q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmMethodsMock p = new BasicCollageProjectConfirmMethodsMock(log);
    CollageController controller = new CollageControllerImpl(in, p, new CollageTextView());
    controller.runProgram();
    assertEquals("addLayer ", log.toString());
  }

  @Test
  public void testAddLayerInputs() {
    Readable in = new StringReader("new-project 60 60 add-layer layer-2 q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmInputsMock p = new BasicCollageProjectConfirmInputsMock(log);
    CollageController controller = new CollageControllerImpl(in, p, new CollageTextView());
    controller.runProgram();
    assertEquals("layerName=layer-2\n", log.toString());
  }

  @Test
  public void testAddImageToLayer() {
    Readable in = new StringReader("new-project 60 60 add-layer l1 add-image l1 "
            + "Collaging/src/res/newSprite.ppm "
            + "10 10 q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmMethodsMock p = new BasicCollageProjectConfirmMethodsMock(log);
    CollageController controller = new CollageControllerImpl(in, p, new CollageTextView());
    controller.runProgram();
    assertEquals("addLayer addImageToLayer ", log.toString());
  }

  @Test
  public void testAddImageToLayerInputs() {
    Readable in = new StringReader("new-project 2 2 add-layer l1 add-image l1 "
            + "Collaging/src/res/newSprite.ppm "
            + "10 10 q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmInputsMock p = new BasicCollageProjectConfirmInputsMock(log);
    CollageController controller = new CollageControllerImpl(in, p, new CollageTextView());
    controller.runProgram();
    assertEquals("layerName=l1\nlayerName=l1 imageLength=100 xPos=10 yPos=10\n", log.toString());
  }

  @Test
  public void testSetFilter() {
    Readable in = new StringReader("new-project 60 60 set-filter layer-2 red-component q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmMethodsMock p = new BasicCollageProjectConfirmMethodsMock(log);
    CollageController controller = new CollageControllerImpl(in, p, new CollageTextView());
    controller.runProgram();
    assertEquals("setFilter ", log.toString());
  }

  @Test
  public void testSetFilterInputs() {
    Readable in = new StringReader("new-project 60 60 set-filter layer-2 red-component q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmInputsMock p = new BasicCollageProjectConfirmInputsMock(log);
    CollageController controller = new CollageControllerImpl(in, p, new CollageTextView());
    controller.runProgram();
    assertEquals("layerName=layer-2 filterType=red-component\n", log.toString());
  }

  @Test
  public void testSaveImage() {
    Readable in = new StringReader("new-project 60 60 save-image Collaging/src/res/image1.ppm q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmMethodsMock p = new BasicCollageProjectConfirmMethodsMock(log);
    CollageController controller = new CollageControllerImpl(in, p, new CollageTextView());
    controller.runProgram();
    assertEquals("saveImage ", log.toString());
  }

  @Test
  public void testSaveImageInputs() {
    Readable in = new StringReader("new-project 60 60 save-image Collaging/src/res/image1.ppm q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmInputsMock p = new BasicCollageProjectConfirmInputsMock(log);
    CollageController controller = new CollageControllerImpl(in, p, new CollageTextView());
    controller.runProgram();
    assertEquals("imagePath=Collaging/src/res/image1.ppm\n", log.toString());
  }

  @Test
  public void testSaveProject() {
    Readable in = new StringReader("new-project 60 60 save-project Collaging/src/res/project1 q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmMethodsMock p = new BasicCollageProjectConfirmMethodsMock(log);
    CollageController controller = new CollageControllerImpl(in, p, new CollageTextView());
    controller.runProgram();
    assertEquals("saveProject ", log.toString());
  }

  @Test
  public void testSaveProjectInputs() {
    Readable in =
            new StringReader("new-project 60 60 save-project Collaging/src/res/project1.txt q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmInputsMock p = new BasicCollageProjectConfirmInputsMock(log);
    CollageController controller = new CollageControllerImpl(in, p, new CollageTextView());
    controller.runProgram();
    assertEquals("ProjectPath=Collaging/src/res/project1.txt\n", log.toString());
  }

}

