package com.example;

import javax.xml.ws.Endpoint;

//Endpoint publisher
public class WSPublisher {
    public static void main(String[] args) {
        Endpoint.publish("http://0.0.0.0:3000/ws/midservice", new MidServiceImp());
    }
}
