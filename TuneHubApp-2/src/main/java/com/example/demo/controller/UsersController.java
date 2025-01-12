package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.entities.Songs;
import com.example.demo.entities.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;

@Controller
public class UsersController {

	@Autowired
	SongService songServ;
	
	@Autowired
	UsersService usersServ;

	@PostMapping("/register")
	public String addUser(@ModelAttribute Users user) {
		boolean usersStatus = usersServ.emailExists(user.getEmail());
		if (usersStatus == false) {
			usersServ.addUser(user);
			System.out.println("User is added");
			return "registersuccess";
		} else {
			System.out.println("User is already exist");
		}
		return "registerfail";
	}

	@PostMapping("/login")
	public String validateUser(Model model ,@RequestParam String email, @RequestParam String password, HttpSession session) {
		
		boolean validEmail =usersServ.emailExists(email);
		if(validEmail) {
		if (usersServ.validateUser(email, password)) {
//			String name =usersServ.getName(email);
//			System.out.println(name);
			
			//welcome users by name.
			model.addAttribute("userName",usersServ.getName(email));
			// User Session
			session.setAttribute("email", email);
			if (usersServ.getRole(email).equals("admin")) {
				return "adminhome";
			} else {
				return "customerhome";
			}
		}
		else {
			return "loginfail";
		}
		}
		else {
			return "loginfail";
		}
	}

	// Customer
	@GetMapping("/exploresongs")
	public String exploreSongs(Model model,HttpSession session) {
		String email = (String) session.getAttribute("email");
		Users user = usersServ.getUser(email);
		boolean userStatus = user.isPremium();
		if (userStatus == true) {
			List<Songs> songslist = songServ.getAllSongs();
			model.addAttribute("songslist", songslist);
			//return "viewsongcustomer";
			return "premiumuser";
		} else {
			// return "makepayment";
			return "samplepayment";
		}
	}
	
	@PostMapping("/reSetPassword")
	public String forgotPassword(@RequestParam String email,@RequestParam String password , Model model) {
		boolean validEmail=usersServ.emailExists(email);
		model.addAttribute("condition",validEmail );
		if(validEmail) {
			Users user=usersServ.getUser(email);
			user.setPassword(password);
			usersServ.updatePassword(user);
			System.out.println("Password reset successful!");
			return "login";
		}else
			System.out.println("Password reset fail or Invalid Email");
		return "forgotPassword";
		
	}
	
	

}
