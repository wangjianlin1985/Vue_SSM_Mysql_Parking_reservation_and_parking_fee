package com.chengxusheji.mapper;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import com.chengxusheji.po.SpaceOrder;

public interface SpaceOrderMapper {
	/*添加车位预约信息*/
	public void addSpaceOrder(SpaceOrder spaceOrder) throws Exception;

	/*按照查询条件分页查询车位预约记录*/
	public ArrayList<SpaceOrder> querySpaceOrder(@Param("where") String where,@Param("startIndex") int startIndex,@Param("pageSize") int pageSize) throws Exception;

	/*按照查询条件查询所有车位预约记录*/
	public ArrayList<SpaceOrder> querySpaceOrderList(@Param("where") String where) throws Exception;

	/*按照查询条件的车位预约记录数*/
	public int querySpaceOrderCount(@Param("where") String where) throws Exception; 

	/*根据主键查询某条车位预约记录*/
	public SpaceOrder getSpaceOrder(int orderId) throws Exception;

	/*更新车位预约记录*/
	public void updateSpaceOrder(SpaceOrder spaceOrder) throws Exception;

	/*删除车位预约记录*/
	public void deleteSpaceOrder(int orderId) throws Exception;

}
