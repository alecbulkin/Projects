package view;

import model.ReadOnlyAnimatedShape;
import model.Shapes;
import java.util.ArrayList;

/**
 * Represents an SVG View that prints out formatted svg text to whichever output stream is
 * specified.
 */
public class SVGView extends AbstractTextView {

  private final int ticksPerSecond;

  /**
   * Constructs an {@code SVGView} that prints formatted svg text of the set model to the output
   * stream {@code out}.
   *
   * @param out The output stream that {@code this} {@code SVGModel} will print to.
   */
  public SVGView(Appendable out, int ticksPerSecond) {
    super(null, out);
    this.ticksPerSecond = ticksPerSecond;
  }

  @Override
  protected String animationText() {
    String svg = "<svg width=\"" + this.model.getWidth() + "\" height=\"" + this.model.getHeight()
        + "\" version=\"1.1\" xmlns=\"http://www.w3.org/2000/svg\">\n";
    ArrayList<ReadOnlyAnimatedShape> animations;

    for (ReadOnlyAnimatedShape shape : this.model.getShapes()) {
      animations = shape.getLog();
      String type;
      ReadOnlyAnimatedShape initialShape = shape.getLog().get(0);

      if (shape.getShapeType() == Shapes.Rectangle) {
        type = "rect";
      } else if (shape.getShapeType() == Shapes.Oval) {
        type = "ellipse";
      } else {
        type = "polygon";
      }

      if (shape.getShapeType() == Shapes.Triangle) {
        svg += "<polygon points=\"" + (int) initialShape.getPosn().getX() + "," + (int) (
            this.model.getHeight() - initialShape.getPosn().getY()) + " "
            + (int) (initialShape.getPosn().getX() + initialShape.getBoundary().getWidth() / 2)
            + ","
            + (int) (this.model.getHeight() - (initialShape.getPosn().getY() + initialShape
            .getBoundary().getHeight())) + " "
            + (int) (initialShape.getPosn().getX() + initialShape.getBoundary().getWidth()) + ","
            + (int) (this.model.getHeight() - initialShape.getPosn().getY()) + "\" ";
      } else {
        svg += "<" + type;
      }
      if (shape.getShapeType() == Shapes.Oval) {
        svg += " id=\"" + shape.getName() + "\" cx=\""
            + (int) (initialShape.getPosn().getX() + initialShape.getBoundary().getWidth() / 2)
            + "\" cy=\""
            + (int) (initialShape.getPosn().getY() + initialShape.getBoundary().getHeight() / 2)
            + "\" rx=\""
            + (int) initialShape.getBoundary().getWidth() / 2 + "\" ry=\""
            + (int) initialShape.getBoundary().getHeight() / 2 + "\" ";
      } else if (shape.getShapeType() == Shapes.Rectangle) {
        svg += " id=\"" + shape.getName() + "\" x=\""
            + (int) initialShape.getPosn().getX() + "\" y=\"" + (int) initialShape.getPosn().getY()
            + "\" width=\""
            + (int) initialShape.getBoundary().getWidth() + "\" height=\"" + (int) initialShape
            .getBoundary().getHeight()
            + "\" ";
      }
      svg += "fill=\"rgb(" + initialShape.getColor().getRed() + "," + initialShape.getColor()
          .getGreen()
          + "," + initialShape.getColor().getBlue() + ")\" visibility=\"visible\" >\n";

      for (int i = 0; i < animations.size(); i = i + 2) {
        svg += this.shapeAnimationTextSVG(animations.get(i), animations.get(i + 1));
      }
      svg += "</" + type + ">\n";
    }
    return svg + "</svg>";
  }

  /**
   * Constructs the string that corresponds to the lines of svg code necessary to illustrate the
   * changes in {@param initialState} to the {finalState}.
   *
   * @param initialState The initial state of the svg animation.
   * @param finalState   The final state of the svg animation.
   * @return A string of SVG formatted code that represents the animation from the initial state to
   *         the final state.
   */
  private String shapeAnimationTextSVG(ReadOnlyAnimatedShape initialState,
      ReadOnlyAnimatedShape finalState) {
    String svg = "";
    Shapes shapeType = initialState.getShapeType();
    String positionX;
    String positionY;
    String dimX;
    String dimY;
    int finalPosnXVal;
    int finalPosnYVal;
    int finalDimXVal;
    int finalDimYVal;
    int initialPosnXVal;
    int initialPosnYVal;
    int initialDimXVal;
    int initialDimYVal;
    int initialOrientationVal;
    int finalOrientationVal;
    String motionBegin = "<animate attributeType=\"xml\" begin=\""
        + ((double) initialState.getTime() / this.ticksPerSecond) * 1000
        + "ms\" dur=\""
        + ((double) (finalState.getTime() - initialState.getTime()) / this.ticksPerSecond) * 1000
        + "ms\" ";
    if (shapeType == Shapes.Oval) {
      positionX = "cx";
      positionY = "cy";
      dimX = "rx";
      dimY = "ry";
      initialPosnXVal = (int) (initialState.getPosn().getX()
          + initialState.getBoundary().getWidth() / 2);
      initialPosnYVal = (int) (initialState.getPosn().getY()
          + initialState.getBoundary().getHeight() / 2);
      initialDimXVal = (int) initialState.getBoundary().getWidth() / 2;
      initialDimYVal = (int) initialState.getBoundary().getHeight() / 2;
      finalPosnXVal = (int) (finalState.getPosn().getX() + finalState.getBoundary().getWidth() / 2);
      finalPosnYVal = (int) (finalState.getPosn().getY()
          + finalState.getBoundary().getHeight() / 2);
      finalDimXVal = (int) finalState.getBoundary().getWidth() / 2;
      finalDimYVal = (int) finalState.getBoundary().getHeight() / 2;
      initialOrientationVal = initialState.getOrientation();
      finalOrientationVal = finalState.getOrientation();
    } else {
      positionX = "x";
      positionY = "y";
      dimX = "width";
      dimY = "ry";
      initialPosnXVal = (int) (initialState.getPosn().getX());
      initialPosnYVal = (int) (initialState.getPosn().getY());
      initialDimXVal = (int) initialState.getBoundary().getWidth();
      initialDimYVal = (int) initialState.getBoundary().getHeight();
      finalPosnXVal = (int) (finalState.getPosn().getX());
      finalPosnYVal = (int) (finalState.getPosn().getY());
      finalDimXVal = (int) finalState.getBoundary().getWidth();
      finalDimYVal = (int) finalState.getBoundary().getHeight();
      initialOrientationVal = initialState.getOrientation();
      finalOrientationVal = finalState.getOrientation();
    }
    if(initialOrientationVal != finalOrientationVal) {
      if(initialState.getShapeType() == Shapes.Rectangle) {
        svg += "<animateTransform attributeName=\"transform\" attributeType=\"XML\" "
            + "type=\"rotate\" from=\"" + initialState.getOrientation() + " "
            + (initialPosnXVal + initialDimXVal / 2) + " "
            + (initialPosnYVal + initialDimYVal / 2) + "\" to=\"" + finalState.getOrientation()
            + " "
            + (finalPosnXVal + finalDimXVal / 2) + " "
            + (finalPosnYVal + finalDimYVal / 2) + "\" dur=\""
            + (finalState.getTime() - initialState.getTime()) + "s\"/>\n";
      } else if(initialState.getShapeType() == Shapes.Oval) {
        svg += "<animateTransform attributeName=\"transform\" attributeType=\"XML\" "
            + "type=\"rotate\" from=\"" + initialState.getOrientation() + " "
            + (initialPosnXVal) + " "
            + (initialPosnYVal) + "\" to=\"" + finalState.getOrientation()
            + " "
            + (finalPosnXVal) + " "
            + (finalPosnYVal) + "\" dur=\""
            + (finalState.getTime() - initialState.getTime()) + "s\"/>\n";
      } else if(initialState.getShapeType() == Shapes.Triangle) {
        svg += "<animateTransform attributeName=\"transform\" attributeType=\"XML\" "
            + "type=\"rotate\" from=\"" + initialState.getOrientation() + " "
            + (initialPosnXVal + initialDimXVal/2) + " "
            + (initialPosnYVal - initialDimYVal/2) + "\" to=\"" + finalState.getOrientation()
            + " "
            + (finalPosnXVal + finalDimXVal/2) + " "
            + (finalPosnYVal - finalDimYVal/2) + "\" dur=\""
            + (finalState.getTime() - initialState.getTime()) + "s\"/>\n";
      }
    } else {
      if (initialPosnXVal != finalPosnXVal && shapeType != Shapes.Triangle) {
        svg += motionBegin + "attributeName=\"" + positionX + "\" from=\"" + initialPosnXVal
            + "\" to=\"" + finalPosnXVal + "\" fill=\"freeze\" />\n";
      }
      if (initialPosnYVal != finalPosnYVal && shapeType != Shapes.Triangle) {
        svg += motionBegin + "attributeName=\"" + positionY + "\" from=\"" + initialPosnYVal
            + "\" to=\"" + finalPosnYVal + "\" fill=\"freeze\" />\n";
      }
      if (!initialState.getColor().equals(finalState.getColor())) {
        svg +=
            motionBegin + "attributeName=\"" + "fill\" from=\"rgb(" + initialState.getColor()
                .getRed()
                + "," + initialState.getColor().getGreen() + "," + initialState.getColor().getBlue()
                + ")\" to=\"rgb(" + finalState.getColor().getRed() + ","
                + finalState.getColor().getGreen() + "," + finalState.getColor().getBlue()
                + ")\" fill=\"freeze\" />\n";
      }
      if (initialDimXVal != finalDimXVal && shapeType != Shapes.Triangle) {
        svg += motionBegin + "attributeName=\"" + dimX + "\" from=\"" + initialDimXVal
            + "\" to=\"" + finalDimXVal + "\" fill=\"freeze\" />\n";
      }
      if (initialDimYVal != finalDimYVal && shapeType != Shapes.Triangle) {
        svg += motionBegin + "attributeName=\"" + dimY + "\" from=\"" + initialDimYVal
            + "\" to=\"" + finalDimYVal + "\" fill=\"freeze\" />\n";
      }
      if (shapeType == Shapes.Triangle) {
        if (initialState.getPosn().getX() != finalState.getPosn().getX()
            || initialState.getPosn().getY() != finalState.getPosn().getY()
            || initialState.getBoundary().getWidth() != finalState.getBoundary().getWidth()
            || initialState.getBoundary().getHeight() != finalState.getBoundary().getHeight()) {
          svg += motionBegin + "attributeName=\"points\" from=\""
              + (int) initialState.getPosn().getX() + "," + (int) (this.model.getHeight()
              - initialState.getPosn().getY()) + " "
              + (int) (initialState.getPosn().getX() + initialState.getBoundary().getWidth() / 2)
              + ","
              + (int) (this.model.getHeight() - (initialState.getPosn().getY() + initialState
              .getBoundary().getHeight())) + " "
              + (int) (initialState.getPosn().getX() + initialState.getBoundary().getWidth()) + ","
              + (int) (this.model.getHeight() - initialState.getPosn().getY()) + "\" to=\""
              + (int) finalState.getPosn().getX() + "," + (int) (this.model.getHeight() - finalState
              .getPosn().getY()) + " "
              + (int) (finalState.getPosn().getX() + finalState.getBoundary().getWidth() / 2) + ","
              + (int) (this.model.getHeight() - (finalState.getPosn().getY() + finalState
              .getBoundary().getHeight())) + " "
              + (int) (finalState.getPosn().getX() + finalState.getBoundary().getWidth()) + ","
              + (int) (this.model.getHeight() - finalState.getPosn().getY())
              + "\" fill=\"freeze\" />\n";
        }

      }

      if (initialDimXVal == finalDimXVal && initialDimYVal == finalDimYVal
          && initialPosnXVal == finalPosnXVal
          && initialPosnYVal == finalPosnYVal && initialState.getColor()
          .equals(finalState.getColor())) {
        svg += motionBegin + "fill=\"freeze\" />\n";
      }
    }
    return svg;
  }
}
