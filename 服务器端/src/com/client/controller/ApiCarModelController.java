package com.client.controller;

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
import com.chengxusheji.po.CarModel;
import com.chengxusheji.service.CarModelService;
import com.client.service.AuthService;
import com.client.utils.JsonResult;
import com.client.utils.JsonResultBuilder;
import com.client.utils.ReturnCode;

@RestController
@RequestMapping("/api/carModel") 
public class ApiCarModelController {
	@Resource CarModelService carModelService;
	@Resource AuthService authService;

	@InitBinder("carModel")
	public void initBinderCarModel(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("carModel.");
	}

	/*客户端ajax方式添加车型信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult add(@Validated CarModel carModel, BindingResult br, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName == null) return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
		if (br.hasErrors()) //验证输入参数
			return JsonResultBuilder.error(ReturnCode.INPUT_PARAM_ERROR);
        carModelService.addCarModel(carModel); //添加到数据库
        return JsonResultBuilder.ok();
	}

	/*客户端ajax更新车型信息*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(@Validated CarModel carModel, BindingResult br, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName == null) return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
		if (br.hasErrors())  //验证输入参数
			return JsonResultBuilder.error(ReturnCode.INPUT_PARAM_ERROR); 
        carModelService.updateCarModel(carModel);  //更新记录到数据库
        return JsonResultBuilder.ok(carModelService.getCarModel(carModel.getModelId()));
	}

	/*ajax方式显示获取车型详细信息*/
	@RequestMapping(value="/get/{modelId}",method=RequestMethod.POST)
	public JsonResult getCarModel(@PathVariable int modelId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键modelId获取CarModel对象*/
        CarModel carModel = carModelService.getCarModel(modelId); 
        return JsonResultBuilder.ok(carModel);
	}

	/*ajax方式删除车型记录*/
	@RequestMapping(value="/delete/{modelId}",method=RequestMethod.POST)
	public JsonResult deleteCarModel(@PathVariable int modelId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName == null) return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
		try {
			carModelService.deleteCarModel(modelId);
			return JsonResultBuilder.ok();
		} catch (Exception ex) {
			return JsonResultBuilder.error(ReturnCode.FOREIGN_KEY_CONSTRAINT_ERROR);
		}
	}

	//客户端查询车型信息
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public JsonResult list(Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if(rows != 0)carModelService.setRows(rows);
		List<CarModel> carModelList = carModelService.queryCarModel(page);
	    /*计算总的页数和总的记录数*/
	    carModelService.queryTotalPageAndRecordNumber();
	    /*获取到总的页码数目*/
	    int totalPage = carModelService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = carModelService.getRecordNumber();
	    HashMap<String, Object> resultMap = new HashMap<String, Object>();
	    resultMap.put("totalPage", totalPage);
	    resultMap.put("list", carModelList);
	    return JsonResultBuilder.ok(resultMap);
	}

	//客户端ajax获取所有车型
	@RequestMapping(value="/listAll",method=RequestMethod.POST)
	public JsonResult listAll() throws Exception{
		List<CarModel> carModelList = carModelService.queryAllCarModel(); 
		return JsonResultBuilder.ok(carModelList);
	}
}

