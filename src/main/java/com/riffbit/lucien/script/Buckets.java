package com.riffbit.lucien.script;

import com.riffbit.lucien.bucket.Bucket;
import com.riffbit.lucien.bucket.BucketFactory;

public class Buckets {
	public Bucket getBucket(String bucket_name) {
		return BucketFactory.getBucket(bucket_name);
	}
}
