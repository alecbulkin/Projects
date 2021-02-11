import model.ReadOnlyAnimationEnvironmentImpl;
import org.junit.Test;

import java.awt.Dimension;
import java.awt.Color;

import static org.junit.Assert.assertEquals;

import model.AnimatedShape;
import model.AnimatedShapeImpl;
import model.AnimationEnvironment;
import model.AnimationEnvironmentImpl;
import model.commands.Move;
import model.commands.Paint;
import model.commands.Rotate;
import model.commands.Scale;
import model.Position2D;
import model.Shapes;
import view.TextView;

/**
 * Ensures that the text view implementation correctly translates the model it is given into a text
 * output that can be output to the given output stream.
 */
public class TestTextView {

  StringBuilder out1 = new StringBuilder();
  StringBuilder out2 = new StringBuilder();

  AnimationEnvironment environment1 =
      AnimationEnvironmentImpl.builder().setBounds(0, 0, 5, 5).build();

  AnimatedShape redRect = new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 0,
      new Dimension(7, 5), new Color(255, 0, 0), new Position2D(20, 25),
      0);

  AnimatedShape blueCircle = new AnimatedShapeImpl(Shapes.Oval, "Blue Circle", 3,
      new Dimension(3, 3), new Color(0, 0, 255), new Position2D(0, 0),
      25);

  AnimatedShape greenTriangle = new AnimatedShapeImpl(Shapes.Triangle, "Green Triangle", 10,
      new Dimension(5, 10), new Color(0, 255, 0), new Position2D(-15, -15),
      0);

  TextView v1 = new TextView(out1);

  TextView v2 = new TextView(out2);

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    v1.display();
  }

  @Test
  public void testSetModelandTextView() {
    v1.setModel(new ReadOnlyAnimationEnvironmentImpl(environment1));

    v1.display();

    assertEquals("canvas 0 0 5 5\n", out1.toString());

    environment1.addShape(redRect);
    environment1.addShape(blueCircle);
    environment1.addShape(greenTriangle);

    v2.setModel(new ReadOnlyAnimationEnvironmentImpl(environment1));

    this.environment1.commandShape("Red Rectangle", 0, 5,
        new Move(20, 25, 0, 0),
        new Scale(7, 5, 10, 10));
    this.environment1.commandShape("Red Rectangle", 7, 10,
        new Move(0, 0, 5, 5),
        new Paint(255, 0, 0, 0, 0, 0),
        new Rotate(0, 25));
    this.environment1.commandShape("Green Triangle", 11, 15,
        new Move(-15, -15, 0, 0));

    v2.display();

    assertEquals("canvas 0 0 5 5\n"
        + "Shape Red Rectangle Rectangle\n"
        + "Motion Red Rectangle 0 20.0 25.0 7.0 5.0 255 0 0 0.0  5 0.0 0.0 10.0 10.0 255 0 0 "
        + "0.0\n"
        + "Motion Red Rectangle 5 0.0 0.0 10.0 10.0 255 0 0 0.0  7 0.0 0.0 10.0 10.0 255 0 0 "
        + "0.0\n"
        + "Motion Red Rectangle 7 0.0 0.0 10.0 10.0 255 0 0 0.0  10 5.0 5.0 10.0 10.0 0 0 0 "
        + "25.0\n"
        + "\n"
        + "Shape Blue Circle Oval\n"
        + "\n"
        + "Shape Green Triangle Triangle\n"
        + "Motion Green Triangle 10 -15.0 -15.0 5.0 10.0 0 255 0 0.0  11 -15.0 -15.0 5.0 "
        + "10.0 0 255 0 0.0\n"
        + "Motion Green Triangle 11 -15.0 -15.0 5.0 10.0 0 255 0 0.0  15 0.0 0.0 5.0 "
        + "10.0 0 255 0 0.0\n"
        + "\n", out2.toString());
  }
}