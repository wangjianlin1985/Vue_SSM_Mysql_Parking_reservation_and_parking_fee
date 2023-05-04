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
import com.chengxusheji.service.CarModelService;
import com.chengxusheji.po.CarModel;

//CarModel管理控制层
@Controller
@RequestMapping("/CarModel")
public class CarModelController extends BaseController {

    /*业务层对象*/
    @Resource CarModelService carModelService;

	@InitBinder("carModel")
	public void initBinderCarModel(WebDataBinder binder) {
		binder.setFieldDefaultPrefix("carModel.");
	}
	/*跳转到添加CarModel视图*/
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String add(Model model,HttpServletRequest request) throws Exception {
		model.addAttribute(new CarModel());
		return "CarModel_add";
	}

	/*客户端ajax方式提交添加车型信息*/
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public void add(@Validated CarModel carModel, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
		boolean success = false;
		if (br.hasErrors()) {
			message = "输入信息不符合要求！";
			writeJsonResponse(response, success, message);
			return ;
		}
        carModelService.addCarModel(carModel);
        message = "车型添加成功!";
        success = true;
        writeJsonResponse(response, success, message);
	}
	/*ajax方式按照查询条件分页查询车型信息*/
	@RequestMapping(value = { "/list" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void list(Integer page,Integer rows, Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		if (page==null || page == 0) page = 1;
		if(rows != 0)carModelService.setRows(rows);
		List<CarModel> carModelList = carModelService.queryCarModel(page);
	    /*计算总的页数和总的记录数*/
	    carModelService.queryTotalPageAndRecordNumber();
	    /*获取到总的页码数目*/
	    int totalPage = carModelService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = carModelService.getRecordNumber();
        response.setContentType("text/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象
		JSONObject jsonObj=new JSONObject();
		jsonObj.accumulate("total", recordNumber);
		JSONArray jsonArray = new JSONArray();
		for(CarModel carModel:carModelList) {
			JSONObject jsonCarModel = carModel.getJsonObject();
			jsonArray.put(jsonCarModel);
		}
		jsonObj.accumulate("rows", jsonArray);
		out.println(jsonObj.toString());
		out.flush();
		out.close();
	}

	/*ajax方式按照查询条件分页查询车型信息*/
	@RequestMapping(value = { "/listAll" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void listAll(HttpServletResponse response) throws Exception {
		List<CarModel> carModelList = carModelService.queryAllCarModel();
        response.setContentType("text/json;charset=UTF-8"); 
		PrintWriter out = response.getWriter();
		JSONArray jsonArray = new JSONArray();
		for(CarModel carModel:carModelList) {
			JSONObject jsonCarModel = new JSONObject();
			jsonCarModel.accumulate("modelId", carModel.getModelId());
			jsonCarModel.accumulate("modelName", carModel.getModelName());
			jsonArray.put(jsonCarModel);
		}
		out.println(jsonArray.toString());
		out.flush();
		out.close();
	}

	/*前台按照查询条件分页查询车型信息*/
	@RequestMapping(value = { "/frontlist" }, method = {RequestMethod.GET,RequestMethod.POST})
	public String frontlist(Integer currentPage, Model model, HttpServletRequest request) throws Exception  {
		if (currentPage==null || currentPage == 0) currentPage = 1;
		List<CarModel> carModelList = carModelService.queryCarModel(currentPage);
	    /*计算总的页数和总的记录数*/
	    carModelService.queryTotalPageAndRecordNumber();
	    /*获取到总的页码数目*/
	    int totalPage = carModelService.getTotalPage();
	    /*当前查询条件下总记录数*/
	    int recordNumber = carModelService.getRecordNumber();
	    request.setAttribute("carModelList",  carModelList);
	    request.setAttribute("totalPage", totalPage);
	    request.setAttribute("recordNumber", recordNumber);
	    request.setAttribute("currentPage", currentPage);
		return "CarModel/carModel_frontquery_result"; 
	}

     /*前台查询CarModel信息*/
	@RequestMapping(value="/{modelId}/frontshow",method=RequestMethod.GET)
	public String frontshow(@PathVariable Integer modelId,Model model,HttpServletRequest request) throws Exception {
		/*根据主键modelId获取CarModel对象*/
        CarModel carModel = carModelService.getCarModel(modelId);

        request.setAttribute("carModel",  carModel);
        return "CarModel/carModel_frontshow";
	}

	/*ajax方式显示车型修改jsp视图页*/
	@RequestMapping(value="/{modelId}/update",method=RequestMethod.GET)
	public void update(@PathVariable Integer modelId,Model model,HttpServletRequest request,HttpServletResponse response) throws Exception {
        /*根据主键modelId获取CarModel对象*/
        CarModel carModel = carModelService.getCarModel(modelId);

        response.setContentType("text/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
		//将要被返回到客户端的对象 
		JSONObject jsonCarModel = carModel.getJsonObject();
		out.println(jsonCarModel.toString());
		out.flush();
		out.close();
	}

	/*ajax方式更新车型信息*/
	@RequestMapping(value = "/{modelId}/update", method = RequestMethod.POST)
	public void update(@Validated CarModel carModel, BindingResult br,
			Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
		String message = "";
    	boolean success = false;
		if (br.hasErrors()) { 
			message = "输入的信息有错误！";
			writeJsonResponse(response, success, message);
			return;
		}
		try {
			carModelService.updateCarModel(carModel);
			message = "车型更新成功!";
			success = true;
			writeJsonResponse(response, success, message);
		} catch (Exception e) {
			e.printStackTrace();
			message = "车型更新失败!";
			writeJsonResponse(response, success, message); 
		}
	}
    /*删除车型信息*/
	@RequestMapping(value="/{modelId}/delete",method=RequestMethod.GET)
	public String delete(@PathVariable Integer modelId,HttpServletRequest request) throws UnsupportedEncodingException {
		  try {
			  carModelService.deleteCarModel(modelId);
	            request.setAttribute("message", "车型删除成功!");
	            return "message";
	        } catch (Exception e) { 
	            e.printStackTrace();
	            request.setAttribute("error", "车型删除失败!");
				return "error";

	        }

	}

	/*ajax方式删除多条车型记录*/
	@RequestMapping(value="/deletes",method=RequestMethod.POST)
	public void delete(String modelIds,HttpServletRequest request,HttpServletResponse response) throws IOException, JSONException {
		String message = "";
    	boolean success = false;
        try { 
        	int count = carModelService.deleteCarModels(modelIds);
        	success = true;
        	message = count + "条记录删除成功";
        	writeJsonResponse(response, success, message);
        } catch (Exception e) { 
            //e.printStackTrace();
            message = "有记录存在外键约束,删除失败";
            writeJsonResponse(response, success, message);
        }
	}

	/*按照查询条件导出车型信息到Excel*/
	@RequestMapping(value = { "/OutToExcel" }, method = {RequestMethod.GET,RequestMethod.POST})
	public void OutToExcel( Model model, HttpServletRequest request,HttpServletResponse response) throws Exception {
        List<CarModel> carModelList = carModelService.queryCarModel();
        ExportExcelUtil ex = new ExportExcelUtil();
        String _title = "CarModel信息记录"; 
        String[] headers = { "车型id","车型名称"};
        List<String[]> dataset = new ArrayList<String[]>(); 
        for(int i=0;i<carModelList.size();i++) {
        	CarModel carModel = carModelList.get(i); 
        	dataset.add(new String[]{carModel.getModelId() + "",carModel.getModelName()});
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
			response.setHeader("Content-disposition","attachment; filename="+"CarModel.xls");//filename是下载的xls的名，建议最好用英文 
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
