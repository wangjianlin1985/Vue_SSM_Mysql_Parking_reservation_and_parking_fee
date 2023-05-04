package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.Region;

public interface RegionMapper {
	/*添加区域信息*/
	public void addRegion(Region region) throws Exception;

	/*按照查询条件分页查询区域记录*/
	public ArrayList<Region> queryRegion(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有区域记录*/
	public ArrayList<Region> queryRegionList(@Param("where") String where) throws Exception;

	/*按照查询条件的区域记录数*/
	public int queryRegionCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条区域记录*/
	public Region getRegion(int regionId) throws Exception;

	/*更新区域记录*/
	public void updateRegion(Region region) throws Exception;

	/*删除区域记录*/
	public void deleteRegion(int regionId) throws Exception;

}
