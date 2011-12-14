package com.riffbit.lucien;

import net.minidev.json.JSONObject;

import com.riffbit.lucien.bucket.Bucket;
import com.riffbit.lucien.bucket.StoreBucket;
import com.riffbit.lucien.store.Store;
import com.riffbit.lucien.store.LevelDBStore;

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
    }
}
