package model.commands;

import model.AnimatedShape;
import model.Command;
import java.awt.Dimension;

/**
 * Represents a Scaling {@code Command} on an {@code AnimatedShape}. Scales the
 * {@code AnimatedShape} to be bounded by the rectangle of dimensions given by the width and height
 * {@code this} {@code Scale} was instantiated with.
 */
public class Scale implements Command {
  private final Dimension initialBoundary;
  private final Dimension finalBoundary;

  /**
   * Constructs a Scaling command object that scales {@code AnimatedShape}s of a given intial size
   * of {@param fromWidth}x{@param fromheight} to the given {@param width} and {@param height}.
   *
   * @param fromWidth The initial width of the bounding rectangle of an {@code AnimatedShape}.
   * @param fromHeight The initial height of the bounding rectangle of an {@code AnimatedShape}.
   * @param toWidth The width that the bounding rectangle of an {@code AnimatedShape}
   *                will be scaled to.
   * @param toHeight The height that the bounding rectangle of an {@code AnimatedShape}
   *                 will be scaled to.
   * @throws IllegalArgumentException if any dimensions are 0 or negative.
   */
  public Scale(int fromWidth, int fromHeight, int toWidth, int toHeight) {
    if (fromWidth <= 0 || fromHeight <= 0 || toWidth <= 0 || toHeight <= 0) {
      throw new IllegalArgumentException("Invalid dimensions!");
    }
    this.initialBoundary = new Dimension(fromWidth, fromHeight);
    this.finalBoundary = new Dimension(toWidth, toHeight);
  }

  @Override
  public void apply(AnimatedShape shape) {
    if (!shape.getBoundary().equals(this.initialBoundary)) {
      throw new IllegalArgumentException("Initial size does not match!");
    }
    shape.scale(this.finalBoundary);
  }
}
