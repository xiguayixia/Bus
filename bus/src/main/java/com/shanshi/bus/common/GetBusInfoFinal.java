package com.shanshi.bus.common;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;

/**
 * 通过公交线路num与线路code   得到公交站点信息与公交车位置信息
 */
public class GetBusInfoFinal {
    //http://www.bjbus.com/home/ajax_rtbus_data.php?act=busTime&selBLine=1&selBDir=5276138694316562750&selBStop=1
    private static final String url="http://www.bjbus.com/home/ajax_rtbus_data.php";
    private static final String act="busTime";
    private static final String selBStop="1";
    public static JSONObject getBusinfo(String linenum, String linecode ){
        Map<String,String> params=new HashMap<>();
        params.put("act", act);
        params.put("selBLine", linenum);
        params.put("selBDir", linecode);
        params.put("selBStop", selBStop);
        String businfo=HttpClientUtil.doGet(url, params);
        JSONObject object=JSONObject.fromObject(businfo);
        JSONObject jsonObject=getBusLocation((String)object.get("html"));
        //System.out.println(businfo);
        //System.out.println((String)object.get("html"));
        return jsonObject;
    }
    /*解析bus到站信息的html得到bus的位置数组*/
    public static JSONObject getBusLocation(String businfoHtml){
        JSONObject jsonObject=new JSONObject();
        Document document= Jsoup.parse(businfoHtml);
        Elements element_info=document.select("div[class=inner]");
        String info=element_info.get(0).select("p").get(0).html();
        String priceinfo=info.substring(info.indexOf(";")+1).replace("&nbsp;", "  ");
        JSONArray array=new JSONArray();
        Elements element_location_temp=document.select("ul[class=fixed]").get(0).select("li");
        for(int i=0;i<element_location_temp.size();i++){
            JSONObject object=new JSONObject();
            Element element=element_location_temp.get(i).select("div").get(0);
            String id=element.attr("id");
            if(id.endsWith("m")){
                object.put("i_class", element.select("i").get(0).attr("class"));
                object.put("station", "");
            }else{
                object.put("i_class", element.select("i").get(0).attr("class"));
                object.put("station", element.select("span").get(0).attr("title"));
            }
            array.add(object);
        }
        jsonObject.put("bus_info", priceinfo);
        jsonObject.put("location_info", array);
        return jsonObject;
    }
}
