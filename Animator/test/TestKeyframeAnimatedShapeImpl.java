import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import model.AnimatedShape;
import model.AnimatedShapeImpl;
import model.KeyframeAnimatedShape;
import model.KeyframeAnimatedShapeImpl;
import model.Position2D;
import model.ReadOnlyAnimatedShape;
import model.ReadOnlyAnimatedShapeImpl;
import model.ReadOnlyKeyframeShape;
import model.Shapes;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Tests that the {@code KeyframeAnimatedShapeImpl} implementation accurately represents an {@code
 * AnimatedShape} with keyframes. Here We only tests the methods that were overriden in the
 * KeyframeAnimatedShapeImpl class, because we have already fully tested the methods that aren't
 * overriden in the AnimatedShapeImpl class.
 */
public class TestKeyframeAnimatedShapeImpl {

  KeyframeAnimatedShape redRect = new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle",
      0,
      new Dimension(7, 5), new Color(255, 0, 0), new Position2D(20, 25), 0);

  KeyframeAnimatedShape blueCircle = new KeyframeAnimatedShapeImpl(Shapes.Oval, "Blue Circle", 3,
      new Dimension(3, 3), new Color(0, 0, 255), new Position2D(0, 0), 0);

  KeyframeAnimatedShape greenTriangle = new KeyframeAnimatedShapeImpl(Shapes.Triangle,
      "Green Triangle", 10,
      new Dimension(5, 10), new Color(0, 255, 0), new Position2D(-15, -15), 0);

  //Tests that a shape type of null is invalid.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType1() {
    AnimatedShape testShape = new KeyframeAnimatedShapeImpl(null, "test Name", 3,
        new Dimension(4, 4), new Color(0, 0, 0), new Position2D(0, 0), 0);
  }

  //Tests that a null name is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType2() {
    AnimatedShape testShape = new KeyframeAnimatedShapeImpl(Shapes.Triangle, null, 3,
        new Dimension(4, 4), new Color(0, 0, 0), new Position2D(0, 0), 0);
  }

  //Tests that a negative time is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType3() {
    AnimatedShape testShape = new KeyframeAnimatedShapeImpl(Shapes.Triangle, "test Name", -1,
        new Dimension(4, 4), new Color(0, 0, 0), new Position2D(0, 0), 0);
  }

  //Tests that a null boundary is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType4() {
    AnimatedShape testShape = new KeyframeAnimatedShapeImpl(Shapes.Oval, "test Name", 3,
        null, new Color(0, 0, 0), new Position2D(0, 0), 0);
  }

  //Tests that a boundary with dimensions of 0 is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType5() {
    AnimatedShape testShape = new KeyframeAnimatedShapeImpl(Shapes.Triangle, "test Name", 3,
        new Dimension(0, 4), new Color(0, 0, 0), new Position2D(0, 0), 0);
  }

  //Tests that a boundary with any negative dimensions is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType6() {
    AnimatedShape testShape = new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "test Name", 3,
        new Dimension(4, -2), new Color(0, 0, 0), new Position2D(0, 0), 0);
  }

  //Tests that a boundary with any negative dimensions is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType7() {
    AnimatedShape testShape = new KeyframeAnimatedShapeImpl(Shapes.Triangle, "test Name", 3,
        new Dimension(-4, 2), new Color(0, 0, 0), new Position2D(0, 0), 0);
  }

  //Tests that a null color is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType8() {
    AnimatedShape testShape = new KeyframeAnimatedShapeImpl(Shapes.Triangle, "test Name", 3,
        new Dimension(4, 2), null, new Position2D(0, 0), 0);
  }

  //Tests that a null position is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType9() {
    AnimatedShape testShape = new KeyframeAnimatedShapeImpl(Shapes.Triangle, "test Name", 3,
        new Dimension(4, 2), new Color(0, 0, 0), null, 0);
  }


  //Tests that the constructor that takes a ReadOnlyAnimatedShape effectively emulates the given
  //shape
  @Test
  public void testReadOnlyConstructor() {
    ReadOnlyAnimatedShape roAnimatedShape = new ReadOnlyAnimatedShapeImpl(
        new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 0,
            new Dimension(7, 5), new Color(255, 0, 0),
            new Position2D(20, 25), 0));

    ReadOnlyAnimatedShape roKeyframeShape = new ReadOnlyKeyframeShape(this.blueCircle);
    KeyframeAnimatedShape newShape = new KeyframeAnimatedShapeImpl(roAnimatedShape);

    assertEquals("Red Rectangle", newShape.getName());
    assertEquals(Shapes.Rectangle, newShape.getShapeType());
    assertEquals(0, newShape.getTime());
    assertEquals(new Dimension(7, 5), newShape.getBoundary());
    assertEquals(new Color(255, 0, 0), newShape.getColor());
    assertEquals(new Position2D(20, 25), newShape.getPosn());
    assertEquals(0.0, newShape.getOrientation(), .01);

    newShape = new KeyframeAnimatedShapeImpl(roKeyframeShape);

    assertEquals("Blue Circle", newShape.getName());
    assertEquals(Shapes.Oval, newShape.getShapeType());
    assertEquals(3, newShape.getTime());
    assertEquals(new Dimension(3, 3), newShape.getBoundary());
    assertEquals(new Color(0, 0, 255), newShape.getColor());
    assertEquals(new Position2D(0, 0), newShape.getPosn());
    assertEquals(0.0, newShape.getOrientation(), .01);
  }


  //Tests that getLog and log work in unison with the idea of keyframes rather than motions
  @Test
  public void testLogging() {

    ArrayList<ReadOnlyAnimatedShape> testLog = new ArrayList();
    assertEquals(testLog, this.redRect.getLog());

    this.redRect.log();
    testLog.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 0,
            new Dimension(7, 5), new Color(255, 0, 0), new Position2D(20, 25), 0)));

    for (int i = 0; i < this.redRect.getLog().size(); i++) {
      assertTrue(testLog.get(0).equals(new ReadOnlyKeyframeShape(this.redRect)));
      assertEquals(testLog.get(i), this.redRect.getLog().get(i));
    }

    this.redRect.setTime(10);
    this.redRect.log();
    testLog.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 10,
            new Dimension(7, 5), new Color(255, 0, 0), new Position2D(20, 25), 0)));

    for (int i = 0; i < this.redRect.getLog().size(); i++) {
      assertEquals(testLog.get(i), this.redRect.getLog().get(i));
    }

    this.redRect.setTime(5);
    this.redRect.log();

    testLog.remove(1);
    testLog.add(new ReadOnlyKeyframeShape(new KeyframeAnimatedShapeImpl(Shapes.Rectangle,
        "Red Rectangle", 5,
        new Dimension(7, 5), new Color(255, 0, 0),
        new Position2D(20, 25), 0)));
    testLog.add(new ReadOnlyKeyframeShape(new KeyframeAnimatedShapeImpl(Shapes.Rectangle,
        "Red Rectangle", 10, new Dimension(7, 5),
        new Color(255, 0, 0), new Position2D(20, 25), 0)));

    assertEquals(testLog, this.redRect.getLog());
  }


  //Tests that delete keyframe deletes the correct keyframe and does nothing if there is no
  //existing keyframe for the given time.
  @Test
  public void testDeleteKeyframe() {
    ArrayList<ReadOnlyAnimatedShape> testLog = new ArrayList();
    assertEquals(testLog, this.redRect.getLog());
    this.redRect.deleteKeyframe(5);
    assertEquals(testLog, this.redRect.getLog());
    this.redRect.log();
    testLog.add(new ReadOnlyKeyframeShape(new KeyframeAnimatedShapeImpl(Shapes.Rectangle,
        "Red Rectangle", 0,
        new Dimension(7, 5), new Color(255, 0, 0),
        new Position2D(20, 25), 0)));
    assertEquals(testLog, this.redRect.getLog());
    this.redRect.deleteKeyframe(5);
    assertEquals(testLog, this.redRect.getLog());
    this.redRect.deleteKeyframe(0);
    testLog.remove(0);
    assertEquals(testLog, this.redRect.getLog());
  }
}
