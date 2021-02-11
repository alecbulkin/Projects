package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Map;

/**
 * A listener that performs the action corresponding to the actionCommand in the stored hashmap of
 * functions to be performed on the model and view.
 */
public class ButtonListener implements ActionListener {
  private Map<String, Runnable> buttonClickedActions;

  /**
   * Constructs a new ButtonListener that initializes the map of button clicked actions to empty.
   */
  public ButtonListener() {
    this.buttonClickedActions = null;
  }

  /**
   * Sets the map of button clicked actions to hold the same actions and corresponding functions
   * as the given {@param buttonClickedActions}.
   *
   * @param buttonClickedActions The actionCommands and the corresponding functions to be used.
   */
  public void setButtonClickedActions(Map<String, Runnable> buttonClickedActions) {
    this.buttonClickedActions = buttonClickedActions;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (this.buttonClickedActions.containsKey(e.getActionCommand())) {
      buttonClickedActions.get(e.getActionCommand()).run();
    }
  }
}
