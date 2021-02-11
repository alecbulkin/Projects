package model.commands;

import model.AnimatedShape;
import model.Command;
import java.awt.Color;

/**
 * Represents a painting {@code Command} that paints {@code AnimatedShape}s the color that it is
 * constructed with.
 */
public class Paint implements Command {
  private final Color initialColor;
  private final Color finalColor;

  /**
   * Constructs a Paint Command that turns a shape from the initial color to the final color.
   *
   * @param fromR The red component of the initial color.
   * @param fromG The green component of the initial color.
   * @param fromB The blue component of the initial color.
   * @param toR The red component of the final color.
   * @param toG The green component of the final color.
   * @param toB The blue component of the final color.
   * @throws IllegalArgumentException if any of the rgb values are negative or greater than 255.
   */
  public Paint(int fromR, int fromG, int fromB, int toR, int toG, int toB) {
    if (fromR < 0 || fromR > 255 || fromG < 0 || fromG > 255 || fromB < 0 || fromB > 255
        || toR < 0 || toR > 255 || toG < 0 || toG > 255 || toB < 0 || toB > 255) {
      throw new IllegalArgumentException("Invalid rgb value!");
    }

    this.initialColor = new Color(fromR, fromG, fromB);
    this.finalColor = new Color(toR, toG, toB);
  }

  @Override
  public void apply(AnimatedShape shape) {
    if (!this.initialColor.equals(shape.getColor())) {
      throw new IllegalArgumentException("Initial color doesn't match!");
    }
    shape.changeColor(this.finalColor);
  }
}
