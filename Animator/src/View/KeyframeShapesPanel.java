package view;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import model.ReadOnlyAnimatedShape;
import model.ReadOnlyAnimationEnvironment;

/**
 * Represents a panel that holds all of the editable information of a models keyframes and shapes.
 */
public class KeyframeShapesPanel extends JPanel {

  private final GridBagConstraints c;
  private final GridBagLayout gridBag;
  private ReadOnlyAnimationEnvironment model;
  private final JLabel shapes;
  private ArrayList<JButton> buttons;
  private Map<String, JTextField> textFields;

  /**
   * Constructs a {@code KeyframeShapesPanel} that displays all of the keyframe and shape
   * information of the given {@param model} with buttons that allow for the editing of the
   * keyframes and shapes.
   *
   * @param model The {@code ReadOnlyAnimationEnvironment} that will have its information
   *              displayed.
   */
  public KeyframeShapesPanel(ReadOnlyAnimationEnvironment model) {
    this.model = model;
    this.buttons = new ArrayList();
    this.textFields = new HashMap();

    this.shapes = new JLabel("Shapes");
    this.gridBag = new GridBagLayout();
    this.c = new GridBagConstraints();
    this.setLayout(gridBag);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = GridBagConstraints.REMAINDER;
    gridBag.setConstraints(shapes, c);
    this.add(shapes);

    this.configureKeyframes();
  }

  /**
   * Adds the given {@param listener} as an {@code ActionListener} for all of the buttons in {@code
   * this} {@code KeyframeShapesPanel}.
   *
   * @param listener The ActionListener to be added to all of the buttons in {@code this} {@code
   *                 KeyframeShapesPanel}.
   */
  public void addActionListener(ActionListener listener) {
    for (JButton button : this.buttons) {
      if (button.getActionListeners().length > 0) {
        ActionListener oldListener = button.getActionListeners()[0];
        button.removeActionListener(oldListener);
      }
    }
    for (JButton button : this.buttons) {
      button.addActionListener(listener);
    }
  }

  /**
   * Sets the text of the textfield that corresponds to the given {@param fieldName} to {@param
   * newText}.
   *
   * @param fieldName The name that corresponds to the textField in {@code this} {@code
   *                  KeyframeShapesPanel} that is to be set.
   * @param newText   The new text that the text field is to be set to.
   * @throws IllegalArgumentException if there is no existing text field corresponding to the given
   *                                  {@param fieldName}.
   */
  public void setTextField(String fieldName, String newText) {
    if (this.textFields.containsKey(fieldName)) {
      JTextField field = this.textFields.get(fieldName);
      field.setColumns(newText.length() * (2/3));
      field.setText(newText);
      this.revalidate();
      this.repaint();
    } else {
      throw new IllegalArgumentException("Does not contain key");
    }
  }

  /**
   * Returns a string of the text that is stored in the text field corresponding to the given
   * {@param fieldName}.
   *
   * @param fieldName The name of the text field that the text of will be returned.
   * @return The text of the text field that corresponds to {@param fieldName}.
   * @throws IllegalArgumentException If there is no text field that corresponds to the given
   *                                  {@param fieldName}.
   */
  public String getTextFieldText(String fieldName) {
    if (this.textFields.containsKey(fieldName)) {
      return this.textFields.get(fieldName).getText();
    }
    throw new IllegalArgumentException("No field exists with that name!");
  }

  /**
   * Updates the keyframe and shape information in {@code this} {@code KeyframeShapesPanel}.
   */
  public void updateKeyframes() {
    this.buttons = new ArrayList();
    this.textFields.clear();
    this.removeAll();
    this.gridBag.setConstraints(shapes, c);
    this.add(shapes);
    this.configureKeyframes();
    this.revalidate();
    this.repaint();
  }

  /**
   * Configures the keyframes to have all of the required buttons with the appropriate action
   * commands and display all of the correct information of the model.
   */
  private void configureKeyframes() {
    for (ReadOnlyAnimatedShape shape : this.model.getShapes()) {

      JLabel shapeTitle = new JLabel(shape.getShapeType().toString() + " " + shape.getName(),
          SwingConstants.LEFT);
      buttons.add(new JButton("delete"));
      buttons.get(buttons.size() - 1).setActionCommand("delete shape " + shape.getName());

      buttons.add(new JButton("Add frame"));
      buttons.get(buttons.size() - 1).setActionCommand("add keyframe " + shape.getName());
      JLabel addKeyframeTimeLabel = new JLabel("time:");
      JTextField addKeyframeTimeField = new JTextField(2);
      this.textFields.put(shape.getName() + " add keyframe time field", addKeyframeTimeField);
      JLabel addKeyframeWidthLabel = new JLabel("width:");
      JTextField addKeyframeWidthField = new JTextField(2);
      this.textFields.put(shape.getName() + " add keyframe width field", addKeyframeWidthField);
      JLabel addKeyframeHeightLabel = new JLabel("height:");
      JTextField addKeyframeHeightField = new JTextField(2);
      this.textFields.put(shape.getName() + " add keyframe height field", addKeyframeHeightField);
      JLabel addKeyframeXLabel = new JLabel("X:");
      JTextField addKeyframeXField = new JTextField(2);
      this.textFields.put(shape.getName() + " add keyframe x field", addKeyframeXField);
      JLabel addKeyframeYLabel = new JLabel("Y:");
      JTextField addKeyframeYField = new JTextField(2);
      this.textFields.put(shape.getName() + " add keyframe y field", addKeyframeYField);
      JLabel addKeyframeRLabel = new JLabel("R:");
      JTextField addKeyframeRField = new JTextField(2);
      this.textFields.put(shape.getName() + " add keyframe r field", addKeyframeRField);
      JLabel addKeyframeGLabel = new JLabel("G:");
      JTextField addKeyframeGField = new JTextField(2);
      this.textFields.put(shape.getName() + " add keyframe g field", addKeyframeGField);
      JLabel addKeyframeBLabel = new JLabel("B:");
      JTextField addKeyframeBField = new JTextField(2);
      this.textFields.put(shape.getName() + " add keyframe b field", addKeyframeBField);
      JLabel addKeyframeOrientationLabel = new JLabel("Angle:");
      JTextField addKeyframeOrientationField = new JTextField(2);
      this.textFields.put(shape.getName() + " add keyframe orientation field",
          addKeyframeOrientationField);


      JLabel spacer = new JLabel("");
      gridBag.setConstraints(spacer, c);
      this.add(shapeTitle);
      this.add(buttons.get(buttons.size() - 2));
      this.add(spacer);
      this.add(buttons.get(buttons.size() - 1));
      this.add(addKeyframeTimeLabel);
      this.add(addKeyframeTimeField);
      this.add(addKeyframeWidthLabel);
      this.add(addKeyframeWidthField);
      this.add(addKeyframeHeightLabel);
      this.add(addKeyframeHeightField);
      this.add(addKeyframeXLabel);
      this.add(addKeyframeXField);
      this.add(addKeyframeYLabel);
      this.add(addKeyframeYField);
      this.add(addKeyframeRLabel);
      this.add(addKeyframeRField);
      this.add(addKeyframeGLabel);
      this.add(addKeyframeGField);
      this.add(addKeyframeBLabel);
      this.add(addKeyframeBField);
      this.add(addKeyframeOrientationLabel);
      this.add(addKeyframeOrientationField);
      JLabel spacer2 = new JLabel("");
      gridBag.setConstraints(spacer2, c);
      this.add(spacer2);

      for (ReadOnlyAnimatedShape keyFrame : shape.getLog()) {

        JLabel timetag = new JLabel("t: " + keyFrame.getTime());
        JLabel heightLabel = new JLabel("h:");
        JTextField heightField = new JTextField((int) keyFrame.getBoundary().getHeight() + "");
        this.textFields
            .put(shape.getName() + " time " + keyFrame.getTime() + " height field", heightField);
        JLabel widthLabel = new JLabel("w:");
        JTextField widthField = new JTextField((int) keyFrame.getBoundary().getWidth() + "");
        this.textFields
            .put(shape.getName() + " time " + keyFrame.getTime() + " width field", widthField);
        JLabel xLabel = new JLabel("x:");
        JTextField xField = new JTextField((int) keyFrame.getPosn().getX() + "");
        this.textFields.put(shape.getName() + " time " + keyFrame.getTime() + " x field", xField);
        JLabel yLabel = new JLabel("y:");
        JTextField yField = new JTextField((int) keyFrame.getPosn().getY() + "");
        this.textFields.put(shape.getName() + " time " + keyFrame.getTime() + " y field", yField);
        JLabel redLabel = new JLabel("red:");
        JTextField redField = new JTextField(keyFrame.getColor().getRed() + "");
        this.textFields.put(shape.getName() + " time " + keyFrame.getTime() + " r field", redField);
        JLabel greenLabel = new JLabel("green:");
        JTextField greenField = new JTextField(keyFrame.getColor().getGreen() + "");
        this.textFields
            .put(shape.getName() + " time " + keyFrame.getTime() + " g field", greenField);
        JLabel blueLabel = new JLabel("blue:");
        JTextField blueField = new JTextField(keyFrame.getColor().getBlue() + "");
        this.textFields
            .put(shape.getName() + " time " + keyFrame.getTime() + " b field", blueField);
        JLabel orientationLabel = new JLabel("Angle:");
        JTextField orientationField = new JTextField(keyFrame.getOrientation() + "");
        this.textFields.put(shape.getName() + " time " + keyFrame.getTime() + " orientation field",
            orientationField);

        buttons.add(new JButton("edit"));
        buttons.get(buttons.size() - 1)
            .setActionCommand("edit keyframe " + shape.getName() + " " + keyFrame.getTime());
        buttons.add(new JButton("delete"));
        buttons.get(buttons.size() - 1)
            .setActionCommand("delete keyframe " + shape.getName() + " " + keyFrame.getTime());

        c.gridwidth = GridBagConstraints.RELATIVE;
        this.add(timetag);
        this.add(widthLabel);
        this.add(widthField);
        this.add(heightLabel);
        this.add(heightField);
        this.add(xLabel);
        this.add(xField);
        this.add(yLabel);
        this.add(yField);
        this.add(redLabel);
        this.add(redField);
        this.add(greenLabel);
        this.add(greenField);
        this.add(blueLabel);
        this.add(blueField);
        this.add(orientationLabel);
        this.add(orientationField);
        this.add(buttons.get(buttons.size() - 2));
        c.gridwidth = GridBagConstraints.REMAINDER;
        gridBag.setConstraints(buttons.get(buttons.size() - 1), c);
        this.add(buttons.get(buttons.size() - 1));

      }
    }
  }


}
