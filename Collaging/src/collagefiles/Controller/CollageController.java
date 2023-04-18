package collagefiles.controller;

/**
 * Represents a controller that assists the user in adding images.
 */
public interface CollageController {
  /**
   * Begins the collage program.
   *
   * @throws IllegalStateException when provided invalid readable.
   */
  void runProgram() throws IllegalStateException;

}
