package edu.iescamp.venteoRestApi.auctions.web.controllers;

import edu.iescamp.venteoRestApi.auctions.models.dto.AuctionPatchDTO;
import edu.iescamp.venteoRestApi.auctions.models.dto.BidDTO;
import edu.iescamp.venteoRestApi.auctions.repositories.entities.Auction;
import edu.iescamp.venteoRestApi.auctions.services.AuctionService;
import edu.iescamp.venteoRestApi.auctions.services.BidService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/v1")
public class AuctionController {
    @Autowired
    private AuctionService auctionService;

    @Autowired
    private BidService bidService;

    @GetMapping("/auctions")
    public ResponseEntity<List<Auction>> findAll(@RequestParam(required = false) Boolean live) {
        List<Auction>  auctions = (live != null && live)
                ? auctionService.findLiveAuctions()
                : auctionService.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(auctions);
    }

    @PostMapping("/auctions")
    public ResponseEntity<Auction> create(@Valid @RequestBody Auction auction) {
        return ResponseEntity.status(HttpStatus.OK).body(auctionService.save(auction));
    }

    @GetMapping("/auction/{id}")
    public ResponseEntity<Auction> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(auctionService.findById(id));
    }

    @PutMapping("/auction/{id}")
    public ResponseEntity<Auction> update(@PathVariable Long id, @Valid @RequestBody Auction auction) {
        auction.setAuctionId(id);
        return ResponseEntity.status(HttpStatus.OK).body(auctionService.save(auction));
    }

    @PatchMapping("/auction/{id}")
    public ResponseEntity<Auction> update(@PathVariable Long id, @Valid @RequestBody AuctionPatchDTO auction) {
        auction.setAuctionId(id);
        return ResponseEntity.status(HttpStatus.OK).body(auctionService.save(auction));
    }

    @DeleteMapping("/auction/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(auctionService.delete(id));
    }

    @GetMapping("/auction/{id}/bids")
    public ResponseEntity<List<BidDTO>> findBidsByAuctionId(@PathVariable Long id) {
        return new ResponseEntity<>(bidService.findBidsByAuctionId(id), HttpStatus.OK);
    }
}
