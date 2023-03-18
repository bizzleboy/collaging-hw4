package collagefiles.Controller;

import java.io.InputStreamReader;

import collagefiles.View.CollageTextView;
import collagefiles.model.BasicCollageProject;
import collagefiles.model.Project;

public class Main {
  public static void main(String[] args) {
    Project project;

   CollageController controller = new CollageControllerImpl( new InputStreamReader(System.in),new CollageTextView());
   controller.runProgram();
  }
}