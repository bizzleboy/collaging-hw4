package collagefiles.view;

import java.awt.Color;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.Component;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.OverlayLayout;

/**
 * Implementation of a view for a collage project.
 * Gives user the option to make projects.
 * Load projects, save images, apply filters and layers and add images.
 */
public class GUIView2 extends JFrame implements GUIView {
  private JButton newProjectButton;
  private JButton loadProjectButton;
  private JFrame frame;

  private JPanel layerPanel;

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

    JPanel mainPanel = new JPanel();
    mainPanel.setMaximumSize(new Dimension(500, 500));
    mainPanel.setLayout(new OverlayLayout(mainPanel));
    mainPanel.setBackground(Color.white);

    JPanel buttonPanel = new JPanel();
    buttonPanel.setLayout(new FlowLayout());

    JPanel projectPanel = new JPanel();
    projectPanel.setLayout(new FlowLayout());

    layerPanel = new JPanel();
    layerPanel.setLayout(new BoxLayout(layerPanel, BoxLayout.Y_AXIS));
    layerPanel.setBorder(BorderFactory.createLineBorder(Color.GRAY));
    layerPanel.add(new JLabel("Layers:"));
    layerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

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


    JScrollPane scrollPane = new JScrollPane(imageView);

    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

    frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    frame.getContentPane().add(projectPanel, BorderLayout.NORTH);
    frame.add(layerPanel, BorderLayout.WEST);

    this.frame.getContentPane().add(scrollPane, BorderLayout.CENTER);


    frame.setVisible(true);

  }

  /**
   * Attatches listener to all primary buttons.
   *
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
   *
   * @return This frame.
   */
  public JFrame getFrame() {
    return frame;
  }

  /**
   * Adds a layer to list of layers visible on gui.
   *
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
    layerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

  }

  /**
   * Renders image on layer panel as a buffered image.
   *
   * @param image  BufferedImage to be processed.
   * @param xOffset Offsets rendered image x pixels to the right.
   * @param yOffset Offsets rendered image y pixels down.
   */
  public void displayImage(BufferedImage image, int xOffset, int yOffset) {
    //This method was modified to remove the creation of the buffered image.
    //We delegated that to the controller instead.

    ImageIcon icon = new ImageIcon(image);

    this.imgHolder.setIcon(icon);

    this.imgHolder.setBorder(BorderFactory.createEmptyBorder(yOffset, xOffset, 0, 0));

    this.imageView.add(this.imgHolder);

    this.frame.revalidate();
    this.frame.repaint();

  }



  /**
   * Displays simple popup box.
   *
   * @param message Message in popup box.
   */
  public void renderMessage(String message) {
    JOptionPane.showMessageDialog(this.frame, message);
  }

  /**
   * Display input box.
   * @param message Displayed message in popup box.
   * @return
   */
  public String renderInput(String message) {
    return JOptionPane.showInputDialog(this.frame, message);
  }

}
