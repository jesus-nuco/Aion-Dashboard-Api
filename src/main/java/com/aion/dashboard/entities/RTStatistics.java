package com.aion.dashboard.entities;

import org.json.JSONArray;
import org.json.JSONObject;

public class RTStatistics {

	private JSONArray blocks;
	private JSONArray transactions;
	private JSONObject metrics;

	private RTStatistics() {
		blocks = new JSONArray();
		transactions = new JSONArray();
		metrics = new JSONObject();
	}

	private static class RTStatisticsHolder {
		static final RTStatistics INSTANCE = new RTStatistics();
	}

	public static RTStatistics getInstance() {
		return RTStatistics.RTStatisticsHolder.INSTANCE;
	}

	public JSONArray getBlocks() {
		return blocks;
	}

	public void setBlocks(JSONArray blocks) {
		this.blocks = blocks;
	}

	public JSONArray getTransactions() {
		return transactions;
	}

	public void setTransactions(JSONArray transactions) {
		this.transactions = transactions;
	}

	public JSONObject getMetrics() {
		return metrics;
	}

	public void setMetrics(JSONObject metrics) {
		this.metrics = metrics;
	}

	public String getDashboardJSON() {
		try {
			RTStatistics rtStatistics = RTStatistics.getInstance();
			JSONObject result = new JSONObject();
			result.put("metrics", rtStatistics.getMetrics());
			result.put("blocks", rtStatistics.getBlocks());
			result.put("transactions", rtStatistics.getTransactions());

			JSONArray jsonArray = new JSONArray();
			jsonArray.put(result);

			return new JSONObject().put("content", jsonArray).toString();

		}catch(Exception e) {
			e.printStackTrace();
			JSONObject result = new JSONObject();
			result.put("message", "Error: Invalid Request");
			return result.toString();
		}
	}
	
	
}
