package edu.iescamp.venteoRestApi.auctions.repositories.dao;

import edu.iescamp.venteoRestApi.auctions.models.dto.BidDTO;

import java.util.List;

public interface BidRepositoryCustom {
    List<BidDTO> findBidDTObyBidId(Long bidId);
    List<BidDTO> findBidsDTOByAuctionId(Long auctionId);
}
