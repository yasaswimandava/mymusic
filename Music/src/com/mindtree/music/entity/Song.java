package com.mindtree.music.entity;

import java.util.List;

public class Song {
	private int songid;
	private String songname;
	private int likes;
	private String language;
	private List<Artist> artists;
	public Song() {
	}
	public Song(int songid, String songname, int likes, String language, List<Artist> artists) {
		this.songid = songid;
		this.songname = songname;
		this.likes = likes;
		this.language = language;
		this.artists = artists;
	}
	
	public Song(int songid, String songname, int likes, String language) {
		this.songid = songid;
		this.songname = songname;
		this.likes = likes;
		this.language = language;
	}
	public Song(String songname, int likes, String language) {
		this.songname = songname;
		this.likes = likes;
		this.language = language;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((artists == null) ? 0 : artists.hashCode());
		result = prime * result + ((language == null) ? 0 : language.hashCode());
		result = prime * result + likes;
		result = prime * result + songid;
		result = prime * result + ((songname == null) ? 0 : songname.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Song other = (Song) obj;
		if (artists == null) {
			if (other.artists != null)
				return false;
		} else if (!artists.equals(other.artists))
			return false;
		if (language == null) {
			if (other.language != null)
				return false;
		} else if (!language.equals(other.language))
			return false;
		if (likes != other.likes)
			return false;
		if (songid != other.songid)
			return false;
		if (songname == null) {
			if (other.songname != null)
				return false;
		} else if (!songname.equals(other.songname))
			return false;
		return true;
	}
	public int getSongid() {
		return songid;
	}
	public void setSongid(int songid) {
		this.songid = songid;
	}
	public String getSongname() {
		return songname;
	}
	public void setSongname(String songname) {
		this.songname = songname;
	}
	public int getLikes() {
		return likes;
	}
	public void setLikes(int likes) {
		this.likes = likes;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public List<Artist> getArtists() {
		return artists;
	}
	public void setArtists(List<Artist> artists) {
		this.artists = artists;
	}
	@Override
	public String toString() {
		return "Song [songid=" + songid + ", songname=" + songname + ", likes=" + likes + ", language=" + language
				+ ", artists=" + artists + "]";
	}
	
}