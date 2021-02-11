package view;

import model.ReadOnlyAnimationEnvironment;

/**
 * An interface for the ExCELlence animator view. Represents the functionality that is necessary to
 * illustrate a visual of an {@code AnimationEnvironment} over time.
 */
public interface AnimationView {

  /**
   * Displays the animation for the set {@code AnimationEnvironment} in the form of the specific
   * {@code AnimationView}.
   */
  void display();

  /**
   * Sets the {@code ReadOnlyAnimationEnvironment} model for {@code this} {@code AnimationView}
   * to represent via it's specific animation.
   *
   * @param model The {@code ReadOnlyAnimationEnvironment} that {@code this} {@code AnimationView}
   *              should represent.
   */
  void setModel(ReadOnlyAnimationEnvironment model);
}
