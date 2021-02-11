import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import model.AnimatedShape;
import model.AnimatedShapeImpl;
import model.KeyframeAnimatedShapeImpl;
import model.Position2D;
import model.ReadOnlyAnimatedShape;
import model.ReadOnlyAnimatedShapeImpl;
import model.ReadOnlyKeyframeShape;
import model.Shapes;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

/**
 * Tests that ReadOnlyKeyframeShape provides an accurate read-only version of KeyframeAnimatedShape.
 */
public class TestReadOnlyKeyframeShape {

  ReadOnlyAnimatedShape roKeyframeShape1 = new ReadOnlyKeyframeShape(new KeyframeAnimatedShapeImpl(
      Shapes.Rectangle, "Red Rectangle", 0,
      new Dimension(7, 5), new Color(255, 0, 0),
      new Position2D(20, 25), 0));


  ReadOnlyAnimatedShape roKeyframeShape2 = new ReadOnlyKeyframeShape(new KeyframeAnimatedShapeImpl(
      Shapes.Oval, "Blue Circle", 3,
      new Dimension(3, 3), new Color(0, 0, 255),
      new Position2D(0, 0), 55));

  //Tests that getTime accurately returns the time stored in a read-only keyframe shape
  @Test
  public void testGetTime() {
    assertEquals(0, this.roKeyframeShape1.getTime());
    assertEquals(3, this.roKeyframeShape2.getTime());
  }

  //Tests that getName accurately returns the name stored in a read-0nly keyframe shape
  @Test
  public void testGetName() {
    assertEquals("Red Rectangle", this.roKeyframeShape1.getName());
    assertEquals("Blue Circle", this.roKeyframeShape2.getName());
  }

  //Tests that getBoundary accurately returns the boundary stored in a read-only keyframe shape
  @Test
  public void testGetBoundary() {
    assertEquals(new Dimension(7, 5), this.roKeyframeShape1.getBoundary());
    assertEquals(new Dimension(3, 3), this.roKeyframeShape2.getBoundary());
  }

  //Tests that getColor accurately returns the color stored in a read-only keyframe shape
  @Test
  public void testGetColor() {
    assertEquals(new Color(255, 0, 0), this.roKeyframeShape1.getBoundary());
    assertEquals(new Color(0, 0, 255), this.roKeyframeShape2.getBoundary());
  }

  //Tests that getPosn accurately returns the position stored in a read-only keyframe shape
  @Test
  public void testGetPosn() {
    assertEquals(new Position2D(20, 25), this.roKeyframeShape1.getPosn());
    assertEquals(new Position2D(0, 0), this.roKeyframeShape2.getPosn());
  }

  //Tests that getLog accurately returns the log stored in a read-only keyframe shape
  @Test
  public void testGetLog() {
    ArrayList<ReadOnlyKeyframeShape> testLog = new ArrayList();

    assertEquals(testLog, this.roKeyframeShape2.getLog());

    AnimatedShape testShape = new AnimatedShapeImpl(Shapes.Rectangle, "testing shape", 0,
        new Dimension(5, 5), new Color(255, 0, 0), new Position2D(0, 0),
        0);

    testShape.log();
    testShape.setTime(5);
    testShape.changeColor(new Color(0, 150, 0));
    testShape.move(new Position2D(50, 50));
    testShape.log();

    ReadOnlyAnimatedShape testReadOnlyShape = new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(new ReadOnlyAnimatedShapeImpl(testShape)));


    testLog.add(new ReadOnlyKeyframeShape(new KeyframeAnimatedShapeImpl(Shapes.Rectangle,
        "testing shape",
        0, new Dimension(5, 5), new Color(255, 0, 0),
        new Position2D(0, 0), 0)) );

    testLog.add(new ReadOnlyKeyframeShape(new KeyframeAnimatedShapeImpl(Shapes.Rectangle,
        "testing shape",
        5, new Dimension(5, 5), new Color(0, 150, 0),
        new Position2D(50, 50), 0)));

    assertEquals(testLog, testShape.getLog());

  }

  //Tests that getOrientation accurately returns the orientation of a read-only keyframe shape
  @Test
  public void testGetOrientation() {
    ReadOnlyAnimatedShape testReadOnlyShape = new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(new ReadOnlyAnimatedShapeImpl(
            new AnimatedShapeImpl(Shapes.Rectangle, "testing shape",
            5, new Dimension(5, 5), new Color(0, 150, 0),
                new Position2D(50, 50), 50))));
    assertEquals(50.5, testReadOnlyShape.getOrientation(), .01);
    assertEquals(0.0, this.roKeyframeShape1.getOrientation(), .01);
    assertEquals(0.0, this.roKeyframeShape2.getOrientation(), .01);
  }



  //Tests that getShapeType accurately returns the type of shape of a read-only keyframe shape
  @Test
  public void testGetShapeType() {
    assertEquals(Shapes.Rectangle, this.roKeyframeShape1.getShapeType());
    assertEquals(Shapes.Oval, this.roKeyframeShape2.getShapeType());
  }


  //Tests that a read-only keyframe shape is equal to another keyframe shape if they have the same
  //Time, Boundary, color, position, orientation, and shapeType
  @Test
  public void testEqualsAndHash() {

    assertFalse(this.roKeyframeShape2.equals(this.roKeyframeShape1));
    assertNotEquals(this.roKeyframeShape2.hashCode(), this.roKeyframeShape1.hashCode());

    ReadOnlyAnimatedShape testShape = new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "shape name testing", 0,
            new Dimension(7, 5), new Color(255, 0, 0),
            new Position2D(20, 25), 0));

    assertTrue(this.roKeyframeShape1.equals(testShape));
    assertEquals(testShape.hashCode(), this.roKeyframeShape1.hashCode());


  }








}
