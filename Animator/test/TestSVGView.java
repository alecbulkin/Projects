import model.ReadOnlyAnimationEnvironmentImpl;
import org.junit.Test;

import java.awt.Dimension;
import java.awt.Color;

import static org.junit.Assert.assertEquals;

import model.AnimatedShape;
import model.AnimatedShapeImpl;
import model.AnimationEnvironment;
import model.AnimationEnvironmentImpl;
import model.commands.Move;
import model.commands.Paint;
import model.commands.Rotate;
import model.commands.Scale;
import model.Position2D;
import model.Shapes;
import view.SVGView;

/**
 * Tests that the {@code SVGView} works as expected. Tests that the {@code SVGView} view is able
 * to accurately format the information from the set model into svg code.
 */
public class TestSVGView {

  StringBuilder out1 = new StringBuilder();
  StringBuilder out2 = new StringBuilder();

  AnimationEnvironment environment1 =
      AnimationEnvironmentImpl.builder().setBounds(0, 0, 5, 5).build();

  AnimatedShape redRect = new AnimatedShapeImpl(Shapes.Rectangle, "Red Rectangle", 0,
      new Dimension(7, 5), new Color(255, 0, 0), new Position2D(20, 25),
      0);

  AnimatedShape blueCircle = new AnimatedShapeImpl(Shapes.Oval, "Blue Circle", 3,
      new Dimension(3, 3), new Color(0, 0, 255), new Position2D(0, 0),
      25);

  AnimatedShape greenTriangle = new AnimatedShapeImpl(Shapes.Triangle, "Green Triangle", 10,
      new Dimension(5, 10), new Color(0, 255, 0), new Position2D(-15, -15),
      0);

  SVGView v1 = new SVGView(out1, 1);

  SVGView v2 = new SVGView(out2, 2);

  @Test(expected = IllegalArgumentException.class)
  public void testNullModel() {
    v1.display();
  }

  @Test
  public void testSetModelandTextView() {
    v1.setModel(new ReadOnlyAnimationEnvironmentImpl(environment1));

    v1.display();

    assertEquals("<svg width=\"5\" height=\"5\" version=\"1.1\" " +
        "xmlns=\"http://www.w3.org/2000/svg\">\n" +
        "</svg>", out1.toString());

    environment1.addShape(redRect);
    environment1.addShape(blueCircle);
    environment1.addShape(greenTriangle);

    v2.setModel(new ReadOnlyAnimationEnvironmentImpl(environment1));

    this.environment1.commandShape("Red Rectangle", 0, 5,
        new Move(20, 25, 0, 0),
        new Scale(7, 5, 10, 10));
    this.environment1.commandShape("Red Rectangle", 7, 10,
        new Move(0, 0, 5, 5),
        new Paint(255, 0, 0, 0, 0, 0),
        new Rotate(0, 25));
    this.environment1.commandShape("Green Triangle", 11, 15,
        new Move(-15, -15, 0, 0));
    this.environment1.commandShape("Blue Circle", 11, 15,
        new Move(0, 0, 100, 100));

    v2.display();

    assertEquals("<svg width=\"5\" height=\"5\" version=\"1.1\" " +
        "xmlns=\"http://www.w3.org/2000/svg\">\n" +
        "<rect id=\"Red Rectangle\" x=\"20\" y=\"25\" width=\"7\" height=\"5\" " +
        "fill=\"rgb(255,0,0)\" visibility=\"visible\" >\n" +
        "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"2500.0ms\" attributeName=\"x\" " +
        "from=\"20\" to=\"0\" fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"2500.0ms\" attributeName=\"y\" " +
        "from=\"25\" to=\"0\" fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"2500.0ms\" " +
        "attributeName=\"width\" from=\"7\" to=\"10\" fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"0.0ms\" dur=\"2500.0ms\" " +
        "attributeName=\"ry\" from=\"5\" to=\"10\" fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"2500.0ms\" dur=\"1000.0ms\" " +
        "fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"3500.0ms\" dur=\"1500.0ms\" " +
        "attributeName=\"x\" from=\"0\" to=\"5\" fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"3500.0ms\" dur=\"1500.0ms\" " +
        "attributeName=\"y\" from=\"0\" to=\"5\" fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"3500.0ms\" dur=\"1500.0ms\" " +
        "attributeName=\"fill\" from=\"rgb(255,0,0)\" to=\"rgb(0,0,0)\" fill=\"freeze\" />\n" +
        "</rect>\n" +
        "<ellipse id=\"Blue Circle\" cx=\"1\" cy=\"1\" rx=\"1\" ry=\"1\" " +
        "fill=\"rgb(0,0,255)\" visibility=\"visible\" >\n" +
        "<animate attributeType=\"xml\" begin=\"1500.0ms\" dur=\"4000.0ms\" " +
        "fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"5500.0ms\" dur=\"2000.0ms\" " +
        "attributeName=\"cx\" from=\"1\" to=\"101\" fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"5500.0ms\" dur=\"2000.0ms\" " +
        "attributeName=\"cy\" from=\"1\" to=\"101\" fill=\"freeze\" />\n" +
        "</ellipse>\n" +
        "<polygon points=\"-15,20 -12,10 -10,20\" fill=\"rgb(0,255,0)\" " +
        "visibility=\"visible\" >\n" +
        "<animate attributeType=\"xml\" begin=\"5000.0ms\" dur=\"500.0ms\" " +
        "fill=\"freeze\" />\n" +
        "<animate attributeType=\"xml\" begin=\"5500.0ms\" dur=\"2000.0ms\" " +
        "attributeName=\"points\" from=\"-15,20 -12,10 -10,20\" to=\"0,5 2,-5 5,5\" " +
        "fill=\"freeze\" />\n" +
        "</polygon>\n" +
        "</svg>", out2.toString());
  }
}