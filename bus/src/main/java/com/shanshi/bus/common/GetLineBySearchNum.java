package com.shanshi.bus.common;

import net.sf.json.JSONArray;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.HashMap;
import java.util.Map;
import net.sf.json.JSONObject;

/**
 * 根据公交号码查询线路信息
 */
public class GetLineBySearchNum {
    //http://www.bjbus.com/home/ajax_rtbus_data.php?act=getLineDirOption&selBLine=1
    private static final String url="http://www.bjbus.com/home/ajax_rtbus_data.php";
    private static final String act="getLineDirOption";
    public static JSONArray getLine(String num){
        Map<String, String> param = new HashMap<>();
        param.put("act", act);
        param.put("selBLine", num);
        String lineHtml = HttpClientUtil.doGet(url, param);
        //System.out.println(lineHtml);
        return jsoupLineHtml(lineHtml);
    }
    /*将请求得到的html进行解析*/
    public static JSONArray jsoupLineHtml(String lineHtml){
        Document doc=Jsoup.parse(lineHtml);
        Elements elements=doc.select("option");
        JSONArray array=new JSONArray();
        if(elements.size()>1){
            for(int i=1; i<elements.size();i++){
                JSONObject object=new JSONObject();
                object.put("linecode", elements.get(i).val());
                object.put("lineinfo", elements.get(i).html().split("\\(")[1].split("\\)")[0]);
                array.add(object);
            }
        }
        return array;
    }
}
