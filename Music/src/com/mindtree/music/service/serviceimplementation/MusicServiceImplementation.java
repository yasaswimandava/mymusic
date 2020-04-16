package com.mindtree.music.service.serviceimplementation;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mindtree.music.dao.MusicDao;
import com.mindtree.music.dao.daoimplementation.MusicDaoImplementation;
import com.mindtree.music.entity.Album;
import com.mindtree.music.entity.Artist;
import com.mindtree.music.entity.Song;
import com.mindtree.music.exception.daoexception.MusicApplicationDaoException;
import com.mindtree.music.exception.serviceexception.MusicApplicationServiceException;
import com.mindtree.music.exception.serviceexception.customexception.AlbumIsEmptyException;
import com.mindtree.music.exception.serviceexception.customexception.ArtistNotFoundException;
import com.mindtree.music.exception.serviceexception.customexception.SongAlreadyExistInAlbum;
import com.mindtree.music.exception.serviceexception.customexception.SongDoesnotFoundException;
import com.mindtree.music.service.MusicService;

public class MusicServiceImplementation implements MusicService {
	MusicDao database = new MusicDaoImplementation();

	@Override
	public Set<Artist> addArtist(String artistname, String gender, int age, int noOfsongs)
			throws MusicApplicationServiceException {

		Set<Artist> temp = new TreeSet<Artist>();

		try {
			Artist artist = new Artist(artistname, gender, age, noOfsongs);
			temp = database.insertArtist(artist);
			XSSFWorkbook workbook1 = new XSSFWorkbook();
			XSSFSheet sheet = workbook1.createSheet("artists details");
			Row row;
			row = sheet.createRow(0);
			Cell cell01 = row.createCell(0);
			cell01.setCellValue("ARTIST_ID");
			Cell cell02 = row.createCell(1);
			cell02.setCellValue("Artist_name");
			Cell cell03 = row.createCell(2);
			cell03.setCellValue("gender");
			Cell cell04 = row.createCell(3);
			cell04.setCellValue("age");
			Cell cell05 = row.createCell(4);
			cell05.setCellValue("No Of Songs");
			int rowcoloum = 1;
			for (Artist artist1 : temp) {
				row = sheet.createRow(rowcoloum++);
				Cell cell1 = row.createCell(0);
				cell1.setCellValue(artist1.getArtistid());
				Cell cell2 = row.createCell(1);
				cell2.setCellValue(artist1.getArtistname());
				Cell cell3 = row.createCell(2);
				cell3.setCellValue(artist1.getGender());
				Cell cell4 = row.createCell(3);
				cell4.setCellValue(artist1.getAge());
				Cell cell5 = row.createCell(4);
				cell5.setCellValue(artist1.getNo_ofsongs());
			}
			FileOutputStream fileOutputStream;

			fileOutputStream = new FileOutputStream(new File("C:\\Users\\M1057687\\Documents\\files\\artists.xlsx"));
			workbook1.write(fileOutputStream);
			fileOutputStream.close();
			workbook1.close();
		} catch (MusicApplicationDaoException | IOException e) {
			throw new MusicApplicationServiceException(e);
		}
		return temp;
	}

	@Override
	public Map<Song, Artist> addSong(String artname, String songname, int likes, String language)
			throws MusicApplicationServiceException {
		Map<Song, Artist> result = null;
		boolean flag = false;
		try {
			flag = database.validateArtist(artname);
			if (flag == true) {
				int resultid = database.getArtistId(artname);
				Song song = new Song(songname, likes, language);
				result = database.insertSong(song, resultid);
				// writing in to a excel sheet
				XSSFWorkbook workbook = new XSSFWorkbook();
				XSSFSheet sheet = workbook.createSheet("songs and artists details");

				Row row;
				row = sheet.createRow(0);
				Cell cell01 = row.createCell(0);
				cell01.setCellValue("SONG_ID");
				Cell cell02 = row.createCell(1);
				cell02.setCellValue("SONG_NAME");
				Cell cell03 = row.createCell(2);
				cell03.setCellValue("SONG_LIKES");
				Cell cell04 = row.createCell(3);
				cell04.setCellValue("SONG_LANGUAGE");
				Cell cell05 = row.createCell(4);
				cell05.setCellValue("ARTIST_ID");
				Cell cell06 = row.createCell(5);
				cell06.setCellValue("Artist_name");
				Cell cell07 = row.createCell(6);
				cell07.setCellValue("gender");
				Cell cell08 = row.createCell(7);
				cell08.setCellValue("age");
				int rownum = 1;
				for (Map.Entry<Song, Artist> songdata : result.entrySet()) {
					row = sheet.createRow(rownum++);
					Cell cell1 = row.createCell(0);
					cell1.setCellValue(songdata.getKey().getSongid());
					Cell cell2 = row.createCell(1);
					cell2.setCellValue(songdata.getKey().getSongname());
					Cell cell3 = row.createCell(2);
					cell3.setCellValue(songdata.getKey().getLikes());
					Cell cell4 = row.createCell(3);
					cell4.setCellValue(songdata.getKey().getLanguage());
					Cell cell5 = row.createCell(4);
					cell5.setCellValue(songdata.getValue().getArtistid());
					Cell cell6 = row.createCell(5);
					cell6.setCellValue(songdata.getValue().getArtistname());
					Cell cell7 = row.createCell(6);
					cell7.setCellValue(songdata.getValue().getGender());
					Cell cell8 = row.createCell(7);
					cell8.setCellValue(songdata.getValue().getAge());
				}
				FileOutputStream fileOutputStream = new FileOutputStream(
						new File("C:\\Users\\M1057687\\Documents\\files\\songsandartists.xlsx"));

				workbook.write(fileOutputStream);
				fileOutputStream.close();
				workbook.close();

			} else {
				throw new ArtistNotFoundException("artist doesnt found to add");
			}

		} catch (MusicApplicationDaoException | ArtistNotFoundException | IOException e) {
			throw new MusicApplicationServiceException(e);
		}
		return result;
	}

	@Override
	public int addAlbum(String songnam, String artistname, String albumname, double albumprice, double albumrating)
			throws MusicApplicationServiceException {
		int result = 0;
		boolean flag = false;
		try {
			flag = database.validateSong(songnam);
			System.out.println(flag);
			if (flag == true) {
				int check = 1;
				boolean flag1 = false;
				flag1 = database.validateArtist(artistname);
				System.out.println(flag1);
				if (flag1 == true) {
					int resultid = database.getSongId(songnam);
					System.out.println(resultid);
					int result1id = database.getArtistId(artistname);
					System.out.println(result1id);

					Album album = new Album(albumname, albumprice, albumrating);
					result = database.insertAlbum(album, resultid, result1id);
					System.out.println(result);

				}
			} else {
				throw new SongDoesnotFoundException("song not found");
			}
		} catch (MusicApplicationDaoException | SongDoesnotFoundException e) {
			throw new MusicApplicationServiceException(e);
		}
		return result;
	}

	@Override
	public Map<Song, String> getAllSongs(String artistname) throws MusicApplicationServiceException {
		int resultid;
		boolean flag = false;
		try {
			flag = database.validateArtist(artistname);
			if (flag = true) {
				resultid = database.getArtistId(artistname);
				Map<Song, String> mapresult = new HashMap<Song, String>();
				Set<Song> results = database.getSongs(resultid);
				results.forEach(songs -> {
					mapresult.put(songs, artistname);
				});
				if (mapresult.isEmpty()) {
					throw new ArtistNotFoundException("artist not found");
				}
				return mapresult;
			} else {
				throw new ArtistNotFoundException("artist not found");
			}
		} catch (MusicApplicationDaoException | ArtistNotFoundException e) {
			throw new MusicApplicationServiceException(e.getMessage());
		}

	}

	@Override
	public Set<Album> getExpensiveAlbum(String artistname) throws MusicApplicationServiceException {
		int artid;
		List<Integer> songid;
		Album album = null;
		Set<Album> album1 = new TreeSet<Album>();
		boolean flag = false;
		try {
			flag = database.validateArtist(artistname);
			if (flag = true) {
				artid = database.getArtistId(artistname);
				songid = database.getSongId2(artid);

				for (Integer i : songid) {
					album = database.getAlbumHighestPrice(i);
					album1.add(album);
				}
			} else {
				throw new ArtistNotFoundException("artist not found");
			}
		} catch (MusicApplicationDaoException | ArtistNotFoundException e) {
			throw new MusicApplicationServiceException(e);

		}
		return album1;
	}

	@Override
	public Set<Album> sortAccordingToName() throws MusicApplicationServiceException {
		Set<Album> alb;
		try {
			alb = new TreeSet<Album>();
			alb = database.getAlbum();
			if (alb.isEmpty()) {
				throw new AlbumIsEmptyException("album is empty");
			}
			List<Album> templist = new ArrayList<Album>(alb);
			Collections.sort(templist);
			alb = new LinkedHashSet<>(templist);
		} catch (MusicApplicationDaoException | AlbumIsEmptyException e) {
			throw new MusicApplicationServiceException(e);
		}

		return alb;
	}

	@Override
	public Set<Album> sortAccordingToPrice() throws MusicApplicationServiceException {
		Set<Album> tempresult = new TreeSet<Album>();
		try {
			tempresult = database.getAlbum();
			if (tempresult.isEmpty()) {
				throw new AlbumIsEmptyException("album is empty");
			}
		} catch (MusicApplicationDaoException | AlbumIsEmptyException e) {
			throw new MusicApplicationServiceException(e);
		}
		return tempresult;
	}

}
