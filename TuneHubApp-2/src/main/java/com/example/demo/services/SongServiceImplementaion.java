package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entities.Songs;
import com.example.demo.repositories.SongRepository;

@Service
public class SongServiceImplementaion implements SongService {

	@Autowired
	SongRepository songRepo;

	@Override
	public String addSongs(Songs song) {
		songRepo.save(song);
		return "Song added";
	}

	@Override
	public boolean validateSong(String name) {
		if (songRepo.findBySongName(name) == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public List<Songs> getAllSongs() {
		List<Songs> song = songRepo.findAll();
		return song;
	}

	@Override
	public String updateSong(Songs song) {
		// TODO Auto-generated method stub
		songRepo.save(song);
		return "song added to playlist";
		
	}

}
