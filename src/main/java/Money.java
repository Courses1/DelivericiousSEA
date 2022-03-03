import java.math.BigDecimal;

public class Money {
    private final BigDecimal amount;
    private final Currency currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount = amount;
        this.currency = currency;
    }

    public static Money SGD(BigDecimal amount) {
        return new Money(amount, Currency.SGD);
    }

}
