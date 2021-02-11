package view;

import java.awt.event.ActionEvent;

public class OrientationScrubbingAnimationPanel extends AnimationPanel {
  protected OrientationScrubbingTimeFlowPanel timeFlowPanel;

  public OrientationScrubbingAnimationPanel(OrientationScrubbingTimeFlowPanel timeFlowPanel) {
    super();
    this.timeFlowPanel = timeFlowPanel;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    this.repaint();
    if (this.isLooping && this.latestTime() <= this.ticks) {
      this.setTick(0);
    } else {
      this.ticks++;
    }
    this.timeFlowPanel.setTicks(this.ticks);
  }
}
