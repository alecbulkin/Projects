package controller;

/**
 * Represents a controller for an Animator. Takes animation command input from a denoted source and
 * passes it to a designated view to handle the illustration of the commands.
 */
public interface AnimatorController {

  /**
   * Parses the Information from the input source, and passes this information to the view in order
   * to be able to illustrate the animation.
   */
  void animate();
}
