package com.aion.dashboard.domainobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ParserState")
public class ParserState {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Integer id;
	Long blockNumber;
	Long transactionId;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Long getBlockNumber() {
		return blockNumber;
	}
	public Long getTransactionId() {
		return transactionId;
	}
}