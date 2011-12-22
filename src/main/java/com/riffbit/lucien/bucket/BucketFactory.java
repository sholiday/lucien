package com.riffbit.lucien.bucket;

import java.util.HashMap;
import java.util.Map;

public class BucketFactory {

	private static Map<String, Bucket> buckets = new HashMap<String, Bucket>();
	private static String path;

	//public BucketFactory {
	public static Bucket getBucket(String bucket_name) {
		Bucket bucket = buckets.get(bucket_name);
		if (bucket == null) {
			bucket = new LevelDBBucket(bucket_name, path);
			buckets.put(bucket_name, bucket);
		}
		return bucket;
	}

	public static void setPath(String string) {
		path = string;
	}
	
	
}
