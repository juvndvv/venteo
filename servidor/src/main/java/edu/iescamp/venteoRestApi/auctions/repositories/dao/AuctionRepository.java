package edu.iescamp.venteoRestApi.auctions.repositories.dao;

import edu.iescamp.venteoRestApi.auctions.repositories.entities.Auction;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
@Transactional
@Repository
public interface AuctionRepository extends JpaRepository<Auction, Long> {
    @Query("SELECT a FROM Auction a WHERE a.categoryId = ?1")
    List<Auction> findAuctionsByCategoryId(Long categoryId);

    @Query("SELECT a FROM Auction a WHERE a.endsAt > CURRENT_TIMESTAMP AND a.startsAt < CURRENT_TIMESTAMP ")
    List<Auction> findLiveAuctions();

    @Query("SELECT a FROM Auction a WHERE a.userId = ?1")
    List<Auction> findAuctionsByUserId(Long userId);
}
