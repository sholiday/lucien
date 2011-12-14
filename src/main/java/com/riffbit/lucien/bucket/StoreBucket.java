package com.riffbit.lucien.bucket;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONStyle;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import com.riffbit.lucien.store.Store;

public class StoreBucket implements Bucket {
	Store store;
	JSONParser parser;
	public StoreBucket(Store store) {
		this.store = store;
		parser = new JSONParser();
	}
	@Override
	public JSONObject get(String key) {
		try {
			return (JSONObject) parser.parse(store.get(key));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	@Override
	public void put(String key, JSONObject value) {
		store.put(key, value.toJSONString(JSONStyle.MAX_COMPRESS));
	}
	@Override
	public void remove(String key) {
		// TODO Auto-generated method stub
		store.remove(key);
	}
	
	
}
