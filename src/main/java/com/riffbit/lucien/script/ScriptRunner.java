package com.riffbit.lucien.script;

import java.util.HashMap;
import java.util.Map;

import com.riffbit.lucien.bucket.Bucket;
import com.riffbit.lucien.bucket.BucketFactory;

import net.minidev.json.JSONObject;

public class ScriptRunner {
	Map<String,ScriptingEngine> engines;
	Bucket scripts;
	public ScriptRunner() {
		engines = new HashMap<String, ScriptingEngine>();
		engines.put("python", new JythonEngine());
		
		scripts = BucketFactory.getBucket("_scripts");
	}
	public JSONObject runScript(String name, String args[]) {
		JSONObject j_script = scripts.get(name);
		
		if (j_script == null) {
			return new JSONObject() {{
				put("succes", false);
				put("message", "No script by that name");
			}};
		}
		System.out.println(j_script.get("engine"));
		if (j_script.get("engine").toString().equals("python")) {
			return engines.get("python").runScript((String)j_script.get("script"), args);
		}
		
		return new JSONObject() {{
			put("succes", false);
			put("message", "No scripting engine by that name...");
		}};
		
	}
}
