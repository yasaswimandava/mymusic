package com.mindtree.music.client;

import com.mindtree.music.entity.Album;
import com.mindtree.music.entity.Artist;
import com.mindtree.music.entity.Song;
import com.mindtree.music.exception.serviceexception.MusicApplicationServiceException;
import com.mindtree.music.service.MusicService;
import com.mindtree.music.service.serviceimplementation.MusicServiceImplementation;
import com.mindtree.music.util.MusicInputUtility;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class MusicApp {
	public static void main(String[] args) {
		boolean flag = true;
		MusicService service = new MusicServiceImplementation();
		do {
			System.out.println("****WELCOME TO MUSIC SYSTEM****");
			System.out.println("PRESS 1: TO ADD ARTIST");
			System.out.println("PRESS 2: ADD SONG AND ASIGN TO THE ARTIST");
			System.out.println("PRESS 3: ADD ALBUM AND ASSIGN SONGS TO IT");
			System.out.println("PRESS 4: GET ALL SONGS NAME GIVEN BY ARTIST NAME");
			System.out.println("PRESS 5: GET MOST EXPENSIVE ALBUM NAME BY GIVEN ARTIST NAME");
			System.out.println("PRESS 6: SORT ALL THE ALBUMS IN THE DESCENDING ORDER OF THEIR NAMES");
			System.out.println("PRESS 7: SORT ALL THE ALBUMS IN ASCENDING ORDER ACCORDING TO PRICE");
			System.out.println("PRESS 8: EXIT");
			System.out.println("**********************************************************");
			System.out.println("ENTER OPTIONS TO PERFORM");
			String option = MusicInputUtility.acceptString();
			switch (option) {
			case "1": {
				System.out.println("enter artistname");
				String artistname = MusicInputUtility.acceptString();
				System.out.println("enter gender");
				String gender = MusicInputUtility.acceptString();
				System.out.println("enter artist age");
				int age = MusicInputUtility.acceptInt();
				System.out.println("enter no of songs");
				int noOfsongs=MusicInputUtility.acceptInt();
				try {
					Set<Artist> result = service.addArtist(artistname, gender, age,noOfsongs);
					 result.forEach(i ->System.out.println(i));
					 System.out.println(result.size());

					FileInputStream fileInputStream = new FileInputStream(
							new File("C:\\\\Users\\\\M1057687\\\\Documents\\\\files\\\\artists.xlsx"));
					XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
					XSSFSheet sheet = workbook.getSheet("artists details");

					Iterator<Row> rowIterator = sheet.iterator();

					while (rowIterator.hasNext()) {

						Row row = rowIterator.next();
						Iterator<Cell> cellIterator = row.iterator();

						while (cellIterator.hasNext()) {
							Cell cell = cellIterator.next();

							switch (cell.getCellType()) {
							case Cell.CELL_TYPE_NUMERIC:
								System.out.print(cell.getNumericCellValue() + "\t\t\t");

								break;

							case Cell.CELL_TYPE_STRING:
								System.out.print(cell.getStringCellValue() + "\t\t\t");
								break;
							}
						}
						System.out.println();
					}
					workbook.close();
				fileInputStream.close();
				} catch (MusicApplicationServiceException | IOException e) {
					System.out.println(e.getMessage());
				}
			}
				break;
			case "2": {
				System.out.println("enter artist name to add songs");
				String artname = MusicInputUtility.acceptString();
				System.out.println("enter song name");
				String songname = MusicInputUtility.acceptString();
				System.out.println("enter likes for the songs");
				int likes = MusicInputUtility.acceptInt();
				System.out.println("enter song language");
				String language = MusicInputUtility.acceptString();
				try {
					Map<Song, Artist> result = service.addSong(artname, songname, likes, language);
					result.entrySet().forEach(map -> System.out.println(map.getKey() + "\t" + map.getValue()));
					System.out.println("***************");
					Map<Song, Artist> temp = result.entrySet().stream().filter(e -> e.getKey().getLikes() > 65)
							.collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
					temp.entrySet().forEach(map2 -> System.out.println(map2.getKey() + "\t" + map2.getValue()));
				} catch (MusicApplicationServiceException e) {
					System.out.println(e);
				}
			}
				break;
			case "3": {
				System.out.println("enter album name");
				String albumname = MusicInputUtility.acceptString();
				System.out.println("enter album price");
				double albumprice = MusicInputUtility.acceptDouble();
				System.out.println("enter album rating");
				double albumrating = MusicInputUtility.acceptDouble();
				System.out.println("enter songname");
				String songnam = MusicInputUtility.acceptString();
				System.out.println("enter artistname");
				String artistname=MusicInputUtility.acceptString();
				try {
					int result = service.addAlbum(songnam, artistname,albumname, albumprice, albumrating);
					if (result == 1) {
						System.out.println("album added successfully");
					}
				} catch (MusicApplicationServiceException e) {
					System.out.println(e);
				}
			}
				break;
			case "4": {
				System.out.println("enter artist name to get songs");
				String artistname = MusicInputUtility.acceptString();
				try {
					Map<Song, String> result = service.getAllSongs(artistname);
					result.entrySet().forEach(map -> {
						System.out.println(map.getValue() + "\t" + map.getKey());
					});

				} catch (MusicApplicationServiceException e) {
					System.out.println(e.getMessage());
				}
			}
				break;
			case "5":
				System.out.println("enter artist name to get expensive album");
				String artistname = MusicInputUtility.acceptString();
				Set<Album> album;
				try {
					album = service.getExpensiveAlbum(artistname);
					for (Album album2 : album) {
						System.out.println("expensive album from this artist  : " + album2);
						System.out.println("*********************************************");
						break;
					}

				} catch (MusicApplicationServiceException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "6":
				try {
					Set<Album> ab = service.sortAccordingToName();
					ab.forEach(System.out::println);
					System.out.println("**************************************************");
					Set<Album> temp = ab.stream().filter(e -> e.getAlbumprice() > 50).collect(Collectors.toSet());
					System.out.println(temp);
				} catch (MusicApplicationServiceException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "7":
				try {
					Set<Album> tempalbum = service.sortAccordingToPrice();

					tempalbum.forEach(System.out::println);

				} catch (MusicApplicationServiceException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "8":
				flag = false;
				System.out.println("****THANK YOU VISIT AGAIN*****");
			default:
				System.out.println("ENTER VALID INPUT TO PERFORM OPTIONS");

			}
		} while (flag);
	}
}
