import java.io.IOException;

import collagefiles.model.Image;
import collagefiles.model.Project;

class BasicCollageProjectConfirmMethodsMock implements Project {

  protected final Appendable log;

  public BasicCollageProjectConfirmMethodsMock(Appendable log) {

    this.log = log;
  }

  @Override
  public String saveProject(String projectPath) {
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