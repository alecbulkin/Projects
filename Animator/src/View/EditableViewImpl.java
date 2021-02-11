package view;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.Timer;
import javax.swing.WindowConstants;
import model.ReadOnlyAnimationEnvironment;

/**
 * Represents an editable visual view, where the user can dynamically change elements of the
 * animation (such as time flow, looping, and the states, behaviors, and animations of all the
 * shapes in the environment).
 */
public class EditableViewImpl extends JFrame implements EditableView, AnimationView {

  protected AnimationPanel environmentPanel;
  protected TimeFlowPanel timeFlow;
  protected KeyframeShapesPanel shapeKeyframes;
  protected final Timer clock;
  protected int ticksPerSecond;

  /**
   * Constructs an {@code EditableView} that initially loops through the animations of the
   * environment at a rate of the given {@code ticksPerSecond}.
   *
   * @param ticksPerSecond The speed that the animation should be played at in ticks per second.
   */
  public EditableViewImpl(int ticksPerSecond) {
    super();
    this.ticksPerSecond = ticksPerSecond;
    this.environmentPanel = new AnimationPanel();
    this.clock = new Timer((int) (1.0 / ticksPerSecond * 1000), this.environmentPanel);
    this.clock.stop();

    this.setTitle("Excellence Animator");
    this.setSize(1000, 800);
    this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
  }

  @Override
  public void display() {
    JScrollPane scrollableEnvironment = new JScrollPane(this.environmentPanel);
    scrollableEnvironment.setSize(700, 700);
    JSplitPane topBottom = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scrollableEnvironment,
        new JScrollPane((Component) this.timeFlow));
    JSplitPane leftRight = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, topBottom,
        new JScrollPane(this.shapeKeyframes));
    this.add(leftRight);
    this.pack();

    this.setVisible(true);
  }

  @Override
  public void setModel(ReadOnlyAnimationEnvironment model) {
    this.environmentPanel.setModel(model);
    this.timeFlow = new TimeFlowPanel(ticksPerSecond);
    this.shapeKeyframes = new KeyframeShapesPanel(model);
  }

  @Override
  public void toggleTimeFlow() {
    if (this.clock.isRunning()) {
      this.clock.stop();
    } else {
      this.clock.start();
    }
    this.timeFlow.togglePlayButton();
  }

  @Override
  public void toggleLooping() {
    this.environmentPanel.toggleLooping();
    this.timeFlow.toggleLooping();
  }

  @Override
  public void setSpeed(int ticksPerSecond) {
    this.clock.setDelay((int) (1.0 / ticksPerSecond * 1000));
  }

  @Override
  public void addActionListener(ActionListener listener) {
    if (this.timeFlow == null || this.shapeKeyframes == null) {
      throw new IllegalArgumentException("Must set the model first!");
    }
    this.timeFlow.addActionListener(listener);
    this.shapeKeyframes.addActionListener(listener);
  }

  @Override
  public String getTextFieldText(String fieldName) {
    if (this.timeFlow.containsTextField(fieldName)) {
      return this.timeFlow.getTextFieldText(fieldName);
    }
    return this.shapeKeyframes.getTextFieldText(fieldName);
  }

  @Override
  public void updateKeyframes() {
    this.shapeKeyframes.updateKeyframes();
  }

  @Override
  public void setTextFieldText(String fieldName, String newText) {
    if (this.timeFlow.containsTextField(fieldName)) {
      this.timeFlow.setTextField(fieldName, newText);
    } else {
      this.shapeKeyframes.setTextField(fieldName, newText);
    }
  }

}
