package com.ignathick.hotel2.Store;

import java.util.List;

public interface Dao<T> { //или Store<T>

    T get(Long id);

    List<T> getAll();

    void save(T t);

    void update(T t);

    void delete(T t);

    void deleteAll();

}
