package org.skypro.skyshop.service;

import org.skypro.skyshop.model.basket.BasketItem;
import org.skypro.skyshop.model.basket.ProductBasket;
import org.skypro.skyshop.model.basket.UserBasket;
import org.springframework.stereotype.Service;

import java.sql.ClientInfoStatus;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService){
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductById(UUID id){
        if (storageService.getProductById(id).isPresent()){
            productBasket.addProduct(id);
        } else{
            throw new IllegalArgumentException("Product not found");
        }
    }

    public UserBasket getUserBasket(){
        Map<UUID, Integer> productsInBasket = productBasket.getAllProducts();
        List<BasketItem> items = productsInBasket.entrySet().stream()
                .map(el-> new BasketItem(storageService.getProductById(el.getKey()).get(),el.getValue()))
                .collect(Collectors.toList());

        if (items.isEmpty()){
            throw new NoSuchElementException("No product");
        } else {
            return new UserBasket(items);
        }


    }
}
