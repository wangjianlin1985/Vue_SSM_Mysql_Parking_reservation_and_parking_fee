package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.Region;
import com.chengxusheji.po.SpaceInfo;

import com.chengxusheji.mapper.SpaceInfoMapper;
@Service
public class SpaceInfoService {

	@Resource SpaceInfoMapper spaceInfoMapper;
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

    /*添加停车位记录*/
    public void addSpaceInfo(SpaceInfo spaceInfo) throws Exception {
    	spaceInfoMapper.addSpaceInfo(spaceInfo);
    }

    /*按照查询条件分页查询停车位记录*/
    public ArrayList<SpaceInfo> querySpaceInfo(Region regionObj,String spaceNo,String spaceState,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(null != regionObj && regionObj.getRegionId()!= null && regionObj.getRegionId()!= 0)  where += " and t_spaceInfo.regionObj=" + regionObj.getRegionId();
    	if(!spaceNo.equals("")) where = where + " and t_spaceInfo.spaceNo like '%" + spaceNo + "%'";
    	if(!spaceState.equals("")) where = where + " and t_spaceInfo.spaceState like '%" + spaceState + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return spaceInfoMapper.querySpaceInfo(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<SpaceInfo> querySpaceInfo(Region regionObj,String spaceNo,String spaceState) throws Exception  { 
     	String where = "where 1=1";
    	if(null != regionObj && regionObj.getRegionId()!= null && regionObj.getRegionId()!= 0)  where += " and t_spaceInfo.regionObj=" + regionObj.getRegionId();
    	if(!spaceNo.equals("")) where = where + " and t_spaceInfo.spaceNo like '%" + spaceNo + "%'";
    	if(!spaceState.equals("")) where = where + " and t_spaceInfo.spaceState like '%" + spaceState + "%'";
    	return spaceInfoMapper.querySpaceInfoList(where);
    }

    /*查询所有停车位记录*/
    public ArrayList<SpaceInfo> queryAllSpaceInfo()  throws Exception {
        return spaceInfoMapper.querySpaceInfoList("where 1=1");
    }
    
    /*查询所有停车位记录*/
    public ArrayList<SpaceInfo> queryAllIdleSpaceInfo()  throws Exception {
        return spaceInfoMapper.querySpaceInfoList("where 1=1 and t_spaceInfo.spaceState='空闲中'");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(Region regionObj,String spaceNo,String spaceState) throws Exception {
     	String where = "where 1=1";
    	if(null != regionObj && regionObj.getRegionId()!= null && regionObj.getRegionId()!= 0)  where += " and t_spaceInfo.regionObj=" + regionObj.getRegionId();
    	if(!spaceNo.equals("")) where = where + " and t_spaceInfo.spaceNo like '%" + spaceNo + "%'";
    	if(!spaceState.equals("")) where = where + " and t_spaceInfo.spaceState like '%" + spaceState + "%'";
        recordNumber = spaceInfoMapper.querySpaceInfoCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取停车位记录*/
    public SpaceInfo getSpaceInfo(int spaceId) throws Exception  {
        SpaceInfo spaceInfo = spaceInfoMapper.getSpaceInfo(spaceId);
        return spaceInfo;
    }

    /*更新停车位记录*/
    public void updateSpaceInfo(SpaceInfo spaceInfo) throws Exception {
        spaceInfoMapper.updateSpaceInfo(spaceInfo);
    }

    /*删除一条停车位记录*/
    public void deleteSpaceInfo (int spaceId) throws Exception {
        spaceInfoMapper.deleteSpaceInfo(spaceId);
    }

    /*删除多条停车位信息*/
    public int deleteSpaceInfos (String spaceIds) throws Exception {
    	String _spaceIds[] = spaceIds.split(",");
    	for(String _spaceId: _spaceIds) {
    		spaceInfoMapper.deleteSpaceInfo(Integer.parseInt(_spaceId));
    	}
    	return _spaceIds.length;
    }
}
