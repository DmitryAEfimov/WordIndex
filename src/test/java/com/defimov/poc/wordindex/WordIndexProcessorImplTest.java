package com.defimov.poc.wordindex;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
        String filename = this.getClass().getResource("/huge.txt").getPath();
        WordIndexProcessorExt hugeTester = new WordIndexProcessorExt();
        Calendar cldFrom = Calendar.getInstance();
        hugeTester.loadFile(filename);
        Calendar cldTo = Calendar.getInstance();
        hugeTester.calcWordCount();

        long millis = cldTo.getTimeInMillis() - cldFrom.getTimeInMillis();
        double speed = ((double) millis)/hugeTester.getWordCount();

        assertTrue("Slow index building: " + speed, speed <= TRIE_NODE_BUILD_SPEED);
    }

    private Integer[] getPositions(Set<Integer> set) {
        Integer[] array = new Integer[set.size()];
        Arrays.sort(set.toArray(array));
        return array;
    }
}
