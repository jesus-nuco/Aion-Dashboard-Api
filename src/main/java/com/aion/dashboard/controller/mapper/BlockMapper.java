package com.aion.dashboard.controller.mapper;
  
import com.aion.dashboard.datatransferobject.BlockDTO;
import com.aion.dashboard.datatransferobject.PageableDTO;
import com.aion.dashboard.domainobject.BlockDO;
import org.springframework.data.domain.Page;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class BlockMapper
{
    public static BlockDO makeDriverDO(BlockDTO blockDTO)
    {
        return new BlockDO(blockDTO.getBlockNumber(), blockDTO.getBlockHash(),
        		blockDTO.getMinerAddress(), blockDTO.getParentHash(), blockDTO.getReceiptTxRoot(),
        		blockDTO.getStateRoot(), blockDTO.getTxTrieRoot(), blockDTO.getExtraData(), blockDTO.getNonce(),
        		blockDTO.getBloom(), blockDTO.getSolution(), blockDTO.getDifficulty(),
        		blockDTO.getTotalDifficulty(), blockDTO.getNrgConsumed(), blockDTO.getNrgLimit(),
        		blockDTO.getSize(), blockDTO.getBlockTimestamp(), blockDTO.getNumTransactions(),
        		blockDTO.getBlockTime(), blockDTO.getTransactionList(), blockDTO.getNrgReward());  
    }


    public static BlockDTO makeBlockDTO(BlockDO blockDO)
    {
        BlockDTO.BlockDTOBuilder blockDTOBuilder = BlockDTO.newBuilder()
        		.setBlockNumber(blockDO.getBlockNumber()).setBlockHash(blockDO.getBlockHash())
        		.setMinerAddress(blockDO.getMinerAddress()).setParentHash(blockDO.getParentHash())
        		.setReceiptTxRoot(blockDO.getReceiptTxRoot())
        		.setStateRoot(blockDO.getStateRoot()).setTxTrieRoot(blockDO.getTxTrieRoot())
        		.setExtraData(blockDO.getExtraData()).setNonce(blockDO.getNonce())
        		.setBloom(blockDO.getBloom()).setSolution(blockDO.getSolution())
        		.setDifficulty(blockDO.getDifficulty())
        		.setTotalDifficulty(blockDO.getTotalDifficulty()).setNrgConsumed(blockDO.getNrgConsumed())
        		.setNrgLimit(blockDO.getNrgLimit())
        		.setSize(blockDO.getSize()).setBlockTimestamp(blockDO.getBlockTimestamp())
        		.setNumTransactions(blockDO.getNumTransactions())
        		.setBlockTime(blockDO.getBlockTime()).setTransactionList(blockDO.getTransactionList())
        		.setNrgReward(blockDO.getNrgReward());
            
        return blockDTOBuilder.createDriverDTO();
    }


    public static List<BlockDTO> makeBlockDTOList(Collection<BlockDO> blockDOS)
    {
        return blockDOS.stream()
            .map(BlockMapper::makeBlockDTO)
            .collect(Collectors.toList());
    }

	public static PageableDTO makePageableDTO(Page<BlockDO> page){
		return PageableDTO
				.getBuilder()
				.setList(page.getContent().parallelStream().map(BlockMapper::makeBlockDTO).collect(Collectors.toList()))
				.setNumber(page.getNumber())
				.setSize(page.getSize())
				.setTotalElements(page.getTotalElements())
				.setTotalPages(page.getTotalPages())
				.build();
	}

}
