package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

/**
 * Represents an {@code AnimateShape} with all of the functionality of the shape and its animation.
 */
public class AnimatedShapeImpl implements AnimatedShape {

  private Dimension boundary;
  private Color color;
  private Position2D pos;
  private int time;
  private ArrayList<AnimatedShape> animationHistory;
  private final String name;
  private final Shapes shapeType;
  private int orientation;

  //INVARIANT: Boundary has both a positive height and width.
  //INVARIANT: The animationHistory is in order with the earliest animations first.
  //INVARIANT: Time is 0 or Positive.
  //INVARIANT: Orientation is an angle between 0 (inclusive) and 360 (exclusive).

  /**
   * Constructs an {@code AnimatedShape} that is of a certain {@param shapeType}, with a given
   * {@param name} bounded by a rectangle with a given dimension {@param boundary}, a color {@param
   * color}, a 2 dimensional position {@param pos}, and an angled orientation {@param Orientation}.
   *
   * @param shapeType   The type of shape that {@code this} {@animatedShape} is.
   * @param name        The name of {@code this} {@animatedShape}.
   * @param time        The initial time of {@code this} {@animatedShape}.
   * @param boundary    The dimensions of the bounding rectangle of {@code this} {@animatedShape}.
   * @param color       The color of {@code this} {@animatedShape}.
   * @param pos         The position of {@code this} {@animatedShape}.
   * @param orientation The angled orientation of {@code this} {@animatedShape}.
   * @throws IllegalArgumentException if any inputs are invalid.
   */
  public AnimatedShapeImpl(Shapes shapeType, String name, int time, Dimension boundary, Color color,
      Position2D pos, int orientation) {
    if (shapeType == null) {
      throw new IllegalArgumentException("Must assign a valid shape type!");
    } else if (name == null) {
      throw new IllegalArgumentException("Must assign a valid name!");
    } else if (time < 0) {
      throw new IllegalArgumentException("Time must be non-negative!");
    } else if (boundary == null || boundary.getWidth() < 1 || boundary.getHeight() < 1) {
      throw new IllegalArgumentException(
          "Invalid boundary! Boundary must be nonnull and positive!");
    } else if (color == null) {
      throw new IllegalArgumentException("Must assign a color!");
    } else if (pos == null) {
      throw new IllegalArgumentException("Must assign a valid position!");
    } else if (orientation < 0 || orientation > 360) {
      throw new IllegalArgumentException("Must assign an orientation between 0 and 360!");
    }
    this.orientation = orientation;
    this.shapeType = shapeType;
    this.name = name;
    this.time = time;
    this.boundary = boundary;
    this.color = color;
    this.pos = pos;
    this.animationHistory = new ArrayList();
  }

  /**
   * Constructs an {@code AnimatedShapeImpl} that has the same state as the given {@code
   * AnimatedShape} {@param shape}.
   *
   * @param shape The {@code AnimatedShape} for {@code this} {@code AnimatedShape} to replicate.
   */
  private AnimatedShapeImpl(AnimatedShape shape) {
    this.orientation = shape.getOrientation();
    this.shapeType = shape.getShapeType();
    this.name = shape.getName();
    this.time = shape.getTime();
    this.boundary = shape.getBoundary();
    this.color = shape.getColor();
    this.pos = shape.getPosn();
    this.animationHistory = new ArrayList();
  }

  @Override
  public void move(Position2D newPos) {
    this.pos = newPos;
  }

  @Override
  public void setOrientation(int degrees) {
    if(degrees < 0 || degrees > 360) {
      throw new IllegalArgumentException("Invalid orientation!");
    }
    this.orientation = degrees;
  }

  @Override
  public void changeColor(Color c) {
    this.color = c;
  }

  @Override
  public int getTime() {
    return this.time;
  }

  @Override
  public void setTime(int newTime) {
    if (newTime < 0) {
      throw new IllegalArgumentException("Time must be positive!");
    }
    this.time = newTime;
  }

  @Override
  public void log() {
    AnimatedShape copy = new AnimatedShapeImpl(this);
    this.animationHistory.add(copy);
    this.animationHistory.sort(new Comparator<AnimatedShape>() {
      @Override
      public int compare(AnimatedShape shape1, AnimatedShape shape2) {
        return shape1.getTime() - shape2.getTime();
      }
    });
  }

  @Override
  public Position2D getPosn() {
    return this.pos;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public Dimension getBoundary() {
    return this.boundary;
  }

  @Override
  public void scale(Dimension d) {
    if (d.getWidth() < 1 || d.getHeight() < 1) {
      throw new IllegalArgumentException("Dimensions must be positive!");
    }
    this.boundary = d;
  }

  @Override
  public ArrayList<ReadOnlyAnimatedShape> getLog() {
    ArrayList<ReadOnlyAnimatedShape> immutableList = new ArrayList();
    for (AnimatedShape shape : this.animationHistory) {
      immutableList.add(new ReadOnlyAnimatedShapeImpl(shape));
    }
    return immutableList;
  }

  @Override
  public int getOrientation() {
    return this.orientation;
  }

  @Override
  public Shapes getShapeType() {
    return this.shapeType;
  }

  /**
   * Compares {@code this} {@code AnimatedShape} to the given object. Returns true if {@param other}
   * is an {@code AnimatedShapeImpl} where time, position, boundary, color, and orientation are
   * equal.
   *
   * @param other The object that {@code this} {@code AnimatedShape} is being compared to.
   * @return a boolean value that indicates whether or not {@param other} is equivalent to {@code
   * this} {@code AnimatedShape} via the definition of equality above.
   */
  @Override
  public boolean equals(Object other) {
    if (this == other) {
      return true;
    }
    if (other instanceof AnimatedShapeImpl) {
      AnimatedShape otherShape = ((AnimatedShapeImpl) other);
      return (otherShape.getShapeType() == this.shapeType
          && otherShape.getTime() == this.time
          && otherShape.getBoundary().equals(this.boundary)
          && otherShape.getColor().equals(this.color)
          && otherShape.getPosn().equals(this.pos)
          && otherShape.getOrientation() == this.orientation);
    } else {
      return false;
    }
  }

  /**
   * Returns a hashcode that is unique based upon the shape type, time, boundary, color, position
   * and orientation of {@code this} {@code AnimatedShape}.
   *
   * @return a hashcode that is unique to the current state of {@code this} {@code AnimatedShape}.
   */
  @Override
  public int hashCode() {
    return Objects.hash(this.shapeType, this.time, this.boundary, this.color, this.pos,
        this.orientation);
  }

  @Override
  public String toString() {
    return this.time + " " + this.pos.getX()
        + " " + this.pos.getY() + " " + this.boundary.getWidth() + " " + this.boundary.getHeight()
        + " " + this.color.getRed() + " " + this.color.getGreen() + " " + this.color.getBlue()
        + " " + this.orientation;
  }

}
