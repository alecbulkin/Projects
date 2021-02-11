import model.AnimatedShapeImpl;
import model.AnimatedShape;
import model.AnimationEnvironment;
import model.AnimationEnvironmentImpl;
import model.Position2D;
import model.ReadOnlyAnimatedShape;
import model.ReadOnlyAnimatedShapeImpl;
import model.ReadOnlyAnimationEnvironment;
import model.ReadOnlyAnimationEnvironmentImpl;
import model.Shapes;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests that {@code ReadOnlyAnimationEnvironmentImpl} works as expected and effectively emulates a
 * read-only version of {@code AnimationEnvironment}.
 */
public class TestReadOnlyAnimationEnvironment {

  AnimationEnvironment environment1 =
      AnimationEnvironmentImpl.builder().setBounds(0, 0, 5, 5).build();

  ReadOnlyAnimationEnvironment roEnv1 = new ReadOnlyAnimationEnvironmentImpl(environment1);

  AnimationEnvironment environment2 =
      AnimationEnvironmentImpl.builder().setBounds(5, -5, 15, 10).build();

  ReadOnlyAnimationEnvironment roEnv2 = new ReadOnlyAnimationEnvironmentImpl(environment2);

  AnimatedShape redRect = new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 0,
      new Dimension(7, 5), new Color(255, 0, 0), new Position2D(20, 25),
      0);

  AnimatedShape blueCircle = new AnimatedShapeImpl(Shapes.Oval, "Blue Circle", 3,
      new Dimension(3, 3), new Color(0, 0, 255), new Position2D(0, 0),
      25);

  AnimatedShape greenTriangle = new AnimatedShapeImpl(Shapes.Triangle, "Green Triangle", 10,
      new Dimension(5, 10), new Color(0, 255, 0), new Position2D(-15, -15),
      0);

  //Tests that the getX method returns the X coordinate of the top left corner of the
  //underlying AnimationEnvironment
  @Test
  public void testGetX() {
    assertEquals(0, this.roEnv1.getX());
    assertEquals(5, this.roEnv2.getX());
  }

  //Tests that the getX method returns the Y coordinate of the top left corner of the
  //underlying AnimationEnvironment
  @Test
  public void testGetY() {
    assertEquals(0, this.roEnv1.getY());
    assertEquals(-5, this.roEnv2.getY());
  }

  //Tests that the getWidth method returns the width of the underlying AnimationEnvironment
  @Test
  public void testGetWidth() {
    assertEquals(5, this.roEnv1.getWidth());
    assertEquals(15, this.roEnv2.getWidth());
  }

  //Tests that the getHeight method returns the height of the underlying AnimationEnvironment
  @Test
  public void testGetHeight() {
    assertEquals(5, this.roEnv1.getHeight());
    assertEquals(10, this.roEnv2.getHeight());
  }

  //Tests that the getShapes method gets the shape of the underlying AnimationEnvironment
  @Test
  public void testAddShapesGetShapes() {
    ArrayList<ReadOnlyAnimatedShape> environmentShapes = new ArrayList();

    assertEquals(environmentShapes, this.roEnv1.getShapes());

    environment1.addShape(this.redRect);

    assertEquals(environmentShapes, this.roEnv2.getShapes());
    assertNotEquals(environmentShapes, this.roEnv1.getShapes());
    environmentShapes.add(new ReadOnlyAnimatedShapeImpl(this.redRect));
    assertEquals(environmentShapes, this.roEnv1.getShapes());

    environment1.addShape(this.blueCircle);
    assertNotEquals(environmentShapes, this.roEnv1.getShapes());
    environmentShapes.add(new ReadOnlyAnimatedShapeImpl(this.blueCircle));
    assertEquals(environmentShapes, this.roEnv1.getShapes());

    environment1.addShape(this.greenTriangle);
    assertNotEquals(environmentShapes, this.roEnv1.getShapes());
    environmentShapes.add(new ReadOnlyAnimatedShapeImpl(this.greenTriangle));
    assertEquals(environmentShapes, this.roEnv1.getShapes());
  }

  //Tests that the equals and hashCode methods function as they should
  @Test
  public void testEqualsAndHash() {
    assertNotEquals(roEnv1, roEnv2);
    assertNotEquals(roEnv1.hashCode(), roEnv2.hashCode());

    AnimationEnvironment environment3 =
        AnimationEnvironmentImpl.builder().setBounds(0, 0, 5, 5).build();

    ReadOnlyAnimationEnvironment roEnv3 = new ReadOnlyAnimationEnvironmentImpl(environment3);

    assertEquals(roEnv1, roEnv3);
    assertEquals(roEnv1.hashCode(), roEnv3.hashCode());

    environment1.addShape(redRect);

    assertNotEquals(roEnv1, roEnv3);
    assertNotEquals(roEnv1.hashCode(), roEnv3.hashCode());

    environment3.addShape(redRect);

    assertEquals(roEnv1, roEnv3);
    assertEquals(roEnv1.hashCode(), roEnv3.hashCode());
  }
}
