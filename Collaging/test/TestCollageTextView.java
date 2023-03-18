import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;

import collagefiles.View.CollageView;

/**
 * A class testing the construction and methods of the SetGameTextView class.
 */
public class TestCollageTextView {




  /**
   * A class for testing if the SetGameView methods renders correctly.
   */
  class CollageTextViewRenderMock implements CollageView {

    private final Appendable log;

    public CollageTextViewRenderMock(Appendable log) {
      this.log = log;
    }

    @Override
    public void renderMessage(String message) throws IOException {
      this.log.append(message);
    }
  }
}

