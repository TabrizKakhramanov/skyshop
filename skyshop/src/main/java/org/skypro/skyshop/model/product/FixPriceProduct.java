package org.skypro.skyshop.model.product;

import java.util.UUID;

public class FixPriceProduct extends Product{

    private static final int FIX_PRICE = 100;

    public FixPriceProduct(UUID id, String productName) {
        super(id, productName);
    }

    @Override
    public int getProductPrice() {
        return FIX_PRICE;
    }

    @Override
    public String toString() {
        return getProductName() + ": фиксированная цена - " + getProductPrice();
    }

    @Override
    public boolean isSpecial() {
        return true;
    }
}
