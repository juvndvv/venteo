package edu.iescamp.venteoRestApi.auctions.services.impl;

import edu.iescamp.venteoRestApi.auctions.repositories.dao.BidRepository;
import edu.iescamp.venteoRestApi.auctions.repositories.entities.Bid;
import edu.iescamp.venteoRestApi.auctions.services.BidService;
import edu.iescamp.venteoRestApi.auctions.models.dto.BidDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BidServiceImpl implements BidService {

    private Logger logger = Logger.getLogger(BidService.class.getName());

    @Autowired
    private BidRepository bidRepository;

    @Override
    public List<BidDTO> findBidsByAuctionId(Long auctionId) {
        List<BidDTO> bids = bidRepository.findBidsDTOByAuctionId(auctionId);
        logger.info("Pujas encontradas: " + bids);
        return bids;
    }

    @Override
    public BidDTO save(Bid bid) throws InterruptedException {
        // TODO: que la entrada sea correcta
        //if (!isBidValid(bid)) {
        //    return null;
        //}

        Bid saved = bidRepository.save(bid);
        logger.info("Puja guardada: " + saved);
        return bidRepository.findBidDTObyBidId(saved.getBidId()).getFirst();
    }

    private boolean isBidValid(Bid bid) {
        Bid greaterBid = bidRepository.findGreaterBidByAuctionId(bid.getAuctionId());
        if (greaterBid == null) {
            return true;
        }

        return greaterBid.getAmount() < bid.getAmount();
    }
}
