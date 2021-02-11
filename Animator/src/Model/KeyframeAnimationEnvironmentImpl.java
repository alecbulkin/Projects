package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 * Represents an animation environment that supports the idea of keyframes rather than motions.
 * supports the editing functionality of keyframes such as adding keyframes, deleting keyframes,
 * adding shapes with initial keyframes, deleting shapes.
 */
public class KeyframeAnimationEnvironmentImpl implements KeyframeAnimationEnvironment {

  private ArrayList<KeyframeAnimatedShape> shapes;
  private AnimationEnvironment baseEnvironment;

  /**
   * Constructs a new {@code KeyframeAnimationEnvironmentImpl} with the given {@code
   * AnimationEnvironment} to convert into the idea of keyframes.
   *
   * @param baseEnvironment The base {@code AnimationEnvironment} to be represented as a {@code
   *                        KeyframAnimationEnvironment}.
   */
  public KeyframeAnimationEnvironmentImpl(AnimationEnvironment baseEnvironment) {
    this.baseEnvironment = baseEnvironment;
    this.shapes = this.convertShapes(baseEnvironment.getShapes());
  }

  @Override
  public int getX() {
    return baseEnvironment.getX();
  }

  @Override
  public int getY() {
    return baseEnvironment.getY();
  }

  @Override
  public int getHeight() {
    return baseEnvironment.getHeight();
  }

  @Override
  public int getWidth() {
    return baseEnvironment.getWidth();
  }

  @Override
  public ArrayList<ReadOnlyAnimatedShape> getShapes() {
    ArrayList<ReadOnlyAnimatedShape> immutableShapes = new ArrayList();
    for (KeyframeAnimatedShape shape : this.shapes) {
      immutableShapes.add(new ReadOnlyKeyframeShape(shape));
    }
    return immutableShapes;
  }

  @Override
  public void addShape(Shapes shapeType, String shapeName, int time, Dimension boundary,
      Color color, Position2D posn, int orientation) {
    for (KeyframeAnimatedShape shape : this.shapes) {
      if (shape.getName().equals(shapeName)) {
        throw new IllegalArgumentException("Shape with the given name " + shapeName
            + " already exists!");
      }
    }
    KeyframeAnimatedShape newShape = new KeyframeAnimatedShapeImpl(shapeType, shapeName, time,
        boundary, color, posn, orientation);
    newShape.log();
    this.shapes.add(newShape);
  }

  @Override
  public void deleteShape(String shapeName) {
    this.shapes.remove(this.getShape(shapeName));
  }

  @Override
  public void addKeyframe(String shapeName, int time, Dimension boundary, Color color,
      Position2D posn, int orientation) {
    KeyframeAnimatedShape shape = this.getShape(shapeName);
    for (ReadOnlyAnimatedShape keyframe : shape.getLog()) {
      if (time == keyframe.getTime()) {
        throw new IllegalArgumentException("Keyframe already exists at this time for this shape!");
      }
    }
    shape.setTime(time);
    shape.scale(boundary);
    shape.changeColor(color);
    shape.move(posn);
    shape.setOrientation(orientation);
    shape.log();
  }

  @Override
  public void deleteKeyframe(String shapeName, int time) {
    this.getShape(shapeName).deleteKeyframe(time);
  }

  @Override
  public void editKeyframe(String shapeName, int time, Dimension boundary, Color color,
      Position2D posn, int orientation) {
    KeyframeAnimatedShape shape = this.getShape(shapeName);
    for (ReadOnlyAnimatedShape keyframe : shape.getLog()) {
      if (keyframe.getTime() == time) {
        shape.deleteKeyframe(time);
        this.addKeyframe(shapeName, time, boundary, color, posn, orientation);
        return;
      }
    }
    throw new IllegalArgumentException("No keyframe corresponding to that time!");
  }


  /**
   * Gets the shape in {@code this} {@code KeyframAnimationEnvironment} that corresponds to the
   * given {@param shapeName}.
   *
   * @param shapeName The name of the {@code KeyframeAnimatedShape} that should be returned.
   * @return The {@code KeyframeAnimatedShape} that corresponds to the given {@param shapeName}.
   * @throws IllegalArgumentException If there is no {@code KeyframeAnimatedShape} in {@code this}
   *                                  {@code KeyframeAnimationEnvironment} corresponding to the
   *                                  {@param shapeName}.
   */
  private KeyframeAnimatedShape getShape(String shapeName) {
    for (KeyframeAnimatedShape shape : this.shapes) {
      if (shape.getName().equals(shapeName)) {
        return shape;
      }
    }
    throw new IllegalArgumentException("No shape in this environment corresponds to that name!");
  }

  /**
   * Converts the given ArrayList of {@code ReadOnlyAnimatedShape}s into and ArrayList of {@code
   * KeyframeAnimatedShape}s.
   *
   * @param shapes The ArrayList of {@code ReadOnlyAnimatedShape}s to be converted.
   * @return An ArrayList of {@code KeyframAnimatedShape}s.
   */
  private ArrayList<KeyframeAnimatedShape> convertShapes(ArrayList<ReadOnlyAnimatedShape> shapes) {
    ArrayList<KeyframeAnimatedShape> newShapes = new ArrayList();
    for (ReadOnlyAnimatedShape shape : shapes) {
      newShapes.add(new KeyframeAnimatedShapeImpl(shape));
    }
    return newShapes;
  }
}
