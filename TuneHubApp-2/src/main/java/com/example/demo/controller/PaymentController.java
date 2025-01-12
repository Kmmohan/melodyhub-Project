package com.example.demo.controller;

import org.json.JSONObject;

import com.razorpay.*;
//    OR
//import com.razorpay.Order;
//import com.razorpay.RazorpayClient;
//import com.razorpay.RazorpayException;
//import com.razorpay.Utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.entities.Users;
import com.example.demo.services.UsersService;


import jakarta.servlet.http.HttpSession;

@Controller
public class PaymentController {

	@Autowired
	UsersService uservice;

	@PostMapping("createorder")
	@ResponseBody
	public String createOrder() {
		Order order = null;
		try {
			RazorpayClient razorpay = new RazorpayClient("rzp_test_ex0rh0CP26x2E5", "lwupUWcWtzXWXKHsxtvjp8SN");

			JSONObject orderRequest = new JSONObject();
			orderRequest.put("amount", 50000);
			orderRequest.put("currency", "INR");
			orderRequest.put("receipt", "receipt#1");
			JSONObject notes = new JSONObject();
			notes.put("notes_key_1", "Tea, Earl Grey, Hot");
			orderRequest.put("notes", notes);

			order = razorpay.orders.create(orderRequest);
		} catch (Exception e) {
			System.out.println("Exception while creating Order.");
		}
		return order.toString();
	}

	@PostMapping("/verify")
	@ResponseBody
	public boolean verifyPayment(@RequestParam String orderId, @RequestParam String paymentId,
			@RequestParam String signature) {
		try {
			// Initialize Razorpay client with your API key and secret
			RazorpayClient razorpayClient = new RazorpayClient("rzp_test_ex0rh0CP26x2E5", "lwupUWcWtzXWXKHsxtvjp8SN");
			// Create a signature verification data string
			String verificationData = orderId + "|" + paymentId;

			// Use Razorpay's utility function to verify the signature
			boolean isValidSignature = Utils.verifySignature(verificationData, signature, "lwupUWcWtzXWXKHsxtvjp8SN");

			return isValidSignature;
		} catch (RazorpayException e) {
			e.printStackTrace();
			return false;
		}
	}

	// payment-success -> update to premium user
	@GetMapping("payment-success")
	public String paymentSuccess(HttpSession session) {
		
		String email= (String) session.getAttribute("email");
		Users user = uservice.getUser(email);
		user.setPremium(true);
		uservice.updateUser(user);
		//Customer is now Premium user, he/she can use premium services by login.
		return "login";

	}

	// payment-failure -> redirect to payment page
	@GetMapping("payment-failure")
	public String paymentFailure() {
		//Payment by customer is failed so redirecting the customer back to the 'makepayment' page.
		return "makepayment";

	}
}
