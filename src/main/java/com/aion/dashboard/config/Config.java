package com.aion.dashboard.config;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.json.JSONArray;
import org.json.JSONObject;

public class Config {

	private JSONObject json;
	private JSONArray web3ApiConnectionList = new JSONArray();
	
	private Config() {
		try {
			byte[] encoded = Files.readAllBytes(Paths.get("config.json"));
			String content = new String(encoded, StandardCharsets.UTF_8);
			json = new JSONObject(content);
			
			this.web3ApiConnectionList = json.getJSONArray("web3ApiConnectionList");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	private static class ConfigHolder {
		static final Config INSTANCE = new Config();
	}

	public static Config getConfig() {
		return Config.ConfigHolder.INSTANCE;
	}

	public JSONArray getWeb3ApiConnectionList() {
		return web3ApiConnectionList;
	}
}
