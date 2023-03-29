package collagefiles.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import collagefiles.controller.GUIController2;

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

    applyFilterButton = new JButton("Add Layer");
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



    scrollPane = new JScrollPane(mainPanel);
   // frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
    frame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    frame.getContentPane().add(projectPanel, BorderLayout.NORTH);
    frame.add(layerPanel, BorderLayout.WEST);


    frame.setVisible(true);
  }

  @Override
  public void setListener(ActionListener listener) {
    addLayerButton.addActionListener(listener);
    addImageButton.addActionListener(listener);
    newProjectButton.addActionListener(listener);
    loadProjectButton.addActionListener(listener);
    applyFilterButton.addActionListener(listener);
  }

  public JFrame getFrame() {
    return frame;
  }

  public JPanel addLayer() {
    JPanel newLayer = new JPanel();
    newLayer.setOpaque(false);

    newLayer.setLayout(null);
    mainPanel.add(newLayer);
    mainPanel.repaint();
    return newLayer;
  }

  public void addLayerButton(JButton layerButton) {
    layerPanel.add(layerButton);
    layerPanel.revalidate();
    layerPanel.repaint();
  }
}