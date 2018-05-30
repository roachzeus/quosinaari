package fi.roachzeus.quosinaari.utils;

import redis.clients.jedis.Jedis;

public class QuosiUtils {
	
	private QuosiUtils(){
		// this be static
	}
	
	public static boolean isAllowedHost(String id){
		
		boolean allowed = false;
		Jedis j = new Jedis("localhost");
		j.clientSetname("utilClass");
		String val = j.get("host");
		// if it's null then no host was found
		if (val.equalsIgnoreCase(id) || val == null){
			allowed = true;
		}
		j.close();
		return allowed;
	}

}
