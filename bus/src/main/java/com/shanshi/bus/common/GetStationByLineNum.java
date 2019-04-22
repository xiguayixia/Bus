package com.shanshi.bus.common;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import org.jsoup.select.Elements;
import java.util.HashMap;
import java.util.Map;

/**
 * 通过公交num跟线路code  得到公交站点列表 （未使用，因为查询位置信息时站点与位置可以同时得到）
 */
public class GetStationByLineNum {
    //http://www.bjbus.com/home/ajax_rtbus_data.php?act=getDirStationOption&selBLine=12&selBDir=5158977645759324323
    private static final String url="http://www.bjbus.com/home/ajax_rtbus_data.php";
    private static final String act="getDirStationOption";
    public static JSONArray getStations(String linenum , String linecode){
        Map<String, String> param = new HashMap<>();
        param.put("act", act);
        param.put("selBLine", linenum);
        param.put("selBDir", linecode);
        String stationHtml = HttpClientUtil.doGet(url, param);
        //GetBusInfoFinal.getBusinfo(linenum, linecode);
        return jsoupStationHtml(stationHtml);
    }
    /*将请求得到的html进行解析*/
    public static JSONArray jsoupStationHtml(String stationHtml){
        Document  document = Jsoup.parse(stationHtml);
        Elements elements = document.select("option");
        JSONArray array = new JSONArray();
        if(elements.size()>1){
            for(int i=1; i<elements.size(); i++){
                JSONObject object=new JSONObject();
                object.put("stationcode", elements.get(i).val());
                object.put("stationname", elements.get(i).html());
                array.add(object);
            }
        }
        return array;
    }
}
