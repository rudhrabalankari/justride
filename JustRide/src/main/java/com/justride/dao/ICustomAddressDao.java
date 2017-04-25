package com.justride.dao;

public interface ICustomAddressDao {
	public void changePickupLocation(int bookingId, String pickupLocation);

	public String getCusLocationIfExists(int bookingId);

	public boolean deleteCusLocation(int bookingId);

}
