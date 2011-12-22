package com.riffbit.lucien;

import net.minidev.json.JSONObject;

import com.riffbit.lucien.bucket.Bucket;
import com.riffbit.lucien.bucket.BucketFactory;
import com.riffbit.lucien.script.ScriptRunner;

public class App 
{
	public static void main( String[] args )
	{
		BucketFactory.setPath("/tmp");
		Bucket bk = BucketFactory.getBucket("bk");
		Bucket settings = BucketFactory.getBucket("_settings");
		Bucket scripts = BucketFactory.getBucket("scripts");
		
		JSONObject j = new JSONObject();
		j.put("engine","python");
		j.put("script", "result = {\"hey\":\"there\"}");
		scripts.put("tester", j);
		
		ScriptRunner sr = new ScriptRunner();
		
		System.out.println(scripts.get("tester"));
		
		System.out.println(sr.runScript("tester", null));

	}
}
