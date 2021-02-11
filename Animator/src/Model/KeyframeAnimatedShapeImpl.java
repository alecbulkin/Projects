package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * Represents an {@code AnimatedShape} that lends itself to the idea of keyframes rather than
 * motions. Supports features such as storing its animationHistory as a list of keyframes and offers
 * the ability to delete a keyframe from the list of keyframes in this shape.
 */
public class KeyframeAnimatedShapeImpl extends AnimatedShapeImpl implements KeyframeAnimatedShape {

  private ArrayList<ReadOnlyAnimatedShape> keyframes;

  /**
   * Constructs a {@code KeyframeAnimatedShapeImpl} that is of a certain {@param shapeType}, with a
   * given {@param name} bounded by a rectangle with a given dimension {@param boundary}, a color
   * {@param color}, a 2 dimensional position {@param pos}, and an angled orientation {@param
   * Orientation}. The initial keyframes of {@code this} {@code KeyframeAnimatedShapeImpl} are
   * empty.
   *
   * @param shapeType The type of shape that {@code this} {@animatedShape} is.
   * @param name      The name of {@code this} {@animatedShape}.
   * @param time      The initial time of {@code this} {@animatedShape}.
   * @param boundary  The dimensions of the bounding rectangle of {@code this} {@animatedShape}.
   * @param color     The color of {@code this} {@animatedShape}.
   * @param pos       The position of {@code this} {@animatedShape}.
   * @param orientation The angled orientation of {@code this} {@code animatedShape}.
   * @throws IllegalArgumentException if any inputs are invalid.
   */
  public KeyframeAnimatedShapeImpl(Shapes shapeType, String name, int time,
      Dimension boundary,
      Color color, Position2D pos, int orientation) {
    super(shapeType, name, time, boundary, color, pos,  orientation);
    this.keyframes = new ArrayList();
  }

  /**
   * Constructs a {KeyframeAnimatedShapeImpl} that matches the details of the given {@code
   * AnimatedShape} {@param shape} except converts the log of the given shape from the idea of
   * motions into the notion of keyframes. In other words instead of having two shape states for a
   * time where one denotes the ending time of the previous motion and the other denotes the
   * beginning of the other now there is only one keyframe for each time.
   *
   * @param shape The shape that {@code this} {@code KeyframeAnimatedShape} will imitate.
   */
  private KeyframeAnimatedShapeImpl(AnimatedShape shape) {
    super(shape.getShapeType(), shape.getName(), shape.getTime(), shape.getBoundary(),
        shape.getColor(), shape.getPosn(), shape.getOrientation());
    this.keyframes = this.convertToKeyframes(shape.getLog());
  }

  /**
   * Constructs a {@code KeyFrameAnimatedShapeImpl} that matches the details of the given {@code
   * ReadOnlyAnimatedShape} {@param shape} except converts the log of the given shape from the idea
   * of motions into the notion of keyframes. In other words instead of having two shape states for
   * a time where one denotes the ending time of the previous motion and the other denotes the
   * beginning of the other now there is only one keyframe for each time.
   *
   * @param shape The shape that {@code this} {@code KeyframeAnimatedShape} will imitate.
   */
  public KeyframeAnimatedShapeImpl(ReadOnlyAnimatedShape shape) {
    super(shape.getShapeType(), shape.getName(),
        shape.getTime(), shape.getBoundary(),
        shape.getColor(), shape.getPosn(),
        shape.getOrientation());
    this.keyframes = this.convertToKeyframes(shape.getLog());
  }

  @Override
  public ArrayList<ReadOnlyAnimatedShape> getLog() {
    return new ArrayList(this.keyframes);
  }

  @Override
  public void log() {
    this.keyframes.add(new ReadOnlyKeyframeShape(new KeyframeAnimatedShapeImpl(this)));
    this.keyframes.sort(new Comparator<ReadOnlyAnimatedShape>() {
      @Override
      public int compare(ReadOnlyAnimatedShape shape1, ReadOnlyAnimatedShape shape2) {
        return shape1.getTime() - shape2.getTime();
      }
    });
  }

  @Override
  public void deleteKeyframe(int time) {
    for (ReadOnlyAnimatedShape shape : this.keyframes) {
      if (shape.getTime() == time) {
        this.keyframes.remove(shape);
        break;
      }
    }
  }

  /**
   * Converts an ArrayList of {@code ReadOnlyAnimatedShape}s that stores shape states in the idea of
   * motions into an ArrayList of {@code ReadOnlyAnimatedShape}s that stores shape states in the
   * idea of keyframes.
   *
   * @param animationHistory The list of past shape states in the idea of motions to be converted to
   *                         keyframes.
   * @return An ArrayList of shape states with only one state for each time.
   */
  private ArrayList<ReadOnlyAnimatedShape> convertToKeyframes(
      ArrayList<ReadOnlyAnimatedShape> animationHistory) {
    for (int i = 0; i < animationHistory.size() - 1; i++) {
      if (animationHistory.get(i).getTime() == animationHistory.get(i + 1).getTime()) {
        animationHistory.remove(i);
      }
    }
    return animationHistory;
  }

}
