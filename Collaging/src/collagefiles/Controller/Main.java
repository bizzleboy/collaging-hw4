package collagefiles.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;

import collagefiles.model.BasicCollageProject;
import collagefiles.model.Project;
import collagefiles.view.CollageTextView;
import collagefiles.view.GUIView2;


/**
 * Main method to run the collage program.
 */
public class Main {
  /**
   * Takes in input to run project.
   *
   * @param args All the inputs.
   */
  public static void main(String[] args) {
    Project project = new BasicCollageProject();

    if (args.length == 0) {
      GUIView2 view = new GUIView2();
      GUIController2 controller2 = new GUIController2(view,project);
    } else if (args[0].equals("-text")) {
      CollageController controller = new CollageControllerImpl(new InputStreamReader(System.in),
              new CollageTextView(),project);
      controller.runProgram();


    } else if (args[0].equals("-file")) {

      CollageController controller = null;
      try {
        controller = new CollageControllerImpl(new InputStreamReader(new FileInputStream(args[1])),
                new CollageTextView(),project);
      } catch (FileNotFoundException e) {
        throw new RuntimeException(e);
      }
      controller.runProgram();
    }


  }
}
