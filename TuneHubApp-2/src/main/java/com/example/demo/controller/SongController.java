package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entities.Songs;
import com.example.demo.services.SongService;

@Controller
public class SongController {

	@Autowired
	SongService songServ;

	@PostMapping("addsongs")
	public String addSongs(@ModelAttribute Songs song) {

		boolean songStatus = songServ.validateSong(song.getSongName());

		if (songStatus == false) {
			String msg = songServ.addSongs(song);
			System.out.println(msg);
			return "addsongsuccess";
		} else {
			return "addsongfail";
		}
	}

	// Admin
	@GetMapping("/map-viwsongs")
	public String getAllSongs(Model model) {
		List<Songs> songslist = songServ.getAllSongs();
		model.addAttribute("songslist", songslist);
		System.out.println(songslist);
		return "displaysongs";
	}

	// Customer
	@GetMapping("/viewsongs")
	public String viewSongs(Model model) {
		List<Songs> songslist = songServ.getAllSongs();
		model.addAttribute("songslist", songslist);
		return "viewsongcustomer";
	}

}
