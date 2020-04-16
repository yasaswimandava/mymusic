package com.mindtree.music.entity;

import java.util.Comparator;
import java.util.Set;

public class Album implements Comparable<Album>, Comparator<Album> {
	private int albumid;
	private String albumname;
	private double albumprice;
	private double rating;
	private Set<Song> songs;
	private Set<Artist> artis;

	public Album() {
	}

	public Album(String albumname, double albumprice, double rating) {
		this.albumname = albumname;
		this.albumprice = albumprice;
		this.rating = rating;
	}

	public Set<Artist> getArtis() {
		return artis;
	}

	public void setArtis(Set<Artist> artis) {
		this.artis = artis;
	}

	public Album(int albumid, String albumname, double albumprice, double rating, Set<Song> songs, Set<Artist> artis) {
		this.albumid = albumid;
		this.albumname = albumname;
		this.albumprice = albumprice;
		this.rating = rating;
		this.songs = songs;
		this.artis = artis;
	}

	public Album(int albumid, String albumname, double albumprice, double rating, Set<Song> songs) {
		this.albumid = albumid;
		this.albumname = albumname;
		this.albumprice = albumprice;
		this.rating = rating;
		this.songs = songs;
	}

	public Album(int albumid, String albumname, double albumprice, double rating) {
		this.albumid = albumid;
		this.albumname = albumname;
		this.albumprice = albumprice;
		this.rating = rating;
	}

	public int getAlbumid() {
		return albumid;
	}

	public void setAlbumid(int albumid) {
		this.albumid = albumid;
	}

	public String getAlbumname() {
		return albumname;
	}

	public void setAlbumname(String albumname) {
		this.albumname = albumname;
	}

	public double getAlbumprice() {
		return albumprice;
	}

	public void setAlbumprice(double albumprice) {
		this.albumprice = albumprice;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Set<Song> getSongs() {
		return songs;
	}

	public void setSongs(Set<Song> songs) {
		this.songs = songs;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + albumid;
		result = prime * result + ((albumname == null) ? 0 : albumname.hashCode());
		long temp;
		temp = Double.doubleToLongBits(albumprice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		temp = Double.doubleToLongBits(rating);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((songs == null) ? 0 : songs.hashCode());
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
		Album other = (Album) obj;
		if (albumid != other.albumid)
			return false;
		if (albumname == null) {
			if (other.albumname != null)
				return false;
		} else if (!albumname.equals(other.albumname))
			return false;
		if (Double.doubleToLongBits(albumprice) != Double.doubleToLongBits(other.albumprice))
			return false;
		if (Double.doubleToLongBits(rating) != Double.doubleToLongBits(other.rating))
			return false;
		if (songs == null) {
			if (other.songs != null)
				return false;
		} else if (!songs.equals(other.songs))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Album [albumid=" + albumid + ", albumname=" + albumname + ", albumprice=" + albumprice + ", rating="
				+ rating + ", songs=" + songs + ", artis=" + artis + "]";
	}

	@Override
	public int compareTo(Album alb) {
		if (albumprice > alb.albumprice) {
			return -1;
		} else if (albumprice < alb.albumprice) {
			return 1;
		} else {
			return 0;
		}

	}

	@Override
	public int compare(Album a1, Album a2) {
		int x = a1.getAlbumname().compareTo(a2.getAlbumname());
		return x;
	}

}
