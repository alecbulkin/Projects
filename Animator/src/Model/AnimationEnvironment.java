package model;

import controller.AnimationBuilder;
import model.AnimationEnvironmentImpl.Builder;
import java.util.ArrayList;

/**
 * Represents the environment and all of its contents and functionality for an animation.
 */
public interface AnimationEnvironment {

  /**
   * Returns the width of {@code this} {@code AnimationEnvironment}.
   *
   * @return the width of {@code this} {@code AnimationEnvironment} as an int.
   */
  int getWidth();

  /**
   * Returns the height of {@code this} {@AnimationEnviornment}.
   *
   * @return the height of {@code this} {@code AnimationEnvironment} as an int.
   */
  int getHeight();

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
   * Adds the shape {@param shape} to {@code this} {@code AnimationEnvironment}.
   *
   * @param shape The shape that is to be added to {@code this} {@code AnimationEnvironment}.
   * @throws IllegalArgumentException if the shape is invalid.
   */
  void addShape(AnimatedShape shape);

  /**
   * Commands a certain shape corresponding to {@code name} in {@code this} {@code
   * AnimationEnvironment} to do the given animations {@param commands} from the beginning time
   * {@param initialTime} to the ending time {@code finalTime}.
   *
   * @param name        The name of the shape to be animated.
   * @param initialTime The beginning time of this animation command.
   * @param finalTime   The ending time of this animation command.
   * @param commands    The animation commands to be run on the given {@code AnimatedShape}
   *                    corresponding to the given {@param name}.
   * @throws IllegalArgumentException if the {@param name} is null or no {@code AnimatedShape} with
   *                                  the given {@param name} is in {@code this} {@code
   *                                  AnimationEnvironment} or the given timeframe overlaps with
   *                                  existing animations.
   */
  void commandShape(String name, int initialTime, int finalTime, Command... commands);

  /**
   * Returns a the list of shapes that are in {@code this} {@code AnimationEnvironment} represented
   * as {@code ReadOnlyAnimatedShapes}.
   *
   * @return An ArrayList of the shapes that are in {@code this} {@code AnimationEnvironment} as
   *              {@code ReadOnlyAnimatedShapes}.
   */
  ArrayList<ReadOnlyAnimatedShape> getShapes();

  /**
   * returns a builder to build an {@code AnimationEnvironment}.
   *
   * @return A builder to construct an {@code AnimationEnvironment}.
   */
  static AnimationBuilder<AnimationEnvironment> builder() {
    return new Builder();
  }

}
