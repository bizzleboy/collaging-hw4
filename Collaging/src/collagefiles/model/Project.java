package collagefiles.model;

public interface Project {


 public String saveProject(String ProjectPath);
 public void addLayer(String layerName);
 public void addImageToLayer(String layerName, Image image,int xPos, int yPos );
 public void setFilter (String layerName, String filterType);
public String saveImage(String imagePath);
}
