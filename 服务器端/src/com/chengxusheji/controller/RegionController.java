package com.chengxusheji.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.chengxusheji.utils.ExportExcelUtil;
import com.chengxusheji.utils.UserException;
import com.chengxusheji.service.RegionService;
import com.chengxusheji.po.Region;

//Region管理控制层
@Controller
@RequestMapping("/Region")
public class RegionController extends BaseController {

    /*业务层对象*/
    @Resource RegionService regionService;

	@InitBinder("region")
	public void initBinderRegion(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("region.");
	}
	/*跳转到添加Region视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new Region());
		return "Region_add";
	}

	/*客户端ajax方式提交添加区域信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated Region region, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
        regionService.addRegion(region);
        message = "区域添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	/*ajax方式按照查询条件分页查询区域信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(String regionFloor,Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
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
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(Region region:regionList) {
			JSONObject jsonRegion = region.getJsonObject();
			jsonArray.put(jsonRegion);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}

	/*ajax方式按照查询条件分页查询区域信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<Region> regionList = regionService.queryAllRegion();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(Region region:regionList) {
			JSONObject jsonRegion = new JSONObject();
			jsonRegion.accumulate("regionId", region.getRegionId());
			jsonRegion.accumulate("regionName", region.getRegionName());
			jsonArray.put(jsonRegion);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询区域信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(String regionFloor,Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		if (regionFloor == null) regionFloor = "";
		List<Region> regionList = regionService.queryRegion(regionFloor, currentPage);
	    /*计算总的页数和总的记录数*/
	    regionService.queryTotalPageAndRecordNumber(regionFloor);
	    /*获取到总的页码数目*/
	    int totalPage = regionService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = regionService.getRecordNumber();
	    request.setAttribute("regionList",  regionList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
	    request.setAttribute("regionFloor", regionFloor);
		return "Region/region_frontquery_result"; 
	}

     /*前台查询Region信息*/
	@RequestMapping(value="/{regionId}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable Integer regionId,Model model,HttpServletRequest request) throws Exception {
		/*根据主键regionId获取Region对象*/
        Region region = regionService.getRegion(regionId);

        request.setAttribute("region",  region);
        return "Region/region_frontshow";
	}

	/*ajax方式显示区域修改jsp视图页*/
	@RequestMapping(value="/{regionId}/update",method=RequestMethod.GET)
	public void update(@PathVariable Integer regionId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键regionId获取Region对象*/
        Region region = regionService.getRegion(regionId);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonRegion = region.getJsonObject();
		out.println(jsonRegion.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新区域信息*/
	@RequestMapping(value = "/{regionId}/update", method = RequestMethod.POST)
	public void update(@Validated Region region, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		try {
			regionService.updateRegion(region);
			message = "区域更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "区域更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除区域信息*/
	@RequestMapping(value="/{regionId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Integer regionId,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  regionService.deleteRegion(regionId);
	            request.setAttribute("message", "区域删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "区域删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条区域记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String regionIds,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = regionService.deleteRegions(regionIds);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出区域信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel(String regionFloor, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        if(regionFloor == null) regionFloor = "";
        List<Region> regionList = regionService.queryRegion(regionFloor);
        ExportExcelUtil ex = new ExportExcelUtil();
        String _title = "Region信息记录"; 
        String[] headers = { "区域id","所在楼层","区域名称"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<regionList.size();i++) {
        	Region region = regionList.get(i); 
        	dataset.add(new String[]{region.getRegionId() + "",region.getRegionFloor(),region.getRegionName()});
        }
        /*
        OutputStream out = null;
		try {
			out = new FileOutputStream("C://output.xls");
			ex.exportExcel(title,headers, dataset, out);
		    out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		*/
		OutputStream out = null;//创建一个输出流对象 
		try { 
			out = response.getOutputStream();//
			response.setHeader("Content-disposition","attachment; filename="+"Region.xls");//filename是下载的xls的名，建议最好用英文 
			response.setContentType("application/msexcel;charset=UTF-8");//设置类型 
			response.setHeader("Pragma","No-cache");//设置头 
			response.setHeader("Cache-Control","no-cache");//设置头 
			response.setDateHeader("Expires", 0);//设置日期头  
			String rootPath = request.getSession().getServletContext().getRealPath("/");
			ex.exportExcel(rootPath,_title,headers, dataset, out);
			out.flush();
		} catch (IOException e) { 
			e.printStackTrace(); 
		}finally{
			try{
				if(out!=null){ 
					out.close(); 
				}
			}catch(IOException e){ 
				e.printStackTrace(); 
			} 
		}
    }
}
