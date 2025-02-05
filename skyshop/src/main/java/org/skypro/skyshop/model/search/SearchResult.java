package org.skypro.skyshop.model.search;

import java.util.UUID;

public class SearchResult {
    private final UUID id;
    private final String name;
    private final String contentType;

    public SearchResult(UUID id, String name,String contentType){
        this.id = id;
        this.name = name;
        this.contentType = contentType;
    }

    public SearchResult fromSearchable(Searchable searchable){
        SearchResult searchResult = new SearchResult(searchable.getId(),
                searchable.getSearchTerm(),
                searchable.getSearchContentType());
        return searchResult;
    }
}
