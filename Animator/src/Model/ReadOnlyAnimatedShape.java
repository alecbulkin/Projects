package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 * Represents a read-only version of {@code AnimatedShape}.
 */
public interface ReadOnlyAnimatedShape {

  /**
   * Returns the time for {@code this} {@code ReadOnlyAnimatedShape}.
   *
   * @return The time of {@code this} {@code ReadOnlyAnimatedShape} as an int.
   */
  int getTime();

  /**
   * Returns the name for {@code this} {@code ReadOnlyAnimatedShape}.
   *
   * @return The name of {@code this} {@code ReadOnlyAnimatedShape} as an int.
   */
  String getName();

  /**
   * Returns the dimension for the rectangle that constrains {@code this} {@code
   * ReadOnlyAnimatedShape}.
   *
   * @return The dimension for the bounding rectangle of {@code this} {@code ReadOnlyAnimatedShape}.
   */
  Dimension getBoundary();

  /**
   * Returns the color of {@code this} {@code ReadOnlyAnimatedShape}.
   *
   * @return The color of {@code this} {@code ReadOnlyAnimatedShape}.
   */
  Color getColor();

  /**
   * Returns a {@code Position2D} that represents the position of {@code this} {@code
   * ReadOnlyAnimatedShape}.
   *
   * @return The position of {@code this} {@code ReadOnlyAnimatedShape} as a {@code Position2D}.
   */
  Position2D getPosn();

  /**
   * Returns a log of all of the past states of {@code this} {@code ReadOnlyAnimatedShape} as an
   * ArrayList of {@code ReadOnlyAnimatedShape}s.
   *
   * @return An ArrayList of {@code ReadOnlyAnimatedShape}s representing the past states of {@code
   * this} {@code ReadOnlyAnimatedShape}
   */
  ArrayList<ReadOnlyAnimatedShape> getLog();

  /**
   * Returns the angled orientation of {@code this} {@code ReadOnlyAnimatedShape}.
   *
   * @return The angled orientation of {@code this} {@code ReadOnlyAnimatedShape}.
   */
  int getOrientation();

  /**
   * Returns the type of shape of {@code this} {@code ReadOnlyAnimatedShape}.
   *
   * @return The shape type of {@code this} {@code ReadOnlyAnimatedShape}.
   */
  Shapes getShapeType();

  /**
   * Returns a String representation of {@code this} {@code ReadOnlyAnimatedShape}'s current state.
   *
   * @return The current sate of {@code this} {@code ReadOnlyAnimatedShape} represented as a String.
   */
  String toString();

}
