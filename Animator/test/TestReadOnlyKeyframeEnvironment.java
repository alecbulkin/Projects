import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import model.AnimationEnvironmentImpl;
import model.KeyframeAnimatedShapeImpl;
import model.KeyframeAnimationEnvironment;
import model.KeyframeAnimationEnvironmentImpl;
import model.Position2D;
import model.ReadOnlyAnimatedShape;
import model.ReadOnlyKeyframeEnvironment;
import model.ReadOnlyKeyframeShape;
import model.Shapes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests that a ReadOnlyKeyframeEnvironment correctly models a read-only version of a
 * KeyframeEnvironment. Tests confirm that the class provides accurate read-only access to a
 * KeyframeEnvironment.
 */
public class TestReadOnlyKeyframeEnvironment {

  ReadOnlyKeyframeEnvironment roEnvironment1 = new ReadOnlyKeyframeEnvironment(
      new KeyframeAnimationEnvironmentImpl(
          AnimationEnvironmentImpl.builder().setBounds(0, 0, 5, 5).build()));

  ReadOnlyKeyframeEnvironment roEnvironment2 = new ReadOnlyKeyframeEnvironment(
      new KeyframeAnimationEnvironmentImpl(
      AnimationEnvironmentImpl.builder().setBounds(5, -5, 15, 10).build()));


  //Tests that getHeight accurately returns the height of the keyframe environment stored in a
  //read-only keyframe environment
  @Test
  public void testGetHeight() {
    assertEquals(5, this.roEnvironment1.getHeight());
    assertEquals(10, this.roEnvironment2.getHeight());
  }

  //Tests that getWidth accurately returns the width of the keyframe environment stored in a
  //read-only keyframe environment
  @Test
  public void testGetWith() {
    assertEquals(5, this.roEnvironment1.getWidth());
    assertEquals(15, this.roEnvironment2.getWidth());
  }


  //Tests that getX accurately returns the x coordinate of the upper left corner of the keyframe
  //environment stored in a read-only keyframe environment
  @Test
  public void testGetX() {
    assertEquals(0, this.roEnvironment1.getX());
    assertEquals(5, this.roEnvironment2.getX());
  }

  //Tests that getY accurately returns the y coordinates of the upper left croner of the keyframe
  //environment stored in a read-only keyframe environment
  @Test
  public void testGetY() {
    assertEquals(0, this.roEnvironment1);
    assertEquals(-5, this.roEnvironment2);
  }


  //Tests that getShapes accurately returns the shapes in the keyframe environment stored in a
  //read-only keyframe environment
  @Test
  public void TestGetShapes() {
    ArrayList<ReadOnlyAnimatedShape> testShapes = new ArrayList();

    assertEquals(testShapes, this.roEnvironment1.getShapes());
    assertEquals(testShapes, this.roEnvironment2.getShapes());

    KeyframeAnimationEnvironment testEnvironment = new KeyframeAnimationEnvironmentImpl(
        AnimationEnvironmentImpl.builder().setBounds(5, -5, 15, 10).build());

    testEnvironment.addShape(Shapes.Rectangle, "Shape name", 5,
        new Dimension(5, 5),
        new Color(255, 0, 0), new Position2D(5, 5), 0);

    testEnvironment.addShape(Shapes.Oval, "Shape name2", 10,
        new Dimension(10, 10),
        new Color(0, 0, 0), new Position2D(20, 20), 0);

    testShapes.add(new ReadOnlyKeyframeShape(new KeyframeAnimatedShapeImpl(Shapes.Rectangle,
        "Shape name", 5, new Dimension(5, 5),
        new Color(255, 0, 0), new Position2D(5, 5), 0)));

    testShapes.add(new ReadOnlyKeyframeShape(new KeyframeAnimatedShapeImpl(Shapes.Rectangle,
        "Shape name", 5, new Dimension(5, 5),
        new Color(255, 0, 0), new Position2D(5, 5), 0)));

    assertEquals(testShapes, testEnvironment.getShapes());
  }
}
