package com.riffbit.lucien;

import net.minidev.json.JSONObject;

import com.riffbit.lucien.bucket.Bucket;
import com.riffbit.lucien.bucket.StoreBucket;
import com.riffbit.lucien.store.Store;
import com.riffbit.lucien.store.LevelDBStore;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class App 
{
	public static void main( String[] args )
	{
		Store store = new LevelDBStore("soemthing","/tmp");
		Bucket bk = new StoreBucket(store);

		JSONObject j = new JSONObject();
		j.put("k","v");
		bk.put("a", j);
		System.out.println(bk.get("a"));

		try {
			ScriptEngine engine = new ScriptEngineManager().getEngineByName("python");
			engine.eval("print \"hello world\"");
		} catch (ScriptException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
