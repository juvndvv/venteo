package edu.iescamp.venteoRestApi.auctions.models.mappers;

import edu.iescamp.venteoRestApi.auctions.models.dto.AuctionOverviewDTO;
import edu.iescamp.venteoRestApi.auctions.repositories.entities.Auction;
import org.springframework.stereotype.Component;

@Component
public class AuctionMapper {

    // TODO: devolver el username del usuario que ha creado la subasta
    public AuctionOverviewDTO toOverviewDTO(Auction auction) {
        return AuctionOverviewDTO.builder().
                id(auction.getAuctionId()).
                name(auction.getAuctionName()).
                description(auction.getAuctionDescription()).
                imageUrl(auction.getImageUrl()).
                initialPrice(auction.getInitialPrice()).
                build();
    }
}
