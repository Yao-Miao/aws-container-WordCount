package com.example;


import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String hostName = "http://";
        String serviceUrl = "";
        String fileUrl = "";
        String exitFlag = "Y";
        System.out.println("****************************************************************");
        System.out.println("* Please Enter The HostName of The WebService:");
        System.out.println("*   -If your Server at local. Enter [localhost]");
        System.out.println("*   -If your Server at EC2. Enter <Public IPv4 DNS>");
        System.out.println("****************************************************************");
        System.out.print(">>");
        hostName += scanner.nextLine();
        serviceUrl = hostName + ":3000/ws/midservice?wsdl";

        System.out.println();
        System.out.println("Your WebService URL is :");
        System.out.println(serviceUrl);
        System.out.println();
        while(exitFlag.equals("Y")){
            System.out.println("Plase Enter File Path (\\ will be replaced): ");
            System.out.print(">>");
            fileUrl = scanner.nextLine();
            fileUrl = fileUrl.replaceAll("\\\\", "");
            if(!FileIO.checkFile(fileUrl)){
                System.out.println("Error: [The File Path is Wrong] ");
                continue;
            }
            System.out.println(fileUrl + " is analyzing.......");
            PrintControl.printRes(serviceUrl, fileUrl);

            System.out.println("Do you Want to Run Again? (Y/N)");
            System.out.print(">>");
            exitFlag = scanner.nextLine();
        }




    }

}
