package com.justride.daotests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.justride.dao.BookingHistoryDao;
import com.justride.models.Booking;

public class BookingHistoryDaoTest {
	
	BookingHistoryDao bhDao;
	ArrayList<Booking> bookings;
	String testEmail;
	int testBookingID;
	boolean methodResult;

	@Before
	public void setUp() throws Exception {
		bhDao = new BookingHistoryDao();
		testEmail = "rbalanka3@uncc.edu";
		testBookingID = 2;
		bookings = new ArrayList<Booking>();
		methodResult = false;
	}

	@Test
	public void getBookingbyUserIdTest() {
		bookings = bhDao.getBookingbyUserId(testEmail);
		assertEquals(12, bookings.size());
	}
	
	@Test
	public void deleteBookingTest() {
		methodResult = bhDao.deleteBooking(testBookingID);
		assertEquals(true, methodResult);
	}

}
