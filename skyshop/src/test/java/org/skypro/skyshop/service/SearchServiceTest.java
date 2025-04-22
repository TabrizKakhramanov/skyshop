package org.skypro.skyshop.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop.model.article.Article;
import org.skypro.skyshop.model.product.SimpleProduct;
import org.skypro.skyshop.model.search.SearchResult;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
public class SearchServiceTest {

    @Mock
    private StorageService storageService;

    @InjectMocks
    private SearchService searchService;

    // 1. Поиск при отсутствии объектов в StorageService
    @Test
    public void testSearchWhenStorageIsEmpty() {
        Mockito.when(storageService.getAllItems()).thenReturn(Collections.emptyList());

        Collection<SearchResult> results = searchService.search("milk");

        assertTrue(results.isEmpty(), "Результаты должны быть пустыми, когда хранилище пусто");
    }

    // 2. Поиск при наличии объектов, но нет подходящих
    @Test
    public void testSearchWhenNoMatchingItems() {
        List<Searchable> mockItems = List.of(
                new Article(UUID.randomUUID(), "not milk", "Just a text"),
                new SimpleProduct(UUID.randomUUID(), "bread", 100)
        );

        Mockito.when(storageService.getAllItems()).thenReturn(mockItems);

        Collection<SearchResult> results = searchService.search("chocolate");

        assertTrue(results.isEmpty(), "Результаты должны быть пустыми, если совпадений нет");
    }

    // 3. Поиск при наличии совпадений
    @Test
    public void testSearchWhenMatchingItemsExist() {
        UUID id = UUID.randomUUID();
        List<Searchable> mockItems = List.of(
                new SimpleProduct(id, "chocolate", 90),
                new Article(UUID.randomUUID(), "some title", "Eat fruit!")
        );

        Mockito.when(storageService.getAllItems()).thenReturn(mockItems);

        Collection<SearchResult> results = searchService.search("chocolate");

        assertEquals(1, results.size(), "Должен быть один результат");
        SearchResult result = results.iterator().next();
        assertEquals("chocolate", result.getName());
        assertEquals(id, result.getId());
    }

    // 4. Поиск без учёта регистра
    @Test
    public void testSearchIgnoreCase() {
        List<Searchable> mockItems = List.of(
                new Article(UUID.randomUUID(), "MILK Article", "Text")
        );

        Mockito.when(storageService.getAllItems()).thenReturn(mockItems);

        Collection<SearchResult> results = searchService.search("milk");

        assertEquals(1, results.size(), "Поиск должен работать без учёта регистра");
    }
}

