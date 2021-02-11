package view;

import javax.swing.event.ChangeListener;
import model.ReadOnlyAnimatedShape;
import model.ReadOnlyAnimationEnvironment;

public class ScrubbingOrientationEditableView extends EditableViewImpl {
  protected OrientationScrubbingTimeFlowPanel osTimeFlowPanel;

  /**
   * Constructs an {@code EditableView} that initially loops through the animations of the environment
   * at a rate of the given {@code ticksPerSecond}.
   *
   * @param ticksPerSecond The speed that the animation should be played at in ticks per second.
   */
  public ScrubbingOrientationEditableView(int ticksPerSecond) {
    super(ticksPerSecond);
  }

  @Override
  public void setModel(ReadOnlyAnimationEnvironment model) {
    this.osTimeFlowPanel = new OrientationScrubbingTimeFlowPanel(ticksPerSecond, this.getLatestTime(model));
    this.timeFlow = osTimeFlowPanel;
    this.environmentPanel = new OrientationScrubbingAnimationPanel(this.osTimeFlowPanel);
    this.clock.addActionListener(this.environmentPanel);
    this.environmentPanel.setModel(model);
    this.shapeKeyframes = new KeyframeShapesPanel(model);
  }

  public void addChangeListener(ChangeListener listener) {
    this.osTimeFlowPanel.addChangeListener(listener);
  }

  public void setTick(int tick) {
    this.environmentPanel.setTick(tick);
  }

  public int getSliderTick() {
    return this.osTimeFlowPanel.getSliderTick();
  }

  public int getAnimationTick() {
    return this.environmentPanel.getTick();
  }

  public boolean isRunning() {
    return this.clock.isRunning();
  }

  public void refresh() {
    this.environmentPanel.repaint();
  }


  private int getLatestTime(ReadOnlyAnimationEnvironment model) {
    int latestTime = 0;
    for(ReadOnlyAnimatedShape shape : model.getShapes()) {
      for (ReadOnlyAnimatedShape keyframe : shape.getLog()) {
        if(keyframe.getTime() > latestTime) {
          latestTime = keyframe.getTime();
        }
      }
    }
    return latestTime;
  }
}
