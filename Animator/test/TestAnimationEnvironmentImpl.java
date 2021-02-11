import model.commands.Move;
import model.commands.Paint;
import model.commands.Rotate;
import model.commands.Scale;
import model.ReadOnlyAnimatedShape;
import model.ReadOnlyAnimatedShapeImpl;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import model.Position2D;
import model.AnimatedShape;
import model.AnimationEnvironment;
import model.AnimationEnvironmentImpl;
import model.AnimatedShapeImpl;
import model.Shapes;

import java.awt.Dimension;
import java.awt.Color;
import java.util.ArrayList;

/**
 * Tests that our {@code AnimationEnvironmentImpl} and all of its methods function as expected in
 * congruence with the {@code AnimatedShapeImpl}.
 */
public class TestAnimationEnvironmentImpl {

  AnimationEnvironment environment1 =
      AnimationEnvironmentImpl.builder().setBounds(0, 0, 5, 5).build();

  AnimationEnvironment environment2 =
      AnimationEnvironmentImpl.builder().setBounds(5, -5, 15, 10).build();

  AnimatedShape redRect = new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 0,
      new Dimension(7, 5), new Color(255, 0, 0), new Position2D(20, 25),
      0);

  AnimatedShape blueCircle = new AnimatedShapeImpl(Shapes.Oval, "Blue Circle", 3,
      new Dimension(3, 3), new Color(0, 0, 255), new Position2D(0, 0),
      25);

  AnimatedShape greenTriangle = new AnimatedShapeImpl(Shapes.Triangle, "Green Triangle", 10,
      new Dimension(5, 10), new Color(0, 255, 0), new Position2D(-15, -15),
      0);

  //Tests that getWidth works as expected
  @Test
  public void testGetWidth() {
    assertEquals(5, this.environment1.getWidth());
    assertEquals(15, this.environment2.getWidth());
  }

  //Tests that getHeight works as expected
  @Test
  public void testGetHeight() {
    assertEquals(5, this.environment1.getHeight());
    assertEquals(10, this.environment2.getHeight());
  }


  //Tests that addShape and getShapes works as expected when adding valid shapes
  @Test
  public void testAddShapesGetShapes() {
    ArrayList<ReadOnlyAnimatedShape> environmentShapes = new ArrayList();

    assertEquals(environmentShapes, this.environment1.getShapes());

    environment1.addShape(this.redRect);

    assertEquals(environmentShapes, this.environment2.getShapes());
    assertNotEquals(environmentShapes, this.environment1.getShapes());
    environmentShapes.add(new ReadOnlyAnimatedShapeImpl(this.redRect));
    assertEquals(environmentShapes, this.environment1.getShapes());

    environment1.addShape(this.blueCircle);
    assertNotEquals(environmentShapes, this.environment1.getShapes());
    environmentShapes.add(new ReadOnlyAnimatedShapeImpl(this.blueCircle));
    assertEquals(environmentShapes, this.environment1.getShapes());

    environment1.addShape(this.greenTriangle);
    assertNotEquals(environmentShapes, this.environment1.getShapes());
    environmentShapes.add(new ReadOnlyAnimatedShapeImpl(this.greenTriangle));
    assertEquals(environmentShapes, this.environment1.getShapes());
  }

  //Tests that commandShape works as expected.
  @Test
  public void testCommandShapesAndTextAnimation() {
    ArrayList<ReadOnlyAnimatedShape> environmentShapes = new ArrayList();

    assertEquals(environmentShapes, this.environment1.getShapes());

    this.environment1.addShape(this.redRect);
    environmentShapes.add(new ReadOnlyAnimatedShapeImpl(this.redRect));

    assertTrue(
        new ReadOnlyAnimatedShapeImpl(this.redRect).equals(this.environment1.getShapes().get(0)));
    assertEquals(environmentShapes, this.environment1.getShapes());

    this.environment1.commandShape("Red Rectangle", 0, 5,
        new Move(20, 25, 0, 0),
        new Scale(7, 5, 10, 10));

    environmentShapes.set(0,
        new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 5,
            new Dimension(10, 10), new Color(255, 0, 0), new Position2D(0, 0),
            0)));

    assertEquals(environmentShapes, this.environment1.getShapes());

    this.environment1.commandShape("Red Rectangle", 7, 10,
        new Move(0, 0, 5, 5),
        new Paint(255, 0, 0, 0, 0, 0),
        new Rotate(0, 25));

    environmentShapes.set(0,
        new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 10,
            new Dimension(10, 10), new Color(0, 0, 0), new Position2D(5, 5),
            25)));

    assertEquals(environmentShapes, this.environment1.getShapes());

    this.environment1.addShape(this.greenTriangle);
    environmentShapes.add(new ReadOnlyAnimatedShapeImpl(this.greenTriangle));

    assertEquals(environmentShapes, this.environment1.getShapes());

    this.environment1.commandShape("Green Triangle", 11, 15,
        new Move(-15, -15, 0, 0));

    environmentShapes.set(1,
        new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Triangle, "Green Triangle", 15,
            new Dimension(5, 10), new Color(0, 255, 0), new Position2D(0, 0),
            0)));

    assertEquals(environmentShapes, this.environment1.getShapes());
  }

  //Tests that an environment with a negative width is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEnvironment1() {
    AnimationEnvironment invalidEnvironment =
        AnimationEnvironmentImpl.builder().setBounds(0, 0, -1, 3).build();
  }

  //Tests that an environment with a negative height is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEnvironment2() {
    AnimationEnvironment invalidEnvironment = AnimationEnvironment.builder()
        .setBounds(10, 10, 1, -3).build();
  }

  //Tests that an environment with a 0 dimension is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEnvironment3() {
    AnimationEnvironment invalidEnvironment = AnimationEnvironment.builder().setBounds(10, 10, 0, 3)
        .build();
  }

  //Tests that an environment with invalid dimensions is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEnvironment4() {
    AnimationEnvironment invalidEnvironment = AnimationEnvironment.builder()
        .setBounds(10, 10, -1, 0).build();
  }

  //Tests that a null name command is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testNullNameCommand() {
    this.environment1.commandShape(null, 10, 20,
        new Move(20, 25, 10, 13));
  }

  //Tests that a command with a shape name not in the shapes in the environment is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeName() {
    this.environment1.commandShape("Green Rectangle", 10, 20,
        new Rotate(0, 25));
  }

  //Tests that a negative initial time for a command is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testWrongInitialTime() {
    this.environment1.commandShape("Green Triangle", -11, 20,
        new Scale(5, 10, 1, 2));
  }

  //Tests that a command with a negative final time is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeTime() {
    this.environment1.commandShape("Green Triangle", 10, -20,
        new Paint(0, 255, 0, 255, 0, 0));
  }

  //Tests that a command with a final time less than initial time is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testFinalTimeLessThanInitial() {
    this.environment1.commandShape("Green Triangle", 10, 2,
        new Move(-15, -15, 10, 10),
        new Scale(5, 10, 12, 12));
  }

  //Tests that a command with a wrong initial position is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testWrongInitialPosition() {
    this.environment1.commandShape("Green Triangle", 10, 20,
        new Move(0, 0, 10, 10));
  }

  //Tests that a command with several commands before an invalid command is still invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommandEventually() {
    this.environment1.commandShape("Green Triangle", 30, 40,
        new Scale(5, 10, 15, 15),
        new Move(-15, -15, 10, 10),
        new Paint(0, 0, 0, 0, 155, 0));
  }

  //Tests that having two commands that change the same animation aspect,
  // but the second one doesn't match up with the first in order to overwrite it is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidSynchronousCommands() {
    this.environment1.commandShape("Green Triangle", 20, 24,
        new Move(-15, -15, 10, 10),
        new Move(11, 11, 0, 0));
  }

  //Tests that a command with a wrong initial dimension is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testWrongInitialDimension() {
    this.environment1.commandShape("Green Triangle", 10, 20,
        new Scale(34, 33, 20, 20));
  }

  //Tests that a command with a 0 final dimension is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testZeroFinalDimension() {
    this.environment1.commandShape("Green Triangle", 10, 20,
        new Scale(5, 10, 0, 0));
  }

  //Tests that a command with a negative final dimension is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeFinalDimension() {
    this.environment1.commandShape("Green Triangle", 10, 20,
        new Scale(5, 10, 5, -3));
  }

  //Tests that a command with a negative rotation is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testNegativeRotation() {
    this.environment1.commandShape("Green Triangle", 10, 20,
        new Rotate(0, -10));
  }

  //Tests that giving a command that is within an existing command time slot is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingCommand1() {
    this.environment1.commandShape("Green Triangle", 10, 20,
        new Move(-15, -15, 20, 20));

    this.environment1.commandShape("Green Triangle", 13, 15,
        new Scale(5, 10, 5, 5));
  }

  //Tests that giving a command that begins during an existing command time slot is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingCommand2() {
    this.environment1.commandShape("Green Triangle", 10, 20,
        new Paint(0, 255, 0, 255, 0, 0));

    this.environment1.commandShape("Green Triangle", 15, 25,
        new Move(-15, -15, 0, 0));
  }

  //Tests that giving a command that ends during an existing command time slot is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingCommand3() {
    this.environment1.commandShape("Green Triangle", 10, 20,
        new Move(-15, -15, 0, 0));

    this.environment1.commandShape("Green Triangle", 5, 15,
        new Scale(5, 10, 5, 5));
  }

  //Tests that giving a command that encapsulates an existing command time slot is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testOverlappingCommand4() {
    this.environment1.commandShape("Green Triangle", 10, 20,
        new Move(-15, -15, 0, 0));
    this.environment1.commandShape("Green Triangle", 5, 25,
        new Paint(0, 255, 0, 50, 50, 50));
  }

  //Tests that getting the x coordinate of the top left corner of the environment works
  @Test
  public void testGetX() {
    assertEquals(0, this.environment1.getX());
    assertEquals(5, this.environment2.getX());
  }

  //Tests that getting the y coordinate of the top left corner of the environment works
  @Test
  public void testGetY() {
    assertEquals(0, this.environment1.getY());
    assertEquals(-5, this.environment2.getY());
  }

  //Tests that the build method returns a default environment with the top left corner at (0, 0)
  //and a height and width of 100x100
  @Test
  public void testDefaultBuild() {
    AnimationEnvironment environmentTest = AnimationEnvironment.builder().build();
    assertEquals(0, environmentTest.getX());
    assertEquals(0, environmentTest.getY());
    assertEquals(100, environmentTest.getWidth());
    assertEquals(100, environmentTest.getHeight());
  }

  //Tests that the set bounds method correctly sets the bounds
  @Test
  public void testSetBounds() {
    AnimationEnvironment environmentTest = AnimationEnvironment.builder().setBounds(5, 7, 20, 25)
        .build();
    assertEquals(5, environmentTest.getX());
    assertEquals(7, environmentTest.getY());
    assertEquals(20, environmentTest.getWidth());
    assertEquals(25, environmentTest.getHeight());
  }

  //Tests that declaring a shape and then adding the shape adds the shape as well as the motion
  @Test
  public void testMotionBuilder() {
    AnimationEnvironment environmentTest =
        AnimationEnvironment.builder().declareShape("Green Triangle", "Triangle")
            .addMotion("Green Triangle", 0, 0, 0, 5, 10, 0, 255, 0,
                10, 30, 30, 10, 20, 0, 255, 0).build();

    assertEquals(
        new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Triangle, "Green Triangle",
            10, new Dimension(10, 20), new Color(0, 255, 0), new Position2D(30, 30), 0)),
        environmentTest.getShapes().get(0));

    ArrayList<ReadOnlyAnimatedShape> testAnimationHistory = new ArrayList();

    testAnimationHistory
        .add(new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Triangle, "Green Triangle",
            0, new Dimension(5, 10), new Color(0, 255, 0), new Position2D(0, 0), 0)));

    testAnimationHistory
        .add(new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Triangle, "Green Triangle",
            10, new Dimension(10, 20), new Color(0, 255, 0), new Position2D(30, 30), 0)));

    assertEquals(testAnimationHistory, environmentTest.getShapes().get(0).getLog());
  }

  //Tests that addKeyFrame adds a "Snapshot" of the current shape state
  @Test
  public void testKeyFrame() {

    AnimationEnvironment testEnvironment = AnimationEnvironment.builder()
        .declareShape("testing shape", "rectangle")
        .addKeyframe("testing shape", 5, 0, 0, 10, 10, 255, 0, 0).build();

    assertEquals(
        new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle, "testing shape",
            5, new Dimension(10, 10), new Color(255, 0, 0), new Position2D(0, 0),
            0)), testEnvironment.getShapes().get(0));

    ArrayList<ReadOnlyAnimatedShape> testLog = new ArrayList();

    testLog
        .add(new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle, "testing shape",
            5, new Dimension(10, 10), new Color(255, 0, 0), new Position2D(0, 0),
            0)));

    assertEquals(testLog, testEnvironment.getShapes().get(0).getLog());
  }
}