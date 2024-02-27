package edu.iescamp.venteoRestApi.auctions.repositories.dao;

import edu.iescamp.venteoRestApi.auctions.models.dto.BidDTO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class BidRepositoryCustomImpl implements BidRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<BidDTO> findBidDTObyBidId(Long bidId) {
        return entityManager.createQuery(
                        "select new edu.iescamp.venteoRestApi.auctions.models.dto.BidDTO(u.userName, u.imageUrl, b.amount) FROM Bid b JOIN User u on b.userId = u.userId where b.bidId = :bidId",
                        BidDTO.class)
                .setParameter("bidId", bidId)
                .getResultList();
    }

    @Override
    public List<BidDTO> findBidsDTOByAuctionId(Long auctionId) {
        return entityManager.createQuery(
                        "select new edu.iescamp.venteoRestApi.auctions.models.dto.BidDTO(u.userName, u.imageUrl, b.amount) " +
                                "FROM Bid b JOIN User u on b.userId = u.userId " +
                                "where b.auctionId = :auctionId",
                        BidDTO.class)
                .setParameter("auctionId", auctionId)
                .getResultList();
    }
}
