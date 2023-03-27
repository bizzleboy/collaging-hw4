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


//    CollageController controller = new CollageControllerImpl(new InputStreamReader(System.in),
//            new CollageTextView());
//    controller.runProgram();
//

    GUIView2 view= new GUIView2();
    GUIController2 controller = new GUIController2(view,project);
  }
//  public static void main(String[] args) {
//    Project model = new BasicCollageProject(10,10,255);
//    GUIView view = new CollageGUI();
//    CollageGUIController controller = new CollageGUIController(model, view);
//  }
}