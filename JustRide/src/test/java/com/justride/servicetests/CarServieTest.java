package com.justride.servicetests;

import static org.junit.Assert.*;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.justride.dao.CarDao;
import com.justride.dao.UserDao;
import com.justride.models.Car;
import com.justride.service.CarServie;

public class CarServieTest {
	
	CarServie cs;
	//CarDao carDao;
	Car testCar;
	int testCarID;
	LocalDateTime startDateTime;
	LocalDateTime endDateTime;

	@Before
	public void setUp() throws Exception {
		cs = new CarServie();
		//carDao = Mockito.mock(CarDao.class);
		testCar = new Car(1, 20, "nano", "Economy", "65mph", 5);
		testCarID = 1;
		startDateTime = LocalDateTime.now();
		endDateTime = LocalDateTime.now();
	}

	@Test
	public void getCarbyIdTest() {
		assertEquals(testCar.getCarId(), cs.getCarbyId(testCarID).getCarId());
	}
	
	@Test
	public void calculateRentTest() {
		float methodResult = cs.calculateRent(testCarID, startDateTime, endDateTime);
		assertEquals((float)0,(float)methodResult, 0);
	}

}