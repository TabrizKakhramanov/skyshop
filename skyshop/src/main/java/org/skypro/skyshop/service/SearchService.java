package org.skypro.skyshop.service;


import org.skypro.skyshop.model.search.SearchResult;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {
    private final StorageService storageService;

    public SearchService(StorageService storageService){
        this.storageService = storageService;
    }

    public Collection<SearchResult> search(String searchItem){
        Collection<SearchResult> results = storageService.getAllItems().stream()
                .filter(el->el.getSearchTerm().toLowerCase().contains(searchItem.toLowerCase()))
                .map(el-> new SearchResult(el.getId(),el.getSearchTerm(),el.getSearchContentType()))
                .toList();
        return results;
    }
}
