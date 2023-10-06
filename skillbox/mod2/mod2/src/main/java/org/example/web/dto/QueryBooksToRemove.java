package org.example.web.dto;

import javax.validation.constraints.NotEmpty;

public class QueryBooksToRemove {
    @NotEmpty
    private String query;

    public String getQuery() {
        return query;
    }

    public void setQuery(String id) {
        this.query = id;
    }
}
