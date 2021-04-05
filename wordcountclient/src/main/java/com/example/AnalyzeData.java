package com.example;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import java.io.IOException;
import java.util.*;

//used to analyze the data get from webservice
public class AnalyzeData {

    public static TreeMap<String, Integer> StringToMap(String str){
        TreeMap<String, Integer> map = new TreeMap<>();
        String[] strs = str.split("&");
        for(String s : strs){
            if(s.length() > 0){
                String[] temp = s.split(":");
                map.put(temp[0], Integer.parseInt(temp[1]));
            }
        }
        return map;
    }

    public static void analyzeMap(Map<String, Integer> map) {
        List<Map.Entry<String, Integer>> list = new ArrayList(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String,Integer>>() {
            //升序排序
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue().compareTo(o1.getValue());
            }
        });
        System.out.println("Words Found Most: ");
        for(int i = 0; i < 10 && i < list.size(); i++){
            System.out.print(list.get(i).getKey() + ": " + list.get(i).getValue() + ", ");
        }
        System.out.println("...");
        System.out.println("Words Found Least: ");
        for(int i = list.size() - 1; i >= 0 && i > list.size() - 11; i--) {
            System.out.print(list.get(i).getKey() + ": " + list.get(i).getValue() + ", ");
        }
        System.out.println("...");
    }
    public static void analyzeExtraInfo(String info) {
        String[] strs = info.split("&");
        System.out.println();
        System.out.println("Leader Elected: " + strs[0].split(":")[0]);
        for(String s : strs){
            System.out.println(s + " bytes");
        }
        System.out.println();

        System.out.println("Results provided by " + strs[0].split(":")[0] + ":");

    }
}
