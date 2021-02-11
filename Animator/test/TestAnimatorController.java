import controller.AnimatorController;
import controller.AnimatorControllerImpl;
import java.io.StringReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests that the {@code AnimatorController} correctly calls methods in the right order in the given
 * view and passes the right information.
 */
public class TestAnimatorController {

  //Tests that the controller accurately calls setModel before calling display when the animate()
  //method is performed.
  @Test
  public void testAnimate() {
    StringBuilder testLog = new StringBuilder();

    AnimatorController controller = new AnimatorControllerImpl(new ViewMock(testLog),
        new StringReader("canvas 0 0 500 500\n"
            + "shape R rectangle\n"
            + "motion R 1 0 0 3 3 0 0 0    10 200 450 3 3 0 0 0\n"
            + "shape C ellipse\n"
            + "motion C 5 50 200 5 5 255 0 0    15 350 0 25 25 0 0 255"), false);
    assertEquals("", testLog.toString());
    controller.animate();
    assertEquals(
        "setModel called with animation environment of left corner location (0,0) and "
            + "dimensions 500x500\n" + "display called\n", testLog.toString());
  }
}