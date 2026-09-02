package pl.tlewandster.ffwork.payment;

import pl.tlewandster.ffwork.money.Money;

public class CardPayment extends Payment {
    private final String fourLastCardNumbers;

    public CardPayment(String paymentId, Money amount, String fourLastCardNumbers) {
        super(paymentId, amount);
        if (!isValidCardNumber(fourLastCardNumbers)) {
            throw new IllegalArgumentException("Card number is not valid");
        }
        this.fourLastCardNumbers = fourLastCardNumbers;
    }

    private boolean isValidCardNumber(String last4) {
        return last4.matches("^\\d{4}$");
    }

    @Override
    public void capture() {
        this.status = PaymentStatus.CAPTURED;
    }

    @Override
    public String toString() {
        return "CardPayment{" + "fourLastCardNumbers='" + fourLastCardNumbers + '\'' + "} " + super.toString();
    }
}
