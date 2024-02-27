package edu.iescamp.venteoRestApi.auctions.repositories.dao;

import edu.iescamp.venteoRestApi.auctions.repositories.entities.Bid;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface BidRepository extends JpaRepository<Bid, Long>, BidRepositoryCustom {
    @Query("SELECT b FROM Bid b WHERE b.auctionId = ?1 ORDER BY b.amount DESC LIMIT 1")
    Bid findGreaterBidByAuctionId(Long auctionId);
}
