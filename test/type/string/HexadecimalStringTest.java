package type.string;

import model.type.string.HexadecimalString;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HexadecimalStringTest {
  @Test
  public void testConversionSimple() {
    HexadecimalString actual = new HexadecimalString("a");
    assertEquals(10, actual.getNumericValue());
  }

  @Test
  public void testConversionComplexe() {
    HexadecimalString actual = new HexadecimalString("2B7E37A");
    assertEquals(45605754, actual.getNumericValue());
  }

  @Test
  public void testConversionZero() {
    HexadecimalString actual = new HexadecimalString("0");
    assertEquals(0, actual.getNumericValue());
  }
}
