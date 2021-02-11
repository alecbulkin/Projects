import org.junit.Test;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import model.AnimatedShape;
import model.AnimatedShapeImpl;
import model.Position2D;
import model.ReadOnlyAnimatedShape;
import model.ReadOnlyAnimatedShapeImpl;
import model.Shapes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Tests that {@code ReadOnlyAnimatedShapeImpl} works as expected and effectively emulates a
 * read-only version of {@code AnimatedShape}.
 */
public class TestReadOnlyAnimatedShape {

  AnimatedShape redRect = new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 0,
      new Dimension(7, 5), new Color(255, 0, 0), new Position2D(20, 25),
      0);

  AnimatedShape blueCircle = new AnimatedShapeImpl(Shapes.Oval, "Blue Circle", 3,
      new Dimension(3, 3), new Color(0, 0, 255), new Position2D(0, 0),
      25);

  AnimatedShape greenTriangle = new AnimatedShapeImpl(Shapes.Triangle, "Green Triangle", 10,
      new Dimension(5, 10), new Color(0, 255, 0), new Position2D(-15, -15),
      0);

  ReadOnlyAnimatedShape readableRedRect = new ReadOnlyAnimatedShapeImpl(this.redRect);

  ReadOnlyAnimatedShape readableBlueCircle = new ReadOnlyAnimatedShapeImpl(this.blueCircle);

  ReadOnlyAnimatedShape readableGreenTriangle = new ReadOnlyAnimatedShapeImpl(this.greenTriangle);


  //Tests that the getTime method returns the time of the underlying AnimatedShape model
  @Test
  public void testGetTime() {
    assertEquals(0, this.readableRedRect.getTime());
    assertEquals(10, this.readableGreenTriangle.getTime());
    assertEquals(3, this.readableBlueCircle.getTime());
    this.redRect.setTime(5);
    assertEquals(5, this.readableRedRect.getTime());
    this.greenTriangle.setTime(0);
    assertEquals(0, this.readableGreenTriangle.getTime());
  }

  //Tests the getName method returns teh name of the underlying AnimatedShape model
  @Test
  public void testGetName() {
    assertEquals("Red Rectangle", this.readableRedRect.getName());
    assertEquals("Green Triangle", this.readableGreenTriangle.getName());
    assertEquals("Blue Circle", this.readableBlueCircle.getName());
  }

  //Tests that the getBoundary returns the boundary of the underlying AnimatedShape model
  @Test
  public void testgetBoundary() {
    assertEquals(new Dimension(7, 5), this.readableRedRect.getBoundary());
    assertEquals(new Dimension(5, 10), this.readableGreenTriangle.getBoundary());
    assertEquals(new Dimension(3, 3), this.readableBlueCircle.getBoundary());

    this.redRect.scale(new Dimension(5, 5));
    this.greenTriangle.scale(new Dimension(5, 10));
    this.blueCircle.scale(new Dimension(1, 1));

    assertEquals(new Dimension(5, 5), this.readableRedRect.getBoundary());
    assertEquals(new Dimension(5, 10), this.readableGreenTriangle.getBoundary());
    assertEquals(new Dimension(1, 1), this.readableBlueCircle.getBoundary());
  }

  //Tests that the getColor method returns the color of the underlying AnimatedShape model
  @Test
  public void testColor() {
    assertEquals(new Color(255, 0, 0), this.readableRedRect.getColor());
    assertEquals(new Color(0, 255, 0), this.readableGreenTriangle.getColor());
    assertEquals(new Color(0, 0, 255), this.readableBlueCircle.getColor());
    this.redRect.changeColor(new Color(200, 34, 0));
    assertEquals(new Color(200, 34, 0), this.readableRedRect.getColor());
    this.greenTriangle.changeColor(new Color(0, 20, 35));
    assertEquals(new Color(0, 20, 35), this.readableGreenTriangle.getColor());
  }

  //Tests that move and getPosn works as expected
  @Test
  public void testGetPosn() {
    assertEquals(this.readableBlueCircle.getPosn(), new Position2D(0, 0));
    assertEquals(this.readableGreenTriangle.getPosn(), new Position2D(-15, -15));
    this.blueCircle.move(new Position2D(10, -5));
    assertEquals(this.readableBlueCircle.getPosn(), new Position2D(10, -5));
    this.greenTriangle.move(new Position2D(-2, 5));
    assertEquals(this.readableGreenTriangle.getPosn(), new Position2D(-2, 5));
    assertEquals(this.readableRedRect.getPosn(), new Position2D(20, 25));
    this.redRect.move(new Position2D(-10, -10));
    assertEquals(this.readableRedRect.getPosn(), new Position2D(-10, -10));
    this.redRect.move(new Position2D(50, 45));
    assertEquals(this.readableRedRect.getPosn(), new Position2D(50, 45));
    this.redRect.move(new Position2D(0, 0));
    assertEquals(this.readableRedRect.getPosn(), new Position2D(0, 0));
  }

  //Tests that the getLog method return the log of the underlying AnimatedShape model
  @Test
  public void testGetLog() {

    ArrayList<ReadOnlyAnimatedShape> testLog = new ArrayList();

    assertEquals(testLog, this.readableRedRect.getLog());
    assertEquals(testLog, this.readableBlueCircle.getLog());
    assertEquals(testLog, this.readableGreenTriangle.getLog());

    this.greenTriangle.log();
    testLog.add(new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Triangle,
        "Green Triangle", 10, new Dimension(5, 10),
        new Color(0, 255, 0), new Position2D(-15, -15), 0)));
    assertEquals(testLog, this.readableGreenTriangle.getLog());
    testLog.remove(0);

    this.redRect.setTime(5);
    this.redRect.move(new Position2D(5, 5));
    this.redRect.scale(new Dimension(5, 5));
    this.redRect.changeColor(new Color(0, 255, 0));
    this.redRect.setOrientation(100);
    //Tests that the log is empty before any states have been logged.
    assertEquals(testLog, this.readableRedRect.getLog());
    this.redRect.log();
    testLog.add(new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle,
        "Red Rectangle", 5, new Dimension(5, 5),
        new Color(0, 255, 0), new Position2D(5, 5), 100)));
    //Tests that the log is accurate with one past state stored.
    assertEquals(testLog, this.readableRedRect.getLog());

    this.redRect.setTime(7);
    this.redRect.move(new Position2D(0, 0));

    testLog.add(new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle,
        "Red Rectangle", 7, new Dimension(5, 5),
        new Color(0, 255, 0), new Position2D(0, 0), 100)));
    this.redRect.log();
    //Tests that the log is ordered and accurate with two past states stored.
    assertEquals(testLog, this.readableRedRect.getLog());

    this.redRect.setTime(6);
    this.redRect.move(new Position2D(10, 15));

    testLog.remove(1);
    testLog.add(new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle,
        "Red Rectangle", 6, new Dimension(5, 5),
        new Color(0, 255, 0), new Position2D(10, 15), 100)));
    testLog.add(new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle,
        "Red Rectangle", 7, new Dimension(5, 5),
        new Color(0, 255, 0), new Position2D(0, 0), 100)));
    this.redRect.log();

    //Tests that adding an intermediate time between two states works
    assertEquals(testLog, this.readableRedRect.getLog());
    testLog.remove(0);
    testLog.remove(0);
    testLog.remove(0);
    this.redRect.setTime(0);
    this.redRect.move(new Position2D(20, 20));
    testLog.add(new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle,
        "Red Rectangle", 0, new Dimension(5, 5),
        new Color(0, 255, 0), new Position2D(20, 20), 100)));
    testLog.add(new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle,
        "Red Rectangle", 5, new Dimension(5, 5),
        new Color(0, 255, 0), new Position2D(5, 5), 100)));
    testLog.add(new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle,
        "Red Rectangle", 6, new Dimension(5, 5),
        new Color(0, 255, 0), new Position2D(10, 15), 100)));
    testLog.add(new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Rectangle,
        "Red Rectangle", 7, new Dimension(5, 5),
        new Color(0, 255, 0), new Position2D(0, 0), 100)));
    this.redRect.log();
    //Tests that logging a time earlier than all of those previously logged works
    assertEquals(testLog, this.readableRedRect.getLog());

    this.blueCircle.move(new Position2D(10, -10));
    this.blueCircle.scale(new Dimension(100, 20));
    this.blueCircle.setOrientation(745);

    testLog.remove(0);
    testLog.remove(0);
    testLog.remove(0);
    testLog.remove(0);
    assertEquals(testLog, this.readableBlueCircle.getLog());
    testLog.add(new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(Shapes.Oval,
        "Blue Circle", 3, new Dimension(100, 20),
        new Color(0, 0, 255), new Position2D(10, -10), 50)));
    this.blueCircle.log();
    assertEquals(testLog, this.readableBlueCircle.getLog());
  }

  //Tests that the getOrientation returns the orientation of the underlying AnimatedShape model
  @Test
  public void testGetOrientation() {
    assertEquals(0.0, this.readableRedRect.getOrientation(), .01);
    assertEquals(0.0, this.readableGreenTriangle.getOrientation(), .01);
    assertEquals(25.5, this.readableBlueCircle.getOrientation(), .01);

    this.redRect.setOrientation(0);
    this.greenTriangle.setOrientation(150);
    this.blueCircle.setOrientation(375);

    assertEquals(0.0, this.readableRedRect.getOrientation(), .01);
    assertEquals(150.5, this.readableGreenTriangle.getOrientation(), .01);
    assertEquals(40.5, this.readableBlueCircle.getOrientation(), .01);

    this.redRect.setOrientation(360);

    assertEquals(0.0, this.readableRedRect.getOrientation(), .01);
  }

  @Test
  //Tests that the toString version returns the toString of the underlying AnimatedShape model
  public void testToString() {
    assertEquals("0 20.0 25.0 7.0 5.0 255 0 0 0.0", this.readableRedRect.toString());

    this.redRect.setOrientation(30);
    this.redRect.move(new Position2D(100, 100));
    this.redRect.changeColor(Color.cyan);

    assertEquals("0 100.0 100.0 7.0 5.0 0 255 255 30.0", this.readableRedRect.toString());
  }

  @Test
  //Tests that the equals and hashCode methods function as they should
  public void testEqualsAndHash() {
    ReadOnlyAnimatedShape copyReadableRedRect = new ReadOnlyAnimatedShapeImpl(new AnimatedShapeImpl(
        Shapes.Rectangle, "Red Rectangle", 0, new Dimension(7, 5),
        new Color(255, 0, 0), new Position2D(20, 25), 0));

    assertTrue(this.readableRedRect.equals(copyReadableRedRect));
    assertEquals(this.readableRedRect.hashCode(), copyReadableRedRect.hashCode());

    ReadOnlyAnimatedShape copyReadableBlueCircle = new ReadOnlyAnimatedShapeImpl(
        new AnimatedShapeImpl(Shapes.Oval, "Blue Circle",
            3, new Dimension(3, 3), new Color(0, 0, 255),
            new Position2D(0, 0), 25));

    assertTrue(this.readableBlueCircle.equals(copyReadableBlueCircle));
    assertEquals(this.readableBlueCircle.hashCode(), this.readableBlueCircle.hashCode());
    assertFalse(this.readableBlueCircle.equals(this.readableRedRect));
    assertFalse(this.readableBlueCircle.hashCode() == this.readableRedRect.hashCode());
    redRect.setTime(15);
    assertFalse(this.readableRedRect.equals(copyReadableRedRect));
    assertFalse(this.readableRedRect.hashCode() == copyReadableRedRect.hashCode());
  }
}