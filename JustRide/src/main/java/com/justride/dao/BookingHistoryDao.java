package com.justride.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.justride.models.Booking;
import com.justride.models.User;
import com.justride.util.GetConnection;
import com.justride.util.MailUtil;
import com.mysql.jdbc.PreparedStatement;

public class BookingHistoryDao implements IBookingHistoryDao {

	@Override
	public ArrayList<Booking> getBookingbyUserId(String email) {
		CustomAddressDAO customAddressDAO = new CustomAddressDAO();
		ArrayList<Booking> bookings = new ArrayList<Booking>();

		Connection con;
		try {
			con = GetConnection.getConnection();
			String sql = "select booking_id, email, carid, intimestamp, outtimestamp, pickup_location, amount from bookinginfo where email=? and intimestamp >= now()";
			java.sql.PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, email);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int bookingId = rs.getInt("booking_id");
				int carId = rs.getInt("carid");
				String inTimeStamp = rs.getString("intimestamp").substring(0, 16);
				String outTImeStamp = rs.getString("outtimestamp").substring(0, 16);
				String pickup_location = "";
				if (!customAddressDAO.getCusLocationIfExists(bookingId).equals("empty")) {
					pickup_location = customAddressDAO.getCusLocationIfExists(bookingId);
				} else {
					System.out.println("In the booking History Dao=======37");
					pickup_location = rs.getString("pickup_location");
				}
				float amount = rs.getFloat("amount");
				CarDao carDao = new CarDao();
				String carName = carDao.getCarbyId(carId).getCarName();
				String dropoffLocation = rs.getString("pickup_location");
				Booking booking = new Booking(inTimeStamp, outTImeStamp, bookingId, email, carId, pickup_location,
						amount, carName, dropoffLocation);
				bookings.add(booking);
			}
		}

		// public Booking(String stringInTime, String stringOutTime, String
		// dropoffLocation, int bookingId, String email,
		// String carName, String pickupLocation, float amount)

		catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookings;

	}

	@Override
	public boolean deleteBooking(int bookingId, String emailId) {
		Connection con;
		CustomAddressDAO customAddressDAO = new CustomAddressDAO();
		try {
			con = GetConnection.getConnection();
			String sql = "delete from bookinginfo where booking_id=?";
			java.sql.PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, bookingId);
			customAddressDAO.deleteCusLocation(bookingId);
			stmt.executeUpdate();
			MailUtil mailUtil = new MailUtil();
			UserDao userDao = new UserDao();
			User user = userDao.getUserDetails(emailId);
			float price = getBookingPriceById(bookingId);
			String messageBody = "Your reservation has been cancelled and the cost of $" + price
					+ "will be credited to xxxx-" + user.getCardNo() + "\n\n" + "We hope to see you again!"
					+ "\n\n ---JustRide Sales Management";
			String subject = "Booking Cancellation";
			mailUtil.sendEmail(emailId, user.getFirstName(), messageBody, subject);
			return true;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public float getBookingPriceById(int bookingId) {
		float price = 0;

		Connection con;
		try {
			con = GetConnection.getConnection();
			String sql = "select amount from bookinginfo where booking_id=?";
			java.sql.PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setInt(1, bookingId);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				price = rs.getFloat("amount");
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return price;

	}

}
