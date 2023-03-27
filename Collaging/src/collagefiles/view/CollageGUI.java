//package collagefiles.view;
//
//import javax.imageio.ImageIO;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
//import java.awt.image.BufferedImage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class CollageGUI extends JFrame implements GUIView{
//  private JFrame frame;
//  private JButton addLayerButton;
//  private JButton addImageButton;
//  private Image layer;
//  private Image placedImage;
//
//  public CollageGUI() {
//    super();
//    frame = new JFrame("Collage GUI");
//    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//    frame.setSize(400, 400);
//
//    addLayerButton = new JButton("Add Layer");
//    addLayerButton.setActionCommand("Add Layer Button");
//    //this.add(addLayerButton);
//
//    addImageButton = new JButton("Add Image");
//    addImageButton.setActionCommand("Add Image Button");
//    //this.add(addImageButton);
//
//    JPanel panel = new JPanel();
//    panel.add(addLayerButton);
//    panel.add(addImageButton);
//    panel.setSize(30,30);
//
//    frame.getContentPane().add(panel);
//    frame.setVisible(true);
//  }
//@Override
//  public void setListener(ActionListener listener){
//    addLayerButton.addActionListener(listener);
//    addImageButton.addActionListener(listener);
//  }
//
//  @Override
//  public Frame getFrame() {
//    return this.frame;
//  }
//
//  @Override
//  public void addImage(File selectedFile) {
//    JPanel imagePanel = new JPanel();
//    this.frame.add(imagePanel);
//    try {
//      BufferedImage image = ImageIO.read(selectedFile);
//      JLabel label = new JLabel(new ImageIcon(image));
//      imagePanel.removeAll();
//      imagePanel.add(label);
//      imagePanel.revalidate();
//      imagePanel.repaint();
//    } catch (IOException ex) {
//      ex.printStackTrace();
//    }
//  }
//
//
////  public static void main(String[] args) {
////    new CollageGUI();
////  }
//}