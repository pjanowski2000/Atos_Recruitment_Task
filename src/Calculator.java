import java.math.BigDecimal;

public class Calculator {
    private final Parser parser;

    public Parser getParser() {
        return parser;
    }

    public Calculator(Parser parser) {
        this.parser = parser;
    }
    public BigDecimal currencyConversion(BigDecimal euro,String currency){
        return  euro.multiply(parser.getRate(currency));
    }
}
