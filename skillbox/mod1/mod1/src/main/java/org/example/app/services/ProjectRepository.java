package org.example.app.services;

import java.util.List;

public interface ProjectRepository<T> {
    List<T> retreiveAll();

    boolean store(T book);

    boolean removeItemById(Integer bookIdToRemove);

    void removeItemByRegex(String regexToRemove);
}
