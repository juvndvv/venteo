package edu.iescamp.venteoRestApi.auctions.services.impl;

import edu.iescamp.venteoRestApi.auctions.exceptions.AuctionNotFoundException;
import edu.iescamp.venteoRestApi.auctions.repositories.entities.Auction;
import edu.iescamp.venteoRestApi.auctions.services.AuctionService;
import edu.iescamp.venteoRestApi.auctions.models.dto.AuctionPatchDTO;
import edu.iescamp.venteoRestApi.auctions.repositories.dao.AuctionRepository;
import edu.iescamp.venteoRestApi.categories.repositories.dao.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuctionServiceImpl implements AuctionService {

    @Autowired
    private AuctionRepository auctionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Auction> findAll() {
        return auctionRepository.findAll();
    }

    @Override
    public List<Auction> findAuctionsByCategoryId(Long categoryId) {
        categoryRepository.findById(categoryId);    // Comprueba que exista
        return auctionRepository.findAuctionsByCategoryId(categoryId);
    }

    @Override
    public List<Auction> findAuctionsByUserId(Long userId) {
        return auctionRepository.findAuctionsByUserId(userId);
    }

    @Override
    public List<Auction> findLiveAuctions() {
        return auctionRepository.findLiveAuctions();
    }

    @Override
    public Auction findById(Long id) {
        return auctionRepository.findById(id).orElseThrow(() -> new AuctionNotFoundException(id));
    }

    @Override
    public Auction save(Auction auction) {
        if (auction.getAuctionId() != null)
            findById(auction.getAuctionId());   // Comprueba que exista

        return auctionRepository.save(auction);
    }

    @Override
    public Auction save(AuctionPatchDTO auctionPatchDTO) {
        // Busca la subasta a actualizar
        Auction auctionToUpdate = auctionRepository.findById(
                auctionPatchDTO.getAuctionId())
                .orElseThrow(() -> new AuctionNotFoundException(auctionPatchDTO.getAuctionId()));

        updateAuction(auctionToUpdate, auctionPatchDTO);

        return auctionRepository.save(auctionToUpdate);
    }

    @Override
    public Boolean delete(Long id) {
        findById(id);   // Comprueba que exista
        auctionRepository.deleteById(id);
        return true;
    }

    private void updateAuction(Auction auctionToUpdate, AuctionPatchDTO auction) {
        if (auction.getAuctionName() != null) {
            auctionToUpdate.setAuctionName(auction.getAuctionName());
        }

        if (auction.getAuctionDescription() != null) {
            auctionToUpdate.setAuctionDescription(auction.getAuctionDescription());
        }

        if (auction.getCategoryId() != null) {
            auctionToUpdate.setCategoryId(auction.getCategoryId());
        }

        if (auction.getUserId() != null) {
            auctionToUpdate.setUserId(auction.getUserId());
        }

        if (auction.getStartsAt() != null) {
            auctionToUpdate.setStartsAt(auction.getStartsAt());
        }

        if (auction.getEndsAt() != null) {
            auctionToUpdate.setEndsAt(auction.getEndsAt());
        }

        auctionToUpdate.setInitialPrice(auction.getInitialPrice());

        if (auction.getImageUrl() != null) {
            auctionToUpdate.setImageUrl(auction.getImageUrl());
        }
    }
}
