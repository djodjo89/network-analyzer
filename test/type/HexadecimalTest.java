package type;

import model.type.Hexadecimal;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HexadecimalTest {
  @Test
  public void testConversionSimple() {
    Hexadecimal e = new Hexadecimal(14);

    assertEquals("e", e.toString());
  }
}
