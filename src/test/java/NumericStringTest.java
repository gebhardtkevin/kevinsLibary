import org.junit.jupiter.api.Test;

import static ch.trinitreesoft.NumericString.*;
import static org.junit.jupiter.api.Assertions.*;

public class NumericStringTest {
    String intString1  =" 5 ";
    String intString2 = "-593";

    String doubleString1 = "45.3";

    String doubleString2 = " -5993885.098d";

    String doubleString3 = "4.5e-3";
    String floatString1 = "45.3f";
    String hexDoubleTest="0x12";

    String nonNumericStringTest= "shouldFail";

    @Test
    void intTest(){
       assertTrue(isInt(intString1));
       assertTrue(isInt(intString2));
       assertFalse(isInt(doubleString1));
        assertEquals(5,parseAsInt(intString1));
        assertEquals(-593,parseAsInt(intString2));
        assertThrows(NumberFormatException.class,()->parseAsInt(doubleString1));
    }

    @Test
    void doubleTest(){
        assertTrue(isDouble(intString1));
        assertTrue(isDouble(doubleString1));
        assertTrue(isDouble(doubleString2));
        assertTrue(isDouble(doubleString3));
        assertFalse(isDouble(nonNumericStringTest));
        assertEquals(45.3,parseAsDouble(doubleString1));
        assertEquals(-5993885.098d,parseAsDouble(doubleString2));
        assertEquals(4.5e-3,parseAsDouble(doubleString3));
        assertEquals(45.3f,parseAsDouble(doubleString1));
        assertEquals(0x12,parseAsDouble(doubleString1));
        assertThrows(NumberFormatException.class,()->parseAsInt(nonNumericStringTest));
    }
}
