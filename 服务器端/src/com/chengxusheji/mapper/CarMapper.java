package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.Car;

public interface CarMapper {
	/*添加车辆信息*/
	public void addCar(Car car) throws Exception;

	/*按照查询条件分页查询车辆记录*/
	public ArrayList<Car> queryCar(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有车辆记录*/
	public ArrayList<Car> queryCarList(@Param("where") String where) throws Exception;

	/*按照查询条件的车辆记录数*/
	public int queryCarCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条车辆记录*/
	public Car getCar(int carId) throws Exception;

	/*更新车辆记录*/
	public void updateCar(Car car) throws Exception;

	/*删除车辆记录*/
	public void deleteCar(int carId) throws Exception;

}
