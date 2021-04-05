package com.example;

import javax.jws.WebService;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import java.net.URL;
import java.util.Random;

@WebService(endpointInterface = "com.example.MidService")
public class MidServiceImp implements MidService{
    //return result to client
    public String getFinalResult(String content, String[] ips){
        String res = "";
        Random random = new Random();
        int leaderIndex = random.nextInt(ips.length);
        System.out.println("The leader ip is " + ips[leaderIndex]);
        String[] newIps = new String[ips.length - 1];
        int index = 0;
        for(int i = 0; i < ips.length; i++){
            if(i != leaderIndex){
                newIps[index] = ips[i];
                index++;
            }
        }

        URL url = null;
        try {
            url = new URL("http://" + ips[leaderIndex] + "/ws/wordcount?wsdl");
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        //1st argument service URI, refer to wsdl document above
        //2nd argument is service name, refer to wsdl document above
        QName qname = new QName("http://example.com/", "WordCountServiceImpService");
        Service service = Service.create(url, qname);
        WordCountService wcs = service.getPort(WordCountService.class);
        res =  wcs.getWordCount(content, "leader", newIps);
        return res;
    }
}
