package com.justride.service;

import com.justride.dao.BookingDao;
import com.justride.dao.CustomAddressDAO;
import com.justride.models.Booking;

public class PaymentService implements IPaymentInterface {

	BookingDao bookingDao = new BookingDao();
	CustomAddressDAO customAddressDAO = new CustomAddressDAO();

	@Override
	public int insertBooking(Booking booking) {

		return bookingDao.insertBooking(booking);
	}

	@Override
	public void customLocation(int bookingId, String location) {
		customAddressDAO.changePickupLocation(bookingId, location);

	}

	@Override
	public String getCusLocationIfExists(int bookingId) {
		return customAddressDAO.getCusLocationIfExists(bookingId);
	}

	@Override
	public boolean deleteCusLocation(int bookingId) {
		return customAddressDAO.deleteCusLocation(bookingId);
	}

}
