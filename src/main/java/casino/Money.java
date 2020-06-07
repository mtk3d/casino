package casino;

public class Money {
    private int value;

    private Money(int value) {
        this.value = value;
    }

    public static Money of(int value) {
        if (value < 0) {
            return new Money(0);
        }

        return new Money(value);
    }

    public Money sum(Money money) {
        return new Money(this.value + money.value());
    }

    public Money substract(Money money) {
        return new Money(this.value - money.value());
    }

    public int value() {
        return this.value;
    }

    public String toString() {
        return String.valueOf(this.value) + "$";
    }
}