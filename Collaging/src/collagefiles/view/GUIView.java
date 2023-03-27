package collagefiles.view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.*;

public interface GUIView {
  public void setListener(ActionListener listener);
public JFrame getFrame();

}
