package com.example;

import javax.management.MalformedObjectNameException;
import javax.xml.ws.Endpoint;
import java.net.InetAddress;
import java.net.UnknownHostException;


//Endpoint publisher
public class WSPublisher{
    public static void main(String[] args) throws UnknownHostException {
        String ip = InetAddress.getLocalHost().getHostAddress();
        //ip = "localhost:3000";
        System.out.println("server ip:" + ip);
        Endpoint.publish("http://" + ip + "/ws/wordcount", new WordCountServiceImp());
    }

}

