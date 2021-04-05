package com.example;

import java.io.IOException;
import java.util.Map;
import java.util.TreeMap;

import org.codehaus.jackson.map.ObjectMapper;

//used for process data
public class WordCount {

    public static String countWord(String input) {
        Map<String, Integer> map = new TreeMap();
        String[] words = input.split("\\W+");
        int len = words.length;

        for(int i = 0; i < len; i++) {
            String word = words[i];
            if (word.matches("[a-zA-Z]+")) {
                String lowWord = word.toLowerCase();
                map.put(lowWord, map.getOrDefault(lowWord, 0) + 1);
            }
        }

        String str = DataCovert.mapToString(map);
        return str;
    }

    public static TreeMap<String, Integer> mapCountWord(String input) {
        TreeMap<String, Integer> map = new TreeMap();
        String[] words = input.split("\\W+");
        int len = words.length;

        for(int i = 0; i < len; i++) {
            String word = words[i];
            if (word.matches("[a-zA-Z]+")) {
                String lowWord = word.toLowerCase();
                map.put(lowWord, map.getOrDefault(lowWord, 0) + 1);
            }
        }
        return map;
    }

    public static String[] splitString(String content, int num) {
        String[] res = new String[num];
        int size = content.length() / num;
        int start = 0;
        int end = 0;

        for(int i = 0; i < num; ++i) {
            if (i == num - 1) {
                res[i] = content.substring(start);
                break;
            }

            for(end += size; Character.isLetter(content.charAt(end)); ++end) {
            }
            res[i] = content.substring(start, end);
            start = end;
        }
        return res;
    }
}
