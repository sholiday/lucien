package com.riffbit.lucien.store;

import static org.fusesource.leveldbjni.JniDBFactory.factory;

import java.io.File;
import java.io.IOException;

import org.iq80.leveldb.DB;
import org.iq80.leveldb.Options;
import java.io.*;
import org.iq80.leveldb.*;
import static org.fusesource.leveldbjni.JniDBFactory.*;

public class LevelDBStore implements Store{
	DB db;
	public LevelDBStore(String bucket_name, String path) {
		Options options = new Options();
		options.createIfMissing(true);

		try {
			db = factory.open(new File(path + "/" + bucket_name), options);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String get(String key) {
		return asString(db.get(bytes(key)));
	}

	public void put(String key, String value) {
		db.put(bytes(key), bytes(value));
	}

	public void close() {
		db.close();
	}

	public void remove(String key) {
		db.delete(bytes(key));
	}

}
