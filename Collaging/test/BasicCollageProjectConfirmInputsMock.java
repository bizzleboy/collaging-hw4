import java.io.IOException;
import java.util.List;

import collagefiles.model.Image;
import collagefiles.model.Layer;
import collagefiles.model.Project;

class BasicCollageProjectConfirmInputsMock implements Project {

  protected final Appendable log;

  public BasicCollageProjectConfirmInputsMock(Appendable log) {
    this.log = log;
  }

  @Override
  public String saveProject(String projectPath) {
    try {
      this.log.append("ProjectPath=" + projectPath + "\n");
    } catch (IOException ignore) {
      //d
    }
    return null;
  }

  @Override
  public void addLayer(String layerName) {
    try {
      this.log.append("layerName=" + layerName + "\n");
    } catch (IOException ignore) {
      //pass
    }
  }

  @Override
  public void addImageToLayer(String layerName, Image image, int xPos, int yPos) {
    try {
      this.log.append("layerName=" + layerName
              + " imageLength=" + image.getFilterPixels().size()
              + " xPos=" + xPos + " yPos=" + yPos + "\n");
    } catch (IOException ignore) {
      //ic
    }
  }

  @Override
  public void setFilter(String layerName, String filterType) {
    try {
      this.log.append("layerName=" + layerName
             + " filterType=" + filterType + "\n");
    } catch (IOException ignore) {
      //catch
    }
  }

  @Override
  public String saveImage(String imagePath) {
    try {
      this.log.append("imagePath=" + imagePath + "\n");
    } catch (IOException ignore) {
      //c
    }
    return null;
  }

  @Override
  public List<Layer> getLayers() {
    return null;
  }

  @Override
  public Image stackToImage(int startIndex) {
    return null;
  }

}