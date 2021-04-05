package com.example;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.TreeMap;

//Used to print result
public class PrintControl {
    public static void printRes(String serviceUrl, String fileUrl){
        System.out.println("===============================================================================");
        System.out.println();
        URL url = null;
        try {
            url = new URL(serviceUrl);
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        QName qname = new QName("http://example.com/", "MidServiceImpService");
        Service service = Service.create(url, qname);
        MidService ms = service.getPort(MidService.class);

        String content = FileIO.readFile(fileUrl);

        try{
            int total = content.getBytes("utf-8").length;
            System.out.println("The file have " + total +" bytes");
            System.out.println();
        }catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }


        String[] ips = new String[4];
        ips[0] = "172.20.0.2";
        ips[1] = "172.20.0.3";
        ips[2] = "172.20.0.4";
        ips[3] = "172.20.0.5";


        System.out.println("Client connected cluster:");
        System.out.print("Cluster Sever: " );
        for(String ip : ips){
            System.out.print(ip +", ");
        }
        System.out.println();


        String res =  ms.getFinalResult(content, ips);
        String[] strs = res.split("//");


        AnalyzeData.analyzeExtraInfo(strs[0]);
        TreeMap<String, Integer> map = AnalyzeData.StringToMap(strs[1]);
        AnalyzeData.analyzeMap(map);
        System.out.println();
        System.out.println("===============================================================================");
    }
}
