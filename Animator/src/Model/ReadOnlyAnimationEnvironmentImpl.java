package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a read-only version of the {@code AnimationEnvironment}. Provides read only access to
 * all of the necessary fields in AnimationEnvironment such as the x coordinate of the top left
 * corner of the base environment, the y coordinate of the top left corner of the base environment,
 * the height and width of the base environment, as well as all of the shapes that are within the
 * base environment.
 */
public class ReadOnlyAnimationEnvironmentImpl implements ReadOnlyAnimationEnvironment {

  private final AnimationEnvironment baseEnvironment;

  /**
   * Constructs an {@code ReadOnlyAnimationEnvironmentImpl} wich refers to the given {@code
   * AnimationEnvironment} {@param baseEnvironment}.
   *
   * @param baseEnvironment The {@code AnimationEnvironment} which {@code this} {@code
   *                        ReadOnlyAnimationEnvironment} refers to.
   */
  public ReadOnlyAnimationEnvironmentImpl(AnimationEnvironment baseEnvironment) {
    this.baseEnvironment = baseEnvironment;
  }

  @Override
  public int getX() {
    return this.baseEnvironment.getX();
  }

  @Override
  public int getY() {
    return this.baseEnvironment.getY();
  }

  @Override
  public int getHeight() {
    return baseEnvironment.getHeight();
  }

  @Override
  public int getWidth() {
    return baseEnvironment.getWidth();
  }

  @Override
  public ArrayList<ReadOnlyAnimatedShape> getShapes() {
    return this.baseEnvironment.getShapes();
  }

  /**
   * Defines equals so that {@code this} is only equal to {@param other} if the {@param other}
   * parameter is an instance of {@code ReadOnlyAnimationEnvironment} and the location of the top
   * left corner of {@param other} is equal to the location of the top left corner of {@code this}
   * as well as if the dimensions and shapes in each are equal.
   *
   * @param other The Object for {@code this} {@code ReadOnlyAnimationEnvironment} to be compared
   *              to.
   * @return A boolean value where true indicates that the given Object {@param other} is considered
   *         to be equal to {@code this} {@code ReadOnlyAnimationEnvironment}.
   */
  @Override
  public boolean equals(Object other) {
    if (other == this) {
      return true;
    }
    if (other instanceof ReadOnlyAnimationEnvironment) {
      ReadOnlyAnimationEnvironment roOtherEnvironment = ((ReadOnlyAnimationEnvironment) other);
      return (this.getX() == roOtherEnvironment.getX()
          && this.getY() == roOtherEnvironment.getY()
          && this.getHeight() == roOtherEnvironment.getHeight()
          && this.getWidth() == roOtherEnvironment.getWidth()
          && this.getShapes().equals(roOtherEnvironment.getShapes()));
    }
    return false;
  }

  /**
   * Gives a hashcode to {@code this} {@code ReadOnlyAnimationEnvironment} that depends on and is
   * unique to a certain combination of the position of the top left coordinate of {@code this}
   * {@code ReadOnlyAnimationEnvironment}, the dimensions of {@code this} {@code
   * ReadOnlyAnimationEnvironment}, and the shapes that are present in {@code this} {@code
   * ReadOnlyAnimationEnvironment}.
   *
   * @return A hashcode that is unique to the attributes of {@code this} {@code
   *         ReadOnlyAnimationEnvironment}.
   */
  @Override
  public int hashCode() {
    return Objects
        .hash(this.getX(), this.getY(), this.getHeight(), this.getWidth(), this.getShapes());
  }

}
