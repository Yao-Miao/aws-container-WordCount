package com.example;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.TreeMap;

public class InterManager {
    //use this method to call the web service of other nodes
    public static String CallOtherServer(String content, String ip){

        System.out.println(">>>>>>>>>>The Leader server is calling server: " + ip);
        URL url = null;
        try {
            url = new URL("http://" + ip + "/ws/wordcount?wsdl");
        }
        catch(Exception e) {
            e.printStackTrace();
        }

        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://example.com/", "WordCountServiceImpService");
        Service service = Service.create(url, qname);
        WordCountService wcs = service.getPort(WordCountService.class);
        String[] ips = new String[0];
        String res =  wcs.getWordCount(content, "server", ips);
        //TreeMap<String,Integer> resMap = DataCovert.StringToMap(res);
        //return resMap;
        return  res;
    }
}
