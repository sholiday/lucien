package com.riffbit.lucien.script;

import net.minidev.json.JSONObject;

public interface ScriptingEngine {
	public JSONObject runScript(String script, String args[]);
}
