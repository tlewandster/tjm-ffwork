package pl.tlewandster.ffwork.payment;

import pl.tlewandster.ffwork.money.Money;

public abstract class Payment {
    protected PaymentStatus status;
    private Money amount;
    private String paymentId;

    public Payment(String paymentId, Money amount) {
        this.amount = amount;
        this.paymentId = paymentId;
        this.status = PaymentStatus.INITIATED;
    }

    public abstract void capture() throws IllegalStateException;

    @Override
    public String toString() {
        return "Payment{" + "status=" + status + ", amount=" + amount + ", paymentId='" + paymentId + '\'' + '}';
    }
}
