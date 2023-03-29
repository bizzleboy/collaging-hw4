package collagefiles.view;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JPanel;

import collagefiles.model.Pixel;


public class ImagePanel extends JPanel {
  private ArrayList<ArrayList<Pixel>> image;
  private int xOffset;
  private int yOffset;

  public ImagePanel(ArrayList<ArrayList<Pixel>> image, int xOffset, int yOffset) {
    this.image = image;
    this.xOffset = xOffset;
    this.yOffset = yOffset;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    // create a new BufferedImage to hold the image data
    int height = image.size();
    int width = image.get(0).size();
    BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

    // loop through the image data and set the pixel colors in the BufferedImage
    for (int row = 0; row < height; row++) {
      for (int col = 0; col < width; col++) {
        Pixel pixel = image.get(row).get(col);
        int rgb = pixel.getPixelColor().getRGB();
        img.setRGB(col, row, rgb);
      }
    }

    // draw the BufferedImage on the panel with the specified offset
    g.drawImage(img, xOffset, yOffset, this);
  }
}