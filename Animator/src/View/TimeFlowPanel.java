package view;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Represents a Panel that controls the time flow for an {@code EditableView}. Contains buttons and
 * all of the functionality for the buttons of toggling time, toggling looping, setting speed, and
 * creating a shape (This was desireable to include here due to layout aesthetics).
 */
public class TimeFlowPanel extends JPanel {

  private final JButton toggleTime;
  private final JButton looping;
  private final JButton speedButton;
  private final JButton createShape;
  protected final Map<String, JTextField> textFields;
  protected final GridBagLayout gridBag;

  /**
   * Constructs a new {@code TimeFlowPanel} that represents all of the time functionalities
   * of an editable view with the given ticks per second displayed in the speed field.
   *
   * @param ticksPerSecond The speed of the animation in ticks per second to be displayed in the
   *                       speed field.
   */
  public TimeFlowPanel(int ticksPerSecond) {
    super();
    textFields = new HashMap();
    this.setSize(600, 100);
    this.gridBag = new GridBagLayout();
    GridBagConstraints c = new GridBagConstraints();
    this.setLayout(gridBag);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.gridwidth = GridBagConstraints.REMAINDER;

    this.toggleTime = new JButton("Play");
    this.toggleTime.setActionCommand("toggle time");
    this.looping = new JButton("Looping: Off");
    this.looping.setActionCommand("toggle looping");
    this.speedButton = new JButton("Set Speed");
    this.speedButton.setActionCommand("set speed");

    JLabel speedLabel = new JLabel("Speed:");
    this.createShape = new JButton("Create Shape");
    createShape.setActionCommand("create shape");
    JLabel shapeName = new JLabel("name:");
    JLabel shapeType = new JLabel("type:");
    JLabel keyframeTime = new JLabel("time:");
    JLabel keyframeHeight = new JLabel("height:");
    JLabel keyframeWidth = new JLabel("width:");
    JLabel keyframeX = new JLabel("X:");
    JLabel keyframeY = new JLabel("Y:");
    JLabel keyframeR = new JLabel("R:");
    JLabel keyframeG = new JLabel("G:");
    JLabel keyframeB = new JLabel("B:");
    JTextField speedField = new JTextField(ticksPerSecond + "");
    this.textFields.put("speed field", speedField);
    JTextField shapeNameField = new JTextField(5);
    this.textFields.put("name field", shapeNameField);
    JTextField shapeTypeField = new JTextField(5);
    this.textFields.put("type field", shapeTypeField);
    JTextField keyframeTimeField = new JTextField(2);
    this.textFields.put("time field", keyframeTimeField);
    JTextField keyframeHeightField = new JTextField(2);
    this.textFields.put("height field", keyframeHeightField);
    JTextField keyframeWidthField = new JTextField(2);
    this.textFields.put("width field", keyframeWidthField);
    JTextField keyframeXField = new JTextField(2);
    this.textFields.put("x field", keyframeXField);
    JTextField keyframeYField = new JTextField(2);
    this.textFields.put("y field", keyframeYField);
    JTextField keyframeRField = new JTextField(2);
    this.textFields.put("r field", keyframeRField);
    JTextField keyframeGField = new JTextField(2);
    this.textFields.put("g field", keyframeGField);
    JTextField keyframeBField = new JTextField(2);
    this.textFields.put("b field", keyframeBField);
    JLabel spacer = new JLabel("");

    gridBag.setConstraints(spacer, c);
    Container createShapeField = new Container();
    createShapeField.setLayout(new FlowLayout());
    createShapeField.add(createShape);
    createShapeField.add(shapeName);
    createShapeField.add(shapeNameField);
    createShapeField.add(shapeType);
    createShapeField.add(shapeTypeField);
    createShapeField.add(keyframeTime);
    createShapeField.add(keyframeTimeField);
    createShapeField.add(keyframeWidth);
    createShapeField.add(keyframeWidthField);
    createShapeField.add(keyframeHeight);
    createShapeField.add(keyframeHeightField);
    createShapeField.add(keyframeX);
    createShapeField.add(keyframeXField);
    createShapeField.add(keyframeY);
    createShapeField.add(keyframeYField);
    createShapeField.add(keyframeR);
    createShapeField.add(keyframeRField);
    createShapeField.add(keyframeG);
    createShapeField.add(keyframeGField);
    createShapeField.add(keyframeB);
    createShapeField.add(keyframeBField);

    Container timeControls = new Container();
    timeControls.setLayout(new FlowLayout());

    timeControls.add(toggleTime);
    timeControls.add(looping);
    timeControls.add(speedLabel);
    timeControls.add(speedField);
    timeControls.add(speedButton);

    this.add(timeControls);
    this.add(spacer);
    this.add(createShapeField);
  }

  public boolean containsTextField(String fieldName) {
    return this.textFields.containsKey(fieldName);
  }

  public void togglePlayButton() {
    if (this.toggleTime.getText().equals("Play")) {
      this.toggleTime.setText("Pause");
    } else {
      this.toggleTime.setText("Play");
    }
  }

  public void toggleLooping() {
    if (this.looping.getText().equals("Looping: Off")) {
      this.looping.setText("Looping: On");
    } else {
      this.looping.setText("Looping: Off");
    }
  }

  public void addActionListener(ActionListener listener) {
    if (this.toggleTime.getActionListeners().length > 0) {
      ActionListener oldListener = this.toggleTime.getActionListeners()[0];
      this.toggleTime.removeActionListener(oldListener);
      this.looping.removeActionListener(oldListener);
      this.speedButton.removeActionListener(oldListener);
      this.createShape.removeActionListener(oldListener);
    }
    this.toggleTime.addActionListener(listener);
    this.looping.addActionListener(listener);
    this.speedButton.addActionListener(listener);
    this.createShape.addActionListener(listener);
  }

  public void setTextField(String fieldName, String newText) {
    if (this.textFields.containsKey(fieldName)) {
      JTextField field = this.textFields.get(fieldName);
      field.setColumns(newText.length());
      field.setText(newText);
    }
  }

  public String getTextFieldText(String fieldName) {
    if (this.textFields.containsKey(fieldName)) {
      return this.textFields.get(fieldName).getText();
    }
    throw new IllegalArgumentException("No field exists with that name!");
  }

}
