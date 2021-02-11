package view;

import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeListener;

/**
 * Represents a {@code TimeFlowPanel} that holds all of the controls for and animation editor
 * regarding time flow as well as additional features such as creating a shape with orientation
 * and scrubbing the frame.
 */
public class OrientationScrubbingTimeFlowPanel extends TimeFlowPanel {
  private final JSlider slider;
  private int ticks;

  public OrientationScrubbingTimeFlowPanel(int TicksPerSecond, int maxTicks) {
    super(TicksPerSecond);
    this.slider = new JSlider(0, maxTicks, 0);
    this.slider.setOpaque(true);
    this.slider.setMajorTickSpacing(100);
    this.slider.setMinorTickSpacing(25);
    this.slider.setPaintLabels(true);
    this.slider.setPaintTicks(true);
    this.slider.setSize(800, 25);


    GridBagConstraints c = new GridBagConstraints();

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = GridBagConstraints.REMAINDER;

    JLabel orientationLabel = new JLabel("Angle:");
    JTextField orientationTextField = new JTextField(2);

    this.textFields.put("orientation field", orientationTextField);

    gridBag.setConstraints(orientationTextField, c);

    this.add(orientationLabel);
    this.add(orientationTextField);
    gridBag.setConstraints(this.slider, c);
    this.add(this.slider);
  }

  public int getSliderTick() {
    return this.slider.getValue();
  }

  public void setTicks(int ticks) {
    this.slider.setValue(ticks);
  }

  public void addChangeListener(ChangeListener listener) {
    this.slider.addChangeListener(listener);
  }
}
