package collagefiles.view;
import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class LayerPanel extends JPanel {

  private String name;
  private ArrayList<Image> images;

  public LayerPanel(String name) {
    this.name = name;
    images = new ArrayList<Image>();
  }

  public String getName() {
    return name;
  }
  public void addImage(File imageFile) {
    try {
      Image image = ImageIO.read(imageFile);
      images.add(image);
      repaint();
    } catch (IOException e) {
      System.out.println("Error loading image: " + e.getMessage());
    }
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    for (Image image : images) {
      g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
    }
  }
}

