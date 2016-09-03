package com.defimov.poc.trie;

import java.util.HashMap;

/**
 * @autor Дмитрий Ефимов
 * @date 02.09.2016
 * @version 1.0
 */
public class TrieNode {
    char c;
    HashMap<Character, TrieNode> children = new HashMap<>();
    boolean isLeaf;

    //конструктор для корня
    public TrieNode() {}

    //конструктор для пути
    public TrieNode(char c){
        this.c = c;
    }
}
