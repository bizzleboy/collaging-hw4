import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import collagefiles.model.Image;
import collagefiles.model.ImageInterface;
import collagefiles.model.Layer;
import collagefiles.model.LayerInterface;
import collagefiles.model.PixelInterface;
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
  public void addImageToLayer(String layerName, ImageInterface image, int xPos, int yPos) {
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
  public List<LayerInterface> getLayers() {
    return null;
  }

  @Override
  public ImageInterface stackToImage(int startIndex) {
    return null;
  }


  @Override
  public ImageInterface LoadImagePixelsFromProjectPNGJPEG(BufferedImage imageToCreate) {
    return null;
  }

  @Override
  public void setDimensions(int size) {

  }

  @Override
  public Project resetProject(int newSize) {
    return null;
  }

}