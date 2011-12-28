package com.riffbit.lucien;

import java.util.List;

import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.server.TServer;
import org.apache.thrift.server.TSimpleServer;
import org.apache.thrift.transport.TServerSocket;
import org.apache.thrift.transport.TServerTransport;

import com.riffbit.lucien.bucket.Bucket;
import com.riffbit.lucien.bucket.BucketFactory;
import com.riffbit.lucien.script.ScriptRunner;
import com.riffbit.lucien.thrift.Lucien;


public class ThriftApp {


	public static class LucienHandler implements Lucien.Iface {
		ScriptRunner scriptRunner;
		JSONParser parser;

		LucienHandler() {
			BucketFactory.setPath("/tmp");
			Bucket settings = BucketFactory.getBucket("_settings");
			Bucket scripts = BucketFactory.getBucket("_scripts");

			scriptRunner = new ScriptRunner();
			parser = new JSONParser();
		}

		public void ping() {
			System.out.println("Pinged");
		}

		@Override
		public String get(String bucket, String key) throws TException {
			return BucketFactory.getBucket(bucket).get(key).toJSONString();
		}

		@Override
		public void put(String bucket, String key, String value)
				throws TException {
			try {
				JSONObject json = (JSONObject) parser.parse(value);
				BucketFactory.getBucket(bucket).put(key, json);
			} catch (ParseException e) {
				e.printStackTrace();
				throw new TException(e);
			}
		}

		@Override
		public String run(String script, List arguments) throws TException {
			return scriptRunner.runScript(script, (String[])arguments.toArray()).toJSONString();
		}
	}

	public static void main(String [] args) {

		try {

			LucienHandler handler = new LucienHandler();

			Lucien.Processor processor = new Lucien.Processor(handler);

			TServerTransport serverTransport = new TServerSocket(9090);
			TServer.Args serverArgs = new TServer.Args(serverTransport);
			serverArgs.processor(processor);
			TServer server = new TSimpleServer(serverArgs);
			System.out.println("Lucien starting up...");
			server.serve();

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Finished.");

	}

}
