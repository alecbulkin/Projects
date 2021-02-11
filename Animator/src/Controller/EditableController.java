package controller;

import java.awt.Color;
import java.awt.Dimension;
import java.util.HashMap;
import java.util.Map;
import model.AnimationEnvironment;
import model.KeyframeAnimationEnvironment;
import model.KeyframeAnimationEnvironmentImpl;
import model.Position2D;
import model.ReadOnlyAnimatedShape;
import model.ReadOnlyKeyframeEnvironment;
import model.Shapes;
import view.EditableView;

/**
 * Represents a Controller that runs and editable animation view and handles input from the user via
 * buttons on the view.
 */
public class EditableController implements AnimatorController {

  protected final EditableView view;
  protected final Readable in;
  protected KeyframeAnimationEnvironment model;

  /**
   * Constructs a controller for our Animator which uses the given {@param model} and the given
   * {@param view}.
   *
   * @param view The view that {@code this} {@code AnimatorController.AnimatorController} will use.
   * @param in   The source file where the Animation Commands and Canvas will be read from.
   */
  public EditableController(EditableView view, Readable in) {
    this.view = view;
    this.in = in;
    this.model = null;
  }

  @Override
  public void animate() {
    this.model = new KeyframeAnimationEnvironmentImpl(AnimationReader.parseFile(
        this.in, AnimationEnvironment.builder()));
    view.setModel(new ReadOnlyKeyframeEnvironment(model));
    this.configureButtonListener();
    view.display();
  }


  /**
   * Configures the buttonListener to be a listener for the view in {@code this} {@code
   * EditableController} with all of the correct actionCommands.
   */
  protected void configureButtonListener() {
    Map<String, Runnable> buttonClickedMap = new HashMap();
    ButtonListener buttonListener = new ButtonListener();

    buttonClickedMap.put("toggle time", () -> {
      this.view.toggleTimeFlow();
    });
    buttonClickedMap.put("toggle looping", () -> {
      this.view.toggleLooping();
    });
    buttonClickedMap.put("set speed", () -> {
      this.view.setSpeed(Integer.parseInt(view.getTextFieldText("speed field")));
    });
    buttonClickedMap.put("create shape", () -> {
      if (!(view.getTextFieldText("name field").equals("") || view.getTextFieldText("type field")
          .equals("")
          || view.getTextFieldText("time field").equals("") || view.getTextFieldText("height field")
          .equals("")
          || view.getTextFieldText("width field").equals("") || view.getTextFieldText("x field")
          .equals("")
          || view.getTextFieldText("y field").equals("") || view.getTextFieldText("r field")
          .equals("")
          || view.getTextFieldText("r field").equals("") || view.getTextFieldText("g field")
          .equals("")
          || view.getTextFieldText("b field").equals("") ||
          view.getTextFieldText("orientation field").equals(""))) {
        Shapes type;
        if (view.getTextFieldText("type field").equalsIgnoreCase("rectangle")) {
          type = Shapes.Rectangle;
        } else if (view.getTextFieldText("type field").equalsIgnoreCase("triangle")) {
          type = Shapes.Triangle;
        } else if (view.getTextFieldText("type field").equalsIgnoreCase("oval")
            || view.getTextFieldText("type field").equalsIgnoreCase("ellipse")) {
          type = Shapes.Oval;
        } else {
          type = null;
        }
        String name = view.getTextFieldText("name field");
        try {
          int time = Integer.parseInt(view.getTextFieldText("time field"));
          int height = Integer.parseInt(view.getTextFieldText("height field"));
          int width = Integer.parseInt(view.getTextFieldText("width field"));
          int x = Integer.parseInt(view.getTextFieldText("x field"));
          int y = Integer.parseInt(view.getTextFieldText("y field"));
          int r = Integer.parseInt(view.getTextFieldText("r field"));
          int g = Integer.parseInt(view.getTextFieldText("g field"));
          int b = Integer.parseInt(view.getTextFieldText("b field"));
          int o = Integer.parseInt(view.getTextFieldText("orientation field"));

          if (type == null || time < 0 || height < 1 || width < 1 || r < 0 || r > 255 || g < 0
              || g > 255 || b < 0 || b > 255 || o < 0 || o > 360) {

            if (type == null) {
              this.view.setTextFieldText("type field", "invalid type");
            }
            if (time < 0) {
              this.view.setTextFieldText("time field", "invalid time");
            }
            if (height < 1) {
              this.view.setTextFieldText("height field", "invalid height");
            }
            if (width < 1) {
              this.view.setTextFieldText("width field", "invalid width");
            }
            if (r < 0 || r > 255) {
              this.view.setTextFieldText("r field", "invalid r");
            }
            if (g < 0 || g > 255) {
              this.view.setTextFieldText("g field", "invalid g");
            }
            if (b < 0 || b > 255) {
              this.view.setTextFieldText("b field", "invalid b");
            }
            if (o < 0 || o > 360) {
              this.view.setTextFieldText("orientation field", "invalid angle");
            }
          } else {
            this.model.addShape(type, name, time, new Dimension(width, height), new Color(r, g, b),
                new Position2D(x, y), o);
            this.view.updateKeyframes();
            this.view.setTextFieldText("type field", "   ");
            this.view.setTextFieldText("name field", "   ");
            this.view.setTextFieldText("time field", "   ");
            this.view.setTextFieldText("width field", "   ");
            this.view.setTextFieldText("height field", "   ");
            this.view.setTextFieldText("r field", "   ");
            this.view.setTextFieldText("g field", "   ");
            this.view.setTextFieldText("b field", "   ");
            this.view.setTextFieldText("x field", "   ");
            this.view.setTextFieldText("y field", "   ");
            this.view.setTextFieldText("orientation field", "   ");
            this.configureButtonListener();
          }
        } catch (NumberFormatException e) {
          System.out.println("An invalid field was entered ");
        }
      }
    });

    for (ReadOnlyAnimatedShape shape : this.model.getShapes()) {
      String name = shape.getName();

      buttonClickedMap.put("delete shape " + shape.getName(), () -> {
        this.model.deleteShape(name);
        this.view.updateKeyframes();
        this.configureButtonListener();
      });

      buttonClickedMap.put("add keyframe " + name, () -> {
        if (!(view.getTextFieldText(name + " add keyframe time field").equals("") || view
            .getTextFieldText(name + " add keyframe height field").equals("")
            || view.getTextFieldText(name + " add keyframe width field").equals("") || view
            .getTextFieldText(name + " add keyframe x field")
            .equals("")
            || view.getTextFieldText(name + " add keyframe y field").equals("") || view
            .getTextFieldText(name + " add keyframe r field")
            .equals("")
            || view.getTextFieldText(name + " add keyframe r field").equals("") || view
            .getTextFieldText(name + " add keyframe g field")
            .equals("")
            || view.getTextFieldText(name + " add keyframe b field").equals(""))) {
          try {
            int time = Integer.parseInt(view.getTextFieldText(name + " add keyframe time field"));
            int height = Integer
                .parseInt(view.getTextFieldText(name + " add keyframe height field"));
            int width = Integer.parseInt(view.getTextFieldText(name + " add keyframe width field"));
            int x = Integer.parseInt(view.getTextFieldText(name + " add keyframe x field"));
            int y = Integer.parseInt(view.getTextFieldText(name + " add keyframe y field"));
            int r = Integer.parseInt(view.getTextFieldText(name + " add keyframe r field"));
            int g = Integer.parseInt(view.getTextFieldText(name + " add keyframe g field"));
            int b = Integer.parseInt(view.getTextFieldText(name + " add keyframe b field"));
            int o = Integer.parseInt(view.getTextFieldText(name + " add keyframe orientation field"));

            if (this.keyframeTimeAlreadyExists(shape, time) || time < 0 || height < 1 || width < 1
                || r < 0 || r > 255 || g < 0
                || g > 255 || b < 0 || b > 255 || o < 0 || o > 360) {
              if (time < 0 || this.keyframeTimeAlreadyExists(shape, time)) {
                this.view.setTextFieldText(name + " add keyframe time field", "invalid time");
              }
              if (height < 1) {
                this.view.setTextFieldText(name + " add keyframe height field", "invalid height");
              }
              if (width < 1) {
                this.view.setTextFieldText(name + " add keyframe width field", "invalid width");
              }
              if (r < 0 || r > 255) {
                this.view.setTextFieldText(name + " add keyframe r field", "invalid r");
              }
              if (g < 0 || g > 255) {
                this.view.setTextFieldText(name + " add keyframe g field", "invalid g");
              }
              if (b < 0 || b > 255) {
                this.view.setTextFieldText(name + " add keyframe b field", "invalid b");
              }
              if (o < 0 || o > 360) {
                this.view.setTextFieldText(name + " add keyframe orientation field", "invalid angle");
              }
            } else {
              this.model.addKeyframe(shape.getName(), time, new Dimension(width, height),
                  new Color(r, g, b), new Position2D(x, y), o);

              this.view.setTextFieldText(name + " add keyframe time field", "");
              this.view.setTextFieldText(name + " add keyframe height field", "");
              this.view.setTextFieldText(name + " add keyframe width field", "");
              this.view.setTextFieldText(name + " add keyframe x field", "");
              this.view.setTextFieldText(name + " add keyframe y field", "");
              this.view.setTextFieldText(name + " add keyframe r field", "");
              this.view.setTextFieldText(name + " add keyframe g field", "");
              this.view.setTextFieldText(name + " add keyframe b field", "");
              this.view.updateKeyframes();
              this.configureButtonListener();
            }
          } catch (NumberFormatException e) {
            System.out.println("An invalid field was entered ");
          }
        }
      });

      for (ReadOnlyAnimatedShape keyframe : shape.getLog()) {
        int time = keyframe.getTime();

        buttonClickedMap.put("edit keyframe " + shape.getName() + " " + keyframe.getTime(), () -> {

          if (!(view.getTextFieldText(name + " time " + time + " height field").equals("") || view
              .getTextFieldText(name + " time " + time + " width field").equals("")
              || view.getTextFieldText(name + " time " + time + " x field").equals("") || view
              .getTextFieldText(name + " time " + time + " y field")
              .equals("")
              || view.getTextFieldText(name + " time " + time + " r field").equals("") || view
              .getTextFieldText(name + " time " + time + " g field")
              .equals("")
              || view.getTextFieldText(name + " time " + time + " b field").equals(""))) {
            try {
              int height = Integer
                  .parseInt(view.getTextFieldText(name + " time " + time + " height field"));
              int width = Integer
                  .parseInt(view.getTextFieldText(name + " time " + time + " width field"));
              int x = Integer
                  .parseInt(view.getTextFieldText(name + " time " + time + " x field"));
              int y = Integer
                  .parseInt(view.getTextFieldText(name + " time " + time + " y field"));
              int r = Integer
                  .parseInt(view.getTextFieldText(name + " time " + time + " r field"));
              int g = Integer
                  .parseInt(view.getTextFieldText(name + " time " + time + " g field"));
              int b = Integer
                  .parseInt(view.getTextFieldText(name + " time " + time + " b field"));
              int o = Integer.parseInt(view.getTextFieldText(name + " time " + time
                  + " orientation field"));

              if (time < 0 || height < 1 || width < 1 || r < 0 || r > 255 || g < 0
                  || g > 255 || b < 0 || b > 255 || o < 0 || o > 360) {
                if (time < 0) {
                  this.view
                      .setTextFieldText(name + " time " + time + " time field", "invalid time");
                }
                if (height < 1) {
                  this.view
                      .setTextFieldText(name + " time " + time + " height field", "invalid height");
                }
                if (width < 1) {
                  this.view
                      .setTextFieldText(name + " time " + time + " width field", "invalid width");
                }
                if (r < 0 || r > 255) {
                  this.view.setTextFieldText(name + " time " + time + " r field", "invalid r");
                }
                if (g < 0 || g > 255) {
                  this.view.setTextFieldText(name + " time " + time + " g field", "invalid g");
                }
                if (b < 0 || b > 255) {
                  this.view.setTextFieldText(name + " time " + time + " b field", "invalid b");
                }
                if (o < 0 || o > 360) {
                  this.view.setTextFieldText(name + " time " + time + " orientation field", "invalid angle");
                }
              } else {
                this.view.setTextFieldText(name + " time " + time + " height field", height + "");
                this.view.setTextFieldText(name + " time " + time + " width field", width + "");
                this.view.setTextFieldText(name + " time " + time + " x field", x + "");
                this.view.setTextFieldText(name + " time " + time + " y field", y + "");
                this.view.setTextFieldText(name + " time " + time + " r field", r + "");
                this.view.setTextFieldText(name + " time " + time + " g field", g + "");
                this.view.setTextFieldText(name + " time " + time + " b field", b + "");
                this.view.setTextFieldText(name + " time " + time + " orientation field", o + "");
                this.model
                    .editKeyframe(name, time, new Dimension(width, height), new Color(r, g, b),
                        new Position2D(x, y), o);
              }
            } catch (NumberFormatException e) {
              System.out.println("An invalid field was entered ");
            }
          }


        });

        buttonClickedMap
            .put("delete keyframe " + shape.getName() + " " + keyframe.getTime(), () -> {
              this.model.deleteKeyframe(name, keyframe.getTime());
              this.view.updateKeyframes();
              this.configureButtonListener();
            });
      }
    }
    buttonListener.setButtonClickedActions(buttonClickedMap);
    this.view.addActionListener(buttonListener);
  }


  /**
   * Returns a boolean value indicating whether or not there already exists a keyframe that
   * corresponds to the given {@param time} for the given {@param shape}.
   *
   * @param shape The shape that's keyframes are to be checked.
   * @param time  The time of the kyeframe to be checked.
   * @return A boolean value where true indicates that there already exists a keyframe for the given
   * {@param shape} at the given {@param time}.
   */
  private boolean keyframeTimeAlreadyExists(ReadOnlyAnimatedShape shape, int time) {
    for (ReadOnlyAnimatedShape keyframe : shape.getLog()) {
      if (keyframe.getTime() == time) {
        return true;
      }
    }
    return false;
  }


}
