import model.ReadOnlyAnimationEnvironment;
import view.AnimationView;

/**
 * A mock against a view that simply appends to the given log that a method was called and if it had
 * any parameters what the details of that parameter are.
 */
public class ViewMock implements AnimationView {

  final StringBuilder log;

  /**
   * Constructs a {@code ViewMock} with the given StringBuilder {@param log} to append the calls to
   * methods in this class (and their parameter's details) to.
   *
   * @param log The log that all of the calls on {@code this} {@code ViewMock} will be kept track of
   *            in.
   */
  public ViewMock(StringBuilder log) {
    this.log = log;
  }

  @Override
  public void display() {
    log.append("display called\n");
  }

  @Override
  public void setModel(ReadOnlyAnimationEnvironment model) {
    log.append("setModel called with animation environment of left corner location ("
        + model.getX() + "," + model.getY() + ") and dimensions " + model.getWidth()
        + "x" + model.getHeight() + "\n");
  }
}
