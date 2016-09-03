package com.defimov.poc.wordindex;

import java.util.Set;

/**
 * Created by Dmitry.Efimov on 03.09.2016.
 */
public class WordIndexProcessorExt extends WordIndexProcessorImpl {
    long wordCount;

    public long getWordCount() {return wordCount;}

    public void calcWordCount() {
        for (Set<Integer> set : frequencyMap.values()) {
            wordCount += set.size();
        }
    }
}
