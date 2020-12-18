package type.numeric;

import model.type.numeric.HexadecimalNumeric;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HexadecimalNumericTest {
  @Test
  public void testConversionSimple() {
    HexadecimalNumeric e = new HexadecimalNumeric(14);

    assertEquals("E", e.toString());
  }

  @Test
  public void testConversionComplexe() {
    HexadecimalNumeric e = new HexadecimalNumeric(165831);

    assertEquals("287C7", e.toString());
  }

  @Test
  public void testConversionZero() {
    HexadecimalNumeric e = new HexadecimalNumeric(0);

    assertEquals("0", e.toString());
  }
}
