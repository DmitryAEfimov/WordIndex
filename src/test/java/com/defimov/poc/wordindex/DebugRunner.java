package com.defimov.poc.wordindex;

import java.util.Set;

/**
 * Created by Dmitry.Efimov on 03.09.2016.
 */
public class DebugRunner {
    public static void main(String[] args) {
        String fileTested = "normal.txt";
        String word = "Искомое";

        ClassLoader classLoader = DebugRunner.class.getClassLoader();
        String filename = classLoader.getResource(fileTested).getFile();
        System.out.println(filename);

        WordIndexProcessor wordIndexProcessor = new WordIndexProcessorImpl();
        wordIndexProcessor.loadFile(filename);
        Set<Integer> set = wordIndexProcessor.getIndexes4Word(word);
        print(set);
    }

    private static void print(Set<Integer> set) {
        String delimiter = "";
        for (Integer value : set) {
            System.out.println(delimiter + value);
            delimiter = ", ";
        }
    }
}
