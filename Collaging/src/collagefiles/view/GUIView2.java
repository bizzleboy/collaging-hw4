package collagefiles.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import collagefiles.controller.GUIController2;
import collagefiles.model.Image;
import collagefiles.model.Pixel;

public class GUIView2 extends JFrame implements GUIView {
  private JButton newProjectButton;
  private JButton loadProjectButton;
  private JFrame frame;
  private JPanel mainPanel;
  private JPanel buttonPanel;
  private JPanel projectPanel;
  private JPanel layerPanel;
  private JScrollPane scrollPane;
  private JButton addLayerButton;
  private JButton addImageButton;
  private JButton applyFilterButton;
  private JLabel imgHolder;
  private JButton saveImageButton;
  private JButton saveProjectButton;


  private JPanel imageView;


  public GUIView2() {
    super();

    frame = new JFrame("Layered Image Viewer");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 600);
    frame.setLayout(new BorderLayout());

    mainPanel = new JPanel();
    mainPanel.setMaximumSize(new Dimension(500,500));
    mainPanel.setLayout(new OverlayLayout(mainPanel));
    mainPanel.setBackground(Color.white);

    buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());

    projectPanel = new JPanel();
    projectPanel.setLayout(new FlowLayout());

    layerPanel = new JPanel();
    layerPanel.setLayout(new BoxLayout(layerPanel,BoxLayout.Y_AXIS));

    applyFilterButton = new JButton("Apply Filter");
    buttonPanel.add(applyFilterButton);
    applyFilterButton.setActionCommand("apply-filter");

    addLayerButton = new JButton("Add Layer");
    addLayerButton.setActionCommand("add-layer");

    buttonPanel.add(addLayerButton);

    addImageButton = new JButton("Add Image");
    addImageButton.setActionCommand("add-image");

    buttonPanel.add(addImageButton);

    newProjectButton = new JButton("New Project");
    newProjectButton.setActionCommand("new-project");
    projectPanel.add(newProjectButton);

    loadProjectButton = new JButton("Load Project");
    loadProjectButton.setActionCommand("load-project");
    projectPanel.add(loadProjectButton);

    saveImageButton = new JButton("Save Image");
    saveImageButton.setActionCommand("save-image");
    projectPanel.add(saveImageButton);

    saveProjectButton = new JButton("Save Project");
    saveProjectButton.setActionCommand("save-project");
    projectPanel.add(saveProjectButton);


    this.imageView = new JPanel();
    this.imageView.setBackground(Color.blue);
    this.imageView.setOpaque(true);


    imgHolder = new JLabel();


    scrollPane = new JScrollPane(imageView);

    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    frame.getContentPane().add(projectPanel, BorderLayout.NORTH);
    frame.add(layerPanel, BorderLayout.WEST);

    this.frame.getContentPane().add(scrollPane,BorderLayout.CENTER);


    frame.setVisible(true);

  }

  @Override
  public void setListener(ActionListener listener) {
    addLayerButton.addActionListener(listener);
    addImageButton.addActionListener(listener);
    newProjectButton.addActionListener(listener);
    loadProjectButton.addActionListener(listener);
    applyFilterButton.addActionListener(listener);
    saveImageButton.addActionListener(listener);
    saveProjectButton.addActionListener(listener);
  }

  public JFrame getFrame() {
    return frame;
  }

  public JPanel addLayer() {
    JPanel newLayer = new JPanel();
    newLayer.setOpaque(true);
    newLayer.setLayout(new FlowLayout(1));


    mainPanel.add(newLayer);
    mainPanel.repaint();
    return newLayer;
  }

  public void addLayerButton(String layerName) {
    JButton layerButton = new JButton(layerName);
    layerPanel.add(layerButton);
    layerButton.setActionCommand(layerName);
    layerPanel.revalidate();
    layerPanel.repaint();

  }

  public void displayImage(Image pixels, int xOffset, int yOffset) {
    int width = pixels.getPixels().size();
    int height = pixels.getPixels().get(0).size();

    BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    for (int y = 0; y < height; y++) {
      for (int x = 0; x < width; x++) {
        Pixel pixel = pixels.getFilterPixels().get(y).get(x);
        int color =pixel.getPixelColor().getRGB();
        image.setRGB(x, y, color);

        this.frame.revalidate();
        this.frame.repaint();
      }
    }

    ImageIcon icon = new ImageIcon(image);

    this.imgHolder.setIcon(icon);

    this.imgHolder.setBorder(BorderFactory.createEmptyBorder(yOffset, xOffset, 0, 0));

    this.imageView.add(this.imgHolder);

    this.frame.revalidate();
    this.frame.repaint();

  }
}

//delegate layer buttons here