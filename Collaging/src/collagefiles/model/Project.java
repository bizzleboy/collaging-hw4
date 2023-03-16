package collagefiles.model;

public interface Project {


 public void saveProject();
 public void addLayer(String layerName);
 public void addImageToLayer(String layerName, Image image,int xPos, int yPos );
 public void setFilter (String layerName, String filterType);
public void saveImage(String fileName);
public void quit();
}
