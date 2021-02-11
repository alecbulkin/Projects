package controller;

import java.util.Objects;
import java.util.Scanner;
import java.util.regex.Pattern;

public class OrientationAnimationReader extends AnimationReader {


  public static <Doc> Doc parseFile(Readable readable, AnimationBuilder<Doc> builder) {
    Objects.requireNonNull(readable, "Must have non-null readable source");
    Objects.requireNonNull(builder, "Must provide a non-null "
        + "AnimatorController.Controller.AnimationBuilder");
    Scanner s = new Scanner(readable);
    // Split at whitespace, and ignore # comment lines
    s.useDelimiter(Pattern.compile("(\\p{Space}+|#.*)+"));
    while (s.hasNext()) {
      String word = s.next();
      switch (word) {
        case "canvas":
          readCanvas(s, builder);
          break;
        case "shape":
          readShape(s, builder);
          break;
        case "motion":
          readMotion(s, builder);
          break;
        default:
          throw new IllegalStateException("Unexpected keyword: " + word + s.nextLine());
      }
    }
    return builder.build();
  }


  protected static <Doc> void readMotion(Scanner s, AnimationBuilder<Doc> builder) {
    String[] fieldNames = new String[]{
        "initial time",
        "initial x-coordinate", "initial y-coordinate",
        "initial width", "initial height",
        "initial red value", "initial green value", "initial blue value",
        "initial orientation value",
        "final time",
        "final x-coordinate", "final y-coordinate",
        "final width", "final height",
        "final red value", "final green value", "final blue value", "final orientation value"
    };
    int[] vals = new int[18];
    String name;
    if (s.hasNext()) {
      name = s.next();
    } else {
      throw new IllegalStateException("Motion: Expected a shape name, but no more input available");
    }
    for (int i = 0; i < 18; i++) {
      vals[i] = getInt(s, "Motion", fieldNames[i]);
    }
    builder.addMotion(name,
        vals[0], vals[1], vals[2], vals[3], vals[4], vals[5], vals[6], vals[7],
        vals[8], vals[9], vals[10], vals[11], vals[12], vals[13], vals[14], vals[15],
        vals[16], vals[17]);
  }
}
