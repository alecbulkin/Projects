import controller.EditableController;
import java.io.StringReader;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests that the editable controller accurately calls the appropriate methods In the model and view
 * when given user input.
 */
public class TestEditableController {




  //Tests that the controller can accurately call toggleTimeFlow when it is appropriate
  @Test
  public void testControllerToggleTimeFlow() {

    StringBuilder viewLog = new StringBuilder();

    EditableViewMock viewMock = new EditableViewMock(viewLog);

    EditableController controller = new EditableController(viewMock,
        new StringReader("canvas 0 0 500 500\n"
        + "shape R rectangle\n"
        + "motion R 1 0 0 3 3 0 0 0    10 200 450 3 3 0 0 0\n"
        + "shape C ellipse\n"
        + "motion C 5 50 200 5 5 255 0 0    15 350 0 25 25 0 0 255"));

    controller.animate();
    viewMock.triggerEvent("toggle time");




    assertEquals("Called set model with\n"
        + "Called addActionListener\n"
        + "Called Display\n"
        + "Called Time Flow\n", viewLog.toString());
  }

  @Test
  public void testControllerToggleLooping() {

    StringBuilder viewLog = new StringBuilder();

    EditableViewMock viewMock = new EditableViewMock(viewLog);

    EditableController controller = new EditableController(viewMock,
        new StringReader("canvas 0 0 500 500\n"
            + "shape R rectangle\n"
            + "motion R 1 0 0 3 3 0 0 0    10 200 450 3 3 0 0 0\n"
            + "shape C ellipse\n"
            + "motion C 5 50 200 5 5 255 0 0    15 350 0 25 25 0 0 255"));

    controller.animate();
    viewMock.triggerEvent("toggle looping");


    assertEquals("Called set model with\n"
        + "Called addActionListener\n"
        + "Called Display\n"
        + "Called toggle Looping\n", viewLog.toString());
  }

  @Test
  public void testControllerSetSpeed() {

    StringBuilder viewLog = new StringBuilder();

    EditableViewMock viewMock = new EditableViewMock(viewLog);

    EditableController controller = new EditableController(viewMock,
        new StringReader("canvas 0 0 500 500\n"
            + "shape R rectangle\n"
            + "motion R 1 0 0 3 3 0 0 0    10 200 450 3 3 0 0 0\n"
            + "shape C ellipse\n"
            + "motion C 5 50 200 5 5 255 0 0    15 350 0 25 25 0 0 255"));

    controller.animate();
    viewMock.triggerEvent("set speed");


    assertEquals("Called set model with\n"
        + "Called addActionListener\n"
        + "Called Display\n"
        + "Called getTextFieldText with speed field\n"
        + "Called set speed with5\n", viewLog.toString());
  }


  //Tests that the animate method correctly calls ...
  @Test
  public void testAnimated() {
    StringBuilder viewLog = new StringBuilder();

    EditableViewMock viewMock = new EditableViewMock(viewLog);

    EditableController controller = new EditableController(viewMock,
        new StringReader("canvas 0 0 500 500\n"
            + "shape R rectangle\n"
            + "motion R 1 0 0 3 3 0 0 0    10 200 450 3 3 0 0 0\n"
            + "shape C ellipse\n"
            + "motion C 5 50 200 5 5 255 0 0    15 350 0 25 25 0 0 255"));

    controller.animate();

    assertEquals("Called set model with\n"
        + "Called addActionListener\n"
        + "Called Display\n", viewLog.toString());
  }









}
