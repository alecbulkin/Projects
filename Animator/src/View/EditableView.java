package view;

import java.awt.event.ActionListener;
import model.ReadOnlyAnimationEnvironment;

/**
 * Represents a view that supports interactive alterations in animation.
 *
 * <p>Supported alterations are:</p>
 * <ul>
 *   <li>start</li>
 *   <li>pause</li>
 *   <li>resume</li>
 *   <li>toggle looping</li>
 *   <li>increase/decrease animation speed</li>
 * </ul>
 *
 */
public interface EditableView {

  /**
   * If time is currently ticking then stops it, and if time is currently stopped then starts it.
   */
  void toggleTimeFlow();

  /**
   * If the {@code EditableView} is set to loop then sets it to play only once, and if the
   * {@code EditableView} is set to only play once then sets it to loop.
   */
  void toggleLooping();

  /**
   * Increases or decreases the speed of the animation to match the speed in ticks per second of
   * the {ticksPerSecond}.
   *
   * @param ticksPerSecond The speed in ticks per second that the animation should match.
   */
  void setSpeed(int ticksPerSecond);


  /**
   * Displays the animation for the set {@code AnimationEnvironment} in the form of the specific
   * {@code AnimationView}.
   */
  void display();

  /**
   * Sets the {@code ReadOnlyAnimationEnvironment} model for {@code this} {@code AnimationView} to
   * represent via it's specific animation.
   *
   * @param model The {@code ReadOnlyAnimationEnvironment} that {@code this} {@code AnimationView}
   *              should represent.
   */
  void setModel(ReadOnlyAnimationEnvironment model);


  /**
   * Adds the given {@param listener} as an {@code ActionListener} for {@code this}
   * {@code EditableView} and all of its components.
   * @param listener The {@code ActionListener} that will be added to
   *                 {@code this} {@code EditableView}
   */
  void addActionListener(ActionListener listener);

  /**
   * Returns the text of the text field that corresponds to the given {@param fieldName}.
   *
   * @param fieldName The name of the textField to return the text of in {@code this}
   *                  {@code EditableView}.
   * @return The text of the text field that corresponds to the given name {@param fieldName}.
   */
  String getTextFieldText(String fieldName);

  /**
   * Sets the text of the text field that corresponds to the given {@param fieldName} to
   * the {@param newText}.
   *
   * @param fieldName The name of the text field that should be changed.
   * @param newText The new text that the text field should be set to.
   */
  void setTextFieldText(String fieldName, String newText);

  /**
   * Updates the Keyframes of {@code this} {@code EditableView} to match that of the current state
   * of the model.
   */
  void updateKeyframes();

}




