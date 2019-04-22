package com.shanshi.bus.controller;

import com.shanshi.bus.common.BusJSONResult;
import com.shanshi.bus.common.GetBusInfoFinal;
import com.shanshi.bus.common.GetStationByLineNum;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 根据公交的几路字段num与线路唯一标志字段code   查询公交站点信息与公交位置信息
 */
@RestController
public class GetLocationController {

    @PostMapping("/getLocation")
    public BusJSONResult getStationByLine(String linenum , String linecode){

        try {
            return BusJSONResult.ok(GetBusInfoFinal.getBusinfo(linenum, linecode).toString());
        } catch (Exception e) {
            e.printStackTrace();
            return BusJSONResult.errorMsg("未找到线路信息！");
        }
    }
}
