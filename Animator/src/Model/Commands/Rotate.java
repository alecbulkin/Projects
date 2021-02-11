package model.commands;

import model.AnimatedShape;
import model.Command;

/**
 * Represents a rotation {@code Command} that rotates an {@code AnimatedShape} by the degrees
 * that it is constructed with.
 */
public class Rotate implements Command {
  private final int initialOrientation;
  private final int finalOrientation;

  /**
   * Constructs a {@code Rotation} command object which will rotate {@code AnimatedShape}s by the
   * given {@param degrees}.
   *
   * @param initialOrientation The initial angled orientation of the {@code AnimatedShape} to be
   *                           rotated.
   * @param finalOrientation The angle to rotate the {@code AnimatedShape} by.
   * @throws IllegalArgumentException if the initial orientation is negative or greater than or
   *                                  equal to 360 or if the rotation is negative.
   */
  public Rotate(int initialOrientation, int finalOrientation) {
    if (initialOrientation < 0 || initialOrientation > 360) {
      throw new IllegalArgumentException("Invalid initial orientation");
    }
    if (finalOrientation < 0 || finalOrientation > 360) {
      throw new IllegalArgumentException("Invalid final orientation");
    }
    this.initialOrientation = initialOrientation;
    this.finalOrientation = finalOrientation;
  }

  @Override
  public void apply(AnimatedShape shape) {
    if (this.initialOrientation != shape.getOrientation()) {
      throw new IllegalArgumentException("Initial orientation does not match!");
    }
    shape.setOrientation(this.finalOrientation);
  }
}
