package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.Region;

import com.chengxusheji.mapper.RegionMapper;
@Service
public class RegionService {

	@Resource RegionMapper regionMapper;
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

    /*添加区域记录*/
    public void addRegion(Region region) throws Exception {
    	regionMapper.addRegion(region);
    }

    /*按照查询条件分页查询区域记录*/
    public ArrayList<Region> queryRegion(String regionFloor,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(!regionFloor.equals("")) where = where + " and t_region.regionFloor like '%" + regionFloor + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return regionMapper.queryRegion(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<Region> queryRegion(String regionFloor) throws Exception  { 
     	String where = "where 1=1";
    	if(!regionFloor.equals("")) where = where + " and t_region.regionFloor like '%" + regionFloor + "%'";
    	return regionMapper.queryRegionList(where);
    }

    /*查询所有区域记录*/
    public ArrayList<Region> queryAllRegion()  throws Exception {
        return regionMapper.queryRegionList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(String regionFloor) throws Exception {
     	String where = "where 1=1";
    	if(!regionFloor.equals("")) where = where + " and t_region.regionFloor like '%" + regionFloor + "%'";
        recordNumber = regionMapper.queryRegionCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取区域记录*/
    public Region getRegion(int regionId) throws Exception  {
        Region region = regionMapper.getRegion(regionId);
        return region;
    }

    /*更新区域记录*/
    public void updateRegion(Region region) throws Exception {
        regionMapper.updateRegion(region);
    }

    /*删除一条区域记录*/
    public void deleteRegion (int regionId) throws Exception {
        regionMapper.deleteRegion(regionId);
    }

    /*删除多条区域信息*/
    public int deleteRegions (String regionIds) throws Exception {
    	String _regionIds[] = regionIds.split(",");
    	for(String _regionId: _regionIds) {
    		regionMapper.deleteRegion(Integer.parseInt(_regionId));
    	}
    	return _regionIds.length;
    }
}
