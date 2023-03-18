package collagefiles.View;

import java.io.IOException;

public class CollageTextView implements CollageView {

  private Appendable output;

  public CollageTextView() {
    this.output = System.out;
  }

  public CollageTextView(Appendable output) throws IllegalArgumentException {
    if (output == null) {
      throw new IllegalArgumentException("provided set game model destination cannot be null.");
    }
    this.output = output;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    try {
      this.output.append(message+"\n");
    } catch (IOException a) {
      throw new IOException(a);
    }
  }
}