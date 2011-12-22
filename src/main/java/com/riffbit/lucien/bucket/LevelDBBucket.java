package com.riffbit.lucien.bucket;

import static org.fusesource.leveldbjni.JniDBFactory.asString;
import static org.fusesource.leveldbjni.JniDBFactory.bytes;
import static org.fusesource.leveldbjni.JniDBFactory.factory;

import java.io.File;
import java.io.IOException;

import net.minidev.json.JSONObject;
import net.minidev.json.JSONStyle;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;

public class LevelDBBucket implements Bucket {
	private DB db;

	JSONParser parser;
	@SuppressWarnings("deprecation")
	public LevelDBBucket(String bucket_name, String path) {
		parser = new JSONParser();
		
		Options options = new Options();
		options.createIfMissing(true);

		try {
			db = factory.open(new File(path + "/" + bucket_name), options);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void close() {
		db.close();
	}

	@Override
	public JSONObject get(String key) {
		try {
			String s = asString(db.get(bytes(key)));
			System.out.println(key);
			return (JSONObject) parser.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void put(String key, JSONObject value) {
		db.put(bytes(key), bytes(value.toJSONString(JSONStyle.MAX_COMPRESS)));
	}

	public void put(String key, String value) {
		db.put(bytes(key), bytes(value));
	}

	public void remove(String key) {
		db.delete(bytes(key));
	}

}
