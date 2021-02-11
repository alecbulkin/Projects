package model;

import java.util.ArrayList;

/**
 * Represents a read-only version of a {@code KeyframeEnvironment} that offers all of the
 * functionality of reading abilities as a {@code ReadOnlyAnimationEnvironment} so that use of this
 * and {@code ReadOnlyAnimationEnvironmentImpl} is interchangeable.
 */
public class ReadOnlyKeyframeEnvironment implements ReadOnlyAnimationEnvironment {
  private final KeyframeAnimationEnvironment baseEnvironment;

  /**
   * Constructs a new {@code ReadOnlyKeyframeEnvironment} with the given
   * {@code KeyframeAnimationEnvironment} {@param baseEnvironment} as the environment to offer read
   * only access to.
   * @param baseEnvironment The {@code KeyframeAnimationEnvironment} to be read.
   */
  public ReadOnlyKeyframeEnvironment(KeyframeAnimationEnvironment baseEnvironment) {
    this.baseEnvironment = baseEnvironment;
  }

  @Override
  public int getHeight() {
    return this.baseEnvironment.getHeight();
  }

  @Override
  public int getWidth() {
    return this.baseEnvironment.getWidth();
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
  public ArrayList<ReadOnlyAnimatedShape> getShapes() {
    return this.baseEnvironment.getShapes();
  }
}
