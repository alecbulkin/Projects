package view;

import model.ReadOnlyAnimatedShape;
import java.util.ArrayList;

/**
 * Represents a text only view. Prints out the details of an {@code AnimationEnvironment} as a
 * string to the given output stream.
 */
public class TextView extends AbstractTextView {

  /**
   * Constructs a {@code TextView} that refers to the given {@code ReadOnlyAnimationEnvironment}
   * {@param model}.
   *
   * @param out The output stream that {@code this} {@code TextView} will display to.
   */
  public TextView(Appendable out) {
    //specifically assign null here because I specifically mean it to refer to the absence of a
    //model and use it to throw an exception if no model has been assigned.
    super(null, out);
  }

  /**
   * Returns a text Representation of {@code this} {@code AnimationEnvironment}.
   *
   * <p>The Assignment was unclear about how exactly it was expected that this function output the
   * animation and how exactly animations were to be constrained, so we assumed the following
   * things: </p>
   *
   * <ul>
   *   <li>Animations must be executed in chronological order and the time periods
   *   must be synchronous.</li>
   *   <li>There are no overlapping animation time periods.</li>
   *   <li>If no animation commands have been given then the textAnimation
   *   string should simply display the canvas info.</li>
   * </ul>
   *
   * @return A text representing all of the animation commands for each shape in {@code this} {@code
   * AnimationEnvironment}.
   */
  protected String animationText() {
    String animation =
        "canvas " + this.model.getX() + " " + this.model.getY() + " " + this.model.getWidth() + " "
            + this.model.getHeight() + "\n";
    for (ReadOnlyAnimatedShape shape : this.model.getShapes()) {
      String shapeName = shape.getName();
      ArrayList<ReadOnlyAnimatedShape> shapeAnimations = shape.getLog();
      animation += "Shape " + shapeName + " " + shape.getShapeType().toString() + "\n";
      for (int i = 0; i < shapeAnimations.size(); i = i + 2) {
        animation += "Motion " + shapeName + " " + shapeAnimations.get(i).toString() + "  "
            + shapeAnimations.get(i + 1).toString() + "\n";
        if (shapeAnimations.size() - i > 2 &&
            shapeAnimations.get(i + 1).getTime() > shapeAnimations.get(i + 2).getTime()) {
          animation += "Motion " + shapeName + " " + shapeAnimations.get(i + 2).toString() + "  "
              + shapeAnimations.get(i + 3);
        }
      }
      animation += "\n";
    }
    return animation;
  }
}
