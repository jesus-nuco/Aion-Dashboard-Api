package com.aion.dashboard.mapper;



import org.json.JSONObject;
import org.springframework.data.domain.Pageable;

/**
 * @author dennis-aion
 *
 * Simple mapper interface
 */
public interface MapperInterface {



    JSONObject getBlockList(int pageNumber, int size);

    JSONObject getTransactionList(int pageNumber, int size);

    JSONObject findByTransactionHash(String searchParam);

    JSONObject findByBlockNumber(long blockNumber);

    JSONObject findByBlockHash(String blockHash);

    JSONObject findTransactionAndBlockFromBlockNumber(long blockNumber);

    JSONObject findTransactionAndBlockFromBlockHash(String blockHash);

    JSONObject findTransactionByBlockNumber(long blockNumber);

    JSONObject findTransactionByBlockHash(String blockHash);

    JSONObject findByBlockNumberBetweenAndMinerAddress(long startBlockNumber, long endBlockNumber, String searchNumber, Pageable pageable);

    JSONObject findByIdBetweenAndFromAddrOrToAddr(long startId, String fromAddr, String toAddr, Pageable pageable);


    JSONObject findBlocksMinedByAddress(String searchParam, int blockPage, int blockSize);


    /**
     *
     * @return blocks mined, inbound transactions and outbound transactions
     */
    JSONObject getDailyTransactionStatistics();

    JSONObject getAccountsDetails(String searchParams);









}
