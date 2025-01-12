package com.example.demo.controller;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.entities.Songs;
import com.example.demo.entities.Users;
import com.example.demo.services.SongService;
import com.example.demo.services.UsersService;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class NavController {

	@Autowired
	UsersService usersServ;
	
	@Autowired
	SongService songServ;
	
	@GetMapping("/map-register")
	public String registerMapping() {
		return "register";

	}

	@GetMapping("/map-login")
	public String loginMapping() {
		return "login";

	}

	@GetMapping("/map-addsongs")
	public String addSongsMapping() {
		return "addsongs";

	}
	

	//testing purpose..
	@GetMapping("/samplepayment")
	public String samplePayment() {
		return "samplepayment";

	}
	
	
	//Customer home non-premium user
	@GetMapping("/map-customerhome")
	public String customerHome() {
		return "customerhome";

	}
	
	
	//Customer home premium user
	@GetMapping("/map-premiumuser")
	public String premiumUser() {

		return "premiumuser";

	}
	
	// Choose plans
	@GetMapping("/map-upgradePlans")
	public String upgradePlans(Model model,HttpSession session) {

		String email = (String) session.getAttribute("email");
		Users user = usersServ.getUser(email);
		boolean userStatus = user.isPremium();
		if (userStatus == true) {
			return "premiumuser";
		} else {
			return "customerhome";
		}
	}
	
	@GetMapping("/map-forgotPassword")
	public String getMethodName() {
		return "forgotPassword";
	}
	
	
	@GetMapping("/map-backToAdminHome")
	public String backToAdminHome() {
		return "adminhome";
	}
	
	@GetMapping("/map-createPlaylist")
	public String createPlaylist(Model model) {
		// Fetching the songs using songs service
		List<Songs> songslist = songServ.getAllSongs();
		// adding the songs in the model
		model.addAttribute("songslist", songslist);
		// sending createPlaylist.
		return "createplaylist";
	}
	

}
