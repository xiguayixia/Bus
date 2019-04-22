package com.shanshi.bus.controller;

import com.shanshi.bus.common.BusJSONResult;
import com.shanshi.bus.model.Collects;
import com.shanshi.bus.service.CollectsService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CollectsController {
    @Autowired
    private Collects collects;
    @Autowired
    private CollectsService collectsService;
    @PostMapping("/getCollectList")
    public BusJSONResult getCollectList(String userid){
        try {
            List<Collects> list=collectsService.selectByUserId(Integer.parseInt(userid));
            JSONArray array=JSONArray.fromObject(list);
            //System.out.println(array.toString());
            return BusJSONResult.ok(array.toString());
        } catch (Exception e) {
            e.printStackTrace();
            return BusJSONResult.errorMsg("未找到收藏信息！");
        }
    }

    @PostMapping("/collect")
    public BusJSONResult collect(String userid,String linecode,String linenum,String linename){
        try {
            List<Collects> list=collectsService.selectByUserId(Integer.parseInt(userid));
            Integer coid=0;
            int col=1;
            for(Collects co : list){
                //已收藏
                if(co.getBuslinecode().equals(linecode) && co.getBuslinenum().equals(linenum) && co.getCollects()){
                    col=0;
                    coid=co.getId();
                }else if(co.getBuslinecode().equals(linecode) && co.getBuslinenum().equals(linenum) && !co.getCollects()){//之前收藏过，表里有记录
                    col=2;
                    coid=co.getId();
                }
            }
            if(col==0){
                collects=collectsService.selectByPrimaryKey(coid);
                collects.setCollects(false);
                collectsService.updateByPrimaryKey(collects);
            }else if(col==2){
                collects=collectsService.selectByPrimaryKey(coid);
                collects.setCollects(true);
                collectsService.updateByPrimaryKey(collects);
            }else if(col==1){
                collects.setId(null);
                collects.setCollects(true);
                collects.setUserid(Integer.parseInt(userid));
                collects.setBuslinecode(linecode);
                collects.setBuslinenum(linenum);
                collects.setBuslinename(linename);
                collectsService.insert(collects);
            }

            return BusJSONResult.ok();
        } catch (Exception e) {
            e.printStackTrace();
            return BusJSONResult.errorMsg("收藏出现错误！");
        }
    }

    @PostMapping("/checkCollect")
    public BusJSONResult checkCollect(String linecode , String linenum , String userid){
        try {
            List<Collects> list=collectsService.selectByUserId(Integer.parseInt(userid));
            for(Collects co : list){
                if(co.getBuslinenum().equals(linenum) && co.getBuslinecode().equals(linecode)){
                    return BusJSONResult.ok(co.getCollects());
                }
            }
            return BusJSONResult.ok(false);
        } catch (Exception e) {
            e.printStackTrace();
            return BusJSONResult.errorMsg("error");
        }

    }
}
