package pl.tlewandster.ffwork.billing;

import pl.tlewandster.ffwork.domain.Booking;

public interface Billable {
    Invoice toInvoice(Booking booking);
}
