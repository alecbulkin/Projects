import java.awt.event.ActionListener;
import javax.swing.JButton;
import model.ReadOnlyAnimationEnvironment;
import view.EditableView;

/**
 * Represents a mock of an {@code EditableView}. Appends to a log when each method is called in
 * order to document that the method was called.
 */
public class EditableViewMock implements EditableView {
  StringBuilder log;
  public JButton button;

  /**
   * Constructs an {@code EditableViewMock} with the given log to document all of the actions
   * performed on {@code this} {@code EditableViewMock}.
   *
   * @param log The StringBuilder that will log all of the actions performed on {@code this}
   *            {@code EditableViewMock}.
   */
  public EditableViewMock(StringBuilder log) {
    this.log = log;
    this.button = new JButton();
  }

  @Override
  public void toggleTimeFlow() {
    this.log.append("Called Time Flow\n");
  }

  @Override
  public void toggleLooping() {
    this.log.append("Called toggle Looping\n");
  }

  @Override
  public void setSpeed(int ticksPerSecond) {
    this.log.append("Called set speed with" + ticksPerSecond + "\n");
  }

  @Override
  public void display() {
    this.log.append("Called Display\n");
  }

  @Override
  public void setModel(ReadOnlyAnimationEnvironment model) {
    this.log.append("Called set model with\n");
  }

  @Override
  public void addActionListener(ActionListener listener) {
    this.log.append("Called addActionListener\n");
    this.button.addActionListener(listener);
  }

  @Override
  public String getTextFieldText(String fieldName) {
    this.log.append("Called getTextFieldText with " + fieldName + "\n");
    return "5";
  }

  @Override
  public void setTextFieldText(String fieldName, String newText) {
    this.log.append("Called setTextFieldText with " + fieldName + " " + newText + "\n");
  }

  @Override
  public void updateKeyframes() {
    this.log.append("Called updateKeyframes\n");
  }

  /**
   * Triggers an event with the {@param given actionCommand} for testing purposes.
   *
   * @param actionCommand The action command to be triggered.
   */
  public void triggerEvent(String actionCommand) {
    this.button.setActionCommand(actionCommand);
    this.button.doClick();
  }
}
