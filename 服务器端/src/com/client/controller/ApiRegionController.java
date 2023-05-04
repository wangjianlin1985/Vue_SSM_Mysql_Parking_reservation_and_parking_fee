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
import com.chengxusheji.po.Region;
import com.chengxusheji.service.RegionService;
import com.client.service.AuthService;
import com.client.utils.JsonResult;
import com.client.utils.JsonResultBuilder;
import com.client.utils.ReturnCode;

@RestController
@RequestMapping("/api/region") 
public class ApiRegionController {
	@Resource RegionService regionService;
	@Resource AuthService authService;

	@InitBinder("region")
	public void initBinderRegion(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("region.");
	}

	/*客户端ajax方式添加区域信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public JsonResult add(@Validated Region region, BindingResult br, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName == null) return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
		if (br.hasErrors()) //验证输入参数
			return JsonResultBuilder.error(ReturnCode.INPUT_PARAM_ERROR);
        regionService.addRegion(region); //添加到数据库
        return JsonResultBuilder.ok();
	}

	/*客户端ajax更新区域信息*/
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public JsonResult update(@Validated Region region, BindingResult br, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName == null) return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
		if (br.hasErrors())  //验证输入参数
			return JsonResultBuilder.error(ReturnCode.INPUT_PARAM_ERROR); 
        regionService.updateRegion(region);  //更新记录到数据库
        return JsonResultBuilder.ok(regionService.getRegion(region.getRegionId()));
	}

	/*ajax方式显示获取区域详细信息*/
	@RequestMapping(value="/get/{regionId}",method=RequestMethod.POST)
	public JsonResult getRegion(@PathVariable int regionId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键regionId获取Region对象*/
        Region region = regionService.getRegion(regionId); 
        return JsonResultBuilder.ok(region);
	}

	/*ajax方式删除区域记录*/
	@RequestMapping(value="/delete/{regionId}",method=RequestMethod.POST)
	public JsonResult deleteRegion(@PathVariable int regionId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
		//通过accessToken获取到用户信息 
		String userName = authService.getUserName(request);
		if(userName == null) return JsonResultBuilder.error(ReturnCode.TOKEN_VALID_ERROR);
		try {
			regionService.deleteRegion(regionId);
			return JsonResultBuilder.ok();
		} catch (Exception ex) {
			return JsonResultBuilder.error(ReturnCode.FOREIGN_KEY_CONSTRAINT_ERROR);
		}
	}

	//客户端查询区域信息
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public JsonResult list(String regionFloor,Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if (regionFloor == null) regionFloor = "";
		if(rows != 0)regionService.setRows(rows);
		List<Region> regionList = regionService.queryRegion(regionFloor, page);
	    /*计算总的页数和总的记录数*/
	    regionService.queryTotalPageAndRecordNumber(regionFloor);
	    /*获取到总的页码数目*/
	    int totalPage = regionService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = regionService.getRecordNumber();
	    HashMap<String, Object> resultMap = new HashMap<String, Object>();
	    resultMap.put("totalPage", totalPage);
	    resultMap.put("list", regionList);
	    return JsonResultBuilder.ok(resultMap);
	}

	//客户端ajax获取所有区域
	@RequestMapping(value="/listAll",method=RequestMethod.POST)
	public JsonResult listAll() throws Exception{
		List<Region> regionList = regionService.queryAllRegion(); 
		return JsonResultBuilder.ok(regionList);
	}
}

