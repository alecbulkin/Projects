package model;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

/**
 * Represents an {@code AnimationEnvironment} that supports removing the shapes within it
 * and with {@code KeyframeAnimatedShape}s instead of {@code AnimatedShape}s.
 */
public interface KeyframeAnimationEnvironment {

  /**
   * Returns the x coordinate of the top left corner of {@code this}
   * {@code KeyframeAnimationEnvironment}.
   *
   * @return The x coordinate of the top left corner of {@code this}
   *         {@code KeyframeAnimationEnvironment}.
   */
  int getX();

  /**
   * Returns the y coordinate of the top left corner of {@code this}
   * {@code KeyframeAnimationEnvironment}.
   *
   * @return The y coordinate of the top left corner of {@code this}
   *         {@code KeyframeAnimationEnvironment}.
   */
  int getY();

  /**
   * Returns the height of {@code this} {@code KeyframeAnimationEnvironment}.
   *
   * @return The height of {@code this} {@code KeyframeAnimationEnvironment}.
   */
  int getHeight();

  /**
   * Returns the width of {@code this} {@code KeyframeAnimationEnvironment}.
   *
   * @return The width of {@code this} {@code KeyframeAnimationEnvironment}.
   */
  int getWidth();

  /**
   * Returns the shapes that are stored in {@code this} {@code KeyframeAnimationEnvironment}
   * represented as immutable {@code ReadOnlyAnimatedShape}s.
   *
   * @return An array of immutable {@code ReadOnlyAnimatedShape}s that represent the shapes stored
   *         in {@code this} {@code KeyframeAnimationEnvironment}.
   */
  ArrayList<ReadOnlyAnimatedShape> getShapes();

  /**
   * Adds a shape with the given parameter attributes as a keyframe to {@code this}
   * {@code KeyframeAnimationEnvironment}.
   *
   * @param shapeType The type of shape to add.
   * @param shapeName The name of the shape to be added.
   * @param time The time that the first keyframe of the new shape should be at.
   * @param boundary The boundary of the rectangle that bounds the shape to be added at the first
   *                 keyframe of the new shape.
   * @param color The color of the shape to be added at the first keyframe it will be added at.
   * @param posn The position of the shape to be added at the first keyframe it will be added at.
   * @param orientation The angled oreintation of the shape to be created.
   * @throws IllegalArgumentException If there is already a shape with the given {@param shapeName}
   *                                  in {@code this} {@code KeyframeAnimationEnvironment}.
   */
  void addShape(Shapes shapeType, String shapeName, int time, Dimension boundary,
      Color color, Position2D posn, int orientation);

  /**
   * Deletes the shape that corresponds to the given {@param shapeName} in {@code this}
   * {@code KeyframeAnimationEnvironment}.
   *
   * @param shapeName The name of the shape to be deleted in {@code this}
   *                  {@code KeyframeAnimationEnvironment}.
   * @throws IllegalArgumentException If there is no shape in {@code this}
   *                                  {@code KeyframeAnimationEnvironment} corresponding to the
   *                                  {@param shapeName}.
   */
  void deleteShape(String shapeName);

  /**
   * Adds a keyframe with the provided attributes to the shape that corresponds to the name
   * {@param shapeName}.
   *
   * @param shapeName The name of the shape that the given keyframe should be added to.
   * @param time The time that the new keyframe should be added at.
   * @param boundary The dimensions of the rectangle that bounds the shape at the new keyframe.
   * @param color The color of the shape at the new keyframe.
   * @param posn The position of the shape at the new keyframe.
   * @param orientation The angled orientation of the keyframe to be added
   * @throws IllegalArgumentException If there is not shape corresponding to the given
   *                                  {@param shapeName} in {@code this}
   *                                  {@code KeyframeAnimationEnvironment}, or if there already
   *                                  exists a keyframe for the shape corresponding to the
   *                                  {@param shapeName} at the given {@param time}.
   */
  void addKeyframe(String shapeName, int time, Dimension boundary, Color color, Position2D posn,
      int orientation);

  /**
   * Deletes the keyframe at the given time of the shape in {@code this}
   * {@code KeyframeAnimationEnvironment} that corresponds to the given {@param shapeName}.
   *
   * @param shapeName The name of the shape that the keyframe should be deleted from.
   * @param time The time of the keyframe to be deleted
   * @throws IllegalArgumentException If there is no shape corresponding to {@param shapeName} in
   *                                  {@code this} {@code KeyframeAnimationEnvironment}, or if
   *                                  there is no keyframe at the given {@param time} in the shape
   *                                  that corresponds to the {@param shapeName}.
   */
  void deleteKeyframe(String shapeName, int time);


  /**
   * Edits the keyframe that corresponds to the keyframe at the given time for the given shape
   * to have the new attributes specified in the parameters.
   *
   * @param shapeName The name of the shape that should have a keyframe edited.
   * @param time The time of the keyframe to be edited.
   * @param boundary The new dimensions of the rectangle that constrains the
   *                 shape at the corresponding keyframe.
   * @param color The new color of the shape at the corresponding keyframe.
   * @param posn The new position of the shape at the corresponding keyframe.
   * @param orientation The new angled orientation of the corresponding keyframe.
   * @throws IllegalArgumentException If there is no corresponding shape to the given
   *                                  {@param shapeName}, or if there is no keyframe in the
   *                                  corresponding shape at the given {@param time}.
   */
  void editKeyframe(String shapeName, int time, Dimension boundary, Color color, Position2D posn,
      int orientation);

}
