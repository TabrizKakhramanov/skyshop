package org.skypro.skyshop.model.article;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public class Article implements Searchable {
    private final UUID id;

    private String articleName;
    private String articleContent;

    public Article(UUID id,String articleName, String articleContent) {
        this.id = id;
        this.articleName = articleName;
        this.articleContent = articleContent;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String toString() {
        return articleName + " " + articleContent;
    }


    @Override
    public String getSearchTerm() {
        return toString();
    }


    @Override
    public String getSearchContentType() {
        return "ARTICLE";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Article article = (Article) o;
        return Objects.equals(articleName, article.articleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(articleName);
    }
}
