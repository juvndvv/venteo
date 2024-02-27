package edu.iescamp.venteoRestApi.auctions.services;

import edu.iescamp.venteoRestApi.auctions.models.dto.AuctionPatchDTO;
import edu.iescamp.venteoRestApi.auctions.repositories.entities.Auction;

import java.util.List;

public interface AuctionService {
    List<Auction> findAll();

    List<Auction> findAuctionsByCategoryId(Long categoryId);

    List<Auction> findAuctionsByUserId(Long userId);

    List<Auction> findLiveAuctions();

    Auction findById(Long id);

    Auction save(Auction auction);

    Auction save(AuctionPatchDTO auctionPatchDTO);

    Boolean delete(Long id);
}
