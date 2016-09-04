package com.defimov.poc.wordindex;

import com.defimov.poc.trie.TrieNode;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Created by Dmitry.Efimov on 02.09.2016.
 */
public class WordIndexProcessorImplTest {
    private final static double TRIE_NODE_BUILD_SPEED = .02; //millisec
    private WordIndexProcessor tester = new WordIndexProcessorImpl();
    private String filename;

    @Before
    public void setFilename() {
        filename = this.getClass().getResource("/normal.txt").getPath();
        tester.loadFile(filename);
    }

    @Test
    public void test1InNormalTXTDescription() {
        String word = "никогда";

        assertNull(tester.getIndexes4Word(word));
    }

    @Test
    public void test2InNormalTXTDescription() {
        String word = "слово";
        Integer[] expectedPositions = {104,144,284,291,477,649};

        assertArrayEquals(expectedPositions, getPositions(tester.getIndexes4Word(word)));
    }

    @Test
    public void test3aInNormalTXTDescription() {
        String word = "we're";
        Integer[] expectedPositions = {441};

        assertArrayEquals(expectedPositions, getPositions(tester.getIndexes4Word(word)));
    }

    @Test
    public void test3bInNormalTXTDescription() {
        String word = "что-то";
        Integer[] expectedPositions = {484};

        assertArrayEquals(expectedPositions, getPositions(tester.getIndexes4Word(word)));
    }

    @Test
    public void test4InNormalTXTDescription() {
        String word = "лад";
        Integer[] expectedPositions = {629,656};

        assertArrayEquals(expectedPositions, getPositions(tester.getIndexes4Word(word)));
    }

    @Test
    public void test5InNormalTXTDescription() {
        String word = "";

        assertNull(tester.getIndexes4Word(word));
    }

    @Test
    public void test6InNormalTXTDescription() {
        String filename = this.getClass().getResource("/empty.txt").getPath();
        tester.loadFile(filename);

        assertNull(tester.getIndexes4Word("noMatterWord"));
    }

    @Test
    public void test7InNormalTXTDescription() {
        HashMap<TrieNode, Set<Integer>> frequencyMap;
        String filename = this.getClass().getResource("/huge.txt").getPath();
        WordIndexProcessorImpl hugeTester = new WordIndexProcessorImpl();
        Calendar cldFrom = Calendar.getInstance();
        hugeTester.loadFile(filename);
        Calendar cldTo = Calendar.getInstance();
        frequencyMap = hugeTester.getFrequencyMap();

        long wordCount = calcWordCount(frequencyMap);
        long millis = cldTo.getTimeInMillis() - cldFrom.getTimeInMillis();
        double speed = ((double) millis)/wordCount;

        assertTrue("Slow index building: " + speed, speed <= TRIE_NODE_BUILD_SPEED);
    }

    private Integer[] getPositions(Set<Integer> set) {
        Integer[] array = new Integer[set.size()];
        Arrays.sort(set.toArray(array));
        return array;
    }

    private long calcWordCount(Map<TrieNode, Set<Integer>> frequencyMap) {
        long wordCount = 0;
        for (Set<Integer> set : frequencyMap.values()) {
            wordCount += set.size();
        }

        return wordCount;
    }
}
