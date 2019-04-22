package com.shanshi.bus.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLTest_jinan {

    public static void main(String []args){
        String searchInfo="https://api.waitbus.com/v3/?mothed=search&shanghe=false&s=302&pos=&from=wap";
        String lineInfo="https://api.waitbus.com/v3/?mothed=getLineInfoBylID&lid=267&from=wap";
        String busInfo="https://api.waitbus.com/v3/?mothed=getBusWithTrafficBylID&lid=267&from=wap";
        try {
            URL url=new URL(lineInfo);
            HttpURLConnection connection=(HttpURLConnection) url.openConnection();
            connection.connect();
            BufferedReader reader=new BufferedReader(new InputStreamReader(connection.getInputStream(),"UTF-8"));
            String line=null;
            StringBuffer stringBuffer=new StringBuffer();
            while ((line=reader.readLine())!=null){
                stringBuffer.append(line);
            }
            reader.close();
            connection.disconnect();
            System.out.println(stringBuffer.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
