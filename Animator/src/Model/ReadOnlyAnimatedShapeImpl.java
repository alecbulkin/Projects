package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a read-only version of {@code AnimatedShape}. That gives access to all of the useful
 * fields of an {@code AnimatedShape} such as the time, name, boundary, color, position, animation
 * history, orientation, and shape type.
 */
public class ReadOnlyAnimatedShapeImpl implements ReadOnlyAnimatedShape {

  private final AnimatedShape baseShape;

  /**
   * Constructs an {@code ReadOnlyAnimatedShapeImpl} with a given {@code AnimatedShape} {@param
   * baseShape} to refer to.
   *
   * @param baseShape The {@code AnimatedShape} for {@code this} {@code ReadOnlyAnimatedShape} to
   *                  refer to.
   */
  public ReadOnlyAnimatedShapeImpl(AnimatedShape baseShape) {
    this.baseShape = baseShape;
  }

  @Override
  public int getTime() {
    return this.baseShape.getTime();
  }

  @Override
  public String getName() {
    return this.baseShape.getName();
  }

  @Override
  public Dimension getBoundary() {
    return this.baseShape.getBoundary();
  }

  @Override
  public Color getColor() {
    return this.baseShape.getColor();
  }

  @Override
  public Position2D getPosn() {
    return this.baseShape.getPosn();
  }

  @Override
  public ArrayList<ReadOnlyAnimatedShape> getLog() {
    return this.baseShape.getLog();
  }

  @Override
  public int getOrientation() {
    return this.baseShape.getOrientation();
  }

  @Override
  public Shapes getShapeType() {
    return this.baseShape.getShapeType();
  }

  @Override
  public String toString() {
    return this.baseShape.toString();
  }

  /**
   * An object is considered equal to {@code this} {@code ReadOnlyAnimatedShape} if it is an
   * instance of {@code ReadOnlyAnimatedShape} and has the same time, name, boundary, color,
   * position, orientation, and shape type as {@code this} {@code ReadOnlyAnimatedShape}.
   *
   * @param other The Object to be compared to {@code this} {@code ReadOnlyAnimatedShape}.
   * @return A boolean value where true indicates that {@param other} is equal to {@code this}
   * {@code ReadOnlyAnimatedShape}.
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other instanceof ReadOnlyAnimatedShape) {
      ReadOnlyAnimatedShape roOtherShape = ((ReadOnlyAnimatedShape) other);
      return (this.getTime() == roOtherShape.getTime()
          && this.getName().equals(roOtherShape.getName())
          && this.getBoundary().equals(roOtherShape.getBoundary())
          && this.getColor().equals(roOtherShape.getColor())
          && this.getPosn().equals(roOtherShape.getPosn())
          && this.getOrientation() == roOtherShape.getOrientation()
          && this.getShapeType() == roOtherShape.getShapeType());
    }
    return false;
  }

  /**
   * Returns an integer that is unique to the combination of shape type, time, name, boundary,
   * color, position, and orientation of {@code this} {@code ReadOnlyAnimatedShape}.
   *
   * @return A unique integer based on salient fields of {@code this} {@code ReadOnlyAnimatedShape}.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.getName(), this.getShapeType(), this.getTime(), this.getBoundary(),
        this.getColor(), this.getPosn(),
        this.getOrientation());
  }


}
