package com.client.controller;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.chengxusheji.po.UserInfo;
import com.chengxusheji.service.UserInfoService;
import com.client.service.AuthService;
import com.client.utils.HttpRequest;
import com.client.utils.JsonResult;
import com.client.utils.JsonResultBuilder;
import com.client.utils.ReturnCode;
import com.client.utils.SessionConsts;
import com.client.utils.UUIDGenerator;
import com.client.utils.UserToken;
import com.client.utils.WeixinLoginParams;

//小程序认证管理控制层
@RestController
@RequestMapping("/api/auth")
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class AuthController {

	@Resource
	private AuthService authService;

	@Resource
	private UserInfoService userInfoService;

	/**
	 * 给手机号发送短信验证码
	 * @param tel 要发送短信的手机号
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/sendSMS",method=RequestMethod.GET)
	public JsonResult sendCode(String tel) throws Exception { 
		String uid = "";  //调用短信平台的用户名
		String pwd = "";  //调用短信平台的密码 
		String code = "1234";
		String message = "【XXX】短信验证码为" + code;
		//String sendUrl = "https://api.smsbao.com/sms?u="+uid+"&p="+pwd+"&m="+tel+"&c=" + urlencode(message);
		//这里可以调用短信中心发送短信验证码哈
		authService.setTelCode(tel, code);
		return JsonResultBuilder.ok(code);
	}
	/**
	 * 新用户注册的时候更新用户昵称和头像
	 * @param nickName
	 * @param avatar
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/register",method=RequestMethod.GET)
	public JsonResult register(String nickName,String avatar,HttpServletRequest request) throws Exception {
		//通过accessToken获取到用户信息
		String userName = authService.getUserName(request);
		if(userName == null) 
			return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
		UserInfo userInfo = userInfoService.getUserInfo(userName);
		userInfo.setName(nickName);
		userInfo.setUserPhoto("images/avatar/avatar" + avatar + ".png");
		userInfoService.updateUserInfo(userInfo); 
		return JsonResultBuilder.ok(); 
	}


	/**
	 * 小程序客户端用户请求登录，登录成功返回Token
	 * @param params
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/appLogin",method=RequestMethod.POST)
	public JsonResult login(String username) throws Exception {
		try {
			if(username == null) return JsonResultBuilder.error(ReturnCode.USER_NOT_LOGIN);
			UserInfo userInfo = userInfoService.getUserInfo(username);
			if(userInfo == null) 
				return JsonResultBuilder.error(ReturnCode.USER_NOT_LOGIN);
			//生成用户Token，将用户Token和用户信息关联起来，放入缓存
			UserToken userToken = new UserToken();
			userToken.setUserName(userInfo.getUser_name());
			String authToken = UUIDGenerator.generate();
			if (authService.setUserToken(authToken, userToken))
				return JsonResultBuilder.ok(SessionConsts.AUTH_TOKEN_KEY, authToken);
			else
				return JsonResultBuilder.error(ReturnCode.CACHE_SET_ERROR);
		} catch (Exception ex) {
			return JsonResultBuilder.error(ReturnCode.FAIL_LOGIN);
		}
	}

	/**
	 * 客户端输入手机号和验证码注册或者登陆系统
	 * @param tell
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	public JsonResult login(String tell,String code) throws Exception {
		String tel_code = authService.getTelCode(tell);
		if(tel_code == null || !tel_code.equals(code)) {
			return JsonResultBuilder.error(ReturnCode.CODE_ERROR);
		}
		boolean NEW_USER_FLAG = false;
		UserInfo userInfo = userInfoService.getUserInfo(tell);
		if(userInfo == null) { 
			NEW_USER_FLAG = true;
			userInfo = new UserInfo();
			
			userInfo.setUser_name(tell);
			userInfo.setPassword("123456");
			userInfo.setName("小萌新");
			userInfo.setGender("男");
			userInfo.setUserPhoto("upload/NoImage.jpg");
			userInfo.setBirthDate("2020-12-21");
			userInfo.setTelephone(tell);
			userInfo.setEmail("");
			userInfo.setAddress("");
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			userInfo.setRegTime(sdf.format(new java.util.Date())); 
			
			userInfoService.addUserInfo(userInfo);
			userInfoService.updateUserInfoOpenid(userInfo.getUser_name(), "--"); 
		} 
		//生成用户Token
		String authToken = UUIDGenerator.generate();
		UserToken userToken = new UserToken();
		userToken.setUserName(userInfo.getUser_name());
		//将用户Token和用户信息关联起来，放入缓存
		if (authService.setUserToken(authToken, userToken)) {
			Map<String,String> resultMap = new HashMap<String,String>();
			resultMap.put(SessionConsts.AUTH_TOKEN_KEY, authToken);
			resultMap.put("new_flag", NEW_USER_FLAG?"1":"0"); 
			return JsonResultBuilder.ok(resultMap);
		} 
		else
			return JsonResultBuilder.error(ReturnCode.CACHE_SET_ERROR);
	}

	/**
	 * 获取用户信息
	 * @throws Exception
	 */
	@RequestMapping(value="/userInfo",method=RequestMethod.GET)
	public JsonResult getUserInfo(HttpServletRequest request) throws Exception {

		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName == null) 
			return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
		else {
			UserInfo userInfo = userInfoService.getUserInfo(userName);
			return JsonResultBuilder.ok(userInfo);
		}
	}

	/**
	 * 退出系统
	 * @throws Exception 
	 */
	@RequestMapping(value="/logout",method=RequestMethod.GET)
	public JsonResult logout(HttpServletRequest request) throws Exception {
		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName != null) 
			authService.removeUserToken(userName);

			return JsonResultBuilder.ok();
	}

	/**
	 * 小程序客户端用户注册
	 * @param avatarUrl 头像地址
	 * @param genDer 性别：1代表男
	 * @param nickName 昵称
	 * @param tel  手机号
	 * @param jsCode 获取微信openid的凭证
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="/register_weixin",method=RequestMethod.POST)
	public JsonResult register(String avatarUrl,String genDer,String nickName,String tel,String jsCode,HttpServletRequest request) throws Exception {
		//获取小程序用户的微信唯一标识openid
		String openId = authService.getOpenId(jsCode);
		//将微信身份标识和用户表进行关联
		UserInfo userInfo = new UserInfo();
		String baseDir = "/upload"; /**保存图像目录路径**/
		String realPathDir = request.getSession().getServletContext().getRealPath(baseDir);  
		String fileName = UUIDGenerator.generate();
		String savePath = realPathDir +  File.separator + fileName;
		HttpRequest.downImage(avatarUrl.replace("https://", "http://"), savePath);
		//用户信息初始化，包括用户头像，昵称，电话号码，性别等
		userInfo.setUser_name(tel);
		//userInfo.setPhoto("upload/" + fileName);
		//userInfo.setSex(genDer.equals("1")?"男":"女");
		userInfoService.addUserInfo(userInfo);
		userInfoService.updateUserInfoOpenid(userInfo.getUser_name(), openId);

		//生成用户Token
		String authToken = UUIDGenerator.generate();
		UserToken userToken = new UserToken();
		userToken.setUserName(userInfo.getUser_name());
		//将用户Token和用户信息关联起来，放入缓存
		if (authService.setUserToken(authToken, userToken))
			return JsonResultBuilder.ok(SessionConsts.AUTH_TOKEN_KEY, authToken);
		else
			return JsonResultBuilder.error(ReturnCode.CACHE_SET_ERROR);
	}

}

