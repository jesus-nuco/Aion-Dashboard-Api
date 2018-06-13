package com.aion.dashboard.datatransferobject;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BlockDTO
{ 
    
    
    //@JsonIgnore
    Long blockNumber;
    //@NotNull(message = "Username can not be null!")
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
    
    

    private BlockDTO()
    {
    }


    private BlockDTO(Long blockNumber,

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
    		String nrgReward )
    {
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


    public static BlockDTOBuilder newBuilder()
    {
        return new BlockDTOBuilder();
    }

 

	public Long getBlockNumber() {
		return blockNumber;
	}


	public String getBlockHash() {
		return blockHash;
	}


	public String getMinerAddress() {
		return minerAddress;
	}


	public String getParentHash() {
		return parentHash;
	}


	public String getReceiptTxRoot() {
		return receiptTxRoot;
	}


	public String getStateRoot() {
		return stateRoot;
	}


	public String getTxTrieRoot() {
		return txTrieRoot;
	}


	public String getExtraData() {
		return extraData;
	}


	public String getNonce() {
		return nonce;
	}


	public String getBloom() {
		return bloom;
	}


	public String getSolution() {
		return solution;
	}


	public String getDifficulty() {
		return difficulty;
	}


	public String getTotalDifficulty() {
		return totalDifficulty;
	}


	public Long getNrgConsumed() {
		return nrgConsumed;
	}


	public Long getNrgLimit() {
		return nrgLimit;
	}


	public Long getSize() {
		return size;
	}


	public Long getBlockTimestamp() {
		return blockTimestamp;
	}


	public Long getNumTransactions() {
		return numTransactions;
	}


	public Long getBlockTime() {
		return blockTime;
	}


	public String getTransactionList() {
		return transactionList;
	}


	public String getNrgReward() {
		return nrgReward;
	}



	public static class BlockDTOBuilder
    {

	    
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
	    
 

		public BlockDTOBuilder setBlockNumber(Long blockNumber) {
			this.blockNumber = blockNumber;
			return this;
		}


		public BlockDTOBuilder setBlockHash(String blockHash) {
			this.blockHash = blockHash;
			return this;
		}


		public BlockDTOBuilder setMinerAddress(String minerAddress) {
			this.minerAddress = minerAddress;
			return this;
		}


		public BlockDTOBuilder setParentHash(String parentHash) {
			this.parentHash = parentHash;
			return this;
		}


		public BlockDTOBuilder setReceiptTxRoot(String receiptTxRoot) {
			this.receiptTxRoot = receiptTxRoot;
			return this;
		}


		public BlockDTOBuilder setStateRoot(String stateRoot) {
			this.stateRoot = stateRoot;
			return this;
		}


		public BlockDTOBuilder setTxTrieRoot(String txTrieRoot) {
			this.txTrieRoot = txTrieRoot;
			return this;
		}


		public BlockDTOBuilder setExtraData(String extraData) {
			this.extraData = extraData;
			return this;
		}


		public BlockDTOBuilder setNonce(String nonce) {
			this.nonce = nonce;
			return this;
		}


		public BlockDTOBuilder setBloom(String bloom) {
			this.bloom = bloom;
			return this;
		}


		public BlockDTOBuilder setSolution(String solution) {
			this.solution = solution;
			return this;
		}


		public BlockDTOBuilder setDifficulty(String difficulty) {
			this.difficulty = difficulty;
			return this;
		}


		public BlockDTOBuilder setTotalDifficulty(String totalDifficulty) {
			this.totalDifficulty = totalDifficulty;
			return this;
		}


		public BlockDTOBuilder setNrgConsumed(Long nrgConsumed) {
			this.nrgConsumed = nrgConsumed;
			return this;
		}


		public BlockDTOBuilder setNrgLimit(Long nrgLimit) {
			this.nrgLimit = nrgLimit;
			return this;
		}


		public BlockDTOBuilder setSize(Long size) {
			this.size = size;
			return this;
		}


		public BlockDTOBuilder setBlockTimestamp(Long blockTimestamp) {
			this.blockTimestamp = blockTimestamp;
			return this;
		}


		public BlockDTOBuilder setNumTransactions(Long numTransactions) {
			this.numTransactions = numTransactions;
			return this;
		}


		public BlockDTOBuilder setBlockTime(Long blockTime) {
			this.blockTime = blockTime;
			return this;
		}


		public BlockDTOBuilder setTransactionList(String transactionList) {
			this.transactionList = transactionList;
			return this;
		}


		public BlockDTOBuilder setNrgReward(String nrgReward) {
			this.nrgReward = nrgReward;
			return this;
		}

        

        public BlockDTO createDriverDTO()
        {
            return new BlockDTO(blockNumber,
blockHash,minerAddress,
            		parentHash,
            		receiptTxRoot,
            		stateRoot,
            		txTrieRoot,
            		extraData,
            		nonce,
            		bloom,
            		solution,
            		difficulty,
            		totalDifficulty,
            		nrgConsumed,
            		nrgLimit,
            		size,
            		blockTimestamp,
            		numTransactions,
            		blockTime,
            		transactionList,
            		nrgReward );
            		
        }

    }
}
