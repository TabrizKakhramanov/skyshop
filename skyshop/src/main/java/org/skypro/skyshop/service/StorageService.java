package org.skypro.skyshop.service;

import org.skypro.skyshop.exceptions.NoSuchProductException;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.DiscountedProduct;
import org.skypro.skyshop.model.product.FixPriceProduct;
import org.skypro.skyshop.model.product.Product;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class StorageService {
    Map<UUID, Product> products;
    Map<UUID, Article> articles;

    public StorageService(){
        products = new HashMap<>();
        articles = new HashMap<>();
        fillProductMap();
        fillArticleMap();
    }

    public Map<UUID, Product> getAllProducts() {
        return products;
    }

    public Map<UUID, Article> getAllArticles() {
        return articles;
    }

    private void fillProductMap(){
        SimpleProduct milk = new SimpleProduct(UUID.randomUUID(),"milk", 90);
        SimpleProduct milk2 = new SimpleProduct(UUID.randomUUID(),"milk2", 190);
        SimpleProduct milk3 = new SimpleProduct(UUID.randomUUID(),"milk3", 290);
        FixPriceProduct banana = new FixPriceProduct(UUID.randomUUID(),"banana");
        DiscountedProduct chocolate = new DiscountedProduct(UUID.randomUUID(),"chocolate", 90, 30);
        this.products.put(milk.getId(), milk);
        this.products.put(milk2.getId(), milk2);
        this.products.put(milk3.getId(), milk3);
        this.products.put(banana.getId(), banana);
        this.products.put(chocolate.getId(), chocolate);
    }

    private void fillArticleMap(){
        Article article1 = new Article(UUID.randomUUID(),"bananas article", "Eat bananas!!!");
        Article article2 = new Article(UUID.randomUUID(),"milk article", "Drink milk!!!");
        this.articles.put(article1.getId(),article1);
        this.articles.put(article2.getId(),article2);
    }

    public List<Searchable> getAllItems(){
        List<Searchable> combinedList = Stream.concat(products.values().stream(), articles.values().stream())
                .collect(Collectors.toList());
        return combinedList;
    }

    public Optional<Product> getProductById(UUID id) {
        if (!products.containsKey(id)){
            throw new NoSuchProductException("Продукт с id "+id+" не найден");
        } else {
            return Optional.ofNullable(products.get(id));
        }
    }
}
