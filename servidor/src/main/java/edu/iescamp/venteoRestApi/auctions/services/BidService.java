package edu.iescamp.venteoRestApi.auctions.services;

import edu.iescamp.venteoRestApi.auctions.models.dto.BidDTO;
import edu.iescamp.venteoRestApi.auctions.repositories.entities.Bid;

import java.util.List;

public interface BidService {
    List<BidDTO> findBidsByAuctionId(Long auctionId);
    BidDTO save(Bid bid) throws InterruptedException;
}
