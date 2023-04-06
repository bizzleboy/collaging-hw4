package collagefiles.controller;
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
   * @param args All the inputs.
   */
  public static void main(String[] args) {
    Project project = new BasicCollageProject(10 ,10,255);

    String strArgs="";
    for(String s: args){
      strArgs += s + " ";

    }
    strArgs = strArgs.trim();

    if(strArgs.equals("java -jar Collaging.jar -text")){
      CollageController controller = new CollageControllerImpl(new InputStreamReader(System.in),
              new CollageTextView());
      controller.runProgram();

    } else if (strArgs.equals("java -jar Collaging.jar")){
      GUIView2 view= new GUIView2();
      GUIController2 controller2 = new GUIController2(view);
    }

  }

  }
