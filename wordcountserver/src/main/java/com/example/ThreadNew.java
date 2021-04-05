package com.example;

import java.util.concurrent.Callable;

//Used for Multi-threaded concurrency
public class ThreadNew implements Callable<String> {
    private String content;
    private String ip;
    public ThreadNew(String content, String ip){
        this.content = content;
        this.ip = ip;
    }
    @Override
    public String call() throws Exception {

        String res = InterManager.CallOtherServer(this.content, this.ip);
        return res;
    }

}
