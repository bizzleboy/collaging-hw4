package collagefiles.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import collagefiles.model.Image;
import collagefiles.model.Pixel;

/**
 * Implementation of a view for a collage project.
 * Gives user the option to make projects.
 * Load projects, save images, apply filters and layers and add images.
 */
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

  /**
   * Constructor for a GUIView2 that sets up all primary panels and buttons.
   */
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
    layerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    layerPanel.add(new JLabel("Layers:"));
    layerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

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
    this.imageView.setBackground(Color.gray);
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

  /**
   * Attatches listener to all primary buttons.
   * @param listener Controller that hears events.
   */
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

  /**
   * Returns the frame of the view for message rendering purposes.
   * @return This frame.
   */
  public JFrame getFrame() {
    return frame;
  }

  /**
   * Adds a layer to list of layers visible on gui.
   * @param layerName Name of the layer as a string.
   */
  public void addLayerButton(String layerName) {
    JLabel spacing = new JLabel(" ");
    layerPanel.add(spacing);
    JLabel layerLabel = new JLabel(layerName);
    layerPanel.add(layerLabel);

    layerPanel.revalidate();
    layerPanel.repaint();

  }

  /**
   * Removes all layers. Used for making new project.
   * Or loading an old project.
   */
  public void removeLayers() {
    Component[] components = layerPanel.getComponents();

// iterate over the components and do something with them
    for (Component component : components) {
      // do something with the component
      layerPanel.remove(component);
    }

    layerPanel.add(new JLabel("Layers:"));
    layerPanel.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

  }

  /**
   * Renders image on layer panel as a buffered image.
   * @param pixels List of List<pixels> to be processed.
   * @param xOffset Offsets rendered image x pixels to the right.
   * @param yOffset Offsets rendered image y pixels down.
   */
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

  /**
   * Displays simple popup box.
   * @param message Message in popup box.
   */
  public void renderMessage(String message){
    JOptionPane.showMessageDialog(this.frame,message);
  }

  /**
   *
   * @param message Displayed message in popup box.
   * @return
   */
  public String renderInput(String message){
    return JOptionPane.showInputDialog(this.frame,message);
  }

}
