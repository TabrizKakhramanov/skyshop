package org.skypro.skyshop.model.basket;

import java.util.List;

public class UserBasket {
    private List<BasketItem> basketItems;
    private int total;

    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    public int getTotal() {
        return total;
    }

    public UserBasket(List<BasketItem> basketItem){
        this.basketItems = basketItem;
        this.total = basketItem.stream().map(el->el.getProduct().getProductPrice()*el.getProductQuantity())
                .reduce((a,e)->a+e).orElse(0);
    }
}

