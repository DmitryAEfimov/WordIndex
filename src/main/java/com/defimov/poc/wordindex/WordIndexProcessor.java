package com.defimov.poc.wordindex;

import java.util.Set;

/**
 * Интерфейс предназначен для поиска слова в текстовом файле
 * @autor Дмитрий Ефимов
 * @date 02.09.2016
 * @version 1.0
 */
public interface WordIndexProcessor {
    /**
     * Метод загрузки файла и построения индекса
     * @param filename
     */
    void loadFile(String filename);

    /**
     * Метод поиска
     * @param searchWord
     * @return
     */
    Set<Integer> getIndexes4Word(String searchWord);
}
