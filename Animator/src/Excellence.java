import controller.AnimatorController;
import controller.AnimatorControllerImpl;
import controller.EditableController;
import controller.ScrubbingOrientationEditableController;
import view.AnimationView;
import view.EditableView;
import view.EditableViewImpl;
import view.SVGView;
import view.ScrubbingOrientationEditableView;
import view.TextView;
import view.VisualView;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 * The entry point for the Excellence Animator. Takes in specifications for which view to perform,
 * What speed to perform the animation at, which location to read the animation instructions from,
 * and which location to output the animation information to in the form of -in "inputfilepath"
 * -view "viewType" -speed "tickspersecond" -out "outputlocationpath".
 *
 * <p>The supported views are the following:</p>
 * <ul>
 *   <li>visual</li>
 *   <li>text</li>
 *   <li>svg</li>
 *   <li>edit</li>
 *   <li>orientationScrubbingEdit</li>
 * </ul>
 *
 * <p>The required command line arguments are the -view and -in fields and the default values for
 * -speed and -out are 1 and System.out.</p>
 */
public class Excellence {

  private static JFrame frame = new JFrame();

  /**
   * The Main method to process the command line arguments and create the controller and call the
   * controller's animate method in order to facilitate the animation.
   *
   * @param args Commandline arguments that determine what kind of view will be run (-view "view
   *             name"), what speed the animation will be run at (-speed "ticks per second"), what
   *             input file will be read from (-in "input file path"), what the output source is
   *             (-out "output source path").
   */
  public static void main(String[] args) {

    String inputFileName = null;
    String typeOfView = null;
    String outputFileName = "System.out";
    int ticksPerSecond = 1;
    AnimatorController controller;
    AnimationView view;
    String features = "regular";

    for (int i = 0; i < args.length; i++) {
      switch (args[i]) {
        case "-in":
          inputFileName = args[i + 1];
          i++;
          break;
        case "-view":
          typeOfView = args[i + 1];
          i++;
          break;
        case "-out":
          outputFileName = args[i + 1];
          i++;
          break;
        case "-speed":
          if (Integer.parseInt(args[i + 1]) < 1) {
            JOptionPane.showMessageDialog(frame,
                "Speed must be positive!",
                "Invalid Command!",
                JOptionPane.WARNING_MESSAGE);
            System.exit(1);
            break;
          }
          ticksPerSecond = Integer.parseInt(args[i + 1]);
          i++;
          break;
        case "-features":
          if(args[i+1].equalsIgnoreCase("orientation")) {
            features = "orientation";
          }
          i++;
          break;
        default:
          JOptionPane.showMessageDialog(frame,
              "Invalid Command here!",
              "Invalid Command!",
              JOptionPane.WARNING_MESSAGE);
          System.exit(1);
      }
    }

    try {
      if (inputFileName == null || typeOfView == null) {
        JOptionPane.showMessageDialog(frame,
            "Invalid Command!",
            "Invalid Command!",
            JOptionPane.WARNING_MESSAGE);
        System.exit(1);
      }
      FileWriter writer = new FileWriter(outputFileName);
      if (outputFileName.equalsIgnoreCase("System.out")) {
        writer = null;
      }
      FileReader reader = new FileReader(inputFileName);
      view = Excellence.ViewFactory.build(typeOfView, writer, ticksPerSecond);
      if (view instanceof ScrubbingOrientationEditableView) {
        controller = new ScrubbingOrientationEditableController(
                (ScrubbingOrientationEditableView) view, reader);
      } else if(view instanceof EditableView) {
        controller = new EditableController((EditableView) view, reader);
      } else if(features.equalsIgnoreCase("orientation")){
        controller = new AnimatorControllerImpl(view, reader, true);
      } else {
        controller = new AnimatorControllerImpl(view, reader, false);
      }
      controller.animate();
      if(writer != null) {
        writer.close();
      }
    } catch (IOException ioe) {
      throw new IllegalStateException("Append failed", ioe);
    }
  }

  /**
   * A view Factory for creating the view that corresponds to the given view type and additional
   * information.
   */
  public static final class ViewFactory {

    /**
     * Creates a view that corresponds to the {@param viewType} and, if applicable, with the given
     * {@param out} and {@param ticksPerSecond}.
     *
     * @param viewType       The type of view that should be constructed.
     * @param out            The output source that should be printed to.
     * @param ticksPerSecond The speed of the animation in ticks per second.
     * @return An {@code AnimationView} that corresponds to the given inputs.
     */
    public static AnimationView build(String viewType, Appendable out, int ticksPerSecond) {

      if (viewType.equalsIgnoreCase("text")) {
        if (out == null) {
          return new TextView(System.out);
        }
        return new TextView(out);
      } else if (viewType.equalsIgnoreCase("svg")) {
        if (out == null) {
          return new SVGView(System.out, ticksPerSecond);
        }
        return new SVGView(out, ticksPerSecond);
      } else if (viewType.equalsIgnoreCase("visual")) {
        return new VisualView(ticksPerSecond);
      } else if (viewType.equalsIgnoreCase("orientationScrubbingEdit")) {
        return new ScrubbingOrientationEditableView(ticksPerSecond);
      } else if (viewType.equalsIgnoreCase("edit")) {
        return new EditableViewImpl(ticksPerSecond);
      } else {
        JOptionPane.showMessageDialog(frame,
            "Invalid View Type!",
            "Invalid Command!",
            JOptionPane.WARNING_MESSAGE);
        System.exit(1);
        return null;
      }
    }
  }
}

