package view;

import java.awt.geom.AffineTransform;
import model.ReadOnlyAnimatedShape;
import model.ReadOnlyAnimationEnvironment;
import model.Shapes;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;

/**
 * Represents the Visual View Panel for the Visual View. Handles the animation and illustration of
 * the set {@code AnimationEnvironment}.
 */
public class AnimationPanel extends JPanel implements ActionListener {

  private ReadOnlyAnimationEnvironment model;
  protected int ticks;
  protected boolean isLooping;

  /**
   * Constructs an {@code AnimationPanel} with the initial {@code ticks} value equal to 0 --
   * essentially starting the animation at time 0. Additionally initializes the panel to only play
   * once by default -- rather than looping.
   */
  public AnimationPanel() {
    super();
    this.ticks = 0;
    this.isLooping = false;
    this.model = null;
  }

  /**
   * toggles the looping of {@code this} {@code AnimationPanel} to be true if it is currently false
   * and false if it is currently true.
   */
  public void toggleLooping() {
    this.isLooping = !this.isLooping;
  }

  /**
   * Sets the tick value of the current time of {@code this} {@code AnimationPanel} to the given
   * {@param tick}.
   *
   * @param tick The specific tick that {@code this} {@code AnimationPanel} should be set to.
   */
  public void setTick(int tick) {
    this.ticks = tick;
  }

  /**
   *
   *
   * @return
   */
  public int getTick() {
    return this.ticks;
  }

  /**
   * Sets the model for {@code this} {@code AnimationPanel} to illustrate.
   *
   * @param model The {@code AnimationEnvironment} that {@code this} {@code AnimationPanel} will
   *              animate.
   */
  public void setModel(ReadOnlyAnimationEnvironment model) {
    this.model = model;
    this.setPreferredSize(new Dimension(this.model.getWidth(), this.model.getHeight()));
    this.setBackground(new Color(0, 0, 0));
  }

  /**
   * Paints the {@code AnimationEnvironment} {@code model} at the time {@code ticks}.
   *
   * @param g The graphics component used to paint this frame.
   * @throws IllegalArgumentException if the shape type is unsupported.
   */
  @Override
  protected void paintComponent(Graphics g) {
    if (this.model == null) {
      throw new IllegalArgumentException("Model must be assigned!");
    }
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;
    g2D.setBackground(Color.BLACK);
    ReadOnlyAnimatedShape beginState;
    ReadOnlyAnimatedShape endState;
    int beginTime;
    int endTime;
    double beginMultiplier;
    double endMultiplier;

    for (ReadOnlyAnimatedShape shape : this.model.getShapes()) {

      for (int i = 0; i < shape.getLog().size() - 1; i++) {
        if (shape.getLog().get(i).getTime() == shape.getLog().get(i + 1).getTime()) {
          i++;
        }
        if (this.ticks >= shape.getLog().get(i).getTime() && this.ticks <= shape.getLog().get(i + 1)
            .getTime()) {
          beginState = shape.getLog().get(i);
          endState = shape.getLog().get(i + 1);
          beginTime = beginState.getTime();
          endTime = endState.getTime();
          beginMultiplier = (double) (endTime - this.ticks) / (endTime - beginTime);
          endMultiplier = (double) (this.ticks - beginTime) / (endTime - beginTime);
          double rotation;
          if(beginState.getOrientation() == 360.0) {
            rotation = Math.toRadians(endState.getOrientation() * endMultiplier);
          } else {
            rotation = Math.toRadians((beginState.getOrientation() * beginMultiplier)
                + (endState.getOrientation() * endMultiplier));
          }

          double xTransformation = (beginState.getPosn().getX() +
              beginState.getBoundary().getWidth()/2) * beginMultiplier
              + (endState.getPosn().getX() + endState.getBoundary().getWidth()/2) * endMultiplier;
          double yTransformation;

          if(shape.getShapeType() == Shapes.Triangle) {
            yTransformation = (beginState.getPosn().getY()
                - beginState.getBoundary().getHeight() / 2) * beginMultiplier
                + (endState.getPosn().getY()
                - endState.getBoundary().getHeight() / 2) * endMultiplier;
          } else {
            yTransformation = (beginState.getPosn().getY()
                + beginState.getBoundary().getHeight() / 2) * beginMultiplier
                + (endState.getPosn().getY()
                + endState.getBoundary().getHeight() / 2) * endMultiplier;
          }
          Graphics2D copy = (Graphics2D) g.create();

          copy.rotate(rotation,
              xTransformation, yTransformation);

          copy.setColor(new Color(
              (int) (beginState.getColor().getRed() * beginMultiplier
                  + endState.getColor().getRed() * endMultiplier),
              (int) (beginState.getColor().getGreen() * beginMultiplier
                  + endState.getColor().getGreen() * endMultiplier),
              (int) (beginState.getColor().getBlue() * beginMultiplier
                  + endState.getColor().getBlue() * endMultiplier)));

          if (shape.getShapeType() == Shapes.Rectangle) {
            copy.fillRect(
                (int) (beginState.getPosn().getX() * beginMultiplier
                    + endState.getPosn().getX() * endMultiplier),
                (int) (beginState.getPosn().getY() * beginMultiplier
                    + endState.getPosn().getY() * endMultiplier),
                (int) (beginState.getBoundary().getWidth() * beginMultiplier
                    + endState.getBoundary().getWidth() * endMultiplier),
                (int) (beginState.getBoundary().getHeight() * beginMultiplier
                    + endState.getBoundary().getHeight() * endMultiplier));
          } else if (shape.getShapeType() == Shapes.Oval) {
            copy.fillOval(
                (int) (beginState.getPosn().getX() * beginMultiplier
                    + endState.getPosn().getX() * endMultiplier),
                (int) (beginState.getPosn().getY() * beginMultiplier
                    + endState.getPosn().getY() * endMultiplier),
                (int) (beginState.getBoundary().getWidth() * beginMultiplier
                    + endState.getBoundary().getWidth() * endMultiplier),
                (int) (beginState.getBoundary().getHeight() * beginMultiplier
                    + endState.getBoundary().getHeight() * endMultiplier));
          } else if (shape.getShapeType() == Shapes.Triangle) {
            int[] xPoints = {(int) (beginState.getPosn().getX() * beginMultiplier
                + endState.getPosn().getX() * endMultiplier),
                (int) ((beginState.getPosn().getX() + beginState.getBoundary().getWidth() / 2)
                    * beginMultiplier
                    + (endState.getPosn().getX() + endState.getBoundary().getWidth() / 2)
                    * endMultiplier),
                (int) ((beginState.getPosn().getX() + beginState.getBoundary().getWidth())
                    * beginMultiplier
                    + (endState.getPosn().getX() + endState.getBoundary().getWidth())
                    * endMultiplier)};
            int[] yPoints = {(int) ((beginState.getPosn().getY()) * beginMultiplier
                + (endState.getPosn().getY()) * endMultiplier),
                (int) (((beginState.getPosn().getY() - beginState.getBoundary().getHeight()))
                    * beginMultiplier
                    + ((endState.getPosn().getY() - endState.getBoundary().getHeight()))
                    * endMultiplier),
                (int) ((beginState.getPosn().getY()) * beginMultiplier
                    + (endState.getPosn().getY()) * endMultiplier)};
            copy.fillPolygon(xPoints, yPoints, 3);
          } else {
            throw new IllegalArgumentException("Unsupported/Invalid shape");
          }
          copy.rotate(-rotation, xTransformation, yTransformation);
          copy.dispose();
          break;
        }
      }
    }
  }

  /**
   * Increases the tick value of {@code this} {@code AnimationPanel} -- essentially moves time
   * forward with every call -- as well as repainting the scene. If {@code this} {@code
   * AnimationPanel} is set to loop, then resets the tick value to 0 once the animation has played
   * through.
   *
   * @param e The event that triggers the repainting and tick increase of {@code this} {@code
   *          AnimationPanel}.
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    this.repaint();
    if (this.isLooping && this.latestTime() <= this.ticks) {
      this.setTick(0);
    } else {
      this.ticks++;
    }
  }

  /**
   * Returns the latest time in the read-only model stored in {@code this} {@code AnimationPanel}.
   *
   * @return The latest time in the model of {@code this} {@code AnimationPanel}.
   */
  protected int latestTime() {
    int latestTime = 0;
    for (ReadOnlyAnimatedShape shapeState : this.model.getShapes()) {
      if (shapeState.getTime() > latestTime) {
        latestTime = shapeState.getTime();
      }
    }
    return latestTime;
  }
}
