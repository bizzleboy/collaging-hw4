package collagefiles.View;


import java.awt.event.ActionListener;


import javax.swing.*;

/**
 * Visual representation of a GUI for collage projects.
 */
public interface GUIView {
  /**
   * Assigns a listener to a component (likely a panel).
   * @param listener Controller that hears events.
   */
  public void setListener(ActionListener listener);

  /**
   * Gets the frame for more advanced display boxes in Controller.
   * @return Frame of the view.
   */
  public JFrame getFrame();

  /**
   * Renders basic popup box in gui.
   * @param message Message in popup box.
   */
  public void renderMessage(String message);

  /**
   *
   * @param message Displayed message in popup box.
   * @return User input.
   */
  public String renderInput(String message);

  /**
   * Deletes all the layers of a project.
   * Helpful for making a new project
   */
  public void removeLayers();


}
