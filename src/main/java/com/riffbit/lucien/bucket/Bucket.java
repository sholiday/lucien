package com.riffbit.lucien.bucket;

import net.minidev.json.JSONObject;

public interface Bucket {
	public JSONObject get(String key);
	public void put(String key, JSONObject value);
	public void remove(String key);
}
