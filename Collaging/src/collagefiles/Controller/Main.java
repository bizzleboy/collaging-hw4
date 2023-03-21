package collagefiles.controller;

import java.io.InputStreamReader;

import collagefiles.view.CollageTextView;
import collagefiles.model.Project;

/**
 * Main method to run the collage program.
 */
public class Main {
  /**
   * Takes in input to run project.
   * @param args All the inputs.
   */
  public static void main(String[] args) {
    Project project;

    CollageController controller = new CollageControllerImpl(new InputStreamReader(System.in),
            new CollageTextView());
    controller.runProgram();
  }
}