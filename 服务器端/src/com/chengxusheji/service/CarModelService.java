package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.CarModel;

import com.chengxusheji.mapper.CarModelMapper;
@Service
public class CarModelService {

	@Resource CarModelMapper carModelMapper;
    /*每页显示记录数目*/
    private int rows = 10;;
    public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

    /*保存查询后总的页数*/
    private int totalPage;
    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }
    public int getTotalPage() {
        return totalPage;
    }

    /*保存查询到的总记录数*/
    private int recordNumber;
    public void setRecordNumber(int recordNumber) {
        this.recordNumber = recordNumber;
    }
    public int getRecordNumber() {
        return recordNumber;
    }

    /*添加车型记录*/
    public void addCarModel(CarModel carModel) throws Exception {
    	carModelMapper.addCarModel(carModel);
    }

    /*按照查询条件分页查询车型记录*/
    public ArrayList<CarModel> queryCarModel(int currentPage) throws Exception { 
     	String where = "where 1=1";
    	int startIndex = (currentPage-1) * this.rows;
    	return carModelMapper.queryCarModel(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<CarModel> queryCarModel() throws Exception  { 
     	String where = "where 1=1";
    	return carModelMapper.queryCarModelList(where);
    }

    /*查询所有车型记录*/
    public ArrayList<CarModel> queryAllCarModel()  throws Exception {
        return carModelMapper.queryCarModelList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber() throws Exception {
     	String where = "where 1=1";
        recordNumber = carModelMapper.queryCarModelCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取车型记录*/
    public CarModel getCarModel(int modelId) throws Exception  {
        CarModel carModel = carModelMapper.getCarModel(modelId);
        return carModel;
    }

    /*更新车型记录*/
    public void updateCarModel(CarModel carModel) throws Exception {
        carModelMapper.updateCarModel(carModel);
    }

    /*删除一条车型记录*/
    public void deleteCarModel (int modelId) throws Exception {
        carModelMapper.deleteCarModel(modelId);
    }

    /*删除多条车型信息*/
    public int deleteCarModels (String modelIds) throws Exception {
    	String _modelIds[] = modelIds.split(",");
    	for(String _modelId: _modelIds) {
    		carModelMapper.deleteCarModel(Integer.parseInt(_modelId));
    	}
    	return _modelIds.length;
    }
}
