package com.example;
import javax.jws.WebService;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@WebService(endpointInterface = "com.example.WordCountService")
public class WordCountServiceImp implements WordCountService {
    public String getWordCount(String content, String role, String[] ips){
        String curip = "";
        try{
            curip = InetAddress.getLocalHost().getHostAddress();
        }catch (UnknownHostException e){
            e.printStackTrace();
        }

        String res = "";
        FutureTask<String> futureTask1 = null;
        FutureTask<String> futureTask2 = null;
        FutureTask<String> futureTask3 = null;

        if(role.equals("leader")){
            System.out.println("The leader server(" + curip + ") is working......");
            String[] subtasks = WordCount.splitString(content, ips.length + 1);
            TreeMap<String, Integer> map = WordCount.mapCountWord(subtasks[0]);
            futureTask1 = new FutureTask<String>(new ThreadNew(subtasks[1], ips[0]));
            futureTask2 = new FutureTask<String>(new ThreadNew(subtasks[2], ips[1]));
            futureTask3 = new FutureTask<String>(new ThreadNew(subtasks[3], ips[2]));
            StringBuilder extrInfo = new StringBuilder();
            extrInfo.append("server-" + curip + ": ");
            try{
                extrInfo.append(subtasks[0].getBytes("utf-8").length);
            }catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            extrInfo.append("&");

            for(int i = 0; i < 3; i++){
                extrInfo.append("server-" + ips[i] + ": ");
                try{
                    extrInfo.append(subtasks[i+1].getBytes("utf-8").length);
                }catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                if(i < 2) extrInfo.append("&");
                else extrInfo.append("//");
            }

            System.out.println(extrInfo.toString());

            new Thread(futureTask1).start();
            new Thread(futureTask2).start();
            new Thread(futureTask3).start();


            String res1 = "";
            String res2 = "";
            String res3 = "";
            try {
                res1 = futureTask1.get();
                res2 = futureTask2.get();
                res3 = futureTask3.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            while(!futureTask1.isDone() || !futureTask2.isDone() || !futureTask3.isDone()){}
            TreeMap<String,Integer> resMap1 = DataCovert.StringToMap(res1);
            resMap1.forEach((key, value) -> {
                map.merge(key, value, Integer::sum);
            });

            TreeMap<String,Integer> resMap2 = DataCovert.StringToMap(res2);
            resMap2.forEach((key, value) -> {
                map.merge(key, value, Integer::sum);
            });

            TreeMap<String,Integer> resMap3 = DataCovert.StringToMap(res3);
            resMap3.forEach((key, value) -> {
                map.merge(key, value, Integer::sum);
            });


            res = DataCovert.mapToString(map);
            res = extrInfo.toString() + res;

        }else if(role.equals("server")){
            System.out.println("The server(" + curip + ") is computing......");
            /*try {
                Thread.sleep(500L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
            res = WordCount.countWord(content);
        }
        return res;
    }
}
