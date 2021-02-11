package model;

import java.util.ArrayList;

/**
 * Represents a read-only version of the AnimationEnvironment.
 */
public interface ReadOnlyAnimationEnvironment {

  /**
   * Returns the height of {@code this} {@code ReadOnlyAnimationEnvironment}.
   *
   * @return The height of {@code this} {@code ReadOnlyAnimationEnvironment} as an int.
   */
  int getHeight();

  /**
   * Returns the width of {@code this} {@code ReadOnlyAnimationEnvironment}.
   *
   * @return The width of {@code this} {@code ReadOnlyAnimationEnvironment} as an int.
   */
  int getWidth();

  /**
   * Returns the x position of the top left corner of {@code this} {@code AnimationEnvironment}.
   *
   * @return The x position of the top left corner of {@code this} {@code AnimationEnvironment} as
   *         an integer.
   */
  int getX();

  /**
   * Returns the y position of the top left corner of {@code this} {@code AnimationEnvironment}.
   *
   * @return The y coordinate of the top left corner of {@code this} {@code AnimationEnvironment} as
   *         an integer.
   */
  int getY();

  /**
   * Returns a list of all of the shapes in {@code this} {@code ReadOnlyAnimationEnvironment} as
   * {@code ReadOnlyAnimatedShape}s.
   *
   * @return All of the shapes in {@code this} {@code ReadOnlyAnimationEnvironment}.
   */
  ArrayList<ReadOnlyAnimatedShape> getShapes();

}
