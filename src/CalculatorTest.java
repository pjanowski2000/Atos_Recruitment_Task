import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class CalculatorTest {
    @Test
    public void test_Calculator_correctly_initialized() {
        Parser parser = new Parser("eurofxref-daily.xml");
        Calculator sut=new Calculator(parser);
        assertEquals(parser, sut.getParser());
    }

    @Test
    public void test_Calculator_currencyConversion() {
        Parser parser = new Parser("eurofxref-daily.xml");
        Calculator sut=new Calculator(parser);
        assertEquals(BigDecimal.valueOf(1.2264), sut.currencyConversion(BigDecimal.valueOf(1),"USD"));
        assertEquals(BigDecimal.valueOf(13409.0705), sut.currencyConversion(BigDecimal.valueOf(100.45),"JPY"));
        assertEquals(BigDecimal.valueOf(24449940610.688835), sut.currencyConversion(BigDecimal.valueOf(1005413232.45),"MXN"));
    }
}
