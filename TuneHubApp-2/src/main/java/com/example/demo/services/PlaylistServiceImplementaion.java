package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Playlist;
import com.example.demo.repositories.PlaylistRepository;

@Service
public class PlaylistServiceImplementaion implements PlaylistService {

	@Autowired
	PlaylistRepository playRepo;

	@Override
	public String addPlaylist(Playlist playlist) {
		playRepo.save(playlist);
		return "song saved in playlist";
	}

	@Override
	public List<Playlist> fetchPlaylists() {
		return playRepo.findAll();
	}

}
