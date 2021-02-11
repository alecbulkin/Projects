package model;

import java.util.Objects;

/**
 * This class represents a 2D position with an X and Y coordinate.
 */
public final class Position2D {

  private double x;
  private double y;

  /**
   * Initializes a Position2D to have a given {@param x} coordinate and {@param y} coordinate.
   *
   * @param x The x coordinate of this position.
   * @param y The y coordinate of this position.
   */
  public Position2D(double x, double y) {
    this.setX(x);
    this.setY(y);
  }

  /**
   * Initializes a Position2D to have an x and y value equal to the values of the given {@param
   * posn}.
   *
   * @param posn A position for this Position2D to replicate.
   */
  public Position2D(Position2D posn) {
    this.setX(posn.x);
    this.setY(posn.y);
  }

  /**
   * Returns the x coordinate of {@code this} {@code Position2D}.
   *
   * @return The x value of {@code this} {@code Position2D} as a double.
   */
  public double getX() {
    return x;
  }

  /**
   * Returns the y coordinate of {@code this} {@code Position2D}.
   *
   * @return The y value of {@code this} {@code Position2D} as a double.
   */
  public double getY() {
    return y;
  }

  /**
   * Set the x coordinate of {@code this} {@code Position2D}.
   *
   * @param x The new x coordinate value of {@code this} {@code Position2D}.
   */
  public void setX(double x) {
    this.x = x;
  }

  /**
   * Set the y coordinate of {@code this} {@code Position2D}.
   *
   * @param y The new y coordinate value of {@code this} {@code Position2D}.
   */
  public void setY(double y) {
    this.y = y;
  }

  @Override
  public boolean equals(Object a) {
    if (this == a) {
      return true;
    }
    if (!(a instanceof Position2D)) {
      return false;
    }

    Position2D that = (Position2D) a;

    return ((Math.abs(this.x - that.x) < 0.01)
        && (Math.abs(this.y - that.y) < 0.01));
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.x, this.y);
  }
}
