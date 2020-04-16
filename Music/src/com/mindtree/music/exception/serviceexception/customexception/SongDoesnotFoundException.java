package com.mindtree.music.exception.serviceexception.customexception;

import com.mindtree.music.exception.serviceexception.MusicApplicationServiceException;

public class SongDoesnotFoundException extends MusicApplicationServiceException {

	public SongDoesnotFoundException() {
		// TODO Auto-generated constructor stub
	}

	public SongDoesnotFoundException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
		// TODO Auto-generated constructor stub
	}

	public SongDoesnotFoundException(String arg0, Throwable arg1) {
		super(arg0, arg1);
		// TODO Auto-generated constructor stub
	}

	public SongDoesnotFoundException(String arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public SongDoesnotFoundException(Throwable arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

}
