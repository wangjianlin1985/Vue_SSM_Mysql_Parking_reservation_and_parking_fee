package com.chengxusheji.po;

import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;
import org.json.JSONException;
import org.json.JSONObject;
import com.client.utils.JsonUtils;
import com.client.utils.SessionConsts;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SpaceOrder {
    /*记录id*/
    private Integer orderId;
    public Integer getOrderId(){
        return orderId;
    }
    public void setOrderId(Integer orderId){
        this.orderId = orderId;
    }

    /*预约车位*/
    private SpaceInfo spaceObj;
    public SpaceInfo getSpaceObj() {
        return spaceObj;
    }
    public void setSpaceObj(SpaceInfo spaceObj) {
        this.spaceObj = spaceObj;
    }

    /*预约用户*/
    private UserInfo userObj;
    public UserInfo getUserObj() {
        return userObj;
    }
    public void setUserObj(UserInfo userObj) {
        this.userObj = userObj;
    }

    /*预约开始时间*/
    @NotEmpty(message="预约开始时间不能为空")
    private String startTime;
    public String getStartTime() {
        return startTime;
    }
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /*预约结束时间*/
    @NotEmpty(message="预约结束时间不能为空")
    private String endTime;
    public String getEndTime() {
        return endTime;
    }
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /*预计费用*/
    @NotNull(message="必须输入预计费用")
    private Float orderMoney;
    public Float getOrderMoney() {
        return orderMoney;
    }
    public void setOrderMoney(Float orderMoney) {
        this.orderMoney = orderMoney;
    }

    /*审核状态*/
    @NotEmpty(message="审核状态不能为空")
    private String shenHeState;
    public String getShenHeState() {
        return shenHeState;
    }
    public void setShenHeState(String shenHeState) {
        this.shenHeState = shenHeState;
    }

    /*预约备注*/
    private String orderMemo;
    public String getOrderMemo() {
        return orderMemo;
    }
    public void setOrderMemo(String orderMemo) {
        this.orderMemo = orderMemo;
    }

    /*下单时间*/
    @NotEmpty(message="下单时间不能为空")
    private String orderTime;
    public String getOrderTime() {
        return orderTime;
    }
    public void setOrderTime(String orderTime) {
        this.orderTime = orderTime;
    }

    @JsonIgnore
    public JSONObject getJsonObject() throws JSONException {
    	JSONObject jsonSpaceOrder=new JSONObject(); 
		jsonSpaceOrder.accumulate("orderId", this.getOrderId());
		jsonSpaceOrder.accumulate("spaceObj", this.getSpaceObj().getSpaceNo());
		jsonSpaceOrder.accumulate("spaceObjPri", this.getSpaceObj().getSpaceId());
		jsonSpaceOrder.accumulate("userObj", this.getUserObj().getName());
		jsonSpaceOrder.accumulate("userObjPri", this.getUserObj().getUser_name());
		jsonSpaceOrder.accumulate("startTime", this.getStartTime().length()>19?this.getStartTime().substring(0,19):this.getStartTime());
		jsonSpaceOrder.accumulate("endTime", this.getEndTime().length()>19?this.getEndTime().substring(0,19):this.getEndTime());
		jsonSpaceOrder.accumulate("orderMoney", this.getOrderMoney());
		jsonSpaceOrder.accumulate("shenHeState", this.getShenHeState());
		jsonSpaceOrder.accumulate("orderMemo", this.getOrderMemo());
		jsonSpaceOrder.accumulate("orderTime", this.getOrderTime().length()>19?this.getOrderTime().substring(0,19):this.getOrderTime());
		return jsonSpaceOrder;
    }

    @Override
	public String toString() {
		return JsonUtils.toJson(this);
	}
}