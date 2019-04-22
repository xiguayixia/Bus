package com.shanshi.bus.controller;

import com.shanshi.bus.common.BusJSONResult;
import com.shanshi.bus.common.GetLineBySearchNum;
import net.sf.json.JSONArray;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 根据搜索查询出线路
 * 控制器
 */
@RestController
public class GetLineBySearchController {
    @PostMapping("/getLineBySearch")
    public BusJSONResult getLineBySearch(String search) {
        try {
            //调用GetLineBySearchNum.getLine()方法   根据查询字段返回的线路信息（json数组） 通常为2条线路（往返）
            JSONArray array=GetLineBySearchNum.getLine(search);
            return BusJSONResult.ok(array.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return BusJSONResult.errorMsg("未找到线路信息！");
        }
    }
}
