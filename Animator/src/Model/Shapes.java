package model;

/**
 * Represents the type of shape of a given {@code AnimatedShape}.
 *
 * <p>In hopes of supporting easy future additions to shapes we decided to use an enumerated type.
 * This way, with the design of our model if we wanted to support more shapes in the future, all we
 * would have to do is add a shape type to this enumerated type and our model would be fully
 * functional for any additional types.</p>
 */
public enum Shapes {
  Rectangle("Rectangle"), Oval("Oval"), Triangle("Triangle");

  private final String shapeType;

  /**
   * Initializes the shapeType String value of {@code this} {@code Shapes}.
   *
   * @param shapeType the String value of {@code this} {@code Shapes}.
   */
  private Shapes(String shapeType) {
    this.shapeType = shapeType;
  }

  @Override
  public String toString() {
    return this.shapeType;
  }
}
