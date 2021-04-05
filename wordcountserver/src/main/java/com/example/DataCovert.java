package com.example;

import java.util.Map;
import java.util.TreeMap;

public class DataCovert {

    /*public static String mapToJson(Map<String, Integer> map){
        String json = "";
        ObjectMapper mapperObj = new ObjectMapper();
        try {
            json = mapperObj.writeValueAsString(map);
            System.out.println(json);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return json;
    }

    public static TreeMap<String, Integer> jsonToMap(String json){
        TreeMap<String,Integer> resultMap = new TreeMap<>();
        ObjectMapper mapperObj = new ObjectMapper();
        try {
            resultMap = mapperObj.readValue(json,
                    new TypeReference<HashMap<String,String>>(){});
            System.out.println("Output Map: "+resultMap);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return resultMap;
    }*/

    // conver map to string
    public static String mapToString(Map<String, Integer> map){
        StringBuilder res = new StringBuilder();
        for(String key : map.keySet()){
            res.append(key);
            res.append(":");
            res.append(map.get(key));
            res.append("&");
        }
        return res.toString();
    }

    //convert string to map
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


}
