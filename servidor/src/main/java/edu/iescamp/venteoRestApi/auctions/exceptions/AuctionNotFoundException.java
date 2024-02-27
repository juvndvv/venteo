package edu.iescamp.venteoRestApi.auctions.exceptions;

import edu.iescamp.venteoRestApi.shared.exceptions.DataNotFoundException;

public class AuctionNotFoundException extends DataNotFoundException {
    public AuctionNotFoundException(Long id) {
        super("La subasta con id " + id + " no existe");
    }
}
