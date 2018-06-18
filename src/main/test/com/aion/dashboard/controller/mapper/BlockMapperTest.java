package com.aion.dashboard.controller.mapper;

import com.aion.dashboard.datatransferobject.BlockDTO;
import com.aion.dashboard.domainobject.BlockDO;
import com.aion.dashboard.domainobject.BlockMap;
import org.junit.jupiter.api.BeforeEach;

import java.util.ArrayList;
import java.util.List;

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


    /**
     * Checks that the the Mapper correctly maps the data in the DO to DTO
     * ie.  the data is placed in the corresponding field in the DO
     */
    @org.junit.jupiter.api.Test
    void makeBlockDTO() {

       // assertTrue(isSame(makeDummyDO(),makeDummyDTO()));
        assertEquals(isSame(makeDummyDOWithData(),makeDummyDTOWithData()), isSame(makeDummyDOWithData(),BlockMapper.makeBlockDTO(makeDummyDOWithData())));




    }

    /**
     * Checks that the mapper correctly converts the DO list to a DTO list
     */
    @org.junit.jupiter.api.Test
    void makeBlockDTOList() {
        List<BlockDO> blockDOS = new ArrayList<>();
        List<BlockDTO> blockDTOS = new ArrayList<>();
        for (int i = 0; i<10;i++) {
            blockDOS.add(makeDummyDOWithData());
            blockDTOS.add(makeDummyDTOWithData());

        }
        List<BlockDTO> acutalDTOS = BlockMapper.makeBlockDTOList(blockDOS);


        assertEquals(blockDTOS.stream().allMatch((blockDTO -> isSame(makeDummyDOWithData(),blockDTO))), acutalDTOS.stream().allMatch(blockDTO -> isSame(makeDummyDOWithData(), blockDTO)) );


    }


    /**
     * Compares the DO and DTO to ensure that they are the same
     * @param blockDO
     * @param blockDTO
     * @return
     */
    boolean isSame(BlockDO blockDO, BlockDTO blockDTO){
        if (!blockDO.getBlockNumber().equals(blockDTO.getBlockNumber())) return false;
        else if (!blockDO.getBlockTimestamp().equals(blockDTO.getBlockTimestamp())) return false;
        else if (!blockDO.getBlockTime().equals(blockDTO.getBlockTime())) return false;
        else if (!blockDO.getDifficulty().equals(blockDTO.getDifficulty())) return false;
        else if (!blockDO.getNrgConsumed().equals(blockDTO.getNrgConsumed())) return false;
        else if (!blockDO.getNrgLimit().equals(blockDTO.getNrgLimit())) return false;
        else if (!blockDO.getNumTransactions().equals(blockDTO.getNumTransactions())) return false;
        else if (!blockDO.getBlockHash().equals(blockDTO.getBlockHash())) return false;
        else if (!blockDO.getBloom().equals(blockDTO.getBloom())) return false;
        else if (!blockDO.getExtraData().equals(blockDTO.getExtraData())) return false;
        else if (!blockDO.getMinerAddress().equals(blockDTO.getMinerAddress())) return false;
        else if (!blockDO.getNonce().equals(blockDTO.getNonce())) return false;
        else if (!blockDO.getNrgReward().equals(blockDTO.getNrgReward())) return false;
        else if (!blockDO.getParentHash().equals(blockDTO.getParentHash())) return false;
        else if (!blockDO.getReceiptTxRoot().equals(blockDTO.getReceiptTxRoot())) return false;
        else if (!blockDO.getSize().equals(blockDTO.getSize())) return false;
        else if (!blockDO.getSolution().equals(blockDTO.getSolution()))return false;
        else if (!blockDO.getStateRoot().equals(blockDTO.getStateRoot())) return false;
        else if (!blockDO.getTotalDifficulty().equals(blockDTO.getTotalDifficulty())) return false;
        else if (!blockDO.getTransactionList().equals(blockDTO.getTransactionList())) return false;
        else if (!blockDO.getTxTrieRoot().equals(blockDTO.getTransactionList())) return false;
        return true;
    }
    

    
    
    private BlockDO makeDummyDOWithData(){
        return new BlockDO(
                 blockNumber,
                 blockHash,
                 minerAddress,
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
                 nrgReward
        );
        
    }


    private BlockDTO makeDummyDTOWithData(){
        return BlockDTO.newBuilder()
                .setBlockNumber(blockNumber)
                .setBlockHash(blockHash)
                .setBlockTime(blockTime)
                .setBlockTimestamp(blockTimestamp)
                .setMinerAddress(minerAddress)
                .setParentHash(parentHash)
                .setReceiptTxRoot(receiptTxRoot)
                .setStateRoot(stateRoot)
                .setTxTrieRoot(txTrieRoot)
                .setExtraData(extraData)
                .setNonce(nonce)
                .setBloom(bloom)
                .setSolution(solution)
                .setDifficulty(difficulty)
                .setTotalDifficulty(totalDifficulty)
                .setNrgConsumed(nrgConsumed)
                .setNrgLimit(nrgLimit)
                .setSize(size)
                .setTransactionList(transactionList)
                .setNrgReward(nrgReward)
                .createDriverDTO();
    }
}