package controller;

import model.AnimationEnvironment;
import model.ReadOnlyAnimationEnvironmentImpl;
import model.ReadOnlyKeyframeEnvironment;
import view.AnimationView;

/**
 * Represents a controller for our Animator. Parses the input file, and passes it to the View to
 * execute the animation.
 */
public class AnimatorControllerImpl implements AnimatorController {

  private final AnimationView view;
  private final Readable in;
  private final boolean orientation;

  /**
   * Constructs a controller for our Animator which uses the given {@param model} and the given
   * {@param view}.
   *
   * @param view The view that {@code this} {@code AnimatorController.AnimatorController} will use.
   * @param in   The source file where the Animation Commands and Canvas will be read from.
   */
  public AnimatorControllerImpl(AnimationView view, Readable in, boolean orientation) {
    this.view = view;
    this.in = in;
    this.orientation = orientation;
  }

  @Override
  public void animate() {
    if(orientation) {
      view .setModel(new ReadOnlyAnimationEnvironmentImpl(
          OrientationAnimationReader.parseFile(this.in, AnimationEnvironment.builder())));
    } else {
      view.setModel(new ReadOnlyAnimationEnvironmentImpl(
          AnimationReader.parseFile(this.in, AnimationEnvironment.builder())));
    }
    view.display();
  }
}
