package com.aion.dashboard.controller.mapper;

import com.aion.dashboard.datatransferobject.BlockDTO;
import com.aion.dashboard.domainobject.BlockDO;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

class BlockMapperTest {

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

    public BlockMapperTest() {

        blockNumber = 45L;
        blockHash = "0xanboarner";
        minerAddress = "0xaev;0";
        parentHash = "avawerv";
        receiptTxRoot = "safagb";
        stateRoot = "anwefjo";
        txTrieRoot = "rawebaer";
        extraData = "";
        nonce = "eabnornboaernbo";
        bloom = "jaenrobnae";
        solution = "aerbponbpi";
        difficulty = "eranjobnaer";
        totalDifficulty = "anebnaepok";
        nrgConsumed = 5L;
        nrgLimit =45L;
        size = 4L;
        blockTimestamp = 4156L;
        numTransactions = 455485L;
        blockTime = 415L;
        nrgReward = "aebaerbv";
        transactionList ="aerb";



    }


    @org.junit.jupiter.api.Test
    void makeBlockDTO() {

        
        
    }

    @org.junit.jupiter.api.Test
    void makeBlockDTOList() {
    }
    
    
    
    
    private BlockDO makeDummyDO(){
        BlockDO blockDO = new BlockDO();
        
        return blockDO;
    }
    
    private BlockDTO makeDummyDTO(){
        
        return BlockDTO.newBuilder().createDriverDTO();
    }
    
    
    private BlockDO makeDummyDOWithData(){
        return new BlockDO();
        
    }
}