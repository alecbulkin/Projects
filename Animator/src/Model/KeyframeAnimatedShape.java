package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 * Represents an {@code AnimatedShape} with additional capabilities regarding keyframes, such as
 * the ability to add keyframes, delete keyframes, and edit keyframes.
 */
public interface KeyframeAnimatedShape {

  /**
   * Deletes the keyframe from the keyframes of {@code this} {@code KeyframeAnimatedShape}
   * that corresponds to the given {@param time}.
   *
   * @param time The time that corresponds to the keyframe that should be deleted.
   */
  void deleteKeyframe(int time);

  /**
   * Moves a shape from its current coordinates to a new position.
   *
   * @param newPos represents the desired new position the shape would move to
   */
  void move(Position2D newPos);

  /**
   * Rotates a shape by a given number of degrees.
   *
   * @param degrees represents the number of degrees to be rotated as a double.
   * @throws IllegalArgumentException if the degrees of rotation are negative.
   */
  void setOrientation(int degrees);

  /**
   * Scales a shape's dimensions to match the specified dimension.
   *
   * @param d the dimension {@code this} {@code animatedShape} should scale to.
   * @throws IllegalArgumentException if the either the height or width of the dimension is
   *                                  negative.
   */
  void scale(Dimension d);

  /**
   * Sets the color of {@code this} {@code AnimateShape} to the {@code Color} of {@param c}.
   *
   * @param c the new Color for {@code this} {@code AnimatedShape}.
   */
  void changeColor(Color c);


  /**
   * Sets the time of {@code this} {@code AnimateShape}.
   *
   * @param newTime the new time for {@code this} {@code AnimateShape}.
   */
  void setTime(int newTime);


  /**
   * Returns the time of {@code this} {@code AnimateShape}.
   *
   * @return the time of {@code this} {@code AnimateShape} as an int.
   */
  int getTime();

  /**
   * Logs the current state of this shape as a string.
   */
  void log();

  /**
   * Returns the name of {@code this} {@code AnimateShape}.
   *
   * @return the name of {@code this} {@code AnimateShape} as a string.
   */
  String getName();

  /**
   * Returns the boundary of {@code this} {@code AnimateShape}.
   *
   * @return the boundary of {@code this} {@code AnimateShape} as a {@code Dimension}.
   */
  Dimension getBoundary();

  /**
   * Returns the color of {@code this} {@code AnimateShape}.
   *
   * @return the color of {@code this} {@code AnimateShape}.
   */
  Color getColor();

  /**
   * Returns the position of {@code this} {@code AnimateShape}.
   *
   * @return the position of {@code this} {@code AnimateShape} as a {@code Position2D}.
   */
  Position2D getPosn();

  /**
   * Returns a list of {@code ReadOnlyAnimatedShape}s that represent the past states of {@code this}
   * {@code KeyframeAnimateShape}.
   *
   * @return the animation log of {@code this} {@code KeyframeAnimateShape} as an ArrayList of
   * {@code ReadOnlyAnimatedShape}s.
   */
  ArrayList<ReadOnlyAnimatedShape> getLog();

  /**
   * Returns the angled orientation of {@code this} {@code KeyframeAnimateShape}.
   *
   * @return the angled orientation of {@code this} {@code KeyframeAnimateShape}.
   */
  int getOrientation();

  /**
   * Returns the shape type of {@code this} {@code KeyframeAnimateShape}.
   *
   * @return the type of shape that {@code this} {@code KeyframeAnimateShape} is.
   */
  Shapes getShapeType();

}
