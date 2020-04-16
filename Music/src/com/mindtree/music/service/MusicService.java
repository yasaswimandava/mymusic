package com.mindtree.music.service;

import java.util.Map;
import java.util.Set;

import com.mindtree.music.entity.Album;
import com.mindtree.music.entity.Artist;
import com.mindtree.music.entity.Song;
import com.mindtree.music.exception.serviceexception.MusicApplicationServiceException;

public interface MusicService {

	Set<Artist> addArtist(String artistname, String gender, int age, int noOfsongs) throws MusicApplicationServiceException;

	Map<Song, Artist> addSong(String artname, String songname, int likes, String language) throws MusicApplicationServiceException;

	int addAlbum(String songnam, String albumname, String albumname2, double albumprice, double albumrating) throws MusicApplicationServiceException;

	Map<Song, String> getAllSongs(String artistname) throws MusicApplicationServiceException;

	Set<Album> getExpensiveAlbum(String artistname) throws MusicApplicationServiceException;

	Set<Album> sortAccordingToName() throws MusicApplicationServiceException;

	Set<Album> sortAccordingToPrice() throws MusicApplicationServiceException;

}
