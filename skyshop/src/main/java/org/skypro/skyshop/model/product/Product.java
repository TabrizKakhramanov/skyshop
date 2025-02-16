package org.skypro.skyshop.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private final UUID id;
    private String productName;

    public Product(UUID id, String productName){
        this.id=id;
        if (!productName.isBlank() && productName != null) {
            this.productName = productName;
        } else {
            throw new IllegalArgumentException("Неверное имя продукта!");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public abstract int getProductPrice();

    @Override
    public String toString() {
        return getProductName() + ": " + getProductPrice();
    }

    public abstract boolean isSpecial();


    @Override
    public String getSearchTerm() {
        return productName;
    }

    @Override
    public String getSearchContentType() {
        return "PRODUCT";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(productName, product.productName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(productName);
    }

}
