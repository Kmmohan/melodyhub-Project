package com.example.demo.services;

import java.util.List;

import com.example.demo.entities.Songs;

public interface SongService {

	public String addSongs(Songs song);
	
	public boolean validateSong(String name);
	
	public List<Songs> getAllSongs();

	public String updateSong(Songs song);

}
