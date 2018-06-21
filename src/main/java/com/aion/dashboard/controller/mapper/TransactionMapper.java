package com.aion.dashboard.controller.mapper;

import com.aion.dashboard.datatransferobject.PageableDTO;
import com.aion.dashboard.datatransferobject.TransactionDTO;
import com.aion.dashboard.domainobject.TransactionDO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;


/**
 * Mapper to be used to create the objects to be returned to the user
 *
 * TODO Test
 */
public class TransactionMapper {


    /**
     * Creates a new transaction domain transfer object from a transaction domain object
     * Can be used to add additional logic
     * @param transactionDO
     * @return TransactionDTO to be returned to client
     */
    public static TransactionDTO makeTransactionDTO(TransactionDO transactionDO){

        return TransactionDTO
                .newBuilder()
                .setBlockHash(transactionDO.getBlockHash())
                .setBlockNumber(transactionDO.getBlockNumber())
                .setBlockTimestamp(transactionDO.getBlockTimestamp())
                .setContractAddr(transactionDO.getContractAddr())
                .setData(transactionDO.getData())
                .setFromAddr(transactionDO.getFromAddr())
                .setToAddr(transactionDO.getToAddr())
                .setNonce(transactionDO.getNonce())
                .setNrgConsumed(transactionDO.getNrgConsumed())
                .setNrgPrice(transactionDO.getNrgPrice())
                .setTransactionHash(transactionDO.getTransactionHash())
                .setTransactionIndex(transactionDO.getTransactionIndex())
                .setTransactionLog(transactionDO.getTransactionLog())
                .setTransactionTimestamp(transactionDO.getTransactionTimestamp())
                .setTxError(transactionDO.getTxError())
                .setValue(transactionDO.getValue())
                .createTransactionDTO();
    }

    /**
     * Creates a list of transfer objects from transaction domain objects
     * @param transactionDOS
     * @return List to be returned to the client
     */
    public static List<TransactionDTO> makeTransactionDTOList(List<TransactionDO> transactionDOS){
        return transactionDOS.
                stream().
                map(TransactionMapper::makeTransactionDTO).
                collect(Collectors.toList());
    }


    public static PageableDTO makePageableDTO(Page<TransactionDO> page){
         return PageableDTO
                 .getBuilder()
                 .setList(page.getContent().parallelStream().map(TransactionMapper::makeTransactionDTO).collect(Collectors.toList()))
                 .setNumber(page.getNumber())
                 .setSize(page.getSize())
                 .setTotalElements(page.getTotalElements())
                 .setTotalPages(page.getTotalPages())
                 .build();
    }



}
