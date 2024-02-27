package edu.iescamp.venteoRestApi.promotions.exceptions;

import edu.iescamp.venteoRestApi.shared.exceptions.DataNotFoundException;

public class PromotionNotFoundException extends DataNotFoundException {

    public PromotionNotFoundException(Long id) {
        super("La promocion con id: " + id + " no exsiste");
    }
}
