package com.mindtree.music.dao.daoimplementation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.mindtree.music.dao.MusicDao;
import com.mindtree.music.entity.Album;
import com.mindtree.music.entity.Artist;
import com.mindtree.music.entity.Song;
import com.mindtree.music.exception.daoexception.MusicApplicationDaoException;
import com.mindtree.music.util.MusicJdbcConnectionUtility;

public class MusicDaoImplementation implements MusicDao {
	MusicJdbcConnectionUtility connect = new MusicJdbcConnectionUtility();

	@Override
	public Set<Artist> insertArtist(Artist artist) throws MusicApplicationDaoException {
		Connection con = MusicJdbcConnectionUtility.getConnection();
		ResultSet rst=null;
		Set<Artist> temp1=new HashSet<Artist>();
		try {
			PreparedStatement call = con.prepareCall("{call add_artist(?,?,?,?)}");
			call.setString(1, artist.getArtistname());
			call.setString(2, artist.getGender());
			call.setInt(3, artist.getAge());
			call.setInt(4, artist.getNo_ofsongs());
			rst=call.executeQuery();
			while(rst.next())
			{
				Artist arti=new Artist();
				arti.setArtistid(rst.getInt(1));
				arti.setArtistname(rst.getString(2));
				arti.setGender(rst.getString(3));
				arti.setAge(rst.getInt(4));
				arti.setNo_ofsongs(5);
				temp1.add(arti);
				
			}
		} catch (SQLException e) {
			throw new MusicApplicationDaoException(e);
		} finally {
			connect.closeResource(con);
		}
		return temp1;
	}

	@Override
	public Map<Song, Artist> insertSong(Song song, int resultid) throws MusicApplicationDaoException {
		Connection con = MusicJdbcConnectionUtility.getConnection();
		CallableStatement call = null;
		ResultSet rst=null;
		Map<Song,Artist> temp=new HashMap<Song, Artist>();
		try {
			call = con.prepareCall("{call add_song(?,?,?,?)}");
			call.setString(1, song.getSongname());
			call.setInt(2, song.getLikes());
			call.setString(3, song.getLanguage());
			call.setInt(4, resultid);
			rst=call.executeQuery();
			while(rst.next())
			{
				Song song1=new Song();
				song1.setSongid(rst.getInt(1));
				song1.setSongname(rst.getString(2));
				song1.setLikes(rst.getInt(3));
				song1.setLanguage(rst.getString(4));
				Artist art=new Artist();
				art.setArtistid(rst.getInt(5));
				art.setArtistname(rst.getString(6));
				art.setGender(rst.getString(7));
				art.setAge(rst.getInt(8));
				temp.put(song1, art);
			}
		} catch (SQLException e) {
			throw new MusicApplicationDaoException(e);
		} finally {
			connect.closeResource(con);
			connect.closeResource(call);
		}
		return temp;
	}

	@Override
	public int getArtistId(String artname) throws MusicApplicationDaoException {
		Connection con = MusicJdbcConnectionUtility.getConnection();
		CallableStatement call = null;
		ResultSet rst = null;
		int id = 0;
		try {
			call = con.prepareCall("{call get_artistid(?)}");
			call.setString(1, artname);
			rst = call.executeQuery();
			while (rst.next()) {
				id = rst.getInt(1);
			}
		} catch (SQLException e) {
			throw new MusicApplicationDaoException(e);
		} finally {
			connect.closeResource(con);
			connect.closeResource(rst);
			connect.closeResource(call);
		}
		return id;
	}

	@Override
	public boolean validateArtist(String artname) throws MusicApplicationDaoException {
		Connection con = MusicJdbcConnectionUtility.getConnection();
		CallableStatement call = null;
		ResultSet rst = null;
		try {
			call = con.prepareCall("{call validate_Artist(?)}");
			call.setString(1, artname);
			rst = call.executeQuery();
			while (rst.next()) {
				if (artname.equalsIgnoreCase(rst.getString(1)))
					;
				return true;
			}
		} catch (SQLException e) {
			throw new MusicApplicationDaoException(e);
		} finally {
			connect.closeResource(con);
			connect.closeResource(rst);
			connect.closeResource(call);
		}

		return false;
	}

	@Override
	public boolean validateSong(String songnam) throws MusicApplicationDaoException {
		Connection con = MusicJdbcConnectionUtility.getConnection();
		CallableStatement call = null;
		ResultSet rst = null;
		try {
			call = con.prepareCall("{call validate_song(?)}");
			call.setString(1, songnam);
			rst = call.executeQuery();
			while (rst.next()) {
				if (songnam.equalsIgnoreCase(rst.getString(1)));
				return true;
			}
		} catch (SQLException e) {
			throw new MusicApplicationDaoException(e);
		} finally {
			connect.closeResource(con);
			connect.closeResource(rst);
			connect.closeResource(call);
		}
		return false;
	}

	@Override
	public int getSongId(String songnam) throws MusicApplicationDaoException {
		Connection con = MusicJdbcConnectionUtility.getConnection();
		CallableStatement call = null;
		ResultSet rst = null;
		int id = 0;
		try {
			call = con.prepareCall("{call getsong_id(?)}");
			call.setString(1, songnam);
			rst = call.executeQuery();
			while (rst.next()) {
				id = rst.getInt(1);
			}
		} catch (SQLException e) {
			throw new MusicApplicationDaoException(e);
		} finally {
			connect.closeResource(con);
			connect.closeResource(rst);
			connect.closeResource(call);
		}
		return id;

	}

	@Override
	public int insertAlbum(Album album, int resultid,int result1id) throws MusicApplicationDaoException {

		Connection con = MusicJdbcConnectionUtility.getConnection();
		CallableStatement call = null;
		try {
		
			call = con.prepareCall("{call add_album(?,?,?,?,?)}");
			call.setString(1, album.getAlbumname());
			call.setDouble(2, album.getAlbumprice());
			call.setDouble(3, album.getRating());
			call.setInt(4, resultid);
			call.setInt(5, result1id);
			call.executeUpdate();
			System.out.println("hello");
		} catch (SQLException e) {
			throw new MusicApplicationDaoException(e);
		} finally {
			connect.closeResource(con);
			connect.closeResource(call);
		}

		return 1;
	}

	@Override
	public Set<Song> getSongs(int resultid) throws MusicApplicationDaoException {
		Connection con = MusicJdbcConnectionUtility.getConnection();
		CallableStatement call1 = null;
		ResultSet rst = null;
		Set<Song> result = new HashSet<Song>();
		try {

			call1 = con.prepareCall("{call get_allsongs(?)}");
			call1.setInt(1, resultid);
			rst = call1.executeQuery();
			while (rst.next()) {
				Song son = new Song(rst.getInt(1), rst.getString(2), rst.getInt(3), rst.getString(4));
				result.add(son);
			}
		} catch (SQLException e) {
			throw new MusicApplicationDaoException(e.getMessage());
		}
		finally {
			connect.closeResource(con);
			connect.closeResource(rst);
			connect.closeResource(call1);
		}
		return result;
	}

	@Override
	public List<Integer> getSongId2(int artid) throws MusicApplicationDaoException {
		Connection con = MusicJdbcConnectionUtility.getConnection();
		CallableStatement call1 = null;
		ResultSet rst = null;
		List<Integer> id = new ArrayList<Integer>();
		try {
			call1 = con.prepareCall("{call getsong_id2(?)}");
			call1.setInt(1, artid);
			rst = call1.executeQuery();
			while (rst.next()) {
				id.add(rst.getInt(1));
			}
		} catch (SQLException e) {
			throw new MusicApplicationDaoException(e);
		}
		finally {
			connect.closeResource(con);
			connect.closeResource(rst);
			connect.closeResource(call1);
		}
		return id;

	}

	@Override
	public Set<Album> getAlbum() throws MusicApplicationDaoException {
		Connection con = MusicJdbcConnectionUtility.getConnection();
		CallableStatement call1 = null;
		ResultSet rst = null;
		Set<Album> albums = new TreeSet<Album>();
		Set<Song> songs1=new HashSet<Song>();
		Set<Artist> artist1=new HashSet<Artist>();
		try {
			call1 = con.prepareCall("{call get_album()}");
			rst = call1.executeQuery();
			while (rst.next()) {
				Artist art=new Artist();
				Song son=new Song();
				Album alb=new Album();
				alb.setAlbumid(rst.getInt(1));
				alb.setAlbumname(rst.getString(2));
				alb.setAlbumprice(rst.getDouble(3));
				alb.setRating(rst.getDouble(4));
				son.setSongid(rst.getInt(5));
				son.setSongname(rst.getString(6));
				son.setLikes(rst.getInt(7));
				son.setLanguage(rst.getString(8));
				songs1.add(son);
				alb.setSongs(songs1);
				art.setArtistid(rst.getInt(9));
				art.setArtistname(rst.getString(10));
				art.setGender(rst.getString(11));
				art.setAge(rst.getInt(12));
				art.setNo_ofsongs(rst.getInt(13));
				artist1.add(art);
				alb.setArtis(artist1);
				albums.add(alb);
				
//				Album album = new Album(rst.getInt(1), rst.getString(2), rst.getDouble(3), rst.getDouble(4));
//				albums.add(album);
			}
		} catch (SQLException e) {
			throw new MusicApplicationDaoException(e);
		}
		finally {
			connect.closeResource(con);
			connect.closeResource(rst);
			connect.closeResource(call1);
		}
		return albums;
	}

	@Override
	public int checkSong(int resultid) throws MusicApplicationDaoException {
		Connection con = MusicJdbcConnectionUtility.getConnection();
		CallableStatement call1 = null;
		ResultSet rst = null;
		int temp = 0;
		try {
			call1 = con.prepareCall("{call check_album}");
			rst = call1.executeQuery();
			while (rst.next()) {
				temp = rst.getInt(1);
			}
		} catch (SQLException e) {
			throw new MusicApplicationDaoException(e);
		}finally {
			connect.closeResource(con);
			connect.closeResource(rst);
			connect.closeResource(call1);
		}

		return temp;
	}

	@Override
	public Album getAlbumHighestPrice(Integer id) throws MusicApplicationDaoException {
		Connection con = MusicJdbcConnectionUtility.getConnection();
		CallableStatement call1 = null;
		ResultSet rst = null;
		Album result = new Album();
		try {
			call1 = con.prepareCall("{call get_highestalbum(?)}");
			call1.setInt(1, id);
			rst = call1.executeQuery();
			while (rst.next()) {
				result = new Album(rst.getInt(6), rst.getString(3), rst.getDouble(4), rst.getDouble(5));
			}
		} catch (SQLException e) {
			throw new MusicApplicationDaoException(e);
		}
		finally {
			connect.closeResource(con);
			connect.closeResource(rst);
			connect.closeResource(call1);
		}
		return result;
	}

}
