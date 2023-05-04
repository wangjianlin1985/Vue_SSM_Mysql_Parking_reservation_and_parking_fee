package com.chengxusheji.service;

import java.util.ArrayList;
import javax.annotation.Resource; 
import org.springframework.stereotype.Service;
import com.chengxusheji.po.SpaceInfo;
import com.chengxusheji.po.UserInfo;
import com.chengxusheji.po.SpaceOrder;

import com.chengxusheji.mapper.SpaceOrderMapper;
@Service
public class SpaceOrderService {

	@Resource SpaceOrderMapper spaceOrderMapper;
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

    /*添加车位预约记录*/
    public void addSpaceOrder(SpaceOrder spaceOrder) throws Exception {
    	spaceOrderMapper.addSpaceOrder(spaceOrder);
    }

    /*按照查询条件分页查询车位预约记录*/
    public ArrayList<SpaceOrder> querySpaceOrder(SpaceInfo spaceObj,UserInfo userObj,String startTime,String endTime,String shenHeState,String orderTime,int currentPage) throws Exception { 
     	String where = "where 1=1";
    	if(null != spaceObj && spaceObj.getSpaceId()!= null && spaceObj.getSpaceId()!= 0)  where += " and t_spaceOrder.spaceObj=" + spaceObj.getSpaceId();
    	if(null != userObj &&  userObj.getUser_name() != null  && !userObj.getUser_name().equals(""))  where += " and t_spaceOrder.userObj='" + userObj.getUser_name() + "'";
    	if(!startTime.equals("")) where = where + " and t_spaceOrder.startTime like '%" + startTime + "%'";
    	if(!endTime.equals("")) where = where + " and t_spaceOrder.endTime like '%" + endTime + "%'";
    	if(!shenHeState.equals("")) where = where + " and t_spaceOrder.shenHeState like '%" + shenHeState + "%'";
    	if(!orderTime.equals("")) where = where + " and t_spaceOrder.orderTime like '%" + orderTime + "%'";
    	int startIndex = (currentPage-1) * this.rows;
    	return spaceOrderMapper.querySpaceOrder(where, startIndex, this.rows);
    }

    /*按照查询条件查询所有记录*/
    public ArrayList<SpaceOrder> querySpaceOrder(SpaceInfo spaceObj,UserInfo userObj,String startTime,String endTime,String shenHeState,String orderTime) throws Exception  { 
     	String where = "where 1=1";
    	if(null != spaceObj && spaceObj.getSpaceId()!= null && spaceObj.getSpaceId()!= 0)  where += " and t_spaceOrder.spaceObj=" + spaceObj.getSpaceId();
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_spaceOrder.userObj='" + userObj.getUser_name() + "'";
    	if(!startTime.equals("")) where = where + " and t_spaceOrder.startTime like '%" + startTime + "%'";
    	if(!endTime.equals("")) where = where + " and t_spaceOrder.endTime like '%" + endTime + "%'";
    	if(!shenHeState.equals("")) where = where + " and t_spaceOrder.shenHeState like '%" + shenHeState + "%'";
    	if(!orderTime.equals("")) where = where + " and t_spaceOrder.orderTime like '%" + orderTime + "%'";
    	return spaceOrderMapper.querySpaceOrderList(where);
    }

    /*查询所有车位预约记录*/
    public ArrayList<SpaceOrder> queryAllSpaceOrder()  throws Exception {
        return spaceOrderMapper.querySpaceOrderList("where 1=1");
    }

    /*当前查询条件下计算总的页数和记录数*/
    public void queryTotalPageAndRecordNumber(SpaceInfo spaceObj,UserInfo userObj,String startTime,String endTime,String shenHeState,String orderTime) throws Exception {
     	String where = "where 1=1";
    	if(null != spaceObj && spaceObj.getSpaceId()!= null && spaceObj.getSpaceId()!= 0)  where += " and t_spaceOrder.spaceObj=" + spaceObj.getSpaceId();
    	if(null != userObj &&  userObj.getUser_name() != null && !userObj.getUser_name().equals(""))  where += " and t_spaceOrder.userObj='" + userObj.getUser_name() + "'";
    	if(!startTime.equals("")) where = where + " and t_spaceOrder.startTime like '%" + startTime + "%'";
    	if(!endTime.equals("")) where = where + " and t_spaceOrder.endTime like '%" + endTime + "%'";
    	if(!shenHeState.equals("")) where = where + " and t_spaceOrder.shenHeState like '%" + shenHeState + "%'";
    	if(!orderTime.equals("")) where = where + " and t_spaceOrder.orderTime like '%" + orderTime + "%'";
        recordNumber = spaceOrderMapper.querySpaceOrderCount(where);
        int mod = recordNumber % this.rows;
        totalPage = recordNumber / this.rows;
        if(mod != 0) totalPage++;
    }

    /*根据主键获取车位预约记录*/
    public SpaceOrder getSpaceOrder(int orderId) throws Exception  {
        SpaceOrder spaceOrder = spaceOrderMapper.getSpaceOrder(orderId);
        return spaceOrder;
    }

    /*更新车位预约记录*/
    public void updateSpaceOrder(SpaceOrder spaceOrder) throws Exception {
        spaceOrderMapper.updateSpaceOrder(spaceOrder);
    }

    /*删除一条车位预约记录*/
    public void deleteSpaceOrder (int orderId) throws Exception {
        spaceOrderMapper.deleteSpaceOrder(orderId);
    }

    /*删除多条车位预约信息*/
    public int deleteSpaceOrders (String orderIds) throws Exception {
    	String _orderIds[] = orderIds.split(",");
    	for(String _orderId: _orderIds) {
    		spaceOrderMapper.deleteSpaceOrder(Integer.parseInt(_orderId));
    	}
    	return _orderIds.length;
    }
}
