package com.aion.dashboard.domainobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Block")
public class BlockDO {
	
	@Id
	Long blockNumber;
	String blockHash;
	String minerAddress;
	String parentHash;
	String receiptTxRoot;
	String stateRoot;
	String txTrieRoot;
	String extraData;
	String nonce;
	String bloom;
	String solution;
	String difficulty;
	String totalDifficulty;
	Long nrgConsumed;
	Long nrgLimit;
	Long size;
	Long blockTimestamp;
	Long numTransactions;
	Long blockTime;
	String transactionList;
	String nrgReward;
	
	public BlockDO(Long blockNumber,
			String blockHash,
			String minerAddress,
			String parentHash,
			String receiptTxRoot,
			String stateRoot,
			String txTrieRoot,
			String extraData,
			String nonce,
			String bloom,
			String solution,
			String difficulty,
			String totalDifficulty,
			Long nrgConsumed,
			Long nrgLimit,
			Long size,
			Long blockTimestamp,
			Long numTransactions,
			Long blockTime,
			String transactionList,
			String nrgReward) {
		this.blockNumber=blockNumber;
		this.blockHash=blockHash;
		this.minerAddress=minerAddress;
		this.parentHash=parentHash;
		this.receiptTxRoot=receiptTxRoot;
		this.stateRoot=stateRoot;
		this.txTrieRoot=txTrieRoot;
		this.extraData=extraData;
		this.nonce=nonce;
		this.bloom=bloom;
		this.solution=solution;
		this.difficulty=difficulty;
		this.totalDifficulty=totalDifficulty;
		this.nrgConsumed=nrgConsumed;
		this.nrgLimit=nrgLimit;
		this.size=size;
		this.blockTimestamp=blockTimestamp;
		this.numTransactions=numTransactions;
		this.blockTime=blockTime;
		this.transactionList=transactionList;
		this.nrgReward=nrgReward;
		
	}
	
	public String getTransactionList() {
		return transactionList;
	}
	public void setTransactionList(String transactionList) {
		this.transactionList = transactionList;
	}
	public Long getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(Long blockNumber) {
		this.blockNumber = blockNumber;
	}
	public String getBlockHash() {
		return blockHash;
	}
	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}
	public String getMinerAddress() {
		return minerAddress;
	}
	public void setMinerAddress(String minerAddress) {
		this.minerAddress = minerAddress;
	}
	public String getParentHash() {
		return parentHash;
	}
	public void setParentHash(String parentHash) {
		this.parentHash = parentHash;
	}
	public String getReceiptTxRoot() {
		return receiptTxRoot;
	}
	public void setReceiptTxRoot(String receiptTxRoot) {
		this.receiptTxRoot = receiptTxRoot;
	}
	public String getStateRoot() {
		return stateRoot;
	}
	public void setStateRoot(String stateRoot) {
		this.stateRoot = stateRoot;
	}
	public String getTxTrieRoot() {
		return txTrieRoot;
	}
	public void setTxTrieRoot(String txTrieRoot) {
		this.txTrieRoot = txTrieRoot;
	}
	public String getExtraData() {
		return extraData;
	}
	public void setExtraData(String extraData) {
		this.extraData = extraData;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getBloom() {
		return bloom;
	}
	public void setBloom(String bloom) {
		this.bloom = bloom;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	public String getTotalDifficulty() {
		return totalDifficulty;
	}
	public void setTotalDifficulty(String totalDifficulty) {
		this.totalDifficulty = totalDifficulty;
	}
	public Long getNrgConsumed() {
		return nrgConsumed;
	}
	public void setNrgConsumed(Long nrgConsumed) {
		this.nrgConsumed = nrgConsumed;
	}
	public Long getNrgLimit() {
		return nrgLimit;
	}
	public void setNrgLimit(Long nrgLimit) {
		this.nrgLimit = nrgLimit;
	}
	public Long getSize() {
		return size;
	}
	public void setSize(Long size) {
		this.size = size;
	}
	public Long getBlockTimestamp() {
		return blockTimestamp;
	}
	public void setBlockTimestamp(Long blockTimestamp) {
		this.blockTimestamp = blockTimestamp;
	}
	public Long getNumTransactions() {
		return numTransactions;
	}
	public void setNumTransactions(Long numTransactions) {
		this.numTransactions = numTransactions;
	}
	public Long getBlockTime() {
		return blockTime;
	}
	public void setBlockTime(Long blockTime) {
		this.blockTime = blockTime;
	}
	public String getNrgReward() {
		return nrgReward;
	}
	public void setNrgReward(String nrgReward) {
		this.nrgReward = nrgReward;
	}
	
}
