package com.aion.dashboard;

import com.github.benmanes.caffeine.cache.Caffeine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCache;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Configuration
@EnableCaching
public class CacheConfig {

   @Autowired
   private Environment environment;

   public final static String BLOCK_LIST = "blockList";
   public final static String TRANSACTION_LIST = "transactionList";
   public final static String BLOCK_DETAIL_FROM_BLOCK_HASH_OR_BLOCK_NUMBER = "blockDetailFromBlockHashOrBlockNumber";
   public final static String TRANSACTION_DETAIL_FROM_TRANSACTION_HASH = "transactionDetailFromTransactionHash";
   public final static String BLOCK_AND_TRANSACTION_DETAIL_FROM_BLOCK_HASH_OR_BLOCK_NUMBER = "blockAndTransactionDetailFromBlockHashOrBlockNumber";
   public final static String TRANSACTION_DETAIL_FROM_BLOCK_HASH_OR_BLOCK_NUMBER = "transactionDetailFromBlockHashOrBlockNumber";
   public final static String ACCOUNT_DETAIL = "accountDetail";
   public final static String BLOCKS_MINED_BY_ADDRESS = "blocksMinedByAddress";
   public final static String TRANSACTIONS_BY_ADDRESS = "transactionsbyAddress";

   @Bean
   public CacheManager cacheManager() {
      SimpleCacheManager cacheManager = new SimpleCacheManager();

      boolean cacheEnable = false;
      String datadogEnable = environment.getProperty("DATADOG_ENABLE");
      if (datadogEnable != null && datadogEnable.equalsIgnoreCase("true"))
         cacheEnable = true;

      cacheManager.setCaches(Arrays.asList(
              buildExpireAfterWriteCache(BLOCK_LIST,
                      10, TimeUnit.SECONDS, 1000,
                      cacheEnable),

              buildExpireAfterWriteCache(TRANSACTION_LIST,
                      10, TimeUnit.SECONDS, 1000,
                      cacheEnable),

              buildExpireAfterWriteCache(BLOCK_DETAIL_FROM_BLOCK_HASH_OR_BLOCK_NUMBER,
                      10, TimeUnit.SECONDS, 1000,
                      cacheEnable),

              buildExpireAfterWriteCache(TRANSACTION_DETAIL_FROM_TRANSACTION_HASH,
                      10, TimeUnit.SECONDS, 1000,
                      cacheEnable),

              buildExpireAfterWriteCache(BLOCK_AND_TRANSACTION_DETAIL_FROM_BLOCK_HASH_OR_BLOCK_NUMBER,
                      10, TimeUnit.SECONDS, 1000,
                      cacheEnable),

              buildExpireAfterWriteCache(TRANSACTION_DETAIL_FROM_BLOCK_HASH_OR_BLOCK_NUMBER,
                      10, TimeUnit.SECONDS, 1000,
                      cacheEnable),

              buildExpireAfterWriteCache(BLOCKS_MINED_BY_ADDRESS,
                      10, TimeUnit.SECONDS, 1000,
                      cacheEnable),

              buildExpireAfterWriteCache(TRANSACTIONS_BY_ADDRESS,
                      10, TimeUnit.SECONDS, 1000,
                      cacheEnable),

              buildExpireAfterWriteCache(ACCOUNT_DETAIL,
                      10, TimeUnit.SECONDS, 1000,
                      cacheEnable)
           ));
      return cacheManager;
   }

   private Cache buildExpireAfterWriteCache(String name, long duration, TimeUnit timeUnit, long maxSize, boolean cacheEnable) {
      Caffeine cc = Caffeine.newBuilder()
              .expireAfterWrite(duration, timeUnit)
              .maximumSize(maxSize);

      if(cacheEnable)
         cc.recordStats();

      return new CaffeineCache(name, cc.build());
   }
}
