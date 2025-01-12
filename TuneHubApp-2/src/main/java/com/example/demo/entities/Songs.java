package com.example.demo.entities;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

@Entity
public class Songs {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	int id;
	String songName;
	String songArtist;
	String songGenere;
	public String songLink;

	@ManyToMany
	List<Playlist> playlists;

	public Songs(int id, String songName, String songArtist, String songGenere, String songLink, List<Playlist> playlists) {
		super();
		this.id = id;
		this.songName = songName;
		this.songArtist = songArtist;
		this.songGenere = songGenere;
		this.songLink = songLink;
		this.playlists = playlists;
	}

	public Songs() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public String getSongArtist() {
		return songArtist;
	}

	public void setSongArtist(String songArtist) {
		this.songArtist = songArtist;
	}

	public String getSongGenere() {
		return songGenere;
	}

	public void setSongGenere(String songGenere) {
		this.songGenere = songGenere;
	}

	public String getsongLink() {
		return songLink;
	}

	public void setsongLink(String songLink) {
		this.songLink = songLink;
	}

	public List<Playlist> getPlaylists() {
		return playlists;
	}

	public void setPlaylists(List<Playlist> playlists) {
		this.playlists = playlists;
	}

	@Override
	public String toString() {
		return "Songs [id=" + id + ", songName=" + songName + ", songArtist=" + songArtist + ", songGenere=" + songGenere + ", songLink=" + songLink + "]";
	}

}
