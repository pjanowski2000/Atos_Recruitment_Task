import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
public class ParserTest {

    @Test
    public void test_Register_correctly_initialized() {
        Parser sut = new Parser("eurofxref-daily.xml");
        assertEquals("eurofxref-daily.xml", sut.getFilePath());
    }

    @Test
    public void test_Register_getRate() {
        Parser sut = new Parser("eurofxref-daily.xml");
        assertEquals(BigDecimal.valueOf(1.2264), sut.getRate("USD"));
        assertEquals(BigDecimal.valueOf(133.49), sut.getRate("JPY"));
        assertEquals(BigDecimal.valueOf(148.3), sut.getRate("ISK"));
        assertEquals(BigDecimal.valueOf(24.3183), sut.getRate("MXN"));
        assertEquals(BigDecimal.valueOf(16.9591), sut.getRate("ZAR"));
        assertEquals(BigDecimal.valueOf(10.3176), sut.getRate("TRY"));
    }

    @Test
    public void test_Register_getRate_Bad_Input() {
        Parser sut = new Parser("eurofxref-daily.xml");
        assertThrows(Exception.class,
                () -> sut.getRate("123"),
                "Wrong currency format "
        );
        assertThrows(Exception.class,
                () -> sut.getRate("abc"),
                "Wrong currency format "
        );
        assertThrows(Exception.class,
                () -> sut.getRate("AaA"),
                "Wrong currency format "
        );
        assertThrows(Exception.class,
                () -> sut.getRate("1AA"),
                "Wrong currency format "
        );
        assertThrows(Exception.class,
                () -> sut.getRate("AA"),
                "Wrong currency format "
        );
    }

    @Test
    public void test_Register_getRate_Currency_Not_Exists() {
        Parser sut = new Parser("eurofxref-daily.xml");
        assertThrows(Exception.class,
                () -> sut.getRate("AAA"),
                "This currency not exists in database"
        );
        assertThrows(Exception.class,
                () -> sut.getRate("ZZZ"),
                "This currency not exists in database"
        );
        assertThrows(Exception.class,
                () -> sut.getRate("ABC"),
                "This currency not exists in database"
        );


    }
}


