import model.AnimatedShape;
import model.AnimatedShapeImpl;
import model.Command;
import model.commands.Move;
import model.commands.Paint;
import model.commands.Rotate;
import model.commands.Scale;
import model.Position2D;
import model.Shapes;
import java.awt.Color;
import java.awt.Dimension;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests that all of the animation commands for our {@code AnimatedShape} make the expected changes
 * to an {@code AnimatedShape} when applied through the commandShape method of an {@code
 * AnimationEnvironment}.
 */
public class TestCommands {

  AnimatedShape greenRect = new AnimatedShapeImpl(Shapes.Rectangle, "Green Rectangle", 0,
      new Dimension(5, 5), new Color(0, 255, 0),
      new Position2D(0, 0), 0);

  AnimatedShape redOval = new AnimatedShapeImpl(Shapes.Oval, "Red Oval", 5,
      new Dimension(10, 10), new Color(255, 0, 0),
      new Position2D(10, 10), 10);

  //Moves from (0, 0) to (5, 5)
  Command move1 = new Move(0, 0, 5, 5);
  //Moves from (10, 10) to (0, 0)
  Command move2 = new Move(10, 10, 0, 0);

  //Changes the color from Red to Green
  Command paint1 = new Paint(255, 0, 0, 0, 255, 0);
  //Changes the color from Green to Blue
  Command paint2 = new Paint(0, 255, 0, 0, 0, 255);

  //Rotates from 0.0 to 10.0
  Command rotate1 = new Rotate(0, 10);
  //Rotates from 10.0 to 20.0
  Command rotate2 = new Rotate(10, 370);
  //Rotates from 10.0 to 10.0
  Command rotate3 = new Rotate(10, 0);

  //Scales from 5x5 to 10x10
  Command scale1 = new Scale(5, 5, 10, 10);
  //Scales frm 10x10 to 4x4
  Command scale2 = new Scale(10, 10, 4, 4);

  //Tests that Move works as expected
  @Test
  public void testMove() {
    this.move1.apply(this.greenRect);
    this.move2.apply(this.redOval);
    assertEquals(new Position2D(5, 5), this.greenRect.getPosn());
    assertEquals(new Position2D(0, 0), this.redOval.getPosn());
  }

  //Tests that Paint works as expected
  @Test
  public void testPaint() {
    this.paint1.apply(this.redOval);
    this.paint2.apply(this.greenRect);
    assertEquals(new Color(0, 255, 0), this.redOval.getColor());
    assertEquals(new Color(0, 0, 255), this.greenRect.getColor());
  }


  //Tests that Rotate works as expected
  @Test
  public void testRotate() {
    this.rotate1.apply(this.greenRect);
    this.rotate3.apply(this.redOval);
    this.rotate2.apply(this.redOval);
    assertEquals(10.0, this.greenRect.getOrientation(), .01);
    assertEquals(20.0, this.redOval.getOrientation(), .01);
  }


  //Tests that Scale works as expected
  @Test
  public void testScale() {
    this.scale1.apply(this.greenRect);
    this.scale2.apply(this.redOval);
    assertEquals(new Dimension(4, 4), this.redOval.getBoundary());
    assertEquals(new Dimension(10, 10), this.greenRect.getBoundary());
  }

  //Tests that an invalid initial position is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidinitialPosition() {
    this.move1.apply(this.redOval);
  }

  //Tests that an invalid initial size is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitialSize() {
    this.scale1.apply(this.redOval);
  }

  //Tests that an invalid initial orientation is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitialOrientation() {
    this.rotate1.apply(this.redOval);
  }


  //Tests that an invalid initial color is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitialolor() {
    this.paint1.apply(this.greenRect);
  }

  //Tests that a negative rotation is negative
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommandRotation() {
    Command invalidRotation = new Rotate(255, -10);
  }

  //Tests that a negative initial orientation is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommandNegInitialOrientation() {
    Command invalidRotation = new Rotate(-10, 10);
  }

  //Tests that an initial orientation greater than 360 is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommandInitialOrientation() {
    Command invalidRotation = new Rotate(375, 10);
  }

  //Tests that a negative dimension is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommandNegDim() {
    Command invalidScale = new Scale(5, 5, 4, -2);
  }

  //Tests that a 0 dimension is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommandZeroDim() {
    Command invalidScale = new Scale(10, 10, 0, 5);
  }

  //Tests that an invalid initial dimension command is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidInitialDimensionCommand() {
    Command invalidDimenion = new Scale(-5, 5, 2, 2);
  }

  //Tests that a negative rgb value is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommandNegRGB() {
    Command invalidPaint = new Paint(-5, 0, 0, 0, 0, 0);
  }

  //Tests that an rgb value greater than 255 is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommandRGB() {
    Command invalidPaint = new Paint(0, 0, 0, 0, 340, 0);
  }


}
