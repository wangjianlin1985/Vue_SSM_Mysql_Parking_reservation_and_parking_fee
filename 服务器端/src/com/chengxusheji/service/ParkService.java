package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.Car;
import com.chengxusheji.po.UserInfo;
import com.chengxusheji.po.SpaceInfo;
import com.chengxusheji.po.Park;

import com.chengxusheji.mapper.ParkMapper;
@Service
public class ParkService {

	@Resource ParkMapper parkMapper;
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

    /*添加车辆停车记录*/
    public void addPark(Park park) throws Exception {
    	parkMapper.addPark(park);
    }

    /*按照查询条件分页查询车辆停车记录*/
    public ArrayList<Park> queryPark(Car carObj,UserInfo userObj,SpaceInfo spaceObj,String startTime,String endTime,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(null != carObj && carObj.getCarId()!= null && carObj.getCarId()!= 0)  where += " and t_park.carObj=" + carObj.getCarId();
    	if(null != userObj &&  userObj.getUser_name() != null  && !userObj.getUser_name().equals(""))  where += " and t_park.userObj='" + userObj.getUser_name() + "'";
    	if(null != spaceObj && spaceObj.getSpaceId()!= null && spaceObj.getSpaceId()!= 0)  where += " and t_park.spaceObj=" + spaceObj.getSpaceId();
    	if(!startTime.equals("")) where = where + " and t_park.startTime like '%" + startTime + "%'";
    	if(!endTime.equals("")) where = where + " and t_park.endTime like '%" + endTime + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return parkMapper.queryPark(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<Park> queryPark(Car carObj,UserInfo userObj,SpaceInfo spaceObj,String startTime,String endTime) throws Exception  { 
     	String where = "where 1=1";
    	if(null != carObj && carObj.getCarId()!= null && carObj.getCarId()!= 0)  where += " and t_park.carObj=" + carObj.getCarId();
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_park.userObj='" + userObj.getUser_name() + "'";
    	if(null != spaceObj && spaceObj.getSpaceId()!= null && spaceObj.getSpaceId()!= 0)  where += " and t_park.spaceObj=" + spaceObj.getSpaceId();
    	if(!startTime.equals("")) where = where + " and t_park.startTime like '%" + startTime + "%'";
    	if(!endTime.equals("")) where = where + " and t_park.endTime like '%" + endTime + "%'";
    	return parkMapper.queryParkList(where);
    }

    /*查询所有车辆停车记录*/
    public ArrayList<Park> queryAllPark()  throws Exception {
        return parkMapper.queryParkList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(Car carObj,UserInfo userObj,SpaceInfo spaceObj,String startTime,String endTime) throws Exception {
     	String where = "where 1=1";
    	if(null != carObj && carObj.getCarId()!= null && carObj.getCarId()!= 0)  where += " and t_park.carObj=" + carObj.getCarId();
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_park.userObj='" + userObj.getUser_name() + "'";
    	if(null != spaceObj && spaceObj.getSpaceId()!= null && spaceObj.getSpaceId()!= 0)  where += " and t_park.spaceObj=" + spaceObj.getSpaceId();
    	if(!startTime.equals("")) where = where + " and t_park.startTime like '%" + startTime + "%'";
    	if(!endTime.equals("")) where = where + " and t_park.endTime like '%" + endTime + "%'";
        recordNumber = parkMapper.queryParkCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取车辆停车记录*/
    public Park getPark(int parkId) throws Exception  {
        Park park = parkMapper.getPark(parkId);
        return park;
    }

    /*更新车辆停车记录*/
    public void updatePark(Park park) throws Exception {
        parkMapper.updatePark(park);
    }

    /*删除一条车辆停车记录*/
    public void deletePark (int parkId) throws Exception {
        parkMapper.deletePark(parkId);
    }

    /*删除多条车辆停车信息*/
    public int deleteParks (String parkIds) throws Exception {
    	String _parkIds[] = parkIds.split(",");
    	for(String _parkId: _parkIds) {
    		parkMapper.deletePark(Integer.parseInt(_parkId));
    	}
    	return _parkIds.length;
    }
}
