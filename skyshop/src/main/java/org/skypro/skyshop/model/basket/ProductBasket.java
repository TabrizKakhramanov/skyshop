package org.skypro.skyshop.model.basket;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
public class ProductBasket {
    private final Map<UUID, Integer> productBasket;

    public ProductBasket(){
        productBasket = new HashMap<>();
    }

    public void addProduct(UUID id){
        if(productBasket.containsKey(id)){
            int value = productBasket.get(id);
            productBasket.put(id,value+1);
        } else {
            productBasket.put(id, 1);
        }
    }

    public Map<UUID,Integer> getAllProducts(){
        return Collections.unmodifiableMap(productBasket);
    }


}
