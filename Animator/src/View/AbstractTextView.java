package view;

import model.ReadOnlyAnimationEnvironment;
import java.io.IOException;

/**
 * An abstract class for text based views. Factors out the duplicate code in the constructors,
 * display, and setModel methods.
 */
public abstract class AbstractTextView implements AnimationView {

  protected ReadOnlyAnimationEnvironment model;
  protected final Appendable out;

  /**
   * Constructs an {@code AbstractTextView}  with the given model to illustrate and output stream to
   * print to.
   *
   * @param model The model for {@code this} {@code AbstractTextView} to illustrate.
   * @param out   The output stream for {@code this} {@code AbstractTextView} to print to.
   */
  protected AbstractTextView(ReadOnlyAnimationEnvironment model, Appendable out) {
    this.model = model;
    this.out = out;
  }

  @Override
  public void display() {
    if (this.model == null) {
      throw new IllegalArgumentException("Must set model!");
    }
    try {
      out.append(this.animationText());
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

  @Override
  public void setModel(ReadOnlyAnimationEnvironment model) {
    this.model = model;
  }

  /**
   * A helper method that produces the String corresponding to the text of {@code this} {@code
   * AbstractTextView}.
   *
   * @return A String of the animation details of the model assigned to {@code this} {@code
   * AbstractTextView} formatted to {@code this} {@code AbstractTextView}'s specifications.
   */
  abstract String animationText();
}
