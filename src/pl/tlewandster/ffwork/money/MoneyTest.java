package pl.tlewandster.ffwork.money;

import java.math.BigDecimal;

public class MoneyTest {
    static void main() {
        try {
            Money money = new Money(null);
            System.out.println("money = " + money);
        } catch (Exception e) {
            System.err.println(e);
        }

        try {
            Money money1 = new Money(new BigDecimal(-1));
            System.out.println("money1 = " + money1);
        } catch (Exception e) {
            System.err.println(e);
        }

        Money money2 = new Money(new BigDecimal("123.456"));
        System.out.println("money2 = " + money2);

        Money money3 = Money.of("234.4");
        System.out.println("money3 = " + money3);

        Money money4 = Money.of(0.1);
        System.out.println("money4 = " + money4);

        Money money5 = money2.add(money3);
        System.out.println("money5 = " + money5);

        Money money6 = money3.subtract(money2);
        System.out.println("money6 = " + money6);

        Money money7 = money5.multiply(0.01);
        System.out.println("money7 = " + money7);

        Money money8 = money5.multiply(new BigDecimal("1.2345"));
        System.out.println("money8 = " + money8);

        int compare = money2.compareTo(money3);
        System.out.println("compare = " + compare);

        boolean equals = Money.of("0.7").add(Money.of("0.1"))
                .equals(Money.of(0.7).add(Money.of(0.1)));
        System.out.println("equals = " + equals);
    }
}
