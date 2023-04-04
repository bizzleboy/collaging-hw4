package collagefiles.controller;

import java.io.InputStreamReader;


import collagefiles.model.BasicCollageProject;
import collagefiles.view.CollageTextView;
import collagefiles.model.Project;
import collagefiles.view.GUIView2;


/**
 * Main method to run the collage program.
 */
public class Main {
  /**
   * Takes in input to run project.
   * @param args All the inputs.
   */
  public static void main(String[] args) {
    Project project = new BasicCollageProject(10 ,10,255);

    GUIView2 view= new GUIView2();
    GUIController2 controller2 = new GUIController2(view);
  }

  }
