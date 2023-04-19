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
  public void addImageToLayer(String layerName, ImageInterface image, int xPos, int yPos) {
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
  public List<LayerInterface> getLayers() {
    return null;
  }

  @Override
  public Image stackToImage(int startIndex) {
    return null;
  }

  @Override
  public ImageInterface LoadImagePixelsFromProject(Scanner sc, ArrayList<ArrayList<PixelInterface>> imageToCreate) {
    return null;
  }

  @Override
  public ImageInterface LoadImagePixelsFromProjectPNGJPEG(BufferedImage imageToCreate) {
    return null;
  }
}