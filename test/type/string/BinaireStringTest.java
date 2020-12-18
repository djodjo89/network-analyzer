package type.string;

import model.type.string.BinaireString;
import model.type.string.HexadecimalString;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BinaireStringTest {
  @Test
  public void testConversionSimple() {
    BinaireString actual = new BinaireString("1101");
    assertEquals(13, actual.getNumericValue());
  }

  @Test
  public void testConversionComplexe() {
    BinaireString actual = new BinaireString("11010100101000110111");
    assertEquals(870967, actual.getNumericValue());
  }

  @Test
  public void testConversionZero() {
    BinaireString actual = new BinaireString("0");
    assertEquals(0, actual.getNumericValue());
  }
}
