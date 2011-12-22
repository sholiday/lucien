package com.riffbit.lucien.script;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.python.core.PyDictionary;

import net.minidev.json.JSONObject;

public class JythonEngine implements ScriptingEngine {
	ScriptEngine engine;
	Buckets buckets = new Buckets();
	public JythonEngine() {
		engine = new ScriptEngineManager().getEngineByName("python");
		engine.put("buckets", buckets);
	}
	
	@Override
	public JSONObject runScript(String script, String[] args) {
		try {
			engine.eval(script);
			
		} catch (final ScriptException e) {
			return new JSONObject() {{
				put("succes", false);
				put("message", "Error executing script...");
				put("trace" , "Trace: " + e.getMessage());
			}};
			
		}
		
		Object o_result = engine.get("result");
		System.out.println("Result " + o_result);
		if (o_result == null) {
			return new JSONObject() {{
				put("succes", false);
				put("message", "result variable was null, scripts must return a result");
			}};
		}
		
		PyDictionary py_result = (PyDictionary)o_result;
		return new JSONObject(py_result);
	}

}
