package org.skypro.skyshop.model.search;

import java.util.UUID;

public interface Searchable {
    public UUID getId();
    String getSearchTerm();
    String getSearchContentType();
    default String getStringRepresentation(){
        return getSearchTerm()+" "+getSearchContentType();
    }
}
