package test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class SortMap {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		HashMap<String,String> map = new HashMap<String,String>();
		map.put("dp1", "500");
		map.put("dp3", "1500");
		map.put("dp2", "300");
		
		System.out.println("map before sort: " + map.toString());
		
		TreeMap<Long,String> tMap = sortMap(map);
		System.out.println("tmap after sort: " + tMap.toString());


	}
	
	
	
	public static TreeMap<Long,String> sortMap(HashMap<String,String> map){
		TreeMap<Long,String> tmap = new TreeMap<Long,String>();
		for(Map.Entry<String, String> e : map.entrySet()) {
			Long l = new Long(e.getValue());
			tmap.put(l, e.getKey());
		}
		return(tmap);
	}

	
	
	
}
