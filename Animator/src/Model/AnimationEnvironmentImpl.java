package model;

import controller.AnimationBuilder;
import model.commands.Move;
import model.commands.Paint;
import model.commands.Rotate;
import model.commands.Scale;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents an Animation environment, where the user can create shapes and animate them in the
 * environment.
 */
public class AnimationEnvironmentImpl implements AnimationEnvironment {

  private final int x;
  private final int y;
  private final int height;
  private final int width;
  private ArrayList<AnimatedShape> shapes;

  /**
   * Constructs an {@code AnimationEnvironmentImpl} with a given environment height and width and an
   * initially empty list of shapes.
   *
   * @param height The height of {@code this} {@code AnimationEnvironment}.
   * @param width  The width of {@code this} {@code AnimationEnvironment}.
   * @throws IllegalArgumentException if the height or width are not positive.
   */
  private AnimationEnvironmentImpl(int x, int y, int width, int height) {
    if (width < 1 || height < 1) {
      throw new IllegalArgumentException("Invalid Dimensions for the environment!");
    }
    this.x = x;
    this.y = y;
    this.height = height;
    this.width = width;
    this.shapes = new ArrayList<AnimatedShape>();
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }

  @Override
  public void addShape(AnimatedShape shape) {
    this.shapes.add(shape);
  }



  @Override
  public void commandShape(String name, int initialTime, int finalTime, Command... commands) {

    if (name == null) {
      throw new IllegalArgumentException("Must have a valid name!");
    }
    if (initialTime < 0 || initialTime > finalTime) {
      throw new IllegalArgumentException("Invalid time segment!");
    }
    AnimatedShape shape = this.getShape(name);
    this.validateTimes(shape, initialTime, finalTime);
    if (initialTime != shape.getTime()) {
      shape.log();
      shape.setTime(initialTime);
      shape.log();
      shape.log();
    } else {
      shape.log();
    }
    shape.setTime(finalTime);
    for (Command cmd : commands) {
      cmd.apply(shape);
    }
    shape.log();
  }

  /**
   * Determines if the given shape {@param shapeName} exists in {@code this} {@code
   * AnimationEnvironment}'s shapes.
   *
   * @param shapeName The name of the shape that might be in {@code this} {@code
   *                  AnimationEnvironment}.
   * @return a boolean value where true indicates that the shape with the given name
   *         {@param shapeName} is in {@code this} {@code AnimationEnvironment}.
   */
  private boolean shapeExists(String shapeName) {
    boolean shapeExists = false;
    for (AnimatedShape shape : this.shapes) {
      if (shape.getName().equals(shapeName)) {
        shapeExists = true;
        break;
      }
    }
    return shapeExists;
  }

  /**
   * Returns the shape that corresponds to the {@param shapeName} in {@code this} {@code
   * AnimationEnviornment}'s list of shapes.
   *
   * @param shapeName The name of the {@code AnimatedShape} to search for.
   * @return The {@code AnimatedShape} that corresponds tho {@param shapeName}.
   * @throws IllegalArgumentException if there is not shape corresponding to the name {@param
   *                                  shapeName} in {@code this} {@code AnimationEnvironment}.
   */
  private AnimatedShape getShape(String shapeName) {
    for (AnimatedShape s : this.shapes) {
      if (s.getName().equalsIgnoreCase(shapeName)) {
        return s;
      }
    }
    throw new IllegalArgumentException("Shape does not exist!");
  }

  @Override
  public ArrayList<ReadOnlyAnimatedShape> getShapes() {
    ArrayList<ReadOnlyAnimatedShape> immutableShapes = new ArrayList();
    for (AnimatedShape shape : this.shapes) {
      immutableShapes.add(new ReadOnlyAnimatedShapeImpl(shape));
    }
    return immutableShapes;
  }

  /**
   * Provides a builder that can be used to construct and 'build' an {@code AnimationEnvironment}.
   *
   * @return A builder that can be used to construct an instance of an {@code AnimationEnvironment}.
   */
  public static Builder builder() {
    return new Builder();
  }

  /**
   * Validates that the given time period does not overlap with any existing enimation commands in
   * the given {@param shape}.
   *
   * @param shape       The shape to compare the time intervals with.
   * @param intiialTime The initial time of the time interval.
   * @param finalTime   The final time of the  time interval.
   * @throws IllegalArgumentException if the time interval overlaps with an existing one in the
   *                                  {@param shape}s animation history.
   */
  private void validateTimes(AnimatedShape shape, int intiialTime, int finalTime) {
    ArrayList<ReadOnlyAnimatedShape> animations = shape.getLog();
    for (int i = 0; i < animations.size(); i = i + 2) {
      if (intiialTime > animations.get(i).getTime() && intiialTime < animations.get(i + 1).getTime()
          || finalTime > animations.get(i).getTime() && finalTime < animations.get(i + 1).getTime()
          || animations.get(i).getTime() > intiialTime && animations.get(i).getTime() < finalTime
          || animations.get(i + 1).getTime() > intiialTime
          && animations.get(i + 1).getTime() < finalTime) {
        throw new IllegalArgumentException("Animation periods cannot be overlapping!");
      }
    }
  }

  /**
   * An Builder that builds an animation using {@code AnimationEnvironmentImpl}.
   */
  public static final class Builder implements AnimationBuilder<AnimationEnvironment> {

    AnimationEnvironment buildee;
    ArrayList<List<String>> undeclaredShapes;

    /**
     * Constructs a Builder with the default location and dimensions set to (0, 0) and 100x100.
     */
    public Builder() {
      buildee = new AnimationEnvironmentImpl(0, 0, 100, 100);
      undeclaredShapes = new ArrayList();
    }

    /**
     * Constructs a Builder that builds upon the given {@code AnimationEnvironment} {@param
     * buildee}.
     *
     * @param buildee The {@code AnimationEnvironment} that {@code this} {@code Builder} will build
     *                upon.
     */
    private Builder(AnimationEnvironment buildee) {
      this.buildee = buildee;
    }

    @Override
    public AnimationEnvironment build() {
      return this.buildee;
    }

    @Override
    public AnimationBuilder<AnimationEnvironment> setBounds(int x, int y, int width, int height) {
      this.buildee = new AnimationEnvironmentImpl(x, y, width, height);
      return this;
    }

    //Stores this shape within the builder until it is given a motion -- because in our
    //implementation of the environment a shape with no initial values does not exist.
    @Override
    public AnimationBuilder<AnimationEnvironment> declareShape(String name, String type) {
      this.undeclaredShapes.add(Arrays.asList(name, type));
      return this;
    }

    //If the shape that is being commanded is undeclared then we add that shape to the environment
    //and then proceed to give it commands. Since we cannot edit this Builder interface, we cannot
    //cleanly edit this function in order to support rotation. Although we will not be able to
    //support it we have decided to leave all of the prior code referencing and tracking
    //orientation with the intention that if we were able to change the code that was
    //given to us in this builder in the future then we would be able to support it.
    @Override
    public AnimationBuilder<AnimationEnvironment> addMotion(String name, int t1, int x1, int y1,
        int w1, int h1, int r1, int g1, int b1, int t2, int x2, int y2, int w2, int h2, int r2,
        int g2, int b2) {
      if (this.undeclared(name)) {
        List<String> undShape = this.getUndeclaredShape(name);
        this.buildee.addShape(new AnimatedShapeImpl(this.getUndeclaredShapeType(undShape), name, t1,
            new Dimension(w1, h1), new Color(r1, g1, b1), new Position2D(x1, y1), 0));
        this.undeclaredShapes.remove(undShape);
      }
      this.buildee.commandShape(name, t1, t2, new Move(x1, y1, x2, y2), new Scale(w1, h1, w2, h2),
          new Paint(r1, g1, b1, r2, g2, b2));
      return this;
    }

    @Override
    public AnimationBuilder<AnimationEnvironment> addMotion(String name, int t1, int x1, int y1,
        int w1, int h1, int r1, int g1, int b1, int o1, int t2, int x2, int y2, int w2, int h2, int r2,
        int g2, int b2, int o2) {
      if (this.undeclared(name)) {
        List<String> undShape = this.getUndeclaredShape(name);
        this.buildee.addShape(new AnimatedShapeImpl(this.getUndeclaredShapeType(undShape), name, t1,
            new Dimension(w1, h1), new Color(r1, g1, b1), new Position2D(x1, y1), o1));
        this.undeclaredShapes.remove(undShape);
      }
      this.buildee.commandShape(name, t1, t2, new Move(x1, y1, x2, y2), new Scale(w1, h1, w2, h2),
          new Paint(r1, g1, b1, r2, g2, b2), new Rotate(o1, o2));
      return this;
    }

    //We read on piazza that this function was possibly being used in the future
    //Although the docs are vague regarding what exactly this function is supposed to do
    //We assume that it should create a 'snapshot' of an undeclared animated shape at the given
    //time and state. So we made it create a shape, add it to this environment, and log the given
    //state in the shape's animation history. Again this does not support our orientation aspect
    //So the keyframe is made with an orientation of 0.
    @Override
    public AnimationBuilder<AnimationEnvironment> addKeyframe(String name, int t, int x, int y,
        int w, int h, int r, int g, int b) {
      AnimatedShape keyFrame = new AnimatedShapeImpl(
          this.getUndeclaredShapeType(this.getUndeclaredShape(name)), name, t,
          new Dimension(w, h), new Color(r, g, b), new Position2D(x, y), 0);
      this.buildee.addShape(keyFrame);
      keyFrame.log();
      return this;
    }

    @Override
    public AnimationBuilder<AnimationEnvironment> addKeyframe(String name, int t, int x, int y,
        int w, int h, int r, int g, int b, int o) {
      AnimatedShape keyFrame = new AnimatedShapeImpl(
          this.getUndeclaredShapeType(this.getUndeclaredShape(name)), name, t,
          new Dimension(w, h), new Color(r, g, b), new Position2D(x, y), o);
      this.buildee.addShape(keyFrame);
      keyFrame.log();
      return this;
    }

    /**
     * Returns a boolean value that indicates whether or not there is an undeclared shape that
     * corresponds to the given {@param name}.
     *
     * @param name The name of the undeclared shape to be found.
     * @return A boolean value where true indicates that there is a shape that corresponds to the
     *         given {@param name}.
     */
    private boolean undeclared(String name) {
      for (List shape : this.undeclaredShapes) {
        if (shape.contains(name)) {
          return true;
        }
      }
      return false;
    }

    /**
     * Returns the undeclared shape that corresponds to the given {@param name}.
     *
     * @param name The name of the undeclared shape to get.
     * @return The undeclared shape that the given {@param name} corresponds to.
     * @throws IllegalArgumentException if there are no undeclared shapes corresponding to {@param
     *                                  name}.
     */
    private List<String> getUndeclaredShape(String name) {
      for (List<String> shape : this.undeclaredShapes) {
        if (shape.contains(name)) {
          return shape;
        }
      }
      throw new IllegalArgumentException("There are no undeclared shapes with that name!");
    }

    /**
     * Returns the type of shape that the given {@param undeclaredShape} is as a {@code Shapes}.
     *
     * @param undeclaredShape The undeclared shape that this method will return the type of.
     * @return The type of shape that the given {@param undeclaredShape} is as a {@code Shapes}.
     */
    private Shapes getUndeclaredShapeType(List<String> undeclaredShape) {
      if (undeclaredShape.get(1).equalsIgnoreCase("ellipse")
          || undeclaredShape.get(1).equalsIgnoreCase("oval")) {
        return Shapes.Oval;
      } else if (undeclaredShape.get(1).equalsIgnoreCase("rectangle")) {
        return Shapes.Rectangle;
      } else if (undeclaredShape.get(1).equalsIgnoreCase("triangle")) {
        return Shapes.Triangle;
      } else {
        return null;
      }
    }

  }
}