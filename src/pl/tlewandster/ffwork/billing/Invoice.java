package pl.tlewandster.ffwork.billing;

import pl.tlewandster.ffwork.domain.Booking;
import pl.tlewandster.ffwork.domain.User;
import pl.tlewandster.ffwork.money.Money;

import java.time.LocalDateTime;

public class Invoice implements Billable{
    private String InvoiceNumber;
    private LocalDateTime issueDate;
    private User buyer;
    private Money Total;
    private String itemDescription;


    @Override
    public Invoice toInvoice(Booking booking) {
        return null;
    }
}
