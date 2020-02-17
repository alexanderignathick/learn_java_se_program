package com.ignathick.hotel2;

import java.util.List;

/**
 * The Interface FileUtil.
 *
 * @param <T> the generic type
 * @author Sergei Yakimchik (SENLA)
 * @date 09/02/2014
 */
public interface IFileUtil<T> {

    /**
     * Read from file.
     *
     * @return the t[]
     */
    //T[] readFromFile();
    List<T> readFromFile();

    /**
     * Write to file.
     *
     * @param values the values
     */
    void writeToFile(final List<T> values);

    /**
     * To line.
     *
     * @param entity the entity
     * @return the string
     */
    String toLine(T entity);


    /**
     * From line.
     *
     * @param line the line
     * @return the t
     */
    T fromLine(String line);

}
