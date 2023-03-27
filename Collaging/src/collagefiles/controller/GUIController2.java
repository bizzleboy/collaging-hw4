package collagefiles.controller;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

import collagefiles.model.Project;
import collagefiles.view.GUIView2;


public class GUIController2 extends JFrame  implements ActionListener {
  private GUIView2 view;
  private JPanel currentLayer;
  private int x_offset;
  private int y_offset;
  private Project project;

  public GUIController2(GUIView2 view, Project project) {
    super();
    this.view = view;
    this.currentLayer = view.addLayer();
    this.view.setListener(this);
    this.project = project;

  }

  public void actionPerformed(ActionEvent e) {
    String command = e.getActionCommand();

    if (command.equals("new-project")) {
      String pHeightS = JOptionPane.showInputDialog(view.getFrame(), "Enter Project Height");
      String pWidthS = JOptionPane.showInputDialog(view.getFrame(), "Enter Project Width");
      try {
        int pHeight = Integer.parseInt(pHeightS);
       int pWidth = Integer.parseInt(pWidthS);
      } catch (NumberFormatException n){
        JOptionPane.showMessageDialog(view.getFrame(), "Invalid height/width.");
      }
    }

    if (command.equals("apply-filter")) {
      String[] options = {"normal", "red-component", "blue-component","green-component"};
      String filter = (String) JOptionPane.showInputDialog(view.getFrame(), "Choose a filter:", "Filter", JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
      if (filter != null) {
        System.out.println(filter);
        // TODO: Apply the selected filter to the current layer.
        // You can use the 'filter' string to determine which filter to apply.
      }
    }

    if (command.equals("add-layer")) {
      String layerName = JOptionPane.showInputDialog(view.getFrame(), "Enter layer name:");
      JPanel newLayer = view.addLayer();
      JButton layerButton = new JButton(layerName);
      layerButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
          System.out.println(e);
          currentLayer = newLayer;

        }
      });
      view.addLayerButton(layerButton);
    } else if (command.equals("add-image")) {
      JFileChooser fileChooser = new JFileChooser();
      int result = fileChooser.showOpenDialog(view.getFrame());
      if (result == JFileChooser.APPROVE_OPTION) {
        File selectedFile = fileChooser.getSelectedFile();
        String x_string = JOptionPane.showInputDialog(view.getFrame(), "Enter x-coordinate:");
        String y_string = JOptionPane.showInputDialog(view.getFrame(), "Enter y-coordinate:");
        try {
          x_offset = Integer.parseInt(x_string);
          y_offset = Integer.parseInt(y_string);
          ImageIcon imageIcon = new ImageIcon(selectedFile.getAbsolutePath());
          JLabel imageLabel = new JLabel(imageIcon);
          imageLabel.setBounds(x_offset, y_offset, imageIcon.getIconWidth(), imageIcon.getIconHeight());
          currentLayer.add(imageLabel);
          currentLayer.repaint();
        } catch (NumberFormatException ex) {
          JOptionPane.showMessageDialog(view.getFrame(), "Invalid coordinates.");
        }
      }
    }
  }
}