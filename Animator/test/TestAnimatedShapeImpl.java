import model.ReadOnlyAnimatedShape;
import model.ReadOnlyAnimatedShapeImpl;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import org.junit.Test;

import model.AnimatedShape;
import model.AnimatedShapeImpl;
import model.Shapes;
import model.Position2D;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;

/**
 * Tests that our {@code AnimatedShapeImpl} and all of it's methods work as expected.
 */
public class TestAnimatedShapeImpl {

  AnimatedShape redRect = new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 0,
      new Dimension(7, 5), new Color(255, 0, 0), new Position2D(20, 25),
      0);

  AnimatedShape blueCircle = new AnimatedShapeImpl(Shapes.Oval, "Blue Circle", 3,
      new Dimension(3, 3), new Color(0, 0, 255), new Position2D(0, 0),
      25);

  AnimatedShape greenTriangle = new AnimatedShapeImpl(Shapes.Triangle, "Green Triangle", 10,
      new Dimension(5, 10), new Color(0, 255, 0), new Position2D(-15, -15),
      0);

  //Tests that a shape type of null is invalid.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType1() {
    AnimatedShape testShape = new AnimatedShapeImpl(null, "test Name", 3,
        new Dimension(4, 4), new Color(0, 0, 0), new Position2D(0, 0),
        0);
  }

  //Tests that a null name is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType2() {
    AnimatedShape testShape = new AnimatedShapeImpl(Shapes.Triangle, null, 3,
        new Dimension(4, 4), new Color(0, 0, 0), new Position2D(0, 0),
        0);
  }

  //Tests that a negative time is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType3() {
    AnimatedShape testShape = new AnimatedShapeImpl(Shapes.Triangle, "test Name", -1,
        new Dimension(4, 4), new Color(0, 0, 0), new Position2D(0, 0),
        0);
  }

  //Tests that a null boundary is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType4() {
    AnimatedShape testShape = new AnimatedShapeImpl(Shapes.Oval, "test Name", 3,
        null, new Color(0, 0, 0), new Position2D(0, 0),
        0);
  }

  //Tests that a boundary with dimensions of 0 is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType5() {
    AnimatedShape testShape = new AnimatedShapeImpl(Shapes.Triangle, "test Name", 3,
        new Dimension(0, 4), new Color(0, 0, 0), new Position2D(0, 0),
        0);
  }

  //Tests that a boundary with any negative dimensions is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType6() {
    AnimatedShape testShape = new AnimatedShapeImpl(Shapes.Rectangle, "test Name", 3,
        new Dimension(4, -2), new Color(0, 0, 0), new Position2D(0, 0),
        0);
  }

  //Tests that a boundary with any negative dimensions is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType7() {
    AnimatedShape testShape = new AnimatedShapeImpl(Shapes.Triangle, "test Name", 3,
        new Dimension(-4, 2), new Color(0, 0, 0), new Position2D(0, 0),
        0);
  }

  //Tests that a null color is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType8() {
    AnimatedShape testShape = new AnimatedShapeImpl(Shapes.Triangle, "test Name", 3,
        new Dimension(4, 2), null, new Position2D(0, 0),
        0);
  }

  //Tests that a null position is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType9() {
    AnimatedShape testShape = new AnimatedShapeImpl(Shapes.Triangle, "test Name", 3,
        new Dimension(4, 2), new Color(0, 0, 0), null,
        0);
  }

  //Tests that a negative Orientation is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType10() {
    AnimatedShape testShape = new AnimatedShapeImpl(Shapes.Triangle, "test Name", 3,
        new Dimension(4, 2), new Color(0, 0, 0), new Position2D(0, 0),
        -7);
  }

  //Tests that an orientation of 360 is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType11() {
    AnimatedShape testShape = new AnimatedShapeImpl(Shapes.Triangle, "test Name", 3,
        new Dimension(4, 2), new Color(0, 0, 0), new Position2D(0, 0),
        360);
  }

  //Tests that an orientation greater than 360 is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeType12() {
    AnimatedShape testShape = new AnimatedShapeImpl(Shapes.Triangle, "test Name", 3,
        new Dimension(4, 2), new Color(0, 0, 0), new Position2D(0, 0),
        734);
  }

  //Tests that move and getPosn works as expected
  @Test
  public void testMove() {
    assertEquals(this.blueCircle.getPosn(), new Position2D(0, 0));
    assertEquals(this.greenTriangle.getPosn(), new Position2D(-15, -15));
    this.blueCircle.move(new Position2D(10, -5));
    assertEquals(this.blueCircle.getPosn(), new Position2D(10, -5));
    this.greenTriangle.move(new Position2D(-2, 5));
    assertEquals(this.greenTriangle.getPosn(), new Position2D(-2, 5));
    assertEquals(this.redRect.getPosn(), new Position2D(20, 25));
    this.redRect.move(new Position2D(-10, -10));
    assertEquals(this.redRect.getPosn(), new Position2D(-10, -10));
    this.redRect.move(new Position2D(50, 45));
    assertEquals(this.redRect.getPosn(), new Position2D(50, 45));
    this.redRect.move(new Position2D(0, 0));
    assertEquals(this.redRect.getPosn(), new Position2D(0, 0));
  }

  //Tests that setOrientation and getOrientation work as expected
  @Test
  public void testSetOrientation() {
    assertEquals(0.0, this.redRect.getOrientation(), .01);
    assertEquals(0.0, this.greenTriangle.getOrientation(), .01);
    assertEquals(25.5, this.blueCircle.getOrientation(), .01);

    this.redRect.setOrientation(0);
    this.greenTriangle.setOrientation(150);
    this.blueCircle.setOrientation(375);

    assertEquals(0.0, this.redRect.getOrientation(), .01);
    assertEquals(150.5, this.greenTriangle.getOrientation(), .01);
    assertEquals(40.5, this.blueCircle.getOrientation(), .01);

    this.redRect.setOrientation(360);

    assertEquals(0.0, this.redRect.getOrientation(), .01);
  }

  //Tests that rotating negative degrees is invalid
  @Test(expected = IllegalArgumentException.class)
  public void invalidRotation() {
    this.redRect.setOrientation(-15);
  }

  //Tests that changeColor and getColor work as expected
  @Test
  public void testColor() {
    assertEquals(new Color(255, 0, 0), this.redRect.getColor());
    assertEquals(new Color(0, 255, 0), this.greenTriangle.getColor());
    assertEquals(new Color(0, 0, 255), this.blueCircle.getColor());
    this.redRect.changeColor(new Color(200, 34, 0));
    assertEquals(new Color(200, 34, 0), this.redRect.getColor());
    this.greenTriangle.changeColor(new Color(0, 20, 35));
    assertEquals(new Color(0, 20, 35), this.greenTriangle.getColor());
  }

  //Tests that getTime and setTime work as expected
  @Test
  public void testTime() {
    assertEquals(0, this.redRect.getTime());
    assertEquals(10, this.greenTriangle.getTime());
    assertEquals(3, this.blueCircle.getTime());
    this.redRect.setTime(5);
    assertEquals(5, this.redRect.getTime());
    this.greenTriangle.setTime(0);
    assertEquals(0, this.greenTriangle.getTime());
  }

  //Tests that setting a negative time is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTime() {
    this.redRect.setTime(-5);
  }

  //Tests that that log and getLog work as expected
  @Test
  public void testLog() {

    ArrayList<ReadOnlyAnimatedShape> testLog = new ArrayList();

    assertEquals(testLog, this.redRect.getLog());
    assertEquals(testLog, this.blueCircle.getLog());
    assertEquals(testLog, this.greenTriangle.getLog());

    this.greenTriangle.log();
    testLog.add(
        new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Triangle, "Green Triangle", 10,
            new Dimension(5, 10), new Color(0, 255, 0), new Position2D(-15, -15),
            0)));
    assertEquals(testLog, this.greenTriangle.getLog());
    testLog.remove(0);

    this.redRect.setTime(5);
    this.redRect.move(new Position2D(5, 5));
    this.redRect.scale(new Dimension(5, 5));
    this.redRect.changeColor(new Color(0, 255, 0));
    this.redRect.setOrientation(100);
    //Tests that the log is empty before any states have been logged.
    assertEquals(testLog, this.redRect.getLog());
    this.redRect.log();
    testLog.add(
        new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 5,
            new Dimension(5, 5), new Color(0, 255, 0), new Position2D(5, 5),
            100)));
    //Tests that the log is accurate with one past state stored.
    assertEquals(testLog, this.redRect.getLog());

    this.redRect.setTime(7);
    this.redRect.move(new Position2D(0, 0));

    testLog.add(
        new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 7,
            new Dimension(5, 5), new Color(0, 255, 0), new Position2D(0, 0),
            100)));
    this.redRect.log();
    //Tests that the log is ordered and accurate with two past states stored.
    assertEquals(testLog, this.redRect.getLog());

    this.redRect.setTime(6);
    this.redRect.move(new Position2D(10, 15));

    testLog.remove(1);
    testLog.add(
        new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 6,
            new Dimension(5, 5), new Color(0, 255, 0), new Position2D(10, 15),
            100)));
    testLog.add(
        new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 7,
            new Dimension(5, 5), new Color(0, 255, 0), new Position2D(0, 0),
            100)));
    this.redRect.log();

    //Tests that adding an intermediate time between two states works
    assertEquals(testLog, this.redRect.getLog());
    testLog.remove(0);
    testLog.remove(0);
    testLog.remove(0);
    this.redRect.setTime(0);
    this.redRect.move(new Position2D(20, 20));
    testLog.add(
        new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 0,
            new Dimension(5, 5), new Color(0, 255, 0), new Position2D(20, 20),
            100)));
    testLog.add(
        new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 5,
            new Dimension(5, 5), new Color(0, 255, 0), new Position2D(5, 5),
            100)));
    testLog.add(
        new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 6,
            new Dimension(5, 5), new Color(0, 255, 0), new Position2D(10, 15),
            100)));
    testLog.add(
        new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 7,
            new Dimension(5, 5), new Color(0, 255, 0), new Position2D(0, 0),
            100)));
    this.redRect.log();
    //Tests that logging a time earlier than all of those previously logged works
    assertEquals(testLog, this.redRect.getLog());

    this.blueCircle.move(new Position2D(10, -10));
    this.blueCircle.scale(new Dimension(100, 20));
    this.blueCircle.setOrientation(45);

    testLog.remove(0);
    testLog.remove(0);
    testLog.remove(0);
    testLog.remove(0);
    assertEquals(testLog, this.blueCircle.getLog());
    testLog.add(new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Oval, "Blue Circle", 3,
        new Dimension(100, 20), new Color(0, 0, 255), new Position2D(10, -10),
        45)));
    this.blueCircle.log();
    assertEquals(testLog, this.blueCircle.getLog());
  }

  //Tests getName
  @Test
  public void testGetName() {
    assertEquals("Red Rectangle", this.redRect.getName());
    assertEquals("Green Triangle", this.greenTriangle.getName());
    assertEquals("Blue Circle", this.blueCircle.getName());
  }

  //Tests scale and getBoundary
  @Test
  public void testScale() {
    assertEquals(new Dimension(7, 5), this.redRect.getBoundary());
    assertEquals(new Dimension(5, 10), this.greenTriangle.getBoundary());
    assertEquals(new Dimension(3, 3), this.blueCircle.getBoundary());

    this.redRect.scale(new Dimension(5, 5));
    this.greenTriangle.scale(new Dimension(5, 10));
    this.blueCircle.scale(new Dimension(1, 1));

    assertEquals(new Dimension(5, 5), this.redRect.getBoundary());
    assertEquals(new Dimension(5, 10), this.greenTriangle.getBoundary());
    assertEquals(new Dimension(1, 1), this.blueCircle.getBoundary());
  }

  //Tests that getShapeType works as expected
  @Test
  public void testGetShapeType() {
    assertEquals(Shapes.Rectangle, this.redRect.getShapeType());
    assertEquals(Shapes.Oval, this.blueCircle.getShapeType());
    assertEquals(Shapes.Triangle, this.greenTriangle.getShapeType());
  }

  //Tests that equals and hashcode work as expected
  @Test
  public void testEqualsAndHash() {
    AnimatedShape copyRedRect = new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle copy", 0,
        new Dimension(7, 5), new Color(255, 0, 0), new Position2D(20, 25),
        0);

    assertTrue(this.redRect.equals(copyRedRect));
    assertEquals(this.redRect.hashCode(), copyRedRect.hashCode());

    AnimatedShape copyBlueCircle = new AnimatedShapeImpl(Shapes.Oval, "Blue Circle copy", 3,
        new Dimension(3, 3), new Color(0, 0, 255), new Position2D(0, 0),
        25);

    assertTrue(this.blueCircle.equals(copyBlueCircle));
    assertEquals(this.blueCircle.hashCode(), this.blueCircle.hashCode());
    assertFalse(this.blueCircle.equals(this.redRect));
    assertFalse(this.blueCircle.hashCode() == this.redRect.hashCode());
    copyRedRect.setTime(15);
    assertFalse(this.redRect.equals(copyRedRect));
    assertFalse(this.redRect.hashCode() == copyRedRect.hashCode());
    this.redRect.setTime(15);
    assertTrue(this.redRect.equals(copyRedRect));
    assertEquals(this.redRect.hashCode(), copyRedRect.hashCode());

    assertTrue(this.redRect.equals(this.redRect));
  }
}
