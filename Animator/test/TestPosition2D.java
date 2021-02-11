import org.junit.Test;

import model.Position2D;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Tests that our Position class implementation works as expected.
 */
public class TestPosition2D {

  //Position at (0, 0)
  Position2D pos = new Position2D(0, 0);

  //Position at (5, 10)
  Position2D pos1 = new Position2D(5, 10);

  //Tests that the position returns the correct value for fields
  @Test
  public void testPosGetters() {
    assertEquals(0, this.pos.getX(), .01);
    assertEquals(0, this.pos.getY(), .01);
    assertEquals(5, this.pos1.getX(), .01);
    assertEquals(10, this.pos1.getY(), .01);
  }

  //Tests that the positions can accurately set the values for each coordinate
  @Test
  public void testPosSetters() {
    this.pos.setX(10);
    this.pos.setY(15);

    assertEquals(10, this.pos.getX(), .01);
    assertEquals(15, this.pos.getY(), .01);
  }

  //Tests that two positions with the same coordinates are equivalent
  @Test
  public void testHasEqPos() {
    Position2D pos2 = new Position2D(0, 0);
    Position2D pos3 = new Position2D(10, 0);

    assertEquals(pos2, pos);
    assertEquals(pos2.hashCode(), pos.hashCode());
    assertNotEquals(pos3, pos);
    assertNotEquals(pos3.hashCode(), pos.hashCode());
  }
}
