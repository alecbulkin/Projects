package controller;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import model.AnimationEnvironment;
import model.KeyframeAnimationEnvironmentImpl;
import model.ReadOnlyKeyframeEnvironment;
import view.ScrubbingOrientationEditableView;

public class ScrubbingOrientationEditableController extends EditableController implements
    ChangeListener {

  protected ScrubbingOrientationEditableView soView;

  /**
   * Constructs a controller for our Animator which uses the given {@param model} and the given
   * {@param view}.
   *
   * @param view The view that {@code this} {@code AnimatorController.AnimatorController} will use.
   * @param in   The source file where the Animation Commands and Canvas will be read from.
   */
  public ScrubbingOrientationEditableController(ScrubbingOrientationEditableView view, Readable in) {
    super(view, in);
    this.soView = view;
  }

  @Override
  public void animate() {
    this.model = new KeyframeAnimationEnvironmentImpl(OrientationAnimationReader.parseFile(
        this.in, AnimationEnvironment.builder()));
    view.setModel(new ReadOnlyKeyframeEnvironment(model));
    this.configureListeners();
    view.display();
  }


  protected void configureListeners() {
    this.configureButtonListener();
    this.soView.addChangeListener(this);
  }


  @Override
  public void stateChanged(ChangeEvent e) {
    if(this.soView.getSliderTick() != this.soView.getAnimationTick()) {
      if(this.soView.isRunning()) {
        this.soView.toggleTimeFlow();
      }
      this.soView.setTick(this.soView.getSliderTick());
      this.soView.refresh();
    }
  }
}
