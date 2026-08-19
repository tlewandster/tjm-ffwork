package pl.tlewandster.ffwork.payment;

import pl.tlewandster.ffwork.money.Money;

public abstract class Payment {
    private Money amount;
    private String paymentId;
    protected PaymentStatus status;

    public abstract void capture();
}
