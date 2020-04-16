package com.mindtree.music.util;

import java.util.Scanner;

public class MusicInputUtility {
	static Scanner sc = new Scanner(System.in);

	public static int acceptInt() {
		return sc.nextInt();
	}

	public static String acceptString() {
		return sc.next();
	}

	public static long acceptLong() {
		return sc.nextLong();
	}
	public static double acceptDouble()
	{
		return sc.nextDouble();
	}
}
