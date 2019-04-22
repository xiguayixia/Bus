package com.shanshi.bus.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class URLTest_beijing {

    public static void main(String []args){
        String searchline="http://www.bjbus.com/home/ajax_rtbus_data.php?act=getLineDirOption&selBLine=1";
        String searchStation="http://www.bjbus.com/home/ajax_rtbus_data.php?act=getDirStationOption&selBLine=12&selBDir=5158977645759324323";
        String searchBusInfo="http://www.bjbus.com/home/ajax_rtbus_data.php?act=busTime&selBLine=1&selBDir=5276138694316562750&selBStop=1";
        try {
            URL url=new URL(searchBusInfo);
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
