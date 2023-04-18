package collagefiles.view;

import java.io.IOException;

/**
 * View for monitoring input output streams.
 */
public class CollageTextView implements collagefiles.view.CollageView {

  private final Appendable output;

  /**
   * Instantiates an output stream type view.
   */
  public CollageTextView() {
    this.output = System.out;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.output.append(message + "\n");
    } catch (IOException a) {
      throw new IOException(a);
    }
  }
}