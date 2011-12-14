package com.riffbit.lucien.store;

public interface Store {
	public String get(String key);
	public void put(String key, String value);
	public void remove(String key);
	public void close();
}
