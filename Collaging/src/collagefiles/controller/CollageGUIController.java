package collagefiles.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

import collagefiles.model.Image;
import collagefiles.model.Layer;
import collagefiles.model.Project;
import collagefiles.view.CollageGUI;
import collagefiles.view.CollageView;
import collagefiles.view.GUIView;
public class CollageGUIController implements ActionListener {
  Project model;
  GUIView view;
  public CollageGUIController(Project p,GUIView v){
    this.model =p;
    this.view = v;
    this.view.setListener(this);

  }
  @Override
  public void actionPerformed(ActionEvent e) {

    switch (e.getActionCommand()) {
      case "Add Layer Button":
        String layerName = JOptionPane.showInputDialog(this.view.getFrame(), "Enter layer name:");
        new Layer(layerName, 100, 100);

        break;
      case "Add Image Button":
        JFileChooser fileChooser = new JFileChooser();
        String layerToAddTo = JOptionPane.showInputDialog(this.view.getFrame(), "Add to which layer?");
        int result = fileChooser.showOpenDialog(this.view.getFrame());
        if (result == JFileChooser.APPROVE_OPTION) {
          File selectedFile = fileChooser.getSelectedFile();
          System.out.println(selectedFile);
          // do something with selectedFile
        }
        break;
      default:
        break;
    }
  }
}
