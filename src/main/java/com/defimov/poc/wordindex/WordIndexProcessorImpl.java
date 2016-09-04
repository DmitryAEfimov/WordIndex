package com.defimov.poc.wordindex;

import com.defimov.poc.trie.Trie;
import com.defimov.poc.trie.TrieNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @autor Дмитрий Ефимов
 * @date 02.09.2016
 * @version 1.0
 */
public class WordIndexProcessorImpl implements WordIndexProcessor {
    private Trie trie = new Trie();
    private HashMap<TrieNode, Set<Integer>> frequencyMap = new HashMap<>();

    @Override
    public void loadFile(String filename) {
        try {
            init(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Set<Integer> getIndexes4Word(String searchWord) {
        return frequencyMap.size() > 0 ? frequencyMap.get(trie.getNode(searchWord)) : null;
    }

    private void init(String filename) throws IOException {
        Pattern pattern = Pattern.compile("([^\\p{Punct}\\p{Space}]|(?<!\\p{Space})[-'](?!\\p{Space}))+");
        Matcher matcher = pattern.matcher("");

        String tmpString;
        //суммарное смещение от начала файла
        int offset = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while ((tmpString = reader.readLine()) != null) {
                int fromIndex = 0;
                matcher.reset(tmpString);
                while (matcher.find()) {
                    String word = matcher.group();
                    TrieNode node = trie.add(word);

                    //если слово встречается в строке несколько раз
                    int position = tmpString.indexOf(word, fromIndex);
                    if (position != -1) {
                        addToMap(node, offset + position);
                        fromIndex = position + word.length();
                    }
                }
                offset += tmpString.length() + System.lineSeparator().length();
            }
        }
    }

    public HashMap<TrieNode, Set<Integer>> getFrequencyMap() {
        return frequencyMap;
    }

    private void addToMap(TrieNode node, int position) {
        Set<Integer> positions;
        if (!frequencyMap.containsKey(node)) {
            positions = new HashSet<>();
            frequencyMap.put(node, positions);
        }

        positions = frequencyMap.get(node);
        positions.add(position);
    }
}
