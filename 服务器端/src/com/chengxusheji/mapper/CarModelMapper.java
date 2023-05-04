package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.CarModel;

public interface CarModelMapper {
	/*添加车型信息*/
	public void addCarModel(CarModel carModel) throws Exception;

	/*按照查询条件分页查询车型记录*/
	public ArrayList<CarModel> queryCarModel(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有车型记录*/
	public ArrayList<CarModel> queryCarModelList(@Param("where") String where) throws Exception;

	/*按照查询条件的车型记录数*/
	public int queryCarModelCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条车型记录*/
	public CarModel getCarModel(int modelId) throws Exception;

	/*更新车型记录*/
	public void updateCarModel(CarModel carModel) throws Exception;

	/*删除车型记录*/
	public void deleteCarModel(int modelId) throws Exception;

}
