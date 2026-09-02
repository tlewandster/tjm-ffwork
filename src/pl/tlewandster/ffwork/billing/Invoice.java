package pl.tlewandster.ffwork.billing;

import pl.tlewandster.ffwork.domain.Booking;
import pl.tlewandster.ffwork.domain.User;
import pl.tlewandster.ffwork.money.Money;

import java.time.LocalDateTime;

public class Invoice implements Billable {
    private String invoiceNumber;
    private LocalDateTime issueDate;
    private User buyer;
    private Money total;
    private String itemDescription;

    public Invoice toInvoice(Booking booking) {
        String invoiceDescription = "Rezerwacja " + booking.getResource().getName() + " " + booking.getStart() + "-" + booking.getEnd();
        this.invoiceNumber = booking.getId();
        this.issueDate = LocalDateTime.now();
        this.buyer = booking.getUser();
        this.total = booking.getCalculatedPrice();
        this.itemDescription = invoiceDescription;
        return this;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", issueDate=" + issueDate +
                ", buyer=" + buyer +
                ", total=" + total +
                ", itemDescription='" + itemDescription + '\'' +
                '}';
    }
}
