package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.SpaceInfo;

public interface SpaceInfoMapper {
	/*添加停车位信息*/
	public void addSpaceInfo(SpaceInfo spaceInfo) throws Exception;

	/*按照查询条件分页查询停车位记录*/
	public ArrayList<SpaceInfo> querySpaceInfo(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有停车位记录*/
	public ArrayList<SpaceInfo> querySpaceInfoList(@Param("where") String where) throws Exception;

	/*按照查询条件的停车位记录数*/
	public int querySpaceInfoCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条停车位记录*/
	public SpaceInfo getSpaceInfo(int spaceId) throws Exception;

	/*更新停车位记录*/
	public void updateSpaceInfo(SpaceInfo spaceInfo) throws Exception;

	/*删除停车位记录*/
	public void deleteSpaceInfo(int spaceId) throws Exception;

}
