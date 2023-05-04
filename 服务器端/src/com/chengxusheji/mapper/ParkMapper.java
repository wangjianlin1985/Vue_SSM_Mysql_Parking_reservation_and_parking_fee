package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.Park;

public interface ParkMapper {
	/*添加车辆停车信息*/
	public void addPark(Park park) throws Exception;

	/*按照查询条件分页查询车辆停车记录*/
	public ArrayList<Park> queryPark(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有车辆停车记录*/
	public ArrayList<Park> queryParkList(@Param("where") String where) throws Exception;

	/*按照查询条件的车辆停车记录数*/
	public int queryParkCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条车辆停车记录*/
	public Park getPark(int parkId) throws Exception;

	/*更新车辆停车记录*/
	public void updatePark(Park park) throws Exception;

	/*删除车辆停车记录*/
	public void deletePark(int parkId) throws Exception;

}
