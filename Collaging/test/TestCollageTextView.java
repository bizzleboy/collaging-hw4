import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import collagefiles.Controller.CollageController;
import collagefiles.Controller.CollageControllerImpl;
import collagefiles.View.CollageView;
import collagefiles.model.BasicCollageProject;
import collagefiles.model.Project;

import static org.junit.Assert.assertEquals;

/**
 * A class testing the construction and methods of the CollageTextView class.
 */
public class TestCollageTextView {

  @Test
  public void testAddLayer() {
    Readable in = new StringReader("add-layer layer-2 q");
    Appendable log = new StringBuilder();
    Project m = new BasicCollageProject(10,10,255);
    CollageView v = new CollageTextViewRenderMock(log);
    CollageController controller = new CollageControllerImpl(in, m, v);
    controller.runProgram();
    assertEquals("enter layer name\nlayer added if name is unique\nprogram quit!\n", log.toString());
  }

  @Test
  public void testSetFilter() {
    Readable in = new StringReader("set-filter name red-component " +
            "set-filter name darken-value " +
            "set-filter name brighten-luma name q");
    Appendable log = new StringBuilder();
    Project m = new BasicCollageProject(10,10,255);
    CollageView v = new CollageTextViewRenderMock(log);
    CollageController controller = new CollageControllerImpl(in, m, v);
    controller.runProgram();
    assertEquals("enter layer name\nenter filter name\nfilter set if layer exists\n" +
            "enter layer name\nenter filter name\nfilter set if layer exists\n" +
            "enter layer name\nenter filter name\nfilter set if layer exists\nprogram quit!\n", log.toString());
  }

  @Test
  public void testAddImageToLayer() {
    Readable in = new StringReader("add-image name " +
            "/Users/dylaningham/Downloads/CS3500/collaging-hw4/Collaging/src/newSprite.ppm  " +
            "1 1 q");
    Appendable log = new StringBuilder();
    Project m = new BasicCollageProject(10,10,255);
    CollageView v = new CollageTextViewRenderMock(log);
    CollageController controller = new CollageControllerImpl(in, m, v);
    controller.runProgram();
    assertEquals("enter layer name\n" +
            "enter image path\n" +
            "enter x position\n" +
            "x entered\n" +
            "enter y position\n" +
            "y entered\n" +
            "image added to layer if layer exists\n" +
            "program quit!\n", log.toString());
  }

  @Test
  public void saveProject() {
    Readable in = new StringReader("save-project " +
            "/Users/dylaningham/Downloads/CS3500/collaging-hw4/Collaging/src/saved.ppm  q");
    Appendable log = new StringBuilder();
    Project m = new BasicCollageProject(10,10,255);
    CollageView v = new CollageTextViewRenderMock(log);
    CollageController controller = new CollageControllerImpl(in, m, v);
    controller.runProgram();
    assertEquals("enter file path\n" +
            "project saved\n" +
            "program quit!\n", log.toString());
  }

  @Test
  public void saveImage() {
    Readable in = new StringReader("save-image " +
            "/Users/dylaningham/Downloads/CS3500/collaging-hw4/Collaging/src/image.ppm  q");
    Appendable log = new StringBuilder();
    Project m = new BasicCollageProject(10,10,255);
    CollageView v = new CollageTextViewRenderMock(log);
    CollageController controller = new CollageControllerImpl(in, m, v);
    controller.runProgram();
    assertEquals("enter image path\n" +
            "image saved\n" +
            "program quit!\n", log.toString());
  }

  /**
   * A class for testing if the CollageTextView methods renders correctly.
   */
  class CollageTextViewRenderMock implements CollageView {

    private final Appendable log;

    public CollageTextViewRenderMock(Appendable log) {
      this.log = log;
    }

    @Override
    public void renderMessage(String message) throws IOException {
      this.log.append(message+"\n");
    }
  }
}

