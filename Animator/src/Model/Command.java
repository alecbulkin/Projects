package model;

/**
 * Represents an animation command to be executed on an {@code AnimatedShape}. These Commands
 * perform changes on the states of {@code AnimatedShape}s.
 */
public interface Command {

  /**
   * Applies the animation command to the given shape.
   *
   * @param shape The {@code AnimatedShape} that {@code this} {@code Command} will be applied to.
   * @throws IllegalArgumentException if the initial state of the shape does not match the initial
   *                                  state in the Command.
   */
  void apply(AnimatedShape shape);

}
