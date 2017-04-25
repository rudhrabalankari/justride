package com.justride.servicetests;

import static org.junit.Assert.*;
import static org.mockito.Mockito.validateMockitoUsage;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.justride.dao.BookingHistoryDao;
import com.justride.models.Booking;
import com.justride.service.BookingHistoryServie;

public class BookingHistoryServieTest {
	
	private BookingHistoryServie bhService;
	private BookingHistoryDao bookingHistoryDao;
	private String testEmail;;
	private int testBookingID;
	ArrayList<Booking> bookingDetails;
	Booking testBookingInfo1;
	Booking testBookingInfo2;

	@Before
	public void setUp() throws Exception {
		bhService = new BookingHistoryServie();
		bookingHistoryDao = Mockito.mock(BookingHistoryDao.class);
		testEmail = "rchallur@uncc.edu";
		bookingDetails = new ArrayList<Booking>();
		testBookingInfo1 = new Booking("2008-10-25 20:30:10", "2008-10-26 20:30:10", 1, "rchallur@uncc.edu", "nano", "UNC, Charlotte", (float) 500.85);
		testBookingInfo2 = new Booking("2008-10-28 20:30:10", "2008-10-29 20:30:10", 2, "rchallur@uncc.edu", "nano", "UNC, Charlotte", (float) 500.85);
		bookingDetails.add(testBookingInfo1);
		bookingDetails.add(testBookingInfo2);
		testBookingID = bookingDetails.get(0).getBookingId();
		when(bookingHistoryDao.getBookingbyUserId(testEmail)).thenReturn(bookingDetails);
		when(bookingHistoryDao.deleteBooking(testBookingID)).thenReturn(true);
		
	}

	@Test
	public void getBookingbyUserIdTest() {
		ArrayList<Booking> booking = bhService.getBookingbyUserId(testEmail);
		assertEquals(0, booking.size());
	}
	
	@Test
	public void deleteBookingTest() {
		assertEquals(true, bhService.deleteBooking(testBookingID));
	}

}
