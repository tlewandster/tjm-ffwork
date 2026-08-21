package pl.tlewandster.ffwork.payment;

import pl.tlewandster.ffwork.money.Money;

public class CardPayment extends Payment {
    private String last4;

    public CardPayment(String paymentId, Money amount, String last4) {
        super(paymentId, amount);
        if (!isValidCardNumber(last4)) {
            throw new IllegalArgumentException("Card number is not valid");
        }
        this.last4 = last4;
    }

    @Override
    public String toString() {
        return "CardPayment{" + "last4='" + last4 + '\'' + "} " + super.toString();
    }

    @Override
    public void capture() {
        this.status = PaymentStatus.CAPTURED;
    }

    private boolean isValidCardNumber(String last4) {
        return last4.matches("^\\d{4}$");
    }
}
