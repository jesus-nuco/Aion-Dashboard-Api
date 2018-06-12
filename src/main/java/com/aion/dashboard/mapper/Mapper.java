package com.aion.dashboard.mapper;

import org.json.JSONObject;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;


@Component
public class Mapper implements MapperInterface {
    @Override
    public JSONObject getBlockList(int pageNumber, int size) {
        if (pageNumber < 0 || size <0) throw new IllegalArgumentException();


        return new JSONObject("{'page':{'number':"+pageNumber+",'size':"+size+",'totalPages':40,'totalElements':1000},'content':[]}");
    }

    @Override
    public JSONObject getTransactionList(int pageNumber, int size) {
        if (pageNumber < 0 || size <0) throw new IllegalArgumentException();


        return new JSONObject("{'page':{'number':"+pageNumber+",'size':"+size+",'totalPages':40,'totalElements':1000},'content':[]}");    }

    @Override
    public JSONObject findByTransactionHash(String searchParam) {
        return null;
    }

    @Override
    public JSONObject findByBlockNumber(long blockNumber) {
        return null;
    }

    @Override
    public JSONObject findByBlockHash(String blockHash) {
        return null;
    }

    @Override
    public JSONObject findTransactionAndBlockFromBlockNumber(long blockNumber) {
        return null;
    }

    @Override
    public JSONObject findTransactionAndBlockFromBlockHash(String blockHash) {
        return null;
    }

    @Override
    public JSONObject findTransactionByBlockNumber(long blockNumber) {
        return null;
    }

    @Override
    public JSONObject findTransactionByBlockHash(String blockHash) {
        return null;
    }

    @Override
    public JSONObject findByBlockNumberBetweenAndMinerAddress(long startBlockNumber, long endBlockNumber, String searchNumber, Pageable pageable) {
        return null;
    }

    @Override
    public JSONObject findByIdBetweenAndFromAddrOrToAddr(long startId, String fromAddr, String toAddr, Pageable pageable) {
        return null;
    }

    @Override
    public JSONObject findBlocksMinedByAddress(String searchParam, int blockPage, int blockSize) {
        return null;
    }

    @Override
    public JSONObject getDailyTransactionStatistics() {
        return null;
    }

    @Override
    public JSONObject getAccountsDetails(String searchParams) {
        return null;
    }
}
