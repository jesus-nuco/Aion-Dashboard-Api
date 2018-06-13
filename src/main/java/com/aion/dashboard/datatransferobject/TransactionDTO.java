package com.aion.dashboard.datatransferobject;

public class TransactionDTO {



    private TransactionDTO(String transactionHash,
                          String blockHash,
                          Long blockNumber,
                          Long transactionIndex,
                          String fromAddr,
                          String toAddr,
                          Long nrgConsumed,
                          Long nrgPrice,
                          String transactionTimestamp,
                          String blockTimestamp,
                          String value,
                          String transactionLog,
                          String data,
                          String nonce,
                          String txError,
                          String contractAddr) {
        this.transactionHash = transactionHash;
        this.blockHash = blockHash;
        this.blockNumber = blockNumber;
        this.transactionIndex = transactionIndex;
        this.fromAddr = fromAddr;
        this.toAddr = toAddr;
        this.nrgConsumed = nrgConsumed;
        this.nrgPrice = nrgPrice;
        this.transactionTimestamp = transactionTimestamp;
        this.blockTimestamp = blockTimestamp;
        this.value = value;
        this.transactionLog = transactionLog;
        this.data = data;
        this.nonce = nonce;
        this.txError = txError;
        this.contractAddr = contractAddr;
    }

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

    public TransactionDTOBuilder newBuilder(){
        return new TransactionDTOBuilder();
    }



    public static class TransactionDTOBuilder{


        String transactionHash;
        String blockHash;
        Long blockNumber;

        public TransactionDTOBuilder setTransactionHash(String transactionHash) {
            this.transactionHash = transactionHash;
            return this;
        }

        public TransactionDTOBuilder setBlockHash(String blockHash) {
            this.blockHash = blockHash;
            return this;
        }

        public TransactionDTOBuilder setBlockNumber(Long blockNumber) {
            this.blockNumber = blockNumber;
            return this;
        }

        public TransactionDTOBuilder setTransactionIndex(Long transactionIndex) {
            this.transactionIndex = transactionIndex;
            return this;
        }

        public TransactionDTOBuilder setFromAddr(String fromAddr) {
            this.fromAddr = fromAddr;
            return this;
        }

        public TransactionDTOBuilder setToAddr(String toAddr) {
            this.toAddr = toAddr;
            return this;
        }

        public TransactionDTOBuilder setNrgConsumed(Long nrgConsumed) {
            this.nrgConsumed = nrgConsumed;
            return this;
        }

        public TransactionDTOBuilder setNrgPrice(Long nrgPrice) {
            this.nrgPrice = nrgPrice;
            return this;
        }

        public TransactionDTOBuilder setTransactionTimestamp(String transactionTimestamp) {
            this.transactionTimestamp = transactionTimestamp;
            return this;
        }

        public TransactionDTOBuilder setBlockTimestamp(String blockTimestamp) {
            this.blockTimestamp = blockTimestamp;
            return this;
        }

        public TransactionDTOBuilder setValue(String value) {
            this.value = value;
            return this;
        }

        public TransactionDTOBuilder setTransactionLog(String transactionLog) {
            this.transactionLog = transactionLog;
            return this;
        }

        public TransactionDTOBuilder setData(String data) {
            this.data = data;
            return this;
        }

        public TransactionDTOBuilder setNonce(String nonce) {
            this.nonce = nonce;
            return this;
        }

        public TransactionDTOBuilder setTxError(String txError) {
            this.txError = txError;
            return this;
        }

        public TransactionDTOBuilder setContractAddr(String contractAddr) {
            this.contractAddr = contractAddr;
            return this;
        }

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


        private TransactionDTOBuilder(){}



        public TransactionDTO createTransactionDTO(){
            return new TransactionDTO( transactionHash,
                    blockHash,
                    blockNumber,
                    transactionIndex,
                    fromAddr,
                    toAddr,
                    nrgConsumed,
                    nrgPrice,
                    transactionTimestamp,
                    blockTimestamp,
                    value,
                    transactionLog,
                    data,
                    nonce,
                    txError,
                    contractAddr);
        }

    }


}
