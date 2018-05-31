package fi.roachzeus.quosinaari.Player;

import redis.clients.jedis.Jedis;

public class PlayerModel {

	private static final String JEDIS_HOST = "localhost";
	private String playerId;
	private Jedis pJedis;
	
	
	
	public PlayerModel(String pid){
		
		this.playerId = pid;
		pJedis = new Jedis(JEDIS_HOST);
		pJedis.clientSetname(playerId);
		
		
		
		
		
	}
	public void enroll(String name){
		
		pJedis.hset("Player#" + playerId, "name", name);
		pJedis.hset("Player#" + playerId, "id", playerId);
		System.out.println("get all for ID: " + pJedis.hgetAll("Player#" + playerId));
		//pJedis.close();
	}
	public void submit(String chars){
		
	}
	
	
}
