package com.ssdi.justride;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.justride.models.Booking;

import com.justride.models.Car;
import com.justride.models.User;
import com.justride.service.CarServie;
import com.justride.service.PaymentService;
import com.justride.service.UserService;

@Controller
public class BookingConfController {

	UserService userServie = new UserService();
	CarServie carServie = new CarServie();
	Car selectedCar;
	float totalPrice;
	User user;

	@RequestMapping(value = "/justride/confirmBooking", method = RequestMethod.GET)
	public String bookingConfirmation(Locale locale, Model model, HttpServletRequest request) {

		int carId = Integer.parseInt(request.getParameter("carId"));

		HttpSession session = request.getSession();

		String email = (String) session.getAttribute("email");

		user = userServie.getUserDetails(email);
		LocalDateTime dateTime, dateTime2;

		dateTime = (LocalDateTime) session.getAttribute("dateTime1");
		dateTime2 = (LocalDateTime) session.getAttribute("dateTime2");

		totalPrice = carServie.calculateRent(carId, dateTime, dateTime2);

		model.addAttribute("startTime", "" + dateTime.getHour() + ":" + dateTime.getMinute() + "");
		model.addAttribute("endTime", dateTime2.getHour() + ":" + dateTime2.getMinute() + "");
		model.addAttribute("startDate",
				dateTime.getMonthValue() + "/" + dateTime.getDayOfMonth() + "/" + dateTime.getYear());
		model.addAttribute("endDate",
				dateTime2.getMonthValue() + "/" + dateTime2.getDayOfMonth() + "/" + dateTime2.getYear());
		selectedCar = carServie.getCarbyId(carId);

		Booking booking = new Booking(email, carId, dateTime, dateTime2, selectedCar.getLocation(), totalPrice);

		session.setAttribute("booking", booking);

		System.out.println("Car Location===========" + selectedCar.getLocation());
		model.addAttribute("car", selectedCar);
		model.addAttribute("totalPrice", totalPrice);
		model.addAttribute("user", user);
		return "bookinginfo";
	}

	@RequestMapping(value = "/confirmBooking", method = RequestMethod.GET)
	public String bookingConfirmation2(Locale locale, Model model, HttpServletRequest request) {

		int carId = Integer.parseInt(request.getParameter("carId"));

		HttpSession session = request.getSession();

		String email = (String) session.getAttribute("email");

		User user = userServie.getUserDetails(email);
		LocalDateTime dateTime, dateTime2;

		dateTime = (LocalDateTime) session.getAttribute("dateTime1");
		dateTime2 = (LocalDateTime) session.getAttribute("dateTime2");

		float totalPrice = carServie.calculateRent(carId, dateTime, dateTime2);

		model.addAttribute("startTime", "" + dateTime.getHour() + ":" + dateTime.getMinute() + "");
		model.addAttribute("endTime", dateTime2.getHour() + ":" + dateTime2.getMinute() + "");
		model.addAttribute("startDate",
				dateTime.getMonthValue() + "/" + dateTime.getDayOfMonth() + "/" + dateTime.getYear());
		model.addAttribute("endDate",
				dateTime2.getMonthValue() + "/" + dateTime2.getDayOfMonth() + "/" + dateTime2.getYear());
		Car selectedCar = carServie.getCarbyId(carId);

		Booking booking = new Booking(email, carId, dateTime, dateTime2, selectedCar.getLocation(), totalPrice);

		session.setAttribute("booking", booking);

		System.out.println("Car Location===========" + selectedCar.getLocation());
		model.addAttribute("car", selectedCar);
		model.addAttribute("totalPrice", totalPrice);

		return "guestrental";
	}

	@RequestMapping(value = "/justride/makepayment", method = RequestMethod.GET)
	public String insertBooking(Locale locale, Model model, HttpServletRequest request) {
		int bookingId = 0;
		HttpSession session = request.getSession();
		Booking booking = (Booking) session.getAttribute("booking");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
		String intime = booking.getIntimeStamp().format(formatter);
		System.out.println("In time stamp==========" + intime);
		String outTime = booking.getOutTimeStamp().format(formatter);
		System.out.println("In time stamp==========" + outTime);
		PaymentService paymentService = new PaymentService();

		String pickupType = request.getParameter("pickuptype");
		System.out.println("Selected pickup type====" + pickupType);
		String address1 = "", address2 = "";
		if (pickupType.equals("custom")) {
			address1 = request.getParameter("address1");
			address2 = request.getParameter("address2");
			if (address1.length() > 2 && address2.length() > 2) {
				System.out.println("Passss");
				session.setAttribute("pickupLocation", (address1 + address2));
				bookingId = paymentService.insertBooking(booking);
			} else {
				System.out.println("Invalid address details!");
				model.addAttribute("error", "Invalid address details!");
				model.addAttribute("car", selectedCar);
				model.addAttribute("totalPrice", totalPrice);
				model.addAttribute("user", user);
				return "bookinginfo";
			}
			System.out.println("Address1=============" + address1);
			System.out.println("Address2=============" + address2);
		} else {
			System.out.println("Selected pickup type====" + pickupType);

			bookingId = paymentService.insertBooking(booking);
		}

		System.out.println("The booking id after booking is============" + bookingId);
		model.addAttribute("error", "Booking confirmed with booking id: " + bookingId);
		return "home2";
	}
}
