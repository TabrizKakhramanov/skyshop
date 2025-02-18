package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@SessionScope
@Component
public class ProductBasket {
    private final Map<UUID, Integer> productBasket;

    public ProductBasket(){
        productBasket = new HashMap<>();
    }

    public void addProduct(UUID id){
            productBasket.put(id, 1);
    }

    public Map<UUID,Integer> getAllProducts(){
        return Collections.unmodifiableMap(productBasket);
    }


}
