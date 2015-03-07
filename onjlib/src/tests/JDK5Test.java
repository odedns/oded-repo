package tests;

import java.util.*;

public class JDK5Test {
	/*
	 * enum example.
	 */
	public enum Season { WINTER, SPRING, SUMMER, FALL }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*
		 * demostrate generics.
		 */
		List<String> list = new ArrayList<String>();
		for(int i=0; i < 5; ++i) {
			list.add("item-" + i);
		}
		/*
		 * enhanced for loop.
		 */
		for(String s:list) {
			System.out.println(s);
		}
		
		/*
		 * auto boxing
		 */
		List<Integer> il = new ArrayList<Integer>();
		for(int i=100; i < 110; ++i) {
			il.add(i);
		}
		for(Integer n:il) {
			System.out.println(n);
		}
		/*
		 * enum example.
		 */
		for(Season s: Season.values()) {
			System.out.println(s);
		}
		Season season = Season.WINTER;
		int n = season.ordinal();
		
		if(season == Season.WINTER ) {
			System.out.println("season is :" + season + "\tordinal= " + n);
		}
		n = 3;
		
	}

}
