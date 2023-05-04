package com.client.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import org.springframework.web.bind.annotation.ModelAttribute;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.chengxusheji.po.Car;
import com.chengxusheji.po.CarModel;
import com.chengxusheji.po.UserInfo;
import com.chengxusheji.service.CarService;
import com.client.service.AuthService;
import com.client.utils.JsonResult;
import com.client.utils.JsonResultBuilder;
import com.client.utils.ReturnCode;

@RestController
@RequestMapping("/api/car") 
public class ApiCarController {
	@Resource CarService carService;
	@Resource AuthService authService;

	@InitBinder("carModelObj")
	public void initBindercarModelObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("carModelObj.");
	}
	@InitBinder("userObj")
	public void initBinderuserObj(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("userObj.");
	}
	@InitBinder("car")
	public void initBinderCar(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("car.");
	}

	/*客户端ajax方式添加车辆信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult add(Car car, BindingResult br, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName == null) return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
		
		UserInfo userObj = new UserInfo();
		userObj.setUser_name(userName);
		car.setUserObj(userObj);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		car.setAddTime(sdf.format(new java.util.Date()));
		
        carService.addCar(car); //添加到数据库
        return JsonResultBuilder.ok();
	}

	/*客户端ajax更新车辆信息*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(@Validated Car car, BindingResult br, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName == null) return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
		if (br.hasErrors())  //验证输入参数
			return JsonResultBuilder.error(ReturnCode.INPUT_PARAM_ERROR); 
        carService.updateCar(car);  //更新记录到数据库
        return JsonResultBuilder.ok(carService.getCar(car.getCarId()));
	}

	/*ajax方式显示获取车辆详细信息*/
	@RequestMapping(value="/get/{carId}",method=RequestMethod.POST)
	public JsonResult getCar(@PathVariable int carId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键carId获取Car对象*/
        Car car = carService.getCar(carId); 
        return JsonResultBuilder.ok(car);
	}

	/*ajax方式删除车辆记录*/
	@RequestMapping(value="/delete/{carId}",method=RequestMethod.POST)
	public JsonResult deleteCar(@PathVariable int carId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName == null) return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
		try {
			carService.deleteCar(carId);
			return JsonResultBuilder.ok();
		} catch (Exception ex) {
			return JsonResultBuilder.error(ReturnCode.FOREIGN_KEY_CONSTRAINT_ERROR);
		}
	}

	//客户端查询车辆信息
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public JsonResult list(String carNo,@ModelAttribute("carModelObj") CarModel carModelObj,String pinpai,String chexianriqi,@ModelAttribute("userObj") UserInfo userObj,String addTime,Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName == null) return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
				
		if (page==null || page == 0) page = 1;
		if (carNo == null) carNo = "";
		if (pinpai == null) pinpai = "";
		if (chexianriqi == null) chexianriqi = "";
		if (addTime == null) addTime = "";
		if(rows != 0)carService.setRows(rows);
		userObj = new UserInfo();
		userObj.setUser_name(userName);
		
		List<Car> carList = carService.queryCar(carNo, carModelObj, pinpai, chexianriqi, userObj, addTime, page);
	    /*计算总的页数和总的记录数*/
	    carService.queryTotalPageAndRecordNumber(carNo, carModelObj, pinpai, chexianriqi, userObj, addTime);
	    /*获取到总的页码数目*/
	    int totalPage = carService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = carService.getRecordNumber();
	    HashMap<String, Object> resultMap = new HashMap<String, Object>();
	    resultMap.put("totalPage", totalPage);
	    resultMap.put("list", carList);
	    return JsonResultBuilder.ok(resultMap);
	}

	//客户端ajax获取所有车辆
	@RequestMapping(value="/listAll",method=RequestMethod.POST)
	public JsonResult listAll() throws Exception{
		List<Car> carList = carService.queryAllCar(); 
		return JsonResultBuilder.ok(carList);
	}
}

