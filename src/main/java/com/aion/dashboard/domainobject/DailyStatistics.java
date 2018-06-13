package com.aion.dashboard.domainobject;

import org.json.JSONArray;

public class DailyStatistics {

	private long peakTransaction;
	private long totalTransaction;
	
	// account info
	private JSONArray inboundTransactions;
	private JSONArray outboundTransactions;
	private JSONArray blocksMined;

	private DailyStatistics() {
		peakTransaction = 0;
		totalTransaction = 0;
		inboundTransactions = new JSONArray();
		outboundTransactions = new JSONArray();
		blocksMined = new JSONArray();
	}

	private static class DailyStatisticsHolder {
		static final DailyStatistics INSTANCE = new DailyStatistics();
	}

	public static DailyStatistics getInstance() {
		return DailyStatistics.DailyStatisticsHolder.INSTANCE;
	}
	
	public void setStatistics(long peakTx, long totalTx, JSONArray inboundTransactions, JSONArray outboundTransactions, JSONArray blocksMined) {
		this.peakTransaction = peakTx;
		this.totalTransaction = totalTx;
		this.inboundTransactions = inboundTransactions;
		this.outboundTransactions = outboundTransactions;
		this.blocksMined = blocksMined;
	}
	
	public long getPeakTransaction() {
		return peakTransaction;
	}
	
	public long getTotalTransaction() {
		return totalTransaction;
	}

	public JSONArray getInboundTransactions() {
		return inboundTransactions;
	}

	public JSONArray getOutboundTransactions() {
		return outboundTransactions;
	}

	public JSONArray getBlocksMined() {
		return blocksMined;
	}
}
