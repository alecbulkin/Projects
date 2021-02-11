import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import model.AnimationEnvironmentImpl;
import model.KeyframeAnimatedShapeImpl;
import model.KeyframeAnimationEnvironment;
import model.KeyframeAnimationEnvironmentImpl;
import model.Position2D;
import model.ReadOnlyAnimatedShape;
import model.ReadOnlyKeyframeShape;
import model.Shapes;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests that a KeyframeAnimationEnvironmentImpl correctly and accurately represents an
 * AnimationEnvironment with the idea of keyframes.
 */
public class TestKeyframeAnimationEnvironmentImpl {


  KeyframeAnimationEnvironment keyframeEnvironment = new KeyframeAnimationEnvironmentImpl(
      AnimationEnvironmentImpl.builder().setBounds(0, 0, 5, 5).build());

  KeyframeAnimationEnvironment keyframeEnvironment2 = new KeyframeAnimationEnvironmentImpl(
      AnimationEnvironmentImpl.builder().setBounds(5, -5, 15, 10).build());


  //Tests that getX returns the accurate x value for the top left corner of the environment.
  @Test
  public void testGetX() {
    assertEquals(0, this.keyframeEnvironment.getX());
    assertEquals(5, this.keyframeEnvironment2.getX());
  }

  //Tests that getY returns the accurate y value for the top left corner of the environment.
  @Test
  public void testGetY() {
    assertEquals(0, this.keyframeEnvironment.getY());
    assertEquals(-5, this.keyframeEnvironment2.getY());
  }

  //Tests that getHeight returns the accurate height value for the height of the environment.
  @Test
  public void testGetHeight() {
    assertEquals(5, this.keyframeEnvironment.getHeight());
    assertEquals(10, this.keyframeEnvironment2.getHeight());
  }

  //Tests that getWidth returns the accurate width value for the height of the environment.
  @Test
  public void testGetWidth() {
    assertEquals(5, this.keyframeEnvironment.getWidth());
    assertEquals(15, this.keyframeEnvironment2.getWidth());
  }


  //Tests that addShape accurately adds all variations of shapes
  @Test
  public void testAddShape() {

    ArrayList<ReadOnlyAnimatedShape> testShapes = new ArrayList();

    assertEquals(testShapes, this.keyframeEnvironment.getShapes());
    this.keyframeEnvironment
        .addShape(Shapes.Triangle, "Shape name", 5, new Dimension(5, 5), new Color(0, 255, 0),
            new Position2D(5, 10), 0);
    testShapes.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Triangle, "Shape name", 5, new Dimension(5, 5),
            new Color(0, 255, 0),
            new Position2D(5, 10), 0)));

    assertEquals(testShapes, this.keyframeEnvironment.getShapes());

    this.keyframeEnvironment
        .addShape(Shapes.Oval, "shape name2", 0, new Dimension(10, 10), new Color(5, 5, 5),
            new Position2D(0, -5), 0);
    testShapes.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Oval, "shape name2", 0, new Dimension(10, 10),
            new Color(5, 5, 5),
            new Position2D(0, -5), 0)));

    assertEquals(testShapes, this.keyframeEnvironment.getShapes());
  }


  //Tests that adding a null type shape is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddShape1() {
    this.keyframeEnvironment
        .addShape(null, "Shape name", 5, new Dimension(5, 5), new Color(0, 255, 0),
            new Position2D(5, 10), 0);
  }

  //Tests that adding a shape with a null name is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddShape2() {
    this.keyframeEnvironment
        .addShape(Shapes.Oval, null, 5, new Dimension(5, 5), new Color(0, 255, 0),
            new Position2D(5, 10), 0);
  }

  //Tests that adding a negative time shape is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddShape3() {
    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", -5, new Dimension(5, 5), new Color(0, 255, 0),
            new Position2D(5, 10), 0);
  }

  //Tests that adding a negative dimension shape is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddShape4() {
    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(-5, 5), new Color(0, 255, 0),
            new Position2D(5, 10), 0);
  }

  //Tests that adding a zero dimension shape is invalida
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddShape5() {
    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 0), new Color(0, 255, 0),
            new Position2D(5, 10), 0);
  }

  //Tests that adding a null dimension is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddShape6() {
    this.keyframeEnvironment.addShape(Shapes.Rectangle, "Shape name", 5, null, new Color(0, 255, 0),
        new Position2D(5, 10), 0);
  }

  //Tests that adding a null position is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddShape7() {
    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), new Color(0, 255, 0),
            null, 0);
  }

  //Tests that adding a null color is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidAddShape8() {
    this.keyframeEnvironment.addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), null,
        new Position2D(5, 10), 0);
  }


  //Tests that deleting a shape throws an error if there is no shape in the environment that
  //corresponds to the given name
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeName() {
    this.keyframeEnvironment.deleteShape("shape does not exist");
  }

  //Tests that deleting a shape throws an error if there is no shape in the environment that
  //corresponds to the given name. even when there are other shapes in the environment
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeName2() {
    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);
    this.keyframeEnvironment.deleteShape("shape does not exist");
  }


  //Tests that delete shape accurately deletes the shape that corresponds to the given name
  //in the environment
  @Test
  public void testDeleteShape() {
    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);

    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name2", 15, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);

    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name3", 51, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);

    ArrayList<ReadOnlyAnimatedShape> testShapes = new ArrayList();

    testShapes.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5),
            new Color(255, 0, 0),
            new Position2D(5, 10), 0)));

    testShapes.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name2", 15, new Dimension(5, 5),
            new Color(255, 0, 0),
            new Position2D(5, 10), 0)));

    testShapes.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name3", 51, new Dimension(5, 5),
            new Color(255, 0, 0),
            new Position2D(5, 10), 0)));

    assertEquals(testShapes, this.keyframeEnvironment.getShapes());

    testShapes.remove(2);
    this.keyframeEnvironment.deleteShape("Shape name3");
    assertEquals(testShapes, this.keyframeEnvironment.getShapes());

    testShapes.remove(1);
    this.keyframeEnvironment.deleteShape("Shape name2");
    assertEquals(testShapes, this.keyframeEnvironment.getShapes());

    testShapes.remove(0);

    this.keyframeEnvironment.deleteShape("Shape name");
    assertEquals(testShapes, this.keyframeEnvironment.getShapes());
  }


  //Tests that add keyframe accurately adds a keyframe to the correct shape at the correct time.
  //Tests that it is valid to add a shape before all existing keyframes, after all existing
  //keyframes and in between two existing keyframes.
  @Test
  public void testAddKeyframe() {

    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);

    this.keyframeEnvironment.addKeyframe("Shape name", 0, new Dimension(5, 5), new Color(255, 0, 0),
        new Position2D(0, 0), 0);
    this.keyframeEnvironment
        .addKeyframe("Shape name", 10, new Dimension(10, 10), new Color(0, 0, 0),
            new Position2D(500, 50), 35);
    this.keyframeEnvironment
        .addKeyframe("Shape name", 3, new Dimension(100, 50), new Color(0, 0, 0),
            new Position2D(250, 50), 200);

    ArrayList<ReadOnlyAnimatedShape> testLog = new ArrayList();

    testLog.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name", 0, new Dimension(5, 5),
            new Color(255, 0, 0), new Position2D(0, 0), 0)));
    testLog.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name", 3, new Dimension(100, 50),
            new Color(0, 0, 0),
            new Position2D(250, 50), 0)));
    testLog.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5),
            new Color(255, 0, 0),
            new Position2D(5, 10), 0)));

    testLog.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name", 10, new Dimension(10, 10),
            new Color(0, 0, 0),
            new Position2D(500, 50), 0)));
    assertEquals(testLog, this.keyframeEnvironment.getShapes().get(0).getLog());
  }

  //Tests that calling add keyframe with a shape name that does not correspond to any shape in this
  //environment is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeInvalidName() {
    this.keyframeEnvironment
        .addKeyframe("Shape name invalid", 10, new Dimension(10, 10), new Color(0, 0, 0),
            new Position2D(500, 50), 0);
  }

  //Tests that calling add keyframe with a negative time is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeInvalidTime() {
    this.keyframeEnvironment
        .addKeyframe("Shape name", -10, new Dimension(10, 10), new Color(0, 0, 0),
            new Position2D(500, 50), 50);
  }


  //Tests that adding a keyframe with a negative boundary is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeInvalidNegBounary() {
    this.keyframeEnvironment
        .addKeyframe("Shape name", 10, new Dimension(-10, 10), new Color(0, 0, 0),
            new Position2D(500, 50), 0);
  }


  //Tests that adding a keyframe with a zero boundary is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeInvalidZeroBoundary() {
    this.keyframeEnvironment.addKeyframe("Shape name", 10, new Dimension(10, 0), new Color(0, 0, 0),
        new Position2D(500, 50), 0);
  }

  //Tests that adding a keyframe with a null boundary is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeInvalidNullBoundary() {
    this.keyframeEnvironment.addKeyframe("Shape name", 10, null, new Color(0, 0, 0),
        new Position2D(500, 50), 0);
  }

  //Tests that adding a keyframe with a null color is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeInvalidNullColor() {
    this.keyframeEnvironment.addKeyframe("Shape name", 10, new Dimension(10, 10), null,
        new Position2D(500, 50), 0);
  }

  //Tests that adding a keyframe with a null position is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeInvalidNullPosition() {
    this.keyframeEnvironment
        .addKeyframe("Shape name", 10, new Dimension(10, 10), new Color(0, 0, 0),
            null, 0);
  }

  //Tests that adding a keyframe to a shape with an already pre-existing keyframe at the given time
  //is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testAddKeyframeInvalidPreExistingKeyframe() {
    this.keyframeEnvironment
        .addKeyframe("Shape name", 10, new Dimension(20, 20), new Color(255, 255, 0),
            new Position2D(0, 0), 0);

    this.keyframeEnvironment
        .addKeyframe("Shape name", 10, new Dimension(10, 10), new Color(0, 0, 0),
            new Position2D(500, 50), 0);
  }


  //Tests that deleting a keyframe accurately deletes the keyframe from the corresponding shape at
  //the corresponding time. Additionally ensures that when deleting a keyframe time that does not
  //exist in the shape, nothing occurs.
  @Test
  public void testDeleteKeyframe() {

    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);

    this.keyframeEnvironment.addKeyframe("Shape name", 0, new Dimension(5, 5), new Color(255, 0, 0),
        new Position2D(0, 0), 0);
    this.keyframeEnvironment
        .addKeyframe("Shape name", 10, new Dimension(10, 10), new Color(0, 0, 0),
            new Position2D(500, 50), 0);
    this.keyframeEnvironment
        .addKeyframe("Shape name", 3, new Dimension(100, 50), new Color(0, 0, 0),
            new Position2D(250, 50), 0);

    ArrayList<ReadOnlyAnimatedShape> testLog = new ArrayList();

    testLog.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name", 0, new Dimension(5, 5),
            new Color(255, 0, 0), new Position2D(0, 0), 0)));
    testLog.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name", 3, new Dimension(100, 50),
            new Color(0, 0, 0),
            new Position2D(250, 50), 0)));
    testLog.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5),
            new Color(255, 0, 0),
            new Position2D(5, 10), 0)));

    testLog.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name", 10, new Dimension(10, 10),
            new Color(0, 0, 0),
            new Position2D(500, 50), 0)));
    assertEquals(testLog, this.keyframeEnvironment.getShapes().get(0).getLog());

    this.keyframeEnvironment.deleteKeyframe("Shape name", 0);
    this.keyframeEnvironment.deleteKeyframe("Shape name", 10);
    this.keyframeEnvironment.deleteKeyframe("Shape name", 3);
    this.keyframeEnvironment.deleteKeyframe("Shape name", 5);

    assertEquals(new ArrayList<ReadOnlyAnimatedShape>(),
        this.keyframeEnvironment.getShapes().get(0).getLog());

    this.keyframeEnvironment.deleteKeyframe("Shape name", 3);

    assertEquals(new ArrayList<ReadOnlyAnimatedShape>(),
        this.keyframeEnvironment.getShapes().get(0).getLog());

  }


  //Tests that deleting a keyframe from a shape with a name that doesnt correspond to any
  //shapes within the environment is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidShapeDeleteKeyframe() {
    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);

    this.keyframeEnvironment.addKeyframe("Shape name", 0, new Dimension(5, 5), new Color(255, 0, 0),
        new Position2D(0, 0), 0);
    this.keyframeEnvironment
        .addKeyframe("Shape name", 10, new Dimension(10, 10), new Color(0, 0, 0),
            new Position2D(500, 50), 0);
    this.keyframeEnvironment
        .addKeyframe("Shape name", 3, new Dimension(100, 50), new Color(0, 0, 0),
            new Position2D(250, 50), 0);

    this.keyframeEnvironment.deleteKeyframe("Shape name invalid", 5);


  }

  //Tests that edit keyframe accurately edits the underlying keyframe log for the given shape.
  @Test
  public void testEditKeyframe() {
    ArrayList<ReadOnlyAnimatedShape> testLog = new ArrayList();
    testLog.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name", 0, new Dimension(5, 5),
            new Color(255, 0, 0), new Position2D(0, 0), 0)));
    testLog.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name", 3, new Dimension(100, 50),
            new Color(0, 0, 0),
            new Position2D(250, 50), 0)));
    testLog.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5),
            new Color(255, 0, 0),
            new Position2D(5, 10), 0)));

    testLog.add(new ReadOnlyKeyframeShape(
        new KeyframeAnimatedShapeImpl(Shapes.Rectangle, "Shape name", 10, new Dimension(10, 10),
            new Color(0, 0, 0),
            new Position2D(500, 50), 0)));

    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);

    this.keyframeEnvironment.addKeyframe("Shape name", 0, new Dimension(5, 5), new Color(255, 0, 0),
        new Position2D(0, 0), 0);
    this.keyframeEnvironment
        .addKeyframe("Shape name", 10, new Dimension(10, 10), new Color(0, 0, 0),
            new Position2D(500, 50), 0);
    this.keyframeEnvironment
        .addKeyframe("Shape name", 3, new Dimension(100, 50), new Color(0, 0, 0),
            new Position2D(250, 50), 0);

    assertEquals(testLog, this.keyframeEnvironment.getShapes().get(0).getLog());

    testLog.set(0, new ReadOnlyKeyframeShape(new KeyframeAnimatedShapeImpl(
        Shapes.Rectangle, "Shape name", 0, new Dimension(10, 10),
        new Color(0, 0, 0),
        new Position2D(5, 5), 0)));

    this.keyframeEnvironment
        .editKeyframe("Shape name", 0, new Dimension(10, 10), new Color(0, 0, 0),
            new Position2D(5, 5), 0);

    assertEquals(testLog, this.keyframeEnvironment.getShapes().get(0).getLog());

    this.keyframeEnvironment
        .editKeyframe("Shape name", 10, new Dimension(5, 20), new Color(0, 255, 255),
            new Position2D(5, 5), 0);

    testLog.set(3, new ReadOnlyKeyframeShape(new KeyframeAnimatedShapeImpl(Shapes.Rectangle,
        "Shape name", 10, new Dimension(5, 20),
        new Color(0, 255, 255),
        new Position2D(5, 5), 0)));

    assertEquals(testLog, this.keyframeEnvironment.getShapes().get(0).getLog());
  }


  //Tests that editing a keyframe of a shape that doesnt have a name corresponding to one in the
  //environment is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEditKeyframeName() {
    this.keyframeEnvironment
        .editKeyframe("Shape name", 0, new Dimension(10, 10), new Color(0, 0, 0),
            new Position2D(5, 5), 0);
  }

  //Tests that editing a keyframe with a negative time is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEditKeyframeTime() {
    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);
    this.keyframeEnvironment
        .editKeyframe("Shape name", -5, new Dimension(10, 10), new Color(0, 0, 0),
            new Position2D(5, 5), 0);
  }

  //Tests that editing a keyframe without a pre-existing keyframe at the given time is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEditKeyframepreTime() {
    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);
    this.keyframeEnvironment
        .editKeyframe("Shape name", 4, new Dimension(10, 10), new Color(0, 0, 0),
            new Position2D(5, 5), 0);
  }

  //Tests that editing a keyframe with a negative boundary is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEditKeyframeNegBoundary() {
    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);
    this.keyframeEnvironment
        .editKeyframe("Shape name", 4, new Dimension(-10, 10), new Color(0, 0, 0),
            new Position2D(5, 5), 0);
  }

  //Tests that editing a keyframe with a zero boundary is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEditKeyframeZeroBoundary() {
    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);
    this.keyframeEnvironment.editKeyframe("Shape name", 4, new Dimension(0, 10), new Color(0, 0, 0),
        new Position2D(5, 5), 0);
  }

  //Tests that editing a keyframe with a null dimension is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEditKeyframeNullDimension() {
    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);
    this.keyframeEnvironment.editKeyframe("Shape name", 4, null, new Color(0, 0, 0),
        new Position2D(5, 5), 0);
  }

  //Tests that editing a keyframe with a null color is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEditKeyframeNullColor() {
    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);
    this.keyframeEnvironment.editKeyframe("Shape name", 4, new Dimension(10, 10), null,
        new Position2D(5, 5), 0);
  }

  //Tests that editing a keyframe with a null position is invalid
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidEditKeyframeNullPos() {
    this.keyframeEnvironment
        .addShape(Shapes.Rectangle, "Shape name", 5, new Dimension(5, 5), new Color(255, 0, 0),
            new Position2D(5, 10), 0);
    this.keyframeEnvironment
        .editKeyframe("Shape name", 4, new Dimension(10, 10), new Color(255, 0, 0),
            null, 0);
  }

}
