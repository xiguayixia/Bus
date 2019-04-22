package com.shanshi.bus.controller;

import java.util.HashMap;
import java.util.Map;

import com.shanshi.bus.common.*;
import com.shanshi.bus.model.UserinfoTemp;
import com.shanshi.bus.model.Users;
import com.shanshi.bus.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shanshi.bus.model.WXSessionModel;

import javax.annotation.Resource;

/**
 * wx登录控制器
 */
@RestController
public class WXLoginController {
	
	@Autowired
	private RedisOperator redis;
	@Autowired
	private UsersService usersService;
	@Autowired
	private Users users;
	

	@PostMapping("/wxLogin")
	public BusJSONResult wxLogin(String code , String userinfo) {
		//code：临时会话code  userinfo：微信资料
		//微信接口：https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&grant_type=authorization_code
		try {
			Map<String, String> param = new HashMap<>();
			param.put("appid", WXLoginParam.appid);
			param.put("secret", WXLoginParam.secret);
			param.put("js_code", code);
			param.put("grant_type", WXLoginParam.grant_type);
			//wxResult为访问微信接口返回的session_key和openid
			String wxResult = HttpClientUtil.doGet(WXLoginParam.url, param);
			//System.out.println(wxResult);
			WXSessionModel model = JsonUtils.jsonToPojo(wxResult, WXSessionModel.class);
			//判断登录用户是否为第一次登录（根据openid查询数据库看是否有用户数据）
			//第一次登录就新增用户信息  否则就查出用户信息
			if(usersService.selectByOpenId(model.getOpenid())==null){
				UserinfoTemp userinfoTemp=JsonUtils.jsonToPojo(userinfo, UserinfoTemp.class);
				users.setNickname(userinfoTemp.getNickName());
				users.setGender(userinfoTemp.getGender());
				users.setAvatarurl(userinfoTemp.getAvatarUrl());
				users.setOpenid(model.getOpenid());
				usersService.insert(users);
			}else{
				users=usersService.selectByOpenId(model.getOpenid());
			}
			//openid传到前台是不安全的  这里设置对象openid为空
			users.setOpenid("");
			// 存入session到redis
			//redis.set("user-redis-session:" + model.getOpenid(),
			//					model.getSession_key(),
			//					1000 * 60 * 30);
			return BusJSONResult.ok(JsonUtils.objectToJson(users));
		} catch (Exception e) {
			return BusJSONResult.errorMsg("登录出现错误!");
		}
	}
	
}
