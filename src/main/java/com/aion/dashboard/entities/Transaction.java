package com.aion.dashboard.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="Transaction")
public class Transaction {
	
	@Id
	Long id;
	String transactionHash;
	String blockHash;
	Long blockNumber;
	Long transactionIndex;
	String fromAddr;
	String toAddr;
	Long nrgConsumed;
	Long nrgPrice;
	String transactionTimestamp;
	String blockTimestamp;
	String value;
	String transactionLog;
	String data;
	String nonce;
	String txError;
	String contractAddr;
	
	
	public String getTxError() {
		return txError;
	}
	public void setTxError(String txError) {
		this.txError = txError;
	}
	public String getContractAddr() {
		return contractAddr;
	}
	public void setContractAddr(String contractAddr) {
		this.contractAddr = contractAddr;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTransactionHash() {
		return transactionHash;
	}
	public void setTransactionHash(String transactionHash) {
		this.transactionHash = transactionHash;
	}
	public String getBlockHash() {
		return blockHash;
	}
	public void setBlockHash(String blockHash) {
		this.blockHash = blockHash;
	}
	public Long getBlockNumber() {
		return blockNumber;
	}
	public void setBlockNumber(Long blockNumber) {
		this.blockNumber = blockNumber;
	}
	public Long getTransactionIndex() {
		return transactionIndex;
	}
	public void setTransactionIndex(Long transactionIndex) {
		this.transactionIndex = transactionIndex;
	}
	public String getFromAddr() {
		return fromAddr;
	}
	public void setFromAddr(String fromAddr) {
		this.fromAddr = fromAddr;
	}
	public String getToAddr() {
		return toAddr;
	}
	public void setToAddr(String toAddr) {
		this.toAddr = toAddr;
	}
	public Long getNrgConsumed() {
		return nrgConsumed;
	}
	public void setNrgConsumed(Long nrgConsumed) {
		this.nrgConsumed = nrgConsumed;
	}
	public Long getNrgPrice() {
		return nrgPrice;
	}
	public void setNrgPrice(Long nrgPrice) {
		this.nrgPrice = nrgPrice;
	}
	public String getTransactionTimestamp() {
		return transactionTimestamp;
	}
	public void setTransactionTimestamp(String transactionTimestamp) {
		this.transactionTimestamp = transactionTimestamp;
	}
	public String getBlockTimestamp() {
		return blockTimestamp;
	}
	public void setBlockTimestamp(String blockTimestamp) {
		this.blockTimestamp = blockTimestamp;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getTransactionLog() {
		return transactionLog;
	}
	public void setTransactionLog(String transactionLog) {
		this.transactionLog = transactionLog;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	
	
	
}
