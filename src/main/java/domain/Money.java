package domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Money {
    private final BigDecimal amount;
    private final Currency   currency;

    public Money(BigDecimal amount, Currency currency) {
        this.amount   = amount;
        this.currency = currency;
    }

    public static Money SGD(BigDecimal amount) {
        return new Money(amount, Currency.SGD);
    }

    public static Money zeroSGD() {
        return new Money(BigDecimal.ZERO, Currency.SGD);
    }

    public Money multiplyBy(BigDecimal multiplier) {
        return new Money(this.amount.multiply(multiplier), currency);
    }

    public Money add(Money money) {
        if (money.currency != this.currency) {
            throw new UnsupportedOperationException();
        }
        return new Money(this.amount.add(money.amount), currency);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        Money otherMoney = (Money) other;
        return Objects.equals(amount, otherMoney.amount) &&
                Objects.equals(currency, otherMoney.currency);
    }
}
