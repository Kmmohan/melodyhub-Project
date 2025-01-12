package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import com.example.demo.entities.Playlist;
import com.example.demo.entities.Songs;
import com.example.demo.services.PlaylistService;
import com.example.demo.services.SongService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PlaylistController {

	@Autowired
	PlaylistService playSer;

	@Autowired
	SongService songServ;

	@GetMapping("/createplaylist")
	public String createPlaylist(Model model) {
		// Fetching the songs using songs service
		List<Songs> songslist = songServ.getAllSongs();
		// adding the songs in the model
		model.addAttribute("songslist", songslist);
		// sending createPlaylist.
		return "createplaylist";
	}
	
	@PostMapping("/addplaylist")
	public String addPlaylist(Playlist playlist) {
		String msg=playSer.addPlaylist(playlist);
		System.out.println(msg);
		//update song table
		List<Songs> songList=playlist.getSong();
		for(Songs song: songList) {
			song.getPlaylists().add(playlist);
			String msg2=songServ.updateSong(song);
			System.out.println(msg2);
		}
		return "playlistsuccess";
		
	}
	
	@GetMapping("/map-viewpaylists")
	public String viewPlaylists(Model model) {
		List<Playlist> plist =playSer.fetchPlaylists();
//		System.out.println(plist);
		model.addAttribute("plist", plist);
		return "viewpaylist";
		
	}
	
	//view Palylist Customer  premium user
	@GetMapping("/map-viewpaylistcustomer")
	public String viewpaylistCustomer(Model model) {
		List<Playlist> plist =playSer.fetchPlaylists();
		model.addAttribute("plist", plist);
		return "viewpaylistcustomer";

	}
	

}
