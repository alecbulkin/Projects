package view;

import model.ReadOnlyAnimationEnvironment;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.Timer;

/**
 * Represents a visual view. Displays a set model of animation commands as a playable animation at a
 * speed of given ticks per second.
 */
public class VisualView extends JFrame implements AnimationView {

  private ReadOnlyAnimationEnvironment model;
  private AnimationPanel environmentPanel;
  private final JScrollPane scrollableEnvironmentPanel;

  /**
   * Constructs a {@code VisualView} that runs an {@code AnimationEnvironment} at a given speed of
   * {@param ticksPerSecond}.
   *
   * @param ticksPerSecond The speed at which {@code this} {@code VisualView} runs the animation in
   *                       ticks per second.
   */
  public VisualView(int ticksPerSecond) {
    super();
    this.setTitle("Excellence Animator");
    this.model = null;
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());

    this.environmentPanel = new AnimationPanel();
    this.scrollableEnvironmentPanel = new JScrollPane(this.environmentPanel);
    Timer timer = new Timer((int) ((double) 1 / ticksPerSecond * 1000), this.environmentPanel);
    timer.start();
  }

  @Override
  public void display() {
    this.setVisible(true);
  }

  @Override
  public void setModel(ReadOnlyAnimationEnvironment model) {
    this.model = model;
    this.setSize(this.model.getWidth(), this.model.getHeight());
    this.environmentPanel.setModel(this.model);
    this.add(this.scrollableEnvironmentPanel, BorderLayout.CENTER);
    this.pack();
  }
}
