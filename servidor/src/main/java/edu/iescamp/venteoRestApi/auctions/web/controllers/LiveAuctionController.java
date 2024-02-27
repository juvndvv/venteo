package edu.iescamp.venteoRestApi.auctions.web.controllers;

import edu.iescamp.venteoRestApi.auctions.models.dto.BidDTO;
import edu.iescamp.venteoRestApi.auctions.repositories.entities.Bid;
import edu.iescamp.venteoRestApi.auctions.services.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
public class LiveAuctionController {

    @Autowired
    private BidService bidService;

    Logger logger = Logger.getLogger(LiveAuctionController.class.getName());

    @MessageMapping("/auction/{id}")
    public BidDTO sendAuction(Bid bid, @DestinationVariable String id) throws InterruptedException{
        logger.info("Recibida una puja: " + bid + " para la subasta " + id);
        return bidService.save(bid);
    }

    @EventListener
    public void handleWebSocketConnectListener(org.springframework.web.socket.messaging.SessionConnectEvent event) {
        logger.info("Se ha recibido una nueva conexión de web socket desde el cliente" + event.getMessage().getHeaders().get("simpSessionId"));
    }

    @EventListener
    public void handleWebSocketDisconnectListener(org.springframework.web.socket.messaging.SessionDisconnectEvent event) {
        logger.info("Se ha recibido una desconexión de web socket desde el cliente" + event.getMessage().getHeaders().get("simpSessionId"));
    }

    @EventListener
    public void handleWebSocketSubscribeListener(org.springframework.web.socket.messaging.SessionSubscribeEvent event) {
        logger.info("Se ha recibido una suscripción de web socket desde el cliente" + event.getMessage().getHeaders().get("simpSessionId") + " al canal " + event.getMessage().getHeaders().get("simpDestination"));
    }

    @EventListener
    public void handleWebSocketUnsubscribeListener(org.springframework.web.socket.messaging.SessionUnsubscribeEvent event) {
        logger.info("Se ha recibido una desuscripción de web socket desde el cliente" + event.getMessage().getHeaders().get("simpSessionId") + " al canal " + event.getMessage().getHeaders().get("simpDestination"));
    }
}
