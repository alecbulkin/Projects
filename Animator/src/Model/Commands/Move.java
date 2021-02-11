package model.commands;

import model.AnimatedShape;
import model.Command;
import model.Position2D;

/**
 * Represents a {@code Command} object that moves the position of an {@code AnimatedShape} to
 * the x and y coordinates that a {@code Move} is constructed with.
 */
public class Move implements Command {
  private final Position2D finalPosn;
  private final Position2D initialPosn;

  /**
   * Instantiates a {@code Move} command object that moves an {@code AnimatedShape} to the give
   * position ({@param x}, {@param y}).
   *
   * @param fromX The x coordinate that an {@code AnimatedShape} should move from.
   * @param fromY The y coordinate that an {@code AnimatedShape} should move from.
   * @param toX The x coordinate that {@code this} {@code Move} will move {@code AniamtedShape}s to.
   * @param toY The y coordinate that {@code this} {@code Move} will move {@code AnimatedShape}s to.
   */
  public Move(int fromX, int fromY, int toX, int toY) {
    this.initialPosn = new Position2D(fromX, fromY);
    this.finalPosn = new Position2D(toX, toY);
  }

  @Override
  public void apply(AnimatedShape shape) {
    if (!shape.getPosn().equals(this.initialPosn)) {
      throw new IllegalArgumentException("Initial position does not match!");
    }
    shape.move(this.finalPosn);
  }
}
