package com.defimov.poc.trie;

import java.util.HashMap;

/**
 * теория
 * https://habrahabr.ru/post/111874/
 *
 * @autor Дмитрий Ефимов
 * @date 02.09.2016
 * @version 1.0
 */
public class Trie {
    private TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    // Добавляем слово в дерево.
    public TrieNode add(String word) {
        HashMap<Character, TrieNode> children = root.children;
        TrieNode trie = null;

        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);

            if (children.containsKey(c)) {
                trie = children.get(c);
            } else {
                trie = new TrieNode(c);
                children.put(c, trie);
            }

            children = trie.children;
        }

        //Если конец слова -> значит узел
        if (trie != null) {
            trie.isLeaf = true;
            return trie;
        } else {
            return null;
        }
    }

    public TrieNode getNode(String str){
        HashMap<Character, TrieNode> children = root.children;
        TrieNode trie = null;
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (children.containsKey(c)) {
                trie = children.get(c);
                children = trie.children;
            } else {
                return null;
            }
        }

        return trie;
    }
}
