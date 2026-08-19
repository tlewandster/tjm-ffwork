package pl.tlewandster.ffwork.billing;

import pl.tlewandster.ffwork.domain.Booking;
import pl.tlewandster.ffwork.domain.User;
import pl.tlewandster.ffwork.money.Money;

import java.time.LocalDateTime;

public class Invoice implements Billable {
    private String invoiceNumber;
    private final LocalDateTime issueDate;
    private final User buyer;
    private final Money Total;
    private final String itemDescription;

    public Invoice(String invoiceNumber, LocalDateTime issueDate, User buyer, Money total, String itemDescription) {
        this.invoiceNumber = invoiceNumber;
        this.issueDate = issueDate;
        this.buyer = buyer;
        Total = total;
        this.itemDescription = itemDescription;
    }

    public Invoice toInvoice(Booking booking) {
        String invoiceDescription = "Rezerwacja " + booking.getResource().getName() + " " + booking.getStart() + "-" + booking.getEnd();
        return new Invoice(booking.getId(), LocalDateTime.now(), booking.getUser(), booking.getCalculatedPrice(), invoiceDescription);
    }
}
