package com.justride.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.justride.util.GetConnection;

public class CustomAddressDAO implements ICustomAddressDao {

	@Override
	public void changePickupLocation(int bookingId, String pickupLocation) {
		Connection conn;

		try {
			conn = GetConnection.getConnection();
			if (conn != null) {
				String sql = "insert into booking_pickuplocation values (?,?)";
				try {
					java.sql.PreparedStatement statement = conn.prepareStatement(sql);
					statement.setInt(1, bookingId);
					statement.setString(2, pickupLocation);
					statement.executeUpdate();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}

	@Override
	public String getCusLocationIfExists(int bookingId) {
		// select * from booking_pickuplocation where booking_id=19;
		String sql = "select booking_id, pickup_location from booking_pickuplocation where booking_id=?";
		String location = "empty";
		Connection conn;
		try {
			conn = GetConnection.getConnection();
			if (conn != null) {
				try {
					java.sql.PreparedStatement statement = conn.prepareStatement(sql);
					statement.setInt(1, bookingId);
					ResultSet rs = statement.executeQuery();
					if (rs != null) {
						while (rs.next()) {
							location = rs.getString("pickup_location");
						}
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return location;
	}

	@Override
	public boolean deleteCusLocation(int bookingId) {
		// select * from booking_pickuplocation where booking_id=19;
		String sql = "delete from booking_pickuplocation where booking_id=?";
		int i = -1;
		boolean status = false;
		Connection conn;
		try {
			conn = GetConnection.getConnection();
			if (conn != null) {
				try {
					java.sql.PreparedStatement statement = conn.prepareStatement(sql);
					statement.setInt(1, bookingId);
					i = statement.executeUpdate();
					System.out.println("Delete status i=" + i);
					if (i == 1)
						status = true;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		return status;
	}

}
