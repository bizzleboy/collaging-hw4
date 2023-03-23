package collagefiles.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

public class CollageGUI extends JFrame implements GUIView{
  private JFrame frame;
  private JButton addLayerButton;
  private JButton addImageButton;

  public CollageGUI() {
    frame = new JFrame("Collage GUI");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(400, 400);

    addLayerButton = new JButton("Add Layer");
    addLayerButton.setActionCommand("Add Layer Button");
    this.add(addLayerButton);

    addImageButton = new JButton("Add Image");
    addImageButton.setActionCommand("Add Image Button");
    this.add(addImageButton);

    JPanel panel = new JPanel();
    panel.add(addLayerButton);
    panel.add(addImageButton);

    frame.getContentPane().add(panel);
    frame.setVisible(true);
  }
@Override
  public void setListener(ActionListener listener){
    addLayerButton.addActionListener(listener);
    addImageButton.addActionListener(listener);
  }

  @Override
  public Frame getFrame() {
    return this.frame;
  }


//  public static void main(String[] args) {
//    new CollageGUI();
//  }
}