package type.numeric;

import model.type.numeric.BinaireNumeric;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaireNumericTest {
  @Test
  public void testConversionSimple() {
    assertEquals("1001110", new BinaireNumeric(78).toString());
  }

  @Test
  public void testConversionComplexe() {
    assertEquals("10101101111110001101111010", new BinaireNumeric(45605754).toString());
  }

  @Test
  public void testConversionZero() {
    assertEquals("0", new BinaireNumeric(0).toString());
  }
}
