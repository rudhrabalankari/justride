package com.justride.service;

import com.justride.models.Booking;

public interface IPaymentInterface {
	public int insertBooking(Booking booking);

	public void customLocation(int bookingId, String location);

	public String getCusLocationIfExists(int bookingId);

	public boolean deleteCusLocation(int bookingId);

}
