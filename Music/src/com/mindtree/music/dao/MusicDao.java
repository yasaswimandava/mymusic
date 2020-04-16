package com.mindtree.music.dao;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mindtree.music.entity.Album;
import com.mindtree.music.entity.Artist;
import com.mindtree.music.entity.Song;
import com.mindtree.music.exception.daoexception.MusicApplicationDaoException;

public interface MusicDao {
	Set<Artist> insertArtist(Artist artist) throws MusicApplicationDaoException;

	Map<Song, Artist> insertSong(Song song,int resultid) throws MusicApplicationDaoException;

	int getArtistId(String artname) throws MusicApplicationDaoException;

	boolean validateArtist(String artname) throws MusicApplicationDaoException;

	boolean validateSong(String songnam) throws MusicApplicationDaoException;

	int getSongId(String songnam) throws MusicApplicationDaoException;

	int insertAlbum(Album album, int resultid, int resultid2) throws MusicApplicationDaoException;

	Set<Song> getSongs(int resultid) throws MusicApplicationDaoException;

	List<Integer> getSongId2(int artid) throws MusicApplicationDaoException;

	Set<Album> getAlbum() throws MusicApplicationDaoException;

	int checkSong(int resultid) throws MusicApplicationDaoException;

	Album getAlbumHighestPrice(Integer i) throws MusicApplicationDaoException;

	
}
