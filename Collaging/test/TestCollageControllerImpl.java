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
  public void testQuitsAfter1Functions() {
    Readable in = new StringReader("new-project 60 60 add-layer layer-2 q");
    Appendable log = new StringBuilder();
    BasicCollageProjectConfirmMethodsMock p = new BasicCollageProjectConfirmMethodsMock(log);
    CollageController controller = new CollageControllerImpl(in,p);
    controller.runProgram();
    assertEquals("addLayer ",log.toString());

//    in = new StringReader("new-project add-layer layer-2 q");
//    log = new StringBuilder();
//    p = new BasicCollageProjectConfirmMethodsMock(log);
//    controller = new CollageControllerImpl(in,p);
//    controller.runProgram();
//    assertEquals("", log.toString());
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
                " image=" + image + " xPos=" + xPos + " yPos=" + yPos + "\n");
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

    @Override
    public void quit() {

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

    @Override
    public void quit() {
      try {
        this.log.append("quit ");
      } catch (IOException ignore) {
        //do nothing
      }
    }
  }

  }
