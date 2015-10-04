package com.silletti.coffiURL.persistence;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutionException;

import com.basho.riak.client.api.RiakClient;
import com.basho.riak.client.api.commands.kv.StoreValue;
import com.basho.riak.client.core.query.Location;
import com.basho.riak.client.core.query.Namespace;

public class URLShortenerRiakDAO implements URLShortenerDAO {

	private RiakClient client;
	
	public URLShortenerRiakDAO(){
		try {
			client = RiakClient.newClient(8087, "127.0.0.1");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public Boolean createShortURL(String shortURL, String longURL) {
		if (shortURL.isEmpty() || longURL.isEmpty()) {
			return false;
		} else {
			Location location = new Location(new Namespace("ShortURLs"), shortURL);
			StoreValue sv = new StoreValue.Builder(longURL).withLocation(location).build();
			try {
				
				StoreValue.Response svResponse = this.client.execute(sv);
				this.client.shutdown();
				return true;
				
			} catch (ExecutionException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	public String getLongURL(String shortURL) {
		// TODO Auto-generated method stub
		return null;
	}

}
